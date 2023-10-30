package util;

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

    public int getInt() {
        return this.valor;
    }
}