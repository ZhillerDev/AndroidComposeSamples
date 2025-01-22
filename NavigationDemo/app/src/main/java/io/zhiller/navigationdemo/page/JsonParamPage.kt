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
import io.zhiller.navigationdemo.navigation.JsonParamPageRoutes
import io.zhiller.navigationdemo.navigation.NavActions
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Composable
fun JsonParamHomePage(navController: NavController) {
  Surface(
    modifier = Modifier.fillMaxSize()
  ) {
    Column(
      modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      val user = JsonParamPageRoutes.User(
        id = 1, name = "zhiller", email = "zhiller@zhiller.io"
      )
      Text("${user.id} \n ${user.name} \n ${user.email}")
      Text("Json: ${Json.encodeToString(user)}")
      Button(onClick = {
        NavActions(navController).navToPage(
          destination = JsonParamPageRoutes.Detail.addJson(user)
        )
      }) {
        Text("go to detail")
      }
    }
  }
}

@Composable
fun JsonParamDetailPage(navController: NavController, user: JsonParamPageRoutes.User) {
  Surface(
    modifier = Modifier.fillMaxSize()
  ) {
    Column(
      modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Text("${user.id} \n ${user.name} \n ${user.email}")
      Button(onClick = {
        NavActions(navController).back()
      }) {
        Text("back")
      }
    }
  }
}