plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.jetbrains.kotlin.compose)
    alias(libs.plugins.jetbrains.kotlinx.serialization)
    alias(libs.plugins.hilt.version)
    alias(libs.plugins.room.version)
    alias(libs.plugins.ksp.version)
    alias(libs.plugins.kotlin.parcelize)
}

android {
    namespace = "com.albertsons.employeeapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.albertsons.employeeapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }

    hilt {
        enableAggregatingTask = true
    }
    room {
        schemaDirectory("$projectDir/schemas")
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)


    //navigation compose
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    //hilt dagger
    implementation(libs.androidx.hilt.android)
    ksp(libs.androidx.hilt.compiler)
    ksp(libs.hilt.androidx.compiler)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.kotlinx.serialisation.json)

    //Coroutines
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.core)
    //Room Database
    ksp(libs.androidx.room.compiler)
    // annotationProcessor(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.guava)
    // implementation(libs.androidx.room.coroutines)
    implementation(libs.androidx.room.common)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.paging.guava)

    // OkHttp3
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)
    implementation(platform(libs.okhttp.bom))

    //coil image loader
    implementation(libs.androidx.coil)
    implementation(libs.androidx.coil.compose)
    //Glide image loader
    implementation(libs.androidx.glider)
    ksp(libs.androidx.glider.compiler)

    //retrofit
    implementation(libs.retrofit)
    implementation(libs.google.gson)
    implementation(libs.converter.gson)
    implementation(libs.retrofit.converters)
    implementation(libs.retrofit.adapters)
    //data store preferences
    implementation(libs.androidx.datastore)
    // paging
    implementation(libs.androidx.paging)
    implementation(libs.androidx.paging.compose)
    // Accompanist
    implementation(libs.accompanist.permission)
    // Viewpager2
    implementation(libs.androidx.viewpager2)

    testImplementation(libs.androidx.junit)
    testImplementation(libs.mockwebserver)
    testImplementation(libs.androidx.core.test)
    testImplementation(libs.androidx.mock.test)
    testImplementation(libs.androidx.mockito.core)
    testImplementation(libs.androidx.mockito.kotlin)
    testImplementation(libs.kotlinx.coroutines.test)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}