package br.com.voting.vote.controllers;

import br.com.voting.vote.dtos.AssociateDTO;
import br.com.voting.vote.models.Associate;
import br.com.voting.vote.services.AssociateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/associate")
public class AssociateController {

    @Autowired
    private AssociateService associateService;

    @GetMapping
    public ResponseEntity<List<Associate>> getAll() {
        return ResponseEntity.ok(associateService.findAll());
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody AssociateDTO associateDTO) {
        associateService.createAssociate(associateDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Associate> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok(associateService.findById(id));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> removeAssociate(@PathVariable("id") String id) {
        associateService.deleteAssociate(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Void> updateAssociate(@PathVariable("id") String id,
                                                @RequestBody AssociateDTO associateDTO) {
        associateService.updateAssociate(associateDTO, id);
        return ResponseEntity.noContent().build();
    }

    // NOVOS ENDPOINTS
    @GetMapping(path = "{id}/can-vote")
    public ResponseEntity<Boolean> canAssociateVote(@PathVariable("id") String id) {
        boolean canVote = associateService.canAssociateVote(id);
        return ResponseEntity.ok(canVote);
    }

    @GetMapping(path = "{id}/can-start-session")
    public ResponseEntity<Boolean> canAssociateStartSession(@PathVariable("id") String id) {
        boolean canStart = associateService.canAssociateStartSession(id);
        return ResponseEntity.ok(canStart);
    }

    @GetMapping(path = "{id}/type")
    public ResponseEntity<String> getAssociateType(@PathVariable("id") String id) {
        String type = associateService.getAssociateType(id);
        return ResponseEntity.ok(type);
    }
}
