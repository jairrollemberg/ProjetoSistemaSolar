package com.example.escolasistemasolarnovo.views.cadastro_usuarios;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.escolasistemasolarnovo.DAO.UsuarioDAO;
import com.example.escolasistemasolarnovo.R;
import com.example.escolasistemasolarnovo.entidades.Usuario;
import com.example.escolasistemasolarnovo.utils.BancoDeDados;

import java.util.ArrayList;

public class UsuarioListAdapter extends RecyclerView.Adapter<UsuarioListAdapter.ItemLista> {
    ArrayList<Usuario> fonteDadosOriginal, fonteDadosAtual;
    LayoutInflater inflater;
    BancoDeDados bd;
    Context context;

    //construtor
    public UsuarioListAdapter(Context context) {
        this.context = context;
        bd = Room.databaseBuilder(context.getApplicationContext(), BancoDeDados.class, "BancoApp").allowMainThreadQueries().build();
        //Obtendo o DAO do usuario
        UsuarioDAO usuarioDAO = bd.getUsuarioDao();
        fonteDadosOriginal = (ArrayList<Usuario>) usuarioDAO.getAll();
        fonteDadosAtual = fonteDadosOriginal;
        inflater = LayoutInflater.from(context);
    }

    public void mudarFonteDados(ArrayList<Usuario> novosDados) {
        if (novosDados.size() == 0) fonteDadosAtual = fonteDadosOriginal;
        fonteDadosAtual = novosDados;
        notifyDataSetChanged(); //notifica que a fonte de dados  e recontroi os viewholders
    }

    //Metodos Abstratos do RecycleView.adapter
    @NonNull
    @Override
    public ItemLista onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View roupaXML = inflater.inflate(R.layout.listview_usuario, parent, false);
        ItemLista novoItemLista = new ItemLista(roupaXML);
        return novoItemLista;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemLista viewholder, int position) {
        viewholder.nomeUsuario.setText(fonteDadosAtual.get(position).nome);
        viewholder.emailUsuario.setText(fonteDadosAtual.get(position).email);
        viewholder.perfilUsuario.setText(fonteDadosAtual.get(position).perfil);
        viewholder.cpfUsuario.setText(fonteDadosAtual.get(position).cpf);
        viewholder.telefoneUsuario.setText(fonteDadosAtual.get(position).telefone);
        viewholder.senhaUsuario.setText(fonteDadosAtual.get(position).senha);
        viewholder.id = fonteDadosAtual.get(position).id;
    }

    @Override
    public int getItemCount() {
        return fonteDadosAtual.size();
    }

//Classe interna que representa cada item da lista(ViewHolder)....................

    public static class ItemLista extends RecyclerView.ViewHolder {

        // atributos..............
        TextView nomeUsuario, emailUsuario, perfilUsuario, cpfUsuario, telefoneUsuario, senhaUsuario;
        Integer id;
        SwitchCompat switchPerfil;
        Button btnAlterar;

        //contrutor...............
        public ItemLista(View roupaXML) {
            super(roupaXML); //obrigatorio
            nomeUsuario = roupaXML.findViewById(R.id.nomeUsuario);
            emailUsuario = roupaXML.findViewById(R.id.emailUsuario);
            perfilUsuario = roupaXML.findViewById(R.id.perfilUsuario);
            cpfUsuario = roupaXML.findViewById(R.id.cpfUsuario);
            telefoneUsuario = roupaXML.findViewById(R.id.telefoneUsuario);
            senhaUsuario = roupaXML.findViewById(R.id.senhaUsuario);
            switchPerfil = roupaXML.findViewById(R.id.switchPerfil);
            btnAlterar = roupaXML.findViewById(R.id.btnAlterar);
            btnAlterar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), UsuarioListAlterar.class);
                    intent.putExtra("nome", ItemLista.this.nomeUsuario.getText().toString());
                    intent.putExtra("email", ItemLista.this.emailUsuario.getText().toString());
                    intent.putExtra("perfil", ItemLista.this.perfilUsuario.getText().toString());
                    intent.putExtra("cpf", ItemLista.this.cpfUsuario.getText().toString());
                    intent.putExtra("telefone", ItemLista.this.telefoneUsuario.getText().toString());
                    intent.putExtra("senha", ItemLista.this.senhaUsuario.getText().toString());
                    intent.putExtra("switchPefil", ItemLista.this.switchPerfil.getText().toString());
                    intent.putExtra("id", id);
                    v.getContext().startActivity(intent);
                }
            });

        }
        //setters...............

        //Evento...............

    }
}
