package com.razor.memories.providers.morphia;

import com.mongodb.MongoClient;
import com.razor.memories.providers.ModelProvider;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

import java.util.List;

public class MorphiaProvider<T> implements ModelProvider<T> {

    private Class<T> clazz;
    private Datastore ds;


    public MorphiaProvider(MongoClient client, String database, Class<T> clazz) {
        this.clazz = clazz;
        this.ds = new Morphia().map(clazz).createDatastore(client, database);
    }

    @Override
    public boolean insert(List<T> model) {
        model.stream().forEach(ds::save);
        return true;
    }

    @Override
    public boolean delete(String id) {
        Query q = this.ds.createQuery(this.clazz).filter("_id =", id).limit(1);
        ds.delete(q);
        return true;
    }

    @Override
    public List<T> findAll() {
        Query q = this.ds.createQuery(this.clazz);
        return q.asList();
    }

    @Override
    public List<T> find(String key, String value) {
        Query q = this.ds.createQuery(this.clazz).filter(key + " =", value);
        return q.asList();
    }

    @Override
    public List<T> find(String complex) {
        Query q = this.ds.createQuery(this.clazz).where(complex).limit(1);
        return q.asList();
    }

    @Override
    public T findById(String id) {
        Query q = this.ds.createQuery(this.clazz).filter("_id =", id).limit(1);
        return (T) q.get();
    }

    @Override
    public T update(T model, String id) {
        ds.save(model);
        return model;
    }
}
