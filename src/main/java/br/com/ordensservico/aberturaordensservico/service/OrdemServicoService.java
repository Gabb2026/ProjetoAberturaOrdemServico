package br.com.ordensservico.aberturaordensservico.service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.ordensservico.aberturaordensservico.dto.EncerramentoRequest;
import br.com.ordensservico.aberturaordensservico.dto.OrdemServicoRequest;
import br.com.ordensservico.aberturaordensservico.model.Equipamento;
import br.com.ordensservico.aberturaordensservico.model.OrdemServico;
import br.com.ordensservico.aberturaordensservico.repository.EquipamentoRepository;
import br.com.ordensservico.aberturaordensservico.repository.OrdemServicoRepository;


@Service 
public class OrdemServicoService {
    private final OrdemServicoRepository ordemServicoRepository;
    private final EquipamentoRepository equipamentoRepository;
    
    public OrdemServicoService(OrdemServicoRepository ordemServicoRepository, EquipamentoRepository equipamentoRepository){
        this.ordemServicoRepository = ordemServicoRepository;
        this.equipamentoRepository = equipamentoRepository;
    }
    public List<OrdemServico> listarOrdensEmAberto() {
        return ordemServicoRepository.findByDataEncerramentoIsNull();
    }
    public Optional<OrdemServico> cadastrar( OrdemServicoRequest dados){
      Optional<Equipamento> equipamento = equipamentoRepository.findById(dados.getEquipamentoId());
      if(equipamento.isEmpty()){
                return Optional.empty();
            }
            OrdemServico ordem = new OrdemServico();
            ordem.setEquipamento(equipamento.get());
            ordem.setDescricao(dados.getDescricao());
    
            

            
            return Optional.of(ordemServicoRepository.save(ordem));
    
    }
    public List<OrdemServico>listarOrdemServico(){
            return ordemServicoRepository.findAll(); 
    }
    
    public Optional<OrdemServico>listarOrdemServicoPorId(Integer id){
            return ordemServicoRepository.findById(id); 
    }
 public Optional<OrdemServico> encerrar(Integer id, EncerramentoRequest dados){
      Optional<OrdemServico> ordemEncerrada = ordemServicoRepository.findById(id);
      if(ordemEncerrada.isEmpty()){
                return Optional.empty();
            }
            OrdemServico encerramentoOrdem = ordemEncerrada.get();
            if (encerramentoOrdem.getDataEncerramento() != null) {
        throw new ResponseStatusException(HttpStatus.CONFLICT, "Esta ordem de serviço já foi encerrada.");
    }
            encerramentoOrdem.setSolucao(dados.getSolucao());
            encerramentoOrdem.setDataEncerramento(LocalDateTime.now());
        
            return Optional.of(ordemServicoRepository.save(encerramentoOrdem));
    
    }

    


}