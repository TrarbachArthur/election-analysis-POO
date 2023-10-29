import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import eleicao.Eleicao;
import io.Leitor;

// Imports para teste, lembrar de remover
import eleicao.Candidato;
import eleicao.Partido;

public class App {
        public static void main(String[] args) {
            if (args.length < 4) {
                System.out.println("Usage: java -jar deputados.jar <opção_de_cargo> <caminho_arquivo_candidatos> <caminho_arquivo_voacao> <data>");
                System.exit(1);
            }

            int opcaoCargo = Integer.parseInt(args[0]);
            String caminhoArquivoCandidatos = args[1];
            String caminhoArquivoVotacao = args[2];
            Date data = null;

            try {
                data = new SimpleDateFormat("dd/MM/yyyy").parse(args[3]);
            }
            catch (ParseException e) {
                System.out.println("Data inválida");
                e.printStackTrace();
                System.exit(1);
            }

            Eleicao eleicao = new Eleicao(opcaoCargo, data);
            
            // TODO: ajustar leitor para classe utilizavel abaixo :P
            Leitor leitor = new Leitor();
            eleicao = leitor.leArquivos(eleicao, caminhoArquivoVotacao, caminhoArquivoCandidatos);
           
           /* 
                Apenas testes, lembrar de remover depois
           */
            //Candidato candidato = new Candidato(opcaoCargo, 1, "Candidato 1", new Partido(1, "Partido 1", "P1"), false, new Date(), true, 2, false);
            //candidato.processaVotos(8500);
            //candidato.processaVotos(1000);

            //System.out.println(candidato);
        }
}
