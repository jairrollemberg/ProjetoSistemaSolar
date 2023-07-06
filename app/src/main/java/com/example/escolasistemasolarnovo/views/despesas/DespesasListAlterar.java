package com.example.escolasistemasolarnovo.views.despesas;

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

import com.example.escolasistemasolarnovo.DAO.DespesasDAO;
import com.example.escolasistemasolarnovo.R;
import com.example.escolasistemasolarnovo.entidades.Despesas;
import com.example.escolasistemasolarnovo.utils.BancoDeDados;

import java.util.Calendar;


public class DespesasListAlterar extends AppCompatActivity implements View.OnClickListener {

    //Atributos*****
    EditText origemDespesasAlterar, detalhamentoDespesasAlterar, valorDespesasAlterar;
    int id;
    ImageButton ib_voltar_inicio;
    Button btnAlterarDespesas, btnExcluirDespesas;
    TextView dataDespesasAlterar;
    BancoDeDados bd;

    private DatePickerDialog datePickerDialog;
    private Button dataBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despesas_list_alterar);
        initDatePicker();

        //******captura dos componentes******
        dataDespesasAlterar = findViewById(R.id.dataDespesasAlterar);
        origemDespesasAlterar = findViewById(R.id.origemDespesasAlterar);
        valorDespesasAlterar = findViewById(R.id.valorDespesasAlterar);
        detalhamentoDespesasAlterar = findViewById(R.id.detalhamentoDespesasAlterar);
        ib_voltar_inicio = findViewById(R.id.ib_voltar_inicio);
        btnAlterarDespesas = findViewById(R.id.btnAlterarDespesas);
        btnExcluirDespesas = findViewById(R.id.btnExcluirDespesas);
        dataBtn = findViewById(R.id.dataPickerBtn);
        dataBtn.setText(getTodaysDate());
        btnExcluirDespesas.setOnClickListener(this);
        btnAlterarDespesas.setOnClickListener(this);


        Bundle extras = getIntent().getExtras();
        if(extras != null){
            dataBtn.setText(extras.getString("data"));
            origemDespesasAlterar.setText(extras.getString("origem"));
            valorDespesasAlterar.setText(extras.getString("valor"));
            detalhamentoDespesasAlterar.setText(extras.getString("obs"));
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
            Intent intent = new Intent(DespesasListAlterar.this, DespesasList.class);
            startActivity(intent);
        });
    }

    @Override
    public void onClick(View origem) {
        String dataDespesas = dataBtn.getText().toString();
        String origemDespesas = origemDespesasAlterar.getText().toString();
        String valorDespesas= valorDespesasAlterar.getText().toString();
        String detalhamento_despesas = detalhamentoDespesasAlterar.getText().toString();

        if (origem.getId() == R.id.btnAlterarDespesas) {
            if (dataDespesas.isEmpty() || origemDespesas.isEmpty() || valorDespesas.isEmpty() || detalhamento_despesas.isEmpty()) {//***verifica se os campos nao estao vazios***
                Toast.makeText(this, "Dados Incompletos!!",Toast.LENGTH_LONG).show();
            }else{
                alterarDados();
            }
        }else if (origem.getId() == R.id.btnExcluirDespesas){
            excluirDados();
        }
    }

    private void excluirDados(){ //exclui dados do banco
        Despesas despesas = new Despesas();
        despesas.id = id;

        bd = Room.databaseBuilder(getApplicationContext(), BancoDeDados.class, "BancoApp").allowMainThreadQueries().build();
        DespesasDAO despesasDAO = bd.getDespesasDAO();

        despesasDAO.delete(despesas);

        Toast.makeText(this, "Remoção Realizada!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(DespesasListAlterar.this, DespesasList.class);
        startActivity(intent);
        finish();
    }

    private void alterarDados() { //alterar informaçoes no banco
        String dataDespesas = dataBtn.getText().toString();
        String origemDespesas = origemDespesasAlterar.getText().toString();
        String valorDespesas = valorDespesasAlterar.getText().toString();
        String detalhamentoDespesas = detalhamentoDespesasAlterar.getText().toString();


        bd = Room.databaseBuilder(getApplicationContext(), BancoDeDados.class, "BancoApp").allowMainThreadQueries().build();
        DespesasDAO despesasDAO = bd.getDespesasDAO();

        Despesas despesas = new Despesas();
        despesas.id = id;
        despesas.dataDespesas = dataDespesas;
        despesas.origemDespesas = origemDespesas;
        despesas.valorDespesas = valorDespesas;
        despesas.detalhamento_despesas = detalhamentoDespesas;

        despesasDAO.update(despesas);

        Toast.makeText(this, "Alteração Realizada!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(DespesasListAlterar.this, DespesasList.class);
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
