package me.david;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Pisstr {
      public static boolean Pathing(String Path){
        File file = null;

        boolean result = false;

        try{
            file = new File(Path);
            if(file.exists())
                result = true;

        } catch (Exception e) {e.printStackTrace();}
        return result;
    }
      public static void HideFile(String Path){
        ProcessBuilder processBuilder = null;
        Process process = null;

        try{
            processBuilder = new ProcessBuilder();
            processBuilder.command("cmd.exe","/c", "attrib +s +h " + Path);

            process = processBuilder.start();
            processBuilder.redirectOutput(ProcessBuilder.Redirect.DISCARD);
            processBuilder.redirectError(ProcessBuilder.Redirect.DISCARD);
            process.waitFor();

        }catch(Exception e){e.printStackTrace();}
    }

    public static void UnHideFile(String Path){
        ProcessBuilder processBuilder = null;
        Process process = null;

        try{
            processBuilder = new ProcessBuilder();
            processBuilder.command("cmd.exe","/c", "attrib -s -h " + Path);

            process = processBuilder.start();
            processBuilder.redirectOutput(ProcessBuilder.Redirect.DISCARD);
            processBuilder.redirectError(ProcessBuilder.Redirect.DISCARD);
            process.waitFor();

        }catch(Exception e){e.printStackTrace();}
    }

    public static int PathRunnable(){
        ProcessBuilder processBuilder = null;
        Process process = null;

        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {


            System.out.println("Enter the argument -> ");
            String data_argument = bufferedReader.readLine();

            System.out.println("Enter the path -> ");
            String data_path = bufferedReader.readLine();

            File file = new File(data_path);
            if(!file.exists()) {
                System.out.println("File does not exist");
                return 1;
            }

            String data_final = data_argument + " " + data_path;

            processBuilder = new ProcessBuilder();
            processBuilder.command("cmd.exe","/c", data_final);
            processBuilder.redirectOutput(ProcessBuilder.Redirect.DISCARD);
            processBuilder.redirectError(ProcessBuilder.Redirect.DISCARD);

            process = processBuilder.start();
            process.waitFor();


        }catch(Exception e){e.printStackTrace();}
        return 0;
    }

    public static void WriteFile(String Path){
        FileWriter fw = null;
        Scanner scan = null;

        try{
            if(!Pathing(Path))
                System.exit(1);
            fw = new FileWriter(Path);
            scan = new Scanner(System.in);

            while (true) {
                System.out.println("Enter the text -> ");
                String data = scan.nextLine();

                if(data.equalsIgnoreCase("bye"))
                    break;
                else {
                    fw.write(data);
                    fw.flush();
                }
            }

            fw.close();
            scan.close();

        } catch (Exception e) {e.printStackTrace();}

        finally {
            try{ if(fw != null) fw.close(); } catch (Exception e) { e.printStackTrace();}
            try{ if(scan != null) scan.close(); } catch (Exception e) { e.printStackTrace();}
        }
    }

    public static void ReadFile(String Path){
        File file = new File(Path);
        try (Scanner scan = new Scanner(file)){

            if (!(file.exists())) {
                System.out.println("File does not exist in the path ");
                System.exit(1);
            }
            while(scan.hasNextLine()){
                String data = scan.nextLine();
                System.out.println(data);
            }

        } catch(Exception e){e.printStackTrace();}
    }

      public static void FileIterator(String Path){

        Path files = Paths.get(Path);
        BufferedReader bufferedReader = null;

        try {
            System.out.println("Do you want to iterate through all of files(Y/N) -> ");
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String response = bufferedReader.readLine();

            if(response.equalsIgnoreCase("yes") || response.equalsIgnoreCase("y")){

                try{
                    Files.walk(files).forEach(inter -> System.out.println(inter.toAbsolutePath()));

                } catch (IOException e) {e.printStackTrace();}

            } else if (response.equalsIgnoreCase("no") || response.equalsIgnoreCase("n")) {
                try{
                    System.out.println("Enter the extension of the file -> ");
                    bufferedReader.readLine();
                    Files.walk(files).filter(inter -> inter.toString().endsWith(response)).forEach(System.out::println);

                } catch (IOException e) {e.printStackTrace();}
            }
            bufferedReader.close();
        } catch (Exception e) {e.printStackTrace();}
        
        finally {try{if(bufferedReader != null) bufferedReader.close();} catch (Exception e) {e.printStackTrace();}}
 
    }
}
