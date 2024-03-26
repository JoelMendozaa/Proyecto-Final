package com.example.gestorinventarioinformaticali

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.gestorinventarioinformaticali.pantallas.Principal
import com.example.gestorinventarioinformaticali.pantallas.infoApp.FuncApp
import com.example.gestorinventarioinformaticali.pantallas.login.Login
import com.example.gestorinventarioinformaticali.pantallas.login.Register
import com.example.gestorinventarioinformaticali.pantallas.product.InfoProduct
import com.example.gestorinventarioinformaticali.pantallas.stock.SectionStock
import com.example.gestorinventarioinformaticali.pantallas.stock.Stock
import com.example.gestorinventarioinformaticali.pantallas.user.User


enum class ScreenList{
    Login,
    Register,
    User,
    Principal,
    InfoProduct,
    FuncApp,
    ActDesc,
    MoreProduct,
    SectionStock,
    Stock
}

@Composable
fun MyApp(navController: NavHostController = rememberNavController()) {
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
                navController = navController
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
    }
}
