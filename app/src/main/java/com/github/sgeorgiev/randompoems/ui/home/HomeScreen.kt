package com.github.sgeorgiev.randompoems.ui.home

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.graphics.ColorUtils
import com.github.sgeorgiev.randompoems.R
import com.github.sgeorgiev.randompoems.data.model.PoemDataModel
import com.github.sgeorgiev.randompoems.ui.model.PoemUIModel
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    state: DataState<List<PoemUIModel>>,
    scaffoldState: ScaffoldState
) {
    Scaffold(
        scaffoldState = scaffoldState
    ) {
        when (state) {
            is DataState.Error -> {
                OnError(scaffoldState, state)
            }
            is DataState.Success<List<PoemUIModel>> -> {
                Column {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        item {
                            Text(
                                text = "Random poems",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 20.dp),
                                style = MaterialTheme.typography.h4
                                    .copy(
                                        textAlign = TextAlign.Center
                                    ),
                                color = MaterialTheme.colors.onSurface,
                            )
                        }
                        items(state.data) { poem ->
                            PoemCard(poem = poem)
                        }
                    }
                }
            }
            is DataState.Idle -> {
            }
            is DataState.Loading -> {
                CircularProgressIndicator()
            }
        }
    }
}

@Composable
private fun PoemCard(
    poem: PoemUIModel,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 10.dp,
    cutCornerSize: Dp = 30.dp,
) {
    Box(
        modifier = modifier
            .padding(
                start = 20.dp,
                end = 20.dp,
                bottom = 20.dp
            )
    ) {
        Canvas(modifier = Modifier.matchParentSize()) {
            val clipPath = Path().apply {
                lineTo(size.width - cutCornerSize.toPx(), 0f)
                lineTo(size.width, cutCornerSize.toPx())
                lineTo(size.width, size.height)
                lineTo(0f, size.height)
                close()
            }

            clipPath(clipPath) {
                val color = Color.Yellow.copy(alpha = 0.1f)
                drawRoundRect(
                    color = color,
                    size = size,
                    cornerRadius = CornerRadius(cornerRadius.toPx())
                )
                drawRoundRect(
                    color = Color(
                        ColorUtils.blendARGB(color.toArgb(), 0x000000, 0.2f)
                    ),
                    topLeft = Offset(size.width - cutCornerSize.toPx(), -100f),
                    size = Size(cutCornerSize.toPx() + 100f, cutCornerSize.toPx() + 100f),
                    cornerRadius = CornerRadius(cornerRadius.toPx())
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(end = 32.dp)
        ) {
            Text(
                text = poem.title,
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.onSurface,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = poem.content,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onSurface,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
private fun OnError(
    scaffoldState: ScaffoldState,
    state: DataState.Error<List<PoemUIModel>>
) {
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(true) {
        coroutineScope.launch {
            scaffoldState.snackbarHostState.showSnackbar(
                message = state.msg
            )
        }
    }
}