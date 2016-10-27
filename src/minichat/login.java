//login.java
//登录界面
package minichat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;



@SuppressWarnings("serial")
public class login extends JFrame implements ActionListener, Runnable{

	private JPanel contentPane;
	private JTextField textField_ServerIP;
	private JTextField textField_ServerPort;
	private JTextField textField_Name;
	
	private JButton btnNewButton_ok;
	private JLabel lblNewLabel_ServerIP;
	
	//基本属性
	String LocalHost_ip;
	String Server_ip;
	int Server_port;
	String Local_Name;
	
	//连接属性
	Socket socket = null;
	BufferedReader sockIn;
	PrintWriter sockOut;
	public BufferedReader reader;
	public PrintWriter writer;
	
	//基本方法
	
	//获取本机IP
	public String getLocalHost_ip() {
		try {
			LocalHost_ip = InetAddress.getLocalHost().getHostAddress().toString();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return LocalHost_ip;
	}
	//获取服务器IP
	public String getServer_ip() {
		Server_ip = textField_ServerIP.getText();
		return Server_ip;
	}
	//获取服务器端口
	public int getServer_port() {
		Server_port = Integer.parseInt(textField_ServerPort.getText());
		return Server_port;
	}
	//获取用户昵称
	public String getLocal_Name() {
		Local_Name = textField_Name.getText();
		return Local_Name;
	}
	
	//创建套接字
	public void getSocket() {
		//textArea.append("尝试连接服务器");
		try {
			socket = new Socket(getServer_ip(),getServer_port());//创建客户端套接字
			Local_Name = getLocal_Name();
			JOptionPane.showMessageDialog(null, "登录成功！欢迎进入膜法的世界！", "登录成功",JOptionPane.INFORMATION_MESSAGE);	
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new PrintWriter(socket.getOutputStream(),true);
			new Thread(this).start();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public login() {
		setTitle("\u8D85\u4FE1\uFF01\uFF01\uFF01");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel_ServerIP = new JLabel("\u670D\u52A1\u5668IP\uFF1A");
		lblNewLabel_ServerIP.setBounds(41, 43, 74, 15);
		contentPane.add(lblNewLabel_ServerIP);
		
		JLabel lblNewLabel_ServerPort = new JLabel("\u670D\u52A1\u5668\u7AEF\u53E3\uFF1A");
		lblNewLabel_ServerPort.setBounds(41, 78, 93, 15);
		contentPane.add(lblNewLabel_ServerPort);
		
		JLabel lblNewLabel_Name = new JLabel("\u6635\u79F0\uFF1A");
		lblNewLabel_Name.setBounds(41, 121, 54, 15);
		contentPane.add(lblNewLabel_Name);
		
		textField_ServerIP = new JTextField();
		textField_ServerIP.setBounds(215, 40, 112, 21);
		contentPane.add(textField_ServerIP);
		textField_ServerIP.setColumns(10);
		
		textField_ServerPort = new JTextField();
		textField_ServerPort.setBounds(215, 75, 112, 21);
		contentPane.add(textField_ServerPort);
		textField_ServerPort.setColumns(10);
		
		textField_Name = new JTextField();
		textField_Name.setBounds(215, 118, 112, 21);
		contentPane.add(textField_Name);
		textField_Name.setColumns(10);
		
		btnNewButton_ok = new JButton("\u5F00\u59CB\u8C08\u7B11\u98CE\u751F");
		btnNewButton_ok.setBounds(256, 209, 125, 23);
		contentPane.add(btnNewButton_ok);
		
		//注册监听器
		btnNewButton_ok.addActionListener(this);
	}
	
	//【登录】按钮事件
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnNewButton_ok ){
			getSocket();			
		}
	}
	@Override
	public void run() {
		try {
			chat frame = new chat(reader,writer,Local_Name);
			new Thread(frame).start();
			frame.setVisible(true);
			setVisible(false);
			//frame.getSocket();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	
}
