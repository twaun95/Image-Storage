object Kotlin {
    const val KOTLIN_STDLIB      = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.KOTLIN_VERSION}"
    const val COROUTINES_CORE    = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.KOTLIN_COROUTINE}"
    const val COROUTINES_ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.KOTLIN_COROUTINE}"
}


object AndroidX {
    const val APP_COMPAT                  = "androidx.appcompat:appcompat:${Versions.APP_COMPAT}"
    const val CORE_KTX                    = "androidx.core:core-ktx:${Versions.CORE_KTX}"
    const val CONSTRAINT_LAYOUT           = "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT}"
    const val ACTIVITY                    = "androidx.activity:activity-ktx:${Versions.ACTIVITY}"
    const val VIEW_MODEL                  = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LIFECYCLE}"
    const val FRAGMENT                    = "androidx.fragment:fragment-ktx:${Versions.FRAGMENT}"
    const val SWIPE_LAYOUT                = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.SWIPE_LAYOUT}"
    const val MULTI_DEX                   = "androidx.multidex:multidex:${Versions.MULTI_DEX}"
}

object Google {
    const val HILT_ANDROID                = "com.google.dagger:hilt-android:${Versions.HILT}"
    const val HILT_ANDROID_COMPILER       = "com.google.dagger:hilt-android-compiler:${Versions.HILT}"

    const val MATERIAL                    = "com.google.android.material:material:${Versions.MATERIAL}"
}

object UnitTest {
    const val JUNIT                       = "junit:junit:${Versions.JUNIT}"
}

object AndroidTest {
    const val ANDROID_JUNIT               = "junit:junit:${Versions.ANDROID_JUNIT}"
    const val ESPRESSO_CORE               = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO_CORE}"
}

object Libraries {
    const val RETROFIT                    = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT}"
    const val RETROFIT_CONVERTER_GSON     = "com.squareup.retrofit2:converter-gson:${Versions.RETROFIT}"
    const val OKHTTP                      = "com.squareup.okhttp3:okhttp:${Versions.OKHTTP}"
    const val OKHTTP_LOGGING_INTERCEPTOR  = "com.squareup.okhttp3:logging-interceptor:${Versions.OKHTTP}"
    const val TIMBER                      = "com.jakewharton.timber:timber:${Versions.TIMBER}"
    const val LOTTIE                      = "com.airbnb.android:lottie:${Versions.LOTTIE}"
    const val LEAK_CANARY                 = "com.squareup.leakcanary:leakcanary-android:${Versions.LEAK_CANARY}"
    const val GLIDE                       = "com.github.bumptech.glide:glide:${Versions.GLIDE}"
    const val GLIDE_COMPILER              = "com.github.bumptech.glide:glide:${Versions.GLIDE}"
    const val GLIDE_OKHTTP                = "com.github.bumptech.glide:okhttp3-integration:${Versions.GLIDE}"
}
