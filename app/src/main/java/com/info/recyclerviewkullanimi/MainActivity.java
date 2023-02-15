package com.info.recyclerviewkullanimi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;
    private ArrayList<String> ulkelerList;
    private BasitRvAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = findViewById(R.id.rv);
        rv.setHasFixedSize(true); //bu içerisine ekleyeceğimiz listelerin daha düzenli durması için yaptığım aşama

        //rv.setLayoutManager(new LinearLayoutManager(this));

        rv.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));  //dikey şekilde olmasını istiyorum.
        //vertical oluyorsa dikeydeki sutun sayısı
        //horizontal oluyorsa yataydaki satır sayısı alınır


        ulkelerList = new ArrayList<>();
        ulkelerList.add("türkiye");
        ulkelerList.add("almanya");
        ulkelerList.add("italya");
        ulkelerList.add("ingiltere");


        adapter = new BasitRvAdapter(getApplicationContext(), ulkelerList);
        rv.setAdapter(adapter);


    }
}