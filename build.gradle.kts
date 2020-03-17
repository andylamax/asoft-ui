import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler

plugins {
    kotlin("multiplatform") version "1.3.70"
    kotlin("plugin.serialization") version "1.3.70"
    id("com.android.library") version "3.6.0"
    id("maven-publish")
}

object versions {
    val tornadofx = "1.7.19"
    val gson = "2.8.5"

    object asoft {
        val test = "4.2.1"
        val auth = "31.0.0"
    }

    object kotlinx {
        val coroutines = "1.3.4"
    }

    object kotlin {
        val react = "16.13.0-pre.93-kotlin-1.3.70"
        val reactDom = react
        val reactRouterDom = "4.3.1-pre.93-kotlin-1.3.70"
        val styled = "1.0.0-pre.93-kotlin-1.3.70"
    }

    object androidx {
        val lifecycle = "2.2.0"
        val fragment = "1.2.2"
        val appcompat = "1.1.0"
    }
}

fun andylamax(lib: String, platform: String, ver: String): String {
    return "com.github.andylamax.$lib:$lib-$platform:$ver"
}

fun KotlinDependencyHandler.asoftLibs(platform: String) {
    api(andylamax("asoft-auth", platform, versions.asoft.auth))
}

fun asoftTest(platform: String) = andylamax("asoft-test", platform, versions.asoft.test)

group = "tz.co.asoft"
version = "8.0.0"

repositories {
    google()
    jcenter()
    maven(url = "https://jitpack.io")
    maven(url = "https://dl.bintray.com/kotlin/kotlin-js-wrappers")
}

android {
    compileSdkVersion(28)
    defaultConfig {
        minSdkVersion(1)
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }

    sourceSets {
        val main by getting {
            manifest.srcFile("src/androidMain/AndroidManifest.xml")
            res.srcDirs("src/androidMain/resources")
        }
    }

    lintOptions {

    }

    buildTypes {
        val release by getting {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

kotlin {
    android {
        compilations.all {
            kotlinOptions { jvmTarget = "1.8" }
        }
        publishLibraryVariants("release")
    }

    jvm {
        compilations.all {
            kotlinOptions { jvmTarget = "1.8" }
        }
    }

    js {
        compilations.all {
            kotlinOptions {
                metaInfo = true
                sourceMap = true
                moduleKind = "commonjs"
            }
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
                api("org.jetbrains.kotlinx:kotlinx-coroutines-core-common:${versions.kotlinx.coroutines}")
                asoftLibs("metadata")
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(asoftTest("metadata"))
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(kotlin("stdlib"))
                api("org.jetbrains.kotlinx:kotlinx-coroutines-android:${versions.kotlinx.coroutines}")
                asoftLibs("android")
                api("androidx.lifecycle:lifecycle-extensions:${versions.androidx.lifecycle}")
                api("androidx.fragment:fragment:${versions.androidx.fragment}")
                api("androidx.appcompat:appcompat:${versions.androidx.appcompat}")
                api("com.google.code.gson:gson:${versions.gson}")
            }
        }

        val androidTest by getting {
            dependencies {
                implementation(asoftTest("android"))
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation(kotlin("stdlib"))
                api("org.jetbrains.kotlinx:kotlinx-coroutines-javafx:${versions.kotlinx.coroutines}")
                api("no.tornado:tornadofx:${versions.tornadofx}")
                asoftLibs("jvm")
            }
        }

        val jvmTest by getting {
            dependencies {
                implementation(asoftTest("jvm"))
            }
        }
        val jsMain by getting {
            dependencies {
                implementation(kotlin("stdlib-js"))
                api("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:${versions.kotlinx.coroutines}")
                asoftLibs("js")
                api("org.jetbrains:kotlin-react:${versions.kotlin.react}")
                api("org.jetbrains:kotlin-react-dom:${versions.kotlin.reactDom}")
                api("org.jetbrains:kotlin-styled:${versions.kotlin.styled}")
                api("org.jetbrains:kotlin-react-router-dom:${versions.kotlin.reactRouterDom}")
            }
        }
        val jsTest by getting {
            dependencies {
                implementation(asoftTest("js"))
            }
        }
    }
}