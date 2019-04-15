package com.qxj.multichannel.navigation

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.qxj.commondata.paging.MainViewModel
import com.qxj.commondata.paging.StudentAdapter
import com.qxj.multichannel.R
import kotlinx.android.synthetic.main.fragment_blank.*

class BlankFragment : Fragment() {


    private val viewModel by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                    MainViewModel(activity!!.application) as T
        }).get(MainViewModel::class.java)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_blank, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        to_plus.setOnClickListener {
            //点击跳转到page2
            Navigation.findNavController(it).navigate(R.id.action_blankFragment_to_plusOneFragment)
            //Bundle 传递参数
            //NavOptions 配置动画
        }

        val studentAdapter = StudentAdapter()
        recycler_view.adapter = studentAdapter

        viewModel.allStudents.observe(this, Observer(studentAdapter::submitList))
    }

}
