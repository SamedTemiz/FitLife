package com.samedtemiz.fitlife.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.samedtemiz.fitlife.data.model.ingredient.Ingredient


class CalorieViewModel() : ViewModel() {

    private val _ingredientList = MutableLiveData<List<Ingredient>>()
    val ingredientList: LiveData<List<Ingredient>> = _ingredientList

    init {
        _ingredientList.value = ingredientList()
    }
}

fun ingredientList(): List<Ingredient> {

    return listOf(
        Ingredient(
            1.0,
            1,
            "https://spoonacular.com/cdn/ingredients_100x100/apple.jpg",
            "Apple",
            95.0,
            0.0,
            1.0,
            19.0
        ),

        Ingredient(
            1.0,
            2,
            "https://spoonacular.com/cdn/ingredients_100x100/pineapple.jpg",
            "Pineapple",
            82.0,
            0.20,
            0.89,
            16.3
        ),

        Ingredient(
            1.0,
            3,
            "https://spoonacular.com/cdn/ingredients_100x100/pear.jpg",
            "Pear",
            57.0,
            0.1,
            0.4,
            10.0
        ),

        Ingredient(
            1.0,
            4,
            "https://spoonacular.com/cdn/ingredients_100x100/peanut-butter.png",
            "Low Fat Peanut Butter",
            5.2,
            0.34,
            0.26,
            0.09
        ),

        Ingredient(
            1.0,
            5,
            "https://spoonacular.com/cdn/ingredients_100x100/taco-shells.jpg",
            "Hard Taco Shells",
            57.12,
            2.62,
            0.77,
            0.18
        ),

        Ingredient(
            1.0,
            6,
            "https://spoonacular.com/cdn/ingredients_100x100/cornmeal.png",
            "Corn Meal",
            3.84,
            0.06,
            0.1,
            0.02
        ),

        Ingredient(
            1.0,
            7,
            "https://spoonacular.com/cdn/ingredients_100x100/chocolate-chips.jpg",
            "Dark Chocolate Morsels",
            5.4,
            0.31,
            0.08,
            0.34
        ),

        Ingredient(
            1.0,
            8,
            "https://spoonacular.com/cdn/ingredients_100x100/whole-chicken.jpg",
            "Whole Chicken",
            1637.78,
            114.72,
            141.69,
            0.0
        ),

        Ingredient(
            1.0,
            9,
            "https://spoonacular.com/cdn/ingredients_100x100/jello-strawberry.jpg",
            "Strawberry Gelatin",
            3.81,
            0.0,
            0.08,
            0.86
        ),

        Ingredient(
            1.0,
            10,
            "https://spoonacular.com/cdn/ingredients_100x100/dried-rice-noodles.jpg",
            "Rice Vermicelli",
            3.64,
            0.01,
            0.03,
            0.05
        ),


        )

}