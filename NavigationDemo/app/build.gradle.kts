plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.android)
  
  id("com.google.devtools.ksp") version "2.0.10-1.0.24"
  id("org.jetbrains.kotlin.plugin.compose") version "2.0.10"
  id("org.jetbrains.kotlin.plugin.serialization") version "2.0.10"
}

android {
  namespace = "io.zhiller.navigationdemo"
  compileSdk = 35
  
  defaultConfig {
    applicationId = "io.zhiller.navigationdemo"
    minSdk = 30
    targetSdk = 34
    versionCode = 1
    versionName = "1.0"
    
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    vectorDrawables {
      useSupportLibrary = true
    }
  }
  
  buildTypes {
    release {
      isShrinkResources = true
      isMinifyEnabled = true
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }
  kotlinOptions {
    jvmTarget = "1.8"
  }
  buildFeatures {
    compose = true
  }
  composeOptions {
    kotlinCompilerExtensionVersion = "1.5.1"
  }
  packaging {
    
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
  }
}

dependencies {
  
  // Navigation
  implementation("androidx.navigation:navigation-compose:2.8.5")
  implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.1")
  // Icons
  implementation("androidx.compose.material:material-icons-extended:1.7.6")
  
  // ViewModel 库
  val lifecycleVersion = "2.8.6"
  implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
  implementation("androidx.lifecycle:lifecycle-runtime-compose:$lifecycleVersion")
  implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycleVersion")
  implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")
  implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
  
  implementation("com.google.accompanist:accompanist-permissions:0.37.0")
  
  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.lifecycle.runtime.ktx)
  implementation(libs.androidx.activity.compose)
  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.androidx.ui)
  implementation(libs.androidx.ui.graphics)
  implementation(libs.androidx.ui.tooling.preview)
  implementation(libs.androidx.material3)
  testImplementation(libs.junit)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)
  androidTestImplementation(platform(libs.androidx.compose.bom))
  androidTestImplementation(libs.androidx.ui.test.junit4)
  debugImplementation(libs.androidx.ui.tooling)
  debugImplementation(libs.androidx.ui.test.manifest)
}