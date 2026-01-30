package com.example.januaryrecipe.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil3.compose.AsyncImage
import coil3.request.CachePolicy
import coil3.request.ImageRequest
import com.example.januaryrecipe.R
import com.example.januaryrecipe.data.Recipe
import com.example.januaryrecipe.ui.theme.InstrumentSans
import com.example.januaryrecipe.ui.theme.InstrumentSerif

@Composable
fun RecipeDialog(
    recipe: Recipe,
    onDismissRequestClicked: () -> Unit,
    windowWidthSizeClass: WindowWidthSizeClass,
    modifier: Modifier = Modifier
) {
    Dialog(
        onDismissRequest = onDismissRequestClicked,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        val cardModifier = when (windowWidthSizeClass) {
            WindowWidthSizeClass.Compact -> {
                modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            }

            WindowWidthSizeClass.Medium, WindowWidthSizeClass.Expanded -> {
                modifier.width(520.dp)
            }

            else -> {
                modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            }
        }
        Card(
            modifier = cardModifier,
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(1.dp, Color.White),
            colors = CardDefaults.cardColors(colorResource(id = R.color.background))
        ) {
            Column {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(recipe.image)
                        .memoryCacheKey(recipe.name)
                        .diskCacheKey(recipe.name)
                        .memoryCachePolicy(CachePolicy.ENABLED)
                        .diskCachePolicy(CachePolicy.ENABLED)
                        .build(),
                    contentDescription = stringResource(id = R.string.recipe_image_content_description),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop,

                    )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = recipe.title,
                    fontFamily = InstrumentSerif,
                    fontWeight = FontWeight.Normal,
                    fontSize = 32.sp,
                    color = colorResource(id = R.color.text_primary),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = recipe.ingredients,
                    fontFamily = InstrumentSans,
                    fontWeight = FontWeight.Light,
                    fontSize = 14.sp,
                    color = colorResource(id = R.color.text_primary),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = recipe.description,
                    fontFamily = InstrumentSans,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    color = colorResource(id = R.color.text_primary),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
                Button(
                    onClick = onDismissRequestClicked,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    border = BorderStroke(1.dp, colorResource(id = R.color.outline)),
                    shape = RoundedCornerShape(4.dp),
                    colors = ButtonColors(
                        containerColor = colorResource(id = R.color.background),
                        contentColor = colorResource(id = R.color.text_primary),
                        disabledContentColor = colorResource(id = R.color.text_primary),
                        disabledContainerColor = colorResource(id = R.color.background),
                    )
                ) {
                    Text(
                        text = stringResource(id = R.string.recipe_dialog_done_button),
                        fontFamily = InstrumentSans,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                        color = colorResource(id = R.color.text_primary)
                    )
                }
            }
        }
    }
}
