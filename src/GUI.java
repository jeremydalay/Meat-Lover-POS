import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SpringLayout;
import javax.swing.JSpinner;
import javax.swing.ScrollPaneConstants;
import javax.swing.JDesktopPane;
import javax.swing.border.EtchedBorder;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.JTextArea;
public class GUI extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel contentPane;
	JTabbedPane tabbedPane;
	JTextField friedStock, roastStock, tinolaStock, adoboStock, afritadaStock;
	JTextField porkSiniStock, nilagaStock, lechonStock, porkAdboStock, liempoStock;
	JTextField tapaStock, caldStock, steakStock, groundStock, bulaloStock;
	JTextField tilapiaStock, filletStock, dinaingStock, hiponStock, tempuraStock;
	JTextField coccStock, spriteStock, pepsiStock, royalStock, beerStock;
	JTextField txtProductId, idNum;
	JTextField txtPrice, price;
	JTextField txtProductDescription;
	JTextArea desc;
	JTextField txtQuantity;
	JTextField txtCurrentOrder;
	JTextArea order = new JTextArea();
	
	JButton btnAdd = new JButton("ADD");
	JButton btnClear = new JButton("CLEAR");
	JButton btnCheckOut = new JButton("CHECK OUT");
	JButton btnFriedChic = new JButton("<html><center>C1::Fried<br>Chicken</html>");
	JButton btnRoastChic = new JButton("<html><center>C2::Roasted<br>Chicken</html>");
	JButton btnTinola = new JButton("<html><center>C3::Chicken<br>Tinola</html>");
	JButton btnAdoboChic = new JButton("<html><center>C4::Chicken<br>Adobo</html>");
	JButton btnAfritada = new JButton("<html><center>C5::Chicken<br>Afritada</html>");
	JButton btnLiempo = new JButton("<html><center>P1::Grilled<br>Liempo</html>");
	JButton btnPorkSini = new JButton("<html><center>P2::Pork<br>Sinigang</html>");
	JButton btnNilaga = new JButton("<html><center>P3::Pork<br>Nilaga</html>");
	JButton btnLechon = new JButton("<html><center>P4::Lechon<br>Kawali</html>");
	JButton btnPorkAdobo = new JButton("<html><center>P5::Pork<br>Adobo</html>");
	JButton btnTapa = new JButton("<html><center>B1::Beef<br>Tapa</html>");
	JButton btnBeefCald = new JButton("<html><center>B2::Beef<br>Caldereta</html>");
	JButton btnSteak = new JButton("<html><center>B3::Beef<br>Steak</html>");
	JButton btnGroundBf = new JButton("<html><center>B4::Ground<br>Beef</html>");
	JButton btnBulalo = new JButton("<html><center>B5::Beef<br>Bulalo</html>");
	JButton btnTilapia = new JButton("<html><center>S1::Fried<br>Tilapia</html>");
	JButton btnFillet = new JButton("<html><center>S2::Fish<br>Fillet</html>");
	JButton btnDinaing = new JButton("<html><center>S3::Dinaing<br>na Bangus</html>");
	JButton btnHipon = new JButton("<html><center>S4::Sinigang<br>na Hipon</html>");
	JButton btnTempura = new JButton("<html><center>S5::Shrimp<br>Tempura</html>");
	JButton btnCoke = new JButton("<html><center>D2::Coke</html>");
	JButton btnSprite = new JButton("<html><center>D3::Sprite</html>");
	JButton btnPepsi = new JButton("<html><center>D4::Pepsi</html>");
	JButton btnRoyal = new JButton("<html><center>D5::Royal</html>");
	JButton btnBeer = new JButton("<html><center>D1::Beer</html>");

	static GUI frame = new GUI();
	
	JSpinner qtySpinner = new JSpinner();
	
	String jdbcUrl = "jdbc:sqlite::resource:MEAT_LOVER_STOCK.db";
	
	String dbid = new String();
	String dbprice = new String();
	String dbdesc = new String();
	String dbdish = new String();
	String head = "ID\tPrice\tQuantity\tTotal\n";
	String show = new String();
	int quant;
	int stockz;
	
	JLabel lblStock[] = new JLabel[25];
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
	
	GUI() {
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1080, 580);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(49, 81, 92));
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(new Color(182, 221, 228));
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel_2 = new JPanel();	//login/logout
		tabbedPane.addTab("Meat Lover", null, panel_2, null);
		
		ImageIcon imgc = new ImageIcon(this.getClass().getResource("meat_lover_with_sf_centered_est.png"));
		Image img = imgc.getImage();
		Image newimg = img.getScaledInstance(480, 480, Image.SCALE_SMOOTH);
		imgc = new ImageIcon(newimg);
		JLabel pic = new JLabel(imgc);
		pic.setHorizontalAlignment(JLabel.CENTER);
		
		panel_2.add(pic);
		panel_2.setBackground(new Color(182, 221, 228));
		
		JPanel panel_1 = new JPanel();	//inventory
		tabbedPane.addTab("Inventory", null, panel_1, null);
		tabbedPane.addChangeListener(new ChangeListener() {
			 public void stateChanged(ChangeEvent changeEvent) {
			        JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
			        int index = sourceTabbedPane.getSelectedIndex();
			        
			        	if(index == 1)
			        	{
			        		table.setVisible(false);
			        		int check = authorize();
			        		if(check==1)
			        		{
			        			table.setVisible(true);
			        			inv_list();
			        		}
			        	}
			        	else if(index==3)
		        		{
		        			int set = JOptionPane.showConfirmDialog(tabbedPane, "Are you sure you want to Log-Out?","Log-Out",JOptionPane.YES_NO_OPTION);
		        			if(set==JOptionPane.YES_OPTION)
		        			{
		        				frame.dispose();
		        				login_window.main(new String[0]);
		        			}
		        		}
			      }
		});
		panel_1.setBackground(new Color(182, 221, 228));
		SpringLayout sl_panel_1 = new SpringLayout();
		panel_1.setLayout(sl_panel_1);
		
		
		//Start here
				JPanel panel_5 = new JPanel();
				
				panel_5.setBorder(new LineBorder(Color.WHITE));
				sl_panel_1.putConstraint(SpringLayout.WEST, panel_5, 50, SpringLayout.WEST, panel_1);
				sl_panel_1.putConstraint(SpringLayout.EAST, panel_5, 1010, SpringLayout.WEST, panel_1);
				panel_5.setBackground(Color.WHITE);
				sl_panel_1.putConstraint(SpringLayout.NORTH, panel_5, 50, SpringLayout.NORTH, panel_1);
				sl_panel_1.putConstraint(SpringLayout.SOUTH, panel_5, 465, SpringLayout.NORTH, panel_1);
				panel_1.add(panel_5);
				SpringLayout sl_panel_5 = new SpringLayout();
				panel_5.setLayout(sl_panel_5);
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
				sl_panel_5.putConstraint(SpringLayout.NORTH, scrollPane, 0, SpringLayout.NORTH, panel_5);
				sl_panel_5.putConstraint(SpringLayout.WEST, scrollPane, 0, SpringLayout.WEST, panel_5);
				sl_panel_5.putConstraint(SpringLayout.SOUTH, scrollPane, 415, SpringLayout.NORTH, panel_5);
				sl_panel_5.putConstraint(SpringLayout.EAST, scrollPane, 960, SpringLayout.WEST, panel_5);
				panel_5.add(scrollPane);
				scrollPane.setBorder(new LineBorder(Color.WHITE));
				
				
				table.setBorder(new LineBorder(Color.WHITE));
				table.setFillsViewportHeight(true);
				
				String header[] = new String[] {"Product ID","Product Category","Product Name","Price","Stock","Product Description" };
				tbl.setColumnIdentifiers(header);
				table.setModel(tbl);
				table.setRowHeight(30);
				TableColumnModel colmod = table.getColumnModel();
				colmod.getColumn(0).setPreferredWidth(2);
				colmod.getColumn(1).setPreferredWidth(15);
				colmod.getColumn(3).setPreferredWidth(2);
				colmod.getColumn(4).setPreferredWidth(2);
				colmod.getColumn(5).setPreferredWidth(600);
				scrollPane.setViewportView(table);
				
		
		JPanel panel = new JPanel(); //POS
		tabbedPane.addTab("Point of Sale", null, panel, null);
		panel.setBackground(new Color(182, 221, 228));
		panel.setLayout(null);
		
		tabbedPane.addTab("LOG-OUT", null, null, null);
		
		
		//POS Contents
		JLabel lblSelect = new JLabel("Select Order Here:");
		lblSelect.setBounds(10, 5, 115, 24);
		panel.add(lblSelect);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(790, 10, 259, 493);
		desktopPane.setBackground(Color.WHITE);
		desktopPane.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel.add(desktopPane);
		desktopPane.setLayout(null);
		
		txtProductId = new JTextField();
		txtProductId.setEditable(false);
		txtProductId.setFont(new Font("Serif", Font.BOLD, 12));
		txtProductId.setForeground(Color.WHITE);
		txtProductId.setText("PRODUCT ID");
		txtProductId.setHorizontalAlignment(SwingConstants.CENTER);
		txtProductId.setBackground(new Color(49, 81, 92));
		txtProductId.setBounds(10, 11, 103, 25);
		desktopPane.add(txtProductId);
		txtProductId.setColumns(10);
		
		idNum = new JTextField();
		idNum.setEditable(false);
		idNum.setBounds(118, 11, 131, 25);
		desktopPane.add(idNum);
		idNum.setColumns(10);
		
		txtPrice = new JTextField();
		txtPrice.setHorizontalAlignment(SwingConstants.CENTER);
		txtPrice.setText("PRICE");
		txtPrice.setForeground(Color.WHITE);
		txtPrice.setFont(new Font("Serif", Font.BOLD, 12));
		txtPrice.setEditable(false);
		txtPrice.setColumns(10);
		txtPrice.setBackground(new Color(49, 81, 92));
		txtPrice.setBounds(10, 45, 103, 25);
		desktopPane.add(txtPrice);
		
		price = new JTextField();
		price.setEditable(false);
		price.setColumns(10);
		price.setBounds(118, 45, 131, 25);
		desktopPane.add(price);
		
		txtProductDescription = new JTextField();
		txtProductDescription.setText("PRODUCT DESCRIPTION");
		txtProductDescription.setHorizontalAlignment(SwingConstants.CENTER);
		txtProductDescription.setForeground(Color.WHITE);
		txtProductDescription.setFont(new Font("Serif", Font.BOLD, 12));
		txtProductDescription.setEditable(false);
		txtProductDescription.setColumns(10);
		txtProductDescription.setBackground(new Color(49, 81, 92));
		txtProductDescription.setBounds(10, 79, 239, 25);
		desktopPane.add(txtProductDescription);
		
		desc = new JTextArea();
		desc.setEditable(false);
		desc.setLineWrap(true);
		desc.setColumns(10);
		desc.setBounds(10, 109, 239, 68);
		desktopPane.add(desc);
		
		txtQuantity = new JTextField();
		txtQuantity.setText("QUANTITY");
		txtQuantity.setHorizontalAlignment(SwingConstants.CENTER);
		txtQuantity.setForeground(Color.WHITE);
		txtQuantity.setFont(new Font("Serif", Font.BOLD, 12));
		txtQuantity.setEditable(false);
		txtQuantity.setColumns(10);
		txtQuantity.setBackground(new Color(49, 81, 92));
		txtQuantity.setBounds(10, 188, 103, 25);
		desktopPane.add(txtQuantity);
		
		
		qtySpinner.setBounds(118, 188, 131, 25);
		desktopPane.add(qtySpinner);
		
		
		btnAdd.setFont(new Font("Georgia", Font.BOLD, 12));
		btnAdd.setToolTipText("");
		btnAdd.setBackground(new Color(49, 81, 92));
		btnAdd.setForeground(Color.ORANGE);
		btnAdd.setBounds(30, 224, 89, 30);
		btnAdd.addActionListener(this);
		desktopPane.add(btnAdd);
		
		
		btnClear.setFont(new Font("Georgia", Font.BOLD, 12));
		btnClear.setToolTipText("");
		btnClear.setBackground(new Color(49, 81, 92));
		btnClear.setForeground(Color.ORANGE);
		btnClear.setBounds(139, 224, 89, 30);
		btnClear.addActionListener(this);
		desktopPane.add(btnClear);
		
		txtCurrentOrder = new JTextField();
		txtCurrentOrder.setText("CURRENT ORDER");
		txtCurrentOrder.setHorizontalAlignment(SwingConstants.CENTER);
		txtCurrentOrder.setForeground(Color.WHITE);
		txtCurrentOrder.setFont(new Font("Serif", Font.BOLD, 12));
		txtCurrentOrder.setEditable(false);
		txtCurrentOrder.setColumns(10);
		txtCurrentOrder.setBackground(new Color(49, 81, 92));
		txtCurrentOrder.setBounds(10, 265, 239, 25);
		desktopPane.add(txtCurrentOrder);
		
		
		order.setEditable(false);
		order.setBounds(10, 294, 239, 137);
		order.setFont(new Font("Arial",Font.PLAIN,10));
		JScrollPane sc = new JScrollPane (order,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		sc.setBounds(10, 294, 239, 137);
		desktopPane.add(sc);
		
		
		btnCheckOut.setFont(new Font("Georgia", Font.BOLD, 12));
		btnCheckOut.setToolTipText("");
		btnCheckOut.setBackground(new Color(49, 81, 92));
		btnCheckOut.setForeground(Color.ORANGE);
		btnCheckOut.setBounds(74, 442, 119, 30);
		btnCheckOut.addActionListener(this);
		desktopPane.add(btnCheckOut);
		
		JDesktopPane menu = new JDesktopPane();
		menu.setBorder(new LineBorder(SystemColor.controlHighlight, 1, true));
		menu.setBounds(20, 30, 764, 473);
		menu.setBackground(new Color(182, 221, 228));
		panel.add(menu);
		
		JLabel lblChicken = new JLabel("CHICKEN");
		lblChicken.setFont(new Font("Georgia", Font.BOLD, 12));
		lblChicken.setHorizontalAlignment(SwingConstants.CENTER);
		lblChicken.setBounds(10, 11, 99, 14);
		menu.add(lblChicken);
		
		JLabel lblPork = new JLabel("PORK");
		lblPork.setHorizontalAlignment(SwingConstants.CENTER);
		lblPork.setFont(new Font("Georgia", Font.BOLD, 12));
		lblPork.setBounds(159, 11, 99, 14);
		menu.add(lblPork);
		
		
		btnFriedChic.setFont(new Font("Georgia", Font.BOLD, 12));
		btnFriedChic.setBackground(new Color(255, 189, 76));
		btnFriedChic.setBounds(10, 30, 99, 41);
		btnFriedChic.addActionListener(this);
		menu.add(btnFriedChic);
		
		JLabel stck1 = new JLabel("Stocks");
		stck1.setBounds(30, 79, 31, 14);
		stck1.setFont(new Font("Arial",Font.PLAIN,10));
		menu.add(stck1);
		
		friedStock = new JTextField();
		friedStock.setHorizontalAlignment(SwingConstants.RIGHT);
		friedStock.setEditable(false);
		friedStock.setBounds(65, 76, 31, 20);
		menu.add(friedStock);
		friedStock.setColumns(10);
		
		
		btnRoastChic.setFont(new Font("Georgia", Font.BOLD, 12));
		btnRoastChic.setBackground(new Color(255, 189, 76));
		btnRoastChic.setBounds(10, 121, 99, 41);
		btnRoastChic.addActionListener(this);
		menu.add(btnRoastChic);
		
		JLabel stck2 = new JLabel("Stocks");
		stck2.setBounds(30, 170, 31, 14);
		stck2.setFont(new Font("Arial",Font.PLAIN,10));
		menu.add(stck2);
		
		roastStock = new JTextField();
		
		roastStock.setHorizontalAlignment(SwingConstants.RIGHT);
		roastStock.setEditable(false);
		roastStock.setColumns(10);
		roastStock.setFont(new Font("Arial",Font.PLAIN,10));
		roastStock.setBounds(65, 167, 31, 20);
		menu.add(roastStock);
		
		
		btnTinola.setFont(new Font("Georgia", Font.BOLD, 12));
		btnTinola.setBackground(new Color(255, 189, 76));
		btnTinola.setBounds(10, 209, 99, 41);
		btnTinola.addActionListener(this);
		menu.add(btnTinola);
		
		JLabel stck3 = new JLabel("Stocks");
		stck3.setBounds(30, 258, 31, 14);
		stck3.setFont(new Font("Arial",Font.PLAIN,10));
		menu.add(stck3);
		
		tinolaStock = new JTextField();
		
		tinolaStock.setHorizontalAlignment(SwingConstants.RIGHT);
		tinolaStock.setEditable(false);
		tinolaStock.setColumns(10);
		tinolaStock.setBounds(65, 255, 31, 20);
		menu.add(tinolaStock);
		
		
		btnAdoboChic.setFont(new Font("Georgia", Font.BOLD, 12));
		btnAdoboChic.setBackground(new Color(255, 189, 76));
		btnAdoboChic.setBounds(10, 295, 99, 41);
		btnAdoboChic.addActionListener(this);
		menu.add(btnAdoboChic);
		
		JLabel stck4 = new JLabel("Stocks");
		stck4.setBounds(30, 344, 31, 14);
		stck4.setFont(new Font("Arial",Font.PLAIN,10));
		menu.add(stck4);
		
		adoboStock = new JTextField();
		
		adoboStock.setHorizontalAlignment(SwingConstants.RIGHT);
		adoboStock.setEditable(false);
		adoboStock.setColumns(10);
		adoboStock.setBounds(65, 341, 31, 20);
		menu.add(adoboStock);
		
		
		btnAfritada.setFont(new Font("Georgia", Font.BOLD, 12));
		btnAfritada.setBackground(new Color(255, 189, 76));
		btnAfritada.setBounds(10, 380, 99, 41);
		btnAfritada.addActionListener(this);
		menu.add(btnAfritada);
		
		JLabel stck5 = new JLabel("Stocks");
		stck5.setBounds(30, 428, 31, 14);
		stck5.setFont(new Font("Arial",Font.PLAIN,10));
		menu.add(stck5);
		
		afritadaStock = new JTextField();
		
		afritadaStock.setHorizontalAlignment(SwingConstants.RIGHT);
		afritadaStock.setEditable(false);
		afritadaStock.setColumns(10);
		afritadaStock.setBounds(65, 425, 31, 20);
		menu.add(afritadaStock);
		
		
		btnLiempo.setFont(new Font("Georgia", Font.BOLD, 12));
		btnLiempo.setBackground(new Color(255, 189, 76));
		btnLiempo.setBounds(159, 30, 99, 41);
		btnLiempo.addActionListener(this);
		menu.add(btnLiempo);
		
		JLabel stck6 = new JLabel("Stocks");
		stck6.setBounds(179, 79, 31, 14);
		stck6.setFont(new Font("Arial",Font.PLAIN,10));
		menu.add(stck6);
		
		liempoStock = new JTextField();
		
		liempoStock.setHorizontalAlignment(SwingConstants.RIGHT);
		liempoStock.setEditable(false);
		liempoStock.setColumns(10);
		liempoStock.setBounds(214, 76, 31, 20);
		menu.add(liempoStock);
		
		
		btnPorkSini.setFont(new Font("Georgia", Font.BOLD, 12));
		btnPorkSini.setBackground(new Color(255, 189, 76));
		btnPorkSini.setBounds(159, 121, 99, 41);
		btnPorkSini.addActionListener(this);
		menu.add(btnPorkSini);
		
		JLabel stck7 = new JLabel("Stocks");
		stck7.setBounds(179, 170, 31, 14);
		stck7.setFont(new Font("Arial",Font.PLAIN,10));
		menu.add(stck7);
		
		porkSiniStock = new JTextField();
		
		porkSiniStock.setHorizontalAlignment(SwingConstants.RIGHT);
		porkSiniStock.setEditable(false);
		porkSiniStock.setColumns(10);
		porkSiniStock.setBounds(214, 167, 31, 20);
		menu.add(porkSiniStock);
		
		
		btnNilaga.setFont(new Font("Georgia", Font.BOLD, 12));
		btnNilaga.setBackground(new Color(255, 189, 76));
		btnNilaga.setBounds(159, 209, 99, 41);
		btnNilaga.addActionListener(this);
		menu.add(btnNilaga);
		
		JLabel stck8 = new JLabel("Stocks");
		stck8.setBounds(179, 258, 31, 14);
		stck8.setFont(new Font("Arial",Font.PLAIN,10));
		menu.add(stck8);
		
		nilagaStock = new JTextField();
		
		nilagaStock.setHorizontalAlignment(SwingConstants.RIGHT);
		nilagaStock.setEditable(false);
		nilagaStock.setColumns(10);
		nilagaStock.setBounds(214, 255, 31, 20);
		menu.add(nilagaStock);
		
		
		btnLechon.setFont(new Font("Georgia", Font.BOLD, 12));
		btnLechon.setBackground(new Color(255, 189, 76));
		btnLechon.setBounds(159, 295, 99, 41);
		btnLechon.addActionListener(this);
		menu.add(btnLechon);
		
		JLabel stck9 = new JLabel("Stocks");
		stck9.setBounds(179, 344, 31, 14);
		stck9.setFont(new Font("Arial",Font.PLAIN,10));
		menu.add(stck9);
		
		lechonStock = new JTextField();
		
		lechonStock.setHorizontalAlignment(SwingConstants.RIGHT);
		lechonStock.setEditable(false);
		lechonStock.setColumns(10);
		lechonStock.setBounds(214, 341, 31, 20);
		menu.add(lechonStock);
		
		
		btnPorkAdobo.setFont(new Font("Georgia", Font.BOLD, 12));
		btnPorkAdobo.setBackground(new Color(255, 189, 76));
		btnPorkAdobo.setBounds(159, 380, 99, 41);
		btnPorkAdobo.addActionListener(this);
		menu.add(btnPorkAdobo);
		
		JLabel stck10 = new JLabel("Stocks");
		stck10.setBounds(179, 428, 31, 14);
		stck10.setFont(new Font("Arial",Font.PLAIN,10));
		menu.add(stck10);
		
		porkAdboStock = new JTextField();
		
		porkAdboStock.setHorizontalAlignment(SwingConstants.RIGHT);
		porkAdboStock.setEditable(false);
		porkAdboStock.setColumns(10);
		porkAdboStock.setBounds(214, 425, 31, 20);
		menu.add(porkAdboStock);
		
		JLabel lblBeef = new JLabel("BEEF");
		lblBeef.setHorizontalAlignment(SwingConstants.CENTER);
		lblBeef.setFont(new Font("Georgia", Font.BOLD, 12));
		lblBeef.setBounds(313, 11, 99, 14);
		menu.add(lblBeef);
		
		
		btnTapa.setFont(new Font("Georgia", Font.BOLD, 12));
		btnTapa.setBackground(new Color(255, 189, 76));
		btnTapa.setBounds(313, 30, 99, 41);
		btnTapa.addActionListener(this);
		menu.add(btnTapa);
		
		JLabel stck11 = new JLabel("Stocks");
		stck11.setBounds(333, 79, 31, 14);
		stck11.setFont(new Font("Arial",Font.PLAIN,10));
		menu.add(stck11);
		
		tapaStock = new JTextField();
		
		tapaStock.setHorizontalAlignment(SwingConstants.RIGHT);
		tapaStock.setEditable(false);
		tapaStock.setColumns(10);
		tapaStock.setBounds(368, 76, 31, 20);
		menu.add(tapaStock);
		
		
		btnBeefCald.setFont(new Font("Georgia", Font.BOLD, 12));
		btnBeefCald.setBackground(new Color(255, 189, 76));
		btnBeefCald.setBounds(313, 121, 99, 41);
		btnBeefCald.addActionListener(this);
		menu.add(btnBeefCald);
		
		JLabel stck12 = new JLabel("Stocks");
		stck12.setBounds(333, 170, 31, 14);
		stck12.setFont(new Font("Arial",Font.PLAIN,10));
		menu.add(stck12);
		
		caldStock = new JTextField();
		
		caldStock.setHorizontalAlignment(SwingConstants.RIGHT);
		caldStock.setEditable(false);
		caldStock.setColumns(10);
		caldStock.setBounds(368, 167, 31, 20);
		menu.add(caldStock);
		
		
		btnSteak.setFont(new Font("Georgia", Font.BOLD, 12));
		btnSteak.setBackground(new Color(255, 189, 76));
		btnSteak.setBounds(313, 209, 99, 41);
		btnSteak.addActionListener(this);
		menu.add(btnSteak);
		
		JLabel stck13 = new JLabel("Stocks");
		stck13.setBounds(333, 258, 31, 14);
		stck13.setFont(new Font("Arial",Font.PLAIN,10));
		menu.add(stck13);
		
		steakStock = new JTextField();
		
		steakStock.setHorizontalAlignment(SwingConstants.RIGHT);
		steakStock.setEditable(false);
		steakStock.setColumns(10);
		steakStock.setBounds(368, 255, 31, 20);
		menu.add(steakStock);
		
		
		btnGroundBf.setFont(new Font("Georgia", Font.BOLD, 12));
		btnGroundBf.setBackground(new Color(255, 189, 76));
		btnGroundBf.setBounds(313, 295, 99, 41);
		btnGroundBf.addActionListener(this);
		menu.add(btnGroundBf);
		
		JLabel stck14 = new JLabel("Stocks");
		stck14.setBounds(333, 344, 31, 14);
		stck14.setFont(new Font("Arial",Font.PLAIN,10));
		menu.add(stck14);
		
		groundStock = new JTextField();
		
		groundStock.setHorizontalAlignment(SwingConstants.RIGHT);
		groundStock.setEditable(false);
		groundStock.setColumns(10);
		groundStock.setBounds(368, 341, 31, 20);
		menu.add(groundStock);
		
		
		btnBulalo.setFont(new Font("Georgia", Font.BOLD, 12));
		btnBulalo.setBackground(new Color(255, 189, 76));
		btnBulalo.setBounds(313, 380, 99, 41);
		btnBulalo.addActionListener(this);
		menu.add(btnBulalo);
		
		JLabel stck15 = new JLabel("Stocks");
		stck15.setBounds(333, 428, 31, 14);
		stck15.setFont(new Font("Arial",Font.PLAIN,10));
		menu.add(stck15);
		
		bulaloStock = new JTextField();
		
		bulaloStock.setHorizontalAlignment(SwingConstants.RIGHT);
		bulaloStock.setEditable(false);
		bulaloStock.setColumns(10);
		bulaloStock.setBounds(368, 425, 31, 20);
		menu.add(bulaloStock);
		
		JLabel lblSeafd = new JLabel("SEAFOOD");
		lblSeafd.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeafd.setFont(new Font("Georgia", Font.BOLD, 12));
		lblSeafd.setBounds(466, 11, 99, 14);
		menu.add(lblSeafd);
		
		btnTilapia.setFont(new Font("Georgia", Font.BOLD, 12));
		btnTilapia.setBackground(new Color(255, 189, 76));
		btnTilapia.setBounds(466, 30, 99, 41);
		btnTilapia.addActionListener(this);
		menu.add(btnTilapia);
		
		JLabel stck16 = new JLabel("Stocks");
		stck16.setBounds(486, 79, 31, 14);
		stck16.setFont(new Font("Arial",Font.PLAIN,10));
		menu.add(stck16);
		
		tilapiaStock = new JTextField();
		
		tilapiaStock.setHorizontalAlignment(SwingConstants.RIGHT);
		tilapiaStock.setEditable(false);
		tilapiaStock.setColumns(10);
		tilapiaStock.setBounds(521, 76, 31, 20);
		menu.add(tilapiaStock);
		
		btnFillet.setFont(new Font("Georgia", Font.BOLD, 12));
		btnFillet.setBackground(new Color(255, 189, 76));
		btnFillet.setBounds(466, 121, 99, 41);
		btnFillet.addActionListener(this);
		menu.add(btnFillet);
		
		JLabel stck17 = new JLabel("Stocks");
		stck17.setBounds(486, 170, 31, 14);
		stck17.setFont(new Font("Arial",Font.PLAIN,10));
		menu.add(stck17);
		
		filletStock = new JTextField();
		
		filletStock.setHorizontalAlignment(SwingConstants.RIGHT);
		filletStock.setEditable(false);
		filletStock.setColumns(10);
		filletStock.setBounds(521, 167, 31, 20);
		menu.add(filletStock);
		
		
		btnDinaing.setFont(new Font("Georgia", Font.BOLD, 11));
		btnDinaing.setBackground(new Color(255, 189, 76));
		btnDinaing.setBounds(466, 209, 99, 41);
		btnDinaing.addActionListener(this);
		menu.add(btnDinaing);
		
		JLabel stck18 = new JLabel("Stocks");
		stck18.setBounds(486, 258, 31, 14);
		stck18.setFont(new Font("Arial",Font.PLAIN,10));
		menu.add(stck18);
		
		dinaingStock = new JTextField();
		
		dinaingStock.setHorizontalAlignment(SwingConstants.RIGHT);
		dinaingStock.setEditable(false);
		dinaingStock.setColumns(10);
		dinaingStock.setBounds(521, 255, 31, 20);
		menu.add(dinaingStock);
		
		
		btnHipon.setFont(new Font("Georgia", Font.BOLD, 11));
		btnHipon.setBackground(new Color(255, 189, 76));
		btnHipon.setBounds(466, 295, 99, 41);
		btnHipon.addActionListener(this);
		menu.add(btnHipon);
		
		JLabel stck19 = new JLabel("Stocks");
		stck19.setBounds(486, 344, 31, 14);
		stck19.setFont(new Font("Arial",Font.PLAIN,10));
		menu.add(stck19);
		
		hiponStock = new JTextField();
		
		hiponStock.setHorizontalAlignment(SwingConstants.RIGHT);
		hiponStock.setEditable(false);
		hiponStock.setColumns(10);
		hiponStock.setBounds(521, 341, 31, 20);
		menu.add(hiponStock);
		
		
		btnTempura.setFont(new Font("Georgia", Font.BOLD, 12));
		btnTempura.setBackground(new Color(255, 189, 76));
		btnTempura.setBounds(466, 380, 99, 41);
		btnTempura.addActionListener(this);
		menu.add(btnTempura);
		
		JLabel stck20 = new JLabel("Stocks");
		stck20.setBounds(486, 428, 31, 14);
		stck20.setFont(new Font("Arial",Font.PLAIN,10));
		menu.add(stck20);
		
		tempuraStock = new JTextField();
		
		tempuraStock.setHorizontalAlignment(SwingConstants.RIGHT);
		tempuraStock.setEditable(false);
		tempuraStock.setColumns(10);
		tempuraStock.setBounds(521, 425, 31, 20);
		menu.add(tempuraStock);
		
		JLabel lblDrinks = new JLabel("DRINKS");
		lblDrinks.setHorizontalAlignment(SwingConstants.CENTER);
		lblDrinks.setFont(new Font("Georgia", Font.BOLD, 12));
		lblDrinks.setBounds(620, 11, 99, 14);
		menu.add(lblDrinks);
		
		btnBeer.setFont(new Font("Georgia", Font.BOLD, 12));
		btnBeer.setBackground(new Color(255, 189, 76));
		btnBeer.setBounds(620, 380, 99, 41);
		btnBeer.addActionListener(this);
		menu.add(btnBeer);
		
		JLabel stck25 = new JLabel("Stocks");
		stck25.setBounds(640, 428, 31, 14);
		stck25.setFont(new Font("Arial",Font.PLAIN,10));
		menu.add(stck25);
		
		beerStock = new JTextField();
		
		beerStock.setHorizontalAlignment(SwingConstants.RIGHT);
		beerStock.setEditable(false);
		beerStock.setColumns(10);
		beerStock.setBounds(675, 425, 31, 20);
		menu.add(beerStock);
		
		
		btnCoke.setFont(new Font("Georgia", Font.BOLD, 12));
		btnCoke.setBackground(new Color(255, 189, 76));
		btnCoke.setBounds(620, 30, 99, 41);
		btnCoke.addActionListener(this);
		menu.add(btnCoke);
		
		JLabel stck21 = new JLabel("Stocks");
		stck21.setBounds(640, 79, 31, 14);
		stck21.setFont(new Font("Arial",Font.PLAIN,10));
		menu.add(stck21);
		
		coccStock = new JTextField();
		
		coccStock.setHorizontalAlignment(SwingConstants.RIGHT);
		coccStock.setEditable(false);
		coccStock.setColumns(10);
		coccStock.setBounds(675, 76, 31, 20);
		menu.add(coccStock);
		
		
		btnSprite.setFont(new Font("Georgia", Font.BOLD, 12));
		btnSprite.setBackground(new Color(255, 189, 76));
		btnSprite.setBounds(620, 121, 99, 41);
		btnSprite.addActionListener(this);
		menu.add(btnSprite);
		
		JLabel stck22 = new JLabel("Stocks");
		stck22.setBounds(640, 170, 31, 14);
		stck22.setFont(new Font("Arial",Font.PLAIN,10));
		menu.add(stck22);
		
		spriteStock = new JTextField();
		
		spriteStock.setHorizontalAlignment(SwingConstants.RIGHT);
		spriteStock.setEditable(false);
		spriteStock.setColumns(10);
		spriteStock.setBounds(675, 167, 31, 20);
		menu.add(spriteStock);
		
		
		btnPepsi.setFont(new Font("Georgia", Font.BOLD, 12));
		btnPepsi.setBackground(new Color(255, 189, 76));
		btnPepsi.setBounds(620, 209, 99, 41);
		btnPepsi.addActionListener(this);
		menu.add(btnPepsi);
		
		JLabel stck23 = new JLabel("Stocks");
		stck23.setBounds(640, 258, 31, 14);
		stck23.setFont(new Font("Arial",Font.PLAIN,10));
		menu.add(stck23);
		
		pepsiStock = new JTextField();
		
		pepsiStock.setHorizontalAlignment(SwingConstants.RIGHT);
		pepsiStock.setEditable(false);
		pepsiStock.setColumns(10);
		pepsiStock.setBounds(675, 255, 31, 20);
		menu.add(pepsiStock);
		
		
		btnRoyal.setFont(new Font("Georgia", Font.BOLD, 12));
		btnRoyal.setBackground(new Color(255, 189, 76));
		btnRoyal.setBounds(620, 295, 99, 41);
		btnRoyal.addActionListener(this);
		menu.add(btnRoyal);
		
		JLabel stck24 = new JLabel("Stocks");
		stck24.setBounds(640, 344, 31, 14);
		stck24.setFont(new Font("Arial",Font.PLAIN,10));
		menu.add(stck24);
		
		royalStock = new JTextField();
		
		royalStock.setHorizontalAlignment(SwingConstants.RIGHT);
		royalStock.setEditable(false);
		royalStock.setColumns(10);
		royalStock.setFont(new Font("Arial",Font.PLAIN,10));
		royalStock.setBounds(675, 341, 31, 20);
		menu.add(royalStock);
		
		set_stock();
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{String code = new String();
		if(e.getSource() == btnFriedChic)
		{
			code = "'C1'";
		}
		else if(e.getSource() == btnRoastChic)
		{
			code = "'C2'";
		}
		else if(e.getSource() == btnTinola)
		{
			code = "'C3'";
		}
		else if(e.getSource() == btnAdoboChic)
		{
			code = "'C4'";
		}
		else if(e.getSource() == btnAfritada)
		{
			code = "'C5'";
		}
		else if(e.getSource() == btnLiempo)
		{
			code = "'P1'";
		}
		else if(e.getSource() == btnPorkSini)
		{
			code = "'P2'";
		}
		else if(e.getSource() == btnNilaga)
		{
			code = "'P3'";
		}
		else if(e.getSource() == btnLechon)
		{
			code = "'P4'";
		}
		else if(e.getSource() == btnPorkAdobo)
		{
			code = "'P5'";
		}
		else if(e.getSource() == btnTapa)
		{
			code = "'B1'";
		}
		else if(e.getSource() == btnBeefCald)
		{
			code = "'B2'";
		}
		else if(e.getSource() == btnSteak)
		{
			code = "'B3'";
		}
		else if(e.getSource() == btnGroundBf)
		{
			code = "'B4'";
		}
		else if(e.getSource() == btnBulalo)
		{
			code = "'B5'";
		}
		else if(e.getSource() == btnTilapia)
		{
			code = "'S1'";
		}
		else if(e.getSource() == btnFillet)
		{
			code = "'S2'";
		}
		else if(e.getSource() == btnDinaing)
		{
			code = "'S3'";
		}
		else if(e.getSource() == btnHipon)
		{
			code = "'S4'";
		}
		else if(e.getSource() == btnTempura)
		{
			code = "'S5'";
		}
		else if(e.getSource() == btnCoke)
		{
			code = "'D2'";
		}
		else if(e.getSource() == btnSprite)
		{
			code = "'D3'";
		}
		else if(e.getSource() == btnPepsi)
		{
			code = "'D4'";
		}
		else if(e.getSource() == btnRoyal)
		{
			code = "'D5'";
		}
		else if(e.getSource() == btnBeer)
		{
			code = "'D1'";
		}
		else if(e.getSource()== btnAdd)
		{
			code = "AA";
		}
		else if(e.getSource()==btnClear)
		{
			code = "XX";
		}
		else if(e.getSource()==btnCheckOut)
		{
			code = "CC";
		}
		
		if(code.equals("AA"))
		{
			add_order();
			order.setText(head.concat(show_list()));
		}
		else if(code.equals("XX"))
		{
			int check = authorize();
			if(check==1)
			{
				clear_order();
				show = head;
				show = show.concat(show_list());
				order.setText(head.concat(show_list()));
			}
		}
		else if(code.equals("CC"))
		{
			check_out();
		}
		else
		{
			display_dish(code);
		}
		
		
	}
	public static void main(String[] args) {
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.set_stock();
	}
	
	public void check_out()
	{
		checkout.main(new String[0]);
		set_stock();
	}
	
	public void clear_order()
	{
		Object[] options = {"Delete an Order", "Clear All Order", "Cancel"};
	    JPanel btns = new JPanel(new GridLayout(0, 1, 2, 2));
	    
	    int res = JOptionPane.showOptionDialog(null,btns,"Delete or Clear", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null, options , null);
	   
	    if(res == JOptionPane.YES_OPTION)
	    {
	    	String cd = JOptionPane.showInputDialog("Input Product ID to delete");
	    	if(cd.length() == 2)
	    	{
	    		Statement statement = null;
	    		
	    		try {
	    			Connection connection = DriverManager.getConnection(jdbcUrl);
	    			statement = connection.createStatement();
	    			String sql = "DELETE FROM ORDER_LIST WHERE PROD_ID='"+cd+"'";
	    			statement.executeUpdate(sql);
	    			JOptionPane.showMessageDialog(null, "Successfully deleted "+cd);
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
	    }
	    else if(res == JOptionPane.NO_OPTION)
	    {
	    	Statement statement = null;
	    	try {
    			Connection connection = DriverManager.getConnection(jdbcUrl);
    			statement = connection.createStatement();
    			String sql = "DELETE FROM ORDER_LIST";
    			statement.executeUpdate(sql);
    			JOptionPane.showMessageDialog(null, "Successfully Cleared");
    			head = "ID\tPrice\tQuantity\tTotal\n";
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
	    
	}
	
	public String show_list()
	{
		String id = new String();
		String pr =  new String();
		String quant = new String();
		String npr = new String();
		String orderl = "";
		Statement statement = null;
		ResultSet result = null;
		
		try {
			Connection connection = DriverManager.getConnection(jdbcUrl);
			String sql = "SELECT * FROM ORDER_LIST";
			statement = connection.createStatement();
			result = statement.executeQuery(sql);
			
			while(result.next())
			{
				id = result.getString("PROD_ID");
				pr = result.getString("INIT_PRICE");
				quant = result.getString("QUANTITY");
				npr = result.getString("LAST_PRICE");
				
				orderl =orderl + id + "\tPhp " + pr + "\t" + quant + "\tPhp" + npr +"\n";
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
		return orderl;
	}
	
	
	public void add_order()
	{	String code = idNum.getText();
		quant = (int) qtySpinner.getValue();
		float price = Float.parseFloat(dbprice);
		float subtotal = price * quant;
		Statement statement = null;
		int check = Integer.signum(quant);
		if( check != 1 || quant == 0 )
		{
			JOptionPane.showMessageDialog(null,"Negative or Zero quantity","ERROR", JOptionPane.ERROR_MESSAGE);
		}
		else if(quant < stockz || quant == stockz)
		{
			int a = stockz - quant;
			try {
				Connection connection = DriverManager.getConnection(jdbcUrl);
				statement = connection.createStatement();
				String sql = "INSERT INTO ORDER_LIST(PROD_ID,PRODUCT,INIT_PRICE,QUANTITY,LAST_PRICE,STOCK_UPDATE) " + "VALUES('"+code+"','"+dbdish+"',"+dbprice+","+quant+","+subtotal+","+a+")";
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
		else
		{
			JOptionPane.showMessageDialog(null,"Not enough stock for this purchase","ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void display_dish(String code)
	{
		Statement statement = null;
		ResultSet result = null;
		
		try {
			Connection connection = DriverManager.getConnection(jdbcUrl);
			String sql = "SELECT * FROM ALL_PRODUCTS WHERE ID=" + code;
			statement = connection.createStatement();
			result = statement.executeQuery(sql);
			
			dbdish = result.getString("Product_Name");
			dbid = result.getString("ID");
			dbprice = result.getString("Product_Price");
			dbdesc = result.getString("Description");
			stockz = Integer.parseInt(result.getString("Stock"));
 			
			idNum.setText(dbid);
			price.setText(dbprice);
			desc.setText(dbdesc);
			qtySpinner.setValue(1);
			
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
	}
	
	public String StockPOS(String dishname)
	{String stock = new String();
	Statement statement = null;
	ResultSet result = null;
	
	try {
		Connection connection = DriverManager.getConnection(jdbcUrl);
		String sql = "SELECT ID,Stock FROM ALL_PRODUCTS WHERE ID=" + dishname;
		statement = connection.createStatement();
		result = statement.executeQuery(sql);
		
			stock = result.getString("Stock");
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
		return stock;	
	}
	
	public int authorize()
	{
		JPanel panell = new JPanel(new BorderLayout(5, 5));

	    JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
	    label.add(new JLabel("Username", SwingConstants.RIGHT));
	    label.add(new JLabel("Password", SwingConstants.RIGHT));
	    panell.add(label, BorderLayout.WEST);

	    JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
	    JTextField username = new JTextField();
	    controls.add(username);
	    JPasswordField password = new JPasswordField();
	    controls.add(password);
	    panell.add(controls, BorderLayout.CENTER);
		JOptionPane.showMessageDialog(tabbedPane, panell, "Admin/Manager Access Required", JOptionPane.QUESTION_MESSAGE);
		String dbuser = new String();
		String dbpass =  new String();
		String usera = username.getText();
		String passw = password.getText();
		Statement statement = null;
		ResultSet result = null;
		
		int a = 0;
		try {
			Connection connection = DriverManager.getConnection(jdbcUrl);
			String sql = "SELECT * FROM USERS";
			
			statement = connection.createStatement();
			result = statement.executeQuery(sql);
			
			while(result.next())
			{
				dbuser = result.getString("USER_NAME");
				dbpass = result.getString("PASSWORD");
				
				if(usera.trim().equals(dbuser) && passw.trim().equals(dbpass)&&(usera.trim().equals("admin")||usera.trim().equals("manager")))
				{
					JOptionPane.showMessageDialog(tabbedPane, "Granted Access!");
					a = 1;
				}
			}
			if(a==0)
				JOptionPane.showMessageDialog(tabbedPane, "Unauthorized User!","ERROR",JOptionPane.ERROR_MESSAGE);
			
		} catch (SQLException e1) 
		{
			JOptionPane.showMessageDialog(tabbedPane, "Unauthorized User!","ERROR",JOptionPane.ERROR_MESSAGE);
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
		return a;
	}
	
	public void inv_list()
	{
		String id = new String();
		String cat =  new String();
		String name = new String();
		String price = new String();
		String stock = new String();
		String desc = new String();
		Statement statement = null;
		ResultSet result = null;
		
		try {
			Connection connection = DriverManager.getConnection(jdbcUrl);
			String sql = "SELECT * FROM ALL_PRODUCTS";
			statement = connection.createStatement();
			result = statement.executeQuery(sql);
			
			while(result.next())
			{
				id = result.getString("ID");
				cat = result.getString("Category");
				name = result.getString("Product_Name");
				price = result.getString("Product_Price");
				stock = result.getString("Stock");
				desc = result.getString("Description");
				
				String data[] = {id,cat,name,price,stock,desc};  			
				tbl.addRow(data);
				
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
	}
	
	public void set_stock()
	{
		order.setText(head.concat(show_list()));
		int a;
		friedStock.setText(StockPOS("'C1'"));
		a = Integer.parseInt(friedStock.getText());
		if(a==0)
			btnFriedChic.setEnabled(false);
		
		roastStock.setText(StockPOS("'C2'"));
		a = Integer.parseInt(roastStock.getText());
		if(a==0)
			btnRoastChic.setEnabled(false);
		
		tinolaStock.setText(StockPOS("'C3'"));
		a = Integer.parseInt(tinolaStock.getText());
		if(a==0)
			btnTinola.setEnabled(false);
		
		adoboStock.setText(StockPOS("'C4'"));
		a = Integer.parseInt(adoboStock.getText());
		if(a==0)
			btnAdoboChic.setEnabled(false);
		
		afritadaStock.setText(StockPOS("'C5'"));
		a = Integer.parseInt(afritadaStock.getText());
		if(a==0)
			btnAfritada.setEnabled(false);
		
		liempoStock.setText(StockPOS("'P1'"));
		a = Integer.parseInt(liempoStock.getText());
		if(a==0)
			btnLiempo.setEnabled(false);
		
		porkSiniStock.setText(StockPOS("'P2'"));
		a = Integer.parseInt(porkSiniStock.getText());
		if(a==0)
			btnPorkSini.setEnabled(false);
		
		nilagaStock.setText(StockPOS("'P3'"));
		a = Integer.parseInt(nilagaStock.getText());
		if(a==0)
			btnNilaga.setEnabled(false);
		
		lechonStock.setText(StockPOS("'P4'"));
		a = Integer.parseInt(lechonStock.getText());
		if(a==0)
			btnLechon.setEnabled(false);
		
		porkAdboStock.setText(StockPOS("'P5'"));
		a = Integer.parseInt(porkAdboStock.getText());
		if(a==0)
			btnPorkAdobo.setEnabled(false);
		
		tapaStock.setText(StockPOS("'B1'"));
		a = Integer.parseInt(tapaStock.getText());
		if(a==0)
			btnTapa.setEnabled(false);
		
		caldStock.setText(StockPOS("'B2'"));
		a = Integer.parseInt(caldStock.getText());
		if(a==0)
			btnBeefCald.setEnabled(false);
		
		steakStock.setText(StockPOS("'B3'"));
		a = Integer.parseInt(steakStock.getText());
		if(a==0)
			btnSteak.setEnabled(false);
		
		groundStock.setText(StockPOS("'B4'"));
		a = Integer.parseInt(groundStock.getText());
		if(a==0)
			btnGroundBf.setEnabled(false);
		
		bulaloStock.setText(StockPOS("'B5'"));
		a = Integer.parseInt(bulaloStock.getText());
		if(a==0)
			btnBulalo.setEnabled(false);
		
		tilapiaStock.setText(StockPOS("'S1'"));
		a = Integer.parseInt(tilapiaStock.getText());
		if(a==0)
			btnTilapia.setEnabled(false);
		
		filletStock.setText(StockPOS("'S2'"));
		a = Integer.parseInt(filletStock.getText());
		if(a==0)
			btnFillet.setEnabled(false);
		
		dinaingStock.setText(StockPOS("'S3'"));
		a = Integer.parseInt(dinaingStock.getText());
		if(a==0)
			btnDinaing.setEnabled(false);
		
		hiponStock.setText(StockPOS("'S4'"));
		a = Integer.parseInt(hiponStock.getText());
		if(a==0)
			btnHipon.setEnabled(false);
		
		tempuraStock.setText(StockPOS("'S5'"));
		a = Integer.parseInt(tempuraStock.getText());
		if(a==0)
			btnTempura.setEnabled(false);
		
		beerStock.setText(StockPOS("'D1'"));
		a = Integer.parseInt(beerStock.getText());
		if(a==0)
			btnBeer.setEnabled(false);
		
		coccStock.setText(StockPOS("'D2'"));
		a = Integer.parseInt(coccStock.getText());
		if(a==0)
			btnCoke.setEnabled(false);
		
		spriteStock.setText(StockPOS("'D3'"));
		a = Integer.parseInt(spriteStock.getText());
		if(a==0)
			btnSprite.setEnabled(false);
		
		pepsiStock.setText(StockPOS("'D4'"));
		a = Integer.parseInt(pepsiStock.getText());
		if(a==0)
			btnPepsi.setEnabled(false);
		
		royalStock.setText(StockPOS("'D5'"));
		a = Integer.parseInt(royalStock.getText());
		if(a==0)
			btnRoyal.setEnabled(false);
		
	}
}
