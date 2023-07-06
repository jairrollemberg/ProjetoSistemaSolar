package com.example.escolasistemasolarnovo.utils;

import com.example.escolasistemasolarnovo.entidades.Usuario;

public class SessionData {
    public static  SessionData singleton = null;

    //atributos
    private Usuario userLogado;

    private SessionData(){}

    public static SessionData getInstance(){
        if (singleton==null)
            singleton = new SessionData();
        return singleton;
    }
    public Usuario getUserLogado() {return userLogado;}

    public void setUserLogado(Usuario userLogado) {this.userLogado = userLogado;}
}
