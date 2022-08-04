import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

class login_window extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton submit = new JButton("Continue");
	JFrame frame = new JFrame("Meat Lover");
	JPasswordField pass = new JPasswordField();
	JTextField user = new JTextField();
	
	login_window() 
	{
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     frame.setSize(400, 320);      
	     frame.setLocationRelativeTo(null);  
	     frame.setVisible(true);
	    
	     JPanel top = new JPanel();
	     JPanel center = new JPanel();
	     center.setBackground(Color.decode("#B6DDE4"));
	     center.setLayout(null);
	     top.setBackground(Color.decode("#31515C"));
	     	JLabel header = new JLabel("LOG IN");
	     	header.setFont(new Font("Arial",Font.BOLD,24));
	     	header.setForeground(Color.WHITE);
	     	header.setHorizontalAlignment(JTextField.CENTER);
	     	top.add(header);
	     JPanel panel = new JPanel();
		   	panel.setLayout(new BorderLayout());
	     	
		   	JLabel name = new JLabel("Meat Lover");
		   	JLabel sub = new JLabel("POS System");
		   	
		   	name.setFont(new Font("Arial",Font.BOLD,26));
		   	name.setBounds(120, 20, 150, 50);
		   	
		   	sub.setFont(new Font("Arial",Font.BOLD,16));
		   	sub.setBounds(140, 40, 150, 50);
		   	
		    user.setBounds(175,100,150,30);
	     
		    JLabel userl = new JLabel("Username:");
		    userl.setBounds(50, 100, 150, 30);
		    userl.setFont(new Font("Arial",Font.BOLD,16));
		    userl.setForeground(Color.BLACK);
		    
		    pass.setBounds(175,155,150,30);
		    
		    JLabel passl = new JLabel("Password:");
		    passl.setHorizontalAlignment(JLabel.CENTER);
		    passl.setFont(new Font("Arial",Font.BOLD,16));
		    passl.setForeground(Color.BLACK);
		    passl.setBounds(40, 150, 100, 30);
	
		    
		    submit.setHorizontalAlignment(JButton.CENTER);
		    submit.setForeground(Color.white);
		    submit.setBackground(Color.decode("#23252d"));
		    submit.setPreferredSize(new Dimension(280,30));
		    submit.addActionListener(this);
		    
		   
		    panel.setBackground(Color.decode("#23252d"));
		    center.add(name);center.add(sub);center.add(userl);center.add(user);center.add(passl);center.add(pass);
		    panel.add(top,BorderLayout.NORTH);panel.add(submit,BorderLayout.SOUTH);
		    panel.add(center,BorderLayout.CENTER);
		    frame.setContentPane(panel);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String usern = user.getText();
		String passn = pass.getText();
		String dbuser = new String();
		String dbpass =  new String();
		
		String jdbcUrl = "jdbc:sqlite::resource:MEAT_LOVER_STOCK.db";
		int a =0;
		try {
			Connection connection = DriverManager.getConnection(jdbcUrl);
			String sql = "SELECT * FROM USERS";
			
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			while(result.next())
			{
				dbuser = result.getString("USER_NAME");
				dbpass = result.getString("PASSWORD");
				
				if(usern.trim().equals(dbuser) && passn.trim().equals(dbpass))
				{
					JOptionPane.showMessageDialog(frame, "Granted Access!");
					frame.dispose();
					GUI.main(new String[0]);
					a = 1;
				}
			}
			if(a==0)
				JOptionPane.showMessageDialog(frame, "Unauthorized User!","ERROR",JOptionPane.ERROR_MESSAGE);
			
		} catch (SQLException e1) 
		{
			JOptionPane.showMessageDialog(frame, "Unauthorized User!","ERROR",JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new login_window();
	}
}
