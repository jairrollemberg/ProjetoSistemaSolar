package com.example.escolasistemasolarnovo.views.entradas_saidas.entradas;

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

import com.example.escolasistemasolarnovo.DAO.CustosDAO;
import com.example.escolasistemasolarnovo.DAO.Outras_EntradasDAO;
import com.example.escolasistemasolarnovo.R;
import com.example.escolasistemasolarnovo.entidades.Custos;
import com.example.escolasistemasolarnovo.entidades.Outras_Entradas;
import com.example.escolasistemasolarnovo.entidades.Usuario;
import com.example.escolasistemasolarnovo.utils.BancoDeDados;
import com.example.escolasistemasolarnovo.utils.SessionData;
import com.example.escolasistemasolarnovo.views.custos.CustoListAdapter;
import com.example.escolasistemasolarnovo.views.custos.CustoListAlterar;

import java.util.ArrayList;

public class OutrasEntradasListAdapter extends RecyclerView.Adapter<OutrasEntradasListAdapter.ItemLista> {
    // atributos..............
    ArrayList<Outras_Entradas> fonteDadosOriginal, fonteDadosAtual;
    LayoutInflater inflater;
    BancoDeDados bd;
    Context context;

    //contrutor...............
    public OutrasEntradasListAdapter(Context context){
        this.context = context;
        bd = Room.databaseBuilder(context.getApplicationContext(), BancoDeDados.class,"BancoApp").allowMainThreadQueries().build();
        //Obtendo o DAO do outras_entradas
        Outras_EntradasDAO outras_entradasDAO = bd.getOutras_EntradasDAO();
        fonteDadosOriginal = (ArrayList<Outras_Entradas>) outras_entradasDAO.getAll();
        fonteDadosAtual = fonteDadosOriginal;
        inflater = LayoutInflater.from(context);
    }
    public void mudarFonteDados(ArrayList<Outras_Entradas> novosDados){
        if (novosDados.size()==0) fonteDadosAtual = fonteDadosOriginal;
        fonteDadosAtual = novosDados;
        notifyDataSetChanged(); //notifica que a fonte de dados  e recontroi os viewholders
    }
    //Metodos abstratos do Recycleview.adapter ..........
    @NonNull
    @Override
    public OutrasEntradasListAdapter.ItemLista onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View roupaXML = inflater.inflate(R.layout.listview_outras_entradas,parent,false);
        OutrasEntradasListAdapter.ItemLista novoItemLista = new OutrasEntradasListAdapter.ItemLista(roupaXML);
        return novoItemLista;
    }
    @Override
    public void onBindViewHolder(@NonNull OutrasEntradasListAdapter.ItemLista viewholder, int position) {
        viewholder.dataOutrasEntradas.setText(fonteDadosAtual.get(position).dataOutrasEntradas);
        viewholder.origemOutrasEntradas.setText(fonteDadosAtual.get(position).origemOutrasEntradas);
        viewholder.valorOutrasEntradas.setText(fonteDadosAtual.get(position).valorOutrasEntradas);
        viewholder.detalhamentoOutrasEntradas.setText(fonteDadosAtual.get(position).detalhamentoOutrasEntradas);
        viewholder.id = fonteDadosAtual.get(position).id;
    }
    @Override
    public int getItemCount() {
        return fonteDadosAtual.size();
    }

    //Classe interna que representa cada item da lista(ViewHolder)....................

    public static class ItemLista extends RecyclerView.ViewHolder{

        // atributos..............
        TextView origemOutrasEntradas, dataOutrasEntradas, valorOutrasEntradas, detalhamentoOutrasEntradas;
        Integer id;
        Button btnAlterar;

        //contrutor...............
        public ItemLista(View roupaXML){
            super(roupaXML); //obrigatorio
            dataOutrasEntradas = roupaXML.findViewById(R.id.dataOutrasEntradas);
            origemOutrasEntradas = roupaXML.findViewById(R.id.mesOutrasEntradas);
            valorOutrasEntradas= roupaXML.findViewById(R.id.valorOutrasEntradas);
            detalhamentoOutrasEntradas = roupaXML.findViewById(R.id.detalhamentoOutrasEntrada);
            btnAlterar = roupaXML.findViewById(R.id.btnAlterar);
            btnAlterar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Usuario usuarioLogado = SessionData.getInstance().getUserLogado();
                    if (usuarioLogado.perfil.equals("Usuário")) {
                    Intent intent = new Intent(v.getContext(), OutrasEntradasListVisualizarUSER.class);
                    intent.putExtra("data", ItemLista.this.dataOutrasEntradas.getText().toString());
                    intent.putExtra("origem", ItemLista.this.origemOutrasEntradas.getText().toString());
                    intent.putExtra("valor", ItemLista.this.valorOutrasEntradas.getText().toString());
                    intent.putExtra("obs",detalhamentoOutrasEntradas.getText().toString());
                    intent.putExtra("id", id);
                    v.getContext().startActivity(intent);
                }else {
                        Intent intent = new Intent(v.getContext(), OutrasEntradasListAlterar.class);
                        intent.putExtra("data", ItemLista.this.dataOutrasEntradas.getText().toString());
                        intent.putExtra("origem", ItemLista.this.origemOutrasEntradas.getText().toString());
                        intent.putExtra("valor", ItemLista.this.valorOutrasEntradas.getText().toString());
                        intent.putExtra("obs",detalhamentoOutrasEntradas.getText().toString());
                        intent.putExtra("id", id);
                        v.getContext().startActivity(intent);
                    }
                }
            });

        }
        //setters...............

        //Evento...............


    }

}






