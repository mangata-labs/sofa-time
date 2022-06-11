apply(from = "$rootDir/library-build.gradle")
apply (plugin = "org.jetbrains.kotlin.plugin.serialization")

dependencies {
    "implementation" (project(Modules.tvShowDomain))

    "implementation" (KotlinX.coroutinesCore)
    "implementation" (KotlinX.serialization)

    "implementation" (Ktor.ktorCore)
    "implementation" (Ktor.ktorAndroid)
    "implementation" (Ktor.ktorContentNegotiation)
    "implementation" (Ktor.ktorSerialization)
    "implementation" (Ktor.ktorLogging)

    "implementation" (Koin.koinCore)
}