package com.slin.study.natively.module

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.slin.study.natively.widget.ScaffoldWithCsAppBar
import com.slin.study.natively.R
import com.slin.study.natively.module.binderExt.BinderTestScreen
import com.slin.study.natively.ui.theme.Size

/**
 * author: slin
 * date: 2021/3/11
 * description: 示例列表
 *
 */
data class SamplePage(
    val name: String,
    val icon: Int,
    val destination: String = name,
    val withActionBar: Boolean = true,
    val content: @Composable (Modifier) -> Unit
)


val samples = listOf(
    SamplePage("BinderTest", R.drawable.img_cartoon_1) { BinderTestScreen(it) },
)


@Composable
fun Samples(onClickSample: (SamplePage) -> Unit) {
    ScaffoldWithCsAppBar(
        isShowBack = false,
        title = stringResource(id = R.string.app_name),
        actions = { Text(text = "by slin") }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .navigationBarsPadding()
                .padding(innerPadding)
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(horizontal = 10.dp, vertical = 16.dp)
            ) {
                samples.forEach { sample ->
                    item { SampleItem(sample, onClickSample = onClickSample) }
                }
            }
        }
    }
}

@Composable
fun SampleItem(samplePage: SamplePage, onClickSample: (SamplePage) -> Unit) {
    Card(
        modifier = Modifier
            .padding(horizontal = Size.mini, vertical = Size.mini)
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable {
                onClickSample(samplePage)
            },
        shape = RoundedCornerShape(Size.medium),
        elevation = CardDefaults.cardElevation()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(vertical = Size.small),
        ) {
            Image(
                painter = painterResource(id = samplePage.icon),
                contentDescription = samplePage.name,
            )
            Text(
                text = samplePage.name,
                modifier = Modifier
                    .padding(top = 10.dp)
            )
        }
    }
}


@Preview
@Composable
fun SampleItemPreview() {
    SampleItem(samplePage = samples[0]) {
    }
}


@Preview
@Composable
fun SamplePreview() {
    Samples {
    }
}
