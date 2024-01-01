package com.rodoyf.satonda.feature_app.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.rodoyf.satonda.R
import com.rodoyf.satonda.feature_app.navigation.Screen
import com.rodoyf.satonda.feature_app.presentation.util.clickableWithoutRipple
import com.rodoyf.satonda.ui.theme.ralewayFamily

@Composable
fun CategoriesPart(
    modifier: Modifier = Modifier,
    navController: NavController,
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 50.dp, end = 10.dp, start = 10.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.category),
            fontFamily = ralewayFamily,
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(30.dp))

        // general row
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            // notes
            Box(
                modifier = Modifier
                    .size(width = 176.dp, height = 156.dp)
                    .clip(shape = RoundedCornerShape(35.dp))
                    .clickableWithoutRipple {
                        navController.navigate(Screen.NotesScreen.route)
                    }
                    .background(MaterialTheme.colorScheme.onTertiaryContainer)
                    .border(
                        1.5.dp,
                        MaterialTheme.colorScheme.onSurface.copy(0.5f),
                        RoundedCornerShape(35.dp)
                    ),
                contentAlignment = Alignment.Center,
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.category_note),
                        contentDescription = stringResource(R.string.note_category),
                        modifier = Modifier.size(60.dp),
                        tint = MaterialTheme.colorScheme.onSurface.copy(0.8f)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = stringResource(R.string.notes),
                        fontFamily = ralewayFamily,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.onSurface.copy(0.8f)
                    )
                }
            }

            // tasks
            Box(
                modifier = Modifier
                    .size(width = 176.dp, height = 156.dp)
                    .clip(shape = RoundedCornerShape(35.dp))
                    .clickableWithoutRipple {
                        navController.navigate(Screen.TaskScreen.route)
                    }
                    .background(MaterialTheme.colorScheme.onBackground)
                    .border(
                        1.5.dp,
                        MaterialTheme.colorScheme.onSurface.copy(0.5f),
                        RoundedCornerShape(35.dp)
                    ),
                contentAlignment = Alignment.Center,
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.category_task),
                        contentDescription = stringResource(R.string.task_category),
                        modifier = Modifier.size(60.dp),
                        tint = MaterialTheme.colorScheme.onSurface.copy(0.8f)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = stringResource(R.string.tasks),
                        fontFamily = ralewayFamily,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.onSurface.copy(0.8f)
                    )
                }
            }
        }
    }
}