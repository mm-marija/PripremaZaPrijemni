package com.example.pripremazaprijemni;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import com.example.pripremazaprijemni.ViewModel.TestViewModel;

public class Test extends AppCompatActivity {
    private RecyclerViewAdapter adapter;
    private TestViewModel testViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        getSupportActionBar().hide();

        initRecyclerView();

        testViewModel = ViewModelProviders.of(this).get(TestViewModel.class);
        testViewModel.init();
        adapter.postaviListuPitanja(testViewModel.randomPitanja());
    }

            private void initRecyclerView() {
                RecyclerView recyclerView = findViewById(R.id.recyclerView);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                DividerItemDecoration dividerItemDecoration =
                        new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
                recyclerView.addItemDecoration(dividerItemDecoration);
                adapter = new RecyclerViewAdapter(getApplicationContext());
                recyclerView.setAdapter(adapter);
            }
}


