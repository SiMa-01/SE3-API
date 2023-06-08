package com.example.cocktailapi.config

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClients
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.data.mongodb.MongoDatabaseFactory
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.convert.MappingMongoConverter

@Configuration
class MonoConfig(private val env: Environment) : AbstractMongoClientConfiguration() {

    override fun getDatabaseName(): String {
        return "mixnfix"
    }

    override fun mongoClientSettings(): MongoClientSettings {
        val connectionString = ConnectionString(env.getProperty("mongodb.connectionstring")!!)
        return MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .build()
    }

    @Bean
    override fun mongoTemplate (databaseFactory: MongoDatabaseFactory, converter: MappingMongoConverter): MongoTemplate {
        return MongoTemplate(MongoClients.create(mongoClientSettings()), "mixnfix")
    }
}