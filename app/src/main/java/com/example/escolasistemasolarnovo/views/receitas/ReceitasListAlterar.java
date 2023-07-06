package com.example.escolasistemasolarnovo.views.receitas;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.escolasistemasolarnovo.DAO.ReceitasDAO;
import com.example.escolasistemasolarnovo.R;
import com.example.escolasistemasolarnovo.entidades.Receitas;
import com.example.escolasistemasolarnovo.utils.BancoDeDados;

import java.util.Calendar;


public class ReceitasListAlterar extends AppCompatActivity implements View.OnClickListener {

    //Atributos*****
    EditText origemReceitasAlterar, detalhamentoReceitasAlterar, valorReceitasAlterar;
    int id;
    ImageButton ib_voltar_inicio;
    Button btnAlterarReceitas, btnExcluirReceitas;
    TextView dataReceitasAlterar;
    BancoDeDados bd;

    private DatePickerDialog datePickerDialog;
    private Button dataBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receitas_list_alterar);
        initDatePicker();

        //******captura dos componentes******
        dataReceitasAlterar = findViewById(R.id.dataReceitasAlterar);
        origemReceitasAlterar = findViewById(R.id.origemReceitasAlterar);
        valorReceitasAlterar = findViewById(R.id.valorReceitasAlterar);
        detalhamentoReceitasAlterar = findViewById(R.id.detalhamentoReceitasAlterar);
        ib_voltar_inicio = findViewById(R.id.ib_voltar_inicio);
        btnAlterarReceitas = findViewById(R.id.btnAlterarReceitas);
        btnExcluirReceitas = findViewById(R.id.btnExcluirReceitas);
        dataBtn = findViewById(R.id.dataPickerBtn);
        dataBtn.setText(getTodaysDate());
        btnExcluirReceitas.setOnClickListener(this);
        btnAlterarReceitas.setOnClickListener(this);


        Bundle extras = getIntent().getExtras();
        if(extras != null){
            dataBtn.setText(extras.getString("data"));
            origemReceitasAlterar.setText(extras.getString("origem"));
            valorReceitasAlterar.setText(extras.getString("valor"));
            detalhamentoReceitasAlterar.setText(extras.getString("obs"));
            id = extras.getInt("id");
        }
    }
    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int ano = cal.get(Calendar.YEAR);
        int mes = cal.get(Calendar.MONTH);
        mes = mes +1;
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(dia, mes, ano);
    }


    public void cliqueListner(View view){
        ib_voltar_inicio.setOnClickListener(v -> {
            Intent intent = new Intent(ReceitasListAlterar.this, ReceitasList.class);
            startActivity(intent);
        });
    }

    @Override
    public void onClick(View origem) {
        String dataReceitas = dataBtn.getText().toString();
        String origemReceitas = origemReceitasAlterar.getText().toString();
        String valorReceitas= valorReceitasAlterar.getText().toString();
        String detalhamento_receitas = detalhamentoReceitasAlterar.getText().toString();

        if (origem.getId() == R.id.btnAlterarReceitas) {
            if (dataReceitas.isEmpty() || origemReceitas.isEmpty() || valorReceitas.isEmpty() || detalhamento_receitas.isEmpty()) {//***verifica se os campos nao estao vazios***
                Toast.makeText(this, "Dados Incompletos!!",Toast.LENGTH_LONG).show();
            }else{
                alterarDados();
            }
        }else if (origem.getId() == R.id.btnExcluirReceitas){
            excluirDados();
        }
    }

    private void excluirDados(){ //exclui dados do banco
        Receitas receitas = new Receitas();
        receitas.id = id;

        bd = Room.databaseBuilder(getApplicationContext(), BancoDeDados.class, "BancoApp").allowMainThreadQueries().build();
        ReceitasDAO receitasDAO = bd.getReceitasDAO();

        receitasDAO.delete(receitas);

        Toast.makeText(this, "Remoção Realizada!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(ReceitasListAlterar.this, ReceitasList.class);
        startActivity(intent);
        finish();
    }

    private void alterarDados() { //alterar informaçoes no banco
        String dataReceitas = dataBtn.getText().toString();
        String origemReceitas = origemReceitasAlterar.getText().toString();
        String valorReceitas = valorReceitasAlterar.getText().toString();
        String detalhamentoReceitas = detalhamentoReceitasAlterar.getText().toString();


        bd = Room.databaseBuilder(getApplicationContext(), BancoDeDados.class, "BancoApp").allowMainThreadQueries().build();
        ReceitasDAO receitasDAO = bd.getReceitasDAO();

        Receitas receitas = new Receitas();
        receitas.id = id;
        receitas.dataReceita = dataReceitas;
        receitas.origemReceita = origemReceitas;
        receitas.valorReceita = valorReceitas;
        receitas.detalhamento_receita = detalhamentoReceitas;

        receitasDAO.update(receitas);

        Toast.makeText(this, "Alteração Realizada!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(ReceitasListAlterar.this, ReceitasList.class);
        startActivity(intent);
        finish();
    }
    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = (view, dia, mes, ano) -> {
            mes = mes + 1;
            String date = makeDateString(ano, mes, dia );
            dataBtn.setText(date);
        };
        Calendar cal = Calendar.getInstance();
        int ano = cal.get(Calendar.YEAR);
        int mes = cal.get(Calendar.MONTH);
        int dia = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_DARK;
        datePickerDialog = new DatePickerDialog(this, style, dateSetListener,ano,mes,dia );
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
    }

    private String makeDateString(int dia, int mes, int ano) {
        return dia + " / " + getMonthFormat(mes) + " / " + ano;
    }

    private String getMonthFormat(int mes) {
        if (mes == 1)
            return "JAN";
        if (mes == 2)
            return "FEV";
        if (mes == 3)
            return "MAR";
        if (mes == 4)
            return "ABR";
        if (mes == 5)
            return "MAI";
        if (mes == 6)
            return "JUN";
        if (mes == 7)
            return "JUL";
        if (mes == 8)
            return "AGO";
        if (mes == 9)
            return "SET";
        if (mes == 10)
            return "OUT";
        if (mes == 11)
            return "NOV";
        if (mes == 12)
            return "DEZ";
        return "JAN";
    }
    public void openDataPicker(View view) {
        datePickerDialog.show();
    }


}
