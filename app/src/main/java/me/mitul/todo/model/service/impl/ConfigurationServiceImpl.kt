package me.mitul.todo.model.service.impl

import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.get
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import kotlinx.coroutines.tasks.await
import me.mitul.todo.BuildConfig
import me.mitul.todo.model.service.ConfigurationService
import me.mitul.todo.model.service.trace
import javax.inject.Inject
import me.mitul.todo.R.xml as AppConfig

class ConfigurationServiceImpl @Inject constructor() : ConfigurationService {
    private val remoteConfig
        get() = Firebase.remoteConfig

    init {
        if (BuildConfig.DEBUG) {
            val configSettings = remoteConfigSettings { minimumFetchIntervalInSeconds = 0 }
            remoteConfig.setConfigSettingsAsync(/* settings = */ configSettings)
        }
        remoteConfig.setDefaultsAsync(/* resourceId = */ AppConfig.remote_config_defaults)
    }

    override suspend fun fetchConfiguration(): Boolean = trace(FETCH_CONFIG_TRACE) {
        remoteConfig.fetchAndActivate().await()
    }

    override val isShowTaskEditButtonConfig: Boolean
        get() = remoteConfig[SHOW_TASK_EDIT_BUTTON_KEY].asBoolean()

    companion object {
        private const val SHOW_TASK_EDIT_BUTTON_KEY = "show_task_edit_button"
        private const val FETCH_CONFIG_TRACE = "fetchConfig"
    }
}
