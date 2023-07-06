package com.example.escolasistemasolarnovo.views.receitas;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.escolasistemasolarnovo.DAO.ReceitasDAO;
import com.example.escolasistemasolarnovo.R;
import com.example.escolasistemasolarnovo.entidades.Receitas;
import com.example.escolasistemasolarnovo.entidades.Usuario;
import com.example.escolasistemasolarnovo.utils.BancoDeDados;
import com.example.escolasistemasolarnovo.utils.SessionData;

import java.util.ArrayList;

public class ReceitasListAdapter extends RecyclerView.Adapter<ReceitasListAdapter.ItemLista> {
    // atributos..............
    ArrayList<Receitas> fonteDadosOriginal, fonteDadosAtual;
    LayoutInflater inflater;
    BancoDeDados bd;
    Context context;

    //contrutor...............
    public ReceitasListAdapter(Context context){
        this.context = context;
        bd = Room.databaseBuilder(context.getApplicationContext(), BancoDeDados.class,"BancoApp").allowMainThreadQueries().build();
        //Obtendo o DAO de receita
        ReceitasDAO receitasDAO = bd.getReceitasDAO();
        fonteDadosOriginal = (ArrayList<Receitas>) receitasDAO.getAll();
        fonteDadosAtual = fonteDadosOriginal;
        inflater = LayoutInflater.from(context);
    }

    public void mudarFonteDados(ArrayList<Receitas> novosDados){
        if (novosDados.size()==0) fonteDadosAtual = fonteDadosOriginal;
        fonteDadosAtual = novosDados;
        notifyDataSetChanged(); //notifica que a fonte de dados  e recontroi os viewholders
    }
    //Metodos abstratos do Recycleview.adapter ..........
    @NonNull
    @Override
    public ItemLista onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View roupaXML = inflater.inflate(R.layout.listview_receita,parent,false);
        ItemLista novoItemLista = new ItemLista(roupaXML);
        return novoItemLista;
    }
    @Override
    public void onBindViewHolder(@NonNull ReceitasListAdapter.ItemLista viewholder, int position) {
        viewholder.dataReceita.setText(fonteDadosAtual.get(position).dataReceita);
        viewholder.origemReceita.setText(fonteDadosAtual.get(position).origemReceita);
        viewholder.valorReceita.setText(fonteDadosAtual.get(position).valorReceita);
        viewholder.detalhamentoReceita.setText(fonteDadosAtual.get(position).detalhamento_receita);
        viewholder.id = fonteDadosAtual.get(position).id;
    }
    @Override
    public int getItemCount() {
        return fonteDadosAtual.size();
    }

    //Classe interna que representa cada item da lista(ViewHolder)....................

    public static class ItemLista extends RecyclerView.ViewHolder{

        // atributos..............
        TextView origemReceita, dataReceita, valorReceita, detalhamentoReceita;
        Integer id;
        Button btnAlterar;

        //contrutor...............
        public ItemLista(View roupaXML){
            super(roupaXML); //obrigatorio
            dataReceita = roupaXML.findViewById(R.id.dataReceita);
            origemReceita = roupaXML.findViewById(R.id.mesReceita);
            valorReceita = roupaXML.findViewById(R.id.valorReceita);
            detalhamentoReceita = roupaXML.findViewById(R.id.detalhamentoReceita);
            btnAlterar = roupaXML.findViewById(R.id.btnAlterar);
            btnAlterar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Usuario usuarioLogado = SessionData.getInstance().getUserLogado();
                    if (usuarioLogado.perfil.equals("Usuário")) {
                    Intent intent = new Intent(v.getContext(), ReceitasListVisualizarUSER.class);
                    intent.putExtra("data",ItemLista.this.dataReceita.getText().toString());
                    intent.putExtra("origem",ItemLista.this.origemReceita.getText().toString());
                    intent.putExtra("valor", ItemLista.this.valorReceita.getText().toString());
                    intent.putExtra("obs",detalhamentoReceita.getText().toString());
                    intent.putExtra("id", id);
                    v.getContext().startActivity(intent);
                }else {
                        Intent intent = new Intent(v.getContext(), ReceitasListAlterar.class);
                        intent.putExtra("data",ItemLista.this.dataReceita.getText().toString());
                        intent.putExtra("origem",ItemLista.this.origemReceita.getText().toString());
                        intent.putExtra("valor", ItemLista.this.valorReceita.getText().toString());
                        intent.putExtra("obs",detalhamentoReceita.getText().toString());
                        intent.putExtra("id", id);
                        v.getContext().startActivity(intent);

                    }
                }
            });
        }
        //setters...............

    }

}
