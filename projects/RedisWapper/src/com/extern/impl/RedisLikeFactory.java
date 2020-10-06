package com.extern.impl;

import java.util.Properties;

public class RedisLikeFactory {

    public static RedisClientImpl create(Properties props) throws IllegalArgumentException, IllegalStateException {
        return new RedisClientImpl();
    }
}
