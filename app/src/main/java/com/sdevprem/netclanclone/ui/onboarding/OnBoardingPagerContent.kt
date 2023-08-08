package com.sdevprem.netclanclone.ui.onboarding

import androidx.annotation.DrawableRes
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.sdevprem.netclanclone.R

data class OnBoardingPagerContent(
    val title: String,
    @DrawableRes val icon: Int,
    @DrawableRes val heroImage: Int,
    val subTitle: String,
    val body: String,
    val backGroundColor: Color,
    val onBackgroundColor: Color
) {
    companion object {
        val OnBoardingPagerContentList: List<OnBoardingPagerContent>
            @Composable
            get() = listOf(
                OnBoardingPagerContent(
                    title = "Personal",
                    icon = R.drawable.ic_people,
                    heroImage = R.drawable.onboarding_social_image,
                    subTitle = "Explore the community nearby",
                    body = "Fiends, companion, hobby partners, professionals service, merchants, deals, rides, dates, soulmates, jobs, used goods",
                    backGroundColor = MaterialTheme.colorScheme.tertiaryContainer,
                    onBackgroundColor = MaterialTheme.colorScheme.onTertiaryContainer
                ),
                OnBoardingPagerContent(
                    title = "Business",
                    icon = R.drawable.ic_people,
                    heroImage = R.drawable.onboarding_business_image,
                    subTitle = "Let the Netclan Community explore your profile, services and offerings",
                    body = "Bet platform to advertise your skills and offerings through professional page of the app while growing with Netclan Community",
                    backGroundColor = MaterialTheme.colorScheme.primary,
                    onBackgroundColor = MaterialTheme.colorScheme.onPrimary
                ),

                OnBoardingPagerContent(
                    title = "Merchant",
                    icon = R.drawable.ic_people,
                    heroImage = R.drawable.onboarding_merchant_image,
                    subTitle = "Let the Netclan community find you know your deals",
                    body = "Publish your profile and deals over the app and get a huge traction. Best way to grow your business with Netclan Community",
                    backGroundColor = MaterialTheme.colorScheme.surface,
                    onBackgroundColor = MaterialTheme.colorScheme.primary
                ),
            )
    }
}