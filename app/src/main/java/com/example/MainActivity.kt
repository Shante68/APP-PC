package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.data.model.ComponentCategory
import com.example.data.model.UsePurpose
import com.example.ui.screens.BuilderScreen
import com.example.ui.screens.CatalogScreen
import com.example.ui.screens.GuideScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.SavedBuildsScreen
import com.example.ui.screens.WizardScreen
import com.example.ui.theme.RigCraftTheme
import com.example.ui.viewmodel.BuilderViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RigCraftTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RigCraftApp()
                }
            }
        }
    }
}

@Composable
fun RigCraftApp() {
    val navController = rememberNavController()
    val viewModel: BuilderViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(
                onNavigateToWizard = { purpose ->
                    val purposeArg = purpose?.name ?: "NONE"
                    navController.navigate("wizard/$purposeArg")
                },
                onNavigateToCatalog = {
                    viewModel.setCategoryFilter(null)
                    navController.navigate("catalog")
                },
                onNavigateToBuilder = {
                    navController.navigate("builder")
                },
                onNavigateToSaved = {
                    navController.navigate("saved")
                },
                onNavigateToGuide = {
                    navController.navigate("guide")
                },
                onQuickPresetSelected = { purpose, budget ->
                    viewModel.generateRecommendedBuild(purpose, budget, null)
                    navController.navigate("builder")
                }
            )
        }

        composable(
            route = "wizard/{purposeName}",
            arguments = listOf(navArgument("purposeName") { type = NavType.StringType })
        ) { backStackEntry ->
            val purposeName = backStackEntry.arguments?.getString("purposeName")
            val initialPurpose = try {
                if (purposeName != null && purposeName != "NONE") UsePurpose.valueOf(purposeName) else null
            } catch (e: Exception) {
                null
            }

            WizardScreen(
                initialPurpose = initialPurpose,
                onBack = { navController.popBackStack() },
                onGenerateAndOpenBuilder = { purpose, budget, brandPref ->
                    viewModel.generateRecommendedBuild(purpose, budget, brandPref)
                    navController.navigate("builder") {
                        popUpTo("home")
                    }
                }
            )
        }

        composable("catalog") {
            CatalogScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() }
            )
        }

        composable("builder") {
            BuilderScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
                onSelectCategoryForCatalog = { category ->
                    viewModel.setCategoryFilter(category)
                    navController.navigate("catalog")
                }
            )
        }

        composable("saved") {
            SavedBuildsScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
                onLoadBuildToStudio = {
                    navController.navigate("builder")
                }
            )
        }

        composable("guide") {
            GuideScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }
}
