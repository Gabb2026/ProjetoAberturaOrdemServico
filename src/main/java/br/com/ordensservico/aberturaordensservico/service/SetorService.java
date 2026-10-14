package br.com.ordensservico.aberturaordensservico.service;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.ordensservico.aberturaordensservico.model.Setor;
import br.com.ordensservico.aberturaordensservico.repository.SetorRepository;

@Service 
public class SetorService {
    private final SetorRepository setorRepository;
    public SetorService(SetorRepository setorRepository) {
        this.setorRepository = setorRepository;
    }

    public Setor cadastrar(Setor setor){
        return setorRepository.save(setor);
        }

    public List<Setor> listarSetores(){
        return setorRepository.findAll();
    }
    public Optional<Setor> ListarSetoresPorId(Integer id){
        return setorRepository.findById(id);
    }
    public Optional<Setor> atualizar (Integer id, Setor novoNome){
        Optional<Setor> setorEncontrado = setorRepository.findById(id);
        if(setorEncontrado.isEmpty()){
            return Optional.empty();
        }
        Setor setor = setorEncontrado.get();
        setor.setNome(novoNome.getNome());
        return Optional.of(setorRepository.save(setor));
    }
    public Optional<Setor> atualizarNome(Integer id, String novoNome){
        Optional<Setor> setorEncontrado = setorRepository.findById(id);
        if(setorEncontrado.isEmpty()){
            return Optional.empty();
        }
        Setor setor = setorEncontrado.get();
        setor.setNome(novoNome);
        return Optional.of(setorRepository.save(setor));
    }
    public boolean excluir(Integer id){
        Optional<Setor> setorEncontrado = setorRepository.findById(id);
        if(setorEncontrado.isEmpty()){
            return false;
        }
        setorRepository.deleteById(id);
        return true;
    }
}
