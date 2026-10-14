package br.com.ordensservico.aberturaordensservico.service;

import java.util.List;
import java.util.Optional;
import br.com.ordensservico.aberturaordensservico.dto.EquipamentoRequest;
import org.springframework.stereotype.Service;
import br.com.ordensservico.aberturaordensservico.model.Setor;
import br.com.ordensservico.aberturaordensservico.repository.SetorRepository;
import br.com.ordensservico.aberturaordensservico.model.Equipamento;
import br.com.ordensservico.aberturaordensservico.repository.EquipamentoRepository;

@Service 
public class EquipamentoService {
    private final EquipamentoRepository equipamentoRepository;
    private final SetorRepository setorRepository;
    public EquipamentoService(EquipamentoRepository equipamentoRepository, SetorRepository setorRepository) {
        this.equipamentoRepository = equipamentoRepository;
        this.setorRepository = setorRepository;
    }
    
    public boolean excluir(Integer id){
        if(equipamentoRepository.existsById(id)){
            equipamentoRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }
    public Equipamento cadastrar(EquipamentoRequest request) {

    Setor setor = setorRepository.findById(request.getSetorId())
        .orElseThrow(() -> new RuntimeException("Setor não encontrado"));

    Equipamento equipamento = new Equipamento(
        request.getNome(),
        request.getNumeroPatrimonio(),
        setor
    );

    return equipamentoRepository.save(equipamento);
}


    public List<Equipamento> listarEquipamentos(){
        return equipamentoRepository.findAll();
    }
    public Optional<Equipamento> ListarEquipamentoPorId(Integer id){
        return equipamentoRepository.findById(id);
    }
    public Optional<Equipamento> atualizar(Integer id, EquipamentoRequest request) {
    Optional<Equipamento> equipamentoEncontrado = equipamentoRepository.findById(id);
    
    if (equipamentoEncontrado.isEmpty()) {
        return Optional.empty();
    }
    
    Setor setor = setorRepository.findById(request.getSetorId())
        .orElseThrow(() -> new RuntimeException("Setor não encontrado"));

    Equipamento equipamento = equipamentoEncontrado.get();
    equipamento.setNome(request.getNome());
    setorRepository.findById(request.getSetorId()); // ou use a lógica do setor
    equipamento.setNumeroPatrimonio(request.getNumeroPatrimonio());
    equipamento.setSetor(setor);
    
    return Optional.of(equipamentoRepository.save(equipamento));
}
}
