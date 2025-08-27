package br.com.voting.vote.services.impl;

import br.com.voting.vote.dtos.AssociateDTO;
import br.com.voting.vote.exception.NotFoundException;
import br.com.voting.vote.models.*;
import br.com.voting.vote.repositories.AssociateRepository;
import br.com.voting.vote.services.AssociateService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssociateServiceImpl implements AssociateService {

    private final AssociateRepository repository;

    public AssociateServiceImpl(AssociateRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public void createAssociate(AssociateDTO associateDTO) {

        Associate associate = createAssociateByType(associateDTO);
        repository.save(associate);
    }

    @Override
    public List<Associate> findAll() {
        return repository.findAll();
    }

    @Override
    public Associate findById(String id) {
        return repository.findById(Long.parseLong(id))
                .orElseThrow(() -> new NotFoundException("Associado não encontrado"));
    }

    @Override
    public void deleteAssociate(String id) {
        Associate associate = findById(id);
        repository.delete(associate);
    }

    @Transactional
    @Override
    public void updateAssociate(AssociateDTO associateDTO, String id) {
        Associate associate = findById(id);
        
        // Atualiza os campos comuns
        associate.setName(associateDTO.getName());
        associate.setCpf(associateDTO.getCpf());
        
        repository.save(associate);
    }

    // IMPLEMENTAÇÃO DOS NOVOS MÉTODOS
    @Override
    public boolean canAssociateVote(String associateId) {
        Associate associate = findById(associateId);
        return associate.hasVotingPermission();
    }

    @Override
    public boolean canAssociateStartSession(String associateId) {
        Associate associate = findById(associateId);
        return associate.canStartSession();
    }

    @Override
    public String getAssociateType(String associateId) {
        Associate associate = findById(associateId);
        return associate.getAssociateType();
    }

    private Associate createAssociateByType(AssociateDTO associateDTO) {
        String type = associateDTO.getType() != null ? 
                     associateDTO.getType().toUpperCase() : "REGULAR";

        return switch (type) {
            case "ADMIN" -> new AdminAssociate(associateDTO.getName(), associateDTO.getCpf());
            case "OBSERVER" -> new ObserverAssociate(associateDTO.getName(), associateDTO.getCpf());
            default -> new RegularAssociate(associateDTO.getName(), associateDTO.getCpf());
        };
    }
}
