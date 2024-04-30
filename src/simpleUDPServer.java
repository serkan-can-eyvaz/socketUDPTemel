import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.SocketException;

public class simpleUDPServer
{
    public static void main(String[] args) {
        try {
            // Sunucu soketini oluştur
            DatagramSocket serverSocket = new DatagramSocket(12345); // Port numarası: 12345

            // Gelen veri için bir tampon oluştur
            byte[] receiveData = new byte[1024];

            // Sunucu sürekli çalışsın
            while (true) {
                // Gelen paketi al
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);

                // Gelen veriyi al ve yazdır
                String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Gelen veri: " + receivedMessage);

                // İstemciye cevap vermek için paket oluştur
                byte[] sendData = receivedMessage.toUpperCase().getBytes(); // Alınan veriyi büyük harfe çevir
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, receivePacket.getAddress(), receivePacket.getPort());

                // Cevap paketini gönder
                serverSocket.send(sendPacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
