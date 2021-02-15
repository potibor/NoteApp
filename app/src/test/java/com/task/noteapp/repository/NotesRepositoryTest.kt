package com.task.noteapp.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.task.noteapp.data.datasource.NotesLocalDataSource
import com.task.noteapp.data.model.NoteModel
import com.task.noteapp.data.repository.NotesRepository
import com.task.noteapp.testutil.functional.now
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.*


@RunWith(JUnit4::class)
class NotesRepositoryTest {

    @get:Rule
    val testInstantTaskExecutorRule = InstantTaskExecutorRule()

    @RelaxedMockK
    lateinit var notesLocalDataSource: NotesLocalDataSource

    @InjectMockKs
    lateinit var notesRepository: NotesRepository

    @Before
    fun setUp() = MockKAnnotations.init(this)

    @Test
    fun `fetch Notes when title not null`() {
        runBlocking {
            val expectedResult = mockNoteList(2)

            coEvery {
                notesLocalDataSource.getAll()
            } returns expectedResult

            val actualResult = notesRepository.fetchNotes()

            assertThat(actualResult.size).isEqualTo(2)
        }
    }

    @Test
    fun `fetch Notes when title null`() {
        runBlocking {
            val expectedResult = mockNoteList(2, title = null)

            coEvery {
                notesLocalDataSource.getAll()
            } returns expectedResult

            val actualResult = notesRepository.fetchNotes()

            assertThat(actualResult.size).isEqualTo(0)
        }
    }

    @Test
    fun `add Note`() {
        runBlocking {
            val noteModel = mockNoteModel()

            notesRepository.addNote("Title", "Description", null)

            coVerify { notesLocalDataSource.add(noteModel) }
        }
    }

    @Test
    fun `get Note`() {
        runBlocking {
            val noteModel = mockNoteModel()

            coEvery { notesLocalDataSource.get(0) } returns noteModel

            val actualResult = notesRepository.getNote(0)
            val expectedResult = NoteModel(0, "Title", "Description")

            assertThat(actualResult).isEqualTo(expectedResult)
        }
    }

    @Test
    fun `delete Note`() {
        runBlocking {
            val noteModel = mockNoteModel()

            notesRepository.deleteNote(noteModel)

            coVerify { notesLocalDataSource.remove(noteModel) }
        }
    }

    @Test
    fun `update Note`() {
        runBlocking {
            val noteModel = mockNoteModel()

            notesRepository.update(noteModel)

            coVerify { notesLocalDataSource.update(noteModel) }
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

    @OptIn(ExperimentalStdlibApi::class)
    private fun mockNoteList(count: Int = 1, title: String? = "Title") = buildList {
        repeat(count) {
            add(mockNoteModel(title = title))
        }
    }

}