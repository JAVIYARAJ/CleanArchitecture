package com.example.newsapp.presentation.onBoarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.newsapp.presentation.common.NewsButton
import com.example.newsapp.presentation.common.NewsTextButton
import com.example.newsapp.presentation.onBoarding.components.OnBoardingAppEvent
import com.example.newsapp.presentation.onBoarding.components.OnBoardingPage
import com.example.newsapp.presentation.onBoarding.components.PageIndicator
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(event: (OnBoardingAppEvent) -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {

        val pageState = rememberPagerState(initialPage = 0) {
            Pages.size
        }
        val buttonState = remember {
            derivedStateOf {
                when (pageState.currentPage) {
                    0 -> listOf("", "Next")
                    1 -> listOf("Back", "Next")
                    2 -> listOf("Back", "Get Started")
                    else -> listOf("", "")
                }
            }
        }

        HorizontalPager(state = pageState) {
            OnBoardingPage(page = Pages[it])
        }
        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .navigationBarsPadding(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            PageIndicator(pageSize = Pages.size, selectedPageIndex = pageState.currentPage)

            Row(verticalAlignment = Alignment.CenterVertically) {
                val scope = rememberCoroutineScope()
                if (buttonState.value[0].isNotEmpty()) {
                    NewsTextButton(title = buttonState.value[0]) {
                        scope.launch {
                            pageState.animateScrollToPage(pageState.currentPage - 1)
                        }
                    }
                }
                NewsButton(title = buttonState.value[1]) {
                    if (pageState.currentPage == 2) {
                        event.invoke(OnBoardingAppEvent.SaveAppEvent)
                    } else {
                        scope.launch {
                            pageState.animateScrollToPage(pageState.currentPage + 1)

                        }
                    }
                }

            }
        }
    }
}