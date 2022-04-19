package com.example.wordroom

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**@Entity(tableName = "word_table")-specifies the name of the table
 *@PrimaryKey -Every entity needs a primary key. To keep things simple,
 *  each word acts as its own primary key.
 *  @ColumnInfo(name = "word")-specifies name of column in the table.
 */

@Entity(tableName = "word_table")
data class Word(@PrimaryKey @ColumnInfo(name = "word") val word: String)
