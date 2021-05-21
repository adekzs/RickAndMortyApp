package com.adeks.rickandmortyapp

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.adeks.rickandmortyapp.api.API
import com.adeks.rickandmortyapp.api.Repository
import com.adeks.rickandmortyapp.databinding.ActivityMainBinding
import com.adeks.rickandmortyapp.model.Character
import com.adeks.rickandmortyapp.model.CharacterResponse

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var characters = mutableListOf<Character>()

    private val viewModel: MainActivityViewModel by lazy {
        ViewModelProvider(this, MainActivityProviderViewModel(Repository(API.apiService))
        ).get(MainActivityViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecycler()
        getResponse()
        viewModel.pageNo.observe(this, {
            viewModel.getCharacters("$it")
        })
    }

    private fun setupRecycler() {
        val adapter = CharacterAdapter(characters)
        viewModel.characterRespMutableLiveData.observe(this, {
            if (characters.isNotEmpty())
                characters.clear()
            characters.addAll(it.results)
            binding.pageNumber.text = "${viewModel.pageNo.value}"
            adapter.notifyDataSetChanged()
            setButtonStates(it)
        })
        binding.characterRv.adapter = adapter
        binding.characterRv.layoutManager = LinearLayoutManager(this)
    }

    private fun setButtonStates(it: CharacterResponse?) {
        when {
            it?.info?.next == null -> {
                binding.next.isEnabled = false
                binding.previous.isEnabled = true
            }
            it?.info?.prev == null -> {
                binding.next.isEnabled = true
                binding.previous.isEnabled = false
            }
            else -> {
                binding.next.isEnabled = true
                binding.previous.isEnabled = true
            }
        }
    }

    private fun getResponse() {

        binding.next.setOnClickListener{
            viewModel.addToPage()
        }

        binding.previous.setOnClickListener{
            viewModel.subtractFromPage()
        }
    }
}