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
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lbren
 */
public class Client {
    int port = 2000;
    InetAddress serverAddress ;
    DatagramPacket inpacket;
    DatagramPacket outpacket;
    DatagramSocket dsocket;
    byte[] buffer;
    String messaggio = "Richiesta data e ora";
    String response;
    
    public Client(int port){
        try {
            dsocket=new DatagramSocket(this.port);
        } catch (SocketException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void scrittura() throws UnknownHostException{
        serverAddress = InetAddress.getLocalHost();		
        System.out.println("Indirizzo del server trovato!");
        outpacket = new DatagramPacket(messaggio.getBytes(), messaggio.length(), serverAddress, port);
        try {
            dsocket.send(outpacket);
            System.out.println("Messaggio inviato"); 
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void ricezione(){
        buffer = new byte[256];
        inpacket = new DatagramPacket(buffer, buffer.length);
        try {
            dsocket.receive(inpacket);
            response = new String(inpacket.getData(), 0, inpacket.getLength());
            System.out.println("Data e ora del server: " + response);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void chiusura(){
        dsocket.close();
        System.out.println("Chiusura della comunicazione");
    }

}
