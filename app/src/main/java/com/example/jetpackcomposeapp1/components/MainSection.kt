package com.example.jetpackcomposeapp1.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposeapp1.ConvertCategory
import com.example.jetpackcomposeapp1.convertClassicUnits
import com.example.jetpackcomposeapp1.length
import com.example.jetpackcomposeapp1.ui.theme.veryLigthBlue

@Composable
fun MainSection(
    currentCategory: ConvertCategory
) {
    var inputValue by remember {
        mutableStateOf("")
    }
    var selectedInput by remember {
        mutableStateOf(currentCategory.units.first())
    }
    var selectedOutput by remember {
        mutableStateOf(currentCategory.units.get(1))
    }
    var expandedInput by remember {
        mutableStateOf(false)
    }
    var outputValue by remember {
        mutableStateOf("")
    }

    fun fillOutputValue(newVal:String): Unit {
        inputValue = newVal
        if (newVal == "") {
            outputValue = ""
        } else {
            val safeValue = newVal.toDouble()
            outputValue = convertClassicUnits(
                from = selectedInput,
                to = selectedOutput,
                value = safeValue,
            ).toString()
        }
    }

    LaunchedEffect(inputValue, selectedInput, selectedOutput) {
        fillOutputValue(inputValue)
    }

    LaunchedEffect(key1 = currentCategory) {
        selectedInput = currentCategory.units.first()
        selectedOutput = currentCategory.units.get(1)
    }

    Card (
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column (
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(10.dp)
        ) {
            Text("Conversion de ${currentCategory.name}", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 25.sp, fontStyle = FontStyle.Italic)
            Row (
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                ValueField(
                    value = inputValue,
                    onValueChange = {newVal ->
                        fillOutputValue(newVal)
                    },
                    label = "Valeur initiale",
                    placeholder = "Exple : 100",
                    modifier = Modifier.weight(0.4f)
                )

                SelectOption(
                    defaultSelected = 0,
                    options = currentCategory.units,
                    modifier = Modifier.weight(0.6f),
                    setSelected = {newVal ->
                        selectedInput = newVal
                    }
                )
            }

            // Icone au centre
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                tint = Color.White,
                modifier = Modifier
                    .background(veryLigthBlue, shape = CircleShape)
                    .padding(10.dp),
                contentDescription = "Fleche descendante")

            // Fin de la visualisation (conversion)
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ValueField(
                    value = outputValue,
                    onValueChange = {},
                    readOnly = true,
                    label = "Valeur finale",
                    modifier = Modifier.weight(0.4f)
                )

                SelectOption(
                    defaultSelected = 1,
                    options = currentCategory.units,
                    modifier = Modifier.weight(0.6f),
                    setSelected = {newVal ->
                        selectedOutput = newVal
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun PffPreview() {
    MainSection(currentCategory = length)
}