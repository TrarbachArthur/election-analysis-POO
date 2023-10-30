package io;

import eleicao.*;
import util.Genero;
import java.time.Period;

public class Relatorio {
    public static void geraRelatorio8(Eleicao eleicao) {
        int menor30 = 0;
        int menor40 = 0;
        int menor50 = 0;
        int menor60 = 0;
        int maior60 = 0;

        for (Candidato c: eleicao.getEleitos()) {
            int idade = Period.between(c.getDataNascimento(), eleicao.getData()).getYears();

            if (idade < 30)      { menor30++; }
            else if (idade < 40) { menor40++; }
            else if (idade < 50) { menor50++; }
            else if (idade < 60) { menor60++; }
            else                 { maior60++; }
        }

        System.out.println("Eleitos, por faixa etária (na data da eleição):");
        System.out.println("      Idade < 30: " + menor30 + " (" + 0 + "%)");
        System.out.println("30 <= Idade < 40: " + menor40 + " (" + 0 + "%)");
        System.out.println("40 <= Idade < 50: " + menor50 + " (" + 0 + "%)");
        System.out.println("50 <= Idade < 60: " + menor60 + " (" + 0 + "%)");
        System.out.println("60 <= Idade     : " + maior60 + " (" + 0 + "%)");
        System.out.println();
    }

    public static void geraRelatorio9(Eleicao eleicao) {
        int qtdFeminino = 0;
        int qtdMasculino = 0;

        for (Candidato c: eleicao.getEleitos()) {
            if (c.getGenero() == Genero.FEMININO) {
                qtdFeminino++;
            }
            else if (c.getGenero() == Genero.MASCULINO) {
                qtdMasculino++;
            }
        }

        System.out.println("Eleitos, por gênero:");
        System.out.println("Feminino:  " + qtdFeminino + "(" + 0 + "%)");
        System.out.println("Masculino: " + qtdMasculino + "(" + 0 + "%)");

        System.out.println();
    }

    public static void geraRelatorio10(Eleicao eleicao) {
        int votosNominais = 0;
        int votosLegenda = 0;

        for (Candidato c: eleicao.getCandidatos().values()) {
            votosNominais += c.getVotos();
        }

        for (Partido p: eleicao.getPartidos().values()) {
            votosLegenda += p.getVotosLegenda();
        }

        System.out.println("Total de votos válidos:     " + (votosNominais + votosLegenda) + "(" + 0 + "%)");
        System.out.println("Total de votos nominais:    " + votosNominais + "(" + 0 + "%)");
        System.out.println("Total de votos de legenda:  " + votosLegenda + "(" + 0 + "%)");

        System.out.println();
    }
}
