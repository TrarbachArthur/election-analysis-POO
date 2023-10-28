package io;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Leitor {
    private static final int IN_CAP_VOT = 1200000;
    private static final int IN_CAP_CAND = 800;
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

    public static String le_conteudo_string(int i, String[] separaded){
        String x = separaded[i].substring(1, separaded[i].length() - 1);
        return x;
    }
    public static Integer le_conteudo_int(int i, String[] separaded){
        String x = separaded[i].substring(1, separaded[i].length() - 1);
        int y = Integer.parseInt(x);
        return y;
    }

    public static void main(String[] args) {
    List<Integer> cargos = new ArrayList<Integer>(IN_CAP_VOT);
    List<Integer> nr_votaveis = new ArrayList<Integer>(IN_CAP_VOT);
    List<Integer> qt_votos = new ArrayList<Integer>(IN_CAP_VOT);
    List<Integer> cargos_cand = new ArrayList<Integer>(IN_CAP_CAND);
    List<Integer> cd_situs = new ArrayList<Integer>(IN_CAP_CAND);
    List<Integer> nr_cands = new ArrayList<Integer>(IN_CAP_CAND);
    List<String> nm_urna = new ArrayList<String>(IN_CAP_CAND);
    List<Integer> nr_partido = new ArrayList<Integer>(IN_CAP_CAND);
    List<String> sg_partido = new ArrayList<String>(IN_CAP_CAND);
    List<Integer> nr_fed = new ArrayList<Integer>(IN_CAP_CAND);
    List<String> dt_nasc = new ArrayList<String>(IN_CAP_CAND);
    List<Integer> cd_sit = new ArrayList<Integer>(IN_CAP_CAND);
    List<Integer> cd_gen = new ArrayList<Integer>(IN_CAP_CAND);
    List<String> nm_tipo = new ArrayList<String>(IN_CAP_CAND); 

        // LENDO O ARQ DE VOTAÇÃO
        try (FileInputStream fin = new FileInputStream("votacao_secao_2022_ES\\votacao_secao_2022_ES.txt");
                Scanner s = new Scanner(fin, "UTF-8")) {
                    s.nextLine();
            while (s.hasNextLine()) {
                String line = s.nextLine();
                String [] separaded = line.split(";");
                cargos.add(le_conteudo_int(CD_CARGO, separaded));
                nr_votaveis.add(le_conteudo_int(NR_VOTAVEL, separaded));
                qt_votos.add(le_conteudo_int(QT_VOTOS, separaded));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // System.out.println(cargos);
        // System.out.println(nr_votaveis);
        // System.out.println(qt_votos);

        // LENDO O ARQ DE CANDIDATOS
        try (FileInputStream fin = new FileInputStream("votacao_secao_2022_ES\\consulta_cand_2022_ES.txt");
                Scanner s = new Scanner(fin, "UTF-8")) {
                s.nextLine();
            while (s.hasNextLine()) {
                String line = s.nextLine();
                String [] separaded = line.split(";");
                cargos_cand.add(le_conteudo_int(CD_CARGO_CAND, separaded));
                cd_situs.add(le_conteudo_int(CD_SITU, separaded));
                nr_cands.add(le_conteudo_int(NR_CAND, separaded));
                nm_urna.add(le_conteudo_string(NM_URNA, separaded));
                nr_partido.add(le_conteudo_int(NR_PART, separaded));
                sg_partido.add(le_conteudo_string(SG_PART, separaded));
                nr_fed.add(le_conteudo_int(NR_FED, separaded));
                dt_nasc.add(le_conteudo_string(DT_NASC, separaded));
                cd_sit.add(le_conteudo_int(CD_SIT, separaded));
                cd_gen.add(le_conteudo_int(CD_GEN, separaded));
                nm_tipo.add(le_conteudo_string(NM_TIPO, separaded));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // System.out.println(lines.get(0));
        // System.out.println(lines.get(1000));
        System.out.println(dt_nasc);
        }
}
