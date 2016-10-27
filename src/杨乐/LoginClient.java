package —Ó¿÷; 
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginClient extends JFrame implements Runnable {
	static LoginClient frame;
	private JPanel contentPane;
	private JTextField ip;
	private JTextField port;
	private JTextField name;
	private JButton button;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new LoginClient();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginClient() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblip = new JLabel("\u670D\u52A1\u5668IP\uFF1A");
		lblip.setBounds(76, 59, 70, 15);
		contentPane.add(lblip);

		ip = new JTextField();
		ip.setText("127.0.0.1");
		ip.setBounds(156, 56, 113, 21);
		contentPane.add(ip);
		ip.setColumns(100);

		JLabel label = new JLabel("\u7AEF\u53E3\u53F7\uFF1A");
		label.setBounds(92, 105, 54, 15);
		contentPane.add(label);

		port = new JTextField();
		port.setText("7235");
		port.setToolTipText("");
		port.setBounds(152, 102, 46, 21);
		contentPane.add(port);
		port.setColumns(10);

		JLabel label_1 = new JLabel("\u6635\u79F0\uFF1A");
		label_1.setBounds(100, 141, 46, 15);
		contentPane.add(label_1);

		name = new JTextField();
		name.setText("\u5C0F\u732A");
		name.setBounds(152, 138, 66, 21);
		contentPane.add(name);
		name.setColumns(10);

		JButton login = new JButton("\u767B\u5F55");
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int port1 = Integer.parseInt(port.getText());
				String ip1 = ip.getText();
				String name1 = name.getText();
				new Client(port1, ip1, name1).getSocket();
			}
		});
		login.setBounds(115, 183, 93, 23);
		contentPane.add(login);

		button = new JButton("\u542F\u52A8\u670D\u52A1\u5668");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(frame).start();
				button.setEnabled(false);
			}
		});

		button.setBounds(161, 10, 108, 23);
		contentPane.add(button);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Server process = new Server();
		process.getServer();

	}
}
