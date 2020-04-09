import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("maven-publish")
}

android {
    configureAndroid()
    defaultConfig {
        minSdkVersion(9)
    }
}

fun KotlinDependencyHandler.coreLibs(platform: String) {
    api(asoftCore("rx", platform))
    api(asoftCore("io", platform))
    api(asoftCore("persist", platform))
    api(asoftCore("platform", platform))
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
                coreLibs("metadata")
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
                coreLibs("android")
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
                coreLibs("jvm")
                api("org.jetbrains.kotlinx:kotlinx-coroutines-core:${versions.kotlinx.coroutines}")
                api("no.tornado:tornadofx:${versions.tornadofx}")
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
                coreLibs("js")
                api("org.jetbrains:kotlin-react:${versions.kotlinjs.react}")
                api("org.jetbrains:kotlin-react-dom:${versions.kotlinjs.reactDom}")
                api("org.jetbrains:kotlin-styled:${versions.kotlinjs.styled}")
                api("org.jetbrains:kotlin-react-router-dom:${versions.kotlinjs.reactRouterDom}")
            }
        }

        val jsTest by getting {
            dependencies {
                implementation(asoftTest("js"))
            }
        }
    }
}