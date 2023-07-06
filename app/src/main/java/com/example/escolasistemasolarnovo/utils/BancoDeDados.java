package com.example.escolasistemasolarnovo.utils;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.escolasistemasolarnovo.DAO.CustosDAO;
import com.example.escolasistemasolarnovo.DAO.DespesasDAO;
import com.example.escolasistemasolarnovo.DAO.Outras_EntradasDAO;
import com.example.escolasistemasolarnovo.DAO.Outras_SaidasDAO;
import com.example.escolasistemasolarnovo.DAO.ReceitasDAO;
import com.example.escolasistemasolarnovo.DAO.UsuarioDAO;
import com.example.escolasistemasolarnovo.entidades.Custos;
import com.example.escolasistemasolarnovo.entidades.Despesas;
import com.example.escolasistemasolarnovo.entidades.Outras_Entradas;
import com.example.escolasistemasolarnovo.entidades.Outras_Saidas;
import com.example.escolasistemasolarnovo.entidades.Receitas;
import com.example.escolasistemasolarnovo.entidades.Usuario;


@Database(entities = {Usuario.class, Custos.class, Outras_Entradas.class,Outras_Saidas.class, Despesas.class,  Receitas.class}, version = 1)
public abstract class BancoDeDados extends RoomDatabase {
    public abstract UsuarioDAO getUsuarioDao();
    public abstract CustosDAO getCustosDAO();
    public abstract ReceitasDAO getReceitasDAO();
    public abstract DespesasDAO getDespesasDAO();
    public abstract Outras_EntradasDAO getOutras_EntradasDAO();
    public abstract Outras_SaidasDAO getOutras_SaidasDAO();


}
