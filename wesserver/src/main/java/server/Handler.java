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
        private static String nome;
        private static int contatore;

        public Handler(Socket s, int c, String nome) {
            this.s = s;
            contatore = c;
            this.nome = nome;
            try {
                pr = new PrintWriter(s.getOutputStream(), true);
                br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    
        public void run() {
            pr.println("Ciao, sono il sever. Puoi fare: 1)data, 2)ora, 3)id, 4)nome, 5)stop");
            try {
                while(true){
                    String str = br.readLine();
                    if(str.equals("data")){
                        data(pr);
                    }else if(str.equals("ora")){
                        ora(pr);
                    }else if(str.equals("id")){
                        id(pr);
                    }else if(str.equals("nome")){
                        nomes(pr);
                    }else if(str.equals("stop")){
                        pr.println("Addio...");
                        s.close();
                    }else{
                        pr.println("Comando " + str + " non trovato");  
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public static void data(PrintWriter pr) throws Exception{
            pr.println("Oggi e' il: " + java.time.LocalDate.now());
            return;
        }

        public static void ora(PrintWriter pr) throws Exception{
            pr.println("Oggi e' il: " + java.time.LocalTime.now());
            return;
        }

        public static void id(PrintWriter pr) throws Exception{
            pr.println("Il tuo id è: " + contatore);
            return;
        }

        public static void nomes(PrintWriter pr) throws Exception{
            pr.println("Nome server: " + nome);
            return;
        }
    }
