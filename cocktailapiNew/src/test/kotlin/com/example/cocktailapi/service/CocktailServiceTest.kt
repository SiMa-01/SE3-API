package com.example.cocktailapi.service

import com.example.cocktailapi.dto.Cocktail
import org.bson.Document
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.*
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.aggregation.AggregationResults

import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.eq
import org.mockito.Mockito.`when`
import org.mockito.InjectMocks
import org.mockito.Mock
import org.springframework.data.mongodb.core.aggregation.Aggregation

@ExtendWith(MockitoExtension::class)
class CocktailServiceTest {

    @Mock
    lateinit var mongoTemplate: MongoTemplate

    @InjectMocks
    lateinit var cocktailService: CocktailService

    @Test
    fun testGetIngredients() {
        // Given
        val ingredient = "Lime"

        // Mocking des MongoDB Templates
        val expectedResults = listOf("Lime Juice", "Lime Wedges")
        val aggregationResults = AggregationResults(expectedResults, Document())
        `when`(mongoTemplate.aggregate(any(Aggregation::class.java), eq("cocktail"), eq(String::class.java)))
                .thenReturn(aggregationResults)

        // When
        val result = cocktailService.getIngredients(ingredient)
        // Then
        assertEquals(expectedResults, result)
    }

    @Test
    fun testFindCocktail() {
        // Given
        val cocktail = Cocktail(null, "Mojito", arrayOf("Mint", "Sugar", "Lime"), "easy", true, "bad", "Gebe alle Zutaten in ein Glas")

        // Mocking MongoTemplate
        whenever(mongoTemplate.find(argThat<Query> { it ->
            it.queryObject.getString("difficulty") == "easy" &&
            it.queryObject.getString("taste") == "bad"

        }, any<Class<Cocktail>>()))
                .thenReturn(listOf(cocktail))

        // When
        val result = cocktailService.findCocktail(null, "Mojito", "bad", null, null, "easy")

        // Then
        assertEquals(1, result.size)
        assertEquals("Mojito", result[0].name)

        verify(mongoTemplate).find(argThat {
            queryObject.getString("taste") == "bad" &&
            queryObject.getString("difficulty") == "easy"
        }, eq(Cocktail::class.java))
    }

    @Test
    fun testGetTastes() {

        // Mocking des MongoDB Templates
        val expectedResults = listOf("sweet", "strong")
        val aggregationResults = AggregationResults(expectedResults, Document())
        `when`(mongoTemplate.aggregate(any(Aggregation::class.java), eq("cocktail"), eq(String::class.java)))
                .thenReturn(aggregationResults)

        // When
        val result = cocktailService.getTastes()
        // Then
        assertEquals(expectedResults, result)

    }
}