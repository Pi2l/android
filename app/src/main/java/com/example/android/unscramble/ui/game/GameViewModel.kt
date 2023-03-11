package com.example.android.unscramble.ui.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    private var _score = 0
    val score: Int
        get() = _score

    private var _currentWordCount = 0
    val currentWordCount: Int
        get() = _currentWordCount

    private var usedWords : MutableList<String> = mutableListOf()
    private lateinit var currentWord: String

    private val _currentScrambledWord = MutableLiveData<String>()
    val currentScrambledWord: LiveData<String>
        get() = _currentScrambledWord

    init {
        Log.d("GameFragment", "GameViewModel created")
        getNextWord()
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("GameFragment", "GameViewModel created")
    }

    fun getNextWord() {
        currentWord = allWordsList.random()
        while (usedWords.contains(currentWord)) {
            currentWord = allWordsList.random()
        }

        var tmpWord = currentWord.toCharArray()
        tmpWord.shuffle()
        while ( String(tmpWord).equals(currentWord, false) ) {
            tmpWord.shuffle()
        }
        _currentScrambledWord.value = String(tmpWord)
        ++_currentWordCount
        usedWords.add(currentWord)
    }

    /*
    * Returns true if the current word count is less than MAX_NO_OF_WORDS.
    * Updates the next word.
    */
    fun nextWord(): Boolean {
        return if (_currentWordCount < MAX_NO_OF_WORDS) {
            getNextWord()
            true
        } else false
    }

    private fun increaseScore() {
        _score += SCORE_INCREASE
    }

    fun isUserCorrect(word: String): Boolean {
        if (currentWord.equals(word, true)) {
            increaseScore()
            return true
        }
        return false
    }

    fun reinitializeData() {
        _score = 0
        _currentWordCount = 0
        usedWords.clear()
        getNextWord()
    }
}