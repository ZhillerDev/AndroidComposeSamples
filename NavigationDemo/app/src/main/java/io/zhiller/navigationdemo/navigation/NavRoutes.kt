package io.zhiller.navigationdemo.navigation

object NavRoutes {
  
  const val INIT_NAV_ROUTE = "main"
  
  val bottomNavRoutes = listOf(
    BottomNavRoutes.Home, BottomNavRoutes.Discovery, BottomNavRoutes.Me
  )
  
  val nestedPageRoutes = listOf(
    NestedPageRoutes.Home.route, NestedPageRoutes.One.route, NestedPageRoutes.Two.route
  )
  
  val navAnimatePageRoutes = listOf(
    NavAnimatePageRoutes.Faded.route, NavAnimatePageRoutes.Scaled.route
  )
  
  val deepLinkRoutes = listOf(
    DeepLinkPageRoutes.Url.route,
    DeepLinkPageRoutes.Notification.route,
  )
}