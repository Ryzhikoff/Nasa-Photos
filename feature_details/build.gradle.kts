plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "evgeniy.ryzhikov.features_details"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
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
    implementation("androidx.appcompat:appcompat:1.6.1")
    testImplementation(Junit.junit)
    androidTestImplementation(AndroidX.junit)
    androidTestImplementation(AndroidX.espresso)
    implementation(Glide.lib)
    implementation(Dagger.dagger_lib)
    ksp(Dagger.dagger_compiler)
    implementation(AndroidX.fragmentKtx)

    implementation("com.github.clans:fab:1.6.4")


    implementation(project(":core"))
    implementation(project(":database"))
}