package io.zhiller.navigationdemo.utils

import android.content.Context
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object ScreenUtils {
  /**
   * 获取屏幕宽度（Dp）
   */
  fun getScreenWidthDp(context: Context): Dp {
    val displayMetrics = context.resources.displayMetrics
    val widthPx = displayMetrics.widthPixels
    return pxToDp(context, widthPx)
  }
  
  /**
   * 获取屏幕高度（Dp）
   */
  fun getScreenHeightDp(context: Context): Dp {
    val displayMetrics = context.resources.displayMetrics
    val heightPx = displayMetrics.heightPixels
    return pxToDp(context, heightPx)
  }
  
  /**
   * 将像素值转换为 Dp
   */
  private fun pxToDp(context: Context, px: Int): Dp {
    val density = context.resources.displayMetrics.density
    return (px / density).dp
  }
  
}