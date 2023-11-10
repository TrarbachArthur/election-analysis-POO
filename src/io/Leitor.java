package io;

import eleicao.Eleicao;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.*;
import util.Genero;
import util.TipoCandidato;

public class Leitor {

  private Eleicao eleicao;
  private String caminhoArquivoCandidatos;
  private String caminhoArquivoVotacao;

  public Leitor(
    Eleicao eleicao,
    String caminhoArquivoCandidatos,
    String caminhoArquivoVotacao
  ) {
    this.eleicao = eleicao;
    this.caminhoArquivoCandidatos = caminhoArquivoCandidatos;
    this.caminhoArquivoVotacao = caminhoArquivoVotacao;
  }

  private static final int CD_CARGO = 17;
  private static final int NR_VOTAVEL = 19;
  private static final int QT_VOTOS = 21;
  private static final int CD_CARGO_CAND = 13;
  private static final int CD_SITU = 68;
  private static final int NR_CAND = 16;
  private static final int NM_URNA = 18;
  private static final int NR_PART = 27;
  private static final int SG_PART = 28;
  private static final int NR_FED = 30;
  private static final int DT_NASC = 42;
  private static final int CD_SIT = 56;
  private static final int CD_GEN = 45;
  private static final int NM_TIPO = 67;

  private static String le_conteudo_string(int i, String[] separated) {
    String x = "";
    try {
      x = separated[i].replaceAll("\"", "");
    } catch (Exception e) {
      System.out.println("Arquivo fora do padrão fornecido!");
      System.exit(1);
    }
    return x;
  }

  private static Integer le_conteudo_int(int i, String[] separated) {
    int y = 0;
    try {
      String x = separated[i].replaceAll("\"", "");
      y = Integer.parseInt(x);
    } catch (Exception e) {
      System.out.println("Arquivo fora do padrão fornecido!");
      System.exit(1);
    }
    return y;
  }

  public void leArquivos() {
    // LENDO O ARQ DE CANDIDATOS
    try (
      FileInputStream fin = new FileInputStream(caminhoArquivoCandidatos);
      InputStreamReader isr = new InputStreamReader(fin, "ISO-8859-1");
      BufferedReader r = new BufferedReader(isr)
    ) {
      String line = r.readLine();

      while ((line = r.readLine()) != null) {
        String[] separated = line.split(";");

        int cargo = (le_conteudo_int(CD_CARGO_CAND, separated));
        int situacaoCandidatura = (le_conteudo_int(CD_SITU, separated));
        int numeroCandidato = (le_conteudo_int(NR_CAND, separated));
        String nomeCandidato = (le_conteudo_string(NM_URNA, separated));
        int numeroPartido = (le_conteudo_int(NR_PART, separated));
        String siglaPartido = (le_conteudo_string(SG_PART, separated));
        int numeroFederacao = (le_conteudo_int(NR_FED, separated));
        String strNascimento = (le_conteudo_string(DT_NASC, separated));
        int situacaoTurno = (le_conteudo_int(CD_SIT, separated));
        int genero = (le_conteudo_int(CD_GEN, separated));
        String strTipoVoto = (le_conteudo_string(NM_TIPO, separated));

        LocalDate dataNascimento = null;
        try {
          dataNascimento =
            LocalDate.parse(
              strNascimento,
              DateTimeFormatter.ofPattern("dd/MM/yyyy")
            );
        } catch (DateTimeParseException e) {
          // Ignora candidatos com data de nascimento inválida
          continue;
        }

        boolean ehFederado = true;
        if (numeroFederacao == -1) {
          ehFederado = false;
        }

        boolean ehEleito = false;
        if (situacaoTurno == 2 || situacaoTurno == 3) {
          ehEleito = true;
        }

        boolean ehVotoLegenda = false;
        if (strTipoVoto.equalsIgnoreCase("Válido (legenda)")) {
          ehVotoLegenda = true;
        }

        boolean ehDeferido = false;
        if (situacaoCandidatura == 2 || situacaoCandidatura == 16) {
          ehDeferido = true;
        }

        eleicao.processaCandidato(
          TipoCandidato.parseInt(cargo),
          ehDeferido,
          numeroCandidato,
          nomeCandidato,
          numeroPartido,
          siglaPartido,
          ehFederado,
          dataNascimento,
          ehEleito,
          Genero.parseInt(genero),
          ehVotoLegenda
        );
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    // LENDO O ARQ DE VOTAÇÃO
    try (
      FileInputStream fin = new FileInputStream(caminhoArquivoVotacao);
      InputStreamReader isr = new InputStreamReader(fin, "ISO-8859-1");
      BufferedReader r = new BufferedReader(isr)
    ) {
      String line = r.readLine();

      while ((line = r.readLine()) != null) {
        String[] separated = line.split(";");

        int cargo = (le_conteudo_int(CD_CARGO, separated));
        int numeroVotado = (le_conteudo_int(NR_VOTAVEL, separated));
        int qtdVotos = (le_conteudo_int(QT_VOTOS, separated));

        if (
          numeroVotado != 95 ||
          numeroVotado != 96 ||
          numeroVotado != 97 ||
          numeroVotado != 98
        ) {
          eleicao.processaVotos(
            TipoCandidato.parseInt(cargo),
            numeroVotado,
            qtdVotos
          );
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
