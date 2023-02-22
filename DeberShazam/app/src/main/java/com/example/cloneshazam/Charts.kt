package com.example.cloneshazam


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class Charts : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private val parentList = ArrayList<ParentItemD>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_charts, container, false)
    }


    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.parentRecyclerView) //Cambio
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(requireContext()) //Cambio

        addDataToList()
        val adapter = ParentAdapter(parentList)
        recyclerView.adapter = adapter
    }
    private fun addDataToList() {

        val childItems1 = ArrayList<ChildItemD>()
        childItems1.add(ChildItemD("Flower","Miley Cyrus",R.drawable.mc_flowers))
        childItems1.add(ChildItemD("The Astronaut","JIM",R.drawable.jin_astronaut))
        childItems1.add(ChildItemD("Calm Down","Rena",R.drawable.rem_calmdown))
        parentList.add(ParentItemD("Global Chart", childItems1))

        val childItems2 = ArrayList<ChildItemD>()
        childItems2.add(ChildItemD("Flower","Miley Cyrus",R.drawable.mc_flowers))
        childItems2.add(ChildItemD("The Astronaut","JIM",R.drawable.jin_astronaut))
        childItems2.add(ChildItemD("Calm Down","Rena",R.drawable.rem_calmdown))
        parentList.add(ParentItemD("Global Chart", childItems2))


        val childItems3 = ArrayList<ChildItemD>()
        childItems3.add(ChildItemD("Flower","Miley Cyrus",R.drawable.mc_flowers))
        childItems3.add(ChildItemD("The Astronaut","JIM",R.drawable.jin_astronaut))
        childItems3.add(ChildItemD("Calm Down","Rena",R.drawable.rem_calmdown))
        parentList.add(ParentItemD("Global Chart", childItems3))

        val childItems4 = ArrayList<ChildItemD>()
        childItems4.add(ChildItemD("Flower","Miley Cyrus",R.drawable.mc_flowers))
        childItems4.add(ChildItemD("The Astronaut","JIM",R.drawable.jin_astronaut))
        childItems4.add(ChildItemD("Calm Down","Rena",R.drawable.rem_calmdown))
        parentList.add(ParentItemD("Global Chart", childItems4))

        val childItems5 = ArrayList<ChildItemD>()
        childItems5.add(ChildItemD("Flower","Miley Cyrus",R.drawable.mc_flowers))
        childItems5.add(ChildItemD("The Astronaut","JIM",R.drawable.jin_astronaut))
        childItems5.add(ChildItemD("Calm Down","Rena",R.drawable.rem_calmdown))
        parentList.add(ParentItemD("Global Chart", childItems5))

    }




}