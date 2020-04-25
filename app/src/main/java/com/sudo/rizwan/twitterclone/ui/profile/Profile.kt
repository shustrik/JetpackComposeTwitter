package com.sudo.rizwan.twitterclone.ui.profile

import androidx.compose.Composable
import androidx.ui.core.*
import androidx.ui.foundation.*
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.material.Button
import androidx.ui.material.IconButton
import androidx.ui.material.icons.Icons
import androidx.ui.material.icons.filled.ArrowBack
import androidx.ui.material.icons.filled.MoreVert
import androidx.ui.res.imageResource
import androidx.ui.unit.dp
import com.sudo.rizwan.twitterclone.R
import com.sudo.rizwan.twitterclone.models.User
import com.sudo.rizwan.twitterclone.state.AppState
import com.sudo.rizwan.twitterclone.state.Screen
import com.sudo.rizwan.twitterclone.state.navigateTo
import com.sudo.rizwan.twitterclone.state.tweets
import com.sudo.rizwan.twitterclone.ui.common.CustomDivider
import com.sudo.rizwan.twitterclone.ui.common.TweetLayout
import com.sudo.rizwan.twitterclone.ui.common.UserInfo

@Composable
fun Profile(user: User) {
    val scrollerPosition = ScrollerPosition()
    VerticalScroller(scrollerPosition = scrollerPosition) {
        ConstraintLayout(constraintSet = ConstraintSet {
            val banner = tag("banner")
            val closeButton = tag("close")
            val moreButton = tag("more")
            val avatar = tag("avatar")
            val content = tag("content")
            val followButton = tag("follow")
            avatar.top constrainTo banner.bottom
            avatar.bottom constrainTo banner.bottom
            avatar.left constrainTo parent.left
            avatar.left.margin = 16.dp
            followButton.top constrainTo banner.bottom
            followButton.right constrainTo parent.right
            followButton.right.margin = 16.dp
            followButton.top.margin = 16.dp
            closeButton.top constrainTo parent.top
            closeButton.left constrainTo parent.left
            moreButton.top constrainTo parent.top
            moreButton.right constrainTo parent.right
            content.top constrainTo banner.bottom
        }) {
            Row(
                modifier = Modifier.preferredHeight(54.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalGravity = Alignment.CenterVertically
            ) {
                Image(
                    imageResource(R.drawable.ic_dm),
                    modifier = Modifier
                        .preferredHeight(30.dp)
                )
                Image(
                    imageResource(R.drawable.ic_notifications),
                    modifier = Modifier
                        .preferredHeight(30.dp)
                )
            }
            Image(
                imageResource(R.drawable.profile_banner),
                modifier = Modifier
                    .preferredHeight(180.dp)
                    .fillMaxWidth()
                    .tag("banner"),
                contentScale = ContentScale.Crop
            )
            Button(
                modifier = Modifier.tag("follow"),
                onClick = {
                },
                shape = RoundedCornerShape(20.dp),
                backgroundColor = AppState.theme.surface,
                border = Border(1.dp, AppState.theme.primary)
            ) {
                Text(text = "Follow", color = AppState.theme.primary)
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    modifier = Modifier.tag("close"),
                    onClick = { navigateTo(Screen.Home) }) {
                    Icon(asset = Icons.Filled.ArrowBack, tint = Color.White)
                }

                IconButton(modifier = Modifier.tag("more"), onClick = {}) {
                    Icon(asset = Icons.Filled.MoreVert, tint = Color.White)
                }
            }
            Image(
                imageResource(user.avatar),
                modifier = Modifier
                    .preferredSize(80.dp)
                    .clip(shape = RoundedCornerShape(40.dp))
                    .tag("avatar")
                    .drawBorder(
                        border = Border(size = 3.dp, color = AppState.theme.surface),
                        shape = RoundedCornerShape(40.dp)
                    ),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.tag("content")) {
                Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
                    Spacer(modifier = Modifier.preferredHeight(44.dp))
                    UserInfo(
                        user = user,
                        showBio = true
                    )
                    Spacer(modifier = Modifier.preferredHeight(16.dp))
                }
                CustomDivider()
                tweets.filter { it.user == user }.forEach { tweet ->
                    TweetLayout(tweet)
                    CustomDivider()
                }
            }
        }
    }
}
