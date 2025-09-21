package com.example.todoist.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todoist.domain.model.ToDoItem
import com.example.todoist.ui.theme.Teal
import com.example.todoist.ui.theme.Unchecked

@Composable
fun TodoListItem(
    item: ToDoItem,
    onChecked: (Boolean) -> Unit,
    onDelete: () -> Unit,
    onEdit: () -> Unit
) {
    Column {
        InteractableRow(
            item = item,
            onChecked = onChecked,
            onDelete = onDelete,
            onEdit = onEdit
        )

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.size(38.dp))
            Text(text = item.description)
        }

    }

}

@Composable
fun InteractableRow(
    item: ToDoItem,
    onChecked: (Boolean) -> Unit,
    onDelete: () -> Unit,
    onEdit: () -> Unit
) {
    var isChecked by remember { mutableStateOf(item.isCompleted) }
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            CustomCheckBoxWithAnimation(
                checked = isChecked,
                onCheckedChange = {
                    isChecked = !isChecked
                    onChecked(it)
                },
                checkedColor = Teal,
                uncheckedColor = Unchecked,
                checkmarkColor = Color.White
            )
            Text(
                text = item.title,
                textDecoration = if (item.isCompleted) TextDecoration.LineThrough else null
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onEdit) {
                Icon(imageVector = Icons.Filled.Edit, contentDescription = "Edit")
            }
            IconButton(onClick = onDelete) {
                Icon(imageVector = Icons.Filled.Delete, contentDescription = "Delete")
            }
        }
    }

}

@Preview
@Composable
private fun ListItemPrev() {
    TodoListItem(
        item = ToDoItem(
            id = 1,
            title = "Title",
            description = "Description",
            isCompleted = false
        ),
        onChecked = { _ -> },
        onDelete = {},
        onEdit = {}

    )
}