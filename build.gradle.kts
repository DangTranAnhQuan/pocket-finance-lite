plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.hilt.android) apply false
    id("org.jlleitschuh.gradle.ktlint") version "14.0.1" apply false
    id("io.gitlab.arturbosch.detekt") version "1.23.8" apply false
}