package com.example.gestorinventarioinformaticali.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.gestorinventarioinformaticali.view.Principal
import com.example.gestorinventarioinformaticali.pantallas.infoApp.FuncApp
import com.example.gestorinventarioinformaticali.pantallas.login.Login
import com.example.gestorinventarioinformaticali.pantallas.login.Register
import com.example.gestorinventarioinformaticali.pantallas.product.InfoProduct
import com.example.gestorinventarioinformaticali.pantallas.product.MainScreen
import com.example.gestorinventarioinformaticali.pantallas.product.Productos
import com.example.gestorinventarioinformaticali.pantallas.stock.SectionStock
import com.example.gestorinventarioinformaticali.pantallas.stock.Stock
import com.example.gestorinventarioinformaticali.pantallas.user.User
import com.example.gestorinventarioinformaticali.view.AgregarView
import com.example.gestorinventarioinformaticali.view.EditarView
import com.example.gestorinventarioinformaticali.view.InicioView
import com.example.gestorinventarioinformaticali.viewmodel.ProductosViewModel


enum class ScreenList{
    Login,
    Register,
    User,
    Principal,
    InfoProduct,
    FuncApp,
    ActDesc,
    SectionStock,
    Stock,
    Product,
    MainScreen
}

@Composable
fun MyApp(navController: NavHostController = rememberNavController(), viewModel: ProductosViewModel) {
    NavHost(
        navController = navController,
        startDestination = ScreenList.Login.name
    ) {
        composable(route = ScreenList.Login.name) {
            Login(
                buttonClickedRegister = { navController.navigate(ScreenList.Register.name) },
                buttonClickedLogin = { navController.navigate(ScreenList.Principal.name) },
            )
        }
        composable(route = ScreenList.Register.name) {
            Register(
                buttonClickedPrincipal = { navController.navigate(ScreenList.Principal.name) }
            )
        }
        composable(route = ScreenList.Principal.name) {
            Principal(
                onButtonClickedInfoApp = { navController.navigate(ScreenList.FuncApp.name) },
                onButtonClickedStock = { navController.navigate(ScreenList.SectionStock.name) },
                onButtonClickedPrincipal = { navController.navigate(ScreenList.Principal.name) },
                onButtonClickedUser = { navController.navigate(ScreenList.User.name) },
                onButtonClickedInfoProduct = { navController.navigate(ScreenList.InfoProduct.name) },
                onButtonClickedProduct = { navController.navigate(ScreenList.Product.name) },
                navController = navController
            )
        }
        composable(route = ScreenList.User.name){
            User(
                onButtonClickedLogin = { navController.navigate(ScreenList.Login.name) },
                onButtonClickedInfoApp = { navController.navigate(ScreenList.FuncApp.name) },
                onButtonClickedUser = { navController.navigate(ScreenList.User.name) },
                onButtonClickedHome = { navController.navigate(ScreenList.Principal.name) },
                onButtonClickedStock = { navController.navigate(ScreenList.SectionStock.name) }
            )
        }
        composable(route = ScreenList.InfoProduct.name){
            InfoProduct(
                onButtonClickedFuncApp = { navController.navigate(ScreenList.FuncApp.name) },
                onButtonClickedUser = { navController.navigate(ScreenList.User.name) },
                onButtonClickedHome = { navController.navigate(ScreenList.Principal.name) },
                onButtonClickedStock = { navController.navigate(ScreenList.SectionStock.name) },
                navController = navController,
                onButtonClickedActDesc = { navController.navigate(ScreenList.ActDesc.name) }

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
                navController = navController
            )
        }
        composable(route = ScreenList.Stock.name){
            Stock(
                onButtonClickedFuncApp = { navController.navigate(ScreenList.FuncApp.name) },
                onButtonClickedStock = { navController.navigate(ScreenList.SectionStock.name) },
                onButtonClickedHome = {  navController.navigate(ScreenList.Principal.name) },
                onButtonClickedUser = { navController.navigate(ScreenList.User.name) },
                navController = navController
            )
        }
        composable(route = ScreenList.Product.name){
            Productos(
                onButtonClickedFuncApp = { navController.navigate(ScreenList.FuncApp.name) },
                onButtonClickedStock = { navController.navigate(ScreenList.Stock.name) },
                onButtonClickedHome = { navController.navigate(ScreenList.Principal.name) },
                onButtonClickedUser = { navController.navigate(ScreenList.User.name) },
                onButtonClickedInfoProduct = { navController.navigate(ScreenList.InfoProduct.name) },
                navController = navController
            )
        }
        composable(route = ScreenList.ActDesc.name){
            MainScreen(viewModel = viewModel, navController = navController)
        }
        composable("agregar"){
            AgregarView(navController, viewModel)
        }
        composable("editar/{id}/{nombre}/{marca}", arguments = listOf(
            navArgument("id"){ type = NavType.IntType},
            navArgument("nombre"){ type = NavType.StringType},
            navArgument("marca"){ type = NavType.StringType},
        )){
            EditarView(
                navController,
                viewModel,
                it.arguments!!.getInt("id"),
                it.arguments?.getString("nombre"),
                it.arguments?.getString("marca"),
            )
        }
    }
}
