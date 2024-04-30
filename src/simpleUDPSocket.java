import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class simpleUDPSocket
{
    public static void main(String[] args) {
        try {
            // Sunucu adresini ve port numarasını belirle
            InetAddress serverAddress = InetAddress.getByName("127.0.0.1"); // Sunucu IP adresi
            int serverPort = 12345; // Sunucu port numarası

            // İstemci soketini oluştur
            DatagramSocket clientSocket = new DatagramSocket();

            // Gönderilecek veriyi belirle
            String message = "Merhaba, UDP Sunucusu!";
            byte[] sendData = message.getBytes();

            // Sunucuya paket oluştur ve gönder
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
            clientSocket.send(sendPacket);

            // Sunucudan gelen cevabı al
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);

            // Gelen veriyi al ve yazdır
            String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Sunucudan gelen cevap: " + receivedMessage);

            // İstemci soketini kapat
            clientSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
