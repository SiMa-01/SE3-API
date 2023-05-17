package com.example.cocktailapi.controller

import com.example.cocktailapi.datascource.dto.Cocktail
import com.example.cocktailapi.datascource.mongodb.CocktailRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/cocktails")
class CocktailController (private val cocktailRepository: CocktailRepository) {

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e: NoSuchElementException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleBadRequest(e: IllegalArgumentException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.BAD_REQUEST)

    @GetMapping("/")
    fun getCocktail(): ResponseEntity<List<Cocktail>>{
        return ResponseEntity.ok(cocktailRepository.findAll())
    }

    @GetMapping("/id/{id}")
    fun getCocktailById(@PathVariable id: String): ResponseEntity<Cocktail>{
        return ResponseEntity.ok(cocktailRepository.findByIdOrNull(id))
    }

    @GetMapping("/name/{name}")
    fun getCocktailByName(@PathVariable name: String): ResponseEntity<List<Cocktail>> {
        return ResponseEntity.ok(cocktailRepository.findByName(name))
    }
    @GetMapping("/taste/{taste}")
    fun getCocktailByTaste(@PathVariable taste: String): ResponseEntity<List<Cocktail>> {
        return ResponseEntity.ok(cocktailRepository.findByTaste(taste))
    }

    @GetMapping("/alcoholic/{alcoholic}")
    fun getCocktailByAlcoholic(@PathVariable alcoholic: Boolean): ResponseEntity<List<Cocktail>> {
        return ResponseEntity.ok(cocktailRepository.findByAlcoholic(alcoholic))
    }

    @GetMapping("/ingredient/{ingredient}")
    fun getCocktailByIngredient(@PathVariable ingredient: String): ResponseEntity<List<Cocktail>> {
        return ResponseEntity.ok(cocktailRepository.findByIngredient(ingredient))
    }

    @GetMapping("/difficulty/{difficulty}")
    fun getCocktailByDifficulty(@PathVariable difficulty: String): ResponseEntity<List<Cocktail>> {
        return ResponseEntity.ok(cocktailRepository.findByDifficulty(difficulty))
    }
}