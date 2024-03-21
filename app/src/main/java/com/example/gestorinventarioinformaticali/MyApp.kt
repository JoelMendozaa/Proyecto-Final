package com.example.gestorinventarioinformaticali

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.gestorinventarioinformaticali.pantallas.Principal
import com.example.gestorinventarioinformaticali.pantallas.login.Login
import com.example.gestorinventarioinformaticali.pantallas.login.Register
import com.example.gestorinventarioinformaticali.pantallas.product.InfoProduct
import com.example.gestorinventarioinformaticali.pantallas.user.User


enum class ScreenList{
    Login,
    Register,
    User,
    Principal,
    InfoProduct,
    InfoApp,
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
                onButtonClickedPrincipal = { navController.navigate(ScreenList.Principal.name) },
                onButtonClickedUser = { navController.navigate(ScreenList.User.name) },
                onButtonClickedInfoProduct = { navController.navigate(ScreenList.InfoProduct.name) },
                navController = navController
            )
        }
        composable(route = ScreenList.User.name){
            User(
                onButtonClickedLogin = { navController.navigate(ScreenList.Login.name) },
                onButtonClickedInfoApp = { navController.navigate(ScreenList.InfoApp.name) },
                onButtonClickedUser = { navController.navigate(ScreenList.User.name) },
                onButtonClickedHome = { navController.navigate(ScreenList.Principal.name) },
                onButtonClickedStock = { navController.navigate(ScreenList.Stock.name) }
            )
        }
        composable(route = ScreenList.InfoProduct.name){
            InfoProduct(
                onButtonClickedInfoApp = { navController.navigate(ScreenList.InfoApp.name) },
                onButtonClickedUser = { navController.navigate(ScreenList.User.name) },
                onButtonClickedHome = { navController.navigate(ScreenList.Principal.name) },
                onButtonClickedStock = { navController.navigate(ScreenList.Stock.name) },
                navController = navController
            )
        }


//        composable(route = ScreenList.SectionStock.name){
//            SectionStock()
//        }

        }
    }
