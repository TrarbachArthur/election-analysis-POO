import eleicao.Eleicao;
import io.*;
import java.time.LocalDate;
import java.time.format.*;
import util.TipoCandidato;

public class App {

  public static void main(String[] args) {
    if (args.length < 4) {
      System.out.println(
        "Usage: java -jar deputados.jar <opção_de_cargo> <caminho_arquivo_candidatos> <caminho_arquivo_voacao> <data>"
      );
      System.exit(1);
    }

    int opcaoCargo;
    switch (args[0]) {
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
    } catch (DateTimeParseException e) {
      System.out.println("Data inválida");
      System.exit(1);
    }

    Eleicao eleicao = new Eleicao(TipoCandidato.parseInt(opcaoCargo), data);
    Leitor leitor = new Leitor(
      eleicao,
      caminhoArquivoCandidatos,
      caminhoArquivoVotacao
    );

    leitor.leArquivos();
    eleicao.processaEleicao();

    Relatorio relatorio = new Relatorio(eleicao);
    relatorio.geraRelatorios();
  }
}
