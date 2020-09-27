/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author ACER
 */
public class Server {
    /* 1 - Criar o servidor de conexoes
    2 - Esperar um pedido de conexao; 
    Outro Processo
    2.1 - e criar uma nova conexao;
    3 - Criar streams de entrada e saída;
    4 - Tratar a convensacao entre cliente e servidor (tratar protocolo)
    4.1 - Fechar socket de comunicacao entre servidor/cliente
    4.2 - Fechar streams  de entrada e saída
    5 - voltar para o passo 2, até que finalize o programa;
    6 - fechar serverSocket
    */
    
    private ServerSocket serverSocket;
    
    private void criarServerSocket(int porta) throws IOException{
    
        serverSocket = new ServerSocket(porta);
        
    }
    
    private Socket esperaConexao() throws IOException{
    Socket socket = serverSocket.accept();
    return socket;
    }
    
    private void fechaSocket(Socket s) throws IOException{
    s.close();
    }
    
    private void trataConexão(Socket socket) throws IOException{
    // Protocolo da aplicacao
    /*
    3 - Criar streams de entrada e saida
    4 - Tratar a conversacao entre cliente e 
    servidor (tratar protocolo);
    */
    try{
        //  3 - Criar streams de entrada e saida
        
    ObjectOutputStream output = new  ObjectOutputStream(socket.getOutputStream()) ;
    ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
    
    /* Protocolo
    Cliente --> HELLO
    Server <---- Hello WORLD
    
    4 - Tratar a conversacao entre cliente e 
    servidor (tratar protocolo);*/
    
        String msg = input.readUTF();
        System.out.println("Mensagem recebida....");
        output.writeUTF("HELLO WORLD");
        
        //4.2 - Fechar streams  de entrada e saída
        input.close();
        output.close();
        
       //output.writeDouble(0);
        } catch (IOException e) {
            // Tratamento de falhas
            JOptionPane.showMessageDialog(null,"Erro: " + e);
            System.out.println(e);
        }finally{
        // Final do tratamento do protocolo
        
        //4.1 - Fechar socket de comunicacao entre servidor/cliente
        fechaSocket(socket);
        
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        try {
            Server server = new Server();
            System.out.println("Aguardando conexao.....");
            server.criarServerSocket(5555);
            Socket socket = server.esperaConexao();// Protocolo que vai gerenciar receber e enviar
            System.out.println("Cliente conectado......");
            server.trataConexão(socket);
            System.out.println("Cliente finalizado.....");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            System.out.println("Erro: " + e);
        }
      
    }
    
}
