package com.gmalija.access.application.home

import android.view.View
import com.gmalija.access.R
import com.gmalija.access.application.base.BaseFragment
import com.gmalija.access.databinding.FragmentHomeBinding
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment: BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    override val mViewModel: HomeViewModel by viewModel()
    override fun getLayoutResId() = R.layout.fragment_home

    override fun init(view: View) { }

}