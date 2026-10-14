package br.com.ordensservico.aberturaordensservico.controller;
import br.com.ordensservico.aberturaordensservico.dto.EquipamentoRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ordensservico.aberturaordensservico.model.Equipamento;
import br.com.ordensservico.aberturaordensservico.service.EquipamentoService;
import jakarta.validation.Valid;

@RestController 
@RequestMapping ("/equipamento")
public class EquipamentoController {
    private final EquipamentoService equipamentoService;
    public EquipamentoController(EquipamentoService equipamentoService) {
        this.equipamentoService = equipamentoService;
    }
    @PostMapping
    public ResponseEntity<Equipamento> cadastrar(@Valid @RequestBody EquipamentoRequest request){
        Equipamento equipamentoCadastrado = equipamentoService.cadastrar(request);
        return ResponseEntity.status(201).body(equipamentoCadastrado);
    }
    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id){
        boolean excluido = equipamentoService.excluir(id);
        if(excluido){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping 
    public ResponseEntity<java.util.List<Equipamento>> listarEquipamentos(){
        java.util.List<Equipamento> equipamentos = equipamentoService.listarEquipamentos();
        return ResponseEntity.ok(equipamentos);
    }   
    @GetMapping ("/{id}")
    public ResponseEntity<Equipamento> listarEquipamentosPorId(@PathVariable Integer id){
        return equipamentoService.ListarEquipamentoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/{id}")
public ResponseEntity<Equipamento> atualizar(@PathVariable Integer id, @Valid @RequestBody EquipamentoRequest request) {
    return equipamentoService.atualizar(id, request)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
}

}
