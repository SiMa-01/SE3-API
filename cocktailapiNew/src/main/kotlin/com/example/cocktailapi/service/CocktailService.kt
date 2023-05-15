package com.example.cocktailapi.service

import com.example.cocktailapi.datascource.CocktailDataSource
import com.example.cocktailapi.model.Cocktail
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
class CocktailService(@Qualifier("mock") private val dataSource: CocktailDataSource) {
    fun getCocktails(): Collection<Cocktail> = dataSource.retrieveCocktails()

    fun getCocktail(cocktailNumber: Int): Cocktail = dataSource.retrieveCocktail(cocktailNumber)

}