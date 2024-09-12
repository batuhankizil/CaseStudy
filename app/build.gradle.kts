plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-parcelize")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")

}

android {
    namespace = "com.example.study"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.study"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        //testInstrumentationRunner = "com.example.android.dagger.CustomTestRunner"

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

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }

    kapt {
        correctErrorTypes = true
    }
}


dependencies {

    testImplementation (libs.kotlinx.coroutines.test)

    androidTestImplementation(libs.hilt.android.testing)
    kaptAndroidTest(libs.hilt.android.compiler)

    testImplementation(libs.mockk)

    androidTestImplementation(libs.androidx.runner)
    androidTestImplementation(libs.androidx.rules)

    // retrofit
    implementation(libs.retrofit)
    // gson converter
    implementation(libs.converter.gson)

    implementation(libs.junit.junit)
    implementation(libs.androidx.junit.ktx)
    testImplementation(libs.junit.v1)

    testImplementation(libs.truth)
    androidTestImplementation(libs.truth)

    implementation(libs.kotlinx.serialization.json)
    implementation(libs.gson)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    implementation(libs.hilt.android)
    kapt (libs.hilt.compiler)

    implementation(libs.androidx.annotation)
    implementation(libs.androidx.lifecycle.viewmodel.ktx.v283)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.livedata.ktx.v283)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.savedstate)

    implementation(libs.androidx.fragment.ktx)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)


}