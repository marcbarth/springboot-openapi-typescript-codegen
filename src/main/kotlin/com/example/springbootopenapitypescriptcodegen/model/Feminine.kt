package com.example.springbootopenapitypescriptcodegen.model

class Feminine(override val type:String) : Gender {
    override fun printGender() {
        println("feminine")
    }

    fun drinkWine() {
        println("drink Wine")
    }
}