package com.interview.demo;

import java.util.Optional;

public interface ICache<K, V> {

    Optional<V> get(K key);

    boolean set(K key, V value);

}
