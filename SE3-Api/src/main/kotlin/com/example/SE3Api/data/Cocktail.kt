package com.example.SE3Api.data

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("cocktail")
data class Cocktail (

        @Id
        var id: String,
        var name: String,
        var ingredients: String,
        var difficulty: String,
        var alcoholic: Boolean,
        var taste: String

)
