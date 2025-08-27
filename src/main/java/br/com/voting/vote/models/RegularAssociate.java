package br.com.voting.vote.models;

import jakarta.persistence.Entity;

@Entity
@Table(name = "regular_associate")
public class RegularAssociate extends Associate {

    public RegularAssociate() {
        super();
    }

    public RegularAssociate(String name, String cpf) {
        super(name, cpf);
    }

    @Override
    public boolean canVoteYes() {
        return true;  // Associados regulares podem votar SIM
    }

    @Override
    public boolean canVoteNo() {
        return true;  // Associados regulares podem votar NÃO
    }

    @Override
    public boolean canStartSession() {
        return false; // Associados regulares NÃO podem iniciar sessões
    }

    @Override
    public String getAssociateType() {
        return "REGULAR";
    }
}
