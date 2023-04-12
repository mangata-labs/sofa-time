apply(from = "$rootDir/android-library-build.gradle")

dependencies {
    "implementation" (project(Modules.core))

    "implementation" (Koin.koinAndroid)
    "implementation" (Koin.koinCompose)

    "implementation" (Compose.materialIconsExtended)

    "implementation" (AndroidX.browser)
}