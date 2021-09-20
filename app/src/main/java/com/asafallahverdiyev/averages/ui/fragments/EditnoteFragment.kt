package com.asafallahverdiyev.averages.ui.fragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.asafallahverdiyev.averages.R
import com.asafallahverdiyev.averages.database.Database
import com.asafallahverdiyev.averages.service.DAO
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_editnote.view.*


class EditnoteFragment : Fragment() {

    private val args by navArgs<EditnoteFragmentArgs>()
    private var subjectId:Int? = null
    private var subjectName:String? = null
    private var subjectScore:Float? = null
    private var subjectCredit:Int? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View? {

        val view = inflater.inflate(R.layout.fragment_editnote,container,false)

        getCurrentNoteWithSafeArgs()
        setCurrentNote(view)

        view.updateNoteButton.setOnClickListener { updateData(view) }

        setHasOptionsMenu(true)
        return view
    }

    private fun getCurrentNoteWithSafeArgs() {

        subjectId = args.currentNote.subjectId
        subjectName = args.currentNote.subjectName
        subjectScore = args.currentNote.score
        subjectCredit = args.currentNote.credit

    }

    private fun setCurrentNote(view: View) {

        view.inputSubjectName2.setText(subjectName)
        view.inputSubjectScore2.setText(subjectScore.toString())
        view.inputSubjectCredit2.setText(subjectCredit.toString())

    }

    private fun updateData(view: View) {

        try {

            subjectId?.let {

                DAO().updateData(
                    Database(requireContext()),it,
                    view.inputSubjectName2.text?.trim().toString(),
                    view.inputSubjectScore2.text?.trim().toString().toFloat(),
                    view.inputSubjectCredit2.text?.trim().toString().toInt())

            }
            Toast.makeText(requireContext(),"Note updated",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updatenoteFragment_to_notesFragment)

        }catch (e:Exception) {
            Toast.makeText(requireContext(),"Please fill in properly", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        requireActivity().menuInflater.inflate(R.menu.menu_toolbar_update_activity,menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == R.id.action_delete) {

            val view:View = requireActivity().findViewById(android.R.id.content)
            Snackbar.make(view,"Are you sure?", Snackbar.LENGTH_LONG)

                .setAction("Yes Delete") {

                    subjectId?.let {

                        DAO().deleteData(Database(requireContext()),it)

                    }
                    Toast.makeText(requireContext(),"Note deleted", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_updatenoteFragment_to_notesFragment)
                }

                .show()

        }
        return true
    }
}