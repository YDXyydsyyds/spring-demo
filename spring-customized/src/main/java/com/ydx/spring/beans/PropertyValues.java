package com.ydx.spring.beans;

import java.util.ArrayList;
import java.util.List;

public class PropertyValues {
    private final List<PropertyValue> propertyValueList = new ArrayList<>();
    public void addPropertyValue(PropertyValue pv){
        for (int i = 0; i < this.propertyValueList.size(); i++) {
            PropertyValue currentPv = this.propertyValueList.get(i);
            if(currentPv.getName().equals(pv.getName())){
                this.propertyValueList.set(i,pv);
                return;
            }
        }
        this.propertyValueList.add(pv);
    }
    public PropertyValue[] getPropertyValues(){
        return this.propertyValueList.toArray(propertyValueList.toArray(new PropertyValue[0]));
    }
    public PropertyValue getPropertyValue(String name){
        for (int i = 0; i < this.propertyValueList.size(); i++) {
            PropertyValue pv = this.propertyValueList.get(i);
            if(pv.getName().equals(name)){
                return pv;
            }
        }
        return null;
    }
}
