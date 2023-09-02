package com.hazem.datalayer.repoImpl

import android.accounts.Account
import android.accounts.AccountManager
import com.hazem.corelayer.model.User
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class Authenticator @Inject constructor(private val mAccountManager: AccountManager) {
    private suspend fun getAccount(name: String): Account {
        return coroutineScope {
            Account(name, "com.hazem")//from gradle variables
        }

    }

    suspend fun login(user: User) {
        mAccountManager.setPassword(getAccount(user.fullName.toString()), user.password)
    }

    suspend fun removeAccount(name: String) {
        mAccountManager.removeAccountExplicitly(getAccount(name))

    }

    suspend fun register(user: User) {
        val authtoken = "temp auth test with name ${user.fullName}"
        val authtokenType: String = "mAuthTokenType"
        mAccountManager.addAccountExplicitly(
            getAccount(user.fullName.toString()),
            user.password,
            null
        )
        mAccountManager.setAuthToken(getAccount(user.fullName.toString()), authtokenType, authtoken)
    }
}