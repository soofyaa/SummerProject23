package com.itis.summerproject23

object RecipeRepository {
    val list: List<Recipe> = listOf(
        Recipe(name = "Блины", url = "https://img.joinfo.com/i/2020/02/800x0/1499450293595fcbb53f4fa9.22298426.jpg"),
        Recipe(name = "Рагу", url = "https://i.pinimg.com/originals/73/71/08/73710871a28a131f6aa198182f966c43.jpg")
    )
    fun getRecipeByName(name: String): Recipe? {
        return list.find { it.name.toString().toLowerCase() == name.toString().toLowerCase() }
    }
}