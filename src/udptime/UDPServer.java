/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udptime;

/**
 *
 * @author lbren
 */

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Date;

public class UDPServer{

	public static void main(String[] args) {
		Server srv = new Server(2000);
            while(true){
                srv.leggi();
                srv.scrivi();
                srv.chiusura();
            }
	}

}