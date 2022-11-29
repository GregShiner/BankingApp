import java.awt.*;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
import javax.swing.*;  
  
public class BankingApp extends JFrame   
{  
  
	private int currCard = 1;     
	private CardLayout cObjl; 
	private JTextField username;
	private JTextField password;
	  
	public BankingApp()  
	{    
		setTitle("Banking App Simulator");  
		setSize(700, 500);   
		JPanel cPanel = new JPanel();   
		cObjl = new CardLayout();   
		cPanel.setLayout(cObjl);  
		
		JPanel jPanel1 = new JPanel();  
		JPanel jPanel2 = new JPanel();   
		JPanel jPanel3 = new JPanel();    
		JPanel jPanel4 = new JPanel();    
		JPanel jPanel5 = new JPanel();
		
		JLabel jLabel1 = new JLabel("Login Screen");    
		JLabel jLabel2 = new JLabel("Main Menu");    
		JLabel jLabel3 = new JLabel("Savings Screen");   
		JLabel jLabel4 = new JLabel("Checking Screen");    
		JLabel jLabel5 = new JLabel("Transfer Screen");    
		
		username = new JTextField(15);
		password = new JTextField(15);
		jPanel1.add(username);
		jPanel1.add(password);
		
		jPanel1.add(jLabel1);   
		jPanel2.add(jLabel2);  
		jPanel3.add(jLabel3);    
		jPanel4.add(jLabel4);  
		jPanel5.add(jLabel5); 
	
		cPanel.add(jPanel1, "1");    
		cPanel.add(jPanel2, "2");  
		cPanel.add(jPanel3, "3");   
		cPanel.add(jPanel4, "4");  
		cPanel.add(jPanel5, "5");  
	 
		JPanel btnPanel = new JPanel();  
		JButton savingsButton = new JButton("Savings Account");  
		JButton checkingButton = new JButton("Checking Account");  
		JButton transferButton = new JButton("Transfer");  
		JButton exitButton = new JButton("Exit");  
		
		btnPanel.add(savingsButton);  
		btnPanel.add(checkingButton);  
		btnPanel.add(transferButton);   
		btnPanel.add(exitButton);  
	
	
		savingsButton.addActionListener(new ActionListener()  
			{  
				public void actionPerformed(ActionEvent ae)  
				{   
					if (currCard != 3)   
					{  		  
						currCard = 3;  
						cObjl.show(cPanel, "" + (currCard));  
					}  
				}   
			}
		);  
	  
	
		exitButton.addActionListener(new ActionListener()  
			{  
				public void actionPerformed(ActionEvent ae)  
				{   
					System.exit(0);
				}   
			}
		);  
	  
	
		checkingButton.addActionListener(new ActionListener()  
			{  
			public void actionPerformed(ActionEvent ae)  
			{   
				if (currCard != 4)   
				{  		  
					currCard = 4;  
					cObjl.show(cPanel, "" + (currCard));  
				}  
			}  
			}
		);  
	  
		transferButton.addActionListener(new ActionListener()  
			{  
				public void actionPerformed(ActionEvent ae)  
				{   
					if (currCard != 5)   
					{  		  
						currCard = 5;  
						cObjl.show(cPanel, "" + (currCard));  
					}  
				}  
			}
		);  
	  
		getContentPane().add(cPanel, BorderLayout.NORTH);  
		getContentPane().add(btnPanel, BorderLayout.SOUTH);  
	}  
	  
	// main method  
		public static void main(String argvs[])  
		{  
			BankingApp cll = new BankingApp();  
			cll.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
			cll.setVisible(true);  
		}  
}