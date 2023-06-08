package com.example.cocktailapi.repository


import com.example.cocktailapi.dto.Cocktail
import org.springframework.data.mongodb.repository.MongoRepository

interface CocktailRepository: MongoRepository<Cocktail, String> {


}
