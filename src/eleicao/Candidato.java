package eleicao;

import java.util.Date;

public class Candidato {
    private String nome;
    private int numero;
    private Partido partido;
    private int votos;
    private boolean ehEleito;
    private Date dataNascimento;
    private int numeroFederacao;
    private int genero;
    private boolean ehVotoLegenda;

    public Candidato(String nome, int numero, Partido partido, int votos, boolean ehEleito, Date dataNascimento,
            int numeroFederacao, int genero, boolean ehVotoLegenda) {
        this.nome = nome;
        this.numero = numero;
        this.partido = partido;
        this.votos = votos;
        this.ehEleito = ehEleito;
        this.dataNascimento = dataNascimento;
        this.numeroFederacao = numeroFederacao;
        this.genero = genero;
        this.ehVotoLegenda = ehVotoLegenda;
    }

    public String getNome() {
        return nome;
    }

    public int getNumero() {
        return numero;
    }

    public Partido getPartido() {
        return partido;
    }

    public int getVotos() {
        return votos;
    }

    public boolean isEhEleito() {
        return ehEleito;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public int getNumeroFederacao() {
        return numeroFederacao;
    }

    public int getGenero() {
        return genero;
    }

    public boolean isEhVotoLegenda() {
        return ehVotoLegenda;
    }
    
}
