package com.distudy.diproject.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.distudy.diproject.common.Fragment.BaseFragment
import com.distudy.diproject.data.UserProfileInfo
import com.distudy.diproject.databinding.FragmentUserListBinding
import com.distudy.diproject.viewModel.OAuthViewModel
import com.distudy.diproject.viewModel.UserListViewModel
import com.distudy.diproject.viewModel.ViewModelFactory
import javax.inject.Inject

class UserListFragment : BaseFragment() {
    private var _binding: FragmentUserListBinding? = null
    private val binding get() = _binding!!
    private var userList = arrayListOf<UserProfileInfo>()
    private val adapter: UserListRecyclerAdapter by lazy {
        UserListRecyclerAdapter(requireContext(), userList)
    }

    @Inject
    lateinit var myViewModelFactory: ViewModelFactory

    private lateinit var userListViewModel: UserListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
        injector.inject(this)
        userListViewModel = ViewModelProvider(this, myViewModelFactory).get(UserListViewModel::class.java)
        userListViewModel.loadUserList(0, 0)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(TAG, "onCreateView")
        _binding = FragmentUserListBinding.inflate(layoutInflater)
        initViewModel()
        initAdapter()
        return binding.root
    }

    private fun initViewModel() {
        userListViewModel.userList.observe(viewLifecycleOwner, Observer<List<UserProfileInfo>> {
            userList.addAll(it)
            adapter.notifyDataSetChanged()
        })
    }

    private fun initAdapter() {
        val layoutManager = LinearLayoutManager(requireContext())
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.userListRecyclerview.layoutManager = layoutManager
        binding.userListRecyclerview.adapter = adapter
    }

    companion object {
        private const val TAG = "UserListFragment"
    }
}