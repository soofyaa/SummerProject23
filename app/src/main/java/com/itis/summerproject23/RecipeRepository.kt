package com.itis.summerproject23

object RecipeRepository {
    val list: List<Recipe> = listOf(
        Recipe(
            id = 1,
            name = "Блины",
            url = "https://img.joinfo.com/i/2020/02/800x0/1499450293595fcbb53f4fa9.22298426.jpg"),
        Recipe(
            id = 2,
            name = "Рагу",
            url = "https://i.pinimg.com/originals/73/71/08/73710871a28a131f6aa198182f966c43.jpg"),
        Recipe(
            id = 3,
            name = "Пицца",
            url = "https://n1s1.hsmedia.ru/4e/fa/18/4efa18fab68b54bfbe731ffed6d475ea/728x542_1_4b74ef42acac035d207606a9427e04f0@1000x745_0xac120003_11819542861578938700.jpg"),
        Recipe(
            id = 4,
            name = "Роллы",
            url = "https://img.delo-vcusa.ru/2019/09/rolly-s-gorbushej-i-svezhim-ogurcom.jpg"),
        Recipe(
            id = 5,
            name = "Яйцо пашот",
            url = "https://cdn.lifehacker.ru/wp-content/uploads/2018/03/shutterstock_278416631-1_1592808634-630x315.jpg")
    )
    fun getRecipeByName(name: String): Recipe? {
        return list.find { it.name.toLowerCase() == name.toLowerCase() }
    }
}