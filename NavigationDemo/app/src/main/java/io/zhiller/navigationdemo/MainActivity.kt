package io.zhiller.navigationdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import io.zhiller.navigationdemo.navigation.NavGraphs
import io.zhiller.navigationdemo.ui.theme.NavigationDemoTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      NavigationDemoTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
          NavGraphs()
        }
      }
    }
  }
}
