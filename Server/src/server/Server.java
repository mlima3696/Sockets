/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
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
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        try {
            Server server = new Server();
            server.criarServerSocket(5555);
            Socket socket = server.esperaConexao();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            System.out.println("Erro: " + e);
        }
      
    }
    
}
