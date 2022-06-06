import org.junit.Test

import org.junit.Assert.*

class CommentServiceTest {

    @Test
    fun add() {
        CommentService.add(Comment(1,2,2, "Нужно запомнить"))
        val result = CommentService.comments.size
        assertEquals(1,result)
    }

    @Test
    fun edit() {
        CommentService.add(Comment(1,2,2, "Нужно запомнить"))
        val  result = CommentService.edit(Comment(1,2,2, "Отмена"))
        assertTrue(result)
    }

    @Test
    fun delete() {
        CommentService.add(Comment(1,2,2, "Нужно запомнить"))
        val result = CommentService.delete(Comment(1,2,2, "Нужно запомнить"))
        assertTrue(result)
    }

    @Test
    fun getById(){
        NoteService.add(Note(id =1, 2, "Напоминание", "Полезный навык", 4062022, comments = CommentService.getById()))
        CommentService.add(Comment(1,2,2, "Коммент №1"))
        CommentService.add(Comment(1,1,2, "Коммент №2"))
        CommentService.add(Comment(1,2,2, "Коммент №3"))
        val result = CommentService.getById()
        assertEquals(CommentService.comments, result)

    }
}