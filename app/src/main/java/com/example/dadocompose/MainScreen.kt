package com.example.dadocompose

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

class DadoViewModel : ViewModel() {
    private var resultado by mutableIntStateOf(0)
    val resultadoActual: Int
        get() = resultado
    fun nuevoResultado() {
        resultado = (1..6).random()
        Log.i("...", "$resultado")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    Scaffold (
        Modifier.fillMaxSize(),
        topBar = { TopAppBar(
            title = { Text(text = "DadoCompose")})},
    ) {
        Box(Modifier.padding(it)) {
            Column (
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally,
            ){
                val viewModel: DadoViewModel = viewModel()
                LanzarDado(viewModel.resultadoActual) {
                    viewModel.nuevoResultado()
                }
            }
        }
    }
}

@Composable
fun LanzarDado(resultadoActual: Int, nuevoResultado: () -> Unit) {
    Row {
        Button(onClick = nuevoResultado) {
            Text(text = "Lanzar Dado")
        }
    }
    Row {
        Text(text = "Resultado: $resultadoActual")
    }
    Row {
        Image(
            painter = painterResource(id = when (resultadoActual) {
                1 -> R.drawable.dice_1
                2 -> R.drawable.dice_2
                3 -> R.drawable.dice_3
                4 -> R.drawable.dice_4
                5 -> R.drawable.dice_5
                6 -> R.drawable.dice_6
                else -> R.drawable.empty_dice
            }),
            contentDescription = "Dado"
        )
    }
}
