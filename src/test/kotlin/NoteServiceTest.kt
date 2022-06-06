import org.junit.Test

import org.junit.Assert.*

class NoteServiceTest {

    @Test
    fun add() {
        NoteService.add(Note(id =1, 2, "Напоминание", "Полезный навык", 4062022, comments = mutableListOf<Comment>()))
        val result = NoteService.notes.size
        assertEquals(1, result)
    }

    @Test
    fun delete() {
        NoteService.delete(Note(1, 2, "Напоминание", "Полезный навык", 4062022, comments = mutableListOf<Comment>()))
        val result = NoteService.notes.size
        assertEquals(0, result)
    }

    @Test
    fun edit() {
        NoteService.add(Note(1, 2, "Напоминание", "Полезный навык", 4062022, comments = mutableListOf()))
        val result = NoteService.edit((Note(1, 2, "Напоминание", "Отмена", 4062022, comments = mutableListOf<Comment>())))
        assertTrue(result)
    }
}