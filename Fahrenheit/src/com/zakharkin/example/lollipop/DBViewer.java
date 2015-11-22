package com.zakharkin.example.lollipop;

import android.app.Activity;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andre on 22.11.2015.
 */
public class DBViewer extends Activity {

    LogsDataHelper dataHelper;
    GridView gridView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dbviewer);
        dataHelper = new LogsDataHelper(getBaseContext());

        gridView = (GridView) findViewById(R.id.gridView);
        List<String> data = new ArrayList<>();
        ListAdapter la = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_list_item_1, data);
        SQLiteDatabase database = null;
        try {
            database = dataHelper.getReadableDatabase();
            Cursor c = dataHelper.GetData(database);
            //Move the cursor to the first row.
            if (c.moveToFirst()) {
                do {
                    String dStr = dataHelper.GetString(c);
                    //add in to array list
                    data.add(dStr);
                    gridView.setAdapter(la);
                } while (c.moveToNext());//Move the cursor to the next row.
            } else {
                Log.w("DBViewer", "No data found");
                Toast.makeText(getApplicationContext(), "No data found", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Log.e("DBViewer", "No data found" + e.getMessage());
            Toast.makeText(getApplicationContext(), "No data found" + e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            if (database != null) {
                database.close();
            }
        }
    }

    public void deleteAllClick(View view){
        dataHelper.deleteAll();
    }
}