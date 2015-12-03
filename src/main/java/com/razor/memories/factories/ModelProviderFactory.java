package com.razor.memories.factories;

import com.mongodb.MongoClient;
import com.razor.memories.models.MongoModel;
import com.razor.memories.providers.ModelProvider;
import com.razor.memories.providers.jongo.JongoProvider;


/**
 * Created by paulhemmings on 12/3/15.
 */

public class ModelProviderFactory<T extends MongoModel> {

    public class ORM {
        public static final String JONGO = "JONGO";
    }

    /*
     * Build provider based on configuration
     */

    public ModelProvider<T> buildProvider(String orm,
                                          MongoClient client,
                                          String database,
                                          String collection,
                                          Class<T> clazz) {
        ModelProvider provider;
        switch (orm) {
            case ORM.JONGO:
                provider = buildJongoProvider(client, database, collection, clazz);
                break;
            default:
                provider = buildJongoProvider(client, database, collection, clazz);
                break;
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
}
