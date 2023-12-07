package ch.sebug.cupcake

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ch.sebug.cupcake.data.DataSource
import ch.sebug.cupcake.ui.OrderSummaryScreen
import ch.sebug.cupcake.ui.OrderViewModel
import ch.sebug.cupcake.ui.SelectOptionScreen
import ch.sebug.cupcake.ui.StartOrderScreen

enum class CupcakeScreen() {
    Start,
    Flavor,
    Pickup,
    Summary
}

/**
 * Composable that displays the topBar and displays back button if back navigation is possible.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CupcakeAppBar(
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(id = R.string.app_name)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@Composable
fun CupcakeApp(
    viewModel: OrderViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        topBar = {
            CupcakeAppBar(
                canNavigateBack = false,
                navigateUp = { /* TODO: implement back navigation */ }
            )
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

        NavHost(navController = navController,
            startDestination = CupcakeScreen.Start.name,
            modifier = Modifier.padding(innerPadding)
            ) {
            composable(route = CupcakeScreen.Start.name) {
                StartOrderScreen(quantityOptions = DataSource.quantityOptions,
                    onNextButtonClicked = {
                    })
            }
            composable(route = CupcakeScreen.Flavor.name) {
                val context = LocalContext.current
                SelectOptionScreen(subtotal = uiState.price,
                    options = DataSource.flavors.map { context.resources.getString(it) },
                    onSelectionChanged = { viewModel.setFlavor(it) })
            }
            composable(route = CupcakeScreen.Pickup.name) {
                SelectOptionScreen(subtotal = uiState.price,
                    options = uiState.pickupOptions,
                    onSelectionChanged = { viewModel.setDate(it)})
            }
            composable(route = CupcakeScreen.Summary.name) {
                OrderSummaryScreen(orderUiState = uiState,
                    onCancelButtonClicked = {

                    },
                    onSendButtonClicked = {
                        fst, snd ->

                    })
            }
        }
    }
}