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
		setSize(800, 300);   
		JPanel cPanel = new JPanel();   
		cObjl = new CardLayout();   
		cPanel.setLayout(cObjl);  
		
		//JPanel jPanel1 = new JPanel(new GridLayout(4, 3));  
		JPanel jPanel1 = new JPanel(new GridBagLayout()); 
		JPanel jPanel2 = new JPanel(new GridBagLayout());   
		JPanel jPanel3 = new JPanel(new GridBagLayout()); 
		JPanel jPanel4 = new JPanel(new GridBagLayout());    
		JPanel jPanel5 = new JPanel(new GridBagLayout());
		JLabel jLabel1 = new JLabel("Login Screen");    
		JLabel jLabel2 = new JLabel("Main Menu");   
		JLabel jLabel3 = new JLabel("Savings Screen");   
		JLabel jLabel4 = new JLabel("Checking Screen");    
		JLabel jLabel5 = new JLabel("Transfer Screen"); 
		
		
		// start code for login screen
		JButton loginButton = new JButton("Login");
		JLabel userLabel = new JLabel("Username");
		JLabel passLabel = new JLabel("Password");
		JPanel userPanel = new JPanel();
		JPanel passPanel = new JPanel();
		
		username = new JTextField(10);
		password = new JTextField(10);
		
		userPanel.add(userLabel);
		userPanel.add(username);
		passPanel.add(passLabel);
		passPanel.add(password);
		
		GridBagConstraints ls = new GridBagConstraints();
		ls.gridx = 2;
		ls.gridy = 0;
		ls.gridwidth = 1;
		jPanel1.add(jLabel1, ls); 
		
		ls.gridx = 0;
		ls.gridy = 1;
		ls.gridwidth = 3;
		jPanel1.add(userPanel, ls);
		
		ls.gridx = 0;
		ls.gridy = 2;
		ls.gridwidth = 3;
		jPanel1.add(passPanel, ls);
		
		ls.gridx = 0;
		ls.gridy = 3;
		ls.gridwidth = 3;
		jPanel1.add(loginButton, ls);	
		// end code for login screen
		
		
		
		// start main menu screen
		JLabel introLabel1 = new JLabel("Welcome to the Banking App Simulator! Press a button to interact with the app!");
		JLabel loginConLabel = new JLabel("Login Successful!");
		GridBagConstraints mm = new GridBagConstraints();
		
		mm.gridx = 2;
		mm.gridy = 0;
		mm.gridwidth = 3;
		jPanel2.add(jLabel2, mm); 
		mm.gridx = 2;
		mm.gridy = 1;
		mm.gridwidth = 3;
		jPanel2.add(loginConLabel, mm);
		mm.gridx = 2;
		mm.gridy = 2;
		mm.gridwidth = 3;
		jPanel2.add(introLabel1, mm);
		// end main menu screen
		
		// start savings screen
		// account info panel code
		JPanel accountInfoPanel = new JPanel(new GridLayout(5, 2, 10, 10));
		JLabel accountNumLabel = new JLabel("Account Number");
		JLabel routingNumLabel = new JLabel("Routing Number");
		JLabel IRLabel = new JLabel("Interest Rate");
		JLabel IRALabel = new JLabel("Interest Accrued over 52 weeks");
		JLabel avaliableBalLabel = new JLabel("Avaliable Balance");
		String accountNum = "6969";
		String routingNum = "420420";
		double IR = 0.5;
		double IRA = 100;
		double avaliableBal = 1000;
		JLabel accountNumL = new JLabel(String.format("%s", accountNum));
		JLabel routingNumL = new JLabel(String.format("%s", routingNum));
		JLabel IRL = new JLabel(String.format("%.2f", IR));
		JLabel IRAL = new JLabel(String.format("%.2f", IRA));
		JLabel avaliableBalL = new JLabel(String.format("%.2f", avaliableBal));
		
		accountInfoPanel.add(accountNumLabel);
		accountInfoPanel.add(accountNumL);
		accountInfoPanel.add(routingNumLabel);
		accountInfoPanel.add(routingNumL);
		accountInfoPanel.add(IRLabel);
		accountInfoPanel.add(IRL);
		accountInfoPanel.add(IRALabel);
		accountInfoPanel.add(IRAL);
		accountInfoPanel.add(avaliableBalLabel);
		accountInfoPanel.add(avaliableBalL);
		
		// recent transactions panel code
		JPanel transactionHistPanelS = new JPanel(new GridLayout(3, 3, 5, 5));
		JLabel recentTranLabelS = new JLabel("Recent Transactions");
		JLabel dateLabelS = new JLabel("Date");
		JLabel amountLabelS = new JLabel("Amount");
		String trans1S = "Transfer from Checkings to Savings";
		String trans2S = "Transfer from Savings to Checkings";
		String date1S = "10/11/22";
		String date2S = "12/5/22";
		double amount1S = 100.75;
		double amount2S = 205;
		JLabel recentTranLabel1S = new JLabel(String.format("%s", trans1S));
		JLabel dateLabel1S = new JLabel(String.format("%s", date1S));
		JLabel amountLabel1S = new JLabel(String.format("%s", amount1S));
		JLabel recentTranLabel2S = new JLabel(String.format("%s", trans2S));
		JLabel dateLabel2S = new JLabel(String.format("%s", date2S));
		JLabel amountLabel2S = new JLabel(String.format("%s", amount2S));
		
		transactionHistPanelS.add(recentTranLabelS);
		transactionHistPanelS.add(dateLabelS);
		transactionHistPanelS.add(amountLabelS);
		transactionHistPanelS.add(recentTranLabel1S);
		transactionHistPanelS.add(dateLabel1S);
		transactionHistPanelS.add(amountLabel1S);
		transactionHistPanelS.add(recentTranLabel2S);
		transactionHistPanelS.add(dateLabel2S);
		transactionHistPanelS.add(amountLabel2S);
		
		GridBagConstraints sa = new GridBagConstraints();
		sa.gridx = 2;
		sa.gridy = 0;
		sa.gridwidth = 1;
		jPanel3.add(jLabel3, sa); 
		
		sa.gridx = 0;
		sa.gridy = 1;
		sa.gridwidth = 3;
		jPanel3.add(accountInfoPanel, sa);
		
		sa.gridx = 0;
		sa.gridy = 2;
		sa.gridwidth = 3;
		jPanel3.add(transactionHistPanelS, sa);
		// end savings screen
		
		// start checking screen
		// transaction history code
		JPanel transactionHistPanelC = new JPanel(new GridLayout(3, 3, 20, 5));
		JLabel recentTranLabelC = new JLabel("Recent Transactions");
		JLabel dateLabelC = new JLabel("Date");
		JLabel amountLabelC = new JLabel("Amount");
		String trans1C = "Transfer from Checkings to Savings";
		String trans2C = "Transfer from Savings to Checkings";
		String date1C = "9/21/22";
		String date2C = "12/10/22";
		double amount1C = 570.75;
		double amount2C = 295;
		JLabel recentTranLabel1C = new JLabel(String.format("%s", trans1C));
		JLabel dateLabel1C = new JLabel(String.format("%s", date1C));
		JLabel amountLabel1C = new JLabel(String.format("%s", amount1C));
		JLabel recentTranLabel2C = new JLabel(String.format("%s", trans2C));
		JLabel dateLabel2C = new JLabel(String.format("%s", date2C));
		JLabel amountLabel2C = new JLabel(String.format("%s", amount2C));
		
		transactionHistPanelC.add(recentTranLabelC);
		transactionHistPanelC.add(dateLabelC);
		transactionHistPanelC.add(amountLabelC);
		transactionHistPanelC.add(recentTranLabel1C);
		transactionHistPanelC.add(dateLabel1C);
		transactionHistPanelC.add(amountLabel1C);
		transactionHistPanelC.add(recentTranLabel2C);
		transactionHistPanelC.add(dateLabel2C);
		transactionHistPanelC.add(amountLabel2C);
		
		// account balance code
		JPanel accountBalPanelC = new JPanel(new GridLayout(1, 2, 20, 5));
		double accountBalC = 2076.88;
		JLabel accountBalanceC = new JLabel(String.format("%s", accountBalC));
		JLabel accountBalLabelC = new JLabel("Account Balance");
		
		accountBalPanelC.add(accountBalLabelC);
		accountBalPanelC.add(accountBalanceC);
		
		GridBagConstraints cs = new GridBagConstraints();
		
		cs.gridx = 2;
		cs.gridy = 0;
		cs.gridwidth = 3;
		jPanel4.add(jLabel4, cs);   
		
		cs.gridx = 2;
		cs.gridy = 1;
		cs.gridwidth = 3;
		jPanel4.add(transactionHistPanelC, cs);
		
		cs.gridx = 2;
		cs.gridy = 2;
		cs.gridwidth = 3;
		jPanel4.add(accountBalPanelC, cs);
		// end checking screen
		
		// start transfer screen
		JPanel transferPanel = new JPanel();
		JLabel transferLabel1 = new JLabel("Transfer $");
		JTextField transferTextField = new JTextField("", 10);
		JLabel transferLabel2 = new JLabel("from");
		JButton transferMoneyButton = new JButton("Transfer Money");
		String[] transferOptions = {"Checking to Saving", "Saving to Checking"};
		JComboBox transferList = new JComboBox(transferOptions);
		transferList.setSelectedIndex(1);
		
		transferPanel.add(transferLabel1);
		transferPanel.add(transferTextField);
		transferPanel.add(transferLabel2);
		transferPanel.add(transferList);
		transferPanel.add(transferMoneyButton);
		
		
		GridBagConstraints ts = new GridBagConstraints();
		
		ts.gridx = 2;
		ts.gridy = 0;
		ts.gridwidth = 3;
		jPanel5.add(jLabel5, ts); 
		
		ts.gridx = 2;
		ts.gridy = 2;
		ts.gridwidth = 3;
		jPanel5.add(transferPanel, ts);
		// end transfer screen
		
		
		
		// adds all panels to card
		cPanel.add(jPanel1, "1");    
		cPanel.add(jPanel2, "2");  
		cPanel.add(jPanel3, "3");   
		cPanel.add(jPanel4, "4");  
		cPanel.add(jPanel5, "5");  
	 
		// adds all buttons to card
		JPanel btnPanel = new JPanel();  
		JButton savingsButton = new JButton("Savings Account");  
		JButton checkingButton = new JButton("Checking Account");  
		JButton transferButton = new JButton("Transfer");  
		JButton exitButton = new JButton("Exit");  
		
		btnPanel.add(savingsButton);  
		btnPanel.add(checkingButton);  
		btnPanel.add(transferButton);   
		btnPanel.add(exitButton);  
	
		
		// button listeners
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
		
		transferMoneyButton.addActionListener(new ActionListener()  
		{  
			public void actionPerformed(ActionEvent ae)  
			{   
				// ******needs code to take money value
			}  
		}
	);  
	  
		
		// shows card panel and buttons
		getContentPane().add(cPanel, BorderLayout.CENTER);  
		getContentPane().add(btnPanel, BorderLayout.SOUTH);  
		
		btnPanel.setVisible(false);
		
		loginButton.addActionListener(new ActionListener()  
		{  
			public void actionPerformed(ActionEvent ae)  
			{   
				// **** needs code to validate / create account
				currCard = 2;
				cObjl.show(cPanel, "" + (currCard));
				btnPanel.setVisible(true);
			}  
		}
	);  
	}  
	  
	// main method  
		public static void main(String argvs[])  
		{  
			BankingApp cll = new BankingApp();  
			cll.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
			cll.setVisible(true);  
		}  
}