package eleicao;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Eleicao {
    private int opcaoCargo;
    private Map<Integer, Partido> partidos = new HashMap<Integer, Partido>();
    private Map<Integer, Candidato> candidatos = new HashMap<Integer, Candidato>();
    private List<Candidato> eleitos = new ArrayList<Candidato>();
    private LocalDate data;

    public Eleicao(int opcaoCargo, LocalDate data) {
        this.opcaoCargo = opcaoCargo;
        this.data = data;
    }

    public Map<Integer, Partido> getPartidos() {
        return new HashMap<Integer, Partido>(partidos);
    }

    public void addPartido(Partido partido) {
        this.partidos.put(partido.getNumero(), partido);
    }

    public Map<Integer, Candidato> getCandidatos() {
        return new HashMap<Integer, Candidato>(candidatos);
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

    public void processaCandidato(int cargo, boolean ehDeferido, int numeroCand, String nomeCand, int numeroPart, String siglaPart,
                        boolean ehFederado, LocalDate dataNasc, boolean ehEleito, int genero, boolean ehVotoLegenda) {                  
        
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
            }
            else if (c.ehVotoLegenda() && !ehDeferido){
                System.out.println("Tentou trocar voto legenda");
                return;
            }
            else {
                System.out.println("Fez nada");
            }
        }
        
        Candidato candidato = new Candidato(cargo, ehDeferido, numeroCand, nomeCand, partido, ehFederado, dataNasc, ehEleito, genero, ehVotoLegenda);
        candidatos.put(candidato.getNumero(), candidato);

        if (candidato.ehEleito()) {
            eleitos.add(candidato);
        }
    }

    public void processaVotos(int cargo, int numeroVotado, int qtdVotos) {
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

        //System.out.println("Não encontrei esse cara " + numeroVotado);
    }
}
