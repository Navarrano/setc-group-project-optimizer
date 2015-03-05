package cranfield.group.project.airfoil.client.view;

import javax.swing.*;

import cranfield.group.project.airfoil.client.MarsClient;

import java.awt.*;
import java.awt.event.*;

public class Log extends JFrame {

    private JButton blogin = new JButton("Login");
    private JPanel panel = new JPanel();
    private JTextField txuser = new JTextField(15);
    private JPasswordField pass = new JPasswordField(15);
    private MarsClient client;
    
    public Log() {
        super("Login Authentification");
        
        client = new MarsClient("localhost",6066);
        
        setSize(300, 200);
        setLocation(500, 280);
        panel.setLayout(null);

        txuser.setBounds(70, 30, 150, 20);
        pass.setBounds(70, 65, 150, 20);
        blogin.setBounds(110, 100, 80, 20);

        panel.add(blogin);
        panel.add(txuser);
        panel.add(pass);

        getContentPane().add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        actionlogin();
        actionClosingWindow();
    }

    public void actionlogin() {
        blogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String puname = txuser.getText();
                String ppaswd = pass.getText();
                if(client.areValidatedCredentials(puname, ppaswd)){
                	MainFrame frame = new MainFrame(client);
                	frame.contentPane.setVisible(true);
                    dispose();
                } else {

                    JOptionPane.showMessageDialog(null, "Wrong Password / Username");
                    txuser.setText("");
                    pass.setText("");
                    txuser.requestFocus();
                }

            }
        });
    }
    
    public void actionClosingWindow(){
    	// Add Listener on the close-window button
    	addWindowListener(new WindowAdapter() {
    		public void windowClosing(WindowEvent e) {
    			// Terminate the socket connection with the server
    			client.terminateConnection();
    		}
    	});
    }
}


