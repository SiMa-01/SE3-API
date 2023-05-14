package com.example.SE3Api.inface

import com.example.SE3Api.data.Cocktail
import org.springframework.data.mongodb.repository.MongoRepository

interface CocktailRepository : MongoRepository<Cocktail, String>
