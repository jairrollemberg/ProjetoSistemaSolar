package com.example.escolasistemasolarnovo.views.entradas_saidas.saidas;

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

import com.example.escolasistemasolarnovo.DAO.Outras_EntradasDAO;
import com.example.escolasistemasolarnovo.DAO.Outras_SaidasDAO;
import com.example.escolasistemasolarnovo.R;
import com.example.escolasistemasolarnovo.entidades.Outras_Entradas;
import com.example.escolasistemasolarnovo.entidades.Outras_Saidas;
import com.example.escolasistemasolarnovo.entidades.Usuario;
import com.example.escolasistemasolarnovo.utils.BancoDeDados;
import com.example.escolasistemasolarnovo.utils.SessionData;
import com.example.escolasistemasolarnovo.views.entradas_saidas.entradas.OutrasEntradasListAlterar;
import com.example.escolasistemasolarnovo.views.entradas_saidas.entradas.OutrasEntradasListVisualizarUSER;

import java.util.ArrayList;

public class OutrasSaidasListAdapter extends RecyclerView.Adapter<OutrasSaidasListAdapter.ItemLista> {
    // atributos..............
    ArrayList<Outras_Saidas> fonteDadosOriginal, fonteDadosAtual;
    LayoutInflater inflater;
    BancoDeDados bd;
    Context context;

    //contrutor...............
    public OutrasSaidasListAdapter(Context context){
        this.context = context;
        bd = Room.databaseBuilder(context.getApplicationContext(), BancoDeDados.class,"BancoApp").allowMainThreadQueries().build();
        //Obtendo o DAO do outras_entradas
        Outras_SaidasDAO outras_saidasDAO = bd.getOutras_SaidasDAO();
        fonteDadosOriginal = (ArrayList<Outras_Saidas>) outras_saidasDAO.getAll();
        fonteDadosAtual = fonteDadosOriginal;
        inflater = LayoutInflater.from(context);
    }
    public void mudarFonteDados(ArrayList<Outras_Saidas> novosDados){
        if (novosDados.size()==0) fonteDadosAtual = fonteDadosOriginal;
        fonteDadosAtual = novosDados;
        notifyDataSetChanged(); //notifica que a fonte de dados  e recontroi os viewholders
    }
    //Metodos abstratos do Recycleview.adapter ..........
    @NonNull
    @Override
    public OutrasSaidasListAdapter.ItemLista onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View roupaXML = inflater.inflate(R.layout.listview_outras_saidas,parent,false);
        OutrasSaidasListAdapter.ItemLista novoItemLista = new OutrasSaidasListAdapter.ItemLista(roupaXML);
        return novoItemLista;
    }
    @Override
    public void onBindViewHolder(@NonNull OutrasSaidasListAdapter.ItemLista viewholder, int position) {
        viewholder.dataOutrasSaidas.setText(fonteDadosAtual.get(position).dataOutrasSaidas);
        viewholder.origemOutrasSaidas.setText(fonteDadosAtual.get(position).origemOutrasSaidas);
        viewholder.valorOutrasSaidas.setText(fonteDadosAtual.get(position).valorOutrasSaidas);
        viewholder.detalhamentoOutrasSaidas.setText(fonteDadosAtual.get(position).detalhamentoOutrasSaidas);
        viewholder.id = fonteDadosAtual.get(position).id;
    }
    @Override
    public int getItemCount() {
        return fonteDadosAtual.size();
    }

    //Classe interna que representa cada item da lista(ViewHolder)....................

    public static class ItemLista extends RecyclerView.ViewHolder{

        // atributos..............
        TextView origemOutrasSaidas, dataOutrasSaidas, valorOutrasSaidas, detalhamentoOutrasSaidas;
        Integer id;
        Button btnAlterar;

        //contrutor...............
        public ItemLista(View roupaXML){
            super(roupaXML); //obrigatorio
            dataOutrasSaidas = roupaXML.findViewById(R.id.dataOutrasSaidas);
            origemOutrasSaidas = roupaXML.findViewById(R.id.mesOutrasSaidas);
            valorOutrasSaidas= roupaXML.findViewById(R.id.valorOutrasSaidas);
            detalhamentoOutrasSaidas = roupaXML.findViewById(R.id.detalhamentoOutrasSaidas);
            btnAlterar = roupaXML.findViewById(R.id.btnAlterar);
            btnAlterar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Usuario usuarioLogado = SessionData.getInstance().getUserLogado();
                    if (usuarioLogado.perfil.equals("Usuário")) {
                    Intent intent = new Intent(v.getContext(), OutrasSaidasListVisualizarUSER.class);
                    intent.putExtra("data", ItemLista.this.dataOutrasSaidas.getText().toString());
                    intent.putExtra("origem", ItemLista.this.origemOutrasSaidas.getText().toString());
                    intent.putExtra("valor", ItemLista.this.valorOutrasSaidas.getText().toString());
                    intent.putExtra("obs",detalhamentoOutrasSaidas.getText().toString());
                    intent.putExtra("id", id);
                    v.getContext().startActivity(intent);
                }else {
                        Intent intent = new Intent(v.getContext(), OutrasSaidasListAlterar.class);
                        intent.putExtra("data", ItemLista.this.dataOutrasSaidas.getText().toString());
                        intent.putExtra("origem", ItemLista.this.origemOutrasSaidas.getText().toString());
                        intent.putExtra("valor", ItemLista.this.valorOutrasSaidas.getText().toString());
                        intent.putExtra("obs",detalhamentoOutrasSaidas.getText().toString());
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






