import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ManyFormsReading {
    public static void main(String[] args) {

        // lendo byte a byte
        System.out.print("Lendo byte a byte: ");
        try (FileInputStream fin = new FileInputStream("a.txt")) {
            int i = fin.read();
            while (i != -1) {
                System.out.print(i);
                i = fin.read();
                if (i != -1)
                    System.out.print(", ");
            }
            System.out.println("");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Lendo caracter a caracter, especificando charset
        System.out.print("Lendo caracter a caracter (ISO-8859-1): ");
        try (FileInputStream fin = new FileInputStream("a.txt");
                InputStreamReader r = new InputStreamReader(fin, "ISO-8859-1")) {
            int i = r.read();
            while (i != -1) {
                System.out.print(i);
                i = r.read();
                if (i != -1)
                    System.out.print(", ");
            }
            System.out.println("");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Lendo caracter a caracter, especificando charset
        System.out.print("Lendo caracter a caracter (UTF-8): ");
        try (FileInputStream fin = new FileInputStream("a.txt");
                InputStreamReader r = new InputStreamReader(fin, "UTF-8")) {
            int i = r.read();
            while (i != -1) {
                System.out.print(i);
                i = r.read();
                if (i != -1)
                    System.out.print(", ");
            }
            System.out.println("");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // lendo linhas inteiras com codificação ISO 8859-1
        System.out.println("Lendo linhas inteiras (ISO 8859-1): ");
        try (FileInputStream fin = new FileInputStream("a.txt");
                InputStreamReader r = new InputStreamReader(fin, "ISO-8859-1");
                BufferedReader br = new BufferedReader(r);) {
            String line = br.readLine();
            while (line != null) {
                System.out.println(line);
                line = br.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // lendo linhas inteiras com codificação UTF-8
        System.out.println("Lendo linhas inteiras (UTF-8): ");
        try (FileInputStream fin = new FileInputStream("a.txt");
                InputStreamReader r = new InputStreamReader(fin, "UTF-8");
                BufferedReader br = new BufferedReader(r);) {
            String line = br.readLine();
            while (line != null) {
                System.out.println(line);
                line = br.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //List<String> linhas = new ArrayList(1200000);
        // lendo linhas inteiras com Scanner
        System.out.println("Lendo linhas inteiras com Scanner (ISO 8859-1): ");
        try (FileInputStream fin = new FileInputStream("a.txt");
                Scanner s = new Scanner(fin, "ISO-8859-1")) {
            while (s.hasNextLine()) {
                String line = s.nextLine();
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        // lendo linhas inteiras com Scanner
        System.out.println("Lendo linhas inteiras com Scanner (UTF-8): ");
        try (FileInputStream fin = new FileInputStream("a.txt");
                Scanner s = new Scanner(fin, "UTF-8")) {
            while (s.hasNextLine()) {
                String line = s.nextLine();
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
