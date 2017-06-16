package com.shellcore.android.asynctasklist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.shellcore.android.asynctasklist.adapters.PokemonListAdapter;
import com.shellcore.android.asynctasklist.tasks.DownloadPokemonTask;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements DownloadPokemonTask.PokemonListListener {

    // Servicios
    private PokemonListAdapter adapter;

    // Variables
    private List<String> names;

    // Componentes
    @BindView(R.id.rec_list)
    RecyclerView recList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        names = new ArrayList<>();
        adapter = new PokemonListAdapter(this, names);
        recList.setLayoutManager(new LinearLayoutManager(this));
        recList.setAdapter(adapter);
    }

    @OnClick(R.id.btn_update_list)
    public void updateList() {
        new DownloadPokemonTask(this, this).execute();
    }

    @Override
    public void handleResult(ArrayList<String> strings) {
        names.clear();
        names.addAll(strings);
        adapter.notifyDataSetChanged();
    }
}
