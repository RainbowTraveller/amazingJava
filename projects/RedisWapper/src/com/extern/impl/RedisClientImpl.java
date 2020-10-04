package com.extern.impl;

import com.extern.RedisLikeClient;

public class RedisClientImpl implements RedisLikeClient {
    byte[] put(String key, byte[] value) {
       return new byte[1];
    }

    byte[] get(String key) {
        return new byte[1];
    }

    byte[] delete(String key) {
        return new byte[1];
    }
}
