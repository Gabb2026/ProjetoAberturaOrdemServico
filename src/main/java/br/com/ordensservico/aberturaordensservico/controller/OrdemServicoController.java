package br.com.ordensservico.aberturaordensservico.controller;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ordensservico.aberturaordensservico.dto.EncerramentoRequest;
import br.com.ordensservico.aberturaordensservico.dto.OrdemServicoRequest;
import br.com.ordensservico.aberturaordensservico.model.OrdemServico;
import br.com.ordensservico.aberturaordensservico.service.OrdemServicoService;
import jakarta.validation.Valid;
@RestController 
 @RequestMapping("/ordens-servico")
public class OrdemServicoController {
   private final OrdemServicoService ordemServicoService;

   public OrdemServicoController(OrdemServicoService ordemServicoService){
          this.ordemServicoService = ordemServicoService;
   }
   @GetMapping("/abertas")
    public ResponseEntity<List<OrdemServico>> listarAbertas() {
        List<OrdemServico> ordensAbertas = ordemServicoService.listarOrdensEmAberto();
        return ResponseEntity.ok(ordensAbertas);
    }
  @PostMapping
    public ResponseEntity<OrdemServico> cadastrar(@Valid @RequestBody OrdemServicoRequest dados) {
        Optional<OrdemServico> ordemServicoAberta = ordemServicoService.cadastrar(dados);
        if (ordemServicoAberta.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(ordemServicoAberta.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 
        }
    }
    @GetMapping 
    public ResponseEntity<java.util.List<OrdemServico>> listarOrdemServico(){
        java.util.List<OrdemServico> os = ordemServicoService.listarOrdemServico();
        return ResponseEntity.ok(os);
    }   
     @GetMapping ("/{id}")
    public ResponseEntity<OrdemServico> listarOrdemServicoPorId(@PathVariable Integer id){
        return ordemServicoService.listarOrdemServicoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());//
    }
    @PatchMapping("{id}/encerramento")
     public ResponseEntity<OrdemServico> Encerrar(@PathVariable Integer id, @Valid @RequestBody EncerramentoRequest dados){
        Optional<OrdemServico> ordemEncerrada = ordemServicoService.encerrar(id, dados);
    if (ordemEncerrada.isEmpty()) {
        return ResponseEntity.notFound().build(); 
    }
    return ResponseEntity.ok(ordemEncerrada.get()); 
}

}
