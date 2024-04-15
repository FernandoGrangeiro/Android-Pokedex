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

const val Pokedex_DIVIDER_TEST_TAG = "PokedexDividerTestTag"

@Composable
fun PokedexListContent(
    PokedexList: List<PokedexDisplayable>,
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
            items = PokedexList,
            key = { _, Pokedex -> Pokedex.id },
        ) { index, item ->
            PokedexItem(
                Pokedex = item,
                onPokedexClick = { onPokedexClick(item.wikiUrl) },
            )

            if (index < PokedexList.lastIndex) {
                HorizontalDivider(
                    modifier = Modifier.testTag(Pokedex_DIVIDER_TEST_TAG),
                )
            }
        }
    }
}
