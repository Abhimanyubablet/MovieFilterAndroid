package com.example.moviefilterproject

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog

class ViewPageActivity : AppCompatActivity() {
    private lateinit var movieListView: RecyclerView
    private lateinit var moviesAdapter: MoviesAdapter
    private lateinit var educatorText: TextView
    private lateinit var skillText: TextView
    private var  movieList = ArrayList<Index>()
    private var  showOwned = arrayListOf("Yes","No")
    private var  circullum = arrayListOf("Licks","Soloing","Techniques","Fingerstyle Guitar","Performance Studies","Rhythm","Accompaniment","Jamming","Bass Lines","Grooves","Chords","Strumming","Improvisation")
    private var  skill = arrayListOf("SKill:All")
    private var  educator = arrayListOf("Educator:All")
    private var  series = arrayListOf("Series","TrueFire","Mike Zito","Frank Vignola","Jeff McErlain","Brad Carlton","Dieo Figueiredo","Stu Hamm")
    private var  filteredDataList=ArrayList<Index>()
    var  skillFilterCount : Int = 0
    var  educatorFilterCount : Int = 0
    private lateinit var emptyText : TextView
    @SuppressLint("MissingInflatedId", "WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_page)

         movieList.addAll( Global.movieListData)
        moviesAdapter = MoviesAdapter(movieList)
       val linearOnlyShowOwned =findViewById<LinearLayout>(R.id.linear_only_show_owned)
        val linearSkill =findViewById<LinearLayout>(R.id.linear_skill)
        val linearCurriculum =findViewById<LinearLayout>(R.id.linear_cirrculum)
        val linearEducator =findViewById<LinearLayout>(R.id.linear_educator)
        val linearSeries =findViewById<LinearLayout>(R.id.linear_series)
       val searchText=findViewById<EditText>(R.id.text_search)

        val textOnlyShowOwned=findViewById<TextView>(R.id.text_only_show_owned)
        emptyText=findViewById(R.id.emptyText)

        skillText=findViewById(R.id.text_skill)
       val textCurriculum=findViewById<TextView>(R.id.text_cirrculum)
         educatorText=findViewById(R.id.text_educator)
        val textSeries=findViewById<TextView>(R.id.text_series)

        movieListView = findViewById(R.id.movieListView)
        movieListView.adapter = moviesAdapter

        movieList.forEach {
            it.skill_tags.forEach{
                if (skill.contains(it)){

                }else{
                    skill.add(it)
                }
            }

            if (educator.contains(it.educator)){

            }else{
                educator.add(it.educator)
            }
        }

        linearOnlyShowOwned.setOnClickListener{

            showAlertDialog(textOnlyShowOwned, showOwned, "owned")
        }

        linearSkill.setOnClickListener{
            showAlertDialog(skillText, skill, "skill")
        }

        linearCurriculum.setOnClickListener{
            showAlertDialog(textCurriculum, circullum, "circullum")
        }

        linearEducator.setOnClickListener{
            showAlertDialog(educatorText, educator, "educator")
        }

        linearSeries.setOnClickListener{
            showAlertDialog(textSeries, series, "series")
        }

        searchText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                try {
                    movieList.clear()
                    Global.movieListData.forEach {
                        if (it.title.toLowerCase().contains(p0.toString().toLowerCase())){
                            Toast.makeText(this@ViewPageActivity, "Exist", Toast.LENGTH_SHORT).show()
                            movieList.add(it)
                        }

                    }
                    moviesAdapter.notifyDataSetChanged()
                }catch(e: Exception){
                    Toast.makeText(this@ViewPageActivity, e.message, Toast.LENGTH_SHORT).show()
                }

            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })

    }


    fun showAlertDialog(text: TextView, listItems: ArrayList<String>,type : String){
        val dialog = BottomSheetDialog(this)

        val view = layoutInflater.inflate(R.layout.buttom_sheet_only_show_owned, null)
        val btnClose = view.findViewById<Button>(R.id.idBtnDismiss)
        val educatorList = view.findViewById<ListView>(R.id.educator_list)
        btnClose.setOnClickListener {
            dialog.dismiss()
        }

        educatorList.adapter= ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, listItems)
        educatorList.setOnItemClickListener { adapterView, view, i, l ->
            text.text = listItems[i]
            val selecedSkill=skillText.text.toString()
            val selecedEducator=educatorText.text.toString()
            val filteredByEditor = ArrayList<Index>()

            if (selecedSkill == "SKill:All" && selecedEducator == "Educator:All") {
                // If "All" is selected, show all items
                filteredByEditor.clear()
                filteredByEditor.addAll(Global.movieListData)
            }
            else if(selecedSkill == "SKill:All"){
                Global.movieListData.forEach {
                        if (it.educator == selecedEducator) {

                            filteredByEditor.add(it)
                            educatorFilterCount++;
                        }
                }
            }
            else if(selecedEducator == "Educator:All"){
                Global.movieListData.forEach {
                        if (it.skill_tags.contains(selecedSkill)) {
                            filteredByEditor.add(it)
                            skillFilterCount++;
                        }
                }
            }
            else {
                Global.movieListData.forEach {
                    println(it)
                    if (it.educator == selecedEducator) {
                        if (it.skill_tags.contains(selecedSkill)){
                            filteredByEditor.add(it)
                            educatorFilterCount++;
                        }
                    }
                }
            }
            if (filteredByEditor.isNotEmpty()) {
                movieList.clear()
                movieList.addAll(filteredByEditor)
                moviesAdapter.notifyDataSetChanged()
                emptyText.visibility = View.GONE;
                movieListView.visibility = View.VISIBLE
            }else{
                emptyText.visibility = View.VISIBLE;
                movieListView.visibility = View.GONE

            }
            dialog.dismiss()

        }
        dialog.setCancelable(false)
        dialog.setContentView(view)
        dialog.show()
    }
}