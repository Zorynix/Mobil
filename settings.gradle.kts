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
        maven { url = uri("https://jitpack.io") }
    }
}

rootProject.name = "Mobile Dev"
//include(":app:prac1")
//include(":app:prac2")
//include(":app:prac3")
//include(":app:prac4")
//include(":app:prac5")
//include(":app:prac6")
//include(":app:prac7")
//include(":app:prac8")
//include(":app:prac9")
//include(":app:prac10")
//include(":app:prac11")
//include(":app:prac12")
include(":app:coursach")
