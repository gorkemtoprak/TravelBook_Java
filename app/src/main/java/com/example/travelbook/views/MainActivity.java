 package com.example.travelbook.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.travelbook.R;
import com.example.travelbook.adapter.CustomAdapter;
import com.example.travelbook.model.PlaceModel;

import java.util.ArrayList;

 public class MainActivity extends AppCompatActivity {

     SQLiteDatabase database;
     ArrayList<PlaceModel> placeModelArrayList = new ArrayList<>();
     ListView listView;
     CustomAdapter customAdapter;

     @Override
     public boolean onCreateOptionsMenu(Menu menu) {

         MenuInflater menuInflater = getMenuInflater();
         menuInflater.inflate(R.menu.add_place, menu);

         return super.onCreateOptionsMenu(menu);
     }

     @Override
     public boolean onOptionsItemSelected(@NonNull MenuItem item) {

         if(item.getItemId() == R.id.add_place){
             Intent intent = new Intent(this, MapsActivity.class);
             intent.putExtra("info", "new");
             startActivity(intent);

         }

         return super.onOptionsItemSelected(item);
     }

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        getData();
    }

     public void getData() {
         try {

             database = this.openOrCreateDatabase("Places", MODE_PRIVATE,null);
             Cursor cursor = database.rawQuery("SELECT * FROM places",null);

             int nameIx = cursor.getColumnIndex("name");
             int latitudeIx = cursor.getColumnIndex("latitude");
             int longitudeIx = cursor.getColumnIndex("longitude");

             while (cursor.moveToNext()) {

                 String nameFromDatabase = cursor.getString(nameIx);
                 String latitudeFromDatabase = cursor.getString(latitudeIx);
                 String longitudeFromDatabase = cursor.getString(longitudeIx);

                 Double latitude = Double.parseDouble(latitudeFromDatabase);
                 Double longitude = Double.parseDouble(longitudeFromDatabase);

                 PlaceModel place = new PlaceModel(nameFromDatabase,latitude,longitude);
                 System.out.println(place.placeName);

                 placeModelArrayList.add(place);

             }
             customAdapter.notifyDataSetChanged();
             cursor.close();
         }
         catch (Exception e) {
             e.printStackTrace();
         }

         listView.setAdapter(customAdapter);

         listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                 Intent intent = new Intent(MainActivity.this,MapsActivity.class);
                 intent.putExtra("info","old");
                 intent.putExtra("place", placeModelArrayList.get(i));
                 startActivity(intent);
             }
         });
     }
 }