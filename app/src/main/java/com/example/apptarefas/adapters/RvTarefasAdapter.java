package com.example.apptarefas.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apptarefas.MainActivity;
import com.example.apptarefas.R;
import com.example.apptarefas.models.Tarefa;

import java.util.List;

import io.objectbox.Box;
import io.objectbox.BoxStore;

public class RvTarefasAdapter extends RecyclerView.Adapter<RvTarefasAdapter.TarefaViewHolder> {


    private Context context;
    private List<Tarefa> tarefas;


    public RvTarefasAdapter(Context context, List<Tarefa> tarefas) {
        this.context = context;
        this.tarefas = tarefas;
    }


    @NonNull
    @Override
    public RvTarefasAdapter.TarefaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_tarefa, parent, false);

        return new TarefaViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull RvTarefasAdapter.TarefaViewHolder holder, int position) {
        Tarefa tarefaAtual = tarefas.get(position);

        holder.tvItemCategoria.setText(tarefaAtual.getCategoria());
        holder.tvItemPrioridade.setText(String.valueOf(tarefaAtual.getPrioridade()));

        String categoria = tarefaAtual.getCategoria();

        if (categoria.equals("Educacional")){
            holder.ivItemCategoria.setImageResource(R.drawable.icon_educacional);
        }

        if (categoria.equals("Profissional")){
            holder.ivItemCategoria.setImageResource(R.drawable.icon_profissional);
        }

        if (categoria.equals("Pessoal")){
            holder.ivItemCategoria.setImageResource(R.drawable.icon_pessoal);
        }

    }

    @Override
    public int getItemCount() {

        return tarefas.size();
    }

    public class TarefaViewHolder extends RecyclerView.ViewHolder{


        private TextView tvItemCategoria, tvItemPrioridade;
        private ImageView ivItemCategoria;


        public TarefaViewHolder(View view, Context context){
            super(view);

            tvItemCategoria = view.findViewById(R.id.tv_item_categoria);
            tvItemPrioridade = view.findViewById(R.id.tv_item_prioridade);
            ivItemCategoria = view.findViewById(R.id.iv_item_categoria);
        }

    }
}
