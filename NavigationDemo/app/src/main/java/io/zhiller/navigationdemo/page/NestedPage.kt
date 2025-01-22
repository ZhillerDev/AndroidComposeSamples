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
import io.zhiller.navigationdemo.navigation.NestedPageRoutes

@Composable
fun NestedPageHome(navController: NavController) {
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
      Button(
        onClick = {
          NavActions(navController).navToPage(NestedPageRoutes.One.route)
        }
      ) {
        Text("forward to basic_page_1")
      }
    }
  }
}

@Composable
fun NestedPageOne(navController: NavController) {
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
          NavActions(navController).navToPage(NestedPageRoutes.Two.route)
        }
      ) {
        Text("forward to basic_page_2")
      }
    }
  }
}

@Composable
fun NestedPageTwo(navController: NavController) {
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
          NavActions(navController).navToPage(NestedPageRoutes.Home.route)
        }
      ) {
        Text("back to basic_page")
      }
    }
  }
}