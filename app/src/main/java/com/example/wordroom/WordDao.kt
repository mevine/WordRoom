package com.example.wordroom

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * WordDao is an interface; DAOs must either be interfaces or abstract classes
 * The @Dao annotation identifies it as a DAO class for Room.DAO(data access object)
 * suspend fun insert(word: Word) : Declares a suspend function to insert one word.
 *  @Insert -annotation is a special DAO method annotation where you don't have to provide any SQL!
 *  onConflict = OnConflictStrategy.IGNORE: The selected onConflict strategy ignores a
 *  new word if it's exactly the same as one already in the list.
 *
 *  suspend fun deleteAll(): Declares a suspend function to delete all the words.
 *  fun getAlphabetizedWords(): List<Word>: A method to get all the
 *  words and have it return a List of Words.
 *
 *  @Query("SELECT * FROM word_table ORDER BY word ASC"):
 *  Query that returns a list of words sorted in ascending order
 *
 *  The @Query annotation allows you to write SQL statements and expose them as DAO methods
 */
@Dao
interface WordDao {

    // The flow always holds/caches latest version of data. Notifies its observers when the
    // data has changed.
    @Query("SELECT * FROM word_table ORDER BY word ASC")
    fun getAlphabetizedWords(): Flow<List<Word>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Word)

    @Query("DELETE FROM word_table")
    suspend fun deleteAll()
}
