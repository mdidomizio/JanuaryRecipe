package com.example.januaryrecipe.data

import androidx.annotation.DrawableRes
import com.example.januaryrecipe.R

enum class Recipe(
    val title: String,
    val ingredients: String,
    val description: String,
    @DrawableRes val image: Int
) {
    CREAMY_MUSHROOM_SOUP(
        "Creamy Mushroom Soup",
        "Mushrooms, vegetable broth, onion, garlic, cream, thyme",
        "Sauté onions and garlic until fragrant, then add mushrooms and broth. Simmer gently and finish with cream for a smooth, comforting soup.",
        R.drawable.creamy_mushroom_soup
    ),
    WINTER_VEGETABLE_STEW(
        "Winter Vegetable Stew",
        "Carrots, potatoes, parsnips, onion, vegetable broth, rosemary",
        "Cook chopped vegetables with herbs in vegetable broth until tender. Serve hot as a hearty winter stew.",
        R.drawable.winter_vegetable_soup
    ),
    SPICED_LENTIL_SOUP(
        "Spiced Lentil Soup",
        "Red lentils, vegetable broth, carrot, onion, garlic, cumin",
        "Sauté the chopped vegetables with garlic and spices until fragrant, then add lentils and broth. Simmer until the lentils soften and the soup thickens.",
        R.drawable.spiced_lentil_soup
    ),
    POTATO_LEEK_COMFORT_SOUP(
        "Potato & Leek Comfort Soup",
        "Potatoes, leeks, butter, vegetable broth, cream",
        "Slowly cook leeks in butter, add potatoes and broth, then simmer until soft. Blend lightly and finish with cream.",
        R.drawable.potato_leek_comfort_soup
    ),
    GINGER_HONEY_TEA(
        "Ginger Honey Tea",
        "Fresh ginger, honey, lemon, water",
        "Steep sliced ginger in hot water, then add honey and lemon. Serve warm for a soothing winter drink.",
        R.drawable.ginger_honey_tea
    ),
    HOT_SPICED_COCOA(
        "Hot Spiced Cocoa",
        "Cocoa powder, milk, cinnamon, nutmeg, sugar",
        "Heat milk with cocoa and spices until smooth and rich. Serve warm with a light sprinkle of cinnamon.",
        R.drawable.hot_spiced_cocoa
    ),
    APPLE_CINNAMON_BREW(
        "Apple Cinnamon Brew",
        "Apple slices, cinnamon sticks, cloves, honey, water",
        "Simmer apples and spices in water to release their flavor. Sweeten lightly and serve warm.",
        R.drawable.apple_cinnamon_brew
    ),
    WARM_BANANA_OAT_MUFFINS(
        "Warm Banana Oat Muffins",
        "Bananas, oats, flour, eggs, honey, baking powder",
        "Mix mashed bananas with oats and batter ingredients, then bake until golden and soft.",
        R.drawable.warm_banana_oat_muffins
    ),
    CINNAMON_SWIRL_ROLLS(
        "Cinnamon Swirl Rolls",
        "Flour, cinnamon, butter, sugar, yeast",
        "Roll soft dough with cinnamon sugar filling and bake until fluffy. Serve warm for best flavor.",
        R.drawable.cinnamon_swirl_rolls
    ),
    BAKED_APPLE_CRISP(
        "Baked Apple Crisp",
        "Apples, oats, butter, brown sugar, cinnamon",
        "Bake sliced apples topped with a crunchy oat mixture until golden and bubbling.",
        R.drawable.baked_apple_crisp
    )
}
