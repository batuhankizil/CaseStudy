package com.example.study

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.study.databinding.ActivityMainBinding
import com.example.study.sharedPreferences.LoginDataSource
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    @Inject
    lateinit var loginDataSource: LoginDataSource
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController

        if (loginDataSource.isLoggedIn) {
            navController.navigate(R.id.homePageFragment)
        } else {
            navController.navigate(R.id.loginFragment)
        }
    }
}