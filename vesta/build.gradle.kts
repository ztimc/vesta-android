import org.jetbrains.kotlin.gradle.targets.js.npm.importedPackageDir

plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    kotlin("kapt")
    alias(libs.plugins.ksp)
    alias(libs.plugins.dagger)
}

android {
    namespace = "com.sabinetek.vesta"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    api(libs.androidx.core.ktx)

    // KotlinX
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutinesjdk8)
    //retrofit
    api(libs.retrofit)
    api(libs.retrofit.moshi)
    api(libs.squareup.moshi)
    api(libs.squareup.moshi.adapters)
    ksp(libs.squareup.moshi.kotlin)
    api(platform(libs.squareup.okhttp.bom))
    api(libs.squareup.okhttp)
    api(libs.squareup.okhttp.logging)
    //hilt
    implementation(libs.google.hilt.android)
    implementation(libs.androidx.hilt.compose)
    ksp(libs.google.hilt.compiler)
    //log
    implementation(libs.jakewharton.timber)


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}