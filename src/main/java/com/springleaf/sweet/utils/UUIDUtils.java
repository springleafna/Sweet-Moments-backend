package com.springleaf.sweet.utils;

import java.util.UUID;

public class UUIDUtils {
    
    public static String generateUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
    
    public static String generateShortUUID() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 16);
    }
}