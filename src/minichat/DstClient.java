package minichat;

import java.net.Socket;

public class DstClient {
	  public void TestClient() {
	    try {
	      Socket socket = new Socket("127.0.0.1", 8891);
	      socket.setKeepAlive(true);
	      socket.setSoTimeout(10);
	      while (true) {
	        socket.sendUrgentData(0xFF); // ����������
	        System.out.println("Ŀǰ�Ǵ�������״̬��");
	        Thread.sleep(3 * 1000);//�߳�˯��3��
	      }
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	  }
	}
