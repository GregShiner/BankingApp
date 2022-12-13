import java.awt.*;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;  
  
public class BankingApp extends JFrame   
{  
  
	private JPanel jPanel1;
	private JPanel jPanel2;  
	private JPanel jPanel3;
	private JPanel jPanel4;   
	private JPanel jPanel5;
	private JLabel jLabel1;   
	private JLabel jLabel2; 
	private JLabel jLabel3; 
	private JLabel jLabel4;    
	private JLabel jLabel5;
	private int currCard = 1;     
	private CardLayout cObjl; 
	private JTextField username;
	private JTextField password;
	private Customer customer;
	private JPanel accountInfoPanel;
	private JLabel accountNumLabel;
	private JLabel routingNumLabel;
	private JLabel IRLabel;
	private JLabel IRALabel;
	private JLabel avaliableBalLabel;
	private int accountNum;
	private int routingNum;
	private double IR;
	private double IRA;
	private double availableBal;
	private JLabel accountNumL;
	private JLabel routingNumL;
	private JLabel IRL;
	private JLabel IRAL;
	private JLabel availableBalL;
	private JPanel transactionHistPanelS;
	private JLabel recentTranLabelS;
	private JLabel dateLabelS;
	private JLabel amountLabelS;
	private String trans1S;
	private String trans2S;
	private String date1S;
	private String date2S;
	private double amount1S;
	private double amount2S;
	private JLabel recentTranLabel1S;
	private JLabel dateLabel1S;
	private JLabel amountLabel1S;
	private JLabel recentTranLabel2S;
	private JLabel dateLabel2S;
	private JLabel amountLabel2S;
	private JPanel transactionHistPanelC;
	private JLabel recentTranLabelC;
	private JLabel dateLabelC;
	private JLabel amountLabelC;
	private String trans1C;
	private	String trans2C;
	private String date1C;
	private String date2C;
	double amount1C;
	double amount2C;
	private JLabel recentTranLabel1C;
	private JLabel dateLabel1C;
	private JLabel amountLabel1C;
	private JLabel recentTranLabel2C;
	private JLabel dateLabel2C;
	private JLabel amountLabel2C;
	private JPanel accountBalPanelC;
	private double accountBalC;
	private JLabel accountBalanceC;
	private JLabel accountBalLabelC;
	private JPanel cPanel;
	private JLabel introLabel1;
	private JLabel loginConLabel;
	private GridBagConstraints mm;
	private JPanel btnPanel;
	private JButton savingsButton; 
	private JButton checkingButton;
	private JButton transferButton;
	private JButton exitButton;
	GridBagConstraints sa;
	JPanel transferPanel;
	JLabel transferLabel1;
	JTextField transferTextField;
	JLabel transferLabel2;
	JButton transferMoneyButton;
	String[] transferOptions = {"Checking to Saving", "Saving to Checking"};
	JComboBox<String> transferList;
	GridBagConstraints ts;
	GridBagConstraints ls;
	JLabel loginErrorMsg;

	public BankingApp()  
	{    
		setTitle("Banking App Simulator");  
		setSize(800, 300);   
		cPanel = new JPanel();   
		cObjl = new CardLayout();   
		cPanel.setLayout(cObjl);  
		
		//JPanel jPanel1 = new JPanel(new GridLayout(4, 3));  
		jPanel1 = new JPanel(new GridBagLayout()); 
		jPanel2 = new JPanel(new GridBagLayout());   
		jPanel3 = new JPanel(new GridBagLayout()); 
		jPanel4 = new JPanel(new GridBagLayout());    
		jPanel5 = new JPanel(new GridBagLayout());
		jLabel1 = new JLabel("Login Screen");    
		jLabel2 = new JLabel("Main Menu");   
		jLabel3 = new JLabel("Savings Screen");   
		jLabel4 = new JLabel("Checking Screen");    
		jLabel5 = new JLabel("Transfer Screen"); 
		
		
		// start code for login screen
		JButton loginButton = new JButton("Login");
		JLabel userLabel = new JLabel("Username");
		JLabel passLabel = new JLabel("Password");
		JPanel userPanel = new JPanel();
		JPanel passPanel = new JPanel();
		loginErrorMsg = new JLabel("Username or Password Invalid!");
		loginErrorMsg.setForeground(Color.RED);
		
		username = new JTextField(10);
		password = new JTextField(10);
		
		userPanel.add(userLabel);
		userPanel.add(username);
		passPanel.add(passLabel);
		passPanel.add(password);
		
		ls = new GridBagConstraints();
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
		
		ls.gridx = 0;
		ls.gridy = 4;
		ls.gridwidth = 3;
		jPanel1.add(loginErrorMsg, ls);	
		loginErrorMsg.setVisible(false);
		
		// end code for login screen
		
		// adds all panels to card
		cPanel.add(jPanel1, "1");    
	
	
	  
		
		// shows card panel and buttons
		getContentPane().add(cPanel, BorderLayout.CENTER);   
		
		
		loginButton.addActionListener(new ActionListener()  
		{  
			public void actionPerformed(ActionEvent ae)  
			{   
				while (true) {

					try {
						customer = new Customer(username.getText(), password.getText());
						break;
					} catch (IllegalArgumentException | IOException e) {
						loginErrorMsg.setVisible(true);
						e.printStackTrace();
					}
				}
				populatePanels();
				update();
				currCard = 2;
				cObjl.show(cPanel, "" + (currCard));
			}  
		}
	);  
	}  
	public void populatePanels() {
		// start main menu screen
				introLabel1 = new JLabel("Welcome to the Banking App Simulator! Press a button to interact with the app!");
				loginConLabel = new JLabel("Login Successful!");
				mm = new GridBagConstraints();
				
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
				//savings
				accountInfoPanel = new JPanel(new GridLayout(5, 2, 10, 10));
				accountNumLabel = new JLabel("Account Number");
				routingNumLabel = new JLabel("Routing Number");
				IRLabel = new JLabel("Interest Rate");
				IRALabel = new JLabel("Interest Accrued over 52 weeks");
				avaliableBalLabel = new JLabel("Avaliable Balance");
				accountNum = customer.getAccountNumber();
				routingNum = customer.getRoutingNumber();
				IR = customer.getInterestRate();
				IRA = customer.getInterest52Weeks();
				availableBal = customer.getSavingsBalance();
				accountNumL = new JLabel(String.format("%s", accountNum));
				routingNumL = new JLabel(String.format("%s", routingNum));
				IRL = new JLabel(String.format("%.2f", IR));
				IRAL = new JLabel(String.format("%.2f", IRA));
				availableBalL = new JLabel(String.format("%.2f", availableBal));
				// account info panel code
	
				accountInfoPanel.add(accountNumLabel);
				accountInfoPanel.add(accountNumL);
				accountInfoPanel.add(routingNumLabel);
				accountInfoPanel.add(routingNumL);
				accountInfoPanel.add(IRLabel);
				accountInfoPanel.add(IRL);
				accountInfoPanel.add(IRALabel);
				accountInfoPanel.add(IRAL);
				accountInfoPanel.add(avaliableBalLabel);
				accountInfoPanel.add(availableBalL);
				
				// recent transactions panel code
				//savings recent trans
				transactionHistPanelS = new JPanel(new GridLayout(3, 3, 5, 5));
				recentTranLabelS = new JLabel("Recent Transactions");
				dateLabelS = new JLabel("Date");
				amountLabelS = new JLabel("Amount");
				trans1S = "Transfer from Checkings to Savings"; // test
				trans2S = "Transfer from Savings to Checkings"; // test
				date1S = "10/11/22"; // test
				date2S = "12/5/22"; // test
				amount1S = 100.75; // test
				amount2S = 205; // test
				recentTranLabel1S = new JLabel(String.format("%s", trans1S));
				dateLabel1S = new JLabel(String.format("%s", date1S));
				amountLabel1S = new JLabel(String.format("%s", amount1S));
				recentTranLabel2S = new JLabel(String.format("%s", trans2S));
				dateLabel2S = new JLabel(String.format("%s", date2S));
				amountLabel2S = new JLabel(String.format("%s", amount2S));
				
				transactionHistPanelS.add(recentTranLabelS);
				transactionHistPanelS.add(dateLabelS);
				transactionHistPanelS.add(amountLabelS);
				transactionHistPanelS.add(recentTranLabel1S);
				transactionHistPanelS.add(dateLabel1S);
				transactionHistPanelS.add(amountLabel1S);
				transactionHistPanelS.add(recentTranLabel2S);
				transactionHistPanelS.add(dateLabel2S);
				transactionHistPanelS.add(amountLabel2S);
				
				sa = new GridBagConstraints();
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
				//checking recent trans
				transactionHistPanelC = new JPanel(new GridLayout(3, 3, 20, 5));
				recentTranLabelC = new JLabel("Recent Transactions");
				dateLabelC = new JLabel("Date");
				amountLabelC = new JLabel("Amount");
				trans1C = "Transfer from Checkings to Savings"; // test
				trans2C = "Transfer from Savings to Checkings"; // test
				date1C = "9/21/22"; // test
				date2C = "12/10/22"; // test
				amount1C = 570.75; // test
				amount2C = 295; // test
				recentTranLabel1C = new JLabel(String.format("%s", trans1C));
				dateLabel1C = new JLabel(String.format("%s", date1C));
				amountLabel1C = new JLabel(String.format("%s", amount1C));
				recentTranLabel2C = new JLabel(String.format("%s", trans2C));
				dateLabel2C = new JLabel(String.format("%s", date2C));
				amountLabel2C = new JLabel(String.format("%s", amount2C));
				// transaction history code
				transactionHistPanelC.add(recentTranLabelC);
				transactionHistPanelC.add(dateLabelC);
				transactionHistPanelC.add(amountLabelC);
				transactionHistPanelC.add(recentTranLabel1C);
				transactionHistPanelC.add(dateLabel1C);
				transactionHistPanelC.add(amountLabel1C);
				transactionHistPanelC.add(recentTranLabel2C);
				transactionHistPanelC.add(dateLabel2C);
				transactionHistPanelC.add(amountLabel2C);
				
				//checking balance
				accountBalPanelC = new JPanel(new GridLayout(1, 2, 20, 5));
				accountBalC = customer.getCheckingBalance();
				accountBalanceC = new JLabel(String.format("%s", accountBalC));
				accountBalLabelC = new JLabel("Account Balance");
				
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
				transferPanel = new JPanel();
				transferLabel1 = new JLabel("Transfer $");
				transferTextField = new JTextField("", 10);
				transferLabel2 = new JLabel("from");
				transferMoneyButton = new JButton("Transfer Money");
				transferList = new JComboBox<>(transferOptions);
				transferList.setSelectedIndex(1);
				
				transferPanel.add(transferLabel1);
				transferPanel.add(transferTextField);
				transferPanel.add(transferLabel2);
				transferPanel.add(transferList);
				transferPanel.add(transferMoneyButton);
				
				
				ts = new GridBagConstraints();
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
				btnPanel = new JPanel();  
				savingsButton = new JButton("Savings Account");  
				checkingButton = new JButton("Checking Account");  
				transferButton = new JButton("Transfer");  
				exitButton = new JButton("Exit");  
				
				btnPanel.add(savingsButton);  
				btnPanel.add(checkingButton);  
				btnPanel.add(transferButton);   
				btnPanel.add(exitButton); 
				   
				getContentPane().add(btnPanel, BorderLayout.SOUTH); 
				
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
						// toAccount = savings if 0, else checking with ternary operator
						String toAccount = (transferList.getSelectedIndex() == 0) ? "savings" : "checking";
						try {
							customer.transferMoney(transferTextField.getText(), toAccount);
						} catch (FileNotFoundException | IllegalArgumentException e) {
							// TODO Auto-generated catch block
							// TODO handle exceptions
							// use e.getMessage() to get error message
						}
						update();
					}  
				}
			);  
			  
				
	}
	
	public void update() {
		
		availableBalL.setText(String.format("%.2f", customer.getSavingsBalance()));
		accountBalanceC.setText(String.format("%.2f", customer.getCheckingBalance()));

	}
	  
	// main method  
		public static void main(String argvs[])  
		{  
			BankingApp cll = new BankingApp();  
			cll.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
			cll.setVisible(true);  
		}  
}