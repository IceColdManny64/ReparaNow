package com.example.reparanow.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.platform.LocalConfiguration
import android.content.res.Configuration
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun MenuScreen(navController: NavController) {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    Scaffold(
        topBar = { Bar(navController) },
        bottomBar = { Bar2(navController) }
    ) { padding ->
        Surface(modifier = Modifier
            .fillMaxSize()
            .padding(padding)) {

            if (isLandscape) {
                // Diseño en horizontal (fila)
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Menú",
                            style = MaterialTheme.typography.headlineSmall,
                            color = Color.Black
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        MenuItem(
                            title = "Ajustes de cuenta",
                            icon = Icons.Default.AccountCircle,
                            onClick = {
                                navController.navigate("AccountSettingsScreen")
                            }
                        )

                        MenuItem(
                            title = "Chat de soporte",
                            icon = Icons.Default.Chat,
                            onClick = {
                                navController.navigate("SupportChatScreen")
                            }
                        )
                    }
                }
            } else {
                // Diseño en vertical (columna)
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Menú",
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    MenuItem(
                        title = "Ajustes de cuenta",
                        icon = Icons.Default.AccountCircle,
                        onClick = {
                            navController.navigate("AccountSettingsScreen")
                        }
                    )

                    MenuItem(
                        title = "Chat de soporte",
                        icon = Icons.Default.Chat,
                        onClick = {
                            navController.navigate("SupportChatScreen")
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun MenuItem(title: String, icon: ImageVector, onClick: () -> Unit) {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    val modifier = Modifier
        .fillMaxWidth()
        .clickable { onClick() }
        .padding(vertical = 16.dp, horizontal = if (isLandscape) 32.dp else 16.dp)

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = if (isLandscape) Arrangement.Center else Arrangement.Start
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            modifier = Modifier.size(if (isLandscape) 36.dp else 28.dp),
            tint = Color.Black
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = title,
            fontSize = if (isLandscape) 20.sp else 18.sp,
            color = Color.Black
        )
    }
}

@Composable
fun AccountSettingsScreen(navController: NavController) {
    Scaffold(
        topBar = { Bar(navController)},
        bottomBar = { Bar2(navController)}
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Ajustes de cuenta",
                fontSize = 22.sp,
                color = Color.Black
            )

            Text(text = "Cambiar contraseña", fontSize = 18.sp)
            Text(text = "Actualizar correo electrónico", fontSize = 18.sp)
            Text(text = "Preferencias de notificación", fontSize = 18.sp)
        }
    }
}

@Composable
fun SupportChatScreen(navController: NavController) {
    Scaffold(
        topBar = { Bar(navController)},
        bottomBar = { Bar2(navController)}
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Chat de soporte",
                fontSize = 22.sp,
                color = Color.Black
            )

            Text(text = "¿Tienes dudas o necesitas ayuda?", fontSize = 18.sp)
            Text(text = "Escríbenos y te responderemos lo antes posible.", fontSize = 16.sp)
        }
    }
}