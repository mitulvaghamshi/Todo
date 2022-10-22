plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "com.example.todo"
    compileSdk = 33
    buildFeatures.compose = true
    composeOptions.kotlinCompilerExtensionVersion = "1.3.1"

    defaultConfig {
        applicationId = "com.example.todo"
        minSdk = 29
        targetSdk = 33
        versionCode = 1
        versionName = "1.0.0"
        vectorDrawables.useSupportLibrary = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"))
        }
    }
}

dependencies {
    val compose = "1.2.1"
    val lifecycle = "2.5.1"
    implementation("androidx.appcompat:appcompat:1.5.1")
    implementation("androidx.activity:activity-compose:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")

    implementation("androidx.compose.ui:ui:$compose")
    implementation("androidx.compose.ui:ui-tooling:$compose")
    implementation("androidx.compose.material:material:$compose")
    implementation("androidx.compose.foundation:foundation:$compose")
    implementation("androidx.compose.ui:ui-test-manifest:$compose")
    implementation("androidx.compose.ui:ui-tooling-preview:$compose")
    implementation("androidx.compose.runtime:runtime-livedata:$compose")
    implementation("androidx.compose.foundation:foundation-layout:$compose")
    implementation("androidx.compose.material:material-icons-extended:$compose")

    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycle")
    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:$lifecycle")

    testImplementation("junit:junit:4.13.2")
    testImplementation("androidx.test.ext:junit:1.1.4")
    testImplementation("com.google.truth:truth:1.1.3")

    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$compose")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.0")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions.freeCompilerArgs += "-opt-in=org.mylibrary.OptInAnnotation"
    kotlinOptions.freeCompilerArgs += "-opt-in=kotlin.RequiresOptIn"
}
