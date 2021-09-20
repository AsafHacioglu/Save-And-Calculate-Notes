package com.asafallahverdiyev.averages.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.asafallahverdiyev.averages.R
import com.asafallahverdiyev.averages.database.Database
import com.asafallahverdiyev.averages.service.DAO
import kotlinx.android.synthetic.main.fragment_addnote.*
import kotlinx.android.synthetic.main.fragment_addnote.view.*
import java.lang.Exception

class AddnoteFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View? {

        val view = inflater.inflate(R.layout.fragment_addnote, container, false)
        view.addNoteButton.setOnClickListener { insertData() }
        return view
    }

    private fun insertData() {

        try {
            DAO().insertData(
                Database(requireContext()),
                inputSubjectName1.text?.trim().toString(),
                inputSubjectScore1.text?.trim().toString().toFloat(),
                inputSubjectCredit1.text?.trim().toString().toInt())

            Toast.makeText(requireContext(),"Note added",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addnoteFragment_to_notesFragment)

        }catch (e: Exception) {
            Toast.makeText(requireContext(),"Please fill in properly", Toast.LENGTH_LONG).show()
        }
    }
}