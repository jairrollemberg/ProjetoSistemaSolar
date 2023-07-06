package com.example.escolasistemasolarnovo.views.custos;

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

import com.example.escolasistemasolarnovo.DAO.CustosDAO;
import com.example.escolasistemasolarnovo.R;
import com.example.escolasistemasolarnovo.entidades.Custos;
import com.example.escolasistemasolarnovo.utils.BancoDeDados;

import java.util.Calendar;


public class CustoListAlterar extends AppCompatActivity implements View.OnClickListener {

    //Atributos*****
    EditText origemCustoAlterar, detalhamentoCustoAlterar, valorCustoAlterar;
    int id;
    ImageButton ib_voltar_inicio;
    Button btnAlterarCusto, btnExcluirCusto;
    TextView dataCustoAlterar;
    BancoDeDados bd;

    private DatePickerDialog datePickerDialog;
    private Button dataBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custos_alterar);
        initDatePicker();

        //******captura dos componentes******
        dataCustoAlterar = findViewById(R.id.dataCustoAlterar);
        origemCustoAlterar = findViewById(R.id.origemCustoAlterar);
        valorCustoAlterar = findViewById(R.id.valorCustoAlterar);
        detalhamentoCustoAlterar = findViewById(R.id.detalhamentoCustoAlterar);
        ib_voltar_inicio = findViewById(R.id.ib_voltar_inicio);
        btnAlterarCusto = findViewById(R.id.btnAlterarCusto);
        btnExcluirCusto = findViewById(R.id.btnExcluirCusto);
        dataBtn = findViewById(R.id.dataPickerBtn);
        dataBtn.setText(getTodaysDate());
        btnExcluirCusto.setOnClickListener(this);
        btnAlterarCusto.setOnClickListener(this);


        Bundle extras = getIntent().getExtras();
        if(extras != null){
            dataBtn.setText(extras.getString("data"));
            origemCustoAlterar.setText(extras.getString("origem"));
            valorCustoAlterar.setText(extras.getString("valor"));
            detalhamentoCustoAlterar.setText(extras.getString("obs"));
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
            Intent intent = new Intent(CustoListAlterar.this, CustoList.class);
            startActivity(intent);
        });
    }

    @Override
    public void onClick(View origem) {
        String dataCusto = dataBtn.getText().toString();
        String origemCusto = origemCustoAlterar.getText().toString();
        String valorCusto = valorCustoAlterar.getText().toString();
        String detalhamento_custo = detalhamentoCustoAlterar.getText().toString();

        if (origem.getId() == R.id.btnAlterarCusto) {
            if (dataCusto.isEmpty() || origemCusto.isEmpty() || valorCusto.isEmpty() || detalhamento_custo.isEmpty()) {//***verifica se os campos nao estao vazios***
                Toast.makeText(this, "Dados Incompletos!!",Toast.LENGTH_LONG).show();
            }else{
                alterarDados();
            }
        }else if (origem.getId() == R.id.btnExcluirCusto){
           excluirDados();
        }
    }

    private void excluirDados(){ //exclui dados do banco
        Custos custos = new  Custos();
        custos.id = id;

        bd = Room.databaseBuilder(getApplicationContext(), BancoDeDados.class, "BancoApp").allowMainThreadQueries().build();
        CustosDAO custosDAO = bd.getCustosDAO();

        custosDAO.delete(custos);

        Toast.makeText(this, "Remoção Realizada!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(CustoListAlterar.this, CustoList.class);
        startActivity(intent);
        finish();
    }

    private void alterarDados() { //alterar informaçoes no banco
        String dataCusto = dataBtn.getText().toString();
        String origemCusto = origemCustoAlterar.getText().toString();
        String valorCusto = valorCustoAlterar.getText().toString();
        String detalhamentoCusto = detalhamentoCustoAlterar.getText().toString();


        bd = Room.databaseBuilder(getApplicationContext(), BancoDeDados.class, "BancoApp").allowMainThreadQueries().build();
        CustosDAO custosDAO = bd.getCustosDAO();

        Custos custos = new  Custos();
        custos.id = id;
        custos.dataCusto = dataCusto;
        custos.origemCusto = origemCusto;
        custos.valorCusto = valorCusto;
        custos.detalhamento_custo = detalhamentoCusto;

        custosDAO.update(custos);

        Toast.makeText(this, "Alteração Realizada!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(CustoListAlterar.this, CustoList.class);
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
