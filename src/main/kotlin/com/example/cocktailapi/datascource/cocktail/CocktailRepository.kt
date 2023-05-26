package com.example.cocktailapi.datascource.cocktail


import com.example.cocktailapi.datascource.dto.Cocktail
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query

interface CocktailRepository: MongoRepository<Cocktail, String> {
 @Query("{'taste': ?0}")
 fun findByTaste(taste: String): List<Cocktail>

 @Query("{'name': ?0}")
 fun findByName(name: String): List<Cocktail>

 @Query("{'ingredients': {'\$all': [?0]}}")
 fun findByIngredient(ingredient: String): List<Cocktail>?

 @Query("{'alcoholic': ?0}")
 fun findByAlcoholic(alcoholic: Boolean): List<Cocktail>

 @Query("{'difficulty': ?0}")
 fun findByDifficulty(difficulty: String): List<Cocktail>

}
