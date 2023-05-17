package com.example.cocktailapi.datascource.mongodb
/*
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
class MongoConfig(
    @Value("\${spring.data.mongodb.uri}")
    private val mongoUri: String
) {
    fun getMongoUri(): String {
        return mongoUri
    }
}*/
import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClients
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.MongoDatabaseFactory
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.convert.MappingMongoConverter

@Configuration
class MongoConfig : AbstractMongoClientConfiguration() {

    override fun getDatabaseName(): String {
        return "mixnfix"
    }

    override fun mongoClientSettings(): MongoClientSettings {
        val connectionString = ConnectionString("mongodb+srv://testSe:test@cluster0.op0zm27.mongodb.net/?retryWrites=true&w=majority")
        return MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .build()
    }

    @Bean
    override fun mongoTemplate(databaseFactory: MongoDatabaseFactory, converter: MappingMongoConverter): MongoTemplate {
        return MongoTemplate(MongoClients.create(mongoClientSettings()), "mixnfix")
    }
}