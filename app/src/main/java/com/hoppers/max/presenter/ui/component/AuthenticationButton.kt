package com.hoppers.max.presenter.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AuthenticationButton(onClick: ()->Unit) {
    Button(
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF2765CF)
        ),
        onClick = onClick,
        shape = RoundedCornerShape(40.dp),
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .height(48.dp)
    ) {
        Text(text = "LOGIN")
    }
}