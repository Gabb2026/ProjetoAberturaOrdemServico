package br.com.ordensservico.aberturaordensservico.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity 
@Table (name = "Ordem_servico")
public class OrdemServico {

    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    private String descricao;
    private String solucao;
    private LocalDateTime dataAbertura = LocalDateTime.now();
    private LocalDateTime dataEncerramento;

    @ManyToOne 
    @JoinColumn(name = "equipamento_id") 
    Equipamento equipamento;

    public OrdemServico(){

    }
    
    public String getDescricao(){
        return descricao;
    }
    public void setDescricao(String descricao){
        this.descricao = descricao;
    }
    public LocalDateTime getDataAbertura(){
        return dataAbertura;
    }
     public Equipamento getEquipamento(){
        return equipamento;
    }
    public void setEquipamento(Equipamento equipamento){
        this.equipamento = equipamento;
    }
    public Integer getId(){
        return id;
    }
     public LocalDateTime getDataEncerramento(){
        return dataEncerramento;
     }
    public void setDataEncerramento(LocalDateTime dataEncerramento){
        this.dataEncerramento = dataEncerramento;
    }

    public String getSolucao(){
        return solucao;
    }
    public void setSolucao(String solucao){
        this.solucao = solucao;
    }

}
