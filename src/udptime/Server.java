/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package udptime;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lbren
 */
public class Server {
    int port = 2000;
    Date d;
    DatagramPacket inpacket;
    DatagramPacket outpacket;
    DatagramSocket dsocket;
    byte[] buffer;
    String messaggioIn, messaggioOut;
    
    public Server(int port){
        try {
            dsocket = new DatagramSocket(this.port);
            System.out.println("server in ascolto");
        } catch (SocketException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    public void leggi(){
        buffer = new byte[256];
        inpacket = new DatagramPacket(buffer, buffer.length);
        try {
            dsocket.receive(inpacket);
            InetAddress clientAddress = inpacket.getAddress();
            int clientPort = inpacket.getPort();
            messaggioIn = new String(inpacket.getData(),0, inpacket.getLength());
            System.out.println("Sono il client" + clientAddress + ":" + clientPort + ">" + messaggioIn);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void scrivi(){
        d = new Date();
        //si crea il messaggio del server in uscita associandolo alla connessione aperta con il client
        try {
            //si invia il messaggio al client
            messaggioOut = d.toString();
            InetAddress clientAddress = inpacket.getAddress();
            int clientPort = inpacket.getPort();
            //si crea un datagramma UDP in cui trasportare il messaggio di lunghezza length
            outpacket = new DatagramPacket(messaggioOut.getBytes(), messaggioOut.length(), clientAddress, clientPort);
            dsocket.send(outpacket);
            System.out.println("Risposta inviata!");
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void chiusura(){
        dsocket.close();
        System.out.println("Chiusura");
    }
}
