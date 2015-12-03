package com.razor.memories.factories;

import com.mongodb.MongoClient;
import com.razor.memories.configuration.PersistenceConfig;
import com.razor.memories.handlers.StoryHandler;
import org.aeonbits.owner.ConfigFactory;

/**
 * Created by paulhemmings on 12/3/15.
 */
public class StoryHandlerFactory {

    public StoryHandler buildStoryHandler() {
        PersistenceConfig persistenceConfig = buildPersistenceConfig();
        return new StoryHandler().initialize(buildMongoClient(persistenceConfig.mongoHost()), persistenceConfig.mongoDatabase());
    }

    protected PersistenceConfig buildPersistenceConfig() {
        return ConfigFactory.create(PersistenceConfig.class);
    }

    protected MongoClient buildMongoClient(String hostName) {
        return new MongoClient(hostName);
    }
}
