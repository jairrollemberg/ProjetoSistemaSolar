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
import android.widget.Toast;

import com.example.escolasistemasolarnovo.DAO.UsuarioDAO;
import com.example.escolasistemasolarnovo.R;
import com.example.escolasistemasolarnovo.entidades.Usuario;
import com.example.escolasistemasolarnovo.utils.BancoDeDados;
import com.example.escolasistemasolarnovo.views.custos.CustoList;
import com.example.escolasistemasolarnovo.views.custos.CustoListADD;


public class UsuarioCadastro extends AppCompatActivity implements View.OnClickListener {

    // atributos ---------------------------------------------------------------
    EditText editTextNome, editTextCPF, editTextTelefone, editTextEmail, editTextSenha, editTextPerfil;
    Button btnCadastrar, btnVoltarCadastro;
    SwitchCompat switchPerfil;
    BancoDeDados bd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_cadastro);


        //******captura dos componentes******
        editTextNome = findViewById(R.id.editTextNome);
        editTextCPF = findViewById(R.id.editTextCPF);
        editTextTelefone = findViewById(R.id.editTextTelefone);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextSenha = findViewById(R.id.editTextSenha);
        editTextPerfil = findViewById(R.id.editTextPerfil);
        switchPerfil = findViewById(R.id.switchPerfil);

        btnCadastrar = findViewById(R.id.btnCadastrar);
        btnVoltarCadastro = findViewById(R.id.btnVoltarCadastro);


        //****Eventos dos Componentes*****
        btnCadastrar.setOnClickListener(this);
        btnVoltarCadastro.setOnClickListener(this);

        //insere o tipo do perfil do usuario
        switchPerfil.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    editTextPerfil.setText("Administrador");
                }else {
                    editTextPerfil.setText("Usuário");
                }

            }
        });
    }

    @Override
    public void onClick(View origem) {

        String nome = editTextNome.getText().toString();
        String cpf = editTextCPF.getText().toString();
        String telefone = editTextTelefone.getText().toString();
        String email = editTextEmail.getText().toString();
        String senha = editTextSenha.getText().toString();
        String perfil = editTextPerfil.getText().toString();

        if (origem.getId() == R.id.btnCadastrar) {
            if (nome.isEmpty()|| email.isEmpty() || cpf.isEmpty() || telefone.isEmpty() || senha.isEmpty() || perfil.isEmpty()){//***verifica se os campos nao estao vazio***
                Toast.makeText(UsuarioCadastro.this,"Confira se não tem algum campo vazio!", Toast.LENGTH_SHORT).show();

            }else{
                capturarDados();

            }

        } else if (origem.getId() == R.id.btnVoltarCadastro) {
            finish();
        }

    }
    private void capturarDados() {
        String nome = editTextNome.getText().toString();
        String cpf = editTextCPF.getText().toString();
        String telefone = editTextTelefone.getText().toString();
        String email = editTextEmail.getText().toString();
        String senha = editTextSenha.getText().toString();
        String perfil = editTextPerfil.getText().toString();

        bd = Room.databaseBuilder(getApplicationContext(), BancoDeDados.class, "BancoApp").allowMainThreadQueries().build();
        UsuarioDAO usuarioDAO = bd.getUsuarioDao();

        //****Cria o usuario a ser inserido no banco e coloca nele os dados capturados da tela
        Usuario usuario = new Usuario();
        usuario.nome = nome;
        usuario.cpf = cpf;
        usuario.email = email;
        usuario.telefone = telefone;
        usuario.perfil = perfil;
        usuario.senha = senha;

        usuarioDAO.insert(usuario);
        Toast.makeText(this, "Usuario Cadastrado", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(UsuarioCadastro.this, UsuarioList.class);
        startActivity(intent);
        finish();
    }


}

