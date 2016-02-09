package com.zakharkin.example.lollipop

import android.app.Activity
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*

import java.util.ArrayList

/**
 * Created by andre on 22.11.2015.
 */
class DBViewer : Activity() {

    internal var dataHelper: LogsDataHelper? = null
    internal var gridView: GridView? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dbviewer)
        dataHelper = LogsDataHelper(baseContext)

        gridView = findViewById(R.id.gridView) as GridView

        val la: ListAdapter
        var database: SQLiteDatabase? = null
        try {
            database = (dataHelper as LogsDataHelper).readableDatabase
            val c = (dataHelper as LogsDataHelper).GetData(database)
            la = SimpleCursorAdapter(baseContext, android.R.layout.simple_list_item_2, c, arrayOf<String>("created_at", "logtext"), intArrayOf(android.R.id.text1, android.R.id.text2), 0)
            (gridView as GridView).adapter = la
        } catch (e: Exception) {
            Log.e("DBViewer", "No data found" + e.message)
            Toast.makeText(applicationContext, "No data found" + e.message, Toast.LENGTH_LONG).show()
        } finally {
            if (database != null) {
                database.close()
            }
        }
    }

    fun deleteAllClick(view: View) {
        dataHelper!!.deleteAll()
    }
}