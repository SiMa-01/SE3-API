package com.example.cocktailapi.datascource.cocktail


import com.example.cocktailapi.datascource.dto.Cocktail
import org.springframework.data.mongodb.repository.MongoRepository

interface CocktailRepository: MongoRepository<Cocktail, String> {


}
