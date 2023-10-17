package com.example.myapplicationasdsds

private val groceryList =  listOf(
        Grocery(1,"mrkve", 2),
        Grocery(2,"banán", 10),
)

data class Groceries(
    val list: List<Grocery> = groceryList
)

data class Grocery(
    val id: Int,
    val name: String,
    val amount: Int
)
