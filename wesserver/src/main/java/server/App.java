package server;

import java.net.ServerSocket;
import java.net.Socket;

public class App{
  public static void main(String[] args) throws Exception {
    ServerSocket ss = new ServerSocket(3000);
    System.out.println("In ascolto sulla porta 3000");
    final String nome="bambino";
    int c = 1;
    boolean running = true;
    while (running) {
      Socket s = ss.accept();
      System.out.println("C connesso");
      Handler client = new Handler(s, c, nome);
      c++;
      client.start();
    }
    ss.close();
  }
}
