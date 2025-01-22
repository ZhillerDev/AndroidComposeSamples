package io.zhiller.navigationdemo.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.core.app.NotificationCompat

object IntentUtils {
  fun openWebsite(context: Context, url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    context.startActivity(intent)
  }
  
  /**
   * 创建并发送一个通知，该通知包含一个Deep Link，用于打开应用程序中的特定页面
   *
   * @param context 上下文，用于访问应用程序的资源和组件
   * @param str 用于构建Deep Link URI的字符串，通常是一个标识符
   */
  fun createNotification(context: Context, str: String) {
    // 获取通知管理器，用于创建和发送通知
    val notificationManager =
      context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    
    // 创建通知渠道（Android 8.0 及以上需要）
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      val channel = NotificationChannel(
        "deep_link_channel",
        "Deep Link Channel",
        NotificationManager.IMPORTANCE_DEFAULT
      )
      notificationManager.createNotificationChannel(channel)
    }
    
    // 创建 Deep Link Intent
    val deepLinkUri = Uri.parse("navigationdemo://deep_link_page_notification/$str")
    val intent = Intent(Intent.ACTION_VIEW, deepLinkUri)
    val pendingIntent = PendingIntent.getActivity(
      context,
      1234,
      intent,
      PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )
    
    // 创建通知
    val notification = NotificationCompat.Builder(context, "deep_link_channel")
      .setContentTitle("Deep Link Notification")
      .setContentText("Click to open detail page")
      .setSmallIcon(android.R.drawable.ic_dialog_info)
      .setContentIntent(pendingIntent)
      .setAutoCancel(true)
      .build()
    
    // 发送通知
    notificationManager.notify(1234, notification)
  }
}