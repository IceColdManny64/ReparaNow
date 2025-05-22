package com.example.reparanow.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowOutward
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.reparanow.R

@Composable
fun ProfileScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF1976D2), Color(0xFF42A5F5))
                )
            )
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            // Header con foto y nombre
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.albanil),
                    contentDescription = "Foto del prestador",
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .background(Color.LightGray),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = "Juan Pérez",
                        style = MaterialTheme.typography.h6.copy(
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    // Calificación
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        repeat(5) {
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = null,
                                tint = Color(0xFFFFD700),
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "4.8",
                            style = MaterialTheme.typography.body2.copy(color = Color.White)
                        )
                    }
                }
            }

            // Sección "Sobre mí"
            Card(
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Sobre mí",
                        style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Bold)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Soy albañil con más de 10 años de experiencia en construcción de viviendas y remodelaciones.",
                        style = MaterialTheme.typography.body2
                    )
                }
            }

            // Galería de trabajos
            Text(
                text = "Galería de trabajos",
                style = MaterialTheme.typography.subtitle1.copy(
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Row(
                modifier = Modifier
                    .horizontalScroll(rememberScrollState())
            ) {
                repeat(5) {
                    Image(
                        painter = painterResource(R.drawable.casa),
                        contentDescription = "Trabajo $it",
                        modifier = Modifier
                            .size(120.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .padding(end = 8.dp),
                        contentScale = ContentScale.Crop
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Sección de comentarios
            Text(
                text = "Comentarios",
                style = MaterialTheme.typography.subtitle1.copy(
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Column {
                repeat(3) { index ->
                    Card(
                        shape = RoundedCornerShape(12.dp),
                        backgroundColor = Color.White,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            verticalAlignment = Alignment.Top
                        ) {
                            Image(
                                painter = painterResource(R.drawable.profile),
                                contentDescription = "Avatar usuario",
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(CircleShape)
                                    .background(Color.LightGray),
                                contentScale = ContentScale.Crop
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "Usuario ${index + 1}",
                                    style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold)
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "Servicio excelente, muy puntual y profesional.",
                                    style = MaterialTheme.typography.body2
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "45 respuestas",
                                    color = Color(0xFFFF9800),
                                    style = MaterialTheme.typography.caption.copy(fontWeight = FontWeight.Medium),
                                    modifier = Modifier.clickable { /* acción ver respuestas */ }
                                )
                            }
                            Icon(
                                Icons.Default.ArrowOutward,
                                contentDescription = "Ver respuestas",
                                tint = Color(0xFFFF9800),
                                modifier = Modifier
                                    .size(30.dp)
                                    .clickable { /* acción ver respuestas */ }
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))
            // Campo para agregar nuevo comentario
            var newComment by remember { mutableStateOf("") }
            TextField(
                value = newComment,
                onValueChange = { newComment = it },
                placeholder = { Text("Escribe un nuevo comentario...") },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { /* acción enviar comentario */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF9800)),
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(text = "Enviar", color = Color.White, style = MaterialTheme.typography.button)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Placeholder de mapa
            Text(
                text = "Ubicación",
                style = MaterialTheme.typography.subtitle1.copy(
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.LightGray),
                painter = painterResource(R.drawable.mapa),
                contentDescription = "Mapa placeholder",
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Botón de contactar
            Button(
                onClick = {navController.navigate("servScreen")},
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF9800)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Contactar",
                    color = Color.White,
                    style = MaterialTheme.typography.button.copy(fontWeight = FontWeight.Bold)
                )
            }
        }
    }
}

