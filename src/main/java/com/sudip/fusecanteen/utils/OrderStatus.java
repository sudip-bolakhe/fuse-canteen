package com.sudip.fusecanteen.utils;

public enum OrderStatus {
    PENDING, INPROGRESS, READY;

    public static boolean contains(String value){
        for(OrderStatus status:values()) {
            return status.name().equalsIgnoreCase(value);
        }
        return false;
    };
}
