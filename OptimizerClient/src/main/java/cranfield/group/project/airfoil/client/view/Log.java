package cranfield.group.project.airfoil.client.view;

import javax.swing.*;

import cranfield.group.project.airfoil.client.MarsClient;
import java.awt.event.*;


public class Log extends JFrame {
	
	private MarsClient client;
    
	private JButton blogin = new JButton("Login");
	private JPanel panel = new JPanel();
	private JTextField txuser = new JTextField(15);
	private JPasswordField pass = new JPasswordField(15);

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
        
        // Add Listener on the close-window button
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
            	// Terminate the socket connection with the server
                client.terminateConnection();
            }
        });
    }

    public void actionlogin() {
        blogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String puname = txuser.getText();
                String ppaswd = pass.getText();
            	if(client.areValidatedCredentials(puname, ppaswd)){
                    DesciptionFrame regFace = new DesciptionFrame(client);
                    regFace.setVisible(true);
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
  
}
