package com.distudy.diproject

import android.util.Log
import com.distudy.diproject.common.AccessTokenController
import com.distudy.diproject.data.AccessToken
import com.distudy.diproject.repository.Repository
import com.distudy.diproject.viewModel.OAuthViewModel
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.TestScheduler
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.TimeUnit

@RunWith(MockitoJUnitRunner::class)
class OAuthViewModelTest {
    //관심 객체 : 테스트 시점에 검증할 객체(viewModel) -> 상태 변화에 대한 테스트
    //외부 객체 : 관심 객체의 의존성에 해당하는 객체(Repository) -> 외부 객체의 함수 호출 여
    //모의 객체 : 실제 객체를 모방한 객체, 동일한 인터페이스를 가지지만 실제로 속은 비어 있음(Repository를 Mock해야함)
    //stub : 모의 객체의 실체를 임의로 정하는 행위 -> 모의 객체가 있다면 함수의 반환 값은? -> 이 반환값을 직접 적어주는 것을 stub이라고 함
    //https://greedy0110.tistory.com/56

    //viewModel의 외부 객체인 repository를 @mock을 통해 mocking
    @Mock lateinit var repository: Repository

    @Mock lateinit var accessTokenController: AccessTokenController

    @InjectMocks lateinit var viewModel: OAuthViewModel

    private val fakeAccessToken = AccessToken("", "", "")
    //@Before을 통해 viewModel을 초기화
    @Before
    fun setup() {
        //Mockito 어노테이션이 선언된 변수들을 객체화 시킴
        //deprecated..?
        MockitoAnnotations.initMocks(this)
        Mockito.mockStatic(Log::class.java)
//        viewModel = OAuthViewModel(repository)
//        viewModel = OAuthViewModel(mock(Repository::class.java))
//        viewModel = mock(OAuthViewModel::class.java)
//
//        viewModel.accessTokenHelper = accessTokenController

        Mockito.`when`(repository.getAccessToken("")).thenReturn(Single.just(fakeAccessToken))
    }

    //@Test를 통해 viewModel 테스트
    @Test
    fun `viewModel의 getAccessToken을 호출하면 repository의 getAccessToken이 호출되는지 확인`() {
        viewModel.getAccessToken("")
        verify(repository).getAccessToken("")
    }

    @Test
    fun `token 가져와 accessTokenController에 잘 저장하는지 확인`() {
        val testScheduler = TestScheduler()
        viewModel.getAccessToken("")
        testScheduler.advanceTimeTo(10, TimeUnit.SECONDS)
        Assert.assertEquals(viewModel.accessTokenHelper.currentToken, fakeAccessToken)
    }

    @After
    fun teardown() {
        RxJavaPlugins.reset()
        RxAndroidPlugins.reset()
    }
}