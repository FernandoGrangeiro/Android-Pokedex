package com.fernando.pokedex.presentation.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import com.fernando.pokedex.presentation.model.PokedexDisplayable
import eu.krzdabrowski.starter.basicfeature.R
import eu.krzdabrowski.starter.core.design.Typography

@Composable
fun PokedexItem(
    pokedex: PokedexDisplayable,
    modifier: Modifier = Modifier,
    onPokedexClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .padding(
                vertical = dimensionResource(id = R.dimen.dimen_medium),
            )
            .clickable { onPokedexClick() },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            modifier = Modifier
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(
                dimensionResource(id = R.dimen.dimen_small),
            ),
        ) {
            Text(
                text = pokedex.name,
                style = Typography.titleMedium,
            )

            Text(
                text = stringResource(
                    id = R.string.pokedex_cost_per_launch,
                    pokedex.baseExperience,
                ),
                style = Typography.bodyMedium,
            )

            Text(
                text = stringResource(
                    id = R.string.pokedex_first_flight,
                    pokedex.isDefault,
                ),
                style = Typography.bodyMedium,
            )

            Text(
                text = stringResource(
                    id = R.string.pokedex_height,
                    pokedex.type,
                ),
                style = Typography.bodyMedium,
            )

            Text(
                text = stringResource(
                    id = R.string.pokedex_weight,
                    pokedex.weight,
                ),
                style = Typography.bodyMedium,
            )
        }

        AsyncImage(
            model = pokedex.imageUrl,
            contentDescription = stringResource(id = R.string.pokedex_image_content_description),
            modifier = Modifier
                .weight(1f),
        )
    }
}
