package eleicao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Partido implements Comparable<Partido> {

  private int votosLegenda;
  private int numero;
  private String sigla;
  private Map<Integer, Candidato> candidatosDeferidos = new HashMap<Integer, Candidato>();
  private Map<Integer, Candidato> candidatosIndeferidos = new HashMap<Integer, Candidato>();

  private int qtdEleitos;
  private List<Candidato> candidatosMaisVotados;

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
    if (candidato.ehDeferido()) {
      this.candidatosDeferidos.put(candidato.getNumero(), candidato);

      if (candidato.ehEleito()) qtdEleitos++;
    } else this.candidatosIndeferidos.put(candidato.getNumero(), candidato);
  }

  public int getQtdEleitos() {
    return qtdEleitos;
  }

  public List<Candidato> getCandidatosMaisVotados() {
    return new ArrayList<Candidato>(candidatosMaisVotados);
  }

  public void processaVotos(int votos) {
    votosLegenda += votos;
  }

  public int getVotosTotais() {
    int votosTotais = this.getVotosLegenda();

    for (Candidato c : candidatosDeferidos.values()) {
      votosTotais += c.getVotos();
    }

    return votosTotais;
  }

  public void processaCandidatosMaisVotados() {
    candidatosMaisVotados =
      new ArrayList<Candidato>(candidatosDeferidos.values());

    Collections.sort(candidatosMaisVotados);
  }

  @Override
  public String toString() {
    return getSigla() + " - " + getNumero();
  }

  @Override
  public int compareTo(Partido p) {
    int dif = p.getVotosTotais() - this.getVotosTotais();

    if (dif != 0) return dif;

    return (this.getNumero() - p.getNumero());
  }
}
