package minichat;

import java.net.Socket;

public class DstClient {
	  public void TestClient() {
	    try {
	      Socket socket = new Socket("127.0.0.1", 8891);
	      socket.setKeepAlive(true);
	      socket.setSoTimeout(10);
	      while (true) {
	        socket.sendUrgentData(0xFF); // 发送心跳包
	        System.out.println("目前是处于链接状态！");
	        Thread.sleep(3 * 1000);//线程睡眠3秒
	      }
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	  }
	}
