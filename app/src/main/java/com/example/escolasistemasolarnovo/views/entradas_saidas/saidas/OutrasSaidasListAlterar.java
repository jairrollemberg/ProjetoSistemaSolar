package com.example.escolasistemasolarnovo.views.entradas_saidas.saidas;

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

import com.example.escolasistemasolarnovo.DAO.Outras_SaidasDAO;
import com.example.escolasistemasolarnovo.R;
import com.example.escolasistemasolarnovo.entidades.Outras_Saidas;
import com.example.escolasistemasolarnovo.utils.BancoDeDados;

import java.util.Calendar;

public class OutrasSaidasListAlterar extends AppCompatActivity implements View.OnClickListener {

    //Atributos*****
    EditText origemOutrasSaidasAlterar, detalhamentoOutrasSaidasAlterar, valorOutrasSaidasAlterar;
    int id;
    ImageButton ib_voltar_inicio;
    Button btnAlterarOutrasSaidas, btnExcluirOutrasSaidas;
    TextView dataOutrasSaidasAlterar;
    BancoDeDados bd;

    private DatePickerDialog datePickerDialog;
    private Button dataBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outras_saidas_list_alterar);
        initDatePicker();

        //******captura dos componentes******
        dataOutrasSaidasAlterar = findViewById(R.id.dataOutrasSaidasAlterar);
        origemOutrasSaidasAlterar = findViewById(R.id.origemOutrasSaidasAlterar);
        valorOutrasSaidasAlterar = findViewById(R.id.valorOutrasSaidasAlterar);
        detalhamentoOutrasSaidasAlterar = findViewById(R.id.detalhamentoOutrasSaidasAlterar);
        ib_voltar_inicio = findViewById(R.id.ib_voltar_inicio);
        btnAlterarOutrasSaidas = findViewById(R.id.btnAlterarOutrasSaidas);
        btnExcluirOutrasSaidas = findViewById(R.id.btnExcluirOutrasSaidas);
        dataBtn = findViewById(R.id.dataPickerBtn);
        dataBtn.setText(getTodaysDate());
        btnExcluirOutrasSaidas.setOnClickListener(this);
        btnAlterarOutrasSaidas.setOnClickListener(this);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            dataBtn.setText(extras.getString("data"));
            origemOutrasSaidasAlterar.setText(extras.getString("origem"));
            valorOutrasSaidasAlterar.setText(extras.getString("valor"));
            detalhamentoOutrasSaidasAlterar.setText(extras.getString("obs"));
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
            Intent intent = new Intent(OutrasSaidasListAlterar.this, OutrasSaidasList.class);
            startActivity(intent);
        });
    }
    @Override
    public void onClick(View origem) {
        String dataOutrasSaidas = dataBtn.getText().toString();
        String origemOutrasSaidas = origemOutrasSaidasAlterar.getText().toString();
        String valorOutrasSaidas= valorOutrasSaidasAlterar.getText().toString();
        String detalhamento_outrasSaidas = detalhamentoOutrasSaidasAlterar.getText().toString();

        if (origem.getId() == R.id.btnAlterarOutrasEntradas) {
            if (dataOutrasSaidas.isEmpty() || origemOutrasSaidas.isEmpty() || valorOutrasSaidas.isEmpty() || detalhamento_outrasSaidas.isEmpty()) {//***verifica se os campos nao estao vazios***
                Toast.makeText(this, "Dados Incompletos!!",Toast.LENGTH_LONG).show();
            }else{
                alterarDados();
            }
        }else if (origem.getId() == R.id.btnExcluirOutrasSaidas){
            excluirDados();
        }
    }
    private void excluirDados(){ //exclui dados do banco
        Outras_Saidas outras_saidas = new Outras_Saidas();
        outras_saidas.id = id;

        bd = Room.databaseBuilder(getApplicationContext(), BancoDeDados.class, "BancoApp").allowMainThreadQueries().build();
        Outras_SaidasDAO outras_saidasDAO = bd.getOutras_SaidasDAO();

        outras_saidasDAO.delete(outras_saidas);

        Toast.makeText(this, "Remoção Realizada!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(OutrasSaidasListAlterar.this, OutrasSaidasList.class);
        startActivity(intent);
        finish();
    }
    private void alterarDados() { //alterar informaçoes no banco
        String dataOutrasSaidas = dataBtn.getText().toString();
        String origemOutrasSaidas = origemOutrasSaidasAlterar.getText().toString();
        String valorOutrasSaidas = valorOutrasSaidasAlterar.getText().toString();
        String detalhamento_outrasSaidas = detalhamentoOutrasSaidasAlterar.getText().toString();


        bd = Room.databaseBuilder(getApplicationContext(), BancoDeDados.class, "BancoApp").allowMainThreadQueries().build();
        Outras_SaidasDAO outras_saidasDAO = bd.getOutras_SaidasDAO();

        Outras_Saidas outras_saidas = new Outras_Saidas();
        outras_saidas.id = id;
        outras_saidas.dataOutrasSaidas = dataOutrasSaidas;
        outras_saidas.origemOutrasSaidas = origemOutrasSaidas;
        outras_saidas.valorOutrasSaidas = valorOutrasSaidas;
        outras_saidas.detalhamentoOutrasSaidas = detalhamento_outrasSaidas;

        outras_saidasDAO.update(outras_saidas);

        Toast.makeText(this, "Alteração Realizada!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(OutrasSaidasListAlterar.this, OutrasSaidasList.class);
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