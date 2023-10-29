package eleicao;

import java.util.HashMap;
import java.util.Map;

public class Partido {
    private int votosLegenda;
    private int numero;
    private String sigla;
    private Map<Integer, Candidato> candidatos = new HashMap<Integer, Candidato>();

    public Partido(int numero, String sigla) {
        this.votosLegenda = 0;
        this.numero = numero;
        this.sigla = sigla;
    }

    public int getVotosLegenda() {
        return votosLegenda;
    }

    public int getNumero() {
        return numero;
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
