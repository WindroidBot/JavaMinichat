package minichat;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class chat extends JFrame implements ActionListener,Runnable{

	private JPanel contentPane;
	private JTextField textField_Send;
	private JButton btnNewButton_Send;
	private JButton btnNewButton_Clean;
	private JLabel lblNewLabel_Namedisplay;
	private JLabel lblNewLabel_Numdisplay;
	private JTextArea textArea;
	
	private BufferedReader reader;
	private PrintWriter writer;
	private Socket socket;
	private String getLocal_Name;
	
	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					chat frame = new chat();
					frame.setVisible(true);
					//frame.getSocket();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public chat(BufferedReader reader,PrintWriter writer,String Local_Name) {
		
		this.reader = reader;
		this.writer = writer;
					
		setTitle("\u8D85\u4FE1\uFF01\u6BD4\u5FAE\u4FE1\u4E0D\u77E5\u9053\u9AD8\u5230\u54EA\u91CC\u53BB\u4E86\uFF01");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_Name = new JLabel("\u6211\u7684\u6635\u79F0\uFF1A");
		lblNewLabel_Name.setBounds(10, 22, 72, 15);
		contentPane.add(lblNewLabel_Name);
		
		lblNewLabel_Namedisplay = new JLabel(Local_Name);
		lblNewLabel_Namedisplay.setBounds(79, 22, 54, 15);
		contentPane.add(lblNewLabel_Namedisplay);
		
		JLabel lblNewLabel_Num = new JLabel("\u5F53\u524D\u5728\u7EBF\u4EBA\u6570\uFF1A");
		lblNewLabel_Num.setBounds(212, 22, 93, 15);
		contentPane.add(lblNewLabel_Num);
		
		lblNewLabel_Numdisplay = new JLabel("NULL");
		lblNewLabel_Numdisplay.setBounds(315, 22, 54, 15);
		contentPane.add(lblNewLabel_Numdisplay);
		
		textField_Send = new JTextField();
		textField_Send.setBounds(54, 210, 211, 21);
		contentPane.add(textField_Send);
		textField_Send.setColumns(10);
		
		btnNewButton_Send = new JButton("\u53D1\u9001");
		btnNewButton_Send.setBounds(293, 194, 93, 23);
		contentPane.add(btnNewButton_Send);
		
		btnNewButton_Clean = new JButton("\u6E05\u9664");
		btnNewButton_Clean.setBounds(293, 228, 93, 23);
		contentPane.add(btnNewButton_Clean);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 47, 404, 140);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);
		
		//关闭窗口事件
		addWindowListener(new WindowAdapter() {  	  		  
			public void windowClosing(WindowEvent e) {  
			super.windowClosing(e);  
			//加入动作  
			//System.out.println("点击了关闭！");	
			try {
				reader.close();
				System.out.println("关闭了reader");	
				writer.close();
				System.out.println("关闭了writer");	
				socket.close();
				System.out.println("关闭了writer");	
			} catch (IOException e1) {
				e1.printStackTrace();
				}
			}  			  
		});
		
		//注册监听器
		btnNewButton_Send.addActionListener(this);
		btnNewButton_Clean.addActionListener(this);
	}
	//事件
	public void actionPerformed(ActionEvent arg0) {
		//发送按钮事件
		if (arg0.getSource() == btnNewButton_Send ){
			writer.println(lblNewLabel_Namedisplay.getText()+":"+textField_Send.getText());
			textField_Send.setText(null);
		}
		//清空按钮事件
		if (arg0.getSource() == btnNewButton_Clean ){
			textField_Send.setText(null);
		}
	}
	
	//主线程
	public void run() {
		while(true) {
			try {
				textArea.append(reader.readLine()+"\n");
				textArea.setCaretPosition(textArea.getText().length());//滚动条自动滚动
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	
	
	
	
	
	//测试代码！！！！！
	//创建套接字
	/*
	private void getSocket() {
		textArea.append("尝试连接服务器");
		try {
			socket = new Socket("127.0.0.1",8891);//创建客户端套接字
			textArea.append("连接成功！开始谈笑风生"+"\n");
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new PrintWriter(socket.getOutputStream(),true);
			new Thread(this).start();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	*/
}
