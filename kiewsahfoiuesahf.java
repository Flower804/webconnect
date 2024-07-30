import java.io.File;  // Import the File class
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.FileWriter;

public class kiewsahfoiuesahf {
    
    public static void main(String[] args) {
        //String text = "abcdefg, hipqq";
        try {
          File myObj = new File("filename.txt");
          if (myObj.createNewFile()) {
            System.out.println("File created: " + myObj.getName());
          } else {
            System.out.println("File already exists.");
          }
        } catch (IOException e) {
          System.out.println("An error occurred.");
          e.printStackTrace();
        }
        //file writer start
        String filename = "filename2.txt";
        //try {
        //    FileWriter myWriter = new FileWriter(filename);
        //    String t = "abc";
        //    String w = "def";
        //    myWriter.write(t + '\n');
        //    myWriter.write(w);
        //    myWriter.close();
        //} catch (IOException e) {
        //    e.printStackTrace();
        //}
        try {
            String text = "abcdefg, hipq";
            List<String> myList = new ArrayList<String>(Arrays.asList(text.split(",")));
            FileWriter myWriter = new FileWriter(filename);
            for (int i = 0; i < myList.size(); i++) {
                myWriter.write(myList.get(i) + '\n');
            }
            myWriter.close();

        } catch(IOException e) {
            e.printStackTrace();
        }
        //List<String> myList = new ArrayList<String>(Arrays.asList(text.split(",")));
        //System.out.println(myList);

    }
}
