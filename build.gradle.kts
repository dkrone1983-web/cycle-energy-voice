// Root build.gradle.kts
plugins {
    id("com.android.application") version "8.1.2" apply false
    kotlin("android") version "1.9.20" apply false
    kotlin("kapt") version "1.9.20" apply false
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}
