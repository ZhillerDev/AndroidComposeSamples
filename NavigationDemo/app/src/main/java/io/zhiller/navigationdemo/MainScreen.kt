package io.zhiller.navigationdemo

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import io.zhiller.navigationdemo.navigation.NavRoutes
import io.zhiller.navigationdemo.screen.DiscoveryScreen
import io.zhiller.navigationdemo.screen.HomeScreen
import io.zhiller.navigationdemo.screen.MeScreen
import kotlinx.coroutines.launch

@Composable
fun MainScreen(navController: NavController) {
  val pagerState = rememberPagerState(pageCount = { 3 }) // ViewPager2 的状态
  val coroutineScope = rememberCoroutineScope()
  
  // 当前选中的底部导航栏项
  var selectedItem by remember { mutableIntStateOf(0) }
  
  Scaffold(
    bottomBar = {
      NavigationBar {
        NavRoutes.bottomNavRoutes.forEachIndexed { index,item ->
          NavigationBarItem(
            icon = { Icon(item.icon, contentDescription = "Home") },
            label = { Text(item.name) },
            selected = selectedItem == index,
            onClick = {
              selectedItem = index
              coroutineScope.launch { pagerState.animateScrollToPage(index) }
            }
          )
        }
      }
    }
  ) { innerPadding ->
    // ViewPager2 实现滑动视图
    HorizontalPager(state = pagerState, userScrollEnabled = false) { page ->
      when (page) {
        0 -> HomeScreen(navController, Modifier.padding(innerPadding))
        1 -> DiscoveryScreen(navController, Modifier.padding(innerPadding))
        2 -> MeScreen(navController, Modifier.padding(innerPadding))
      }
    }
  }
}