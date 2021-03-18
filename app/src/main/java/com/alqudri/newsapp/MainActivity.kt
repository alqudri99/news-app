package com.alqudri.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.alqudri.newsapp.adapter.TeAdapter
import com.alqudri.newsapp.data.database.StudentDatabase
import com.alqudri.newsapp.data.entities.Students
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var studentDatabase: StudentDatabase? =null
    val compositeDisposable = CompositeDisposable()
    val l = arrayListOf<Students>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var i =0
        studentDatabase = StudentDatabase.getInstance(this)
        getAll()
        tambah.setOnClickListener {
            i++
            var students = Students("adass $i", "sdj $i", "45 $i")
            insert(Students("adass $i", "sdj $i", "45 $i"))
            getAll()
        }

        delete.setOnClickListener {
            for (i in l){
                delete(i)
            }
        }
    }

    fun insert(students: Students){
        compositeDisposable.add(Observable.fromCallable{studentDatabase?.studentDao()?.insert(students)}
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe())
    }

   fun getAll(){
       compositeDisposable.add(Observable.fromCallable{studentDatabase!!.studentDao().getAll()}
           .subscribeOn(Schedulers.computation())
           .observeOn(AndroidSchedulers.mainThread())
           .subscribe{
               val gson = Gson()
               Log.d("kasa", gson.toJson(it))
               l.clear()
               l.addAll(it)
               rv.layoutManager = LinearLayoutManager(this)
               rv.adapter = TeAdapter(l)

//               students.clear()
//               students.addAll(it)
//               item_recyclerview.adapter = ItemAdapter(students, this)
           })
   }

    fun delete(students: Students){
        compositeDisposable.add(Observable.fromCallable { studentDatabase!!.studentDao().delete(students) }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread()).subscribe()
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        StudentDatabase.destroy()
        compositeDisposable.dispose()
    }
}