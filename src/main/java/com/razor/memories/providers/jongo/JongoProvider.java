package com.razor.memories.providers.jongo;

import com.mongodb.DB;
import com.razor.memories.providers.ModelProvider;
import org.bson.types.ObjectId;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paulhemmings on 12/3/15.
 */

public class JongoProvider<T> implements ModelProvider<T> {  // NOSONAR

    private final MongoCollection collection;
    private final Class<T> clazz;

    public JongoProvider(DB db, String collection, Class<T> clazz) {
        this.clazz = clazz;
        Jongo jongo = new Jongo(db);
        this.collection = jongo.getCollection(collection);
    }

    @Override
    public List<T> findAll() {
        List<T> models = new ArrayList<>();
        MongoCursor<T> cursor = this.collection.find().as(this.clazz);
        while (cursor.hasNext()) {
            T model = cursor.next();
            models.add(model);
        }
        return models;
    }

    @Override
    public T findById(String id) {
        return this.collection.findOne(new ObjectId(id)).as(this.clazz);
    }

    @Override
    public T update(T model, String id) {
        collection.save(model);
        return model;
    }

    @Override
    public boolean delete(String id) {
        return collection.remove(new ObjectId(id)).wasAcknowledged();
    }

    @Override
    public boolean insert(List<T> models) {
        return collection.insert(models.toArray()).wasAcknowledged();
    }

    @Override
    public List<T> find(String key, String value) {
        return this.find("{'" + key + "' : " + "'" + value + "'}");
    }

    @Override
    public List<T> find(String complex) {
        List<T> models = new ArrayList<>();
        MongoCursor<T> cursor = this.collection.find(complex).as(this.clazz);
        while (cursor.hasNext()) {
            T model = cursor.next();
            models.add(model);
        }
        return models;
    }
}
