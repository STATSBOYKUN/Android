package com.umaru.recycleview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MahasiswaAdapter adapter;
    private ArrayList<Mahasiswa> mahasiswaArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addData();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        adapter = new MahasiswaAdapter(mahasiswaArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    void addData() {
        mahasiswaArrayList = new ArrayList<>();

        mahasiswaArrayList.add(new Mahasiswa("Umaru", "123456789", "08123456789"));
        mahasiswaArrayList.add(new Mahasiswa("Shela", "169319611", "08123453752"));
        mahasiswaArrayList.add(new Mahasiswa("Rizky", "164196361", "08121131789"));
        mahasiswaArrayList.add(new Mahasiswa("Dimas", "816418414", "08123451319"));
        mahasiswaArrayList.add(new Mahasiswa("Rizal", "164196361", "08123451319"));
    }
}
