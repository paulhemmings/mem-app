package com.razor.memories.factories;

import com.mongodb.MongoClient;
import com.razor.memories.configuration.PersistenceConfig;
import com.razor.memories.models.MongoModel;
import com.razor.memories.providers.ModelProvider;
import com.razor.memories.providers.jongo.JongoProvider;
import com.razor.memories.providers.mongo.MongoProvider;
import com.razor.memories.providers.mongo.SimpleMongoModelAdapter;
import com.razor.memories.providers.morphia.MorphiaProvider;


/**
 * Created by paulhemmings on 12/3/15.
 */

public class ModelProviderFactory<T extends MongoModel> {

    public class ORM {
        public static final String JONGO = "JONGO";
        public static final String MONGO = "MONGO";
        public static final String MORPHIA = "MORPHIA";
    }

    /*
     * Build provider based on configuration
     */

    public ModelProvider<T> buildProvider(String orm,
                                          MongoClient client,
                                          String database,
                                          String collection,
                                          Class<T> clazz) throws Exception {
        ModelProvider provider;
        switch (orm) {
            case ORM.MONGO:
                provider = buildMongoProvider(client, database, collection, clazz);
                break;
            case ORM.JONGO:
                provider = buildJongoProvider(client, database, collection, clazz);
                break;
            case ORM.MORPHIA:
                provider = buildMorphiaProvider(client, database, clazz);
                break;
            default:
                throw new Exception("Invalid provider configuration");
        }
        return provider;
    }

    /**
     * Use the Jongo ORM
     * @param client
     * @return
     */

    private ModelProvider<T> buildJongoProvider(MongoClient client, String database, String collection, Class<T> clazz) {
        return new JongoProvider<>(client.getDB(database), collection, clazz);
    }

    /**
     * Use the standard OOTB Mongo provider
     * @param client
     * @return
     */

    private ModelProvider<T> buildMongoProvider(MongoClient client, String database, String collection, Class<T> clazz ) {
        return new MongoProvider<>(client.getDatabase(database).getCollection(collection), new SimpleMongoModelAdapter<>(clazz));
    }

    /**
     * Use Morphia ORM
     * @param client
     * @param database
     * @param clazz
     * @return
     */

    private ModelProvider<T> buildMorphiaProvider(MongoClient client, String database, Class<T> clazz) {
        return new MorphiaProvider<>(client, database, clazz);
    }
}
