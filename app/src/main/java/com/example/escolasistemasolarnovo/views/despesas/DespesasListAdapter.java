package com.example.escolasistemasolarnovo.views.despesas;

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

import com.example.escolasistemasolarnovo.DAO.DespesasDAO;
import com.example.escolasistemasolarnovo.R;
import com.example.escolasistemasolarnovo.entidades.Despesas;
import com.example.escolasistemasolarnovo.entidades.Usuario;
import com.example.escolasistemasolarnovo.utils.BancoDeDados;
import com.example.escolasistemasolarnovo.utils.SessionData;

import java.util.ArrayList;

public class DespesasListAdapter extends RecyclerView.Adapter<DespesasListAdapter.ItemLista> {
    // atributos..............
    ArrayList<Despesas> fonteDadosOriginal, fonteDadosAtual;
    LayoutInflater inflater;
    BancoDeDados bd;
    Context context;

    //contrutor...............
    public DespesasListAdapter(Context context){
        this.context = context;
        bd = Room.databaseBuilder(context.getApplicationContext(), BancoDeDados.class,"BancoApp").allowMainThreadQueries().build();
        //Obtendo o DAO do despesas
        DespesasDAO despesasDAO = bd.getDespesasDAO();
        fonteDadosOriginal = (ArrayList<Despesas>) despesasDAO.getAll();
        fonteDadosAtual = fonteDadosOriginal;
        inflater = LayoutInflater.from(context);
    }

    public void mudarFonteDados(ArrayList<Despesas> novosDados){
        if (novosDados.size()==0) fonteDadosAtual = fonteDadosOriginal;
        fonteDadosAtual = novosDados;
        notifyDataSetChanged(); //notifica que a fonte de dados  e recontroi os viewholders
    }

    //Metodos abstratos do Recycleview.adapter ..........
    @NonNull
    @Override
    public ItemLista onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View roupaXML = inflater.inflate(R.layout.listview_despesas,parent,false);
        ItemLista novoItemLista = new ItemLista(roupaXML);
        return novoItemLista;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemLista viewholder, int position) {
        viewholder.dataDespesas.setText(fonteDadosAtual.get(position).dataDespesas);
        viewholder.origemDespesas.setText(fonteDadosAtual.get(position).origemDespesas);
        viewholder.valorDespesas.setText(fonteDadosAtual.get(position).valorDespesas);
        viewholder.detalhamentoDespesas.setText(fonteDadosAtual.get(position).detalhamento_despesas);
        viewholder.id = fonteDadosAtual.get(position).id;

    }

    @Override
    public int getItemCount() {
        return fonteDadosAtual.size();
    }

    //Classe interna que representa cada item da lista(ViewHolder)....................

    public static class ItemLista extends RecyclerView.ViewHolder{

        // atributos..............
        TextView origemDespesas, dataDespesas, valorDespesas, detalhamentoDespesas;
        Integer id;
        Button btnAlterar;

        //contrutor...............
        public ItemLista(View roupaXML){
            super(roupaXML); //obrigatorio
            dataDespesas = roupaXML.findViewById(R.id.dataDespesas);
            origemDespesas = roupaXML.findViewById(R.id.mesDespesas);
            valorDespesas= roupaXML.findViewById(R.id.valorDespesas);
            detalhamentoDespesas = roupaXML.findViewById(R.id.detalhamentoDespesas);
            btnAlterar = roupaXML.findViewById(R.id.btnAlterar);
            btnAlterar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Usuario usuarioLogado = SessionData.getInstance().getUserLogado();
                    if (usuarioLogado.perfil.equals("Usuário")) {
                    Intent intent = new Intent(v.getContext(), DespesasListVisualizarUSER.class);
                    intent.putExtra("data", ItemLista.this.dataDespesas.getText().toString());
                    intent.putExtra("origem", ItemLista.this.origemDespesas.getText().toString());
                    intent.putExtra("valor", ItemLista.this.valorDespesas.getText().toString());
                    intent.putExtra("obs",detalhamentoDespesas.getText().toString());
                    intent.putExtra("id", id);
                    v.getContext().startActivity(intent);
                }else{
                        Intent intent = new Intent(v.getContext(), DespesasListAlterar.class);
                        intent.putExtra("data", ItemLista.this.dataDespesas.getText().toString());
                        intent.putExtra("origem", ItemLista.this.origemDespesas.getText().toString());
                        intent.putExtra("valor", ItemLista.this.valorDespesas.getText().toString());
                        intent.putExtra("obs",detalhamentoDespesas.getText().toString());
                        intent.putExtra("id", id);
                        v.getContext().startActivity(intent);
                    }
                }
            });

        }
        //setters...............

    }

}


