package com.example.januaryrecipe.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.example.januaryrecipe.R
import com.example.januaryrecipe.data.Recipe
import com.example.januaryrecipe.ui.theme.InstrumentSerif

@Composable
fun RecipeCard(
    recipe: Recipe,
    onCardClicked: () -> Unit,
    modifier: Modifier = Modifier
) {

        Column(){
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clickable(onClick = onCardClicked)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(recipe.image)
                        .memoryCacheKey(recipe.name)
                        .diskCacheKey(recipe.name)
                        .build(),
                    contentDescription = stringResource(R.string.recipe_image_content_description),
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = recipe.title,
                fontFamily = InstrumentSerif,
                fontWeight = FontWeight.Normal,
                fontSize = 28.sp,
                color = colorResource(id = R.color.text_primary)
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
