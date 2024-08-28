package com.example.myandroidproject.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.myandroidproject.core.data.Result
import com.example.myandroidproject.core.domain.model.listnewsmodel.ListNewsModel
import com.example.myandroidproject.core.domain.usecase.DataUseCase
import com.example.myandroidproject.presentation.viewmodel.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.any
import org.mockito.Mockito.anyString
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mockitoRule: MockitoRule = MockitoJUnit.rule()

    private lateinit var mainViewModel: MainViewModel

    @Mock
    private lateinit var dataUseCase: DataUseCase

    @Mock
    private lateinit var listObserver: Observer<List<ListNewsModel>>

    @Mock
    private lateinit var errorObserver: Observer<String>

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        mainViewModel = MainViewModel(dataUseCase)
        mainViewModel.getListLiveData().observeForever(listObserver)
        mainViewModel.errorListLiveData().observeForever(errorObserver)
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getListData success test`() = runTest {
        val mockList = listOf(ListNewsModel("1", "title", "description", "bannerUrl", 1234, 1))
        `when`(dataUseCase.getListData()).thenReturn(Result.Success(mockList))
        mainViewModel.getListData().join()
        verify(listObserver).onChanged(mockList)
        verify(errorObserver, never()).onChanged(anyString())
    }

    @Test
    fun `getListData error test`() = runTest {
        val errorMessage = "Error occurred"
        `when`(dataUseCase.getListData()).thenReturn(Result.Error(errorMessage))
        mainViewModel.getListData().join()
        verify(errorObserver).onChanged(errorMessage)
        verify(listObserver, never()).onChanged(any())
    }
}