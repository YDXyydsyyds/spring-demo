package com.ydx.spring.core;


import com.ydx.spring.core.annotation.BeanY;
import com.ydx.spring.core.annotation.Di;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class AnnotationApplicationContext implements ApplicationContext{
    //存储bean的容器
    private HashMap<Class, Object> beanFactory = new HashMap<>();
    //去掉绝对路径
    private static String rootPath;
    @Override
    public Object getBean(Class clazz) {
        return beanFactory.get(clazz);
    }

    /**
     * 根据包扫描加载bean
     * @param basePackage
     */
    public AnnotationApplicationContext(String basePackage){
        try {
            String packageDirName = basePackage.replaceAll("\\.", "\\\\");
            Enumeration<URL> dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
            while(dirs.hasMoreElements()){
                URL url = dirs.nextElement();
                String filePath = URLDecoder.decode(url.getFile(), "utf-8");
                rootPath = filePath.substring(0, filePath.length() - packageDirName.length()); //这行代码有点难理解
                loadBean(new File(filePath));
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
          loadDi();
    }
    private void loadBean(File fileParent){
        if(fileParent.isDirectory()){
            File[] childrenFiles = fileParent.listFiles();
            if(childrenFiles == null || childrenFiles.length == 0){
                return;
            }
            for (File child : childrenFiles) {
                if(child.isDirectory()){
                    //如果是文件夹，继续调用该方法，递归
                    loadBean(child);
                }else {
                    //通过文件路径转变成全类名，第一步把绝对路径部分去掉
                    String pathWithClass = child.getAbsolutePath().substring(rootPath.length() - 1);
                    //选中class文件
                    if(pathWithClass.contains(".class")){
                        //con.ydx.dao.UserDao
                        //去掉.class后缀，并且把\替换成.
                        String fullName = pathWithClass.replaceAll("\\\\",".").replace(".class","");
                        try {
                           Class<?> aClass = Class.forName(fullName);
                           //把非接口的类实例化放在map中
                            if(!aClass.isInterface()){
                                BeanY annotation = aClass.getAnnotation(BeanY.class);
                                if(annotation != null){
                                    Object instance = aClass.newInstance();
                                    //判断一下又没有接口
                                    if(aClass.getInterfaces().length > 0){
                                        //如果有接口把接口的class当成key，实例对象当成value
                                        System.out.println("正在加载【" + aClass.getInterfaces()[0]+ "】,实例对象是：" + instance.getClass().getName());
                                        beanFactory.put(aClass.getInterfaces()[0],instance);
                                    }else {
                                        //如果没接口把自己的class当成key，实例对象当成value
                                        System.out.println("正在加载【" + aClass.getName() +"】,实例对象是：" + instance.getClass().getName());
                                        beanFactory.put(aClass, instance);
                                    }
                                }
                            }
                        }catch (ClassNotFoundException | IllegalAccessException | InstantiationException e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    /**
     * 依赖注入
     */
    private void loadDi(){
        for (Map.Entry<Class, Object> entry : beanFactory.entrySet()) {
            Object obj = entry.getValue(); //已经放进容器的对象
            Class<?> aClass = obj.getClass();
            Field[] declaredFields = aClass.getDeclaredFields();
            for (Field field : declaredFields) {
                Di annotation = field.getAnnotation(Di.class);
                if(annotation != null){
                    field.setAccessible(true);
                    try {
                        System.out.println("正在给【"+ obj.getClass().getName() + "】属性【" + field.getName() + "】注入值【" + beanFactory.get(field.getType()).getClass().getName() + "】");
                        field.set(obj, beanFactory.get(field.getType()));
                    }catch (IllegalAccessException e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}