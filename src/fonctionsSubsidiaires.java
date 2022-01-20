import java.io.*;
import java.util.List;

public class fonctionsSubsidiaires {

    private static List<String> author_list = List.of("balzac.txt", "hugo.txt", "maupassant.txt", "moliere.txt", "montaigne.txt", "sand.txt", "tocqueville.txt", "tolstoi.txt", "verne.txt", "zola.txt");


    public static void main(String[] args) throws IOException {
        // minimumLine();
        minimumWord();
    }



    public static void minimumLine() throws IOException {
        // find the file with minimum amount of lines
        long min_line = Long.MAX_VALUE;
        // we go through the files
        for (String author:author_list) {
            System.out.println("reading "+author);
            // give info in console (func is quite long)
            BufferedReader reader = new BufferedReader(new FileReader(new File("data/author_corpus/train/"+author)));
            // create bufferedReader
            String line = reader.readLine();
            // init of line var
            long line_count = 0;
            // init of line counter
            while (line != null ){
                // we go through every line of the file
                line_count++;
                line = reader.readLine();
            }
            // and keep the most little
            if(line_count < min_line){
                min_line = line_count;
            }
        }

        // once the minimum amount of line got
        // we can write files with the same amount of lines
        for (String author:author_list) {
            // go through each author
            System.out.println("writing "+author+" with "+String.valueOf(min_line)+" line");
            // give info in console
            BufferedReader reader = new BufferedReader(new FileReader(new File("data/author_corpus/train/"+author)));
            String line = reader.readLine();
            PrintWriter new_file = new PrintWriter("data/author_corpus/train/same_line_amount/"+author);
            for(long line_count = 0; line_count < min_line; line_count++){
                new_file.append(line).append("\n");
                line = reader.readLine();
            }
            new_file.close();
        }
        System.out.println("Done !");
    }

    public static void minimumWord() throws IOException{
        // find the file with minimum amount of lines
        long min_words = Long.MAX_VALUE;
        // we go through the files
        for (String author:author_list) {
            System.out.println("reading "+author);
            // give info in console (func is quite long)
            BufferedReader reader = new BufferedReader(new FileReader(new File("data/author_corpus/train/"+author)));
            // create bufferedReader
            String line = reader.readLine();
            long word_count = 0;
            while (line != null ){
                // we go through every line of the file
                word_count += line.split(" ").length;
                line = reader.readLine();
            }
            // and keep the most little
            if(word_count < min_words){
                min_words = word_count;
            }
        }

        // once the minimum amount of line got
        // we can write files with the same amount of lines
        for (String author:author_list) {
            // go through each author
            System.out.println("writing "+author+" with "+String.valueOf(min_words)+" line");
            // give info in console
            BufferedReader reader = new BufferedReader(new FileReader(new File("data/author_corpus/train/"+author)));
            String line = reader.readLine();
            PrintWriter new_file = new PrintWriter("data/author_corpus/train/same_word_amount/"+author);
            long word_count=0;
            // its not exactly the same amount of words but we keep entire phrases and lines
            while(word_count < min_words){
                new_file.append(line).append("\n");
                word_count += line.split(" ").length;
                line = reader.readLine();
            }
            new_file.close();
        }

        System.out.println("Done !");
    }

}
