package com.example.jetpackcomposeapp1

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposeapp1.components.HomeScreen
import com.example.jetpackcomposeapp1.ui.theme.JetpackComposeApp1Theme
import com.example.jetpackcomposeapp1.ui.theme.Purple80
import com.example.jetpackcomposeapp1.ui.theme.colorPrimary
import com.example.jetpackcomposeapp1.ui.theme.colorSecondary
import com.example.jetpackcomposeapp1.ui.theme.veryLigthBlue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeApp1Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background) {
                    HomeScreen()
                }
            }
        }
    }
}

data class ConvertCategory(
    val name: String,
    val id: Int,
    val units: MutableList<MeasureUnit>,
)

data class MeasureUnit(
    val name: String,
    val symbol: String,
    val value: Double,
)

// Liste des Unités pour les différentes mesures
fun classicUnits(type:String = "gramme"): MutableList<MeasureUnit> {
    val symbol = type.first().lowercase()
    val unitList = mutableListOf<MeasureUnit>()

    if(type === "durée") {
        unitList.add(MeasureUnit(name = "Heure", symbol = "H", value = 3600.0))
        unitList.add(MeasureUnit(name = "Minute", symbol = "min", value = 60.0))
        unitList.add(MeasureUnit(name = "Seconde", symbol = "s", value = 1.0))
    } else {
        if(type !== "litre") {
            unitList.add(MeasureUnit(name = "Kilo$type", symbol = "K$symbol", value = 1000.0))
        }
        unitList.add(MeasureUnit(name = symbol.uppercase() + type.substring(1, type.length), symbol = symbol, value = 1.0))
        unitList.add(MeasureUnit(name = "Hecto$type", symbol = "H$symbol", value = 100.0))
        unitList.add(MeasureUnit(name = "Déca$type", symbol = "da$symbol", value = 10.0))
        unitList.add(MeasureUnit(name = "Déci$type", symbol = "d$symbol", value = 0.1))
        unitList.add(MeasureUnit(name = "Centi$type", symbol = "K$symbol", value = 0.01))
        unitList.add(MeasureUnit(name = "Milli$type", symbol = "K$symbol", value = 0.001))
        if(type === "gramme") {
            unitList.add(MeasureUnit(name = "Tonnes", symbol = "t", value = 1000000.0))
            unitList.add(MeasureUnit(name = "Quintal", symbol = "q", value = 100000.0))
            unitList.add(MeasureUnit(name = "Point", symbol = ".", value = 10000.0))
        }
    }

    return unitList
}

fun convertClassicUnits(from: MeasureUnit, to: MeasureUnit, value: Double): Double {
    return (value * from.value / to.value)
}

val length = ConvertCategory(name = "Longueur", id = 1, units = classicUnits("mètre"))
val weight = ConvertCategory(name = "Poids", id = 2, units = classicUnits("gramme"))
val time = ConvertCategory(name = "Durée", id = 3, units = classicUnits("durée"))
val volume = ConvertCategory(name = "Volume", id = 4, units = classicUnits("litre"))
val categories = listOf(length, weight, time, volume)

@Preview(showBackground = true, backgroundColor = 0xFF00EEFF)
@Composable
fun MyTextPreview() {
    JetpackComposeApp1Theme {
        HomeScreen()
    }
}

