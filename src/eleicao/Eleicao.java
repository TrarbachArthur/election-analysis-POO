package eleicao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import util.*;

public class Eleicao {

  private TipoCandidato opcaoCargo;
  private Map<Integer, Partido> partidos = new HashMap<Integer, Partido>();
  private Map<Integer, Candidato> candidatos = new HashMap<Integer, Candidato>();
  private List<Candidato> eleitos = new ArrayList<Candidato>();
  private LocalDate data;

  private int numeroVagas;
  private List<Candidato> candidatosMaisVotados;
  private List<Partido> partidosMaisVotados;

  public Eleicao(TipoCandidato opcaoCargo, LocalDate data) {
    this.opcaoCargo = opcaoCargo;
    this.data = data;
  }

  public TipoCandidato getOpcaoCargo() {
    return opcaoCargo;
  }

  public Map<Integer, Partido> getPartidos() {
    return new HashMap<Integer, Partido>(partidos);
  }

  public List<Partido> getPartidosValues() {
    return new ArrayList<Partido>(partidos.values());
  }

  public void addPartido(Partido partido) {
    this.partidos.put(partido.getNumero(), partido);
  }

  public Map<Integer, Candidato> getCandidatos() {
    return new HashMap<Integer, Candidato>(candidatos);
  }

  public List<Candidato> getCandidatosValues() {
    return new ArrayList<Candidato>(candidatos.values());
  }

  public void addCandidato(Candidato candidato) {
    this.candidatos.put(candidato.getNumero(), candidato);
  }

  public List<Candidato> getEleitos() {
    return new ArrayList<Candidato>(eleitos);
  }

  public void addEleito(Candidato eleito) {
    this.eleitos.add(eleito);
  }

  public LocalDate getData() {
    return data;
  }

  public int getNumeroVagas() {
    return numeroVagas;
  }

  public List<Candidato> getCandidatosMaisVotados() {
    return new ArrayList<Candidato>(candidatosMaisVotados);
  }

  public List<Partido> getPartidosMaisVotados() {
    return new ArrayList<Partido>(partidosMaisVotados);
  }

  public void processaCandidato(
    TipoCandidato cargo,
    boolean ehDeferido,
    int numeroCand,
    String nomeCand,
    int numeroPart,
    String siglaPart,
    boolean ehFederado,
    LocalDate dataNasc,
    boolean ehEleito,
    Genero genero,
    boolean ehVotoLegenda
  ) {
    Partido partido = partidos.get(numeroPart);

    if (partido == null) { // Cria partido caso ainda nao exista
      partido = new Partido(numeroPart, siglaPart);
      partidos.put(partido.getNumero(), partido);
    }

    if (cargo != this.opcaoCargo) return; // Ignora candidatos de outros cargos
    if (!ehDeferido && !ehVotoLegenda) return;

    Candidato c = candidatos.get(numeroCand);
    if (c != null) {
      if (c.ehDeferido()) {
        System.out.println("Tentou trocar deferido!");
        return;
      } else if (c.ehVotoLegenda() && !ehDeferido) {
        System.out.println("Tentou trocar voto legenda");
        return;
      } else {
        System.out.println("Fez nada");
      }
    }

    Candidato candidato = new Candidato(
      cargo,
      ehDeferido,
      numeroCand,
      nomeCand,
      partido,
      ehFederado,
      dataNasc,
      ehEleito,
      genero,
      ehVotoLegenda
    );
    candidatos.put(candidato.getNumero(), candidato);

    if (candidato.ehEleito()) {
      eleitos.add(candidato);
    }
  }

  public void processaVotos(
    TipoCandidato cargo,
    int numeroVotado,
    int qtdVotos
  ) {
    if (cargo != this.opcaoCargo) return; // Ignora candidatos de outros cargos

    // Processando numero de candidato
    if (candidatos.containsKey(numeroVotado)) {
      candidatos.get(numeroVotado).processaVotos(qtdVotos);
      return;
    }

    while (numeroVotado >= 100) {
      numeroVotado %= 10;
    }

    // Processando numero de partido
    Partido partido = partidos.get(numeroVotado);

    if (partido != null) {
      partido.processaVotos(qtdVotos);
      return;
    }
  }

  public void processaEleicao() {
    numeroVagas = eleitos.size();

    // Ordena lista de candidatos mais votados
    candidatosMaisVotados = getCandidatosValues();
    Collections.sort(candidatosMaisVotados);

    // Ordena lista de partidos conforme votos totais
    partidosMaisVotados = getPartidosValues();
    Collections.sort(partidosMaisVotados);

    // Gera e ordena lista de candidatos mais votados por partido
    for (Partido p : getPartidosValues()) {
      p.processaCandidatosMaisVotados();
    }

    // Ordena lista de candidatos eleitos
    Collections.sort(eleitos);
  }
}
