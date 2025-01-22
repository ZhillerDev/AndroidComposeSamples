package io.zhiller.navigationdemo.utils

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable

object AnimationUtils {
  @Composable
  fun VisibilitySlideUp(visible: Boolean, content: @Composable () -> Unit) {
    AnimatedVisibility(
      visible = visible,
      enter = slideInVertically(
        initialOffsetY = { it -> -it }, // 从屏幕下方开始，偏移量根据自身高度（这里简单用自身高度对应的dp值表示）
        animationSpec = tween(200)
      ) + fadeIn(animationSpec = tween(200)),
      exit = // 当组件要隐藏（消失）时，从上往下滑动退出，同时淡出，动画时长300ms
      slideOutVertically(
        targetOffsetY = { it -> -it }, // 滑动到屏幕上方，偏移量根据自身高度（同样简单用自身高度对应的dp值表示）
        animationSpec = tween(50)
      ) + fadeOut(animationSpec = tween(50)),
    ) {
      content()
    }
  }
  
  @Composable
  fun VisibilitySlideDown(visible: Boolean, content: @Composable () -> Unit) {
    AnimatedVisibility(
      visible = visible,
      enter = slideInVertically(
        initialOffsetY = { it -> it }, // 从屏幕下方开始，偏移量根据自身高度（这里简单用自身高度对应的dp值表示）
        animationSpec = tween(200)
      ) + fadeIn(animationSpec = tween(200)),
      exit = // 当组件要隐藏（消失）时，从上往下滑动退出，同时淡出，动画时长300ms
      slideOutVertically(
        targetOffsetY = { it -> it }, // 滑动到屏幕上方，偏移量根据自身高度（同样简单用自身高度对应的dp值表示）
        animationSpec = tween(50)
      ) + fadeOut(animationSpec = tween(50)),
    ) {
      content()
    }
  }
}