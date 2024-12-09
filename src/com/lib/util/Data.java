package com.lib.util;

public class Data {

    public static NullPointerException checkArr (Object[] arr) {
        if (arr==null)
            return new NullPointerException(arr+" is null");
        for (Object o : arr)
            if (o==null)
                return new NullPointerException(arr+" contains null");
        return null;
    }

}
