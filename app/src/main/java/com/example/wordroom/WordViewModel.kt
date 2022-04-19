package com.example.wordroom
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.launch
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope

class WordViewModel(private val repository: WordRepository) : ViewModel() {

        //  public LiveData member variable to cache the list of words.
//        initialized LiveData with the allWords Flow from the Repository.
//        You then converted the Flow to LiveData by calling asLiveData().
        // - We can put an observer on the data (instead of polling for changes) and only update the
        //   the UI when the data actually changes.
        // - Repository is completely separated from the UI through the ViewModel.
        val allWords: LiveData<List<Word>> = repository.allWords.asLiveData()

        /**
         * Launching a new coroutine to insert the data in a non-blocking way
         * created a wrapper insert() method that calls the Repository's insert() method.
         * This way, the implementation of insert()is encapsulated from the UI
         */
        fun insert(word: Word) = viewModelScope.launch {
            repository.insert(word)
        }
    }

/**
 * created the ViewModel and implemented a ViewModelProvider.Factory that gets as a parameter the
 * dependencies needed to create WordViewModel: the WordRepository.
 */
    class WordViewModelFactory(private val repository: WordRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(WordViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return WordViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
