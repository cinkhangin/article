package com.naulian.article

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.naulian.anhance.*
import com.naulian.article.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {
    private var viewBinding: FragmentMainBinding? = null
    private val binding get() = viewBinding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = FragmentMainBinding.bind(view)

        initialize()
        binding.loadUi()
        loadData { observeChanges() }
    }

    private fun initialize() {

    }

    private fun FragmentMainBinding.loadUi() {

    }

    private fun CoroutineScope.observeChanges() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }
}