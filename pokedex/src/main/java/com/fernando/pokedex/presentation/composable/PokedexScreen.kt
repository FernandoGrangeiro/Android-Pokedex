package com.fernando.pokedex.presentation.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalUriHandler
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.fernando.pokedex.presentation.PokedexEvent
import com.fernando.pokedex.presentation.PokedexIntent
import com.fernando.pokedex.presentation.PokedexUiState
import com.fernando.pokedex.presentation.PokedexViewModel
import eu.krzdabrowski.starter.core.utils.collectWithLifecycle
import kotlinx.coroutines.flow.Flow

@Composable
fun PokedexRoute(
    viewModel: PokedexViewModel = hiltViewModel(),
) {
    HandleEvents(viewModel.getEvents())
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    PokedexScreen(
        uiState = uiState,
        onIntent = viewModel::acceptIntent,
    )
}

@Composable
internal fun PokedexScreen(
    uiState: PokedexUiState,
    onIntent: (PokedexIntent) -> Unit,
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val pullToRefreshState = rememberPullToRefreshState()

    HandlePullToRefresh(
        pullState = pullToRefreshState,
        uiState = uiState,
        onIntent = onIntent,
    )

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .nestedScroll(pullToRefreshState.nestedScrollConnection),
        ) {
            if (uiState.pokedex.isNotEmpty()) {
                PokedexAvailableContent(
                    snackbarHostState = snackbarHostState,
                    uiState = uiState,
                    onPokedexClick = { onIntent(PokedexIntent.PokedexClicked(it)) },
                )
            } else {
                PokedexNotAvailableContent(
                    uiState = uiState,
                )
            }

            PullToRefreshContainer(
                state = pullToRefreshState,
                modifier = Modifier
                    .align(Alignment.TopCenter),
            )
        }
    }
}

@Composable
private fun HandlePullToRefresh(
    pullState: PullToRefreshState,
    uiState: PokedexUiState,
    onIntent: (PokedexIntent) -> Unit,
) {
    if (pullState.isRefreshing) {
        LaunchedEffect(true) {
            onIntent(PokedexIntent.RefreshPokedex)
        }
    }

    if (uiState.isLoading.not()) {
        LaunchedEffect(true) {
            pullState.endRefresh()
        }
    }
}

@Composable
private fun HandleEvents(events: Flow<PokedexEvent>) {
    val uriHandler = LocalUriHandler.current

    events.collectWithLifecycle {
        when (it) {
            is PokedexEvent.OpenWebBrowserWithDetails -> {
                uriHandler.openUri(it.uri)
            }
        }
    }
}

@Composable
private fun PokedexAvailableContent(
    snackbarHostState: SnackbarHostState,
    uiState: PokedexUiState,
    onPokedexClick: (String) -> Unit,
) {
    if (uiState.isError) {
        val errorMessage = "stringResource(R.string.Pokedex_error_refreshing)"

        LaunchedEffect(snackbarHostState) {
            snackbarHostState.showSnackbar(
                message = errorMessage,
            )
        }
    }

    PokedexListContent(
        pokedexList = uiState.pokedex,
        onPokedexClick = onPokedexClick,
    )
}

@Composable
private fun PokedexNotAvailableContent(uiState: PokedexUiState) {
    when {
        uiState.isLoading -> PokedexLoadingPlaceholder()
        uiState.isError -> PokedexErrorContent()
    }
}
