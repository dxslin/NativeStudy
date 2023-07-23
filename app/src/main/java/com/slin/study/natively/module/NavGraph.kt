package com.slin.study.natively.module

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.slin.study.natively.widget.ScaffoldWithCsAppBar

const val ROUTE_SAMPLES = "route_samples"

val LocalNavController = compositionLocalOf<NavController?> { null }

@Composable
fun NavGraph(startDestination: String = ROUTE_SAMPLES) {

    val navController = rememberNavController()
    CompositionLocalProvider(LocalNavController provides navController) {
        NavHost(navController = navController, startDestination = startDestination) {
            composable(route = ROUTE_SAMPLES) {
                Samples { sample ->
                    navController.navigate(sample.destination)
                }
            }

            samples.forEach { page ->
                composable(route = page.destination) {
                    if (page.withActionBar) {
                        WithActionBar(page)
                    } else {
                        page.content(Modifier)
                    }
                }
            }
        }
    }

}


@Composable
private fun WithActionBar(page: SamplePage) {
    ScaffoldWithCsAppBar(
        isShowBack = true,
        title = page.name,
    ) { paddingValues ->
        page.content(Modifier.padding(paddingValues))
    }
}
