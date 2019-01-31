package com.example.apptarefas;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;

import com.example.apptarefas.adapters.RvTarefasAdapter;
import com.example.apptarefas.models.Tarefa;
import com.example.apptarefas.persistencia.Aplicacao;

import java.util.List;

import io.objectbox.Box;
import io.objectbox.BoxStore;

public class MainActivity extends AppCompatActivity {


    private Box<Tarefa> boxTarefa;
    private RecyclerView rvTarefas;
    private ImageButton ibDelete, ibOk;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //binding
        rvTarefas = findViewById(R.id.rv_tarefas);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab_nova_tarefa);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent novaTarefa = new Intent(MainActivity.this, NovaTarefaActivity.class);
                startActivity(novaTarefa);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        BoxStore boxStore = ((Aplicacao)getApplication()).getBoxStore();
        boxTarefa = boxStore.boxFor(Tarefa.class);

        List<Tarefa> tarefas = boxTarefa.getAll();

        RvTarefasAdapter adapter = new RvTarefasAdapter(this, tarefas);
        rvTarefas.setAdapter(adapter);
        rvTarefas.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_realizadas) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
