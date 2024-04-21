plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    //kotlin("android")
    //kotlin("android.extensions")
    //kotlin("kapt")
}

android {
    namespace = "app.gratum.kawaishiryuappnew"
    compileSdk = 34

    defaultConfig {
        applicationId = "app.gratum.kawaishiryuappnew"
        minSdk = 24
        targetSdk = 34
        versionCode = 2
        versionName = "1.0.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            applicationIdSuffix = ".debug"
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //Firebase
    implementation("com.google.firebase:firebase-analytics")
    implementation(platform("com.google.firebase:firebase-bom:32.8.1"))

    // Firebase Firestore, Storage, Auth
    implementation("com.google.firebase:firebase-firestore-ktx")
    implementation("com.google.firebase:firebase-storage-ktx")
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation("com.google.firebase:firebase-auth")

//    // Google Play Services Auth
//    implementation("com.google.android.gms:play-services-auth")

    // Kotlin
    val n1 = "2.7.7"
    implementation("androidx.navigation:navigation-fragment-ktx:$n1")
    implementation("androidx.navigation:navigation-ui-ktx:$n1")

    // Tools
    implementation("com.github.bumptech.glide:glide:4.14.2")
    annotationProcessor("com.github.bumptech.glide:compiler:4.14.2")
    implementation("de.hdodenhof:circleimageview:3.1.0") // Imagen circular
    implementation("com.squareup.picasso:picasso:2.8")
    implementation("com.hbb20:ccp:2.7.0") // InputNumber
    implementation("com.airbnb.android:lottie:6.0.0") // Animation
    implementation ("io.github.florent37:shapeofview:1.4.7")//ShapeOfView
    implementation("me.relex:circleindicator:2.1.6") //Circle Indicator
    implementation("org.imaginativeworld.whynotimagecarousel:whynotimagecarousel:2.1.0") //Carousel

    // ViewModel and LiveData
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")

    // ViewModel MVVM
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")

    // Corrutinas
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.7.1")

    //Implementation JSON
    implementation("com.google.code.gson:gson:2.9.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")

    //Datastore preference
    implementation("androidx.datastore:datastore-preferences:1.0.0")
}