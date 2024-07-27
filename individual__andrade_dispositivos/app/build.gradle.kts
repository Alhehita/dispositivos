plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)

    //añadidos
    //Volverlo a pasar a TOM
    id("com.google.devtools.ksp")

    id("androidx.navigation.safeargs.kotlin")

    //FIREBASE
   // id("com.android.application")   --> Ya no necesitamos porque ya esta arriba
    id("com.google.gms.google-services")
}

android {
    namespace = "com.andrade.dennisse.proyectoandrade"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.andrade.dennisse.proyectoandrade"
        minSdk = 33
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    //añadido
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }




}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.firebase.auth.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)


    //añadidos y pasarlos al TOM

    implementation(libs.bundles.retrofit)
    implementation(libs.coil)
    implementation(libs.lottieLib)

    // RecyclerView
    implementation(libs.swiperefreshlayout)
    implementation(libs.coordinatorlayout)

    // Room
    implementation(libs.room.runtime)
    annotationProcessor(libs.room.compiler)
    ksp(libs.room.compiler.ksp)

    implementation(libs.androidx.biometric)
    implementation(libs.androidx.splashscreen)

    implementation(libs.androidx.datastore)
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)


    implementation(libs.androidx.fragment)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.lifecycle.livedata)



    //Import FIREBASE BoM    SDK
    implementation(platform("com.google.firebase:firebase-bom:33.1.1"))
    implementation("com.google.firebase:firebase-analytics")


    //FIRESTORE
    implementation("com.google.firebase:firebase-firestore")

}