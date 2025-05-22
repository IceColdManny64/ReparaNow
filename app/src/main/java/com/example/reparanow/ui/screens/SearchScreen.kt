package com.example.reparanow.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.navigation.NavHostController
import com.example.reparanow.R

@Composable
fun SearchScreen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
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
            // Barra de búsqueda
            var query by remember { mutableStateOf("") }
            TextField(
                value = query,
                onValueChange = { query = it },
                placeholder = { Text("Buscar profesionales...") },
                leadingIcon = { Icon(Icons.Default.Search, null, tint = Color(0xFF1976D2)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, RoundedCornerShape(24.dp))
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Filtros desplegables
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
            ) {
                var oficioExpanded by remember { mutableStateOf(false) }
                var ratingExpanded by remember { mutableStateOf(false) }

                FilterDropdown(
                    label = "Oficio",
                    options = listOf("Albañil", "Electricista", "Plomero"),
                    expanded = oficioExpanded,
                    onExpandChange = { oficioExpanded = it }
                )
                FilterDropdown(
                    label = "Valoración",
                    options = listOf("3.0+", "4.0+", "5.0"),
                    expanded = ratingExpanded,
                    onExpandChange = { ratingExpanded = it }
                )
                // Más filtros...
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Recomendaciones
            Text(
                text = "Recomendados",
                style = MaterialTheme.typography.subtitle1.copy(
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.horizontalScroll(rememberScrollState())
            ) {
                repeat(5) { idx ->
                    Card(
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .size(120.dp)
                            .padding(end = 8.dp)
                            .clickable { navController.navigate("profScreen") }
                    ) {
                        Image(
                            painter = painterResource(R.drawable.plomero),
                            contentDescription = "Profesional $idx",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Lista de resultados
            Text(
                text = "Profesionales Cercanos",
                style = MaterialTheme.typography.subtitle1.copy(
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.height(8.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                repeat(5) { idx ->
                    Card(
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { navController.navigate("profScreen") }
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(12.dp)
                        ) {
                            Image(
                                painter = painterResource(R.drawable.carpi),
                                contentDescription = "Profesional $idx",
                                modifier = Modifier
                                    .size(60.dp)
                                    .clip(RoundedCornerShape(8.dp)),
                                contentScale = ContentScale.Crop
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "Juan Pérez",
                                    style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold)
                                )
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    repeat(5) {
                                        Icon(
                                            Icons.Default.Star,
                                            contentDescription = null,
                                            tint = Color(0xFFFFD700),
                                            modifier = Modifier.size(16.dp)
                                        )
                                    }
                                    Text(
                                        text = "4.7",
                                        style = MaterialTheme.typography.caption
                                    )
                                }
                                Text(
                                    text = "Experiencia: 5 años",
                                    style = MaterialTheme.typography.caption
                                )
                            }
                            Icon(
                                Icons.Default.FilterList,
                                contentDescription = "Ver perfil",
                                tint = Color(0xFFFF9800),
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FilterDropdown(
    label: String,
    options: List<String>,
    expanded: Boolean,
    onExpandChange: (Boolean) -> Unit
) {
    var selected by remember { mutableStateOf(options.first()) }
    Box(modifier = Modifier
        .background(Color.White, RoundedCornerShape(24.dp))
        .clickable { onExpandChange(true) }
        .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "\$label: \$selected", style = MaterialTheme.typography.body2)
            Icon(Icons.Default.FilterList, null, tint = Color(0xFF1976D2), modifier = Modifier.size(20.dp))
        }
        // Aquí iría el DropdownMenu funcional
    }
}
