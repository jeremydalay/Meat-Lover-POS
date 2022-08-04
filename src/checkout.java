import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;


public class checkout extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextField textField_2;
	JTextField textField_3;
	JTextField textField_4;
	JTextField textField;
	JTextField textField_1;
	JButton btnNewButton = new JButton("BACK");
	JButton btnNewButton_1 = new JButton("PAY");
	JLabel lblNewLabel_2;
	JLabel lblNewLabel_3;
	JLabel lblNewLabel_4;
	JLabel lblNewLabel_5;
	JLabel title = new JLabel("CHECK OUT ORDER");
	String receipt = "";
	String head = "Product\t\tPrice\tQuantity\tCost\tTax\tTotal\n";
	
	DecimalFormat df  = new DecimalFormat("#.00");
	
	DefaultTableModel tbl = new DefaultTableModel(0,0){
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public boolean isCellEditable(int row, int column)
		{
			return false;
		}
	};
	JTable table = new JTable();
	
	JPanel checkoutorder = new JPanel();
	
	String jdbcUrl = "jdbc:sqlite::resource:MEAT_LOVER_STOCK.db";
	
	Double payment;
	int upstock [] = new int [100];
	String ids[] = new String[100];
	Double total_sale = 0.0 ;
	int x = 0;
	int stock[] = new int[100];
	
	static checkout newframe = new checkout();
	public static void main(String[] args)
	{
		
		newframe.setVisible(true);
		newframe.setLocationRelativeTo(null);  
		newframe.order_list();
	}
	
	checkout()
	{
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 1080, 580);
			
			title.setFont(new Font("Arial",Font.BOLD,40));
			title.setForeground(Color.white);
			checkoutorder.setBackground(new Color(49, 81, 92));
			checkoutorder.setBorder(null);
			setContentPane(checkoutorder);
			checkoutorder.setLayout(new BorderLayout(0, 0));
			
			JDesktopPane desktopPane = new JDesktopPane();
			desktopPane.setBackground(new Color(49, 81, 92));
			checkoutorder.add(title, BorderLayout.NORTH);
			checkoutorder.add(desktopPane, BorderLayout.CENTER);
			
			JPanel panel = new JPanel();
			panel.setBounds(0, 94, 1066, 449);
			panel.setBackground(new Color(182,221,228));
			desktopPane.add(panel);
			SpringLayout sl_panel = new SpringLayout();
			panel.setLayout(sl_panel);
			
			JPanel panel_1 = new JPanel();
			sl_panel.putConstraint(SpringLayout.SOUTH, panel_1, 398, SpringLayout.NORTH, panel);
			panel_1.setBackground(Color.WHITE);
			sl_panel.putConstraint(SpringLayout.NORTH, panel_1, 36, SpringLayout.NORTH, panel);
			sl_panel.putConstraint(SpringLayout.WEST, panel_1, 20, SpringLayout.WEST, panel);
			sl_panel.putConstraint(SpringLayout.EAST, panel_1, 590, SpringLayout.WEST, panel);
			panel.add(panel_1);
			
			JLabel lblNewLabel_1 = new JLabel("Total:");
			sl_panel.putConstraint(SpringLayout.WEST, lblNewLabel_1, 767, SpringLayout.WEST, panel);
			lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 18));
			panel.add(lblNewLabel_1);
			
			textField_2 = new JTextField();
			sl_panel.putConstraint(SpringLayout.WEST, textField_2, 832, SpringLayout.WEST, panel);
			sl_panel.putConstraint(SpringLayout.SOUTH, textField_2, -279, SpringLayout.SOUTH, panel);
			sl_panel.putConstraint(SpringLayout.EAST, textField_2, -103, SpringLayout.EAST, panel);
			textField_2.setBorder(null);
			textField_2.setBackground(Color.ORANGE);
			textField_2.setEditable(false);
			panel.add(textField_2);
			textField_2.setColumns(10);
			
			textField_3 = new JTextField();
			sl_panel.putConstraint(SpringLayout.NORTH, textField_3, 176, SpringLayout.NORTH, panel);
			sl_panel.putConstraint(SpringLayout.WEST, textField_3, 0, SpringLayout.WEST, textField_2);
			sl_panel.putConstraint(SpringLayout.SOUTH, textField_3, 36, SpringLayout.SOUTH, textField_2);
			sl_panel.putConstraint(SpringLayout.EAST, textField_3, 0, SpringLayout.EAST, textField_2);
			textField_3.setColumns(10);
			textField_3.setBorder(null);
			textField_3.setBackground(Color.ORANGE);
			textField_3.setEditable(false);
			panel.add(textField_3);
			
			textField_4 = new JTextField();
			sl_panel.putConstraint(SpringLayout.NORTH, textField_4, 6, SpringLayout.SOUTH, textField_3);
			sl_panel.putConstraint(SpringLayout.WEST, textField_4, 0, SpringLayout.WEST, textField_2);
			sl_panel.putConstraint(SpringLayout.SOUTH, textField_4, 36, SpringLayout.SOUTH, textField_3);
			sl_panel.putConstraint(SpringLayout.EAST, textField_4, 0, SpringLayout.EAST, textField_2);
			textField_4.setColumns(10);
			textField_4.setBorder(null);
			textField_4.setBackground(Color.ORANGE);
			textField_4.setEditable(false);
			panel.add(textField_4);
			
			textField = new JTextField();
			sl_panel.putConstraint(SpringLayout.SOUTH, textField, -315, SpringLayout.SOUTH, panel);
			sl_panel.putConstraint(SpringLayout.NORTH, textField_2, 6, SpringLayout.SOUTH, textField);
			sl_panel.putConstraint(SpringLayout.NORTH, textField, 104, SpringLayout.NORTH, panel);
			sl_panel.putConstraint(SpringLayout.WEST, textField, 0, SpringLayout.WEST, textField_2);
			sl_panel.putConstraint(SpringLayout.EAST, textField, 0, SpringLayout.EAST, textField_2);
			textField.setColumns(10);
			textField.setBorder(null);
			textField.setBackground(Color.ORANGE);
			textField.setEditable(false);
			panel.add(textField);
			
			textField_1 = new JTextField();
			sl_panel.putConstraint(SpringLayout.WEST, textField_1, 832, SpringLayout.WEST, panel);
			sl_panel.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 3, SpringLayout.NORTH, textField_1);
			sl_panel.putConstraint(SpringLayout.SOUTH, lblNewLabel_1, 0, SpringLayout.SOUTH, textField_1);
			sl_panel.putConstraint(SpringLayout.EAST, lblNewLabel_1, -19, SpringLayout.WEST, textField_1);
			sl_panel.putConstraint(SpringLayout.NORTH, textField_1, 68, SpringLayout.NORTH, panel);
			sl_panel.putConstraint(SpringLayout.SOUTH, textField_1, -6, SpringLayout.NORTH, textField);
			sl_panel.putConstraint(SpringLayout.EAST, textField_1, 0, SpringLayout.EAST, textField_2);
			textField_1.setColumns(10);
			textField_1.setBorder(null);
			textField_1.setBackground(Color.ORANGE);
			textField_1.setEditable(false);
			panel.add(textField_1);
			
			btnNewButton_1.setEnabled(true);
			btnNewButton_1.addActionListener(this);
			sl_panel.putConstraint(SpringLayout.NORTH, btnNewButton_1, 351, SpringLayout.NORTH, panel);
			sl_panel.putConstraint(SpringLayout.WEST, btnNewButton_1, 169, SpringLayout.EAST, panel_1);
			sl_panel.putConstraint(SpringLayout.SOUTH, btnNewButton_1, -68, SpringLayout.SOUTH, panel);
			sl_panel.putConstraint(SpringLayout.EAST, btnNewButton_1, 255, SpringLayout.EAST, panel_1);
			SpringLayout sl_panel_1 = new SpringLayout();
			panel_1.setLayout(sl_panel_1);
			panel.add(btnNewButton_1);
			
			btnNewButton.addActionListener(this);
			sl_panel.putConstraint(SpringLayout.NORTH, btnNewButton, 60, SpringLayout.SOUTH, textField_4);
			sl_panel.putConstraint(SpringLayout.WEST, btnNewButton, 169, SpringLayout.EAST, panel_1);
			sl_panel.putConstraint(SpringLayout.SOUTH, btnNewButton, -19, SpringLayout.NORTH, btnNewButton_1);
			sl_panel.putConstraint(SpringLayout.EAST, btnNewButton, 0, SpringLayout.EAST, btnNewButton_1);
			panel.add(btnNewButton);
			
			lblNewLabel_2 = new JLabel("12% VAT:");
			sl_panel.putConstraint(SpringLayout.SOUTH, lblNewLabel_2, 0, SpringLayout.SOUTH, textField);
			sl_panel.putConstraint(SpringLayout.EAST, lblNewLabel_2, 0, SpringLayout.EAST, lblNewLabel_1);
			lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 18));
			panel.add(lblNewLabel_2);
			
			lblNewLabel_3 = new JLabel("Total Sale:");
			sl_panel.putConstraint(SpringLayout.EAST, lblNewLabel_3, -253, SpringLayout.EAST, panel);
			sl_panel.putConstraint(SpringLayout.SOUTH, lblNewLabel_3, 0, SpringLayout.SOUTH, textField_2);
			lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 18));
			panel.add(lblNewLabel_3);
			
			lblNewLabel_4 = new JLabel("Payment:");
			sl_panel.putConstraint(SpringLayout.SOUTH, lblNewLabel_4, 0, SpringLayout.SOUTH, textField_3);
			sl_panel.putConstraint(SpringLayout.EAST, lblNewLabel_4, 0, SpringLayout.EAST, lblNewLabel_1);
			lblNewLabel_4.setFont(new Font("Arial", Font.PLAIN, 18));
			panel.add(lblNewLabel_4);
			
			lblNewLabel_5 = new JLabel("Change:");
			sl_panel.putConstraint(SpringLayout.SOUTH, lblNewLabel_5, 0, SpringLayout.SOUTH, textField_4);
			sl_panel.putConstraint(SpringLayout.EAST, lblNewLabel_5, 0, SpringLayout.EAST, lblNewLabel_1);
			lblNewLabel_5.setFont(new Font("Arial", Font.PLAIN, 18));
			panel.add(lblNewLabel_5);
			
			JScrollPane scrollPane = new JScrollPane();
			
			table.setBorder(new LineBorder(Color.WHITE));
			table.setFillsViewportHeight(true);
			
			String header[] = new String[] {"Product Name","Price","Tax per Serving","Quantity", "Sub Total"};
			
			tbl.setColumnIdentifiers(header);
			table.setModel(tbl);
			scrollPane.setViewportView(table);
			
			sl_panel_1.putConstraint(SpringLayout.NORTH, scrollPane, 0, SpringLayout.NORTH, panel_1);
			sl_panel_1.putConstraint(SpringLayout.WEST, scrollPane, 0, SpringLayout.WEST, panel_1);
			sl_panel_1.putConstraint(SpringLayout.SOUTH, scrollPane, 0, SpringLayout.SOUTH, panel_1);
			sl_panel_1.putConstraint(SpringLayout.EAST, scrollPane, 570, SpringLayout.WEST, panel_1);
			scrollPane.setBackground(Color.WHITE);
			panel_1.add(scrollPane);		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		double temp;
		if (e.getSource()==btnNewButton_1)
		{
			payment = Double.parseDouble(JOptionPane.showInputDialog("PAYMENT IN CASH"));
			
			if(payment < total_sale)
			{
				temp = total_sale - payment;
				JOptionPane.showMessageDialog(newframe, "Needs Php "+df.format(temp)+" more","Payment Incomplete",JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				temp = payment - total_sale;
				JOptionPane.showMessageDialog(newframe, "RECEIVED: Php "+df.format(payment)+"\nCHANGE: Php "+df.format(temp));
				   JTextArea textArea = new JTextArea();
				    textArea.setColumns(60);
				    textArea.setLineWrap(true);
				    textArea.setWrapStyleWord(true);
				    textArea.setText(head+receipt);
				    textArea.append("\t\t\t\t\tTOTAL:\t"+df.format(total_sale));
				    textArea.setSize(textArea.getPreferredSize().width, 10);
				    JScrollPane resibo = new JScrollPane();
				    resibo.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
					resibo.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
					resibo.getViewport().add(textArea);
				    JOptionPane.showMessageDialog(null, resibo, "Order Complete",JOptionPane.PLAIN_MESSAGE);
				textField_3.setText(df.format(payment));
				textField_4.setText(df.format(temp));
				clear_order();
				btnNewButton_1.setEnabled(false);
				receipt = "";
			int y = 0;
				while(y<=x)
				{
					update_stock(y,ids[y]);
					y++;
				}
			}
		}
		else
		{
			
			newframe.dispose();
			GUI.main(new String[0]);
		}
	}
	
	
	public void order_list()
	{
		String prod_name = new String();
		double pps = 0;
		double taxps = 0;
		int quant = 0;
		double ttax = 0;
		double subt = 0;
		double lpr = 0;
		double total = 0;
		double total_tax = 0;
		btnNewButton_1.setEnabled(true);
		
		textField.setText("");
		textField_1.setText("");
		textField_2.setText("");
		textField_3.setText("");
		textField_4.setText("");
		
		
		int row = tbl.getRowCount();
		
		
		for (int i = row-1; i>=0; i--)
		{
			tbl.removeRow(i);
		}
		
		x = 0;
		Statement statement = null;
		ResultSet result = null;
		
		try {
			Connection connection = DriverManager.getConnection(jdbcUrl);
			String sql = "SELECT * FROM ORDER_LIST";
			statement = connection.createStatement();
			result = statement.executeQuery(sql);
			
			while(result.next())
			{
				prod_name = result.getString("PRODUCT");
				pps = Double.parseDouble(result.getString("INIT_PRICE"));
				taxps = pps * 0.12;
				quant = Integer.parseInt(result.getString("QUANTITY"));
				ttax = taxps * quant;
				lpr = Double.parseDouble(result.getString("LAST_PRICE"));
				subt = lpr+ttax;
				total_tax += ttax; 
				total+=lpr;
				upstock[x] =  Integer.parseInt(result.getString("STOCK_UPDATE"));
				String res = forma(prod_name,30)+"\tPhp "+df.format(pps)+"\t"+Integer.toString(quant)+"\tPhp "+df.format(lpr)+"\tPhp "+df.format(ttax)+"\tPhp "+df.format(subt)+"\n";
				receipt = receipt + res;
				String data[] = {prod_name,"Php "+df.format(pps),"Php "+df.format(taxps),Integer.toString(quant),"Php "+df.format(subt)};  			
				tbl.addRow(data);
				ids [x] = result.getString("PROD_ID");
 				x++;
			}
		}
		catch (SQLException e1)
		{
			e1.printStackTrace();
		}
		finally {
			  if (result != null) {
			    try {
			      result.close(); 
			    } catch (SQLException e) {
			      e.printStackTrace();
			    }
			  }
			  if (statement != null) { 
				    try {
				      statement.close(); 
				    } catch (SQLException e) {
				      e.printStackTrace();
				    }
				  }
			}
		total_sale = total + total_tax;
		textField.setText(df.format(total_tax));
		textField_1.setText(df.format(total));
		textField_2.setText(df.format(total_sale));
	}
	
	public void update_stock(int z, String code)
	{
		Statement statement = null;
		try {
			Connection connection = DriverManager.getConnection(jdbcUrl);
			String sql = "UPDATE ALL_PRODUCTS SET Stock = '"+upstock[z]+"' WHERE ID ='"+code+"'";
			statement = connection.createStatement();
			statement.executeUpdate(sql);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			 if (statement != null) { 
				    try {
				      statement.close(); 
				    } catch (SQLException e) {
				      e.printStackTrace();
				    }
				  }
		}
	}
	
	public void clear_order()
	{
		Statement statement = null;
    	try {
			Connection connection = DriverManager.getConnection(jdbcUrl);
			statement = connection.createStatement();
			String sql = "DELETE FROM ORDER_LIST";
			statement.executeUpdate(sql);
	
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally {
			  if (statement != null) {
				    try {
				      statement.close(); 
				    } catch (SQLException e) {
				      e.printStackTrace();
				    }
				  }
			}
    }
	public static String forma(String string, int length) {
	    return String.format("%1$"+length+ "s", string);
	}
}