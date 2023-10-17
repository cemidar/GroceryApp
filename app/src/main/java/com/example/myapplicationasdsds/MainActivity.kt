package com.example.myapplicationasdsds

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplicationasdsds.ui.theme.MyApplicationasdsdsTheme

class MainActivity : ComponentActivity() {

    private val viewModel: GroceryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val state by viewModel.state.collectAsState()
            MyApplicationasdsdsTheme {
                Box(modifier = Modifier.fillMaxSize()){

                    Column (modifier = Modifier.fillMaxSize()) {
                        for (i in state.list) {
                            GroceryRow(i)
                        }
                    }
                        GroceryForm(modifier = Modifier
                            .fillMaxWidth()
                            .align(BottomCenter)
                            //.background(color = Color.LightGray)
                        ){
                            name, amount ->
                            viewModel.addGrocery(name, amount.toInt())
                        }
                }
            }
        }
    }
}


@Composable
fun GroceryRow(grocery: Grocery){
    Row {
        Text(text = grocery.amount.toString() + "x ", fontSize = 25.sp)
        Column {
            Text(text = grocery.name, fontSize = 25.sp)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroceryForm(modifier: Modifier = Modifier, onSaveClicked: (String, String)-> Unit){
    Box(modifier = modifier){
        var name: String by remember { mutableStateOf("") }
        var amount: String by remember { mutableStateOf("1") }
        Column {
            TextField(modifier = Modifier.fillMaxWidth(),
                value = name, onValueChange = {name = it}, label = {
                Text(text = "Type a grocery name")
            })
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = amount,
                onValueChange = {
                    amount = (it.toIntOrNull() ?: 0).toString()
                },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                label = { Text(text = "Amount") }
            )

            // Three down buttons section (plusAmount, minusAmount, add)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween) {
                Button(
                    modifier = Modifier
                        .weight(0.25f)
                        .padding(end = 4.dp) // Add padding to the right of the "+" button
                    ,
                    onClick = {
                        //when clicked make amount higher
                        var amountCopy = amount.toInt()
                        amountCopy++
                        amount = amountCopy.toString()
                    }
                ) {
                    Text(text = "+", fontSize = 20.sp)
                }
                Button(
                    modifier = Modifier
                        .weight(0.25f)
                        .padding(end = 4.dp) // Add padding to the right of the "+" button
                    ,
                    onClick = {
                        //when clicked make amount smaller
                        var amountCopy= amount.toInt()
                        if (amountCopy > 1){
                            amountCopy--
                            amount = amountCopy.toString()
                        }
                    }
                ) {
                    Text(text = "-", fontSize = 20.sp)
                }
                Button(
                    modifier = Modifier
                        .weight(0.50f),
                    onClick = {
                        onSaveClicked(name, amount)
                        name = ""
                        amount = "1"
                    }) {
                    Text(text = "add", fontSize = 20.sp)
                }
            }
        }

    }
}



