package com.samedtemiz.fitlife.data.model.ingredient

data class Nutrition(
    val caloricBreakdown: CaloricBreakdown,
    val flavonoids: List<Flavonoid>,
    val nutrients: List<Nutrient>,
    val properties: List<Property>,
    val weightPerServing: WeightPerServing
)