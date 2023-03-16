package com.example.cloneshazam

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Library.newInstance] factory method to
 * create an instance of this fragment.
 */
class Library : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private val heardSongList = ArrayList<ChildItemD>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_library, container, false)
    }


    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.rv_heard_song) //Cambio
        recyclerView.setHasFixedSize(true)
        //recyclerView.layoutManager = LinearLayoutManager(requireContext()) //Cambio

        addDataToList()
        val adapter = LibrayAdapter(heardSongList)
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(recyclerView.context,2 )
    }
    private fun addDataToList() {
        heardSongList.add(ChildItemD("Amor Plastique","Videoclub",R.drawable.vc_amourp))
        heardSongList.add(ChildItemD("Unholy","Sam Smith",R.drawable.ss_unholy))
        heardSongList.add(ChildItemD("Miss you","Oliver Tree",R.drawable.ot_missyou))
        heardSongList.add(ChildItemD("Calm Down","Rena",R.drawable.rem_calmdown))
        heardSongList.add(ChildItemD("Creeping","Metro boomin",R.drawable.mb_creepin))
        heardSongList.add(ChildItemD("Flower","Miley Cyrus",R.drawable.mc_flowers))
        heardSongList.add(ChildItemD("Kill Bill","SZA",R.drawable.sza_killbill))
        heardSongList.add(ChildItemD("Bloody Mary","Lady Gaga",R.drawable.lg_bloodymarry))
        heardSongList.add(ChildItemD("Calm Down","Rema",R.drawable.rem_calmdown))

    }
}