package com.extern;

public interface RedisLikeClient {
    /**
     * Stores given key-value pair, overwriting any existing association.
     *
     * @param key   key, cannot be null
     * @param value value, cannot be null
     * @return value previously associated with given key, or null if not allowed
     */
    byte[] put(String key, byte[] value);

    /**
     * Returns value associated with given key.
     *
     * @param key   key, cannot be null
     * @param value value, cannot be null
     * @return value associated with given key, if there is any, or null otherwise
     */
    byte[] get(String key);

    /**
     * Deletes the key-value for given association.
     *
     * @param key key, cannot be null
     * @return value that was deleted, if where was any, or null otherwise
     */
    byte[] delete(String key);
}
