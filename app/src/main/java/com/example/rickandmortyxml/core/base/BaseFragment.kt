package com.example.rickandmortyxml.core.base

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseFragment<T : BaseViewModel>(@LayoutRes layoutRes: Int) : Fragment(layoutRes) {
    abstract val viewModel: T

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initObservers()
        observeLifecycle()
    }

    private fun observeLifecycle() {
        lifecycle.addObserver(viewModel)
    }

    open fun initObservers() {
        observeUiState()
        observeMessage()
    }

    private fun observeUiState() {
        viewModel.uiState.observe(viewLifecycleOwner) {
            when (it) {
                UiState.PendingState -> onPendingState()
                UiState.IdleState -> onIdleState()
            }
        }
    }

    private fun observeMessage() {
        viewModel.message.observe(viewLifecycleOwner) {
            showToast(it)
        }
    }

    open fun initViews() {}
    open fun onPendingState() {}

    open fun onIdleState() {}

    private fun showToast(it: String) {
        Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
    }
}