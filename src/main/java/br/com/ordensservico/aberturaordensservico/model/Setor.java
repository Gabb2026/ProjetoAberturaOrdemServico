package br.com.ordensservico.aberturaordensservico.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
 @Entity
 @Table (name= "setor")
public class Setor {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank (message = "O nome do setor não pode ser nulo ou vazio")
    private String nome;
    @OneToMany (mappedBy = "setor")
    private List<Equipamento> equipamentos;
    public Setor() {
    }
    public Setor(String nome) {
        this.nome = nome;
    }
    
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getId(){
        return id;
    }

}
