package test.saad.dindinnminiassignment.assingmenttask.datasource

import test.saad.dindinnminiassignment.assingmenttask.model.Product

object Datasource {
    val drinks = arrayListOf<String>("https://images-na.ssl-images-amazon.com/images/I/71cEMQhq3IL._AC_SL1000_.jpg",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSVZ9YvIoLO2-QYiTlwt9W8ETjnHXuvTsoyhQ&usqp=CAU",
        "https://i.ebayimg.com/images/g/R1wAAOSwm79cnHC-/s-l500.jpg",
        "https://staticecp.uprinting.com/150/530x530/Beverage_Labels_Marketing_Materials_A.jpg")
    val pizzas = arrayListOf<String>("https://qph.fs.quoracdn.net/main-qimg-011c868d31aea572be4be3aee53b397c",
    "https://cdnimg.webstaurantstore.com/uploads/blog/2016/8/rectangle.jpg",
        "https://cdnimg.webstaurantstore.com/uploads/blog/2019/3/blog-types-pizza_in-blog-8.jpg",
        "https://media.istockphoto.com/photos/square-shaped-pizza-picture-id156052256?k=6&m=156052256&s=170667a&w=0&h=HrzK7li1XJSuf5jO7NMuAA-sK7Qb1KByBvQfJZPAdPY=",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT5TXMnIxx1RyRHHkF30CItcZciTHlQg0rmVw&usqp=CAU",
        "https://cdnimg.webstaurantstore.com/uploads/buying_guide/2014/11/pizzatypes-margherita-.jpg",
        "https://qph.fs.quoracdn.net/main-qimg-e377be2cee9e66b9da397dc76a30aad2.webp",
        "https://s3.amazonaws.com/ocn-media/45f6887b-bf55-4fe8-82e0-939039bb0bdf.webp")
    val sushis = arrayListOf<String>("https://previews.123rf.com/images/ashtray25/ashtray251903/ashtray25190300033/119334025-japanese-sushi-rolls-in-the-shape-of-a-square-on-plate-top-view.jpg",
        "https://hellopetitefoodie.files.wordpress.com/2017/02/img_5539.jpg?w=427&h=498",
        "https://2xs0so2vt22q1niww1rw0cm7-wpengine.netdna-ssl.com/wp-content/uploads/2015/02/mosaic-sushi-roll-evolution.jpg",
        "https://i.pinimg.com/originals/da/4c/52/da4c52d6da277a5678b6e4d52193f695.jpg",
        "https://previews.123rf.com/images/karmast/karmast1002/karmast100200013/6368458-four-sushi-uramaki-and-five-sushi-hosomaki-in-a-square-shape.jpg",
        "https://ae01.alicdn.com/kf/HTB1sI4errsTMeJjy1zcq6xAgXXaw/Rectangle-Arch-Shape-Sushi-Plate-Wood-Vintage-Serving-Dishes-for-Cake-Square-Wooden-Yellow-Dessert-Sashimi.jpg_Q90.jpg_.webp",
        "https://img-global.cpcdn.com/recipes/5570465748746240/751x532cq70/easy-square-shaped-chirashizushi-and-cup-sushi-for-girls-day-recipe-main-photo.jpg"
      )
    val offers = listOf<String>(
        "https://cdn.grabon.in/gograbon/images/category/1546252575451.png",
        "https://images.unsplash.com/photo-1482049016688-2d3e1b311543?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=353&q=80",
        "https://d1csarkz8obe9u.cloudfront.net/posterpreviews/special-menu-template-design-5bab7b46189135c20fb7273f86934673_screen.jpg?ts=1594713368",
        "https://d1csarkz8obe9u.cloudfront.net/posterpreviews/restaurant-menu-or-special-offers-template-c52401378c824108ea084dd4f89d9eab_screen.jpg?ts=1561534831"
    )
    fun getRandomPizzaImage():String
    {
        return pizzas.random()
    }
    fun getRandomSushiImage():String
    {
        return sushis.random()
    }
    fun getRandomDrinkImage():String
    {
        return drinks.random()
    }
    val productCategories = listOf<String>("Pizza","Sushi","Drinks")
    val productNames = productCategories
    val pizzaProducts = arrayListOf<Product>(
        Product(
            getRandomPizzaImage(),
            "Pizza","Pepperoni Pizza",
            "Mushrooms, pepporoni, black pepper, sauces of all types and \neverything of the world inside it to please you","6 inch = 1 pieces",
            25,"USD",true),
        Product(
            getRandomPizzaImage(),"Pizza","Sheep Pizza",
            "Mushrooms, pepporoni, black pepper, sauces of all types and \neverything of the world inside it to please you","9 inch = 3 pieces",
            73,"USD",false),
        Product(
            getRandomPizzaImage(),"Pizza","Duck Pizza",
            "Mushrooms, pepporoni, black pepper, sauces of all types and \neverything of the world inside it to please you","18 inch = 7 pieces",
            69,"USD",false),
        Product(
            getRandomPizzaImage(),"Pizza","Deer Pizza",
            "Mushrooms, pepporoni, black pepper, sauces of all types and \neverything of the world inside it to please you","8 inch = 4 pieces",
            30,"USD",false),
        Product(
            getRandomPizzaImage(),"Pizza","Chicken Pizza",
            "Mushrooms, pepporoni, black pepper, sauces of all types and \neverything of the world inside it to please you","12 inch = 6 pieces",
            80,"USD",false),
        Product(
            getRandomPizzaImage(),"Pizza","Beef Pizza",
            "Mushrooms, pepporoni, black pepper, sauces of all types and \neverything of the world inside it to please you","21 inch = 12 pieces",
            50,"USD",false)
    )

    val shushiProducts = arrayListOf<Product>(
        Product(
            getRandomSushiImage(),
            "Sushi","Pepperoni Sushi",
            "Mushrooms, pepporoni, black pepper, sauces of all types and \neverything of the world inside it to please you","6 inch = 1 pieces",
            25,"USD",false),
        Product(
            getRandomSushiImage(),
            "Sushi","Sheep Sushi",
            "Mushrooms, pepporoni, black pepper, sauces of all types and \neverything of the world inside it to please you","9 inch = 3 pieces",
            73,"USD",false),
        Product(
            getRandomSushiImage(),"Sushi","Duck Sushi",
            "Mushrooms, pepporoni, black pepper, sauces of all types and \neverything of the world inside it to please you","18 inch = 7 pieces",
            69,"USD",true),
        Product(
            getRandomSushiImage(),"Sushi","Deer Sushi",
            "Mushrooms, pepporoni, black pepper, sauces of all types and \neverything of the world inside it to please you","8 inch = 4 pieces",
            30,"USD",false),
        Product(
            getRandomSushiImage(),"Sushi","Chicken Sushi",
            "Mushrooms, pepporoni, black pepper, sauces of all types and \neverything of the world inside it to please you","12 inch = 6 pieces",
            80,"USD",false),
        Product(
            getRandomSushiImage(),"Sushi","Beef Sushi",
            "Mushrooms, pepporoni, black pepper, sauces of all types and \neverything of the world inside it to please you","21 inch = 12 pieces",
            50,"USD",true)
    )

    val drinkProducts = arrayListOf<Product>(
        Product(
            getRandomDrinkImage(),"Drink","Coke",
            "250ml","1 Can",
            25,"USD",false),
        Product(getRandomDrinkImage(),"Drinks","Cola",
            "250ml","1 Can",
            73,"USD",false),
        Product(getRandomDrinkImage(),"Drinks","Coca Cola",
            "250ml","1 Can",
            69,"USD",false),
        Product(getRandomDrinkImage(),"Drinks","Pespi",
            "250ml","1 Can",
            30,"USD",false),
        Product(getRandomDrinkImage(),"Drinks","Copesi",
            "250ml","1 Can",
            80,"USD",false),
        Product(getRandomDrinkImage(),"Drinks","Red Bull",
            "250ml","1 Can",
            50,"USD",false)
    )
}