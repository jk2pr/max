@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.application")
    id("kotlin-parcelize")
    id("kotlin-android")
    id("com.apollographql.apollo3").version("3.7.3")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

apollo {
    service("service") {
        packageName.set("com.hoppers.max")
    }
}
android {
    namespace = "com.hoppers.max"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.hoppers.max"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName  = "1.0"

        testInstrumentationRunner  = "androidx.test.runner.AndroidJUnitRunner"
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.4"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.10.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.0")
    implementation("androidx.compose.ui:ui:1.4.1")
    implementation("androidx.compose.ui:ui-tooling-preview:1.4.1")
    implementation ("com.google.android.material:material:1.8.0")
    implementation ("androidx.constraintlayout:constraintlayout-compose:1.0.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.4.1")
    debugImplementation("androidx.compose.ui:ui-tooling:1.4.1")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.4.1")

    implementation("com.apollographql.apollo3:apollo-runtime:3.7.3")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")
    implementation ("androidx.compose.material3:material3:1.0.1")
    //DataStore
    implementation ("androidx.datastore:datastore-preferences:1.0.0")
    implementation ("com.google.accompanist:accompanist-systemuicontroller:0.17.0")
    implementation("com.google.dagger:hilt-android:2.43.2")
    kapt("com.google.dagger:hilt-android-compiler:2.43.2")
    kapt("androidx.hilt:hilt-compiler:1.0.0")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    implementation("androidx.navigation:navigation-compose:2.5.3")
}