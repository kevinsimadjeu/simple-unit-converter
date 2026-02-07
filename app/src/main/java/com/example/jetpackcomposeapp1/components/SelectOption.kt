package com.example.jetpackcomposeapp1.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposeapp1.MeasureUnit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectOption(
    defaultSelected: Int,
    options: List<MeasureUnit>,
    modifier: Modifier = Modifier,
    setSelected: (MeasureUnit) -> Unit,
) {
    var expandedSelect by remember { mutableStateOf(false) }
    var selectedUnit by remember { mutableStateOf(options.get(defaultSelected)) }

    LaunchedEffect(key1 = defaultSelected, key2 = options) {
        selectedUnit = options.get(defaultSelected)
    }

    ExposedDropdownMenuBox(
        expanded = expandedSelect,
        onExpandedChange = {expandedSelect = it},
        modifier = modifier
            .border(1.dp, color = Color.Black, shape = RoundedCornerShape(20.dp))
    ) {
        TextField(
            value = selectedUnit.name,
            onValueChange = {},
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
            ),
            readOnly = true,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedSelect)},
            modifier = Modifier
                .background(Color.White)
                .border(1.dp, color = Color.White)
                .menuAnchor())
        ExposedDropdownMenu(
            expanded = expandedSelect,
            onDismissRequest = { expandedSelect = false }
        ) {
            options.forEach {option ->
                DropdownMenuItem(
                    modifier = Modifier
                        .background(Color.White)
                        .fillMaxSize()
                        .border(1.dp, Color.Black),
                    text = { Text(option.name, fontSize = 21.sp, color = Color.Black) },
                    onClick = {
                        selectedUnit = option
                        setSelected(option)
                        expandedSelect = false
                    }
                )
            }
        }
    }
}