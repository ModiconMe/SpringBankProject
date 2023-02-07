package com.modicon.user.core.config;

import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import org.axonframework.config.Configurer;
import org.axonframework.config.MessageMonitorFactory;
import org.axonframework.eventhandling.tokenstore.TokenStore;
import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.extensions.mongo.DefaultMongoTemplate;
import org.axonframework.extensions.mongo.MongoTemplate;
import org.axonframework.extensions.mongo.eventsourcing.eventstore.MongoEventStorageEngine;
import org.axonframework.extensions.mongo.eventsourcing.eventstore.MongoFactory;
import org.axonframework.extensions.mongo.eventsourcing.eventstore.MongoSettingsFactory;
import org.axonframework.extensions.mongo.eventsourcing.tokenstore.MongoTokenStore;
import org.axonframework.messaging.Message;
import org.axonframework.monitoring.MessageMonitor;
import org.axonframework.monitoring.MultiMessageMonitor;
import org.axonframework.serialization.Serializer;
import org.axonframework.spring.config.AxonConfiguration;
import org.axonframework.spring.config.SpringConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class AxonConfig {
    @Value("${spring.data.mongodb.host:127.0.0.1}")
    private String mongoHost;

    @Value("${spring.data.mongodb.port:27017}")
    private int mongoPort;

    @Value("${spring.data.mongodb.port:user}")
    private String mongoDatabase;

    @Bean
    public MongoClient mongo() {
        var mongoFactory = new MongoFactory();
        var mongoSettingsFactory = new MongoSettingsFactory();
        mongoSettingsFactory.setMongoAddresses(Collections.singletonList(new ServerAddress(mongoHost, mongoPort)));
        mongoFactory.setMongoClientSettings(mongoSettingsFactory.createMongoClientSettings());

        return mongoFactory.createMongo();
    }

    @Bean
    public MongoTemplate axonMongoTemplate() {
        return DefaultMongoTemplate.builder()
                .mongoDatabase(mongo(), mongoDatabase)
                .build();
    }

    @Bean
    public TokenStore tokenStore(Serializer serializer) {
        return MongoTokenStore.builder()
                .mongoTemplate(axonMongoTemplate())
                .serializer(serializer)
                .build();
    }

//    @Bean
//    public EventStorageEngine storageEngine(MongoClient mongoClient) {
//        return MongoEventStorageEngine.builder()
//                .mongoTemplate(DefaultMongoTemplate.builder()
//                        .mongoDatabase(mongoClient)
//                        .build()
//                ).build();
//    }

//
//    @Bean
//    public EmbeddedEventStore eventStore(EventStorageEngine storageEngine, SpringConfigurer configuration) {
//        return EmbeddedEventStore.builder()
//                .storageEngine(storageEngine)
//                .messageMonitor(configuration.configureMessageMonitor(EventStore.class, "eventStore", messageMonitorFactory()))
//                .messageMonitor(configuration.configureMessageMonitor())
//                .build();
//    }

    public void configureMongoEventStorage(Configurer configurer, MongoTemplate mongoTemplate) {
        configurer.configureEmbeddedEventStore(
                config -> MongoEventStorageEngine.builder()
                        .mongoTemplate(mongoTemplate)
                        .build()
        );
    }

//    @Bean
//    public MessageMonitorFactory messageMonitorFactory() {
//        return (configuration, componentType, componentName) -> new MultiMessageMonitor<>();
//    }

}
