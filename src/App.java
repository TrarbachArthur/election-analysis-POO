import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.time.format.*;
import java.time.LocalDate;
import eleicao.Eleicao;
import io.*;

// Imports para teste, lembrar de remover
import eleicao.Candidato;
import eleicao.Partido;

public class App {
    public static void main(String[] args) {
        if (args.length < 4) {
            System.out.println("Usage: java -jar deputados.jar <opção_de_cargo> <caminho_arquivo_candidatos> <caminho_arquivo_voacao> <data>");
            System.exit(1);
        }

        int opcaoCargo;
        switch(args[0]) {
            case "--federal":
                opcaoCargo = 6;
                break;
            case "--estadual":
                opcaoCargo = 7;
                break;
            default:
                throw new RuntimeException("Opção de cargo inválida");
        }
        
        String caminhoArquivoCandidatos = args[1];
        String caminhoArquivoVotacao = args[2];
        LocalDate data = null;

        try {
            data = LocalDate.parse(args[3], DateTimeFormatter.ofPattern("d/MM/yyyy"));
        }
        catch (DateTimeParseException e) {
            System.out.println("Data inválida");
            e.printStackTrace();
            System.exit(1);
        }

        Eleicao eleicao = new Eleicao(opcaoCargo, data);

        Leitor.leArquivos(eleicao, caminhoArquivoVotacao, caminhoArquivoCandidatos);
        
        Relatorio.geraRelatorio8(eleicao);
        Relatorio.geraRelatorio9(eleicao);
        Relatorio.geraRelatorio10(eleicao);

        /* 
            Apenas testes, lembrar de remover depois
        */
        /* 
        for (Candidato c: eleicao.getCandidatos().values()) {
            System.out.println(c);
        }
        
        System.out.println("Candidatos eleitos: ");
        for (Candidato c: eleicao.getEleitos()) {
            System.out.println(c);
        }
        System.out.println("");

        System.out.println("Quantidade de partidos: " + eleicao.getPartidos().size());

        for (Partido p: eleicao.getPartidos().values()) {
            System.out.println(p + "\n--------------------------------------------------------------------------");
            for (Candidato c: p.getCandidatosDeferidos().values()) {
                System.out.println(c);
            }
            System.out.println("\n--------------------------------------------------------------------------\n");
        }
        */        
    }
}
