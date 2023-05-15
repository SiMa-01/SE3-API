package com.example.cocktailapi.datascource

import com.example.cocktailapi.model.Cocktail

interface CocktailDataSource {

    fun retrieveCocktails(): Collection<Cocktail>

    fun retrieveCocktail(cocktailNumber: Int): Cocktail
}