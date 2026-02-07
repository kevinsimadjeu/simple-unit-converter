package com.example.jetpackcomposeapp1.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeapp1.ConvertCategory
import com.example.jetpackcomposeapp1.MeasureUnit
import com.example.jetpackcomposeapp1.ui.theme.veryLigthBlue

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CategorySelector(
    currentCategory: ConvertCategory,
    categories: List<ConvertCategory>,
    setCurrentCategory: (ConvertCategory) -> Unit,
    setSelectedInput: (MeasureUnit) -> Unit,
) {
    fun isCurrentCategory(category: ConvertCategory): Boolean {
        return (category.id == currentCategory.id)
    }


    Card (
        shape = CardDefaults.elevatedShape,
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        FlowRow (
            maxItemsInEachRow = 2,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .padding(10.dp)
        ) {
            for (category in categories) {
                Box(modifier = Modifier
                    .height(50.dp)
                    .weight(1f)
                    .border(
                        width = 2.dp,
                        color = veryLigthBlue,
                        shape = RoundedCornerShape(30.dp)
                    )
                    .background(
                        color = if (isCurrentCategory(category)) veryLigthBlue else Color.White,
                        shape = RoundedCornerShape(30.dp)
                    )
                    .clickable {
                        setCurrentCategory(category)
                        setSelectedInput(currentCategory.units.first())
                    },
                ) {
                    Row (
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        Text(
                            text = category.name,
                            color = if (isCurrentCategory(category)) Color.White else Color.Black
                        )
                    }
                }
            }

        }
    }
}