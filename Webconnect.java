import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.File;
import java.io.FileWriter;  
import java.util.Scanner;

public class Webconnect {
    static void WebConnect(String playername) {
        System.out.println("running webConnect with player name " + playername);
        String hostname = "localhost";
        int port = 0000;
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
            pw.println("GET / HTTP/1.1");
            pw.println("Host: ");
            pw.println();
            pw.println("<html><body><h1>Hello world<\\h1><\\body><\\html>");
            pw.println();
            pw.flush();
            is_temp_on_dir();
            String name = filecreater();
            BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String t;
            while((t = br.readLine()) != null){
                System.out.println(t);
                filewriter(t, name);
            }
            client.close();
            br.close();
        }catch (IOException e) {
            System.out.println("connection was unstable or was unable to be executed");
        }
    }
    
    public static String filecreater() {
        try {
            File myObj = new File("filename.txt");
            if (myObj.createNewFile()){
                System.out.println("File created: " + myObj.getName());
                String filename = "filename.txt";
                return filename;
            } else {
                System.out.println("File already exists.");
                //is_temp_on_dir();
                String filename = "filename.txt";
                return filename;
            }
        } catch (IOException e){
            System.out.println("An error occurred :/");
        }
        return null;
    }
    static void filewriter(String t, String name) {
        try{
            FileWriter myWriter = new FileWriter(name);
            myWriter.write(t);
            myWriter.close();
        } catch(IOException e){
            System.out.println("An error occurred writing to file " + name);
            e.printStackTrace();
        }
    }

    static void is_temp_on_dir( ){
        try{
            File myObj = new File("filename.txt");
            if (myObj.createNewFile()){
                System.out.println("temp Fyle exists");
            } else {
                System.out.println("temp file missing");
            }
            if (myObj.delete()) { 
                System.out.println("Deleted the file: " + myObj.getName()); //MIKU MIKU BEAMMMMMMM
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
        WebConnect(playername);
    }
}