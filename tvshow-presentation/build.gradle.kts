apply(from = "$rootDir/android-library-build.gradle")

dependencies {
    "implementation" (project(Modules.tvShowDomain))
    "implementation" (project(Modules.coreUI))

    "implementation" (Coil.coilCompose)

    "implementation" (Koin.koinAndroid)
    "implementation" (Koin.koinCompose)
}