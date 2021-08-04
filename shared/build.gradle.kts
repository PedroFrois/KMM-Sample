import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("plugin.serialization")
}
// Android SDK Versions
val compileSdkNumber = (findProperty(("android.compileSdk")) as String).toInt()
val targetSdkNumber = (findProperty(("android.targetSdk")) as String).toInt()
val minSdkNumber = (findProperty(("android.minSdk")) as String).toInt()

// Network
val ktorVersion = findProperty("version.ktor") as String
// Coroutines
val coroutineVersion = findProperty("version.kotlinx.coroutines") as String
// Logger
val napierVersion = findProperty("version.napier") as String
// Key-Value storage
val multiSettingsVersion = findProperty("version.multiplatform.settings") as String
// Test
val junitVersion = findProperty("version.junit") as String

kotlin {
    android()

    val iosTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget = when {
        System.getenv("SDK_NAME")?.startsWith("iphoneos") == true -> ::iosArm64
        else -> ::iosX64
    }

    iosTarget("ios") {
        binaries {
            framework {
                baseName = "shared"
            }
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                // Network
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-logging:$ktorVersion")
                implementation("io.ktor:ktor-client-mock:$ktorVersion")
                implementation("io.ktor:ktor-client-serialization:$ktorVersion")
                // Coroutines
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutineVersion")
                // Logger
                implementation("io.github.aakira:napier:$napierVersion")
                // Key-Value storage
                implementation("com.russhwolf:multiplatform-settings:$multiSettingsVersion")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting {
            dependencies {
                // Network
                implementation("io.ktor:ktor-client-okhttp:$ktorVersion")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:$junitVersion")
            }
        }
        val iosMain by getting {
            dependencies {
                // Network
                implementation("io.ktor:ktor-client-ios:$ktorVersion")
            }
        }
        val iosTest by getting
    }
}

android {
    compileSdk = compileSdkNumber
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = minSdkNumber
        targetSdk = targetSdkNumber
    }
}
