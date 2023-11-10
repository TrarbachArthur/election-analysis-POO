package io;

import eleicao.*;
import java.text.NumberFormat;
import java.time.Period;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import util.Genero;
import util.TipoCandidato;

public class Relatorio {

  private Eleicao eleicao;
  private static NumberFormat nfFloat = NumberFormat.getInstance(
    new Locale("pt", "BR")
  );
  private static NumberFormat nfInt = NumberFormat.getIntegerInstance(
    new Locale("pt", "BR")
  );

  public Relatorio(Eleicao eleicao) {
    this.eleicao = eleicao;
    nfFloat.setMaximumFractionDigits(2);
    nfFloat.setMinimumFractionDigits(2);
  }

  public void geraRelatorio1() {
    System.out.println("Número de vagas: " + eleicao.getNumeroVagas());
    System.out.println();
  }

  public void geraRelatorio2() {
    String s = eleicao.getOpcaoCargo() == TipoCandidato.FEDERAL
      ? "federais"
      : "estaduais";

    System.out.println("Deputados " + s + " eleitos:");

    int i = 1;
    for (Candidato c : eleicao.getEleitos()) {
      System.out.printf(
        "%d - %s%s\n",
        i,
        c.ehFederado() ? "*" : "",
        c.toString()
      );
      i++;
    }

    System.out.println();
  }

  public void geraRelatorio3() {
    System.out.println(
      "Candidatos mais votados (em ordem decrescente de votação e respeitando número de vagas):"
    );

    int i = 1;

    for (Candidato c : eleicao.getCandidatosMaisVotados()) {
      if (i > eleicao.getNumeroVagas()) break;
      System.out.printf(
        "%d - %s%s\n",
        i,
        c.ehFederado() ? "*" : "",
        c.toString()
      );
      i++;
    }

    System.out.println();
  }

  public void geraRelatorio4() {
    System.out.println(
      "Teriam sido eleitos se a votação fosse majoritária, e não foram eleitos:"
    );
    System.out.println("(com sua posição no ranking de mais votados)");

    List<Candidato> eleitos = eleicao.getEleitos();
    int i = 1;

    for (Candidato c : eleicao.getCandidatosMaisVotados()) {
      if (i > eleicao.getNumeroVagas()) break;
      if (!eleitos.contains(c)) {
        System.out.printf(
          "%d - %s%s\n",
          i,
          c.ehFederado() ? "*" : "",
          c.toString()
        );
      }
      i++;
    }

    System.out.println();
  }

  public void geraRelatorio5() {
    System.out.println("Eleitos, que se beneficiaram do sistema proporcional:");
    System.out.println("(com sua posição no ranking de mais votados)");

    List<Candidato> candidatosMaisVotados = eleicao.getCandidatosMaisVotados();
    List<Candidato> maisVotadosLimitada = candidatosMaisVotados.subList(
      0,
      eleicao.getNumeroVagas()
    );

    for (Candidato c : eleicao.getEleitos()) {
      if (!maisVotadosLimitada.contains(c)) {
        System.out.printf(
          "%d - %s%s\n",
          candidatosMaisVotados.indexOf(c) + 1,
          c.ehFederado() ? "*" : "",
          c.toString()
        );
      }
    }

    System.out.println();
  }

  public void geraRelatorio6() {
    System.out.println("Votação dos partidos e número de candidatos eleitos:");

    int i = 1;
    for (Partido p : eleicao.getPartidosMaisVotados()) {
      int votosTotais = p.getVotosTotais();
      int votosLegenda = p.getVotosLegenda();
      int votosNominais = votosTotais - votosLegenda;

      String s =
        nfInt.format(votosTotais) + (votosTotais > 1 ? " votos (" : " voto (");
      s +=
        nfInt.format(votosNominais) +
        ((votosNominais > 0) ? " nominais e " : " nominal e ");
      s += nfInt.format(votosLegenda) + " de legenda), " + p.getQtdEleitos();
      s +=
        (p.getQtdEleitos() > 1) ? " candidatos eleitos" : " candidato eleito";

      System.out.printf("%d - %s, %s\n", i, p.toString(), s);

      i++;
    }

    System.out.println();
  }

  public void geraRelatorio7() {
    Comparator<Partido> comparador = new Comparator<Partido>() {
      @Override
      public int compare(Partido p1, Partido p2) {
        List<Candidato> p1MaisVotados = p1.getCandidatosMaisVotados();
        List<Candidato> p2MaisVotados = p2.getCandidatosMaisVotados();

        if (p1MaisVotados.isEmpty()) return 1;
        if (p2MaisVotados.isEmpty()) return -1;

        int dif = p1
          .getCandidatosMaisVotados()
          .get(0)
          .compareTo(p2.getCandidatosMaisVotados().get(0));
        if (dif != 0) return dif;

        return p2.getNumero() - p1.getNumero();
      }
    };

    System.out.println("Primeiro e último colocados de cada partido:");

    List<Partido> partidosOrdenado = eleicao.getPartidosValues();
    partidosOrdenado.sort(comparador);

    int i = 1;
    for (Partido p : partidosOrdenado) {
      List<Candidato> candidatosMaisVotados = p.getCandidatosMaisVotados();

      if (!candidatosMaisVotados.isEmpty()) {
        if (candidatosMaisVotados.get(0).getVotos() > 0) {
          Candidato maisVotado = candidatosMaisVotados.get(0);
          Candidato menosVotado = candidatosMaisVotados.get(
            candidatosMaisVotados.size() - 1
          );

          System.out.printf(
            "%d - %s, %s (%d, %s %s) / %s (%d, %s %s)\n",
            i,
            p.toString(),
            maisVotado.getNome(),
            maisVotado.getNumero(),
            nfInt.format(maisVotado.getVotos()),
            maisVotado.getVotos() > 1 ? "votos" : "voto",
            menosVotado.getNome(),
            menosVotado.getNumero(),
            nfInt.format(menosVotado.getVotos()),
            menosVotado.getVotos() > 1 ? "votos" : "voto"
          );
          i++;
        }
      }
    }
    System.out.println();
  }

  public void geraRelatorio8() {
    int menor30 = 0;
    int menor40 = 0;
    int menor50 = 0;
    int menor60 = 0;
    int maior60 = 0;
    int total = 0;

    for (Candidato c : eleicao.getEleitos()) {
      int idade = Period
        .between(c.getDataNascimento(), eleicao.getData())
        .getYears();

      if (idade < 30) {
        menor30++;
      } else if (idade < 40) {
        menor40++;
      } else if (idade < 50) {
        menor50++;
      } else if (idade < 60) {
        menor60++;
      } else {
        maior60++;
      }
    }

    total = menor30 + menor40 + menor50 + menor60 + maior60;

    System.out.println("Eleitos, por faixa etária (na data da eleição):");
    System.out.printf(
      "      Idade < 30: %d (%s%%)\n",
      menor30,
      nfFloat.format((float) 100 * menor30 / total)
    );
    System.out.printf(
      "30 <= Idade < 40: %d (%s%%)\n",
      menor40,
      nfFloat.format((float) 100 * menor40 / total)
    );
    System.out.printf(
      "40 <= Idade < 50: %d (%s%%)\n",
      menor50,
      nfFloat.format((float) 100 * menor50 / total)
    );
    System.out.printf(
      "50 <= Idade < 60: %d (%s%%)\n",
      menor60,
      nfFloat.format((float) 100 * menor60 / total)
    );
    System.out.printf(
      "60 <= Idade     : %d (%s%%)\n",
      maior60,
      nfFloat.format((float) 100 * maior60 / total)
    );
    System.out.println();
  }

  public void geraRelatorio9() {
    int qtdFeminino = 0;
    int qtdMasculino = 0;
    int qtdTotal = 0;

    for (Candidato c : eleicao.getEleitos()) {
      if (c.getGenero() == Genero.FEMININO) {
        qtdFeminino++;
      } else if (c.getGenero() == Genero.MASCULINO) {
        qtdMasculino++;
      }
    }

    qtdTotal = qtdFeminino + qtdMasculino;

    System.out.println("Eleitos, por gênero:");
    System.out.printf(
      "Feminino:  %d (%s%%)\n",
      qtdFeminino,
      nfFloat.format((float) 100 * qtdFeminino / qtdTotal)
    );
    System.out.printf(
      "Masculino: %d (%s%%)\n",
      qtdMasculino,
      nfFloat.format((float) 100 * qtdMasculino / qtdTotal)
    );

    System.out.println();
  }

  public void geraRelatorio10() {
    int votosNominais = 0;
    int votosLegenda = 0;
    int votosTotal = 0;

    for (Candidato c : eleicao.getCandidatos().values()) {
      votosNominais += c.getVotos();
    }

    for (Partido p : eleicao.getPartidos().values()) {
      votosLegenda += p.getVotosLegenda();
    }

    votosTotal = votosLegenda + votosNominais;

    System.out.printf(
      "Total de votos válidos:     %s\n",
      nfInt.format(votosTotal)
    );
    System.out.printf(
      "Total de votos nominais:    %s (%s%%)\n",
      nfInt.format(votosNominais),
      nfFloat.format((float) 100 * votosNominais / votosTotal)
    );
    System.out.printf(
      "Total de votos de legenda:  %s (%s%%)\n",
      nfInt.format(votosLegenda),
      nfFloat.format((float) 100 * votosLegenda / votosTotal)
    );

    System.out.println();
  }

  public void geraRelatorios() {
    geraRelatorio1();
    geraRelatorio2();
    geraRelatorio3();
    geraRelatorio4();
    geraRelatorio5();
    geraRelatorio6();
    geraRelatorio7();
    geraRelatorio8();
    geraRelatorio9();
    geraRelatorio10();
  }
}
