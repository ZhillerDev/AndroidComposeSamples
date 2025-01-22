package io.zhiller.navigationdemo.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import io.zhiller.navigationdemo.navigation.DeepLinkPageRoutes
import io.zhiller.navigationdemo.navigation.JsonParamPageRoutes
import io.zhiller.navigationdemo.navigation.NavActions
import io.zhiller.navigationdemo.navigation.NavAnimatePageRoutes
import io.zhiller.navigationdemo.navigation.NestedPageRoutes
import io.zhiller.navigationdemo.navigation.ParamPageRoutes

@Composable
fun HomeScreen(navController: NavController, modifier: Modifier = Modifier) {
  Surface(modifier = modifier.fillMaxSize()) {
    LazyColumn {
      item {
        Text(
          text = "Navigation Demos",
          style = MaterialTheme.typography.headlineMedium,
          modifier = Modifier.padding(16.dp)
        )
      }
      
      // 嵌套路由实例
      item {
        Card(modifier = Modifier
          .clickable {
            NavActions(navController).navToPage(NestedPageRoutes.Home.route)
          }
          .fillMaxWidth()
          .padding(horizontal = 16.dp, vertical = 8.dp)) {
          Text(text = "Nested Navigation Basic", modifier = Modifier.padding(16.dp))
        }
      }
      
      // 路由切换动画实例
      item {
        Card(modifier = Modifier
          .clickable {
            NavActions(navController).navToPage(NavAnimatePageRoutes.Faded.route)
          }
          .fillMaxWidth()
          .padding(horizontal = 16.dp, vertical = 8.dp)) {
          Text(text = "Page Change Faded Animate", modifier = Modifier.padding(16.dp))
        }
      }
      item {
        Card(modifier = Modifier
          .clickable {
            NavActions(navController).navToPage(NavAnimatePageRoutes.Scaled.route)
          }
          .fillMaxWidth()
          .padding(horizontal = 16.dp, vertical = 8.dp)) {
          Text(text = "Page Change Scale Animate", modifier = Modifier.padding(16.dp))
        }
      }
      
      // 参数路由实例
      item {
        Card(modifier = Modifier
          .clickable {
            NavActions(navController).navToPage(ParamPageRoutes.Home.route)
          }
          .fillMaxWidth()
          .padding(horizontal = 16.dp, vertical = 8.dp)) {
          Text(text = "Param Route", modifier = Modifier.padding(16.dp))
        }
      }
      
      // JSON 参数路由实例
      item {
        Card(modifier = Modifier
          .clickable {
            NavActions(navController).navToPage(JsonParamPageRoutes.Home.route)
          }
          .fillMaxWidth()
          .padding(horizontal = 16.dp, vertical = 8.dp)) {
          Text(text = "Json Param Route", modifier = Modifier.padding(16.dp))
        }
      }
      
      // deeplink 参数路由实例
      item {
        Card(modifier = Modifier
          .clickable {
            NavActions(navController).navToPage(DeepLinkPageRoutes.Home.route)
          }
          .fillMaxWidth()
          .padding(horizontal = 16.dp, vertical = 8.dp)) {
          Text(text = "Deeplink Samples", modifier = Modifier.padding(16.dp))
        }
      }
    }
  }
}