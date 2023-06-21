package com.example.mynewcv.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mynewcv.databinding.FragmentHomeBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.itextpdf.text.Document
import com.itextpdf.text.Paragraph
import com.itextpdf.text.pdf.PdfWriter
import java.io.FileOutputStream

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var database: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        database = Firebase.database.reference

        binding.Save.setOnClickListener(View.OnClickListener {

            AddNewUserInfo()


            val PjName = binding.CvId.text.toString()
            val name = binding.editTextName.text.toString()
            val surname = binding.editSurname.text.toString()
            val phoneNumber = binding.editTextNumber.text.toString()
            val email = binding.editEmail.text.toString()
            val post = binding.editPosada.text.toString()
            val summary = binding.editSummary.text.toString()
            val hardSkills = binding.editHadrSkils.text.toString()
            val softSkills = binding.EditSoftSkils.text.toString()

            generatePdf(PjName, name, surname, phoneNumber, email, post, summary, hardSkills, softSkills)

            Toast.makeText(requireContext(), "CV is successfully saved", Toast.LENGTH_SHORT).show()
        })

        return root
    }


    fun generatePdf(
        PjName: String,
        name: String,
        surname: String,
        phoneNumber: String,
        email: String,
        post: String,
        summary: String,
        hardSkills: String,
        softSkills: String
    ) {
        val fileName = "output.pdf"

        try {
            val document = Document()
            PdfWriter.getInstance(document, FileOutputStream(fileName))
            document.open()

            document.add(Paragraph("Project Name: $PjName"))
            document.add(Paragraph("Name: $name"))
            document.add(Paragraph("Surname: $surname"))
            document.add(Paragraph("Phone Number: $phoneNumber"))
            document.add(Paragraph("Email: $email"))
            document.add(Paragraph("Post: $post"))
            document.add(Paragraph("Summary: $summary"))
            document.add(Paragraph("Hard Skills: $hardSkills"))
            document.add(Paragraph("Soft Skills: $softSkills"))

            document.close()

            println("PDF generated successfully. File: $fileName")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }



    private fun AddNewUserInfo() {
        val PjName = binding.CvId.text.toString()
        val name = binding.editTextName.text.toString()
        val surname = binding.editSurname.text.toString()
        val phoneNumber = binding.editTextNumber.text.toString()
        val email = binding.editEmail.text.toString()
        val post = binding.editPosada.text.toString()
        val summary = binding.editSummary.text.toString()
        val hardSkills = binding.editHadrSkils.text.toString()
        val softSkills = binding.EditSoftSkils.text.toString()

        val newCv = UserCvDTO(
            PjName,
            name,
            surname,
            phoneNumber,
            email,
            post,
            summary,
            hardSkills,
            softSkills
        )

        val cvId = database.child("UsersCV").push().key
        if (cvId != null) {
            database.child("UsersCV").child(cvId).setValue(newCv)
        } else {
            Log.e("HomeFragment", "Failed to generate cvId")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
