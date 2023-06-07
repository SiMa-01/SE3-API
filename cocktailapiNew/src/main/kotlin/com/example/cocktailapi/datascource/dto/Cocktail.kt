package com.example.cocktailapi.datascource.dto

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Cocktail(
    @Id val _id: String,
    val name: String,
    val ingredients: Array<String>,
    val difficulty: String,
    val alcoholic: Boolean,
    val taste: String,
    val preparation: String
)

