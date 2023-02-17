package com.jmsc.kotlinflowdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.jmsc.kotlinflowdemo.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!


    private var itemAdapter : CustomAdapter = CustomAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnAdd.setOnClickListener {
            if(binding.edtName.text.toString().isNotEmpty())
                viewModel.addString(Item(0,binding.edtName.text.toString()))

            binding.edtName.setText("")
        }
        binding.btnUpdate.setOnClickListener {
            viewModel.updateString(itemAdapter.selectedPosition,Item(0,binding.edtName.text.toString()))
            binding.edtName.setText("")
        }
        binding.btnDelete.setOnClickListener {
            viewModel.deleteString(itemAdapter.selectedPosition)
        }

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.custom.collect {
                    Log.i("==> collect ",""+it.size)
                    itemAdapter.updateItems(it)
                }
            }
        }

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.custom.collectLatest {
                    Log.i("==> collectLatest ",""+it.size)
                }
            }
        }

        binding.recyclerview.layoutManager = LinearLayoutManager(this)

        // Setting the Adapter with the recyclerview
        binding.recyclerview.adapter = itemAdapter

       itemAdapter.onItemClick = {
           position,item ->
           binding.edtName.setText(item.text)
           itemAdapter.selectedPosition = position
           Toast.makeText(this,""+position+" "+item.text,Toast.LENGTH_SHORT).show()
       }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}