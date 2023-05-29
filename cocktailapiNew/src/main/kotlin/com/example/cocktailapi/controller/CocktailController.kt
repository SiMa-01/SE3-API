package com.example.cocktailapi.controller

import com.example.cocktailapi.datascource.dto.Cocktail
import com.example.cocktailapi.datascource.cocktail.CocktailRepository
import com.example.cocktailapi.datascource.cocktail.CocktailService
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class CocktailController (private val cocktailRepository: CocktailRepository, private val cocktailService: CocktailService) {

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e: NoSuchElementException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleBadRequest(e: IllegalArgumentException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.BAD_REQUEST)

    @GetMapping("/cocktails")
    fun getCocktailById(@RequestParam(required = false) id: String?,
                        @RequestParam(required = false) name: String?,
                        @RequestParam(required = false) taste:String?,
                        @RequestParam(required = false) ingredient: List<String>?,
                        @RequestParam(required = false) alcoholic: Boolean?,
                        @RequestParam(required = false) difficulty: String?): ResponseEntity<List<Cocktail>>{
        return ResponseEntity.ok(cocktailService.findCocktail(id, name, taste, ingredient, alcoholic, difficulty))
    }

    @PostMapping("/add")
    fun createCocktail(@RequestBody cocktail: Cocktail){
        val savedCocktail = cocktailRepository.save(cocktail)
    }

    @GetMapping("/ingredients")
    fun getDistinctIngredients(@RequestParam(required = false) ingredient: String?): ResponseEntity<List<String>> {
        val distinctStrings = cocktailService.getIngredients(ingredient)

        val ingredients = distinctStrings.map { json ->
            val startIndex = json.indexOf('[')
            val endIndex = json.indexOf(']')
            val ingredient = json.substring(startIndex + 2, endIndex - 1)
            ingredient
        }
        return ResponseEntity.ok(ingredients)
    }
}