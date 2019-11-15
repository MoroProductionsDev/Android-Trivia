package com.example.android.navigation


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
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
        // which is title framgent to the game fragment view.
        binding.playButton.setOnClickListener{ view : View ->
               Navigation.findNavController(view).navigate(R.id.action_titleFragment_to_gameFragment)       }
        return binding.root
    }
}
