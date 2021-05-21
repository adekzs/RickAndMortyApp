package com.adeks.rickandmortyapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adeks.rickandmortyapp.api.Repository
import com.adeks.rickandmortyapp.model.CharacterResponse
import kotlinx.coroutines.launch
import java.lang.Exception

class MainActivityViewModel(private val repository: Repository) : ViewModel() {
    private  val TAG = "MainActivityViewModel"
    private val _pageNo = MutableLiveData<Int>();
    val pageNo : LiveData<Int>
    get() = _pageNo

    fun addToPage() {
        _pageNo.value = _pageNo.value?.plus(1)
    }

    fun subtractFromPage() {
        _pageNo.value = _pageNo.value?.minus(1)
    }


    init {
        _pageNo.value = 1
        getCharacters("1")

    }
    private val _characterRespMutableLiveData = MutableLiveData<CharacterResponse>()

    val characterRespMutableLiveData : LiveData<CharacterResponse>
    get() = _characterRespMutableLiveData

    fun getCharacters(pageNo : String) {
        viewModelScope.launch {
            try {
                _characterRespMutableLiveData.value = repository.getCharacterResponse(pageNo)
            } catch (e : Exception) {
                Log.e(TAG, "getCharacters: ${e.message}" )
            }
        }
     }






}