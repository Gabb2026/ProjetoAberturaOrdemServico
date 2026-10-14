package br.com.ordensservico.aberturaordensservico.controller;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.ordensservico.aberturaordensservico.model.Setor;
import br.com.ordensservico.aberturaordensservico.service.SetorService;
import jakarta.validation.Valid;

@RestController 
@RequestMapping ("/setor") 
public class SetorController {
    private final SetorService setorService;
    public SetorController(SetorService setorService) {
        this.setorService = setorService;
    }
    @PostMapping 
    public ResponseEntity<Setor> cadastrar(@Valid @RequestBody Setor setor){
        Setor setorCadastrado = setorService.cadastrar(setor);
        return ResponseEntity.status(201).body(setorCadastrado);

    }
    @GetMapping
    public ResponseEntity<List<Setor>> listarSetores(){
        List<Setor> setores = setorService.listarSetores();
        return ResponseEntity.ok(setores);
    }   
    @GetMapping ("/{id}")
    public ResponseEntity<Setor> listarSetoresPorId(@PathVariable Integer id){
        return setorService.ListarSetoresPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());//
    }
    @PutMapping ("/{id}")
    public ResponseEntity<Setor> atualizar(@PathVariable Integer id, @Valid @RequestBody Setor novoNome){
        return setorService.atualizar(id, novoNome)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    
    }
    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id){
        boolean excluido = setorService.excluir(id);
        if(excluido){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
}
    }
}