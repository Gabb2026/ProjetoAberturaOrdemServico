package br.com.ordensservico.aberturaordensservico.dto;

import jakarta.validation.constraints.NotBlank;

public class EncerramentoRequest {
    @NotBlank (message = "A SOLUÇÃO DEVE SER INFORMADA")
    private String solucao;

    public EncerramentoRequest(){

    }

    public String getSolucao(){
           return solucao;
    }
    public void setSolucao(String solucao){
        this.solucao = solucao;
    }
}
