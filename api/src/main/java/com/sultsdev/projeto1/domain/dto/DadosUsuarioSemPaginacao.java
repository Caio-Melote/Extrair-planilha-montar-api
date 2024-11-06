package com.sultsdev.projeto1.domain.dto;

import java.util.HashMap;
import java.util.Map;

import com.sultsdev.projeto1.model.Usuario;

public class DadosUsuarioSemPaginacao {

    private Map<String, Object> data;

    public DadosUsuarioSemPaginacao(Usuario usuario) {
        this.data = new HashMap<>();
        this.data.put("id", usuario.getId());    
        this.data.put("login", usuario.getLogin());  
    }

    public Map<String, Object> getData() {
        return data;
    }
}

