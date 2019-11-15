package com.example.android.navigation


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.android.navigation.databinding.FragmentTitleBinding
import java.util.zip.Inflater

/**
 * A simple [Fragment] subclass.
 */
class TitleFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
            val binding =  DataBindingUtil.inflate<FragmentTitleBinding>(inflater, R.layout.fragment_title,
                                                        container, false)
        // THis sets on event that whenever the play button is clicked, it goes fro the current view
        // which is title fragment to the game fragment view.
        binding.playButton.setOnClickListener{ view : View ->
                Navigation.findNavController(view).navigate(R.id.action_titleFragment_to_gameFragment)       }
                //view.findNavController().navigate(R.id.action_titleFragment_to_gameFragment)

        // This activate the menu resource
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // If navigationUI cannot handle the menu, it will call super.OnOptionsItemSelected to avoid crashing if it does not find the menu
        return NavigationUI.onNavDestinationSelected(item!!, view!!.findNavController()) || super.onOptionsItemSelected(item)
    }
}
