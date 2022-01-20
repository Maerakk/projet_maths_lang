import java.io.*;
import java.util.List;

public class fonctionsSubsidiaires {

    private static List<String> author_list = List.of("balzac.txt", "hugo.txt", "maupassant.txt", "moliere.txt", "montaigne.txt", "sand.txt", "tocqueville.txt", "tolstoi.txt", "verne.txt", "zola.txt");


    public static void main(String[] args) throws IOException {
        lineOnly();
    }


    public static void lineOnly() throws IOException {
        // find the file with least lines
        long max_line = Long.MAX_VALUE;
        for (String author:author_list) {
            System.out.println("reading "+author);
            BufferedReader reader = new BufferedReader(new FileReader(new File("data/author_corpus/train/"+author)));
            String line = reader.readLine();
            long line_count = 0;
            while (line != null ){
                line_count++;
                System.out.println("reading line "+String.valueOf(line_count)+" of "+author);
                line = reader.readLine();
            }
            if(line_count < max_line){
                max_line = line_count;
            }
        }

        for (String author:author_list) {
            System.out.println("writing "+author+" with "+String.valueOf(max_line)+" line");
            BufferedReader reader = new BufferedReader(new FileReader(new File("data/author_corpus/train/"+author)));
            String line = reader.readLine();
            PrintWriter new_file = new PrintWriter("data/author_corpus/train/same_line_amount/"+author);
            for( long line_count = 0; line_count < max_line; line_count++){
                new_file.append(line).append("\n");
                line = reader.readLine();
            }
        }

        System.out.println("Done !");
    }

}
