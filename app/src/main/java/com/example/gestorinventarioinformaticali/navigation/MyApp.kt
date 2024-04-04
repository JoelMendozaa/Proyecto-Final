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
import com.example.gestorinventarioinformaticali.pantallas.product.ActDesc
import com.example.gestorinventarioinformaticali.pantallas.product.InfoProduct
import com.example.gestorinventarioinformaticali.pantallas.product.Producto
import com.example.gestorinventarioinformaticali.pantallas.stock.SectionStock
import com.example.gestorinventarioinformaticali.pantallas.stock.Stock
import com.example.gestorinventarioinformaticali.pantallas.user.User
import com.example.gestorinventarioinformaticali.view.AgregarStock
import com.example.gestorinventarioinformaticali.view.AgregarView
import com.example.gestorinventarioinformaticali.view.EditarStock
import com.example.gestorinventarioinformaticali.view.EditarView
import com.example.gestorinventarioinformaticali.viewmodel.ProductosViewModel
import com.example.gestorinventarioinformaticali.viewmodel.StockViewModel

enum class ScreenList{
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
    NavHost(
        navController = navController,
        startDestination = ScreenList.Login.name
    ) {
        composable(route = ScreenList.Login.name) {
            Login(
                navController = navController
            )
        }
        composable(route = ScreenList.Principal.name) {
            Principal(
                onButtonClickedFuncApp = { navController.navigate(ScreenList.FuncApp.name) },
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
                onButtonClickedFuncApp = { navController.navigate(ScreenList.FuncApp.name) },
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
        composable(route = ScreenList.ActDesc.name){
            ActDesc(
                onButtonClickedFuncApp = { navController.navigate(ScreenList.FuncApp.name) },
                onButtonClickedUser = { navController.navigate(ScreenList.User.name) },
                onButtonClickedHome = { navController.navigate(ScreenList.Principal.name) },
                onButtonClickedStock = { navController.navigate(ScreenList.SectionStock.name) },
                navController = navController,
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
        composable("agregar1"){
            AgregarView(navController, viewModel)
        }
        composable("editar/{id}/{nombre}/{marca}/", arguments = listOf(
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
        composable("agregar2"){
            AgregarStock(navController, viewModel2)
        }
        composable("editar/{id}/{nombre}/{marca}/{stock}", arguments = listOf(
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
