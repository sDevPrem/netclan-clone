package com.sdevprem.netclanclone.ui.onboarding

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sdevprem.netclanclone.R
import com.sdevprem.netclanclone.ui.navigation.Destination

@OptIn(ExperimentalFoundationApi::class, ExperimentalAnimationApi::class)
@Composable
fun OnBoardingScreen(
    navController: NavController
) {
    val pageList = OnBoardingPagerContent.OnBoardingPagerContentList
    val pagerState = rememberPagerState()
    val backgroundColor by animateColorAsState(targetValue = pageList[pagerState.currentPage].backGroundColor)
    val onBackgroundColor by animateColorAsState(targetValue = pageList[pagerState.currentPage].onBackgroundColor)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    listOf(
                        backgroundColor,
                        MaterialTheme.colorScheme.surface
                    ),
                )
            )
            .padding(16.dp)
    ) {
        Text(
            text = "Skip",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .padding(horizontal = 8.dp)
                .clickable {
                    Destination.OnBoarding.navigateToProfileEditScreen(navController)
                },
            textAlign = TextAlign.End,
            color = onBackgroundColor
        )
        HorizontalPager(
            pageCount = pageList.size,
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            PagerContent(
                title = pageList[it].title,
                icon = pageList[it].icon,
                heroImage = pageList[it].heroImage,
                subTitle = pageList[it].subTitle,
                body = pageList[it].body
            )
        }
        Spacer(modifier = Modifier.size(8.dp))
        PagerIndicators(totalPages = pageList.size, selectedPage = pagerState.currentPage)
        Spacer(modifier = Modifier.height(16.dp))
        AnimatedVisibility(
            visible = pagerState.settledPage == pageList.size - 1,
            enter = scaleIn() + expandVertically(),
            exit = scaleOut() + shrinkVertically()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Button(onClick = {
                    Destination.OnBoarding.navigateToProfileEditScreen(navController)
                }) {
                    Text(
                        text = "Get Started"
                    )
                }
            }
        }
    }
}

@Composable
private fun PagerIndicators(
    totalPages: Int,
    selectedPage: Int
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(totalPages) {
            Indicator(isSelected = it == selectedPage)
        }
    }
}

@Composable
private fun Indicator(
    isSelected: Boolean
) {
    val width by animateDpAsState(targetValue = if (isSelected) 24.dp else 10.dp)
    Box(
        modifier = Modifier
            .padding(2.dp)
            .height(10.dp)
            .width(width = width)
            .clip(CircleShape)
            .background(color = MaterialTheme.colorScheme.primary)
    )
}

@Composable
private fun PagerContent(
    modifier: Modifier = Modifier,
    title: String,
    @DrawableRes icon: Int,
    @DrawableRes heroImage: Int,
    subTitle: String,
    body: String
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PagerHeader(
            imageVector = ImageVector.vectorResource(id = icon),
            title = title,
            modifier = Modifier.padding(horizontal = 32.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = heroImage),
            contentDescription = null,
            modifier = Modifier
                .weight(1f)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = subTitle,
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier
                .padding(horizontal = 16.dp),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = body,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.primary
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun PagerHeader(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    title: String
) {
    val elevation = remember { 16.dp }
    Box(modifier = modifier) {
        Text(
            text = title,
            modifier = Modifier
                .fillMaxWidth()
                .shadow(elevation = elevation, shape = MaterialTheme.shapes.extraLarge)
                .background(
                    color = MaterialTheme.colorScheme.surface,
                )
                .padding(vertical = 4.dp, horizontal = 24.dp)
                .align(Alignment.CenterEnd),
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Medium
            ),
            color = MaterialTheme.colorScheme.primary
        )
        Icon(
            imageVector = imageVector,
            contentDescription = null,
            modifier = Modifier
                .size(64.dp)
                .shadow(elevation = elevation, shape = CircleShape, clip = true)
                .background(
                    color = MaterialTheme.colorScheme.surface,
                )
                .padding(12.dp)
                .align(Alignment.CenterStart),
            tint = MaterialTheme.colorScheme.primary
        )
    }

}

@Composable
@Preview(showBackground = true)
private fun PagerContentPreview() {
    PagerContent(
        modifier = Modifier,
        title = "Pesonal",
        icon = R.drawable.ic_people,
        heroImage = R.drawable.onboarding_social_image,
        subTitle = "Explore the community nearby",
        body = "Friends, companion, hobby partners, professional services, merchants, deals, rides, dates, soulmates, jobs, used goods"
    )
}

