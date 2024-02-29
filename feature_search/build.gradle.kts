plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "evgeniy.ryzhikov.feature_search"
    compileSdk = 34

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    }
}

dependencies {

    implementation(AndroidX.core)
    implementation(AndroidX.appcompat)
    implementation(AndroidX.material)
    implementation(AndroidX.constraintLayout)
    implementation(AndroidX.coordinatorLayout)
    testImplementation(Junit.junit)
    androidTestImplementation(AndroidX.junit)
    androidTestImplementation(AndroidX.espresso)

    implementation(AndroidX.Navigation.fragment)
    implementation(AndroidX.Navigation.ui)
    implementation(AndroidX.fragmentKtx)
    implementation(AndroidX.paging)

    implementation(Glide.lib)

    implementation(Retrofit.retrofit)
    implementation(Retrofit.converter)
    implementation(Retrofit.logging)

    implementation(AndroidX.paging)

    implementation(Dagger.dagger_lib)
    ksp(Dagger.dagger_compiler)
    implementation(project(":core"))
    implementation(project(":remote"))
    implementation(project(":database"))
}