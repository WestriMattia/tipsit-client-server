    package server;
    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.io.PrintWriter;
    import java.net.Socket;
    
    public class Handler extends Thread {
        private Socket s;
        private PrintWriter pr = null;
        private BufferedReader br = null;
        private int contatore;
    
        public Handler(Socket s, int c) {
            this.s = s;
            contatore = c;
            try {
                pr = new PrintWriter(s.getOutputStream(), true);
                br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    
        public void run() {
            try {
                System.out.println(br.readLine());
                pr.println("Come ti chiami?"); 
                String nome = br.readLine(); 
                String nomeUpper = nome.toUpperCase(); 
                System.out.println("Nome utente: " + nomeUpper + " connesso"); 
                pr.println("Ciao " + nomeUpper + " sei l'utente numero " + contatore); 
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
