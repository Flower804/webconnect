import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.FileWriter;  
import java.util.Scanner;

public class Webconnect {
    static void WebConnect(String playername) { //TODO: when changed to wifi host based server, put the localhost connection protocoll on the catch section, I think it will be nice
        //localhost connection protocol
        System.out.println("running webConnect with player name " + playername);
        String hostname = "localhost";
        int port = 0000; //TODO: find if there is a more optimal way of doing this
        if(playername == "Tim"){
            port = 8080;
        }
        if(playername == "Yamisa"){
            port = 8000;
        }
        try {
            System.out.println("connecting to " + hostname + " on port " + port);
            Socket client = new Socket(hostname, port);
            System.out.println("connection succesfully esablished to " + hostname + " on port " + port);
            PrintWriter pw = new PrintWriter(client.getOutputStream());
            pw.println("GET / HTTP/1.1"); //do request to server for data and stuff
            pw.println("Host: "); 
            pw.println();
            pw.println("<html><body><h1>Hello world<\\h1><\\body><\\html>");
            pw.println();
            pw.flush();
            is_temp_on_dir(); //check if the final text file has already been created
            String name = filecreater(); //create final text file
            BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String servergatheredinfo; //variable for the text gathered from the localhost server with the membered connected by a comma
            while((servergatheredinfo = br.readLine()) != null){
                System.out.println(servergatheredinfo); 
                filewriter(servergatheredinfo, name); //send the text from the server and the name of the final text file to the call that will write the final file
            }
            client.close();
            br.close();
        }catch (IOException e) {
            System.out.println("connection was unstable or was unable to be executed");
            e.printStackTrace();
        }
    }
    
    public static String filecreater() {
        try {
            File myObj = new File("filename.txt"); //create a global file to store the text obtained in the html server
            if (myObj.createNewFile()){
                System.out.println("File created: " + myObj.getName());
                String filename = "filename.txt";
                return filename;
            } else {
                System.out.println("File already exists."); //TODO:I think this is kind of unecessary will check up on later date
                //is_temp_on_dir();
                String filename = "filename.txt";
                return filename;
            }
        } catch (IOException e){
            System.out.println("An error occurred :/");
        }
        return null;
    }
    static void filewriter(String servertext, String filename) {
        try{
            FileWriter myWriter = new FileWriter(filename);
            List<String> myList = new ArrayList<String>(Arrays.asList(servertext.split(","))); //turn the original text got from the localhost server into an arraylist with each element separated
            for (int i = 0; i < myList.size(); i++){ //write the text from the server into the final text file with the different elements in separate lines
                myWriter.write(myList.get(i) + '\n');
            }
            myWriter.close();
        } catch(IOException e){
            System.out.println("An error occurred writing to file " + filename); //this is bad
            e.printStackTrace();
        }
    }

    static void is_temp_on_dir(){
        try{
            File myObj = new File("filename.txt");
            if (myObj.createNewFile()){ //check if the finalfile exists
                System.out.println("temp Fyle exists");
            } else {
                System.out.println("temp file missing");
            }
            if (myObj.delete()) { 
                System.out.println("Deleted the file: " + myObj.getName()); //MIKU MIKU BEAMMMMMMM(delete file)
            } else {
                System.out.println("Failed to delete the file.");//oops :O
            }
        } catch(IOException e){
            System.out.println("An error occurred deleting the temp file");
            e.printStackTrace();
        }
    }
    //born to be full of love forced to be doomed by the narrative
    //I'm kinda lost on what to do after this point
    static String getStringfromFile() throws IOException { //TODO:I'm lost i don't know what this does but I'm scared that if i delet it the hole program will crash
        Path fileName = Path.of("filename.txt");
        String content = Files.readString(fileName);

        System.out.println(content);
        return content;
    }

    public static void main(String[] args) {
        System.out.print("\033[H\033[2J");  //clear screen
        System.out.flush();                   //still to clear the screen
        String playername = "Tim";
        Scanner myObj = new Scanner(System.in);
        System.out.println("1- Tim" + '\n');
        System.out.println("2 - Yamisa" + '\n');
        int userName = myObj.nextInt();
        if(userName == 1) {
            playername = "Tim";
        } else {
            playername = "Yamisa";
        }
        myObj.close();
        WebConnect(playername);
        //filereader_translater();
    }
}