package com.liveloc

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import java.util.*
import com.facebook.AccessToken
import com.facebook.AccessTokenTracker
import com.facebook.login.LoginManager
import android.content.pm.PackageManager
import android.content.pm.PackageInfo
import android.util.Log
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import android.support.v4.content.ContextCompat.startActivity
import com.liveloc.rest.ExampleApi


class MainActivity : AppCompatActivity() {

    private val EMAIL = "email"
    private val PROFILE = "public_profile"

    /*
        Synthetic Binding Activity Elements
     */
    lateinit var callbackManager : CallbackManager
    lateinit var loginButton : LoginButton
    lateinit var askFortoken : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var exampleApi = ExampleApi()

        loginButton = findViewById<LoginButton>(R.id.loginButton)
        callbackManager = CallbackManager.Factory.create();

        if (AccessToken.getCurrentAccessToken() != null) {
            login()
        }else{
            Toast.makeText(applicationContext , "Cannot Login To Server" , Toast.LENGTH_LONG).show()
        }


        loginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                val accessToken = loginResult.accessToken
                useLoginInformation(accessToken)
            }
            override fun onCancel() {}
            override fun onError(error: FacebookException) {}
        })

        loginButton.setReadPermissions(Arrays.asList("email", "public_profile"))
        callbackManager = CallbackManager.Factory.create()
        loginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                handleAccessToke(loginResult.accessToken)
            }
            override fun onCancel() {}
            override fun onError(error: FacebookException) {}
        })
        logReleaseKey()

    }

    private fun handleAccessToke(accessToken: AccessToken?) {
        AccessToken.setCurrentAccessToken(accessToken)
        login()
    }

    private fun login() {
        val liveLocActivity = LiveLoc.newIntent(this)
        startActivity(liveLocActivity)
    }

    private fun logReleaseKey() {
        try {
            val info = packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES)
            for (signature in info.signatures) {
                val md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                var hash = android.util.Base64 .encodeToString(md.digest(), android.util.Base64 .DEFAULT)
                Log.d("KeyHash:", hash )
            }
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
    }

    public override fun onActivityResult(requestCode: Int, resulrCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resulrCode, data)
        super.onActivityResult(requestCode, resulrCode, data)
    }

    fun useLoginInformation(accessToken: AccessToken){
        var i = 1
    }

}


