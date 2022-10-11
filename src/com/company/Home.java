package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Home extends Frame{

    private static int pageNumber = 0;
    private UserInfo userInfo;
    private JFrame frame;
    private JPanel panel;
    private JButton showSharesButton, depoOrwthdrwButton, buyorsellButton, buyButton
            , sellButton, transactionButton, depositButton,backButton,withdrawButton,CryptoMarketButton,
    LogoutButton;
    private JTextField balanceBox, nameBox;
    private JLabel label1,label2;
    private JTextArea coins;
    private float investmentAmount, bitcoinInvestment, USDCoinInvestment;
    private float bitcoinOwned, USDCoinOwned;
    private UserInfo userinfo;
    private static JTextArea NameandBalance = new JTextArea();

    //Standard user input/output
    public static void level1() {
        String Login = inputString("Do you want to enter the system (Yes/No)");
        if (Login.equals("Yes")) {

        } else {
            System.exit(0);
        }
    }
    //outputs the users details
    public void printUserInfo(int index){
        String text = userinfo.getUserInfo(index);
        print (text);
    }
//Adds a user
    public void addUser(UsersBalance usersBalance){
        userinfo.addUser(usersBalance);
        userinfo.getUserInfo(pageNumber);
        printUserInfo(pageNumber);
        this.setVisible(false);
    }
//Prints to the GUI main menu
    public static void print(String text){
            NameandBalance.setText(text);
            NameandBalance.setEditable(false);
        }
//Creates the string to output in the show shares button
    public static String showShares(CryptoStocks bitcoin, CryptoStocks USDCoin){
        String text = (bitcoin.getCryptoname()+"                       £"+bitcoin.getPrice()+"\n"+ USDCoin.getCryptoname()+"                    £"+USDCoin.getPrice());
        return(text);
        }

    public Home(UsersBalance user, UserInfo _userInfo) {
        userinfo = _userInfo;
        //Increment Static values
        pageNumber++;
        //Create objects of the coins
        CryptoStocks bitcoin = new Bitcoin();
        CryptoStocks USDCoin = new USDCoin();

        this.addUser(user);
        //Uses JFrame for GUI
        frame = new JFrame();
        panel = new JPanel();
        //Sets sizes and placements on the the mainmenu
        frame.setSize(600, 300);
        NameandBalance.setBounds(10,100,120,120);
        panel.add(NameandBalance);
        showSharesButton = new JButton("Show Shares");
        showSharesButton.setBounds(10, 10, 50, 50);
        panel.add(showSharesButton);
        frame.add(panel);
        frame.setVisible(true);
        panel.setVisible(true);
        showSharesButton.addActionListener(new ActionListener() {
            @Override
            //Allows the user to view prices of crypto
            public void actionPerformed(ActionEvent e) {
                frame = new JFrame();
                panel = new JPanel();
                frame.setSize(350, 250);
                Label d1;
                d1 = new Label("CryptoCurrency");
                Label d2 = new Label("Price(£)");
                backButton = new JButton("Back");
                panel.setLayout(null);
                d1.setBounds(10, 50, 130, 25);
                d2.setBounds(150, 50, 200, 25);
                coins = new JTextArea(showShares(bitcoin, USDCoin));
                coins.setEditable(false);
                coins.setBounds(10, 80, 200, 50);
                panel.add(d1);
                panel.add(d2);
                panel.add(coins);
                backButton.setBounds(65, 150, 100, 30);
                panel.add(backButton);
                frame.add(panel);
                frame.setVisible(true);
                panel.setVisible(true);
                backButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.setVisible(false);
                    }
                });
            }
        });
        //Builds GUI for the button to add and withdraw moeny
        depoOrwthdrwButton = new JButton("Deposit/Withdraw");
        depoOrwthdrwButton.setBounds(10, 10, 50, 50);
        panel.add(depoOrwthdrwButton);
        frame.add(panel);
        frame.setVisible(true);
        panel.setVisible(true);
        depoOrwthdrwButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame = new JFrame();
                panel = new JPanel();
                balanceBox = new JTextField();
                nameBox = new JTextField();
                label1 = new JLabel("Client Name");
                label2 = new JLabel("Amount");
                depositButton = new JButton("Deposit");
                withdrawButton = new JButton("Withdraw");
                frame.setSize(350, 250);
                frame.add(panel);
                panel.setLayout(null);
                label1.setBounds(10, 10, 100, 10);
                label2.setBounds(120, 10, 100, 10);
                nameBox.setBounds(10, 30, 100, 30);
                balanceBox.setBounds(120, 30, 100, 30);
                depositButton.setBounds(15, 60, 100, 30);
                withdrawButton.setBounds(115, 60, 100, 30);
                panel.add(depositButton);
                panel.add(withdrawButton);
                panel.add(balanceBox);
                panel.add(nameBox);
                panel.add(label1);
                panel.add(label2);
                frame.setVisible(true);
                depositButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                        LocalDateTime now = LocalDateTime.now();
                        frame.setVisible(false);
                        userinfo.deposit(nameBox.getText(),Integer.parseInt(balanceBox.getText())); //deposits into account
                        printUserInfo(pageNumber);
                        String fileinput ="Name: "+ nameBox.getText()+"--Amount Deposited: £"+ balanceBox.getText()+
                                "--Date: "+dtf.format(now);
                        FileInpAndOutp.writeInput(fileinput); //inputs to file
                        FileInpAndOutp.sortInputFile(); //sorts file into size
                    }
                });
                withdrawButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                        LocalDateTime now = LocalDateTime.now();
                        frame.setVisible(false);
                        //Doesn't allow the user to take out money they dont have
                        if (Float.parseFloat(balanceBox.getText()) <= user.getFunds())
                            userinfo.withdraw(nameBox.getText(),Integer.parseInt(balanceBox.getText())); //withdraws
                            printUserInfo(pageNumber);
                            String fileinput ="Name: "+ nameBox.getText()+"--Amount Withdrawn: £"+ balanceBox.getText()+
                                    "--Date: "+dtf.format(now); // String to be stored in file
                            FileInpAndOutp.writeInput(fileinput);
                            FileInpAndOutp.sortInputFile();
                    }
                });
            }
        });
        //buying and selling crypto
        buyorsellButton = new JButton("Buy/Sell Shares");
        buyorsellButton.setBounds(10, 10, 50, 50);
        panel.add(buyorsellButton);
        frame.add(panel);
        frame.setVisible(true);
        panel.setVisible(true);
        buyorsellButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame = new JFrame();
                panel = new JPanel();
                balanceBox = new JTextField();
                nameBox = new JTextField();
                label1 = new JLabel("Coin Name");
                label2 = new JLabel("Amount");
                buyButton = new JButton("Buy");
                sellButton = new JButton("Sell");
                frame.setSize(350, 250);
                frame.add(panel);
                panel.setLayout(null);
                label1.setBounds(10, 10, 100, 10);
                label2.setBounds(120, 10, 100, 10);
                nameBox.setBounds(10, 30, 100, 30);
                balanceBox.setBounds(120, 30, 100, 30);
                buyButton.setBounds(15, 60, 100, 30);
                sellButton.setBounds(115, 60, 100, 30);
                panel.add(buyButton);
                panel.add(sellButton);
                panel.add(balanceBox);
                panel.add(nameBox);
                panel.add(label1);
                panel.add(label2);
                frame.setVisible(true);
                buyButton.addActionListener(new ActionListener() {
                    //Buy coins
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //for bitcoin
                        if(nameBox.getText().equals("Bitcoin")){
                            investmentAmount = Float.parseFloat(balanceBox.getText())*bitcoin.getPrice();
                            //Only allows if the amount in account is enough to buy (Validation)
                            if(investmentAmount <= user.getFunds()){
                                bitcoinInvestment += investmentAmount;
                                user.withdraw(investmentAmount);
                                //Resets the variable
                                investmentAmount = 0;
                                bitcoinOwned += Float.parseFloat(balanceBox.getText());
                                //outputs amount of coins owned to home screen
                                print("Name: "+user.getName()+"\n"+"Balance: £"+user.getFunds()+
                                        "\n"+"Bitcoin Amo: "+bitcoinOwned+"\n"+"USDCoin Amo: "+USDCoinOwned);
                            }
                            //for USDCoin
                        } else if (nameBox.getText().equals("USDCoin")){
                            investmentAmount = Float.parseFloat(balanceBox.getText())*USDCoin.getPrice();
                            if(investmentAmount <= user.getFunds()){
                                USDCoinInvestment += investmentAmount;
                                user.withdraw(investmentAmount);
                                investmentAmount = 0;
                                USDCoinOwned += Float.parseFloat(balanceBox.getText());
                                print("Name: "+user.getName()+"\n"+"Balance: £"+user.getFunds()+
                                        "\n"+"Bitcoin Amo: "+bitcoinOwned+"\n"+"USDCoin Amo: "+USDCoinOwned);
                                System.out.println(USDCoinOwned);
                            }
                        }
                        frame.setVisible(false);
                    }
                });
                sellButton.addActionListener(new ActionListener() {
                    //Sell coins
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(nameBox.getText().equals("Bitcoin")){
                            if(Float.parseFloat(balanceBox.getText()) <= bitcoinOwned){
                                investmentAmount = Float.parseFloat(balanceBox.getText())*bitcoin.getPrice();
                                bitcoinInvestment -= investmentAmount;
                                user.deposit(investmentAmount);
                                investmentAmount = 0;
                                bitcoinOwned -= Float.parseFloat(balanceBox.getText());
                                print("Name: "+user.getName()+"\n"+"Balance: "+user.getFunds()+
                                        "\n"+"Bitcoin Amo: "+bitcoinOwned+"\n"+"USDCoin Amo: "+USDCoinOwned);
                            }

                        } else if (nameBox.getText().equals("USDCoin")){
                            if(Float.parseFloat(balanceBox.getText()) <= USDCoinOwned){
                                investmentAmount = Float.parseFloat(balanceBox.getText())*USDCoin.getPrice();
                                USDCoinInvestment -= investmentAmount;
                                user.deposit(investmentAmount);
                                investmentAmount = 0;
                                USDCoinOwned -= Float.parseFloat(balanceBox.getText());
                                print("Name: "+user.getName()+"\n"+"Balance: "+user.getFunds()+
                                        "\n"+"Bitcoin Amo: "+bitcoinOwned+"\n"+"USDCoin Amo: "+USDCoinOwned);
                            }
                        }
                        frame.setVisible(false);
                    }
                });
            }
        });
        //outputs all transactions in a readable format
        transactionButton = new JButton("View Transactions");
        transactionButton.setBounds(10, 10, 50, 50);
        panel.add(transactionButton);
        frame.add(panel);
        frame.setVisible(true);
        panel.setVisible(true);
        transactionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame = new JFrame();
                panel = new JPanel();
                label1 = new JLabel("Look at terminal");
                label1.setBounds(10, 10, 300, 10);
                panel.add(label1);
                backButton = new JButton("Back");
                frame.setSize(350, 250);
                frame.add(panel);
                panel.setLayout(null);
                backButton.setBounds(65, 60, 100, 30);
                panel.add(backButton);
                frame.setVisible(true);
                //trys and catches are necessary here in case of the text file corrupting
                try {
                    FileInpAndOutp.OutputFile();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                backButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.setVisible(false);
                    }
                });
            }
        });
        CryptoMarketButton = new JButton("View prices of some coins ");
        CryptoMarketButton.setBounds(10, 10, 50, 50);
        panel.add(CryptoMarketButton);
        frame.add(panel);
        frame.setVisible(true);
        panel.setVisible(true);

        CryptoMarketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EventQueue.invokeLater(new Runnable()
                {
                    public void run()
                    {
                        BarChart.createAndShowGUI();
                    }
                });
            }
        });
        LogoutButton = new JButton("Logout ");
        LogoutButton.setBounds(10, 10, 50, 50);
        panel.add(LogoutButton);
        frame.add(panel);
        frame.setVisible(true);
        panel.setVisible(true);
        //Add logout button
        LogoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Make trade window disappear
                new LoginWindow(_userInfo);
            }
        });



    }
    //for taking inputs
    public static String inputString (String message){
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        return scanner.nextLine();
    }
//main methods
    public static void main(String[] args) {
        level1();
        UserInfo userInfo = new UserInfo();
        new LoginWindow(userInfo);
    }

}