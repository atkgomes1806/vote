package br.com.voting.vote.models;

import jakarta.persistence.Entity;

@Entity
@Table(name = "observer_associate")
public class ObserverAssociate extends Associate {

    public ObserverAssociate() {
        super();
    }

    public ObserverAssociate(String name, String cpf) {
        super(name, cpf);
    }

    @Override
    public boolean canVoteYes() {
        return false; // Observadores NÃO podem votar SIM
    }

    @Override
    public boolean canVoteNo() {
        return false; // Observadores NÃO podem votar NÃO
    }

    @Override
    public boolean canStartSession() {
        return false; // Observadores NÃO podem iniciar sessões
    }

    @Override
    public boolean canParticipate() {
        // Observadores só podem participar como observadores
        return super.canParticipate();
    }

    @Override
    public String getAssociateType() {
        return "OBSERVER";
    }
}
