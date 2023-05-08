package com.example.springbootopenapitypescriptcodegen.model

class Masculine(override val type:String, val beerType:String? = null ) : Gender {
    override fun printGender() {
        println("masculine")
    }

    fun drinkBeer() {
        val message = "lets drink a" + (beerType?.let { " $beerType" } ?: "beer")
        println(message)

    }
}