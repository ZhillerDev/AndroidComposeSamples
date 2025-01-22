package io.zhiller.navigationdemo.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import io.zhiller.navigationdemo.navigation.NavActions
import io.zhiller.navigationdemo.navigation.ParamPageRoutes
import io.zhiller.navigationdemo.utils.ScreenUtils

@Composable
fun ParamHomePage(navController: NavController) {
  Surface(
    modifier = Modifier.fillMaxSize()
  ) {
    Column(
      modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      val str = remember { mutableStateOf("") }
      val content = LocalContext.current
      val textFieldWidth = ScreenUtils.getScreenWidthDp(content).times(0.6f)
      
      Text("ParamPage")
      TextField(modifier = Modifier.width(textFieldWidth), value = str.value, onValueChange = {
        str.value = it
      })
      Button(onClick = {
        NavActions(navController).navToPage(ParamPageRoutes.Link.addParam(str.value))
      }) {
        Text("ParamPageLink")
      }
    }
  }
}

@Composable
fun ParamLinkPage(navController: NavController, str: String) {
  Surface(
    modifier = Modifier.fillMaxSize()
  ) {
    Column(
      modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Text(str)
      Button(onClick = {
        NavActions(navController).back()
      }) {
        Text("go back")
      }
    }
  }
}