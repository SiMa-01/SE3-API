package com.example.SE3Api.controller

import com.example.SE3Api.data.Cocktail
import com.example.SE3Api.inface.CocktailRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/cocktails")
class CocktailController(val cocktailRepository: CocktailRepository) {

    @GetMapping
    fun getAllCocktails(): List<Cocktail> = cocktailRepository.findAll()

    @GetMapping("/{id}")
    fun getCocktailById(@PathVariable id: String): ResponseEntity<Cocktail> {
        val cocktail = cocktailRepository.findById(id).orElse(null)
        return if (cocktail != null) {
            ResponseEntity.ok(cocktail)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    fun createCocktail(@RequestBody cocktail: Cocktail): Cocktail {
        return cocktailRepository.save(cocktail)
    }
}
