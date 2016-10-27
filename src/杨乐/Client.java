package 杨乐; 
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class Client extends JFrame implements Runnable {
	JTextArea textArea;
	private int port;
	private String name;
	private String ip;
	private JPanel contentPane;
	private BufferedReader reader;
	private PrintWriter writer;
	private Socket socket;
	private JTextField textField;
	/**
	 * Launch the application.
	 */
	
	

	/**
	 * Create the frame.
	 */
	public Client(int port,String ip,String name) {
		this.port=port;
		this.ip=ip;
		this.name=name;
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("\u53D1\u9001");
		btnNewButton.setBounds(346, 203, 93, 23);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(39, 10, 385, 185);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				writer.println(name+":"+textField.getText());
				writer.flush();
			}
		});
		textField.setBounds(39, 204, 297, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				writer.println(name+":"+textField.getText());
				writer.flush();
			}
		});
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				textArea.append(reader.readLine()+"\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void getSocket(){
		try {
			textArea.append("正在连接"+"\n");
			socket=new Socket(ip, port);
			textArea.append("连接成功"+"\n");
			reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer=new PrintWriter(socket.getOutputStream());
			new Thread(this).start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
