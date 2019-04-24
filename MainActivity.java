package com.nikolaihost.sqlite2;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{

            SQLiteDatabase sqLiteDatabase = this.openOrCreateDatabase("Users", MODE_PRIVATE, null);

            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS newUsers(name VARCHAR, age INT(4), id INTEGER PRIMARY KEY)");

            //sqLiteDatabase.execSQL("INSERT INTO events (name, age) VALUES('Justin', 25)");
            //sqLiteDatabase.execSQL("INSERT INTO newUsers (name, age) VALUES ('Nemo', 20)");
           // sqLiteDatabase.execSQL("INSERT INTO newUsers (name, age) VALUES ('Nemo', 15)");
           // sqLiteDatabase.execSQL("INSERT INTO newUsers (name, age) VALUES ('Nemo', 45)");

            sqLiteDatabase.execSQL("DELETE FROM newUsers WHERE id = 2");

            Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM newUsers WHERE name = 'Nemo' AND age  >10", null);
           // Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM users WHERE name LIKE '%e%'", null);

            int nameIndex = c.getColumnIndex("name");
            int ageIndex = c.getColumnIndex("age");
            int idIndex = c.getColumnIndex("id");
            c.moveToFirst();

            while (c != null){
                Log.i("RESULTS ", c.getString(nameIndex));
                Log.i("RESULTS ", Integer.toString(c.getInt(ageIndex)));
                Log.i("RESULTS ", Integer.toString(c.getInt(idIndex)));
                c.moveToNext();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
