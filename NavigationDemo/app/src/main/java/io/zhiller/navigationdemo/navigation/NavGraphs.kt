package io.zhiller.navigationdemo.navigation

import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import androidx.navigation.navigation
import io.zhiller.navigationdemo.MainScreen
import io.zhiller.navigationdemo.page.DeepLinkHomePage
import io.zhiller.navigationdemo.page.DeepLinkNotificationPage
import io.zhiller.navigationdemo.page.DeepLinkUrlPage
import io.zhiller.navigationdemo.page.JsonParamDetailPage
import io.zhiller.navigationdemo.page.JsonParamHomePage
import io.zhiller.navigationdemo.page.NavFadedAnimatePage
import io.zhiller.navigationdemo.page.NavScaleAnimatePage
import io.zhiller.navigationdemo.page.NestedPageHome
import io.zhiller.navigationdemo.page.NestedPageOne
import io.zhiller.navigationdemo.page.NestedPageTwo
import io.zhiller.navigationdemo.page.ParamHomePage
import io.zhiller.navigationdemo.page.ParamLinkPage
import kotlinx.coroutines.delay
import kotlinx.serialization.json.Json

@Composable
fun NavGraphs(
  modifier: Modifier = Modifier,
  navController: NavHostController = rememberNavController(),
  startDestination: String = NavRoutes.INIT_NAV_ROUTE
) {
  val context = LocalContext.current
  
  // -- 返回按钮处理 --
  // 用于记录是否已经点击过一次返回按钮
  var backPressedOnce by remember { mutableStateOf(false) }
  LaunchedEffect(backPressedOnce) {
    // 2 秒后重置标志位
    delay(2000)
    backPressedOnce = false
  }
  // 监听返回按钮事件
  BackHandler(enabled = true) {
    val route = navController.currentBackStackEntry?.destination?.route
    if (route == NavRoutes.INIT_NAV_ROUTE) {
      if (backPressedOnce) {
        // 如果已经点击过一次，退出应用
        (context as ComponentActivity).finish()
      } else {
        // 第一次点击，显示提示消息
        backPressedOnce = true
        Toast.makeText(context, "再次点击将退出应用", Toast.LENGTH_SHORT).show()
      }
    } else {
      // 如果不是顶级路由，正常返回
      navController.popBackStack()
    }
    
    NavRoutes.deepLinkRoutes.forEach {
      if (route == it) {
        NavActions(navController).navToMain()
      }
    }
  }
  
  NavHost(modifier = modifier, navController = navController, startDestination = startDestination) {
    // 主导航图
    composable(NavRoutes.INIT_NAV_ROUTE) { MainScreen(navController) }
    
    // 嵌套图
    navigation(
      startDestination = NestedPageRoutes.Home.route, route = NestedPageRoutes.NestedName.route
    ) {
      composableSlide(NestedPageRoutes.Home.route) {
        NestedPageHome(navController)
      }
      composableSlide(NestedPageRoutes.One.route) {
        NestedPageOne(navController)
      }
      composableSlide(NestedPageRoutes.Two.route) {
        NestedPageTwo(navController)
      }
    }
    
    // 导航过渡动画
    composableScale(route = NavAnimatePageRoutes.Scaled.route) {
      NavScaleAnimatePage(navController)
    }
    composableFaded(route = NavAnimatePageRoutes.Faded.route) {
      NavFadedAnimatePage(navController)
    }
    
    // 基本参数传递
    composableSlide(route = ParamPageRoutes.Home.route) {
      ParamHomePage(navController)
    }
    composableSlide(route = ParamPageRoutes.Link.route, arguments = listOf(navArgument("str") {
      type = NavType.StringType
    })) { entry ->
      val str = entry.arguments?.getString("str") ?: ""
      ParamLinkPage(navController, str)
    }
    
    // JSON 参数传递
    composableSlide(route = JsonParamPageRoutes.Home.route) {
      JsonParamHomePage(navController)
    }
    composableSlide(
      route = JsonParamPageRoutes.Detail.route, arguments = listOf(navArgument("json") {
        type = NavType.StringType
      })
    ) { entry ->
      val json = entry.arguments?.getString("json") ?: ""
      val user = Json.decodeFromString<JsonParamPageRoutes.User>(json)
      JsonParamDetailPage(navController, user)
    }
    
    // deeplink 路由
    composableSlide(route = DeepLinkPageRoutes.Home.route) {
      DeepLinkHomePage(navController)
    }
    composableSlide(route = DeepLinkPageRoutes.Url.route, arguments = listOf(navArgument("str") {
      type = NavType.StringType
    }), deepLinks = listOf(navDeepLink {
      uriPattern = "navigationdemo://deep_link_page_url/{str}"
    })) { entry ->
      val str = entry.arguments?.getString("str") ?: ""
      DeepLinkUrlPage(navController, str)
    }
    composableSlide(route = DeepLinkPageRoutes.Notification.route,
      arguments = listOf(navArgument("str") {
        type = NavType.StringType
      }),
      deepLinks = listOf(navDeepLink {
        uriPattern = "navigationdemo://deep_link_page_notification/{str}"
      })
    ) { entry ->
      val str = entry.arguments?.getString("str") ?: ""
      DeepLinkNotificationPage(navController, str)
    }
  }
}

fun NavGraphBuilder.composableScale(
  route: String,
  arguments: List<NamedNavArgument> = emptyList(),
  deepLinks: List<NavDeepLink> = emptyList(),
  content: @Composable (NavBackStackEntry) -> Unit
) {
  composable(route = route, arguments = arguments, deepLinks = deepLinks, enterTransition = {
    // 新页面：由内往外扩展 + 渐变显示
    fadeIn(animationSpec = tween(300)) + scaleIn(initialScale = 0.8f, animationSpec = tween(300))
  }, exitTransition = {
    // 旧页面：向内缩放 + 渐变消失
    fadeOut(animationSpec = tween(300)) + scaleOut(targetScale = 0.8f, animationSpec = tween(300))
  }, popEnterTransition = {
    // 返回时：新页面（实际上是旧页面）由内往外扩展 + 渐变显示
    fadeIn(animationSpec = tween(300)) + scaleIn(initialScale = 0.8f, animationSpec = tween(300))
  }, popExitTransition = {
    // 返回时：旧页面（实际上是新页面）向内缩放 + 渐变消失
    fadeOut(animationSpec = tween(300)) + scaleOut(targetScale = 0.8f, animationSpec = tween(300))
  }) { entry ->
    content(entry)
  }
}

fun NavGraphBuilder.composableFaded(
  route: String,
  arguments: List<NamedNavArgument> = emptyList(),
  deepLinks: List<NavDeepLink> = emptyList(),
  content: @Composable (NavBackStackEntry) -> Unit
) {
  composable(route = route, arguments = arguments, deepLinks = deepLinks, enterTransition = {
    // 新页面：逐渐呈现（不透明度从 0 到 100%）
    fadeIn(animationSpec = tween(300))
  }, exitTransition = {
    // 旧页面：逐渐变淡消失（不透明度从 100% 到 0）
    fadeOut(animationSpec = tween(300))
  }, popEnterTransition = {
    // 返回时：新页面（实际上是旧页面）逐渐呈现（不透明度从 0 到 100%）
    fadeIn(animationSpec = tween(300))
  }, popExitTransition = {
    // 返回时：旧页面（实际上是新页面）逐渐变淡消失（不透明度从 100% 到 0）
    fadeOut(animationSpec = tween(300))
  }) { entry ->
    content(entry)
  }
}

fun NavGraphBuilder.composableSlide(
  route: String,
  arguments: List<NamedNavArgument> = emptyList(),
  deepLinks: List<NavDeepLink> = emptyList(),
  content: @Composable (NavBackStackEntry) -> Unit
) {
  composable(route = route, arguments = arguments, deepLinks = deepLinks, enterTransition = {
    // 新页面：从右到左滑入 + 渐变显示
    slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(300)) + fadeIn(
      animationSpec = tween(300)
    )
  }, exitTransition = {
    // 旧页面：缩小消失 + 渐变消失
    scaleOut(targetScale = 0.8f, animationSpec = tween(300)) + fadeOut(animationSpec = tween(300))
  }, popEnterTransition = {
    // 返回时：新页面从小到大缩放 + 渐变显示
    scaleIn(initialScale = 0.8f, animationSpec = tween(300)) + fadeIn(animationSpec = tween(300))
  }, popExitTransition = {
    // 返回时：旧页面从左到右滑出 + 渐变消失
    slideOutHorizontally(targetOffsetX = { it }, animationSpec = tween(300)) + fadeOut(
      animationSpec = tween(300)
    )
  }) { entry ->
    content(entry)
  }
}