package com.sabinetek.vesta.data.api

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sabinetek.vesta.TestFileUtils.MOCK_SERVER_PORT
import com.sabinetek.vesta.TestFileUtils.getMockResponse
import com.sabinetek.vesta.data.api.model.req.Sms
import com.sabinetek.vesta.di.ApiConfigModule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.runBlocking
import com.google.common.truth.Truth.assertThat
import com.sabinetek.vesta.data.api.model.req.Login
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import java.net.HttpURLConnection
import javax.inject.Inject

@HiltAndroidTest
@Config(application = HiltTestApplication::class, manifest = Config.NONE)
@RunWith(AndroidJUnit4::class)
class AuthServiceTest {
    @get:Rule(order = 0)
    internal val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var sut: AuthService

    private val server = MockWebServer()


    @Before
    fun setUp() {
        hiltRule.inject()

        server.apply {
            dispatcher = object : Dispatcher() {
                override fun dispatch(request: RecordedRequest): MockResponse {
                    request.path.orEmpty().apply {
                        return when {
                            contains(other = "user/sms") -> getMockResponse(fileName = "/sms.json")
                            contains(other = "user/login") -> getMockResponse(fileName = "/login.json")
                            else -> MockResponse().setResponseCode(code = HttpURLConnection.HTTP_NOT_FOUND)
                        }
                    }
                }
            }

            start(MOCK_SERVER_PORT)
        }
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun testAuthSms() = runBlocking {
        val response = sut.sendSms(Sms("18501052331"))
        val body = response.body()!!
        assertThat(body.code == 0).isTrue()
    }

    @Test
    fun testAuthLogin() = runBlocking {
        val response = sut.login(Login("18501052331","123123"))
        val body = response.body()!!
        assertThat(body.code == 0).isTrue()
    }
}