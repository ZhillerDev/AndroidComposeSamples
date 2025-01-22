package io.zhiller.navigationdemo.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ManageSearch
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

// 密闭类定义底部导航项
sealed class BottomNavRoutes(
  val name: String, // 导航项名称
  val icon: ImageVector // 导航项图标
) {
  data object Home : BottomNavRoutes(
    name = "Home", icon = Icons.Default.Home
  )
  
  data object Discovery : BottomNavRoutes(
    name = "Discovery", icon = Icons.AutoMirrored.Filled.ManageSearch
  )
  
  data object Me : BottomNavRoutes(
    name = "Me", icon = Icons.Default.Person
  )
}

sealed class NestedPageRoutes(val route: String) {
  data object Home : NestedPageRoutes("nested_page_home")
  data object One : NestedPageRoutes("nested_page_1")
  data object Two : NestedPageRoutes("nested_page_2")
  data object NestedName : NestedPageRoutes("nested_page_route")
}

sealed class NavAnimatePageRoutes(val route: String) {
  data object Scaled : NavAnimatePageRoutes("nav_page_scaled")
  data object Faded : NavAnimatePageRoutes("nav_page_faded")
}

// 定义路由
sealed class ParamPageRoutes(val route: String) {
  data object Home : ParamPageRoutes("param_page_home")
  data object Link : ParamPageRoutes("param_page_link/{str}") {
    fun addParam(str: String) = "param_page_link/$str"
  }
}

sealed class JsonParamPageRoutes(val route: String) {
  @Serializable
  data class User(
    val id: Int, val name: String, val email: String
  )
  
  data object Home : JsonParamPageRoutes("json_param_page_home")
  data object Detail : JsonParamPageRoutes("json_param_page_detail/{json}") {
    fun addJson(user: User) = "json_param_page_detail/${Json.encodeToString(user)}"
  }
}

sealed class DeepLinkPageRoutes(val route: String) {
  data object Home : DeepLinkPageRoutes("deep_link_page_home")
  data object Url : DeepLinkPageRoutes("deep_link_page_url/{str}")
  data object Notification : DeepLinkPageRoutes("deep_link_page_notification/{str}")
}