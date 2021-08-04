buildscript {
    repositories {
        google()
        mavenCentral()
    }

    val kotlinVersion = findProperty("version.kotlin") as String
    val gradlePluginVersion = findProperty("version.androidGradlePlugin") as String

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("org.jetbrains.kotlin:kotlin-serialization:$kotlinVersion")
        classpath("com.android.tools.build:gradle:$gradlePluginVersion")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
