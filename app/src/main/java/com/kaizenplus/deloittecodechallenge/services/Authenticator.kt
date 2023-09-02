package com.kaizenplus.deloittecodechallenge.services

import android.accounts.AbstractAccountAuthenticator
import android.accounts.Account
import android.accounts.AccountAuthenticatorResponse
import android.accounts.AccountManager
import android.accounts.NetworkErrorException
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import com.kaizenplus.deloittecodechallenge.ui.screen.login.MainActivity


/*
 * Implement AbstractAccountAuthenticator and stub out all
 * of its methods
 */
class Authenticator(private val context: Context) // Simple constructor
    : AbstractAccountAuthenticator(context) {

    // Editing properties is not supported
    override fun editProperties(r: AccountAuthenticatorResponse, s: String): Bundle {
        throw UnsupportedOperationException()
    }

    // Don't add additional accounts
    @Throws(NetworkErrorException::class)
    override fun addAccount(
        r: AccountAuthenticatorResponse,
        s: String,
        s2: String,
        strings: Array<String>,
        bundle: Bundle
    ): Bundle?
    {

        val intent = Intent(context, MainActivity::class.java)
        intent.putExtra("ARG_ACCOUNT_TYPE", s)
        intent.putExtra("ARG_AUTH_TYPE", s2)
        intent.putExtra("ARG_IS_ADDING_NEW_ACCOUNT", true)
        intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, r)
        val bundle = Bundle()
        bundle.putParcelable(AccountManager.KEY_INTENT, intent)
        return bundle

    }

    // Ignore attempts to confirm credentials
    @Throws(NetworkErrorException::class)
    override fun confirmCredentials(
        r: AccountAuthenticatorResponse,
        account: Account,
        bundle: Bundle
    ): Bundle?
    {
        return null
    }

    @Throws(NetworkErrorException::class)
    override fun getAuthToken(
        response: AccountAuthenticatorResponse?,
        account: Account,
        authTokenType: String?,
        options: Bundle?
    ): Bundle? {

        // Extract the username and password from the Account Manager, and ask
        // the server for an appropriate AuthToken.
        val am = AccountManager.get(context)
        var authToken = am.peekAuthToken(account, authTokenType)

        // Lets give another try to authenticate the user
        if (TextUtils.isEmpty(authToken)) {
            val password = am.getPassword(account)
            if (password != null) {
//                authToken = sServerAuthenticate.userSignIn(account.name, password, authTokenType)
            }
        }

        // If we get an authToken - we return it
        if (!TextUtils.isEmpty(authToken)) {
            val result = Bundle()
            result.putString(AccountManager.KEY_ACCOUNT_NAME, account.name)
            result.putString(AccountManager.KEY_ACCOUNT_TYPE, account.type)
            result.putString(AccountManager.KEY_AUTHTOKEN, authToken)
            return result
        }

        // If we get here, then we couldn't access the user's password - so we
        // need to re-prompt them for their credentials. We do that by creating
        // an intent to display our AuthenticatorActivity.
        val intent = Intent(context, MainActivity::class.java)
        intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response)
        intent.putExtra("ARG_ACCOUNT_TYPE", account.type)
        intent.putExtra("ARG_AUTH_TYPE", authTokenType)
        val bundle = Bundle()
        bundle.putParcelable(AccountManager.KEY_INTENT, intent)
        return bundle
    }

    // Getting a label for the auth token is not supported
    override fun getAuthTokenLabel(s: String): String {
        throw UnsupportedOperationException()
    }

    // Updating user credentials is not supported
    @Throws(NetworkErrorException::class)
    override fun updateCredentials(
        r: AccountAuthenticatorResponse,
        account: Account,
        s: String,
        bundle: Bundle
    ): Bundle {
        throw UnsupportedOperationException()
    }

    // Checking features for the account is not supported
    @Throws(NetworkErrorException::class)
    override fun hasFeatures(
        r: AccountAuthenticatorResponse,
        account: Account,
        strings: Array<String>
    ): Bundle {
        throw UnsupportedOperationException()
    }
}