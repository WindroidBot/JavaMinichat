//Client1.java
import java.net.Socket;
import java.io.*;
public class Client1 {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Socket socket = null;
		BufferedReader sockIn;
		PrintWriter sockOut;
		try {
			//创建一个连接服务器的socket，IP 127.0.0.1 Port 8890
			socket = new Socket("127.0.0.1",8891);
			if(socket == null) {
				System.out.println("Socket is null,connect Error!");
				System.exit(1);
			}
			//使用getInputStream()和getOutputStream()得到输入输出流
			sockIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			sockOut = new PrintWriter(socket.getOutputStream());
			//向服务器输出信息
			sockOut.println("Hello,I'm Client!");
			sockOut.flush();
			//从服务器读取信息
			String s = sockIn.readLine();
			System.out.println("Client received:" + s);
			//关闭连接
			sockOut.close();
			sockIn.close();
		}catch(Exception e) {
			System.out.println(e.toString());
		}
		System.out.println("Client exit!");
	}
}
