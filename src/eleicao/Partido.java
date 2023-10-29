package eleicao;

import java.util.HashMap;
import java.util.Map;

public class Partido {
    private int votosLegenda;
    private int numero;
    private String sigla;
    private Map<Integer, Candidato> candidatosDeferidos = new HashMap<Integer, Candidato>();
    private Map<Integer, Candidato> candidatosIndeferidos = new HashMap<Integer, Candidato>();


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

    public Map<Integer, Candidato> getCandidatosDeferidos() {
        return new HashMap<Integer, Candidato>(candidatosDeferidos);
    }

    public Map<Integer, Candidato> getCandidatosIndeferidos() {
        return new HashMap<Integer, Candidato>(candidatosIndeferidos);
    }

    public void addCandidato(Candidato candidato) {
        if (candidato.ehDeferido()) this.candidatosDeferidos.put(candidato.getNumero(), candidato);
        else this.candidatosIndeferidos.put(candidato.getNumero(), candidato);
    }

    public void processaVotos(int votos) {
        votosLegenda += votos;
    }

    @Override
    public String toString() {
        return this.sigla + " (" + this.getNumero() + ") - " + this.getVotosLegenda() + " votos de legenda";
    }
}
