/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.navigation

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.android.navigation.databinding.FragmentGameWonBinding


class GameWonFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding: FragmentGameWonBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_game_won, container, false)
        binding.nextMatchButton.setOnClickListener{view : View ->
                    //view.findNavController().navigate(R.id.action_gameWonFragment_to_gameFragment)}
            view.findNavController().navigate(GameWonFragmentDirections.actionGameWonFragmentToGameFragment())}

        // catching the arguments from the global passed bundle arguments.
        val args =  GameWonFragmentArgs.fromBundle(arguments!!)

        // Print numbers of question and number of correct answers
        Toast.makeText(context, "NumCorrect: ${args.numCorrect}, NumQuestions: ${args.numQuestion}",
                        Toast.LENGTH_LONG).show()

        // Setting the menu fro winner
        setHasOptionsMenu(true)

        return binding.root
    }

    //
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.winner_menu, menu)

        // check if the activity resolves. If there is not app to handle it
        // Protect against a case where there is no app able to handle the Intent
        if (null == getShareIntent().resolveActivity(activity!!.packageManager)) {
            menu?.findItem(R.id.share)?.setVisible(false)
        }
    }

    // Gets the arguments and build the share intent for sharing the result when user wins the trivia
    private fun getShareIntent() : Intent {
        // catching the arguments from the global passed bundle arguments.
        var args =  GameWonFragmentArgs.fromBundle(arguments!!)
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT,
                getString(R.string.share_success_text, args.numCorrect, args.numQuestion))
        return shareIntent
    }

    // This method does the sharing
    private fun shareSuccess() {
        // Calls the activity perform on the intent
        // If their multiple apps user gets to choose base on the system apps
        startActivity(getShareIntent())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Switch statement that item(Non NUll). itemId creates intent filter
        when (item!!.itemId) {
            R.id.share-> shareSuccess()
        }
        return super.onOptionsItemSelected(item)
    }
}
