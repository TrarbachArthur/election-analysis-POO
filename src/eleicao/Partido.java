package eleicao;

import java.util.HashMap;
import java.util.Map;

public class Partido {
    private int votosLegenda=0;
    private int numero;
    private String nome;
    private String sigla;
    private Map<Integer, Candidato> candidatos = new HashMap<Integer, Candidato>();

    public Partido(int numero, String nome, String sigla) {
        this.numero = numero;
        this.nome = nome;
        this.sigla = sigla;
    }

    public int getVotosLegenda() {
        return votosLegenda;
    }

    public int getNumero() {
        return numero;
    }

    public String getNome() {
        return nome;
    }

    public String getSigla() {
        return sigla;
    }

    public Map<Integer, Candidato> getCandidatos() {
        return new HashMap<Integer, Candidato>(candidatos);
    }

    public void addCandidato(Candidato candidato) {
        this.candidatos.put(candidato.getNumero(), candidato);
    }

    public void processaVotos(int votos) {
        votosLegenda += votos;
    }
}
