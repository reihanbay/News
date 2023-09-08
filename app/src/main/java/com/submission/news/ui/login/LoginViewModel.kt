package com.submission.news.ui.login

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.submission.news.R
import com.submission.news.data.firebase.FirebaseRepository
import com.submission.news.utils.api.Result
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect


class LoginViewModel(val repository: FirebaseRepository) : ViewModel() {
    private var _isLogin = MutableLiveData<Result<AuthResult>>()
    val loginResult = _isLogin as LiveData<Result<AuthResult>>

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private var _hasLoginSession = MutableLiveData<Boolean>()
    val hasLoginSession = _hasLoginSession as LiveData<Boolean>

    fun checkLogin() : Boolean {
        var isSignIn = false
        viewModelScope.launch {
            isSignIn = repository.checkSignIn()
        }

        return isSignIn
    }

    fun logout() : Boolean {
        viewModelScope.launch {
           repository.logout()
        }
        _hasLoginSession.value = !checkLogin()
        return !checkLogin()
    }

    fun register(email: String, password: String) {
        _isLogin.value = Result.loading()
        viewModelScope.launch {
            var result : Task<AuthResult>
            withContext(Dispatchers.IO) {
                result = repository.signUpFirebase(email, password)
            }
            if (result.isSuccessful) {
                Log.d("TAG", "login: Success")
                _isLogin.value = Result.success(result.result!!)
            } else {
                Log.w("TAG", "signUpWithEmail:failure", result.exception)
                _isLogin.value = Result.fail(result.exception?.localizedMessage ?: "Authentication Failed",
                    result.exception?.message
                )
            }
        }
    }
    fun login(email: String, password: String) {
        _isLogin.value = Result.loading()
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                repository.signInFirebase(email, password)
            }
            if (result.isSuccessful) {
                Log.d("TAG", "login: Success")
                _isLogin.value = Result.success(result.result!!)
            } else {
                if (result.exception.toString().contains("FirebaseAuthInvalidUserException")) register(email, password)
                else {
                    Log.w("TAG", "signInWithEmail:failure ${result.exception} ", result.exception)
                    _isLogin.value = Result.fail(
                        result.exception?.localizedMessage ?: "Authentication Failed",
                        result.exception?.message
                    )

                }
            }
        }

    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(emailError =  R.string.invalid_email)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains("@")) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}