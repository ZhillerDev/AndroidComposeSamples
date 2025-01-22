package io.zhiller.navigationdemo.page

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
import androidx.navigation.NavController
import io.zhiller.navigationdemo.navigation.NavActions

@Composable
fun NavScaleAnimatePage(navController: NavController) {
  Surface(
    modifier = Modifier.fillMaxSize()
  ) {
    Column(
      modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Button(
        onClick = {
          NavActions(navController).back()
        }
      ) {
        Text("back to home")
      }
    }
  }
}

@Composable
fun NavFadedAnimatePage(navController: NavController) {
  Surface(
    modifier = Modifier.fillMaxSize()
  ) {
    Column(
      modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      Button(
        onClick = {
          NavActions(navController).back()
        }
      ) {
        Text("back to home")
      }
    }
  }
}