plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("org.jetbrains.kotlin.plugin.serialization")
}

android {
    namespace = "evgeniy.ryzhikov.nasaphotos"
    compileSdk = 34

    defaultConfig {
        applicationId = "evgeniy.ryzhikov.nasaphotos"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_19
        targetCompatibility = JavaVersion.VERSION_19
    }
    kotlinOptions {
        jvmTarget = "19"
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {

    implementation(AndroidX.core)
    implementation(AndroidX.appcompat)
    implementation(AndroidX.material)
    implementation(AndroidX.constraintLayout)
    testImplementation(Junit.junit)
    androidTestImplementation(AndroidX.junit)
    androidTestImplementation(AndroidX.espresso)

    implementation(AndroidX.Navigation.fragment)
    implementation(AndroidX.Navigation.ui)
    implementation(AndroidX.fragmentKtx)
    implementation(Coroutines.lib)
    implementation(Dagger.dagger_lib)
    implementation(Dagger.dagger_compiler)

    implementation(Glide.lib)

    implementation(project(":feature_random_photo"))
    implementation(project(":feature_search"))
    implementation(project(":feature_favorites"))
    implementation(project(":database"))
    implementation(project(":utils"))
    implementation(project(":remote"))
    implementation(project(":core"))

}
