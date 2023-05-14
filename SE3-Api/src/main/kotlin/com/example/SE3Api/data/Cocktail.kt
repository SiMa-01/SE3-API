package com.example.SE3Api.data

import org.springframework.data.annotation.Id

data class Cocktail(
    @Id
    val id: Long = 0,
    val name: String,
    val ingredients: String,
    val instructions: String
)
