package com.example.lab_2_anime.interf_components
import android.content.Context
import android.view.View
import android.widget.Button
import com.example.lab_2_anime.DataFrom.FilmsArray
import com.example.lab_2_anime.findViewById
import com.example.lab_2_anime.refrashList


class MyButtons(private val id_butt_1: Int, private val id_butt_2: Int, cont: Context) {
    private var butt1 = Button(cont)
    private var butt2 = Button(cont)

    fun InitButt(films: FilmsArray, MyView: findViewById, frash: refrashList){
        butt1 = MyView.findView(id_butt_1)
        butt2 = MyView.findView(id_butt_2)
        butt1.visibility = View.INVISIBLE
        butt1.setOnClickListener {
            if(films.getPage() == films.getCountPage())
                butt2.visibility = View.VISIBLE
            films.previousPage()
            if(films.getPage() == 1.toLong())
                butt1.visibility = View.INVISIBLE
            frash.smenPage()
        }

        butt2.setOnClickListener {
            if(films.getPage() == 1.toLong())
                butt1.visibility = View.VISIBLE
            films.nextPage()
            if(films.getPage() == films.getCountPage())
                butt2.visibility = View.INVISIBLE
            frash.smenPage()
        }
    }
}