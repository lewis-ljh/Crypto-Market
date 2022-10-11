package com.company;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginWindow extends Frame {

        public LoginWindow(UserInfo userInfo){

            //UI Creation

            Panel panel = new Panel();
            panel.setLayout(new GridLayout(1,3));
            this.add(panel);
            Label textLabel = new Label("Name:");
            panel.add(textLabel);
            TextArea inputBox = new TextArea();
            panel.add(inputBox);
            Button submitButton = new Button("Login");
            panel.add(submitButton);
            WindowCloser wc = new WindowCloser();
            this.addWindowListener(wc);

            //Make pages


            submitButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent evt) {
                    String userInput = inputBox.getText();
                    if (!(userInput.equals(""))){
                        UsersBalance user = new UsersBalance(userInput);
                        userInfo.addUser(user);
                        new Home(user, userInfo);
                        dispose();
                    }
                }
            });

            panel.setVisible(true);

            this.setSize(300,75);
            this.setLocationRelativeTo(null);
            this.setVisible(true);
        }
    }