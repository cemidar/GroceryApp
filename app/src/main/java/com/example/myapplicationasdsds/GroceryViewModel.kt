package com.example.myapplicationasdsds

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class GroceryViewModel: ViewModel() {

    private val _state: MutableStateFlow<Groceries>
        = MutableStateFlow(Groceries())

    val state: StateFlow<Groceries> = _state
    init {
        addGrocery("paprikový joe", "2")
    }


    fun addGrocery(name: String, amount: String){
        val grocerySize = _state.value.list.size
        val grocery = Grocery(grocerySize + 1, name, amount.toInt())

        val list = _state.value.list.toMutableList()
        list.add(grocery)

        val groceries = _state.value.copy(list = list)
        _state.update { groceries }
    }
}