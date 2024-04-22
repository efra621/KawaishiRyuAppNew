package app.gratum.kawaishiryuappnew.ui.fragment.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import app.gratum.kawaishiryuappnew.R
import app.gratum.kawaishiryuappnew.core.ViewModelState
import app.gratum.kawaishiryuappnew.data.model.UserModel
import app.gratum.kawaishiryuappnew.databinding.FragmentLoginOrRegisterBinding
import app.gratum.kawaishiryuappnew.ui.activity.MainMenuHostActivity
import app.gratum.kawaishiryuappnew.ui.viewmodel.LoginScreenViewModel
import app.gratum.kawaishiryuappnew.ui.viewmodel.RegisterUserViewModel
import app.gratum.kawaishiryuappnew.util.SnackbarUtils
import app.gratum.kawaishiryuappnew.util.SocialMediaUtils
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class LoginOrRegisterFragment : Fragment(R.layout.fragment_login_or_register) {

    private lateinit var binding: FragmentLoginOrRegisterBinding

    private val viewModel by viewModels<LoginScreenViewModel>()
    private val viewModel2 by viewModels<RegisterUserViewModel>()

    //GMAIL
//    private val startForResult =
//        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//            if (result.resultCode == Activity.RESULT_OK) {
//                val data: Intent? = result.data
//                val task = GoogleSignIn.getSignedInAccountFromIntent(data)
//
//                try {
//                    val account = task.getResult(ApiException::class.java)
//
//                    viewLifecycleOwner.lifecycleScope.launch {
//
//                        viewModel.signInUserWithGoogle(account)
//
//                        viewModel.signInUserWithGoogle.collect { signInState ->
//                            when (signInState) {
//                                is ViewModelState.SignInUserSuccesfully -> {
//                                    Log.d("???", "LoadingOrRegisterGoogle: SignInSuccessfully")
//
//                                    val idUser = signInState.user.id
//                                    val isExist = viewModel2.checkUserExistence(idUser)
//
//                                    Log.d("???", "LoginOrRegister: val isExist $isExist")
//                                    if (isExist) {
//                                        val intent = Intent(
//                                            requireContext(),
//                                            MainMenuHostActivity::class.java
//                                        )
//                                        startActivity(intent)
//                                        requireActivity().finish()
//
//                                    } else {
//                                        val model = UserModel(
//                                            name = signInState.user.name,
//                                            apellido = signInState.user.apellido,
//                                            email = signInState.user.email,
//                                            id = signInState.user.id,
//                                            rol = "Alumno"
//                                        )
//                                        viewModel2.registerUserCollectionDb(model)
//
//                                        val intent = Intent(
//                                            requireContext(),
//                                            MainMenuHostActivity::class.java
//                                        )
//                                        startActivity(intent)
//                                        requireActivity().finish()
//                                    }
//                                }
//
//                                is ViewModelState.Loading2 -> {
//                                    Log.d("???", "LoadingOrRegisterGoogle: Loading User...")
//                                }
//
//                                is ViewModelState.Error2 -> {
//                                    Log.d("???", "LoadingOrRegisterGoogle: User not found...")
//                                    //SnackbarUtils.showCustomSnackbar(view, layoutInflater, 2, "Usuario o contraseña incorrecta")
//                                }
//
//                                else -> {}
//                            }
//                        }
//                    }
//                } catch (e: ApiException) {
//                    Log.d("???", "LoadingOrRegisterGoogle: Error en la api: $e")
//                }
//            }
//        }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentLoginOrRegisterBinding.bind(view)

        events()
        starFlow(view)
    }

    //
    private fun starFlow(view: View) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.signInUser.collect() {
                    when (it) {
                        is ViewModelState.SignInUserSuccesfully -> {
                            Log.d("???", "LoadingOrRegister: SignInSuccesfully")
                            SnackbarUtils.showCustomSnackbar(
                                view,
                                layoutInflater,
                                1,
                                "Usuario cargado con exito"
                            )

                            // Agrega un retraso antes de iniciar la nueva actividad
                            delay(1500) // Ajusta el tiempo de espera según sea necesario

                            val intent = Intent(requireContext(), MainMenuHostActivity::class.java)
                            startActivity(intent)
                        }

                        is ViewModelState.Loading2 -> {
                            Log.d("???", "LoadingOrRegister: Loading User...")
                        }

                        is ViewModelState.Error2 -> {
                            Log.d("???", "LoadingOrRegister: User not found...")
                            SnackbarUtils.showCustomSnackbar(
                                view,
                                layoutInflater,
                                2,
                                "Usuario o contraseña incorrecta"
                            )
                        }

                        else -> {}
                    }
                }
            }
        }
    }

    private fun signIn(email: String, password: String) {
        viewModel.signIn(UserModel(email = email, password = password))
    }

    private fun events() = with(binding)
    {
        etUsuario.text.toString()
        etPassword.text.toString()

        btnLogin.setOnClickListener {
            signIn(etUsuario.text.toString(), etPassword.text.toString())
        }

        btnRegister.setOnClickListener {
            requireView().findNavController()
                .navigate(R.id.action_loginOrRegisterFragment2_to_registerFragment)
        }

        binding.btnGoogle.setOnClickListener {
           // signInGoogle()
        }

        binding.tvPolPriv.setOnClickListener {
            SocialMediaUtils.openGoogleDocs(requireContext())
        }

    }

//    private fun signInGoogle() {
//        val apiKey = BuildConfig.API_KEY
//
//        // Configuración de opciones de inicio de sesión con Google
//        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestIdToken(apiKey)
//            .requestEmail()
//            .build()
//
//        val googleSignInClient = GoogleSignIn.getClient(requireContext(), gso)
//        val signInIntent = googleSignInClient.signInIntent
//        startForResult.launch(signInIntent)
//    }

}