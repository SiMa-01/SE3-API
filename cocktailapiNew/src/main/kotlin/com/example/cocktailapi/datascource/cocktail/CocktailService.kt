package com.example.cocktailapi.datascource.cocktail

import com.example.cocktailapi.datascource.dto.Cocktail
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.aggregation.Aggregation
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Service

@Service
class CocktailService(private val mongoTemplate: MongoTemplate) {

    fun getIngredients(ingredient: String?): List<String> {
        val regex = "^(?i)${ingredient?:""}.*(?-i)"
        print(regex)
        val unwindStage = Aggregation.unwind("ingredients")
        val groupStage = Aggregation.group("ingredients").addToSet("ingredients").`as`("distinctStrings")
        val matchStage = Aggregation.match(Criteria.where("distinctStrings").regex(regex))
        val projectStage = Aggregation.project().and("distinctStrings").`as`("distinctStrings")

        val pipeline = Aggregation.newAggregation(unwindStage, groupStage, matchStage, projectStage)

        val aggregationResults = mongoTemplate.aggregate(pipeline, "cocktail", String::class.java)
        return aggregationResults.toList()
    }

    fun findCocktail(id: String?, name: String?, taste:String?, ingredients: List<String>?, alcoholic: Boolean?, difficulty: String?): List<Cocktail>{
        val query = Query()

        if (!id.isNullOrBlank()) {
            query.addCriteria(Criteria.where("_id").`is`(id))
        }

        if (!name.isNullOrBlank()) {
            query.addCriteria(Criteria.where("name").`is`(name))
        }

        if (!taste.isNullOrBlank()) {
            query.addCriteria(Criteria.where("taste").`is`(taste))
        }
        //Gib alle Cocktails zurück, die mindestens einer der Zutaten haben.
        /*
        if(!ingredients.isNullOrEmpty()){
            query.addCriteria(Criteria.where("ingredients").`in`(ingredients))
        }*/
        //Gib alle Cocktails zurück, die alle der Zutaten enthalten.
        if(!ingredients.isNullOrEmpty()){
            query.addCriteria(Criteria.where("ingredients").all(ingredients))
        }
        if (alcoholic != null) {
            query.addCriteria(Criteria.where("alcoholic").`is`(alcoholic))
        }

        if (!difficulty.isNullOrBlank()) {
            query.addCriteria(Criteria.where("difficulty").`is`(difficulty))
        }

        return mongoTemplate.find(query, Cocktail::class.java)
    }
}
