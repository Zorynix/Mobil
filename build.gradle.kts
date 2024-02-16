// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.2" apply false
    id("io.gitlab.arturbosch.detekt") version("1.23.3")
}

detekt {
    toolVersion = "1.23.3"
    config.setFrom(file(".detekt.yml"))
    parallel=true
    source.setFrom("app/prac1/src/main/java", "app/prac2/src/main/java")
    allRules=true
}

dependencies {
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.23.3")
}