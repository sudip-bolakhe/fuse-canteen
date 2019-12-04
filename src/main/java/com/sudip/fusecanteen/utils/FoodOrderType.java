package com.sudip.fusecanteen.utils;

public enum  FoodOrderType {
    ORDERED, REQUESTED;

    public static boolean contains(String value){
        for(FoodOrderType type:values()) {
            return type.name().equalsIgnoreCase(value);
        }
        return false;
    };
}
