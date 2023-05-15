package com.example.cocktailapi.controller

import com.example.cocktailapi.model.Cocktail
import com.example.cocktailapi.service.CocktailService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/cocktails")
class CocktailController (private val service: CocktailService) {

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e: NoSuchElementException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleBadRequest(e: IllegalArgumentException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.BAD_REQUEST)

    @GetMapping
    fun getCocktails(): Collection<Cocktail> = service.getCocktails()

    @GetMapping("/{cocktailNumber}")
    fun getCocktail(@PathVariable cocktailNumber: Int): Cocktail = service.getCocktail(cocktailNumber)
}