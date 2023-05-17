package com.example.cocktailapi.datascource.mongodb


import com.example.cocktailapi.datascource.dto.Cocktail
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.http.ResponseEntity

interface CocktailRepository: MongoRepository<Cocktail, String>{
 @Query("{'alcoholic': ?0")
 fun findByAlcoholic(taste: String): ResponseEntity<List<Cocktail>>
}