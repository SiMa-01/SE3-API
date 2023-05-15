package com.example.cocktailapi.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Cocktail (
    @JsonProperty("cocktail_number")
    val cocktailNumber: Int,

    @JsonProperty("name")
    val name: String,

    @JsonProperty("alcoholic")
    val alcoholic: Boolean
)