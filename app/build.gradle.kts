plugins {
    id("com.android.application")
    kotlin("android")
    id("org.jetbrains.kotlin.plugin.serialization")
}

android {
    compileSdk = ProjectConfig.compileSdk

    defaultConfig {
        applicationId = ProjectConfig.appId
        minSdk = ProjectConfig.minSdk
        targetSdk = ProjectConfig.targetSdk
        versionCode = ProjectConfig.versionCode
        versionName = ProjectConfig.versionName

        buildConfigField("String", ApiKeys.TMDB_API_KEY, ApiKeys.getTmdbApiKey)
        buildConfigField("String", ApiKeys.TRAKT_CLIENT_ID, ApiKeys.getTraktClientID)

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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
        kotlinCompilerExtensionVersion = Compose.composeVersion
    }
    packagingOptions {
        exclude("META-INF/AL2.0")
        exclude("META-INF/LGPL2.1")
    }
}

dependencies {
    implementation(project(Modules.core))
    implementation(project(Modules.coreUI))
    implementation(project(Modules.tvShowData))
    implementation(project(Modules.tvShowDomain))
    implementation(project(Modules.tvShowPresentation))

    implementation(AndroidX.coreKtx)
    implementation(AndroidX.lifecycleKtx)

    implementation(Compose.ui)
    implementation(Compose.material)
    implementation(Compose.materialIconsExtended)
    implementation(Compose.uiTooling)
    implementation(Compose.uiToolingPreview)
    implementation(Compose.viewModelCompose)
    implementation(Compose.navigation)
    implementation(Compose.activityCompose)
    implementation(Compose.runtime)
    implementation(Compose.compiler)

    implementation(Coil.coilCompose)

    implementation(Koin.koinAndroid)
    implementation(Koin.koinNavigation)
    implementation(Koin.koinCompose)
}