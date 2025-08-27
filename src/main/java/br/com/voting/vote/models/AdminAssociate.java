package br.com.voting.vote.models;

import jakarta.persistence.Entity;

@Entity
@Table(name = "admin_associate")
public class AdminAssociate extends Associate {

    public AdminAssociate() {
        super();
    }

    public AdminAssociate(String name, String cpf) {
        super(name, cpf);
    }

    @Override
    public boolean canVoteYes() {
        return true;  // Admins podem votar SIM
    }

    @Override
    public boolean canVoteNo() {
        return true;  // Admins podem votar NÃO
    }

    @Override
    public boolean canStartSession() {
        return true;  // Admins podem iniciar sessões
    }

    @Override
    public String getAssociateType() {
        return "ADMIN";
    }
}
