plugins {
    kotlin("android").version("1.7.10").apply(false)
    id("com.android.application").version("7.3.1").apply(false)
    id("com.google.gms.google-services").version("4.3.14").apply(false)
    id("com.google.dagger.hilt.android").version("2.44").apply(false)
    id("com.google.firebase.crashlytics").version("2.9.1").apply(false)
    id("com.google.firebase.firebase-perf").version("1.4.1").apply(false)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
