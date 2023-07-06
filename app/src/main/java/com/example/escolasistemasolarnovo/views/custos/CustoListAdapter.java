package com.example.escolasistemasolarnovo.views.custos;

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
import com.example.escolasistemasolarnovo.R;
import com.example.escolasistemasolarnovo.entidades.Custos;
import com.example.escolasistemasolarnovo.entidades.Usuario;
import com.example.escolasistemasolarnovo.utils.BancoDeDados;
import com.example.escolasistemasolarnovo.utils.SessionData;

import java.util.ArrayList;

public class CustoListAdapter extends RecyclerView.Adapter<CustoListAdapter.ItemLista> {
    // atributos..............
    ArrayList<Custos> fonteDadosOriginal, fonteDadosAtual;
    LayoutInflater inflater;
    BancoDeDados bd;
    Context context;

    //contrutor...............
    public CustoListAdapter(Context context){
        this.context = context;
        bd = Room.databaseBuilder(context.getApplicationContext(), BancoDeDados.class,"BancoApp").allowMainThreadQueries().build();
        //Obtendo o DAO do custo
        CustosDAO custosDAO = bd.getCustosDAO();
        fonteDadosOriginal = (ArrayList<Custos>) custosDAO.getAll();
        fonteDadosAtual = fonteDadosOriginal;
        inflater = LayoutInflater.from(context);
    }

    public void mudarFonteDados(ArrayList<Custos> novosDados){
        if (novosDados.size()==0) fonteDadosAtual = fonteDadosOriginal;
        fonteDadosAtual = novosDados;
        notifyDataSetChanged(); //notifica que a fonte de dados  e recontroi os viewholders
    }
    //Metodos abstratos do Recycleview.adapter ..........
    @NonNull
    @Override
    public ItemLista onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View roupaXML = inflater.inflate(R.layout.listview_custo,parent,false);
        ItemLista novoItemLista = new ItemLista(roupaXML);
        return novoItemLista;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemLista viewholder, int position) {
        viewholder.dataCusto.setText(fonteDadosAtual.get(position).dataCusto);
        viewholder.origemCusto.setText(fonteDadosAtual.get(position).origemCusto);
        viewholder.valorCusto.setText(fonteDadosAtual.get(position).valorCusto);
        viewholder.detalhamentoCusto.setText(fonteDadosAtual.get(position).detalhamento_custo);
        viewholder.id = fonteDadosAtual.get(position).id;
    }

    @Override
    public int getItemCount() {
        return fonteDadosAtual.size();
    }

    //Classe interna que representa cada item da lista(ViewHolder)....................

    public static class ItemLista extends RecyclerView.ViewHolder{

        // atributos..............
            TextView origemCusto, dataCusto, valorCusto, detalhamentoCusto;
            Integer id;
            Button btnAlterar;

        //contrutor...............
        public ItemLista(View roupaXML){
            super(roupaXML); //obrigatorio
            dataCusto = roupaXML.findViewById(R.id.dataCusto);
            origemCusto = roupaXML.findViewById(R.id.mesCusto);
            valorCusto= roupaXML.findViewById(R.id.valorCusto);
            detalhamentoCusto = roupaXML.findViewById(R.id.detalhamentoCusto);
            btnAlterar = roupaXML.findViewById(R.id.btnAlterar);
            btnAlterar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Usuario usuarioLogado = SessionData.getInstance().getUserLogado();
                    if (usuarioLogado.perfil.equals("Usuário")) {
                        Intent intent = new Intent(v.getContext(), CustoListVisualizarUSER.class);
                        intent.putExtra("data", ItemLista.this.dataCusto.getText().toString());
                        intent.putExtra("origem", ItemLista.this.origemCusto.getText().toString());
                        intent.putExtra("valor", ItemLista.this.valorCusto.getText().toString());
                        intent.putExtra("obs", detalhamentoCusto.getText().toString());
                        intent.putExtra("id", id);
                        v.getContext().startActivity(intent);
                    }else {
                        Intent intent = new Intent(v.getContext(), CustoListAlterar.class);
                        intent.putExtra("data", ItemLista.this.dataCusto.getText().toString());
                        intent.putExtra("origem", ItemLista.this.origemCusto.getText().toString());
                        intent.putExtra("valor", ItemLista.this.valorCusto.getText().toString());
                        intent.putExtra("obs", detalhamentoCusto.getText().toString());
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
