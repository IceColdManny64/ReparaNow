package com.example.reparanow.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.ui.layout.ContentScale
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.reparanow.R

@Composable
fun MainScreen(navController: NavController) {
    Scaffold(
        topBar = { Bar(navController) },
        bottomBar = { Bar2(navController) }
    ) { padding ->
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .padding(padding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "ReparaNow!",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1565C0),
                modifier = Modifier.padding(bottom = 24.dp)
            )

            SectionBlock("Recomendaciones", profesionales = sampleProfesionales, navController)
            Spacer(modifier = Modifier.height(40.dp))
            SectionBlock("Populares", profesionales = sampleProfesionales.shuffled(), navController)
            Spacer(modifier = Modifier.height(40.dp))
            SectionBlock("Mejores reseñas", profesionales = sampleProfesionales.shuffled(), navController)
        }
    }
}

@Composable
fun Bar(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(95.dp)
            .background(color = Color(0xFF2196F3))
            .padding(horizontal = 12.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.reparanow_bg),
                    contentDescription = "Logo",
                    modifier = Modifier.size(48.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "ReparaNow!",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White
                )
            }

            IconButton(
                onClick = { navController.navigate("searchScreen") }
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Buscar",
                    modifier = Modifier.size(28.dp)
                )
            }
        }
    }
}

@Composable
fun Bar2(navController: NavController){
    NavigationBar {
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate("mainScreen") },
            icon = {
                Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = "Inicio"
                )
            },
            label = { Text("Inicio") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate("menuScreen") },
            icon = {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Menú"
                )
            },
            label = { Text("Menú") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate("profileScreen") },
            icon = {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "Perfil"
                )
            },
            label = { Text("Perfil") }
        )
    }
}



@Composable
fun SectionBlock(title: String, profesionales: List<Profesional>, navController: NavController) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Transparent, RoundedCornerShape(8.dp))
                .padding(8.dp)
        ) {
            Text(
                text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            profesionales.take(3).forEach { profesional ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .clickable {
                            navController.navigate("professional/${profesional.id}")
                        }
                        .width(100.dp)
                ) {
                    IconButton(
                        onClick = {navController.navigate("professional/${profesional.id}")}
                    ) {
                        Image(
                            painter = painterResource(id = profesional.imagen),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(80.dp)
                                .clip(CircleShape)
                                .border(2.dp, Color.White, CircleShape)
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = profesional.nombre, color = Color.Black, fontSize = 14.sp)
                    Text(text = profesional.profesion, color = Color(0xFF2196F3), fontSize = 12.sp)
                }
            }
        }
    }
}

@Composable
fun ProfessionalScreen(profesionalId: Int, navController: NavController) {
    val profesional = sampleProfesionales.find { it.id == profesionalId }

    Scaffold(
        topBar = { Bar(navController) },
        bottomBar = { Bar2(navController) }
    ) { padding ->
        profesional?.let {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = it.imagen),
                    contentDescription = it.nombre,
                    modifier = Modifier
                        .size(150.dp)
                        .padding(16.dp),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = it.nombre,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = it.profesion,
                    fontSize = 18.sp,
                    color = Color(0xFF1976D2)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Aquí puedes agregar una descripción completa, calificaciones, años de experiencia, servicios, etc.",
                    fontSize = 14.sp,
                    color = Color.DarkGray
                )
            }
        } ?: run {
            Text(
                text = "Profesional no encontrado",
                modifier = Modifier.padding(16.dp),
                color = Color.Red
            )
        }
    }
}

data class Profesional(
    val id: Int,
    val nombre: String,
    val profesion: String,
    val imagen: Int
)

val sampleProfesionales = listOf(
    Profesional(1, "Andrea Ruiz", "Electricista", R.drawable.profesionista),
    Profesional(2, "Carlos Gómez", "Plomero", R.drawable.profesionista),
    Profesional(3, "Luisa Torres", "Cerrajera", R.drawable.profesionista),
    Profesional(4, "Marco Díaz", "Albañil", R.drawable.profesionista),
    Profesional(5, "Sandra López", "Carpintera", R.drawable.profesionista)
)