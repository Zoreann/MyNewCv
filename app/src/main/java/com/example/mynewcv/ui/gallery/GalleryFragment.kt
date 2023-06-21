package com.example.mynewcv.ui.gallery

import ProjectAdapter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mynewcv.databinding.FragmentGalleryBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var database: DatabaseReference
    private lateinit var adapter: ProjectAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root
        recyclerView = binding.Recucle
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = ProjectAdapter()

        database = FirebaseDatabase.getInstance().reference

        recyclerView.adapter = adapter // Set the adapter for the RecyclerView

        fetchProjectData()
        return root
    }

    private fun fetchProjectData() {
        val projectRef = database.child("UsersCV")

        projectRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val projectList = mutableListOf<String>()

                for (snapshot in dataSnapshot.children) {
                    val projectName = snapshot.child("pjName").getValue(String::class.java)
                    projectName?.let {
                        projectList.add(it)
                    }
                }

                adapter.setData(projectList)
                Log.e("AAA", "DataList $projectList")
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("AAA", "Failed to read value.", error.toException())
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
