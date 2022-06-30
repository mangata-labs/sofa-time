apply(from = "$rootDir/android-library-build.gradle")

dependencies {
    "implementation" (project(Modules.core))
    "implementation" (project(Modules.tvShowDomain))
    "implementation" (project(Modules.coreUI))

    "implementation" (Coil.coilCompose)

    "implementation" (Koin.koinAndroid)
    "implementation" (Koin.koinCompose)

    "implementation" (Compose.materialIcons)
    "implementation" (Compose.pagerCompose)
}