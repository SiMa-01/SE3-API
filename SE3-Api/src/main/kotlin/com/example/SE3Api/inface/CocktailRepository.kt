package com.example.SE3Api.inface

import com.example.SE3Api.data.Cocktail
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query

interface CocktailRepository : MongoRepository<Cocktail, String> {


    fun findByName(name: String): Cocktail?

    fun findByIngredients(ingredient: String): List<Cocktail>



}