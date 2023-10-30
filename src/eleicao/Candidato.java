package eleicao;

import java.time.LocalDate;
import util.*;

public class Candidato {
    private TipoCandidato tipo;
    private boolean ehDeferido;
    private int numero;
    private String nome;
    private Partido partido;
    private boolean ehFederado;
    private LocalDate dataNascimento;
    private boolean ehEleito;
    private int votosNominais;
    private Genero genero;
    private boolean ehVotoLegenda;

    public Candidato(int tipo, boolean ehDeferido, int numero, String nome, Partido partido, boolean ehFederado, LocalDate dataNascimento, boolean ehEleito,
            int genero, boolean ehVotoLegenda) {
        this.tipo = TipoCandidato.parseInt(tipo);
        this.ehDeferido = ehDeferido;
        this.nome = nome;
        this.numero = numero;
        this.partido = partido;
        this.ehEleito = ehEleito;
        this.dataNascimento = dataNascimento;
        this.ehFederado = ehFederado;
        this.genero = Genero.parseInt(genero);
        this.ehVotoLegenda = ehVotoLegenda;

        partido.addCandidato(this);
    }

    public TipoCandidato getTipo() {
        return tipo;
    }
    
    public boolean ehDeferido() {
        return ehDeferido;
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
        return votosNominais;
    }

    public boolean ehEleito() {
        return ehEleito;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public boolean ehFederado() {
        return ehFederado;
    }

    public Genero getGenero() {
        return genero;
    }

    public boolean ehVotoLegenda() {
        return ehVotoLegenda;
    }

    public void processaVotos(int votos) {
        if (ehVotoLegenda()) {
            partido.processaVotos(votos);
        }
        else {
            this.votosNominais += votos;
        }
    }

    @Override
    public String toString() {
        return this.nome + " (" + this.partido.getSigla() + "," + this.getVotos() + ")";
    }
}
