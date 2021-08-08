plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    id("kotlin-kapt")
}
// Android SDK Versions
val compileSdkNumber = (findProperty(("android.compileSdk")) as String).toInt()
val targetSdkNumber = (findProperty(("android.targetSdk")) as String).toInt()
val minSdkNumber = (findProperty(("android.minSdk")) as String).toInt()
// Coroutine
val coroutineVersion = findProperty("android.kotlinx.coroutines") as String
// UI
val appCompatVersion = findProperty("android.appcompat") as String
val materialVersion = findProperty("android.material") as String
val constraintVersion = findProperty("android.constraintlayout") as String
// Lifecycle
val lifecycleVersion = findProperty("android.lifecycle") as String
val lifecycleExtVersion = findProperty("android.lifecycle.extensions") as String
// Navigation
val navigationVersion = findProperty("android.navigation") as String
// DI
val koinVersion = findProperty("android.koin") as String
// WorkManager
val workVersion = findProperty("android.work") as String
// Key-Value storage
val multiSettingsVersion = findProperty("version.multiplatform.settings") as String

dependencies {
    implementation(project(":shared"))

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutineVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutineVersion")

    // UI
    implementation("androidx.appcompat:appcompat:$appCompatVersion")
    implementation("com.google.android.material:material:$materialVersion")
    implementation("androidx.constraintlayout:constraintlayout:$constraintVersion")

    // Lifecycle
    implementation("androidx.lifecycle:lifecycle-extensions:$lifecycleExtVersion")
    implementation("androidx.lifecycle:lifecycle-runtime:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")

    // Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:$navigationVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navigationVersion")

    // DI
    implementation("io.insert-koin:koin-core:$koinVersion")
    implementation("io.insert-koin:koin-android:$koinVersion")

    // WorkManager
    implementation("androidx.work:work-runtime-ktx:$workVersion")

    // Key-Value storage
    implementation("com.russhwolf:multiplatform-settings:$multiSettingsVersion")
}

android {
    compileSdk = compileSdkNumber
    buildFeatures {
        dataBinding = true
    }
    defaultConfig {
        applicationId = "br.com.bittencourt.kmmsample.android"
        minSdk = minSdkNumber
        targetSdk = targetSdkNumber
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}
