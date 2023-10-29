package eleicao;

import java.util.Date;

public class Candidato {
    public enum TipoCandidato {
        FEDERAL(6), ESTADUAL(7);

        private int valor;

        TipoCandidato(int valor) {
            this.valor = valor;
            }

        public static TipoCandidato parseInt(int codigo) {
            if (codigo == FEDERAL.valor)
                return FEDERAL;
            else
                return ESTADUAL;
        }
    }

    public enum Genero {
        MASCULINO(2), FEMININO(4);

        private int valor;

        Genero(int valor) {
            this.valor = valor;
        }

        public static Genero parseInt(int codigo) {
            if (codigo == MASCULINO.valor)
                return MASCULINO;
            else
                return FEMININO;
        }
    }

    private TipoCandidato tipo;
    private boolean ehDeferido;
    private int numero;
    private String nome;
    private Partido partido;
    private boolean ehFederado;
    private Date dataNascimento;
    private boolean ehEleito;
    private int votosNominais;
    private Genero genero;
    private boolean ehVotoLegenda;

    public Candidato(int tipo, boolean ehDeferido, int numero, String nome, Partido partido, boolean ehFederado, Date dataNascimento, boolean ehEleito,
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

    public Date getDataNascimento() {
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
