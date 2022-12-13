import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

public class BankingApp extends JFrame {
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
	private JLabel availableBalLabel;
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
	private JPanel transactionHistPanelC;
	private JLabel recentTranLabelC;
	private JLabel dateLabelC;
	private JLabel amountLabelC;
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
	private GridBagConstraints sa;
	private JPanel transferPanel;
	private JLabel transferLabel1;
	private JTextField transferTextField;
	private JLabel transferLabel2;
	private JButton transferMoneyButton;
	private String[] transferOptions = { "Checking to Saving", "Saving to Checking" };
	private JComboBox<String> transferList;
	private GridBagConstraints ts;
	private GridBagConstraints ls;
	private JLabel loginErrorMsg;
	private JLabel transferValMsg = new JLabel();
	private JLabel[][] savingsRecentTransactions;
	private JLabel[][] checkingRecentTransactions;

	public BankingApp() {
		setTitle("Banking App Simulator");
		setSize(800, 400);
		cPanel = new JPanel();
		cObjl = new CardLayout();
		cPanel.setLayout(cObjl);

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

		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {
					customer = new Customer(username.getText(), password.getText());
				} catch (IllegalArgumentException | IOException e) {
					loginErrorMsg.setVisible(true);
					return;
				}

				populatePanels();
				update();
				currCard = 2;
				cObjl.show(cPanel, "" + (currCard));
			}
		});
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
		// savings
		accountInfoPanel = new JPanel(new GridLayout(5, 2, 10, 10));
		accountNumLabel = new JLabel("Account Number");
		routingNumLabel = new JLabel("Routing Number");
		IRLabel = new JLabel("Interest Rate");
		IRALabel = new JLabel("Interest Accrued over 52 weeks");
		availableBalLabel = new JLabel("Available Balance");
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
		accountInfoPanel.add(availableBalLabel);
		accountInfoPanel.add(availableBalL);

		// recent transactions panel code
		// savings recent transactions
		transactionHistPanelS = new JPanel(new GridLayout(4, 3, 20, 5));
		recentTranLabelS = new JLabel("Recent Transactions");
		dateLabelS = new JLabel("Date");
		amountLabelS = new JLabel("Amount");
		transactionHistPanelS.add(recentTranLabelS);
		transactionHistPanelS.add(dateLabelS);
		transactionHistPanelS.add(amountLabelS);
		
		savingsRecentTransactions = new JLabel[3][3];
		for (int i = 0; i < savingsRecentTransactions.length; i++) {
			for (int j = 0; j < savingsRecentTransactions[i].length; j++) {
				savingsRecentTransactions[i][j] = new JLabel("");
				transactionHistPanelS.add(savingsRecentTransactions[i][j]);
			}
		}
		
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
		// checking recent trans
		transactionHistPanelC = new JPanel(new GridLayout(4, 3, 20, 5));
		recentTranLabelC = new JLabel("Recent Transactions");
		dateLabelC = new JLabel("Date");
		amountLabelC = new JLabel("Amount");
		transactionHistPanelC.add(recentTranLabelC);
		transactionHistPanelC.add(dateLabelC);
		transactionHistPanelC.add(amountLabelC);
		checkingRecentTransactions = new JLabel[3][3];
		for (int i = 0; i < checkingRecentTransactions.length; i++) {
			for (int j = 0; j < checkingRecentTransactions[i].length; j++) {
				checkingRecentTransactions[i][j] = new JLabel("");
				transactionHistPanelC.add(checkingRecentTransactions[i][j]);
			}
		}

		// checking balance
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
		jPanel4.add(accountBalPanelC, cs);

		cs.gridx = 2;
		cs.gridy = 2;
		cs.gridwidth = 3;
		jPanel4.add(transactionHistPanelC, cs);
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

		ts.gridx = 2;
		ts.gridy = 3;
		ts.gridwidth = 3;
		jPanel5.add(transferValMsg, ts);
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
		savingsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if (currCard != 3) {
					currCard = 3;
					cObjl.show(cPanel, "" + (currCard));
				}
			}
		});

		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				System.exit(0);
			}
		});

		checkingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if (currCard != 4) {
					currCard = 4;
					cObjl.show(cPanel, "" + (currCard));
				}
			}
		});

		transferButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if (currCard != 5) {
					transferValMsg.setVisible(false);
					currCard = 5;
					cObjl.show(cPanel, "" + (currCard));
				}
			}
		});

		transferMoneyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				// toAccount = savings if 0, else checking with ternary operator
				String toAccount = (transferList.getSelectedIndex() == 0) ? "savings" : "checking";
				try {
					customer.transferMoney(transferTextField.getText(), toAccount);
					transferValMsg.setText("Transfer Success!");
					transferValMsg.setForeground(Color.GREEN);
					transferValMsg.setVisible(true);
				} catch (FileNotFoundException | IllegalArgumentException e) {
					transferValMsg.setText(e.getMessage());
					transferValMsg.setForeground(Color.RED);
					transferValMsg.setVisible(true);
				}
				update();
			}
		});
	}

	public void update() {
		availableBalL.setText(String.format("%.2f", customer.getSavingsBalance()));
		accountBalanceC.setText(String.format("%.2f", customer.getCheckingBalance()));
		ArrayList<Transaction> savingsTransactions = customer.getSavingsTransactions();
		ArrayList<Transaction> checkingTransactions = customer.getCheckingTransactions();
		// update transaction history
		// get the last five transactions in reverse order
		for (int i = 0; i < 3; i++) {
			savingsRecentTransactions[i][0].setText(savingsTransactions.get(savingsTransactions.size() - 1 - i).description);
			savingsRecentTransactions[i][1].setText(savingsTransactions.get(savingsTransactions.size() - 1 - i).date);
			savingsRecentTransactions[i][2].setText(String.format("%.2f", savingsTransactions.get(savingsTransactions.size() - 1 - i).amount));
			checkingRecentTransactions[i][0].setText(checkingTransactions.get(checkingTransactions.size() - 1 - i).description);
			checkingRecentTransactions[i][1].setText(checkingTransactions.get(checkingTransactions.size() - 1 - i).date);
			checkingRecentTransactions[i][2].setText(String.format("%.2f", checkingTransactions.get(checkingTransactions.size() - 1 - i).amount));
		}

	}

	// main method
	public static void main(String args[]) {
		BankingApp cll = new BankingApp();
		cll.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cll.setVisible(true);
	}
}