package com.example.jetpackcomposeapp1.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposeapp1.R
import com.example.jetpackcomposeapp1.categories
import com.example.jetpackcomposeapp1.length
import com.example.jetpackcomposeapp1.ui.theme.colorPrimary
import com.example.jetpackcomposeapp1.ui.theme.veryLigthBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    var currentCategory by remember {
        mutableStateOf(length)
    }
    var selectedInput by remember {
        mutableStateOf(currentCategory.units.first())
    }
    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Row (
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.logo),
                            modifier = Modifier
                                .background(Color.White, shape = CircleShape)
                                .size(width = 55.dp, height = 55.dp),
                            contentDescription = "Logo"
                        )
                        Text("Convertisseur D'unités", fontStyle = FontStyle.Italic, fontWeight = FontWeight.Bold, fontSize = 28.sp)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorPrimary,
                    titleContentColor = Color.White,
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(containerColor = colorPrimary, contentColor = Color.White, shape = CircleShape, onClick = {}) {
                Icon(imageVector = Icons.Default.Add , contentDescription = "Icone plus")
            }
        }
    ) {innerPadding ->
        Column (
            verticalArrangement = Arrangement.spacedBy(15.dp),
            modifier = Modifier
                .fillMaxSize()
                .background(veryLigthBlue)
                .padding(innerPadding)
                .padding(10.dp)
        ) {
            CategorySelector(
                currentCategory = currentCategory,
                categories = categories,
                setCurrentCategory = {newCurrent ->
                    currentCategory = newCurrent
                },
                setSelectedInput = {newSelected ->
                    selectedInput = newSelected
                })

            MainSection(currentCategory = currentCategory)
        }
    }
}