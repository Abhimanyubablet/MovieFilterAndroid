package com.example.moviefilterproject

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DashBoardFragment : Fragment() {
    private lateinit var ownedRecyclerView: RecyclerView
    private lateinit var recentlyWatchedRecyclerView: RecyclerView
    private lateinit var favoriteRecyclerView: RecyclerView
    private lateinit var wishListRecyclerView: RecyclerView
    private lateinit var ownedAdapter: MoviesAdapter
    private lateinit var recentlyWatchedAdapter: MoviesAdapter
    private lateinit var favoriteAdapter: MoviesAdapter
    private lateinit var wishListAdapter: MoviesAdapter
    private val recentWatchList=ArrayList<Index>()
    private val ownedList = ArrayList<Index>()
    private val fevoritesList=ArrayList<Index>()
    private val witchListList=ArrayList<Index>()


    @SuppressLint("MissingInflatedId", "NotifyDataSetChanged", "CheckResult")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rowView= inflater.inflate(R.layout.fragment_dash_board, container, false)

        ownedRecyclerView=rowView.findViewById(R.id.ownedListView)
        wishListRecyclerView=rowView.findViewById(R.id.wishlistListView)
        favoriteRecyclerView=rowView.findViewById(R.id.favoriteListView)
        recentlyWatchedRecyclerView=rowView.findViewById(R.id.recentlyWatchedListView)

        val ownedViewAll=rowView.findViewById<TextView>(R.id.text_view_all)
        val recentlyWatchViewAll=rowView.findViewById<TextView>(R.id.recentlyWatchedViewAll)
        val favouritesViewAll=rowView.findViewById<TextView>(R.id.fevoriteViewAll)
        val wshListViewAll=rowView.findViewById<TextView>(R.id.wishListText)



        ownedAdapter =  MoviesAdapter( ownedList)
        favoriteAdapter =  MoviesAdapter( fevoritesList)
        wishListAdapter =  MoviesAdapter( witchListList)
        recentlyWatchedAdapter =  MoviesAdapter(recentWatchList)

        MovieApiCalling.create().getMovie()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe {
                    result ->
                println(result)
                val collections = result.record.result.index
                val smart = result.record.result.collections.smart
                val owned=smart[0];
                val recentWatch=smart[1];
                val favrouites=smart[2];
                val witchList=smart[3];

                for (i in 0 until owned.courses.size){
                    collections.forEach{
                        if (owned.courses[i] == it.id){
                            ownedList.add(it)
                        }
                    }
                }
                for (i in 0 until recentWatch.courses.size){
                    collections.forEach{
                        if (recentWatch.courses[i] == it.id){
                            recentWatchList.add(it)
                        }
                    }
                }

                for (i in 0 until favrouites.courses.size){
                    collections.forEach{
                        if (favrouites.courses[i] == it.id){
                            fevoritesList.add(it)
                        }
                    }
                }
                for (i in 0 until witchList.courses.size){
                    collections.forEach{
                        if (witchList.courses[i] == it.id){
                            witchListList.add(it)
                        }
                    }
                }

                ownedRecyclerView.adapter= MoviesAdapter(ownedList)
                ownedAdapter.notifyDataSetChanged()

                favoriteRecyclerView.adapter= MoviesAdapter(fevoritesList)
                favoriteAdapter.notifyDataSetChanged()

                recentlyWatchedRecyclerView.adapter= MoviesAdapter(recentWatchList)
                recentlyWatchedAdapter.notifyDataSetChanged()

                wishListRecyclerView.adapter= MoviesAdapter(witchListList)
                wishListAdapter.notifyDataSetChanged()
            }

        ownedViewAll.setOnClickListener {
            val intent = Intent(context, ViewPageActivity::class.java)
            Global.movieListData = ownedList;
            context?.startActivity(intent)
        }

        recentlyWatchViewAll.setOnClickListener {
            val intent = Intent(context, ViewPageActivity::class.java)
            Global.movieListData = recentWatchList;
            context?.startActivity(intent)
        }

        favouritesViewAll.setOnClickListener {
            val intent = Intent(context, ViewPageActivity::class.java)
            Global.movieListData = fevoritesList;
            context?.startActivity(intent)
        }

        wshListViewAll.setOnClickListener {
            val intent = Intent(context, ViewPageActivity::class.java)
            Global.movieListData = ownedList;
            context?.startActivity(intent)
        }

        return rowView
    }


}

object Global{
    var movieListData = ArrayList<Index>()
}