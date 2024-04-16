package com.fernando.pokedex.presentation.composable

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import com.fernando.pokedex.presentation.model.PokedexDisplayable
import eu.krzdabrowski.starter.basicfeature.R

const val POKEDEX_DIVIDER_TEST_TAG = "PokedexDividerTestTag"

@Composable
fun PokedexListContent(
    pokedexList: List<PokedexDisplayable>,
    modifier: Modifier = Modifier,
    onPokedexClick: (String) -> Unit,
) {
    LazyColumn(
        modifier = modifier
            .padding(
                horizontal = dimensionResource(id = R.dimen.dimen_medium),
            ),
    ) {
        itemsIndexed(
            items = pokedexList,
            key = { _, pokedex -> pokedex.id },
        ) { index, item ->
            PokedexItem(
                pokedex = item,
                onPokedexClick = { onPokedexClick(item.toString()) },
            )

            if (index < pokedexList.lastIndex) {
                HorizontalDivider(
                    modifier = Modifier.testTag(POKEDEX_DIVIDER_TEST_TAG),
                )
            }
        }
    }
}
