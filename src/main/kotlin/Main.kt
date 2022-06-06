fun main() {
    NoteService.add(Note(1, 2, "Заголовок №1", "Заметка №1", 20220606, CommentService.getById()))
    NoteService.add(Note(1, 2, "Заголовок №2", "Заметка №2", 20220606, CommentService.getById()))
    NoteService.add(Note(1, 2, "Заголовок №3", "Заметка №3", 20220606, CommentService.getById()))

    NoteService.delete(Note(1, 2, "Заголовок №1", "Заметка №1", 20220606, CommentService.getById()))
    NoteService.edit(Note(2, 2, "Заголовок №3", "Заметка №2", 20220606, CommentService.getById()))

    CommentService.add(Comment(1, 2, 3, "Круто!", false))
    CommentService.add(Comment(3, 1, 3, "Отлично!", false))

    CommentService.edit((Comment(1, 1, 3, "Плохо!", false)))

    CommentService.delete(Comment(3, 2, 3, "Отлично!", false))
    println(NoteService.notes)
    println(CommentService.comments)
}

data class Note(
    val id: Int,
    val ownerId: Int,
    val title: String,
    val text: String,
    val date: Int,
    var comments: MutableList<Comment>? = CommentService.getById()
)

object NoteService : Service<Note>() {

    override fun add(note: Note) {
        val newNote = note.copy(id = getUniqueId())
        notes.add(newNote)
    }

    override fun delete(note: Note): Boolean {
        return notes.remove(note)
    }

    override fun edit(note: Note): Boolean {
        for ((index, item) in notes.withIndex()) {
            if (item.id == note.id) {
                notes[index] = note
                return true
            }
        }
        return false
    }

    override fun getById(): MutableList<Note> {
        for ((index, item) in notes.withIndex()) {
            if (item.id == notes[index].id) {
                notes
            }
        }
        return notes
    }

}

object CommentService : Service<Comment>() {

    override fun add(comment: Comment) {
        comments.add(comment)
    }


    override fun edit(comment: Comment): Boolean {
        for ((index, item) in comments.withIndex()) {
            if (item.id == comment.id) {
                comments[index] = comment
                return true
            }
        }
        return false
    }

    override fun delete(comment: Comment): Boolean {
        for ((index, item) in comments.withIndex()) {
            if (item.id == comment.id) {
                comments[index] = comment.copy(isDelete = true)
                return true
            }
        }
        return false
    }

    override fun getById(): MutableList<Comment> {
        for ((index, item) in notes.withIndex()) {
            if (item.notesId == notes[index].id) {
                comments
            }
        }
        return comments
    }


}

abstract class Service<T> {
    val notes = mutableListOf<T>()
    var comments = mutableListOf<Comment>()
    private var uniqueId = 0

    abstract fun add(note: T)

    abstract fun edit(note: T): Boolean

    abstract fun delete(note: T): Boolean

    abstract fun getById(): MutableList<T>


    fun getUniqueId(): Int {
        uniqueId++
        return uniqueId
    }
}

data class Comment(
    val id: Int,
    val notesId: Int,
    val ownerId: Int,
    val text: String,
    var isDelete: Boolean = false
)