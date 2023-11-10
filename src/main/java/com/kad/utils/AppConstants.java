package com.kad.utils;

import org.springframework.beans.factory.annotation.Value;

public class AppConstants {

    public static final String[] PUBLIC_URLS={
            "/user/**",
            "/post/getAllPostsByTime/**",
            "/send",
            "/applyJob",
            "/getAllGallery",
            "/gallery/**"
    };



    //JWT
    public static final String JWT_SECRET_KEY="KADETECH";
    public static final Long JWT_EXPIRATION_SEC=86400000L;
}
