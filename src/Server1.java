//Server1.java
import java.net.*;
import java.io.*;
public class Server1{
	public static void main(String argv[]) {
		ServerSocket serverSocket = null;
		Socket socket = null;
		BufferedReader sockIn;
		PrintWriter socketOut;
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		try {
			//创建ServerSocket
			serverSocket = new ServerSocket(8891);
			System.out.println("服务器监听端口：8891");
			socket = serverSocket.accept();//调用阻塞，直到连接建立，并返回socket对象
			
			if(socket == null) {
				System.out.println("Socket为空");
				System.exit(1);
			}
			System.out.println("接收到来自"+socket.getInetAddress().getHostAddress()+"连接请求");
			//得到输入流
			sockIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			//向客户端输出消息
			socketOut = new PrintWriter(socket.getOutputStream());
			socketOut.println("服务器已连接！");
			socketOut.flush();
			//接收客户端的消息并输出
			String s = sockIn.readLine();
			System.out.println("接收到来自客户端的消息："+s);
			//关闭输入输出流
			socketOut.close();
			sockIn.close();
			socket.close();
			serverSocket.close();
		}catch(Exception e) {
			System.out.println(e.toString());
		}
		System.out.println("服务器关闭");
	}
}
