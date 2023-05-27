package com.example.cocktailapi.datascource.cocktail

import com.example.cocktailapi.datascource.dto.Cocktail
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.aggregation.Aggregation
import org.springframework.stereotype.Service

@Service
class CocktailService(private val mongoTemplate: MongoTemplate) {

    fun getDistinctIngredients(): List<String> {
        val aggregation = Aggregation.newAggregation(
            Aggregation.unwind("ingredients"),
            Aggregation.group("ingredients").addToSet("ingredients").`as`("distinctStrings"),
            Aggregation.project().and("distinctStrings").`as`("distinctStrings")
        )

        val results = mongoTemplate.aggregate(aggregation, "cocktail", String::class.java)
        return results.toList()
    }
}
