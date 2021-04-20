import org.junit.After;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class EchoServer {

    private  ServerSocket serverSocket;



    public void start(int port) {

        try {
            serverSocket = new ServerSocket(port);
            while (true) {
                new EchoClientHandler(serverSocket.accept()).start();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private  static  class EchoClientHandler extends Thread {

        private Socket clientSocket ;
        private PrintWriter out ;
        private BufferedReader in ;
        private ArrayList<String> headers = new ArrayList<>();


        public  EchoClientHandler(Socket socket) {
            this.clientSocket = socket ;
        }





        public void run() {

            ContentReader cr = new ContentReader();

            try {
                out = new PrintWriter(clientSocket.getOutputStream(),true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                HeadersParser parser = new HeadersParser(in);
                parser.InitializeMethodPathHttp();

                String method = parser.getMethod();
                String PathUrl = parser.getPathUrl();
                String HttpVersion = parser.getHttpVersion();


               urls urls = new urls(method,PathUrl,HttpVersion,clientSocket);
               urls.UrlsCheck();


               AllowStatic allowStatic = new AllowStatic(method,PathUrl,HttpVersion,clientSocket);
               allowStatic.addStaticFolder("Static/");


                in.close();
                out.close();
                clientSocket.close();


            } catch (IOException e) {
                e.printStackTrace();
            }


        }



    }






}
