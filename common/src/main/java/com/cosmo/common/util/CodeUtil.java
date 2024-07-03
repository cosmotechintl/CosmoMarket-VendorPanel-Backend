package com.cosmo.common.util;

import java.util.UUID;

public class CodeUtil {
    public static String generateCode(){
        return UUID.randomUUID().toString();
    }
}
