package com.pankajkcodes.mvvmsignuplogin.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.pankajkcodes.mvvmsignuplogin.R
import com.pankajkcodes.mvvmsignuplogin.viewModel.AuthViewModel

class SignUpFragment : Fragment() {

    lateinit var email: EditText
    lateinit var pass: EditText
    lateinit var button: Button
    lateinit var signInText: TextView
    lateinit var authViewModel: AuthViewModel
    lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        authViewModel = ViewModelProvider(
            this, ViewModelProvider.AndroidViewModelFactory
                .getInstance(activity!!.application)
        )[AuthViewModel::class.java]

        authViewModel.userData?.observe(this, {
            if (it != null) {
                navController.navigate(R.id.action_signUpFragment_to_signInFragment)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        email = view.findViewById(R.id.sign_up_username)
        pass = view.findViewById(R.id.sign_up_password)
        button = view.findViewById(R.id.sign_up_btn)
        signInText = view.findViewById(R.id.sign_in_text)
        navController = Navigation.findNavController(view)
        signInText.setOnClickListener {
            navController.navigate(R.id.action_signUpFragment_to_signInFragment)
        }

        button.setOnClickListener {
            val email1 : String = email.text.toString().trim()
            val pass1 : String = pass.text.toString().trim()
            authViewModel.register(email1,pass1)

        }

    }
}