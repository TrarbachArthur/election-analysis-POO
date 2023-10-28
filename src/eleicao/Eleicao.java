package eleicao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Eleicao {
    private Map<Integer, Partido> partidos = new HashMap<Integer, Partido>();
    private Map<Integer, Candidato> candidatos = new HashMap<Integer, Candidato>();
    private List<Candidato> eleitos = new ArrayList<Candidato>();
    private Date data;

    public Eleicao(Date data) {
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

    public Date getData() {
        return data;
    }
    
}
