package org.lsposed.lspatch.config

import android.content.Intent
import org.lsposed.lspatch.lspApp
import org.lsposed.lspatch.manager.ModuleService
import org.lsposed.lspatch.ui.util.delegateStateOf
import org.lsposed.lspatch.ui.util.getValue
import org.lsposed.lspatch.ui.util.setValue

object Configs {

    private const val PREFS_KEYSTORE_PASSWORD = "keystore_password"
    private const val PREFS_KEYSTORE_ALIAS = "keystore_alias"
    private const val PREFS_KEYSTORE_ALIAS_PASSWORD = "keystore_alias_password"
    private const val PREFS_STORAGE_DIRECTORY = "storage_directory"
    private const val PREFS_DETAIL_PATCH_LOGS = "detail_patch_logs"
    private const val PREFS_KEEP_ALIVE = "keep_alive"

    enum class KeepAlive {
        OFF, FOREGROUND
    }

    var keyStorePassword by delegateStateOf(lspApp.prefs.getString(PREFS_KEYSTORE_PASSWORD, "123456")!!) {
        lspApp.prefs.edit().putString(PREFS_KEYSTORE_PASSWORD, it).apply()
    }

    var keyStoreAlias by delegateStateOf(lspApp.prefs.getString(PREFS_KEYSTORE_ALIAS, "key0")!!) {
        lspApp.prefs.edit().putString(PREFS_KEYSTORE_ALIAS, it).apply()
    }

    var keyStoreAliasPassword by delegateStateOf(lspApp.prefs.getString(PREFS_KEYSTORE_ALIAS_PASSWORD, "123456")!!) {
        lspApp.prefs.edit().putString(PREFS_KEYSTORE_ALIAS_PASSWORD, it).apply()
    }

    var storageDirectory by delegateStateOf(lspApp.prefs.getString(PREFS_STORAGE_DIRECTORY, null)) {
        lspApp.prefs.edit().putString(PREFS_STORAGE_DIRECTORY, it).apply()
    }

    var detailPatchLogs by delegateStateOf(lspApp.prefs.getBoolean(PREFS_DETAIL_PATCH_LOGS, true)) {
        lspApp.prefs.edit().putBoolean(PREFS_DETAIL_PATCH_LOGS, it).apply()
    }

    var keepAlive by delegateStateOf(KeepAlive.values()[lspApp.prefs.getInt(PREFS_KEEP_ALIVE, KeepAlive.OFF.ordinal)]) {
        lspApp.prefs.edit().putInt(PREFS_KEEP_ALIVE, it.ordinal).apply()
        lspApp.startService(Intent(lspApp, ModuleService::class.java))
    }
}
