plugins {
    id("com.android.application")
    id("kotlin-android")
}

android {
    setCompileSdkVersion(property("compileSdkVersion") as Int)
    defaultConfig {
        applicationId = "com.otaliastudios.cameraview.demo"
        setMinSdkVersion(property("minSdkVersion") as Int)
        setTargetSdkVersion(property("targetSdkVersion") as Int)
        versionCode = 1
        versionName = "1.0"
        vectorDrawables.useSupportLibrary = true
    }
    sourceSets["main"].java.srcDir("src/main/kotlin")
}

dependencies {
    implementation(project(":cameraview"))
    implementation("com.github.bumptech.glide:glide:4.7.1")
    implementation ("androidx.viewpager2:viewpager2:1.0.0")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("com.google.android.material:material:1.2.0")
}
