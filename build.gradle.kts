// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.50")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.22")
    }
}

plugins {
    id("com.android.application") version "8.2.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.22" apply false
    id ("com.google.dagger.hilt.android") version "2.50" apply false
    id ("com.google.devtools.ksp") version "1.9.22-1.0.17" apply false
    id ("org.jetbrains.kotlin.jvm") version "1.9.22" apply false
}