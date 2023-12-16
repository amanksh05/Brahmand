package com.example.brahmand

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.brahmand.data.Planet
import com.example.brahmand.data.planets
import com.example.brahmand.ui.theme.BrahmandTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BrahmandTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BrahmandApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun BrahmandApp() {
    Scaffold(
        topBar = {
            PlanetTopAppBar()
        }
    ) { it ->
        LazyColumn(contentPadding = it) {
            items(planets) {
                PlanetItem(
                    planet = it,
                    modifier = Modifier.padding(8.dp)
                )

            }
        }

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlanetTopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = stringResource(id = R.string.app_name),
                    style = MaterialTheme.typography.displayMedium,
                    fontFamily = FontFamily.Cursive
                )
            }
        },
        modifier = modifier
    )
}

@Composable
fun PlanetItem(
    planet: Planet,
    modifier: Modifier = Modifier,
) {
    var expanded by remember {
        mutableStateOf(false)
    }

    Card(modifier = modifier, elevation = CardDefaults.cardElevation(defaultElevation = 3.dp))
    {
        Column(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                ),
            horizontalAlignment = Alignment.CenterHorizontally

        )
        {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                PlanetIcon(planet.imageResourceId)
                PlanetName(planet.planetName, planet.nickName)
                Spacer(Modifier.weight(1f))
                PlanetItemButton(
                    expanded = expanded,
                    onClick = { expanded = !expanded }
                )
            }

            if (expanded) {
                PlanetDetail(
                    planet.distance, planet.moons, planet.mass, modifier = Modifier.padding(8.dp)
                )
            }

        }

    }
}

@Composable
fun PlanetDetail(
    @StringRes distance: Int,
    @StringRes moons: Int,
    @StringRes mass: Int,
    modifier: Modifier=Modifier,
) {
    Row(Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceEvenly) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Distance",
                fontSize = 14.sp,
                textDecoration = TextDecoration.Underline
            )
            Text(text = stringResource(id = distance))

        }
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                text = "Mass",
                fontSize = 14.sp,
                textDecoration = TextDecoration.Underline
            )
            Text(text = stringResource(id = mass))

        }
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Moon",
                fontSize = 14.sp,
                textDecoration = TextDecoration.Underline
            )
            Text(text = stringResource(id = moons))
        }
    }

}

@Composable
fun PlanetName(
    @StringRes planetName: Int,
    @StringRes nickName: Int,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(id = planetName),
            fontSize=24.sp,
            modifier = Modifier.padding(top = 8.dp)
        )
        Text(
            text = stringResource(id = nickName),
            fontSize = 14.sp
        )
    }
}

@Composable
fun PlanetIcon(
    @DrawableRes planetIcon: Int,
    modifier: Modifier = Modifier,
) {
    Image(
        modifier = modifier
            .size(64.dp)
            .padding(8.dp)
            .clip(MaterialTheme.shapes.small),
        painter = painterResource(planetIcon), contentDescription = null
    )
}

@Composable
private fun PlanetItemButton(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
            contentDescription = null
        )

    }
}


@Preview
@Composable
private fun Preview() {
    PlanetDetail(distance = R.string.distance_uranus, moons =R.string.moons_uranus , mass =R.string.mUranus , modifier = Modifier)

}
