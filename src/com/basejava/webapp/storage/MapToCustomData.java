package com.basejava.webapp.storage;

@FunctionalInterface
public interface MapToCustomData<T> {
    T get();
}
