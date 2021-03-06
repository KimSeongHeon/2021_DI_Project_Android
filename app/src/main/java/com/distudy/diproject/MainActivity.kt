package com.distudy.diproject

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.distudy.diproject.common.Activity.BaseActivity
import com.distudy.diproject.common.URLProvider
import com.distudy.diproject.databinding.ActivityMainBinding
import com.distudy.diproject.ui.UserListFragment
import com.distudy.diproject.utils.SecureInfoUtil
import com.distudy.diproject.viewModel.OAuthViewModel
import com.distudy.diproject.viewModel.UserListViewModel
import com.distudy.diproject.viewModel.ViewModelFactory
import javax.inject.Inject

class MainActivity : BaseActivity() {
    @Inject
    lateinit var urlProvider: URLProvider

    @Inject
    lateinit var myViewModelFactory: ViewModelFactory

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var oAuthViewModel: OAuthViewModel
    private lateinit var userListViewModel: UserListViewModel

    private lateinit var requestActivity: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        injector.inject(this)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        bindActivityLauncher()
        oAuthViewModel = ViewModelProvider(this, myViewModelFactory).get(OAuthViewModel::class.java)
        supportFragmentManager.beginTransaction().add(R.id.frame_content, UserListFragment()).commit()
        initViewModel()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.actionbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_settings -> {
                val intent = createIntent()
                requestActivity.launch(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initViewModel() {
        oAuthViewModel.tokenLiveData.observe(this, Observer { token ->
            binding.tokenTestText.text = token
        })
    }

    private fun createIntent(): Intent {
        val uriString =
            urlProvider.getOAuthUrl() + OAUTH_PATH + QUERY_START + CLIENT_ID_KEY + EQUAL + "${SecureInfoUtil.OAuth_ID}" + "&" + REDIRECT_KEY + EQUAL + SCHEME
        return Intent(Intent.ACTION_VIEW, Uri.parse(uriString))
    }

    private fun bindActivityLauncher() {
        requestActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            val uri = it.data?.data
            uri?.let {
                uri.getQueryParameter(CODE_KEY)?.let { code ->
                    oAuthViewModel.getAccessToken(code)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()

        val uri = intent.data
        uri?.let {
            uri.getQueryParameter(CODE_KEY)?.let { code ->
                oAuthViewModel.getAccessToken(code)
            }
        }
    }

    companion object {
        private const val OAUTH_PATH = "authorize"
        private const val QUERY_START = "?"
        private const val EQUAL = "="
        private const val CLIENT_ID_KEY = "client_id"
        private const val CODE_KEY = "code"
        private const val REDIRECT_KEY = "redirect_uri"
        private const val SCHEME = "com.distudy.diproject://aaaa"
    }
}