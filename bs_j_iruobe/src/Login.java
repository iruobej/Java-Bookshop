import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JComboBox SelectedAccount;
	private ArrayList<Book> bookList;
	private String givenAccount;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		bookList = new ArrayList<Book>();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Select an account to login:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(128, 38, 230, 50);
		contentPane.add(lblNewLabel);
		
		SelectedAccount = new JComboBox();
		SelectedAccount.setBounds(128, 136, 181, 21);
		contentPane.add(SelectedAccount);
		
		populateComboBox();
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] option = SelectedAccount.getSelectedItem().toString().split("-");
				
				givenAccount = option[1].trim();
				
				
				if (givenAccount.equals("admin")) {
					//System.out.println("Here 1");
					AdminAccount aAccount = new AdminAccount();
					aAccount.setVisible(true);
				} else {
					//System.out.print(givenAccount);
					CustomerAccount cAccount = new CustomerAccount();
					cAccount.setVisible(true);
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton.setBounds(181, 204, 85, 21);
		contentPane.add(btnNewButton);
		
		
	}
	
	private void populateComboBox() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("UserAccounts.txt"));
			String line;
			while ((line = reader.readLine()) != null) {	
				String[] account = line.split(", ");
				if (account[7].trim().equals("admin")) {
					Admin admin = new Admin(Integer.parseInt(account[0]), account[1], account[2], Integer.parseInt(account[3]), account[4], account[5], account[7]);
					SelectedAccount.addItem(admin.getUsername()+" - "+admin.getRole());
				} else if (account[7].trim().equals("customer")){
					Customer customer = new Customer(Integer.parseInt(account[0]), account[1], account[2], Integer.parseInt(account[3]), account[4], account[5], Double.parseDouble(account[6]), account[7]);
					SelectedAccount.addItem(customer.getUsername()+" - "+customer.getRole());
				}
				givenAccount = account[7].trim();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
