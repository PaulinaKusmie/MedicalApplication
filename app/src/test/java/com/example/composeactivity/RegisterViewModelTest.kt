package com.example.composeactivity

import com.example.composeactivity.api.UserAPI
import com.example.composeactivity.data.dto.ConfirmAccountRequest
import com.example.composeactivity.data.dto.ConfirmResponse
import com.example.composeactivity.repository.UserRepository
import com.example.composeactivity.viewmodel.RegisterViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import okhttp3.Dispatcher
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody

import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class RegisterViewModelTest {

    private lateinit var userRepository: UserRepository
    private lateinit var viewModel: RegisterViewModel

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup(){
        Dispatchers.setMain(testDispatcher)
        userRepository = mockk()
        viewModel = RegisterViewModel(userRepository)
    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }
        @Test
        fun `confirmUser when success - updates states correctly`() = runTest {

            viewModel.updateCode("123456")
            viewModel.updateEmail("test123@gmail.com")

            val mockResponse = ConfirmResponse(
                success = true,
                message = "Email confirmed"
            )

            coEvery {
                userRepository.confirmCode(any())
            } returns Response.success(mockResponse)

            viewModel.confirmUser()
            advanceUntilIdle() // wykonywanie coroutine

            val state = viewModel.uiStateCode.value

            assert(state.isSuccess == true){
                "Expected isSuccess to be true, but was ${state.isSuccess}"
            }

            assert(state.message == "Email confirmed"){
                "Expected message 'Email confirmed' to be true, but was ${state.message}"
            }

            assert(state.isLoading == false){
                "Expected isLoading to be false, but was ${state.isLoading}"
            }

        }

    @Test
    fun `confirmUser when email is empty - shows validation error message`() = runTest {
        viewModel.updateCode("123456")
        viewModel.updateEmail("")

        val mockResponse = ConfirmResponse(
            success = false,
            message = "Podaj email i poprawny kod"
        )

        coEvery {
            userRepository.confirmCode(any())
        } returns Response.success(mockResponse)

        viewModel.confirmUser()
        advanceUntilIdle() // wykonywanie coroutine

        val state = viewModel.uiStateCode.value
        assert(state.isSuccess == false){
            "Expected isSuccess to be false, but was ${state.isSuccess}"
        }

        assert(state.message == "Podaj email i poprawny kod"){
            "Expected message 'Podaj email i poprawny kod' to be true, but was ${state.message}"
        }

        assert(state.isLoading == false){
            "Expected isLoading to be false, but was ${state.isLoading}"
        }

    }

    @Test
    fun `confirmUser when code is not a number - shows validation error message`() = runTest {

        viewModel.updateCode("12ab56")
        viewModel.updateEmail("")

        val mockResponse = ConfirmResponse(
            success = false,
            message = "Podaj email i poprawny kod"
        )

        coEvery {
            userRepository.confirmCode(any())
        } returns Response.success(mockResponse)

        viewModel.confirmUser()
        advanceUntilIdle() // wykonywanie coroutine

        val state = viewModel.uiStateCode.value
        assert(state.isSuccess == false){
            "Expected isSuccess to be false, but was ${state.isSuccess}"
        }

        assert(state.message == "Podaj email i poprawny kod"){
            "Expected message 'Podaj email i poprawny kod' to be true, but was ${state.message}"
        }

        assert(state.isLoading == false){
            "Expected isLoading to be false, but was ${state.isLoading}"
        }

    }

    @Test
    fun `confirmUser when buissnes error - shows validation error message`() = runTest {
        viewModel.updateCode("654321")
        viewModel.updateEmail("test123@gmail.com")

        val mockResponse = ConfirmResponse(
            success = false,
            message = "Error code"
        )

        coEvery {
            userRepository.confirmCode(any())
        } returns Response.success(mockResponse)

        viewModel.confirmUser()
        advanceUntilIdle() // wykonywanie coroutine

        val state = viewModel.uiStateCode.value
        assert(state.isSuccess == false){
            "Expected isSuccess to be true, but was ${state.isSuccess}"
        }

        assert(state.message == "Error code"){
            "Expected message 'Error code' to be true, but was ${state.message}"
        }

        assert(state.isLoading == false){
            "Expected isLoading to be false, but was ${state.isLoading}"
        }

    }

    @Test
    fun `confirmUser when server error - shows validation error message`() = runTest {

        viewModel.updateCode("123456")
        viewModel.updateEmail("test123@gmail.com")


        coEvery {
            userRepository.confirmCode(any())
        } returns Response.error(
            500,
            "Internal Server Error"
                .toResponseBody("text/plain".toMediaType())
        )

        viewModel.confirmUser()
        advanceUntilIdle() // wykonywanie coroutine

        val state = viewModel.uiStateCode.value
        assert(state.isSuccess == false){
            "Expected isSuccess to be true, but was ${state.isSuccess}"
        }

        assert(state.message == "Coś poszło nie tak! Spróbuj ponownie"){
            "Expected message 'Coś poszło nie tak! Spróbuj ponownie' to be true, but was ${state.message}"
        }

        assert(state.isLoading == false){
            "Expected isLoading to be false, but was ${state.isLoading}"
        }

    }


}