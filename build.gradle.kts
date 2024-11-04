// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.jetbrains.kotlin.compose) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.jetbrains.kotlinx.serialization) apply false
    alias(libs.plugins.ksp.version) apply false
    alias(libs.plugins.hilt.version) apply false
    alias(libs.plugins.room.version) apply false
    alias(libs.plugins.kotlin.parcelize) apply false
}