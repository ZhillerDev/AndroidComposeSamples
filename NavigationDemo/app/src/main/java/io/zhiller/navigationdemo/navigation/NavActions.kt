package io.zhiller.navigationdemo.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination

class NavActions(private val navController: NavController) {
  /**
   * 导航到指定页面
   *
   * 此函数通过传入一个目的地字符串来实现页面跳转它不仅导航到目的地，还管理导航栈的状态，
   * 确保用户体验的一致性和流畅性
   *
   * @param destination 目的地页面的唯一标识符，通常是一个字符串资源
   */
  fun navToPage(destination: String) {
    // 导航到目的地，并配置导航选项
    navController.navigate(destination) {
      // 设置为单次顶层启动，避免多次点击导致多个实例
      launchSingleTop = true
      // 恢复状态，确保用户界面的一致性
      restoreState = true
    }
  }
  
  fun navToPagePopUp(destination: String) {
    // 导航到目的地，并配置导航选项
    navController.navigate(destination) {
      // 配置弹出到的条件，确保从起始目的地开始保存状态
      popUpTo(navController.graph.findStartDestination().id) {
        saveState = true
      }
      // 设置为单次顶层启动，避免多次点击导致多个实例
      launchSingleTop = true
      // 恢复状态，确保用户界面的一致性
      restoreState = true
    }
  }
  
  fun navToMain() = navToPagePopUp(NavRoutes.INIT_NAV_ROUTE)
  
  fun back() {
    navController.popBackStack()
  }
}