package me.mitul.todo.model.service.module

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.BuildConfig
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {
    private const val HOST = "192.168.0.111"
    private const val AUTH_PORT = 9099
    private const val FIRESTORE_PORT = 8080
    private const val USE_EMULATOR = BuildConfig.DEBUG

    @Provides
    fun auth(): FirebaseAuth = if (!USE_EMULATOR) Firebase.auth
    else Firebase.auth.also {
        it.useEmulator(HOST, AUTH_PORT)
    }

    @Provides
    fun firestore(): FirebaseFirestore = if (!USE_EMULATOR) Firebase.firestore
    else Firebase.firestore.also {
        it.useEmulator(HOST, FIRESTORE_PORT)
    }
}

// 10.0.2.2 is the special IP address to connect to the 'localhost' of the host computer from an Android emulator.
// FirebaseStorage.getInstance().useEmulator("10.0.2.2", 9199);
// FirebaseFunctions.getInstance().useEmulator("10.0.2.2", 5001);
// FirebaseAuth.getInstance().useEmulator("10.0.2.2", 9099);
// FirebaseFirestore.getInstance().useEmulator("10.0.2.2", 8080);
// FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder().setPersistenceEnabled(false).build();
// FirebaseFirestore.getInstance().setFirestoreSettings(settings);
