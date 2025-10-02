package me.david;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.Proxy;
import java.net.InetSocketAddress;

public class PisstrNet {
    public static void ProxySocks(String host, int port, String urlLink) {
        URL url = null;
        HttpURLConnection con = null;
        BufferedReader bufferedReader = null;
        Proxy proxy = null;

        try{
            url = new URL(urlLink);
            proxy = new Proxy(Proxy.Type.SOCKS, new InetSocketAddress(host,port));

            con = (HttpURLConnection) url.openConnection(proxy);
            con.setConnectTimeout(10000);
            con.setReadTimeout(10000);

            bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String data;

            while((data = bufferedReader.readLine()) != null){
                System.out.println(data);
            }

            bufferedReader.close();
            con.disconnect();

        }catch(Exception e){e.printStackTrace();}
        finally {
            try{if(bufferedReader != null) bufferedReader.close();}catch(Exception e){e.printStackTrace();}
            try{if(con != null) con.disconnect();}catch(Exception e){e.printStackTrace();}
        }
    }

      public static void ProxyHttp(String host, int port, String urlLink){
        URL url = null;
        HttpURLConnection con = null;
        BufferedReader bufferedReader = null;
        Proxy proxy = null;

        try{
            url = new URL(urlLink);
            proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(host,port));

            con = (HttpURLConnection) url.openConnection(proxy);
            con.setConnectTimeout(10000);
            con.setReadTimeout(10000);

            bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String data;

            while((data = bufferedReader.readLine()) != null){
                System.out.println(data);
            }

            bufferedReader.close();
            con.disconnect();

        }catch(Exception e){e.printStackTrace();}
        finally {
            try{if(bufferedReader != null) bufferedReader.close();}catch(Exception e){e.printStackTrace();}
            try{if(con != null) con.disconnect();}catch(Exception e){e.printStackTrace();}
        }
    }

      public static String HttpReq(String urlLink){
        URL url = null;
        HttpURLConnection connection = null;
        BufferedReader bufferedReader = null;
        StringBuilder stringBuilder = null;

        try{
            url = new URL(urlLink);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int status = connection.getResponseCode();

            if(status == 0) {

                bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String data;

                stringBuilder = new StringBuilder();
                while ((data = bufferedReader.readLine()) != null)
                    stringBuilder.append(data);

                bufferedReader.close();
                connection.disconnect();
            }


        } catch (Exception e) {e.printStackTrace();}

        finally {
            try{if(bufferedReader != null) bufferedReader.close();} catch (Exception e) {e.printStackTrace();}
            try{if(connection != null) connection.disconnect();} catch (Exception e) {e.printStackTrace();}
        }

        return stringBuilder.toString();
    }

      public static String PopenCommand(String command){
        ProcessBuilder processBuilder = null;
        Process process = null;
        BufferedReader bufferedReader = null;
        StringBuilder stringBuilder = null;

        try{
            processBuilder = new ProcessBuilder();
            processBuilder.command("cmd.exe", "/c", command);

            process = processBuilder.start();

            bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String data;
            stringBuilder = new StringBuilder();

            while((data = bufferedReader.readLine()) != null){
                stringBuilder.append(data).append(System.lineSeparator());
            }

            process.waitFor();
            bufferedReader.close();

        } catch (Exception e) {e.printStackTrace();}

        finally {try{if(bufferedReader != null) bufferedReader.close();} catch (Exception e) {e.printStackTrace();}}

        return stringBuilder.toString();
    }
}
