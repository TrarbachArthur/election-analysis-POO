package util;

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

    public int getInt() {
        return this.valor;
    }
}