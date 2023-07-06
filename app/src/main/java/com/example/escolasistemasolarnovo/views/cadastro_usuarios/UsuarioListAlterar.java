package com.example.escolasistemasolarnovo.views.cadastro_usuarios;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;

import android.widget.Toast;

import com.example.escolasistemasolarnovo.DAO.UsuarioDAO;
import com.example.escolasistemasolarnovo.R;
import com.example.escolasistemasolarnovo.entidades.Usuario;
import com.example.escolasistemasolarnovo.utils.BancoDeDados;

public class UsuarioListAlterar extends AppCompatActivity implements View.OnClickListener {

    //Atributos*****
    EditText editTextNomeAlterar, editTextCPFAlterar, editTextEmailAlterar,editTextTelefoneAlterar,editTextPerfilAlterar,editTextSenhaAlterar;
    int id;
    ImageButton ib_voltar_inicio;
    Button btnAlterarUsuario, btnExcluirUsuario;
    SwitchCompat switchPerfilAlterar;
    BancoDeDados bd;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_alterar);

        //******captura dos componentes******
        editTextNomeAlterar = findViewById(R.id.editTextNomeAlterar);
        editTextCPFAlterar = findViewById(R.id.editTextCPFAlterar);
        editTextEmailAlterar = findViewById(R.id.editTextEmailAlterar);
        editTextTelefoneAlterar = findViewById(R.id.editTextTelefoneAlterar);
        editTextPerfilAlterar = findViewById(R.id.editTextPerfilAlterar);
        editTextSenhaAlterar = findViewById(R.id.editTextSenhaAlterar);
        switchPerfilAlterar = findViewById(R.id.switchPerfilAlterar);
        btnAlterarUsuario = findViewById(R.id.btnAlterarUsuario);
        btnExcluirUsuario = findViewById(R.id.btnExcluirUsuario);
        ib_voltar_inicio = findViewById(R.id.ib_voltar_inicio);
        btnExcluirUsuario.setOnClickListener(this);
        btnAlterarUsuario.setOnClickListener(this);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            editTextNomeAlterar.setText(extras.getString("nome"));
            editTextEmailAlterar.setText(extras.getString("email"));
            editTextCPFAlterar.setText(extras.getString("cpf"));
            editTextTelefoneAlterar.setText(extras.getString("telefone"));
            editTextPerfilAlterar.setText(extras.getString("perfil"));
            editTextSenhaAlterar.setText(extras.getString("senha"));
            switchPerfilAlterar.setChecked(true);
            id = extras.getInt("id");
        }

        //insere o tipo do perfil do usuario
        switchPerfilAlterar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    editTextPerfilAlterar.setText("Administrador");
                }else {
                    editTextPerfilAlterar.setText("Usuário");
                }

            }
        });
    }
    public void cliqueListner(View view){
        ib_voltar_inicio.setOnClickListener(v -> {
            Intent intent = new Intent(UsuarioListAlterar.this, UsuarioList.class);
            startActivity(intent);
        });
    }

    @Override
    public void onClick(View origem) {
        String editNomeAlterar = editTextNomeAlterar.getText().toString();
        String editEmailAlterar = editTextEmailAlterar.getText().toString();
        String editCPFAlterar = editTextCPFAlterar.getText().toString();
        String editTelefoneAlterar = editTextTelefoneAlterar.getText().toString();
        String editPerfilAlterar = editTextPerfilAlterar.getText().toString();
        String editSenhaAlterar = editTextSenhaAlterar.getText().toString();


        if (origem.getId() == R.id.btnAlterarUsuario) {
            if (editNomeAlterar.isEmpty() || editEmailAlterar.isEmpty() || editCPFAlterar.isEmpty() || editTelefoneAlterar.isEmpty() || editPerfilAlterar.isEmpty() || editSenhaAlterar.isEmpty()) {//***verifica se os campos nao estao vazios***
                Toast.makeText(this, "Dados Incompletos!!",Toast.LENGTH_LONG).show();
            }else{
                alterarDados();
            }
        }else if (origem.getId() == R.id.btnExcluirUsuario){
            excluirDados();
        }
    }
    private void excluirDados(){ //exclui dados do banco
        Usuario usuario = new Usuario();
        usuario.id = id;

        bd = Room.databaseBuilder(getApplicationContext(), BancoDeDados.class, "BancoApp").allowMainThreadQueries().build();
        UsuarioDAO usuarioDAO = bd.getUsuarioDao();

        usuarioDAO.delete(usuario);

        Toast.makeText(this, "Remoção Realizada!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(UsuarioListAlterar.this, UsuarioList.class);
        startActivity(intent);
        finish();
    }
    private void alterarDados() { //alterar informaçoes no banco
        String editNomeAlterar = editTextNomeAlterar.getText().toString();
        String editEmailAlterar = editTextEmailAlterar.getText().toString();
        String editCPFAlterar = editTextCPFAlterar.getText().toString();
        String editTelefoneAlterar = editTextTelefoneAlterar.getText().toString();
        String editPerfilAlterar = editTextPerfilAlterar.getText().toString();
        String editSenhaAlterar = editTextSenhaAlterar.getText().toString();


        bd = Room.databaseBuilder(getApplicationContext(), BancoDeDados.class, "BancoApp").allowMainThreadQueries().build();
        UsuarioDAO usuarioDAO = bd.getUsuarioDao();

        Usuario usuario = new Usuario();
        usuario.id = id;
        usuario.nome = editNomeAlterar;
        usuario.cpf = editCPFAlterar;
        usuario.telefone = editTelefoneAlterar;
        usuario.email = editEmailAlterar;
        usuario.perfil = editPerfilAlterar;
        usuario.senha = editSenhaAlterar;

        usuarioDAO.update(usuario);

        Toast.makeText(this, "Alteração Realizada!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(UsuarioListAlterar.this, UsuarioList.class);
        startActivity(intent);
        finish();
    }


}