package com.example.apptarefas;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.apptarefas.models.Tarefa;
import com.example.apptarefas.persistencia.Aplicacao;

import io.objectbox.Box;
import io.objectbox.BoxStore;

public class NovaTarefaActivity extends AppCompatActivity {


    private EditText etTarefaDescricao;
    private RadioGroup rgCategoria, rgPrioridade;
    private ToggleButton tbRealizada;
    private Box<Tarefa> boxTarefa;
    private boolean r;
    private int[] imagens = new int[]{R.drawable.icon_pessoal, R.drawable.icon_profissional, R.drawable.icon_educacional};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_tarefa);

        //binding
        etTarefaDescricao = findViewById(R.id.et_tarefa_descricao);
        rgCategoria = findViewById(R.id.rg_categoria);
        rgPrioridade = findViewById(R.id.rg_prioridade);
        tbRealizada = findViewById(R.id.tb_realizada);

        //BoxStore
        BoxStore boxStore = ((Aplicacao)getApplication()).getBoxStore();
        boxTarefa = boxStore.boxFor(Tarefa.class);
    }


    //Retorna a categoria marcada
    public String getRgCategoria(){

        switch(rgCategoria.getCheckedRadioButtonId()){

            case R.id.rb_educacional:
                return "Educacional";

            case R.id.rb_pessoal:
                return "Pessoal";

            case R.id.rb_profissional:
                return "Profissional";
        }

        return "-1";
    }

    //Retorna a prioridade marcada
    public int getRgPrioridade(){

        switch(rgPrioridade.getCheckedRadioButtonId()){

            case R.id.rb_p1:
                return 1;

            case R.id.rb_p2:
                return 2;

            case R.id.rb_p3:
                return 3;

            case R.id.rb_p4:
                return 4;

            case R.id.rb_p5:
                return 5;
        }

        return -1;
    }

    //Retorna verdadeiro ou falso de acordo com a opção do Toggle Button
    public boolean getRealizada(){

        tbRealizada.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked){
                  NovaTarefaActivity.this.r = true;
                }

                else{
                    NovaTarefaActivity.this.r = false;
                }
            }
        });

        return this.r;
    }


    //Inflando layout do Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_nova_tarefa, menu);

        return true;
    }

    //Ouvindo cliques no menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.action_enviar:

                String descricao = etTarefaDescricao.getText().toString();
                String categoria = getRgCategoria();
                int prioridade = getRgPrioridade();
                boolean realizada = getRealizada();

                Tarefa tarefa = new Tarefa(descricao, categoria, prioridade, realizada);
                boxTarefa.put(tarefa);

                Toast.makeText(NovaTarefaActivity.this, "Salvou", Toast.LENGTH_SHORT).show();

                finish();

        }

        return super.onOptionsItemSelected(item);
    }


}
