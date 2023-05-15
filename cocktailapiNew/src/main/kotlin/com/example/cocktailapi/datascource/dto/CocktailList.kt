package com.example.cocktailapi.datascource.dto

import com.example.cocktailapi.model.Cocktail

data class CocktailList (
    val results: Collection<Cocktail>
)