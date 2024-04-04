package com.example.gestorinventarioinformaticali.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.gestorinventarioinformaticali.pantallas.Principal
import com.example.gestorinventarioinformaticali.pantallas.infoApp.FuncApp
import com.example.gestorinventarioinformaticali.pantallas.login.Login
import com.example.gestorinventarioinformaticali.pantallas.product.ActDesc
import com.example.gestorinventarioinformaticali.pantallas.product.InfoProduct
import com.example.gestorinventarioinformaticali.view.Producto
import com.example.gestorinventarioinformaticali.pantallas.stock.SectionStock
import com.example.gestorinventarioinformaticali.view.Stock
import com.example.gestorinventarioinformaticali.pantallas.user.User
import com.example.gestorinventarioinformaticali.view.AgregarStock
import com.example.gestorinventarioinformaticali.view.AgregarView
import com.example.gestorinventarioinformaticali.view.EditarStock
import com.example.gestorinventarioinformaticali.view.EditarView
import com.example.gestorinventarioinformaticali.viewmodel.ProductosViewModel
import com.example.gestorinventarioinformaticali.viewmodel.StockViewModel

enum class ScreenList{
    // Enumeración que define las diferentes pantallas de la aplicación
    Login,
    User,
    Principal,
    InfoProduct,
    FuncApp,
    ActDesc,
    SectionStock,
    Stock,
    Product,
}

@Composable
fun MyApp(navController: NavHostController = rememberNavController(), viewModel: ProductosViewModel, viewModel2: StockViewModel) {
    // Estado mutable para descripción de producto
    val descriptionState = remember { mutableStateOf("") }

    // Componente de navegación que define las rutas y composables de la aplicación
    NavHost(
        navController = navController,
        startDestination = ScreenList.Login.name // Pantalla de inicio
    ) {
        // Composable para la pantalla de inicio de sesión
        composable(route = ScreenList.Login.name) {
            Login(
                navController = navController
            )
        }
        // Composable para la pantalla principal
        composable(route = ScreenList.Principal.name) {
            Principal(
                // Lambdas para manejar eventos de botones y control de navegación
                onButtonClickedFuncApp = { navController.navigate(ScreenList.FuncApp.name) },
                onButtonClickedStock = { navController.navigate(ScreenList.SectionStock.name) },
                onButtonClickedPrincipal = { navController.navigate(ScreenList.Principal.name) },
                onButtonClickedUser = { navController.navigate(ScreenList.User.name) },
                onButtonClickedInfoProduct = { navController.navigate(ScreenList.InfoProduct.name) },
                onButtonClickedProduct = { navController.navigate(ScreenList.Product.name) },
                navController = navController
            )
        }
        // Composable para la pantalla de usuario
        composable(route = ScreenList.User.name) {
            User(
                // Lambdas para manejar eventos de botones y control de navegación
                onButtonClickedLogin = { navController.navigate(ScreenList.Login.name) },
                onButtonClickedFuncApp = { navController.navigate(ScreenList.FuncApp.name) },
                onButtonClickedUser = { navController.navigate(ScreenList.User.name) },
                onButtonClickedHome = { navController.navigate(ScreenList.Principal.name) },
                onButtonClickedStock = { navController.navigate(ScreenList.SectionStock.name) }
            )
        }
        composable(route = ScreenList.InfoProduct.name) {
            InfoProduct(
                onButtonClickedFuncApp = { navController.navigate(ScreenList.FuncApp.name) },
                onButtonClickedUser = { navController.navigate(ScreenList.User.name) },
                onButtonClickedHome = { navController.navigate(ScreenList.Principal.name) },
                onButtonClickedStock = { navController.navigate(ScreenList.SectionStock.name) },
                navController = navController,
                description = descriptionState.value,
                onButtonClickedActDesc = { navController.navigate(ScreenList.ActDesc.name) }
            )
        }
        composable(route = ScreenList.ActDesc.name) {
            ActDesc(
                onButtonClickedFuncApp = { navController.navigate(ScreenList.FuncApp.name) },
                onButtonClickedUser = { navController.navigate(ScreenList.User.name) },
                onButtonClickedHome = { navController.navigate(ScreenList.Principal.name) },
                onButtonClickedStock = { navController.navigate(ScreenList.SectionStock.name) },
                navController = navController,
                onDescriptionChanged = { description ->
                    descriptionState.value = description
                    navController.navigate(ScreenList.InfoProduct.name)
                }
            )
        }
        composable(route = ScreenList.FuncApp.name){
            FuncApp(
                onButtonClickedFuncApp = { navController.navigate(ScreenList.FuncApp.name) },
                onButtonClickedStock = { navController.navigate(ScreenList.SectionStock.name) },
                onButtonClickedHome = {  navController.navigate(ScreenList.Principal.name) },
                onButtonClickedUser = { navController.navigate(ScreenList.User.name) },
                navController = navController
            )
        }
        composable(route = ScreenList.SectionStock.name){
            SectionStock(
                onButtonClickedFuncApp = { navController.navigate(ScreenList.FuncApp.name) },
                onButtonClickedStock = { navController.navigate(ScreenList.SectionStock.name) },
                onButtonClickedHome = {  navController.navigate(ScreenList.Principal.name) },
                onButtonClickedUser = { navController.navigate(ScreenList.User.name) },
                navController = navController,
                viewModel = viewModel2
            )
        }
        composable(route = ScreenList.Stock.name){
            Stock(
                onButtonClickedFuncApp = { navController.navigate(ScreenList.FuncApp.name) },
                onButtonClickedStock = { navController.navigate(ScreenList.SectionStock.name) },
                onButtonClickedHome = { navController.navigate(ScreenList.Principal.name) },
                onButtonClickedUser = { navController.navigate(ScreenList.User.name) },
                viewModel = viewModel2,
                navController = navController
            )
        }
        composable(route = ScreenList.Product.name){
            Producto(
                onButtonClickedFuncApp = { navController.navigate(ScreenList.FuncApp.name) },
                onButtonClickedStock = { navController.navigate(ScreenList.Stock.name) },
                onButtonClickedHome = { navController.navigate(ScreenList.Principal.name) },
                onButtonClickedUser = { navController.navigate(ScreenList.User.name) },
                navController = navController,
                viewModel = viewModel
            )
        }
        // Pantalla para agregar un nuevo producto
        composable("agregar1"){
            AgregarView(navController, viewModel)
        }
        // Pantalla para editar un producto existente
        composable("editar/{id}/{nombre}/{marca}/", arguments = listOf(
            navArgument("id"){ type = NavType.IntType},
            navArgument("nombre"){ type = NavType.StringType},
            navArgument("marca"){ type = NavType.StringType},
        )){
            EditarView(
                navController,
                viewModel,
                it.arguments!!.getInt("id") ?: 0,
                it.arguments?.getString("nombre"),
                it.arguments?.getString("marca"),
            )
        }
        // Pantalla para agregar un nuevo elemento de stock
        composable("agregar2"){
            AgregarStock(navController, viewModel2)
        }
        // Pantalla para editar un elemento de stock existente
        composable("editar2/{id}/{nombre}/{marca}/{stock}", arguments = listOf(
            navArgument("id"){ type = NavType.IntType},
            navArgument("nombre"){ type = NavType.StringType},
            navArgument("marca"){type = NavType.StringType},
            navArgument("stock"){type = NavType.StringType}
        )){
            EditarStock(
                navController,
                viewModel2,
                it.arguments!!.getInt("id"),
                it.arguments!!.getString("nombre"),
                it.arguments!!.getString("marca"),
                it.arguments!!.getString("stock")
            )
        }
    }
}