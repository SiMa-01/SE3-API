package com.example.cocktailapi.controller

import com.example.cocktailapi.dto.Cocktail
import com.example.cocktailapi.repository.CocktailRepository
import com.example.cocktailapi.service.CocktailService
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
    fun getCocktails(@RequestParam(required = false) id: String?,
                     @RequestParam(required = false) name: String?,
                     @RequestParam(required = false) taste:String?,
                     @RequestParam(required = false) ingredients: List<String>?,
                     @RequestParam(required = false) alcoholic: Boolean?,
                     @RequestParam(required = false) difficulty: String?): ResponseEntity<out List<Any>> {
        if(!id.isNullOrBlank()) {
            return ResponseEntity.ok(listOf(cocktailRepository.findById(id)))
        }
        return ResponseEntity.ok(cocktailService.findCocktail(id, name, taste, ingredients, alcoholic, difficulty))
    }

    @PostMapping("/cocktails")
    fun createCocktail(@RequestBody cocktail: Cocktail): ResponseEntity<Cocktail>{
        val savedCocktail = cocktailRepository.save(cocktail)
        return ResponseEntity.ok(savedCocktail)
    }

    @DeleteMapping("/cocktails")
    fun deleteCocktail(@RequestParam id: String): ResponseEntity<Unit>{
        cocktailRepository.deleteById(id)
        return ResponseEntity.noContent().build()
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

    @GetMapping("/tastes")
    fun getDistinctTastes(): ResponseEntity<List<String>> {
        val distinctStrings = cocktailService.getTastes()

        val tastes = distinctStrings.map { json ->
            val startIndex = json.indexOf('[')
            val endIndex = json.indexOf(']')
            val tastes = json.substring(startIndex + 2, endIndex - 1)
            tastes
        }
        return ResponseEntity.ok(tastes)
    }
}