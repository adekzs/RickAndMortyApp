package com.adeks.rickandmortyapp.api

class Repository(private val apiService: ApiService) {

    suspend fun getCharacterResponse(pageNo : String) = apiService.getCharacters(pageNo)

}