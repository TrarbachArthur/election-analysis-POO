package io;

import java.io.FileInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import eleicao.Eleicao;

public class Leitor {
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

    private static String le_conteudo_string(int i, String[] separated){
        String x = separated[i].substring(1, separated[i].length() - 1);
        return x;
    }
    private static Integer le_conteudo_int(int i, String[] separated){
        String x = separated[i].substring(1, separated[i].length() - 1);
        int y = Integer.parseInt(x);
        return y;
    }

    public void leArquivos(Eleicao eleicao, String caminhoArquivoVotacao, String caminhoArquivoCandidatos) {
        // LENDO O ARQ DE CANDIDATOS
        try (FileInputStream fin = new FileInputStream(caminhoArquivoCandidatos);
                Scanner s = new Scanner(fin, "ISO-8859-1")) {
                s.nextLine();
                
            while (s.hasNextLine()) {
                String line = s.nextLine();
                String [] separated = line.split(";");

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
                
                Date dataNascimento = null;
                try {
                    dataNascimento = new SimpleDateFormat("dd/MM/yyyy").parse(strNascimento);
                }
                catch (ParseException e) {
                    System.out.println("Data inválida");
                    e.printStackTrace();
                    
                    continue; // Ignora candidatos com data de nascimento invalida
                }

                boolean ehFederado;
                if (numeroFederacao == -1) {ehFederado= false;}
                else{ ehFederado = true;}
                
                boolean ehEleito;
                if (situacaoTurno == 2 || situacaoTurno == 3) {ehEleito = true;}
                else {ehEleito = false;}

                boolean ehVotoLegenda;
                if (strTipoVoto == "Válido (legenda)") {ehVotoLegenda = true;}
                else {ehVotoLegenda = false;}

                // Processando apenas candidaturas deferidas
                if (situacaoCandidatura == 2 || situacaoCandidatura == 16) {
                    eleicao.processaCandidato(
                        cargo, numeroCandidato, nomeCandidato, numeroPartido, siglaPartido,
                        ehFederado, dataNascimento, ehEleito, genero, ehVotoLegenda
                    );
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // LENDO O ARQ DE VOTAÇÃO
        try (FileInputStream fin = new FileInputStream(caminhoArquivoVotacao);
            Scanner s = new Scanner(fin, "ISO-8859-1")) {
                
            s.nextLine();
            while (s.hasNextLine()) {
                String line = s.nextLine();
                String [] separated = line.split(";");

                int cargo = (le_conteudo_int(CD_CARGO, separated));
                int numeroVotado = (le_conteudo_int(NR_VOTAVEL, separated));
                int qtdVotos = (le_conteudo_int(QT_VOTOS, separated));

                if (numeroVotado != 95 || numeroVotado != 96 || numeroVotado != 97 || numeroVotado != 98) {
                    eleicao.processaVotos(cargo, numeroVotado, qtdVotos);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
