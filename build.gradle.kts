buildscript {
    dependencies {
        val nav_version = "2.7.7"
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version")
        classpath("com.google.gms:google-services:4.4.1")
    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.3.1" apply false
    id("io.gitlab.arturbosch.detekt") version("1.23.3")
    id("com.android.library") version "8.3.1" apply false
    id("com.google.gms.google-services") version "4.4.1" apply false
}

detekt {
    toolVersion = "1.23.3"
    config.setFrom(file(".detekt.yml"))
    parallel=true
    source.setFrom("app/prac1/src/main/java",
            "app/prac2/src/main/java",
            "app/prac3/src/main/java",
            "app/prac4/src/main/java",
            "app/prac5/src/main/java",
            "app/prac6/src/main/java",
            "app/prac7/src/main/java",
            "app/prac8/src/main/java",
            "app/prac9/src/main/java",
            "app/prac10/src/main/java",
            "app/prac11/src/main/java",
            "app/prac12/src/main/java", )
    allRules=true
}

dependencies {
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.23.3")
}