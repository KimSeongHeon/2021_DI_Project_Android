package com.distudy.diproject

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.distudy.diproject.common.AccessTokenController
import com.distudy.diproject.data.AccessToken
import com.distudy.diproject.data.UserProfileInfo
import com.distudy.diproject.repository.Repository
import com.distudy.diproject.viewModel.UserListViewModel
import io.reactivex.rxjava3.core.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.junit.MockitoRule

@RunWith(MockitoJUnitRunner::class)
class UserListViewModelTest {
    @get:Rule
    var rule: MockitoRule = MockitoJUnit.rule()

    @JvmField
    @Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @InjectMocks lateinit var viewModel: UserListViewModel
    @Mock lateinit var accessTokenController: AccessTokenController
    @Mock lateinit var repository: Repository

    private var fakeUsersList = listOf<UserProfileInfo>()
    private var fakeAccessToken = AccessToken("", "", "")

    //when은 메소드 호출 조건, 그리고 thenReturn은 그 조건을 충족할 때 리턴값을 지정
    @Before
    fun setup() {
        viewModel = UserListViewModel(repository)
        viewModel.accessTokenController = AccessTokenController()

        `when`(repository.loadAllUserList(since = 0, per_page = 0)).thenReturn(Single.just(fakeUsersList))
    }

    @Test
    fun `viewModel의 loadAllUserList 호출하면 repository의 loadAllUserList 호출되는지 확인`() {
        viewModel.loadUserList(0, 0)
        Mockito.verify(repository).loadAllUserList(since = 0, per_page = 0)
    }

    @Test
    fun `userList를 올바르게 받아왔을 때 liveData에 값을 저장하는지 확인`() {
        viewModel.loadUserList(0, 0)
        viewModel.userList.getOrAwaitValue()
        Assert.assertEquals(viewModel.userList.value, fakeUsersList)
    }
}