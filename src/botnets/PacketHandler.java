package botnets;

import jpcap.PacketReceiver;
import jpcap.packet.Packet;
import jpcap.packet.UDPPacket;

class PacketHandler implements PacketReceiver {
	//this method is called every time Jpcap captures a packet
	public void receivePacket(Packet packet) {		
		if(packet.header[23] == 17) { //udp packets (IP Packet protocol 17)
			UDPPacket p = (UDPPacket) packet;
			if(p.dst_port == 53) { //dns outbound queries only
				System.out.println(new String(p.data)); //print data of dns query
				//System.out.println(p);
			}
		}
	}
	
	//Conversation from byte[] to string
	String convert(byte[] data) {
	    StringBuilder sb = new StringBuilder(data.length);
	    for (int i = 0; i < data.length; ++ i) {
	        if (data[i] < 0) throw new IllegalArgumentException();
	        else if (data[i] < 32) sb.append('.');
	        else sb.append((char) data[i]);
	    }
	    return sb.toString();
	}
}