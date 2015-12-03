package com.razor.memories.providers;

import java.util.List;

/**
 * Created by paulhemmings on 12/3/15.
 */

public interface ModelProvider<T> {
    boolean insert(List<T> model);
    boolean delete(String id);
    List<T> findAll();
    List<T> find(String key, String value);
    List<T> find(String complex);
    T findById(String id);
    T update(T model, String id);
}

