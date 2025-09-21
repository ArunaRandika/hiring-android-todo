package com.example.todoist.presentation.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.todoist.ui.theme.Teal

@Composable
fun CustomRoundedButton(
    bgColor: Color,
    onPressed: () -> Unit,
    modifier: Modifier = Modifier,
    size: Dp = 48.dp,
    enabled: Boolean = true,
    contentDescription: String? = null,
    icon: @Composable () -> Unit
) {


    Surface(
        color = bgColor,
        shape = RoundedCornerShape(size / 2),
        onClick = onPressed,
        modifier = modifier
            .size(size)
    ) {
        icon()
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CustomRoundedButtonPreview() {
    CustomRoundedButton(
        bgColor = Teal,
        onPressed = {},
        icon = {
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = "Favorite",
                tint = Color.White
            )
        }
    )
}
