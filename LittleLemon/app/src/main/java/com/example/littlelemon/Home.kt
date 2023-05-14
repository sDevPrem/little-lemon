package com.example.littlelemon

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.littlelemon.data.model.MenuItemLocal
import com.example.littlelemon.data.model.util.Result
import com.example.littlelemon.ui.theme.app.AppTheme
import com.example.littlelemon.viewmodel.HomeVM

@Composable
fun HomeScreen(navController: NavController, homeVM: HomeVM = viewModel()) {
    val result = homeVM
        .menuData
        .collectAsStateWithLifecycle()
        .value

    HomeScreenUI(result, homeVM::fetchData) {
        navController.navigate(Destinations.Profile.getRoute())
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreenUI(
    result: Result<List<MenuItemLocal>>,
    onRetry: () -> Unit,
    navigateToProfile: () -> Unit
) {
    var query by rememberSaveable { mutableStateOf("") }
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        item(
            key = "header"
        ) {
            Column {
                NavBar(navigateToProfile)
                Spacer(modifier = Modifier.height(8.dp))
                HeroSection {
                    query = it
                }
            }
        }
        stickyHeader(
            key = "sticky_header"
        ) {
            MenuBreakDown()
            Spacer(modifier = Modifier.height(16.dp))
        }

        if (result is Result.Success)
            items(
                items = result.data
                    .filter {
                        query.isBlank() ||
                                it.title.contains(other = query, ignoreCase = true)
                    },
                key = { item -> item.id }
            ) {
                MenuItem(item = it)
            }
        else item(
            key = "empty_screen"
        ) {
            EmptyScreen(result = result, onRetry)
        }
    }
}

@Composable
fun <T> EmptyScreen(result: Result<T>, onFailure: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (result is Result.Loading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(50.dp),
                color = AppTheme.color.primary1,
            )
        } else if (result is Result.Failure) {
            Text(
                text = stringResource(R.string.menu_item_loading_error_msg),
                style = AppTheme
                    .typography.leadText
            )
            Button(
                onClick = { onFailure() },
                modifier = Modifier
                    .padding(0.dp, 16.dp),
                colors = ButtonDefaults.filledTonalButtonColors(containerColor = AppTheme.color.primary2),
                shape = MaterialTheme.shapes.medium
            ) {
                Text(text = stringResource(R.string.menu_item_reload_btn_txt))
            }
        }
    }
}

@Composable
fun NavBar(navigateToProfile: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "little lemon logo",
            modifier = Modifier
                .padding(top = 16.dp, bottom = 16.dp, start = 50.dp)
                .height(50.dp)
                .weight(1F, true)
        )

        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "profile",
            modifier = Modifier
                .padding(vertical = 16.dp, horizontal = 16.dp)
                .size(50.dp)
                .clickable {
                    navigateToProfile()
                }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnsafeOptInUsageError")
@Composable
fun HeroSection(onSearch: (String) -> Unit) {
    var query by rememberSaveable { mutableStateOf("") }
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .background(color = AppTheme.color.primary1)
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.home_hero_section_title),
            style = AppTheme.typography.display,
            color = AppTheme.color.primary2,
        )
        Text(
            text = stringResource(R.string.home_hero_section_subtitle),
            style = AppTheme.typography.subTitle,
            color = AppTheme.color.highlight1,
            modifier = Modifier
                .offset(y = (-15).dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Text(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .weight(1f)
                    .width(0.dp)
                    .align(Alignment.CenterVertically),
                text = stringResource(R.string.home_hero_section_about),
                style = AppTheme.typography.highlight,
                color = AppTheme.color.highlight1
            )
            Image(
                modifier = Modifier
                    .width(100.dp)
                    .height(120.dp)
                    .clip(MaterialTheme.shapes.medium),
                painter = painterResource(id = R.drawable.hero_image),
                contentDescription = "hero image",
                contentScale = ContentScale.Crop,
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = query,
            onValueChange = {
                query = it
                if (it.isBlank())
                    onSearch(it)
            },
            placeholder = {
                Text(
                    text = stringResource(id = R.string.home_search_bar_hint),
                    style = AppTheme.typography.leadText,
                )
            },
            textStyle = AppTheme.typography.leadText,
            singleLine = true,
            leadingIcon = {
                Icon(
                    modifier = Modifier
                        .size(24.dp),
                    imageVector = Icons.Default.Search,
                    contentDescription = "",
                    tint = Color.Black
                )
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search,
            ),
            keyboardActions = KeyboardActions(
                onSearch = { onSearch(query) }
            ),
            shape = MaterialTheme.shapes.medium,
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                containerColor = Color.LightGray,
                cursorColor = AppTheme.color.primary1
            )
        )
    }
}

@Composable
fun MenuBreakDown() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(top = 16.dp)
    ) {
        Text(
            text = stringResource(R.string.home_menu_break_down_title),
            style = AppTheme.typography.sectionTitle,
            modifier = Modifier
                .padding(start = 16.dp)
        )
        Spacer(
            modifier = Modifier
                .height(8.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            CategoryItem(text = stringResource(R.string.starters_category_label))
            CategoryItem(text = stringResource(R.string.mains_category_label))
            CategoryItem(text = stringResource(R.string.desserts_category_label))
            CategoryItem(text = stringResource(R.string.slides_category_label))
        }
        Divider(
            modifier = Modifier
                .padding(top = 4.dp)
                .height(1.dp),
            color = AppTheme.color.primary1
        )
    }
}

@Composable
fun CategoryItem(text: String) {
    Text(
        text = text,
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 8.dp)
            .background(
                color = AppTheme.color.primary1.copy(alpha = 0.2F),
                shape = MaterialTheme.shapes.medium
            )
            .padding(8.dp),
        color = AppTheme.color.primary1,
        style = AppTheme.typography.sectionCategory
    )
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuItem(item: MenuItemLocal) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = item.title,
            style = AppTheme.typography.cardTitle,
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .width(0.dp)
            ) {
                Text(
                    text = item.description,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = AppTheme.typography.paragraph,
                    modifier = Modifier
                        .padding(top = 8.dp, end = 8.dp, bottom = 8.dp)
                )
                Text(
                    text = stringResource(id = R.string.menu_item_price, item.price),
                    style = AppTheme.typography.leadText
                )
            }
            GlideImage(
                model = item.image,
                contentDescription = "Food Image",
                modifier = Modifier
                    .size(80.dp)
                    .clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop
            )
        }
        Divider(
            modifier = Modifier
                .padding(vertical = 24.dp)
                .height(1.dp),
            color = Color.LightGray
        )
    }
}

@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    val item = MenuItemLocal(
        image = "",
        price = "12.99",
        description = "The famous greek salad of crispy lettuce, peppers, olives and our Chicago",
        title = "Greek Salad",
        category = "Starters"
    )

    HomeScreenUI(
        Result.Success(listOf(item)), {}
    ) {}
}