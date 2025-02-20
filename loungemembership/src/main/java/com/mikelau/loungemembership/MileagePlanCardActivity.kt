package com.mikelau.loungemembership

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mikelau.loungemembership.models.Profile
import com.mikelau.loungemembership.utils.BodyLarge
import com.mikelau.loungemembership.utils.Headline
import com.mikelau.loungemembership.utils.OneWorldTier
import com.mikelau.loungemembership.utils.Title1
import com.mikelau.loungemembership.utils.Title2
import com.mikelau.loungemembership.viewmodels.ProfileViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MileagePlanCardActivity : AppCompatActivity() {

    private val profileViewModel: ProfileViewModel by viewModel()
    private var profile: Profile? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val token = intent.getStringExtra("token") ?: ""
        val userId = intent.getStringExtra("userId") ?: ""

        profileViewModel.getProfile(jwtToken = token, userId = userId)

        setContent {
            val profileState by profileViewModel.profile.collectAsState()

            if (profileState != null) {
                profile = profileState
                MileagePlanCardScreen(profile!!)
            } else {
                // Show a loading indicator or placeholder
                Text(text = "Loading...")
            }
        }
    }

    @Composable
    fun MileagePlanCardScreen(profile: Profile) {
        val textColor = ColorPrimary

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(ColorGrayLight)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .paint(
                        painterResource(R.drawable.digital_membership),
                        contentScale = ContentScale.FillBounds
                    )
            ) {
                Row {
                    IconButton(
                        onClick = {
                            finish()
                        }) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                    Spacer(Modifier.weight(1f))

                    Image(
                        painter = painterResource(getOneWorldBadge()),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(top = 4.dp, end = 32.dp)
                            .size(150.dp)
                    )
                }
            }
            Text(
                modifier = Modifier.padding(
                    horizontal = 24.dp
                ),
                text = profile.fullName!!,
                color = textColor,
                style = Headline,
            )
            SelectionContainer {
                Text(
                    modifier = Modifier.padding(
                        horizontal = 24.dp
                    ),
                    text = profile.mileagePlanNumber!!,
                    color = textColor,
                    style = Title1,
                )
            }
            if (!profile.enrollmentDate.isNullOrEmpty()) {
                Text(
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .padding(top = 4.dp),
                    // membership_card_member_since accepts a %s value which doesn't allow ints
                    text = String.format(
                        "Member since %s",
                        profile.enrollmentDate.takeLast(4)
                    ),
                    color = textColor,
                    style = BodyLarge,
                )
            }
            if (!profile.tierStatusExpirationDate.isNullOrEmpty()) {
                Text(
                    modifier = Modifier.padding(
                        horizontal = 24.dp
                    ),
                    text = String.format(
                        "Valid until %s",
                        profile.tierStatusExpirationDate
                    ),
                    color = textColor,
                    style = BodyLarge,
                )
            }

            Text(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .padding(horizontal = 24.dp),
                text = getAdditionalText(profile = profile),
                color = textColor,
                style = BodyLarge.copy(fontWeight = FontWeight(800))
            )
            Row(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .padding(top = 24.dp)
            ) {
                Column(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                ) {
                    if (getTierStatusText(profile = profile).isNotEmpty()) {
                        Text(
                            text = getTierStatusText(profile = profile),
                            color = getTextColorTier(profile = profile),
                            style = Title2.copy(fontWeight = FontWeight(1000))
                        )
                    }
                    Text(
                        text = getTierStatus2Text(profile = profile),
                        color = getTextColorTier(profile = profile),
                        style = Title2.copy(fontWeight = FontWeight(1000))
                    )
                }
                Spacer(Modifier.weight(1f))

                if (!profile.oneWorldTierStatus.isNullOrEmpty()) {
                    Image(
                        modifier = Modifier
                            .padding(
                                end = 4.dp
                            )
                            .align(Alignment.CenterVertically)
                            .size(80.dp),
                        painter = painterResource(getOneWorldTierBadge(profile = profile)),
                        contentDescription = null
                    )
                }
            }
            Spacer(Modifier.weight(0.25f))
            val resID = R.drawable.ic_add_google_wallet
            Image(
                painter = painterResource(resID),
                contentDescription = null,
                modifier = Modifier
                    .padding(vertical = 40.dp)
                    .padding(horizontal = 16.dp)
                    .align(Alignment.CenterHorizontally)
                    .width(130.dp)
            )
        }
    }

    @Composable
    fun getTextColorTier(profile: Profile): Color {
        return if (profile.tierStatus.equals(
                Standard,
                ignoreCase = true
            ) && profile.isClub49
        ) {
            ColorPrimary
        } else if (profile.tierStatus.equals(MVP, ignoreCase = true)) {
            ColorMvp
        } else {
            ColorMvpGold75k
        }
    }

    private fun getTierStatusText(profile: Profile): String {
        val tierStatus =
            if (profile.tierStatus.equals(Standard, ignoreCase = true)
                || profile.tierStatus.equals(MVP, ignoreCase = true)
                || (!profile.tierStatus.equals(Gold, ignoreCase = true)
                        && !profile.tierStatus.equals(Gold75K, ignoreCase = true)
                        && !profile.tierStatus.equals(Gold100K, ignoreCase = true)
                        && profile.isClub49)
            ) {
                //tierStatus1 is invisible for standard, club49 with no tier status, and MVP tiers
                ""
            } else {
                MVP
            }
        return tierStatus.uppercase()
    }

    @Composable
    fun getTierStatus2Text(profile: Profile): String {
        val tierStatus2 = when {
            profile.tierStatus?.lowercase() == Gold.lowercase() -> GoldDisplay
            profile.tierStatus?.lowercase() == Gold75K.lowercase() -> Gold75KDisplay
            profile.tierStatus?.lowercase() == Gold100K.lowercase() -> Gold100KDisplay
            profile.tierStatus?.lowercase() == MVP.lowercase() -> MVPDisplay
            profile.isClub49 -> Club49
            else -> ""
        }
        return tierStatus2.uppercase()
    }

    @Composable
    fun getAdditionalText(profile: Profile): String {
        return when {
            profile.isClub49 && profile.tierStatus != Standard -> Club49
            profile.isClub49 -> Club49
            profile.isMillionMiler -> MillionMiler
            else -> ""
        }
    }

    private fun getOneWorldBadge(): Int {
        return R.drawable.alaska_mileage_plan_oneworld_logo
    }

    private fun getOneWorldTierBadge(profile: Profile): Int {
        return when (OneWorldTier.entries
            .find { it.status == profile.oneWorldTierStatus?.lowercase() }) {
            OneWorldTier.RUBY -> OneWorldTier.RUBY.drawableResId
            OneWorldTier.SAPPHIRE -> OneWorldTier.SAPPHIRE.drawableResId
            else -> OneWorldTier.EMERALD.drawableResId
        }
    }

    companion object {
        // Tier Statuses
        const val Standard: String = "Standard"
        const val MVP: String = "MVP"
        const val Gold: String = "Gold"
        const val Gold75K: String = "Gold75K"
        const val Gold100K: String = "Gold100K"

        // Tiers
        const val Club49 = "Club 49"
        const val MillionMiler = "Million Miler"
        const val MVPDisplay = "MVP"
        const val GoldDisplay = "Gold"
        const val Gold75KDisplay = "Gold 75K"
        const val Gold100KDisplay = "Gold 100K"
    }

}