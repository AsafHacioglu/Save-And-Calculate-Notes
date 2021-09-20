package com.asafallahverdiyev.averages.ui.fragments


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.asafallahverdiyev.averages.models.NoteModel
import com.asafallahverdiyev.averages.R
import com.asafallahverdiyev.averages.adapters.RecyclerAdapter
import com.asafallahverdiyev.averages.database.Database
import com.asafallahverdiyev.averages.service.DAO
import kotlinx.android.synthetic.main.card_view.*
import kotlinx.android.synthetic.main.fragment_notes.*
import kotlinx.android.synthetic.main.fragment_notes.view.*

class NotesFragment : Fragment(),RecyclerAdapter.Listener {

    private lateinit var fragmentView: View
    private lateinit var dbClass: Database
    private var notes = ArrayList<NoteModel>()
    private var sortedNotes = ArrayList<NoteModel>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        fragmentView = inflater.inflate(R.layout.fragment_notes, container, false)
        dbClass = Database(requireContext())

        notes = DAO().getData(dbClass)

        setRecyclerView(notes)
        showAverage(notes)
        fragmentView.fab.setOnClickListener { findNavController().navigate(R.id.action_notesFragment_to_addnoteFragment) }
        setHasOptionsMenu(true)
        return fragmentView
    }

    private fun setRecyclerView(list: ArrayList<NoteModel>) {

        fragmentView.recyclerView.setHasFixedSize(true)
        fragmentView.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = RecyclerAdapter(list, this@NotesFragment)
        fragmentView.recyclerView.adapter = adapter

    }

    private fun calculateAverage(notes: ArrayList<NoteModel>): Double {

        var totalScoreWithCredit = 0.0
        var totalCredit = 0.0

        if (notes.isNotEmpty()) {

            for (i in notes) {

                val formula = i.score * i.credit
                totalScoreWithCredit += formula

                totalCredit += i.credit
            }
        }else {

            fragmentView.averageCardView.visibility = View.INVISIBLE
        }

        return totalScoreWithCredit / totalCredit
    }

    private fun showAverage(notes: ArrayList<NoteModel>) {

        fragmentView.average.text = "Average: ${calculateAverage(notes)}"

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        requireActivity().menuInflater.inflate(R.menu.menu_toolbar_main_activity, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            R.id.action_ascending -> {

                sortedNotes.clear()
                sortedNotes = DAO().sortAscending(dbClass)
                setRecyclerView(sortedNotes)

            }

            R.id.action_descending -> {

                sortedNotes.clear()
                sortedNotes = DAO().sortDescending(dbClass)
                setRecyclerView(sortedNotes)

            }
        }
        return true
    }

    override fun onCardViewClickListener(model: NoteModel) {

            val action = NotesFragmentDirections.actionNotesFragmentToUpdatenoteFragment(model)
            findNavController().navigate(action)
    }
}
