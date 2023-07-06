package com.example.escolasistemasolarnovo.views;



import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.escolasistemasolarnovo.DAO.UsuarioDAO;
import com.example.escolasistemasolarnovo.R;
import com.example.escolasistemasolarnovo.entidades.Custos;
import com.example.escolasistemasolarnovo.entidades.Despesas;
import com.example.escolasistemasolarnovo.entidades.Outras_Entradas;
import com.example.escolasistemasolarnovo.entidades.Outras_Saidas;
import com.example.escolasistemasolarnovo.entidades.Receitas;
import com.example.escolasistemasolarnovo.entidades.Usuario;
import com.example.escolasistemasolarnovo.utils.BancoDeDados;
import com.example.escolasistemasolarnovo.utils.SessionData;
import com.example.escolasistemasolarnovo.views.cadastro_usuarios.UsuarioCadastro;
import com.example.escolasistemasolarnovo.views.menu.MenuInicialADM;
import com.example.escolasistemasolarnovo.views.menu.MenuInicialUSER;

import java.util.List;


public class TelaLogin extends AppCompatActivity implements View.OnClickListener {

    // atributos ---------------------------------------
    EditText editLogin, editTextSenha;
    Button btnLogin;
    TextView txtRecuperarSenha, CriarConta;
    BancoDeDados bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);
        RestabelecerDados();

        //********************* captura de componentes *********************
        editLogin = findViewById(R.id.editLogin);
        editTextSenha = findViewById(R.id.editTextSenha);
        btnLogin = findViewById(R.id.btnLogin);
        txtRecuperarSenha = findViewById(R.id.txtRecuperarSenha);
        CriarConta = findViewById(R.id.CriarConta);

        //***************eventos de componentes*****
        btnLogin.setOnClickListener(this);
        txtRecuperarSenha.setOnClickListener(this);
        //BOTAO TEMPORARIO CRIAR CONTA
        CriarConta.setOnClickListener(v -> {
            Intent intent = new Intent(TelaLogin.this, UsuarioCadastro.class);
            startActivity(intent);
        });
    }

    //***************metodos interface View.OnClickListener*****
    @Override
    public void onClick(View origem) {
        if(origem.getId() == R.id.btnLogin) {
            //Capturar Login e Senha
            String nome = editLogin.getText().toString();
            String senha = editTextSenha.getText().toString();

            bd = Room.databaseBuilder(getApplicationContext(), BancoDeDados.class, "BancoApp").allowMainThreadQueries().build();
            // obtem o DAO de Usuario
            UsuarioDAO usuarioDAO = bd.getUsuarioDao();
            List<Usuario> usuario = usuarioDAO.getUser(nome, senha);
            if (usuario.size() == 0) { // dados incorretos
                Toast.makeText(TelaLogin.this, "Dados do usuário incorretos!", Toast.LENGTH_SHORT).show();


            } else { // dados corretos (permissão concedida)
                //verificar se o usuario que acabou de logar é admin ou user.
                SessionData.getInstance().setUserLogado(usuario.get(0));
                if (usuario.get(0).perfil.equals("Administrador")){
                    Intent intent = new Intent(TelaLogin.this, MenuInicialADM.class);
                    Toast.makeText(TelaLogin.this, "Login de ADMINISTRADOR concedido!", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    finish();
                }
               else {
                    Intent intent = new Intent(TelaLogin.this, MenuInicialUSER.class);
                    Toast.makeText(TelaLogin.this, "Login de USUÁRIO concedido!", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    finish();
                }
            }
          }
        }
    //Funcao Temporaria Para Reseta os dados no BancodeDados
    private void RestabelecerDados (){
        //TODO limpa todos os dados do banco
        bd = Room.databaseBuilder(getApplicationContext(), BancoDeDados.class, "BancoApp").allowMainThreadQueries().build();
        bd.clearAllTables();
        //TODO Inserir Dados de Usuarios
        bd.getUsuarioDao().insert(new Usuario(1, "Jair", "000.000.000-00","0000000000","@gmail.com", "Administrador", "1234"));
        bd.getUsuarioDao().insert(new Usuario(2, "ADM", "000.000.000-00","0000000000","@gmail.com", "Administrador", "1234"));
        bd.getUsuarioDao().insert(new Usuario(3, "Luiz Henrique", "000.000.000-00","0000000000","@gmail.com", "Administrador", "1234"));
        bd.getUsuarioDao().insert(new Usuario(4, "Joao", "000.000.000-00","0000000000","@gmail.com", "Usuário", "1234"));
        bd.getUsuarioDao().insert(new Usuario(5, "Maria", "000.000.000-00","0000000000","@gmail.com", "Usuário", "1234"));
        bd.getUsuarioDao().insert(new Usuario(6, "Joana", "000.000.000-00","0000000000","@gmail.com", "Usuário", "1234"));
        bd.getUsuarioDao().insert(new Usuario(7, "Victor", "000.000.000-00","0000000000","@gmail.com", "Usuário", "1234"));
        bd.getUsuarioDao().insert(new Usuario(8, "USER", "000.000.000-00","0000000000","@gmail.com", "Usuário", "1234"));

        //TODO Inseção de Custos
        bd.getCustosDAO().insert(new Custos(1,"10/DEZ/2022","Cadeiras Novas","120.00", "Cadeiras novas azuis." ));
        bd.getCustosDAO().insert(new Custos(2,"15/NOV/2022","Quadros Novos","150.00", "Quadros novos para salas de aula." ));
        bd.getCustosDAO().insert(new Custos(3,"22/OUT/2022","Computador","500.00", "Computador para a recepção." ));

        //TODO Inseção de Despesas
        bd.getDespesasDAO().insert(new Despesas(1,"05/DEZ/2021","EMBASA","200.00", "Fatura da EMBASA do mes NOVEMBRO."));
        bd.getDespesasDAO().insert(new Despesas(2,"06/DEZ/2021","COELBA","250.00", "Fatura da COELBA do mes NOVEMBRO."));
        bd.getDespesasDAO().insert(new Despesas(3,"10/DEZ/2021","TELEFONE","130.00", "Fatura da CLARO do mes NOVEMBRO."));

        //TODO Inseção de Receitas
        bd.getReceitasDAO().insert(new Receitas(1,"06/DEZ/2021","Mensalidades Dezembro","1100.0", "Valor total das mensalidades dos alunos do mes de Dezembro" ));
        bd.getReceitasDAO().insert(new Receitas(2,"06/NOV/2021","Mensalidades Novembro","1000.0", "Valor total das mensalidades dos alunos do mes de Novembro" ));
        bd.getReceitasDAO().insert(new Receitas(3,"06/OUT/2021","Mensalidades Outubro","1300.0", "Valor total das mensalidades dos alunos do mes de Outubro" ));

        //TODO Inseção de Outras_Entradas
        bd.getOutras_EntradasDAO().insert(new Outras_Entradas(1,"09/DEZ/2021","Feira de ciências ","1000.00", "Arrecadação das vendas na Feira de ciências realizada em 02/12/2021 " ));
        bd.getOutras_EntradasDAO().insert(new Outras_Entradas(2,"19/MAI/2021","Brechó dos alunos ","1150.00", "Arrecadação das vendas na Brechó dos alunos realizado em 15/11/2021 " ));
        bd.getOutras_EntradasDAO().insert(new Outras_Entradas(3,"30/ABR/2021","Doação ","650.00", "Total de doações recebidas no mes de Abril " ));

        //TODO Inseção de Outras_Saidas
        bd.getOutras_SaidasDAO().insert(new Outras_Saidas(1,"07/DEZ/2021","Lampadas","100.0", "Compra de 5 lâmpadas para os corredores"));
        bd.getOutras_SaidasDAO().insert(new Outras_Saidas(2,"07/DEZ/2021","Manutenção do Ar-condicionado","150.0", "Manutenção realizada em 07/12 no ar-condicionado da recepção"));
        bd.getOutras_SaidasDAO().insert(new Outras_Saidas(3,"11/JAN/2021","Pintura da M12","60.0", "Pintura da porta da sala M12"));

    }
    }
