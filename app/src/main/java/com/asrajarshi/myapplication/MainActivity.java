package com.asrajarshi.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TableRow.LayoutParams wrapWrapTableRowParams = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int[] fixedColumnWidths = new int[]{20, 20, 20, 20, 20};
        int[] scrollableColumnWidths = new int[]{20, 20, 20, 30, 30};
        int fixedRowHeight = 80;
        int fixedHeaderHeight = 100;

        Context context = getBaseContext();
        GetAssets getFile = new GetAssets(context);
        String bufferString = null;
        try {
            bufferString = getFile.getFileAssets();
            GetJson getJson1 = new GetJson(bufferString);
            List<ArrayList<String>> ab;
            ab = getJson1.getIt(); // get the List of ArrayLists
            int nCol = ab.get(0).size(); //size of column
            int nRow = ab.size(); //size of row
            ArrayList<String> key1 = getJson1.key;


            TableRow row = new TableRow(this);
            //header (fixed vertically)
            TableLayout header = (TableLayout) findViewById(R.id.scrollable_part);
            row.setLayoutParams(wrapWrapTableRowParams);
            row.setGravity(Gravity.LEFT);
            for(int j = 0 ; j < nCol ; j++){
                row.addView(makeTableRowWithText("  "+key1.get(j), fixedColumnWidths[0], fixedHeaderHeight));
            }
            header.addView(row);
            //header (fixed horizontally)
            TableLayout fixedColumn = (TableLayout) findViewById(R.id.fixed_column);
            //rest of the table (within a scroll view)
            TableLayout scrollablePart = (TableLayout) findViewById(R.id.scrollable_part);
            TextView fixed = makeTableRowWithText("Num", scrollableColumnWidths[0], fixedHeaderHeight);
            fixedColumn.addView(fixed);
            row = new TableRow(this);
            row.setLayoutParams(wrapWrapTableRowParams);
            row.setGravity(Gravity.LEFT);
            row.setBackgroundColor(Color.WHITE);
            for (int i = 0; i < nRow; i++) {
                TextView fixedView = makeTableRowWithText(ab.get(i).get(0), scrollableColumnWidths[0], fixedRowHeight);
                fixedView.setBackgroundColor(Color.WHITE);
                fixedColumn.addView(fixedView);
                row = new TableRow(this);
                row.setLayoutParams(wrapWrapTableRowParams);
                row.setGravity(Gravity.LEFT);
                row.setBackgroundColor(Color.WHITE);
                for(int k = nCol-1; k >= 0 ; k--){// TODO need to return fixed column elements as the first element of each arraylist
                    row.addView(makeTableRowWithText(ab.get(i).get(k), scrollableColumnWidths[1], fixedRowHeight));
                }
                scrollablePart.addView(row);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //util method
    private TextView recyclableTextView;

    public TextView makeTableRowWithText(String text, int widthInPercentOfScreenWidth, int fixedHeightInPixels) {
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        recyclableTextView = new TextView(this);
        recyclableTextView.setText(text);
        recyclableTextView.setTextColor(Color.BLACK);
        recyclableTextView.setTextSize(20);
        recyclableTextView.setPadding(10,10,10,10);
        return recyclableTextView;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
