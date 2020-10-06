package com.prep;

import com.extern.impl.RedisClientImpl;
import com.extern.impl.RedisLikeFactory;

import java.util.Properties;

public class Wrapper {
    private RedisClientImpl client;

    public Wrapper(Properties properties) {
        RedisLikeFactory.create(properties);

    }
}
