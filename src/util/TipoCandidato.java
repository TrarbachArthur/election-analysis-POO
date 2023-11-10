package util;

public enum TipoCandidato {
    OUTRO(0), FEDERAL(6), ESTADUAL(7);

    private int valor;

    TipoCandidato(int valor) {
        this.valor = valor;
        }
    
    public static TipoCandidato parseInt(int codigo) {
        if (codigo == FEDERAL.valor)
            return FEDERAL;
        else if (codigo == ESTADUAL.valor)
            return ESTADUAL;
        return OUTRO;
    }

    public int getInt() {
        return this.valor;
    }
}