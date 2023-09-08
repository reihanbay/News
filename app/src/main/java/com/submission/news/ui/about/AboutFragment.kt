package com.submission.news.ui.about

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.submission.news.databinding.FragmentAboutBinding
import com.submission.news.ui.login.LoginViewModel
import com.submission.news.utils.preference.Preference
import org.koin.androidx.viewmodel.ext.android.viewModel

class AboutFragment : Fragment() {

    private var binding : FragmentAboutBinding? = null
    private val bind get() = binding!!
    private val vm : LoginViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAboutBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind.btnLogout.text = if (vm.checkLogin())  "Logout" else "Login"
        bind.btnLogout.setOnClickListener {
            if (vm.checkLogin()) vm.logout()
            else findNavController().navigate(AboutFragmentDirections.actionAboutFragmentToLoginFragment())
        }
        initObservable()
    }
    private fun initObservable() {
        vm.hasLoginSession.observe(viewLifecycleOwner) {
            if (it) {
                Preference(requireContext()).clear()
                findNavController().popBackStack()
            }
        }
    }

}