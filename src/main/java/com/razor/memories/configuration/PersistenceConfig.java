package com.razor.memories.configuration;

import org.aeonbits.owner.Config;

/**
 * Created by paulhemmings on 12/3/15.
 */

@Config.Sources({"file:${CONFIG_FILE}"})
public interface PersistenceConfig extends Config {

    @Config.Key("persistence.mongo.host")
    @DefaultValue("localhost")
    String mongoHost();

    @Config.Key("persistence.mongo.database")
    @DefaultValue("stories")
    String mongoDatabase();

    @Config.Key("persistence.mongo.provider")
    @DefaultValue("jongo")
    String mongoProvider();

    @Config.Key("persistence.mongo.username")
    @DefaultValue("")
    String mongoUser();

    @Config.Key("persistence.mongo.password")
    @DefaultValue("")
    String mongoPassword();
}
