import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp") version "2.2.0-2.0.2"
}

android {
    namespace = "com.example.todoist"
    compileSdk = 36

    val secretsFile = rootProject.file("secrets.properties")
    val secrets = Properties()

    if (secretsFile.exists()) {
        secrets.load(FileInputStream(secretsFile))
    } else {
        logger.warn("secrets.properties not found!")
    }

    defaultConfig {
        applicationId = "com.example.todoist"
        minSdk = 30
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("long", "BUILD_TIME", "${System.currentTimeMillis()}L")
        buildConfigField("String", "API_KEY", "\"${secrets.getProperty("API_KEY", "")}\"")
        buildConfigField("String", "BASE_URL", "\"${secrets.getProperty("BASE_URL", "")}\"")
    }

    signingConfigs {

        create("release") {
            storeFile = file(secrets.getProperty("STORE_FILE", "todo-keystore.jks"))
            storePassword = secrets.getProperty("KEYSTORE_PASSWORD", "")
            keyPassword = secrets.getProperty("KEY_PASSWORD", "")
            keyAlias = secrets.getProperty("KEY_ALIAS", "")
        }

    }

    flavorDimensions += listOf("country", "env")
    
    productFlavors {
        create("sriLanka") {
            dimension = "country"
            versionNameSuffix = "-Lk"
            resValue("string", "app_name", "ToDoList LK")
        }
        create("canada") {
            dimension = "country"
            versionNameSuffix = "-Ca"
            resValue("string", "app_name", "DoToIst CA")
        }
        create("live") {
            dimension = "env"
            buildConfigField("String", "BASE_URL_LANKA", "\"https://live.srilanka.example.com\"")
            buildConfigField("String", "BASE_URL_CANADA", "\"https://live.canada.example.com\"")
        }
        create("staging") {
            dimension = "env"
            buildConfigField("String", "BASE_URL_LANKA", "\"https://staging.srilanka.example.com\"")
            buildConfigField("String", "BASE_URL_CANADA", "\"https://staging.canada.example.com\"")
        }
        create("dev") {
            dimension = "env"
            buildConfigField("String", "BASE_URL_LANKA", "\"https://dev.srilanka.example.com\"")
            buildConfigField("String", "BASE_URL_CANADA", "\"https://dev.canada.example.com\"")
        }
    }

    sourceSets {
        sourceSets.getByName("sriLanka") {
            java {
                srcDirs("src/lk/java")
            }
            res {
                srcDirs("src/lk/res")
            }
        }
        sourceSets.getByName("canada") {
            java {
                srcDirs("src/ca/java")
            }
            res {
                srcDirs("src/ca/res")
            }
        }

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
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    implementation(libs.hilt.navigation)
    implementation(libs.navigation.compose)
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    ksp(libs.room.compiler)
    implementation(libs.bundles.room)
}