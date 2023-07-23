package com.slin.study.natively.widget

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.slin.study.natively.R


/**
 * author: slin
 * date: 2021/3/12
 * description:
 *
 */

@Preview
@Composable
fun CsAppBar(
    isShowBack: Boolean = false,
    title: String = stringResource(id = R.string.app_name),
    actions: @Composable RowScope.() -> Unit = {},
) {
    val colors = MaterialTheme.colorScheme
    val backPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            if (isShowBack) {
                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_arrow_back_24),
                    contentDescription = "back",
                    modifier = Modifier.clickable {
                        backPressedDispatcher?.onBackPressed()
                    }
                )
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding(),
        actions = actions,
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = colors.primary,
            scrolledContainerColor = colors.primary,
            navigationIconContentColor = colors.onPrimary,
            titleContentColor = colors.onPrimary,
            actionIconContentColor = colors.onPrimary
        )
    )
}

@Composable
fun ScaffoldWithCsAppBar(
    title: String,
    isShowBack: Boolean = true,
    actions: @Composable (RowScope.() -> Unit) = {},
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = { CsAppBar(isShowBack = isShowBack, title = title, actions = actions) },
        content = content
    )
}


