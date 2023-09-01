package com.kaizenplus.deloittecodechallenge.ui.screen.dashboard


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.kaizenplus.deloittecodechallenge.ui.theme.DeloitteCodeChallengeTheme
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.minimumInteractiveComponentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

class DashboardActivity : ComponentActivity() {
    @OptIn(ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DeloitteCodeChallengeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PagingScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PagingScreen() {
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState()

    val tabRowItems = listOf(//List of tabs to use later
        TabItem(
            text = "Dashboard",
            screen = { SearchScreen() }
        ),//First TabItem
        TabItem(
            text = "More",
            screen = { More() }
        )
    )

    Column(modifier = Modifier.fillMaxSize()) {
        TabRow(
            backgroundColor = Color.Transparent.copy(0f),//To separate it from background
            modifier = Modifier
                .padding(vertical = 4.dp, horizontal = 8.dp),
            selectedTabIndex = pagerState.currentPage
        ) {
            tabRowItems.forEachIndexed { index, item ->
                Tab(
                    text = { Text(text = item.text) },
//                    icon = { Icon(imageVector = item.icon,"") },
                    selected = pagerState.currentPage == index,
                    onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } }
                )
            }
        }
        HorizontalPager(
            count = tabRowItems.size,
            state = pagerState,
            userScrollEnabled = false
        ) {
            tabRowItems[pagerState.currentPage].screen()
        }
    }
}

@Composable
fun More() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row {
            Surface(
                color = Color.Transparent,
                modifier = Modifier.clip(MaterialTheme.shapes.medium)
            ) {
                Row {
                    Column {
                        AsyncImage(
                            model = "https://images.unsplash.com/photo-1535468850893-d6e543fbd7f5?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1740&q=80",
                            modifier = Modifier
                                .size(48.dp)
                                .clip(RectangleShape),
                            contentScale = ContentScale.Crop,
                            contentDescription = "Chat photo",
                        )
                    }
                    Column {
                        Text("Hazem Afaneh")
                    }
                }

            }

        }
        Row {
            Text("Details")
        }
        Spacer(Modifier.height(10.dp))
        Row {
            Text("National ID")
        }
        Row {
            Text("123456")
        }
        Spacer(Modifier.height(4.dp))
        Row {
            Text("Phone Number")
        }
        Row {
            Text("123344")
        }
        Spacer(Modifier.height(4.dp))
        Row {
            Text("Date of birth")
        }
        Row {
            Text("30/11/1998")
        }

    }
}

@Composable
fun SearchScreen() {
    var searchQuery by remember { mutableStateOf("Any") }

    fun onSearch() {

    }

    @Composable
    fun SearchBar(
        modifier: Modifier = Modifier,
        searchQuery: String,
        onSearchQueryChange: (String) -> Unit,
        onImeSearch: () -> Unit,
    ) {
        Box(modifier = modifier.padding(horizontal = 16.dp, vertical = 10.dp)) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                        focusedContainerColor = MaterialTheme.colorScheme.surface,
                    ),
                    shape = RoundedCornerShape(100),
                    placeholder = { Text("Search...") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Rounded.Search,
                            contentDescription = null,
                            modifier = Modifier.minimumInteractiveComponentSize(),
                            tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.66f)
                        )
                    },
                    modifier = Modifier
                        .weight(1f)
                        .minimumInteractiveComponentSize(),
                    value = searchQuery,
                    singleLine = true,
                    keyboardActions = KeyboardActions(onSearch = { onImeSearch() }),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Search
                    ),
                    onValueChange = onSearchQueryChange,
                    trailingIcon = {
                        AnimatedVisibility(
                            visible = searchQuery.isNotBlank(),
                            enter = fadeIn(),
                            exit = fadeOut()
                        ) {
                            IconButton(onClick = { onSearchQueryChange("") }) {
                                Icon(
                                    imageVector = Icons.Rounded.Clear,
                                    contentDescription = "Clear",
                                    tint = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                    }
                )
            }
        }
    }

    @Composable
    fun MessageItem(
        onClick: () -> Unit,
        recipient: String,
        snippet: String,
        recipientHighlight: String,
        timestamp: String,
        image: String
    ) {
        Surface(
            onClick = onClick,
            color = Color.Transparent,
            modifier = Modifier.clip(MaterialTheme.shapes.medium)
        ) {
            ListItem(
                colors = ListItemDefaults.colors(
                    containerColor = Color.Transparent,
                    overlineColor = Color.Transparent
                ),
                headlineContent = {
                    val recipientString = buildAnnotatedString {
                        append(recipient)
                        val start = recipient.lowercase().indexOf(recipientHighlight.lowercase())
                        if (start != -1) {
                            val end = start + recipientHighlight.length
                            addStyle(
                                style = SpanStyle(fontWeight = FontWeight.ExtraBold),
                                start = start,
                                end = end
                            )
                        }
                    }
                    Text(recipientString, maxLines = 1, overflow = TextOverflow.Ellipsis)
                },
                supportingContent = {
                    Text(snippet)
                },
                trailingContent = { Text(timestamp) },
                leadingContent = {
                    AsyncImage(
                        model = image,
                        modifier = Modifier
                            .size(48.dp)
                            .clip(RectangleShape),
                        contentScale = ContentScale.Crop,
                        contentDescription = "Chat photo",
                    )
                }
            )
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        Scaffold(
            modifier = Modifier.widthIn(max = 600.dp),
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface,
        ) { padding ->
            Column {
                Row(modifier = Modifier.padding(top = 10.dp, start = 24.dp)) {
                    Text(
                        text = "Data",
                        style = MaterialTheme.typography.titleLarge
                    )
                }
                Row {
                    SearchBar(
                        modifier = Modifier.fillMaxWidth(),
                        searchQuery = searchQuery,
                        onSearchQueryChange = { searchQuery = it },
                        onImeSearch = { onSearch() }
                    )
                }
                Row {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
                        Box(modifier = Modifier.padding(padding)) {
                            LazyColumn {

                                item {
                                    MessageItem(
                                        onClick = { /*TODO */ },
                                        recipient = "Emily Green",
                                        image = "https://images.unsplash.com/photo-1544005313-94ddf0286df2?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=688&q=80",
                                        snippet = "Any plans for the weekend?",
                                        recipientHighlight = searchQuery,
                                        timestamp = "1m"
                                    )
                                }
                                item {
                                    MessageItem(
                                        onClick = { /*TODO */ },
                                        recipient = "Stew Wonder",
                                        image = "https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=774&q=80",
                                        snippet = "Sounds like a plan 👌",
                                        recipientHighlight = searchQuery,
                                        timestamp = "12m"
                                    )
                                }
                                item {
                                    MessageItem(
                                        onClick = { /*TODO */ },
                                        recipient = "Mark Thompson",
                                        image = "https://images.unsplash.com/photo-1552374196-c4e7ffc6e126?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=774&q=80",
                                        snippet = " It's part of my 'cooking plans'",
                                        recipientHighlight = searchQuery,
                                        timestamp = "Fri"
                                    )
                                }
                                item {
                                    MessageItem(
                                        onClick = { /*TODO */ },
                                        recipient = "Stella Spanakopita",
                                        image = "https://images.unsplash.com/photo-1535468850893-d6e543fbd7f5?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1740&q=80",
                                        snippet = "Plan it. I'll be there!",
                                        recipientHighlight = searchQuery,
                                        timestamp = "Sat"
                                    )
                                }
                            }
                        }
                    }
                }
            }

        }
    }
}
data class TabItem(
    val text: String,//Tab Title
    val screen: @Composable ()->Unit//Tab Screen(can also take params)
)
