package com.example.primerospasos

import android.R.attr.text
import android.os.Bundle
import android.text.style.UnderlineSpan
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.primerospasos.ui.theme.PrimerosPasosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PrimerosPasosTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ListaAlumnos(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ListaAlumnos( modifier: Modifier = Modifier) {

    val listaAlumnos = remember {
        arrayListOf(
            "Juan GÓMEZ",
            "María RODRÍGUEZ",
            "Pedro MARTÍNEZ",
            "Ana LÓPEZ",
            "Luis SÁNCHEZ",
            "Laura GARCÍA",
            "Carlos FERNÁNDEZ",
            "Sofía TORRES",
            "Diego ALONSO",
            "Valentina MORENO"
        )
    }

    var alumnosDisponibles by remember {
        mutableStateOf(listaAlumnos.toMutableList()) }

    var alumnoSeleccionado by remember {
        mutableStateOf("Pulsa 'Siguiente'")
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Turno de pregunta: ",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
        )
        Spacer(Modifier.height(32.dp))
        Text(
            text = alumnoSeleccionado,
            fontSize = 32.sp,
            color = Color.Green,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(32.dp))
        Button(onClick = {
            if (alumnosDisponibles.isEmpty()) {
                alumnoSeleccionado = "Lista completa, reiniciando"
                alumnosDisponibles = listaAlumnos.toMutableList()
            }else{
                val proximoAlumno = alumnosDisponibles.random()
                alumnoSeleccionado = proximoAlumno
                alumnosDisponibles.remove(proximoAlumno)
            }

        },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Gray
        )) {
            Text(
                text = "Siguiente",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
            )
        }
        Spacer(Modifier.height(32.dp))
        Button(onClick = {
            alumnosDisponibles = listaAlumnos.toMutableList()
            alumnoSeleccionado = "Lista reiniciada"

        },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Red
        )) {
            Text(
                text = "Reiniciar lista",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PrimerosPasosTheme {
        ListaAlumnos()

    }
}