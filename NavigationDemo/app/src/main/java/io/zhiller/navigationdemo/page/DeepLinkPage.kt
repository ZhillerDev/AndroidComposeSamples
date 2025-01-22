package io.zhiller.navigationdemo.page

import android.os.Build
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import io.zhiller.navigationdemo.navigation.NavActions
import io.zhiller.navigationdemo.utils.IntentUtils

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun DeepLinkHomePage(navController: NavController) {
  
  val context = LocalContext.current
  // 请求通知权限
  val notificationPermissionState = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
    rememberPermissionState(
      android.Manifest.permission.POST_NOTIFICATIONS
    )
  } else {
    TODO("VERSION.SDK_INT < TIRAMISU")
  }
  
  Surface(
    modifier = Modifier.fillMaxSize()
  ) {
    Column(
      modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Button(onClick = {
        IntentUtils.openWebsite(
          context = context, url = "navigationdemo://deep_link_page_url/helloworld"
        )
      }) {
        Text("open website and redirect")
      }
      Button(onClick = {
        if (notificationPermissionState.status.isGranted) {
          IntentUtils.createNotification(
            context = context, str = "navigationdemo://deep_link_page_notification/helloworld"
          )
        } else {
          notificationPermissionState.launchPermissionRequest()
        }
      }) {
        Text("run notification and redirect")
      }
    }
  }
}

@Composable
fun DeepLinkUrlPage(navController: NavController, string: String) {
  Surface(
    modifier = Modifier.fillMaxSize()
  ) {
    Column(
      modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Text(text = string)
      Button(onClick = {
        NavActions(navController).navToMain()
      }) {
        Text("back to main")
      }
    }
  }
}

@Composable
fun DeepLinkNotificationPage(navController: NavController, string: String) {
  Surface(
    modifier = Modifier.fillMaxSize()
  ) {
    Column(
      modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Text(text = string)
      Button(onClick = {
        NavActions(navController).navToMain()
      }) {
        Text("back to main")
      }
    }
  }
}