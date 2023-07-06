package com.example.escolasistemasolarnovo.views.entradas_saidas.entradas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

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

import com.example.escolasistemasolarnovo.DAO.Outras_EntradasDAO;
import com.example.escolasistemasolarnovo.R;
import com.example.escolasistemasolarnovo.entidades.Outras_Entradas;
import com.example.escolasistemasolarnovo.utils.BancoDeDados;

import java.util.Calendar;

public class OutrasEntradasListAlterar extends AppCompatActivity implements View.OnClickListener {

    //Atributos*****
    EditText origemOutrasEntradasAlterar, detalhamentoOutrasEntradasAlterar, valorOutrasEntradasAlterar;
    int id;
    ImageButton ib_voltar_inicio;
    Button btnAlterarOutrasEntradas, btnExcluirOutrasEntradas;
    TextView dataOutrasEntradasAlterar;
    BancoDeDados bd;

    private DatePickerDialog datePickerDialog;
    private Button dataBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outras_entradas_alterar);
        initDatePicker();

        //******captura dos componentes******
        dataOutrasEntradasAlterar = findViewById(R.id.dataOutrasEntradasAlterar);
        origemOutrasEntradasAlterar = findViewById(R.id.origemOutrasEntradasAlterar);
        valorOutrasEntradasAlterar = findViewById(R.id.valorOutrasEntradasAlterar);
        detalhamentoOutrasEntradasAlterar = findViewById(R.id.detalhamentoOutrasEntradasAlterar);
        ib_voltar_inicio = findViewById(R.id.ib_voltar_inicio);
        btnAlterarOutrasEntradas = findViewById(R.id.btnAlterarOutrasEntradas);
        btnExcluirOutrasEntradas = findViewById(R.id.btnExcluirOutrasEntradas);
        dataBtn = findViewById(R.id.dataPickerBtn);
        dataBtn.setText(getTodaysDate());
        btnExcluirOutrasEntradas.setOnClickListener(this);
        btnAlterarOutrasEntradas.setOnClickListener(this);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            dataBtn.setText(extras.getString("data"));
            origemOutrasEntradasAlterar.setText(extras.getString("origem"));
            valorOutrasEntradasAlterar.setText(extras.getString("valor"));
            detalhamentoOutrasEntradasAlterar.setText(extras.getString("obs"));
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
            Intent intent = new Intent(OutrasEntradasListAlterar.this, OutrasEntradasList.class);
            startActivity(intent);
        });
    }
    @Override
    public void onClick(View origem) {
        String dataOutrasEntradas = dataBtn.getText().toString();
        String origemOutrasEntradas = origemOutrasEntradasAlterar.getText().toString();
        String valorOutrasEntradas = valorOutrasEntradasAlterar.getText().toString();
        String detalhamento_outrasEntradas = detalhamentoOutrasEntradasAlterar.getText().toString();

        if (origem.getId() == R.id.btnAlterarOutrasEntradas) {
            if (dataOutrasEntradas.isEmpty() || origemOutrasEntradas.isEmpty() || valorOutrasEntradas.isEmpty() || detalhamento_outrasEntradas.isEmpty()) {//***verifica se os campos nao estao vazios***
                Toast.makeText(this, "Dados Incompletos!!",Toast.LENGTH_LONG).show();
            }else{
                alterarDados();
            }
        }else if (origem.getId() == R.id.btnExcluirOutrasEntradas){
            excluirDados();
        }
    }
    private void excluirDados(){ //exclui dados do banco
        Outras_Entradas outras_entradas = new Outras_Entradas();
        outras_entradas.id = id;

        bd = Room.databaseBuilder(getApplicationContext(), BancoDeDados.class, "BancoApp").allowMainThreadQueries().build();
        Outras_EntradasDAO outras_entradasDAO = bd.getOutras_EntradasDAO();

        outras_entradasDAO.delete(outras_entradas);

        Toast.makeText(this, "Remoção Realizada!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(OutrasEntradasListAlterar.this, OutrasEntradasList.class);
        startActivity(intent);
        finish();
    }
    private void alterarDados() { //alterar informaçoes no banco
        String dataOutrasEntradas = dataBtn.getText().toString();
        String origemOutrasEntradas = origemOutrasEntradasAlterar.getText().toString();
        String valorOutrasEntradas = valorOutrasEntradasAlterar.getText().toString();
        String detalhamento_outrasEntradas = detalhamentoOutrasEntradasAlterar.getText().toString();


        bd = Room.databaseBuilder(getApplicationContext(), BancoDeDados.class, "BancoApp").allowMainThreadQueries().build();
        Outras_EntradasDAO outras_entradasDAO = bd.getOutras_EntradasDAO();

        Outras_Entradas outras_entradas = new Outras_Entradas();
        outras_entradas.id = id;
        outras_entradas.dataOutrasEntradas = dataOutrasEntradas;
        outras_entradas.origemOutrasEntradas = origemOutrasEntradas;
        outras_entradas.valorOutrasEntradas = valorOutrasEntradas;
        outras_entradas.detalhamentoOutrasEntradas = detalhamento_outrasEntradas;

        outras_entradasDAO.update(outras_entradas);

        Toast.makeText(this, "Alteração Realizada!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(OutrasEntradasListAlterar.this, OutrasEntradasList.class);
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