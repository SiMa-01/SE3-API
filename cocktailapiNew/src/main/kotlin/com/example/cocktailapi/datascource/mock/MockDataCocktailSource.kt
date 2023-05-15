package com.example.cocktailapi.datascource.mock

import com.example.cocktailapi.datascource.CocktailDataSource
import com.example.cocktailapi.model.Cocktail
import org.springframework.stereotype.Repository

@Repository("mock")
class MockDataCocktailSource : CocktailDataSource {

    val cocktails = mutableListOf(
        Cocktail(1, "Gin", true),
        Cocktail(2, "Vodka", true),
        Cocktail(3, "Water", false),
    )

    override fun retrieveCocktails(): Collection<Cocktail> = cocktails

    override fun retrieveCocktail(cocktailNumber: Int): Cocktail {
        return cocktails.find { it.cocktailNumber == cocktailNumber }!!
    }
}