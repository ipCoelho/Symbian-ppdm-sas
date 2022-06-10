package com.ppdm.androidtest.remote;

public class APIUtil {
    public APIUtil() {
    }

    public static final String API_URL = "http://10.107.144.11:3131";

    public static RouterInterface getUserInterface(){
        return RetrofitClient
            .getClient(API_URL)
            .create(RouterInterface.class);
    }

}







