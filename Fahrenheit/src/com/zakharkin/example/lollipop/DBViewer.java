package com.zakharkin.example.lollipop;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

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

        ListAdapter la;
        SQLiteDatabase database = null;
        try {
            database = dataHelper.getReadableDatabase();
            Cursor c = dataHelper.GetData(database);
            la = new SimpleCursorAdapter(getBaseContext(), android.R.layout.simple_list_item_2, c, new String[]{"created_at", "logtext"}, new int[] {android.R.id.text1, android.R.id.text2}, 0);
            gridView.setAdapter(la);
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