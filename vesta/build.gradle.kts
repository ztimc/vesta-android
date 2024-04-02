import org.jetbrains.kotlin.de.undercouch.gradle.tasks.download.org.apache.commons.logging.LogFactory.release
import org.jetbrains.kotlin.gradle.targets.js.npm.importedPackageDir

plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.ksp)
    alias(libs.plugins.dagger)

    `maven-publish`
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("maven") {
                groupId = "com.sabinetek.vesta"
                artifactId = "com-sabinetek-vesta"
                version = "1.0.8"

                from(components["release"])
            }
        }
    }
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

    implementation(files("libs/SaaS_TalkingDataSDK_Android_V5.0.24.jar"))

    // KotlinX
    api(libs.kotlinx.coroutines.android)
    api(libs.kotlinx.coroutines.core)
    api(libs.kotlinx.coroutinesjdk8)
    //retrofit
    api(libs.retrofit)
    api(libs.retrofit.moshi)
    api(libs.squareup.moshi)
    api(libs.squareup.moshi.adapters)
    ksp(libs.squareup.moshi.kotlin)
    api(platform(libs.squareup.okhttp.bom))
    api(libs.squareup.okhttp)
    api(libs.squareup.okhttp.logging)
    testImplementation(libs.squareup.okhttp.mockwebserver)
    androidTestImplementation(libs.squareup.okhttp.mockwebserver)
    //hilt
    implementation(libs.google.hilt.android)
    implementation(libs.androidx.hilt.compose)
    ksp(libs.google.hilt.compiler)
    kspTest(libs.google.hilt.compiler)
    kspAndroidTest(libs.google.hilt.compiler)
    testImplementation(libs.google.hilt.testing)
    androidTestImplementation(libs.google.hilt.testing)
    //log
    api(libs.jakewharton.timber)

    // Unit Tests
    testImplementation(libs.androidx.test.annotation)
    testImplementation(libs.androidx.test.core)
    testImplementation(libs.androidx.test.junit)
    testImplementation(libs.google.truth)
    testImplementation(libs.junit)
    testImplementation(libs.mockito.inline)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.robolectric)

    // Android Tests
    androidTestUtil(libs.androidx.test.orchestrator)
    androidTestImplementation(libs.androidx.test.annotation)
    androidTestImplementation(libs.androidx.test.core)
    androidTestImplementation(libs.androidx.test.junit)
    androidTestImplementation(libs.androidx.test.runner)
    androidTestImplementation(libs.androidx.test.rules)
    androidTestImplementation(libs.google.truth)
    androidTestImplementation(libs.junit)
    androidTestImplementation(libs.robolectric.annotations)
}