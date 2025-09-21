package com.example.todoist.presentation.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todoist.ui.theme.Purple40
import com.example.todoist.ui.theme.Teal

@Composable
fun CustomCheckBoxWithAnimation(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    checkedColor: Color = Teal,
    uncheckedColor: Color = Purple40,
    checkmarkColor: Color = Color.White,
    contentDescription: String? = null
) {
    val boxColor by animateColorAsState(
        targetValue = if (checked) checkedColor else uncheckedColor,
        animationSpec = tween(durationMillis = 300)
    )
    val checkScale by animateFloatAsState(
        targetValue = if (checked) 1f else 0f,
        animationSpec = tween(durationMillis = 300)
    )
    Box(
        modifier = modifier
            .size(28.dp)
            .background(color = boxColor, shape = RoundedCornerShape(6.dp))
            .clickable(
                role = Role.Checkbox,
                onClick = { onCheckedChange(!checked) }
            )
            .semantics {
                this.contentDescription = contentDescription ?: "Checkbox"
            },
        contentAlignment = Alignment.Center
    ) {
        if (checkScale > 0f) {
            Icon(
                imageVector = Icons.Filled.Check,
                contentDescription = null,
                tint = checkmarkColor,
                modifier = Modifier.scale(checkScale)
            )
        }
    }
}

@Preview
@Composable
fun CustomCheckBoxWithAnimationPreview() {
    var checked = false
    CustomCheckBoxWithAnimation(
        checked = checked,
        onCheckedChange = {},
        checkedColor = Teal,
        uncheckedColor = Purple40,
        checkmarkColor = Color.White
    )
}