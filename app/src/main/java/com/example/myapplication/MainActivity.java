package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Dummy> dummyDataList= new ArrayList<Dummy>();
    ArrayList<String> numberlist = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        get_json();


        this.InitializeDummyData();

        ListView listView = (ListView)findViewById(R.id.thelist);
        final MyAdapter myAdapter = new MyAdapter(this,dummyDataList);

        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id){
                Toast.makeText(getApplicationContext(),
                        myAdapter.getItem(position).getName(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    public void get_json()
    {
        String json;
        try
        {
            InputStream is = getAssets().open("a.json");

            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");
            JSONArray jsonArray = new JSONArray(json);

            for(int i =0; i<jsonArray.length();i++)
            {
                JSONObject obj = jsonArray.getJSONObject(i);
                String Name = jsonArray.getJSONObject(i).getString("name");
                String Phonenum = jsonArray.getJSONObject(i).getString("email");
                String Email = jsonArray.getJSONObject(i).getString("phone");

                dummyDataList.add(new Dummy(Name,Phonenum,Email));



            }
            Toast.makeText(getApplicationContext(), numberlist.toString(), Toast.LENGTH_SHORT).show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch(JSONException e)
        {
            e.printStackTrace();
        }
    }


    public void InitializeDummyData(){

        dummyDataList.add(new Dummy("Mingyu","010-9809-1626", "jmg2107@kaist.ac.kr"));
        dummyDataList.add(new Dummy( "Fred","191235456", "Fred@fred.gmail"));
        dummyDataList.add(new Dummy("Derf","000-1-2--123sfd", "derf@ders"));
    }


}
