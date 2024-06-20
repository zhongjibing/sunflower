package com.icezhg.sunflower.quartz.util;

public record InvokeParameter(Class<?> type, Object value) {
    @Override
    public String toString() {
        return type.getName() + ": " + value;
    }
}
