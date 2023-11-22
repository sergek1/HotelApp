pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}

rootProject.name = "HotelTest"
include(":app")
include(":hotel")
include(":rooms")
include(":booking")
include(":resources")
include(":database")
include(":paid")
