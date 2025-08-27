package br.com.voting.vote.models;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class Associate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "name")
    protected String name;

    @Column(name = "cpf")
    protected String cpf;

    public Associate() {
    }

    public Associate(String name, String cpf) {
        this.name = name;
        this.cpf = cpf;
    }

    public abstract boolean canVoteYes();
    public abstract boolean canVoteNo();
    public abstract boolean canStartSession();
    public abstract String getAssociateType();

    public boolean canParticipate() {
        return cpf != null && !cpf.isEmpty();
    }

    public boolean hasVotingPermission() {
        return canVoteYes() || canVoteNo();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "Associate{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cpf='" + cpf + '\'' +
                ", type=" + getAssociateType() +
                '}';
    }
}
