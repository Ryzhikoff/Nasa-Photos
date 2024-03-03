object Versions {
    const val appcompat = "1.6.1"
    const val constraintLayout = "2.1.4"
    const val coordinatorLayout = "1.2.0"
    const val dagger = "2.50"
    const val core = "1.12.0"
    const val material = "1.11.0"
    const val espresso = "3.5.1"
    const val junit = "4.13.2"
    const val androidx_junit = "1.1.5"
    const val navigation = "2.7.7"
    const val fragmentKtx = "1.6.2"
    const val retrofit = "2.9.0"
    const val okhttp3 = "4.12.0"
    const val room = "2.6.1"
    const val glide = "4.16.0"
    const val coroutines = "1.7.3"
    const val kotlinxSerialization = "1.6.3"
    const val paging = "3.2.1"
}

object Junit {
    const val junit = "junit:junit:${Versions.junit}"
}
object AndroidX {
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val coordinatorLayout = "androidx.coordinatorlayout:coordinatorlayout:${Versions.coordinatorLayout}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val core = "androidx.core:core-ktx:${Versions.core}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val junit = "androidx.test.ext:junit:${Versions.androidx_junit}"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragmentKtx}"
    const val paging = "androidx.paging:paging-runtime:${Versions.paging}"
    object Navigation {
        const val fragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
        const val ui = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    }
}

object KotlinX {
    const val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.kotlinxSerialization}"
}

object Dagger {
    const val dagger_lib = "com.google.dagger:dagger:${Versions.dagger}"
    const val dagger_compiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
}
object Retrofit {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val converter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val logging = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp3}"
}

object Room {
    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val room = "androidx.room:room-ktx:${Versions.room}"
    const val compiler ="androidx.room:room-compiler:${Versions.room}"
}

object Glide {
    const val lib = "com.github.bumptech.glide:glide:${Versions.glide}"
}

object Coroutines {
    const val lib = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
}

