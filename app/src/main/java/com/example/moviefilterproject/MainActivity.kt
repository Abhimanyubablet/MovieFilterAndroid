package com.example.moviefilterproject

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val movieTabLayout=findViewById<TabLayout>(R.id.movie_tablaout)
        val pageViewer=findViewById<ViewPager>(R.id.viewpager)

        val movieAdapter=MovieTabAdapter(supportFragmentManager)
        movieAdapter.addFragment(DashBoardFragment(),"DashBoard")
        movieAdapter.addFragment(MyCollectionFragment(),"MyCollection")
        movieAdapter.addFragment(DownloadFragment(),"Download")

        pageViewer.adapter=movieAdapter
        movieTabLayout.setupWithViewPager(pageViewer)

    }
}