package com.interview.demo;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Optional;

class LRUCache<K, V> implements ICache<K, V> {

    HashMap<K, V> cache = new HashMap<>();
    LinkedList<K> lruLinkedList;
    int size;

    public LRUCache(int size) {
        this.size = size;
        this.cache = new HashMap<>();
        this.lruLinkedList = new LinkedList<>();
    }

    @Override
    public Optional<V> get(K key) {
        if (cache.containsKey(key)) {
            lruLinkedList.remove(key);
            lruLinkedList.addFirst(key);
            return Optional.of(cache.get(key));
        } else {
            return null;
        }
    }

    @Override
    public boolean set(K key, V value) {

        if (cache.size() < this.size) {
            if (!cache.containsKey(key)) {
                System.out.println(lruLinkedList.toString());
                cache.put(key, value);
                lruLinkedList.addFirst(key);
            } else {
                cache.put(key, value);
                lruLinkedList.remove(key);
                lruLinkedList.addFirst(key);
            }

        } else {
            K removedElement = lruLinkedList.removeLast();
            cache.remove(removedElement);
            cache.put(key, value);
            lruLinkedList.addFirst(key);
        }

        return true;

    }

    public HashMap<K, V> getCache() {
        return cache;
    }

}
