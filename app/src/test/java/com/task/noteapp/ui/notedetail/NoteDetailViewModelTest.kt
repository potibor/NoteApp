package com.task.noteapp.ui.notedetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.task.noteapp.data.model.NoteModel
import com.task.noteapp.domain.NoteDeleteUseCase
import com.task.noteapp.domain.NoteDetailUseCase
import com.task.noteapp.domain.NoteUpdateUseCase
import com.task.noteapp.testutil.CoroutineTestRule
import com.task.noteapp.testutil.Failure
import com.task.noteapp.testutil.functional.Either
import com.task.noteapp.testutil.functional.now
import com.task.noteapp.testutil.getOrAwaitValue
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.spyk
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.*

class NoteDetailViewModelTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @get:Rule
    val testInstantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var noteDetailUseCase: NoteDetailUseCase

    @MockK
    lateinit var noteDeleteUseCase: NoteDeleteUseCase

    @MockK
    lateinit var noteUpdateUseCase: NoteUpdateUseCase

    private lateinit var viewModel: NoteDetailViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        viewModel =
            spyk(NoteDetailViewModel(noteDetailUseCase, noteDeleteUseCase, noteUpdateUseCase))
    }

    @Test
    fun `get note model detail when success`() {
        coroutineTestRule.runBlocking {
            val noteModelResponse = mockNoteModel()

            coEvery {
                noteDetailUseCase.run(any())
            } returns Either.Right(noteModelResponse)

            viewModel.getNoteDetail(0)

            assertThat(viewModel.noteModel.value).isEqualTo(noteModelResponse)
        }
    }

    @Test
    fun `get note model detail when failure`() {
        coroutineTestRule.runBlocking {
            val mockedResponse = Failure.IgnorableError

            coEvery {
                noteDetailUseCase.run(any())
            } returns Either.Left(mockedResponse)

            viewModel.getNoteDetail(0)

            assertThat(viewModel.failureLiveData.value).isEqualTo(mockedResponse)
        }
    }

    @Test
    fun `edit note when success`() {
        coroutineTestRule.runBlocking {
            viewModel.noteModel.value = mockNoteModel()

            coEvery {
                noteUpdateUseCase.run(any())
            } returns Either.Right(mockk())

            viewModel.editNote()

            val navigationResult =
                viewModel.navigateBackLiveData.getOrAwaitValue().getContentIfNotHandled()
            assertNotNull(navigationResult)
        }
    }

    @Test
    fun `edit note when failure`() {
        coroutineTestRule.runBlocking {
            viewModel.noteModel.value = mockNoteModel()
            val mockedResponse = Failure.IgnorableError

            coEvery {
                noteUpdateUseCase.run(any())
            } returns Either.Left(mockedResponse)

            viewModel.editNote()

            assertThat(viewModel.failureLiveData.value).isEqualTo(mockedResponse)
        }
    }

    @Test
    fun `delete note when success`() {
        coroutineTestRule.runBlocking {
            viewModel.noteModel.value = mockNoteModel()

            coEvery {
                noteDeleteUseCase.run(any())
            } returns Either.Right(mockk())

            viewModel.deleteNote()

            val navigationResult =
                viewModel.navigateBackLiveData.getOrAwaitValue().getContentIfNotHandled()
            assertNotNull(navigationResult)
        }
    }

    @Test
    fun `delete note when failure`() {
        coroutineTestRule.runBlocking {
            viewModel.noteModel.value = mockNoteModel()
            val mockedResponse = Failure.IgnorableError

            coEvery {
                noteDeleteUseCase.run(any())
            } returns Either.Left(mockedResponse)

            viewModel.deleteNote()

            assertThat(viewModel.failureLiveData.value).isEqualTo(mockedResponse)
        }
    }
    private fun mockNoteModel(
        id: Int = 0,
        title: String? = "Title",
        description: String? = "Description",
        createdDate: String = Date().now(),
        image: String? = null,
        isUpdated: Boolean = false
    ) = NoteModel(id, title, description, createdDate, image, isUpdated)
}