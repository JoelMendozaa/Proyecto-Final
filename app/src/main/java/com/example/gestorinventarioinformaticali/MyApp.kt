package com.example.gestorinventarioinformaticali

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.gestorinventarioinformaticali.pantallas.Principal
import com.example.gestorinventarioinformaticali.pantallas.login.Login
import com.example.gestorinventarioinformaticali.pantallas.login.Register


enum class ScreenList{
    Login,
    Register,
    User,
    UserUnlogin,
    Principal,
    InfoProduct,
    InfoApp,
    InfoAppUnlog,
    Categories,
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
                onButtonClickedInfoApp = { navController.navigate(ScreenList.InfoApp.name) },
                onButtonClickedStock = { navController.navigate(ScreenList.Stock.name) },
                onButtonClickedHome = { navController.navigate(ScreenList.Principal.name) },
                onButtonClickedUser = { navController.navigate(ScreenList.User.name) }
            )
//        composable(route = ScreenList.InfoAppUnlog.name){
//            InfoAppUnlogued()
//        }
//        composable(route = ScreenList.SectionStock.name){
//            SectionStock()
//        }
//        composable(route = ScreenList.UserUnlogin.name){
//            UserUnlogin()
//        }
        }
    }
}
