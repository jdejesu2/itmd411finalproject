import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ticketsGUI implements ActionListener {

	// class level member objects

	Dao dao = new Dao(); // for CRUD operations
	String chkIfAdmin = null;
	private JFrame mainFrame;

	JScrollPane sp = null;

	// Main menu object items
	private JMenu mnuFile = new JMenu("File");
	private JMenu mnuAdmin = new JMenu("Admin");
	private JMenu mnuTickets = new JMenu("Tickets");

	/* add any more Main menu object items below */

	// Sub menu item objects for all Main menu item objects
	JMenuItem mnuItemExit;
	JMenuItem mnuItemUpdate;
	JMenuItem mnuItemDelete;
	JMenuItem mnuItemOpenTicket;
	JMenuItem mnuItemViewTicket;

	/* add any more Sub object items below */
	
	String role;

	// constructor
	public ticketsGUI(String verifyRole) {

		chkIfAdmin = verifyRole; 
		ImageIcon icon = new ImageIcon(ticketsGUI.class.getResource("/happy.jpg"));
		JOptionPane.showMessageDialog(null, "Welcome " + verifyRole, "login", JOptionPane.INFORMATION_MESSAGE, icon);
		
		role = verifyRole; 
		
		if (chkIfAdmin.equals("Admin")) {

			//dao.createTables(); // fire up table creations (tickets / user
								// tables)
		/*
		 * else do something else if you like
		 *
		 */

		createMenu();
		prepareGUI();
		} 
		
		
		else {
			createMenu2();
			prepareGUI2();
			ImageIcon icon2 = new ImageIcon(ticketsGUI.class.getResource("/glasses.jpg"));
			JOptionPane.showMessageDialog(null, "not admin", null, JOptionPane.INFORMATION_MESSAGE, icon2);
		}
		
	}

	private void createMenu() {

		/* Initialize sub menu items **************************************/

		// initialize sub menu item for File main menu
		mnuItemExit = new JMenuItem("Exit");
		// add to File main menu item
		mnuFile.add(mnuItemExit);

		// initialize first sub menu items for Admin main menu
		mnuItemUpdate = new JMenuItem("Update Ticket");
		// add to Admin main menu item
		mnuAdmin.add(mnuItemUpdate);

		// initialize second sub menu items for Admin main menu
		mnuItemDelete = new JMenuItem("Delete Ticket");
		// add to Admin main menu item
		mnuAdmin.add(mnuItemDelete);

		// initialize first sub menu item for Tickets main menu
		mnuItemOpenTicket = new JMenuItem("Open Ticket");
		// add to Ticket Main menu item
		mnuTickets.add(mnuItemOpenTicket);

		// initialize second sub menu item for Tickets main menu
		mnuItemViewTicket = new JMenuItem("View Ticket");
		// add to Ticket Main menu item
		mnuTickets.add(mnuItemViewTicket);

		// initialize any more desired sub menu items below

		/* Add action listeners for each desired menu item *************/
		mnuItemExit.addActionListener(this);
		mnuItemUpdate.addActionListener(this);
		mnuItemDelete.addActionListener(this);
		mnuItemOpenTicket.addActionListener(this);
		mnuItemViewTicket.addActionListener(this);

		// add any more listeners for any additional sub menu items if desired

	}

	private void prepareGUI() {
		// initialize frame object
		mainFrame = new JFrame("Tickets");

		// create jmenu bar
		JMenuBar bar = new JMenuBar();
		bar.add(mnuFile); // add main menu items in order, to JMenuBar
		bar.add(mnuAdmin);
		bar.add(mnuTickets);
		// add menu bar components to frame
		mainFrame.setJMenuBar(bar);

		mainFrame.addWindowListener(new WindowAdapter() {
			// define a window close operation
			public void windowClosing(WindowEvent wE) {
				System.exit(0);
			}
		});
		// set frame options
		mainFrame.setSize(400, 400);
		mainFrame.getContentPane().setBackground(Color.LIGHT_GRAY);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
	}

	/*
	 * action listener fires up items clicked on from sub menus with one action
	 * performed event handler!
	 */
	
	private void createMenu2() {

		/* Initialize sub menu items **************************************/

		// initialize sub menu item for File main menu
		mnuItemExit = new JMenuItem("Exit");
		// add to File main menu item
		mnuFile.add(mnuItemExit);

		//can not update, delete, view ANY ticket
		//can open tickets, view OWN tickets, and refresh table

		// initialize first sub menu item for Tickets main menu
		mnuItemOpenTicket = new JMenuItem("Open Ticket");
		// add to Ticket Main menu item
		mnuTickets.add(mnuItemOpenTicket);

		
		
		
		

		//NEED TO CREATE TO VIEW OWN TICKET FOR USER
		//DO WE NEED IT FOR ADMIN TOO??
		
		
		// View my tickets menu item
		//mnuItmMyTckt = new JMenuItem("My Tickets");
		//mnuTickets.add(mnuItmMyTckt);
		
		// Refresh the table of tickets
		//mnuItmRfrsh = new JMenuItem("Refresh Ticket Table");
		//mnuExtr.add(mnuItmRfrsh);

		// initialize any more desired sub menu items below

		/* Add action listeners for each desired menu item *************/
		mnuItemExit.addActionListener(this);
		mnuItemOpenTicket.addActionListener(this);
		//NEED OWN TICKET
		//NEED REFRESH?

		// add any more listeners for any additional sub menu items if desired

		
		
		
		
		
		
	}
	
	
	
	private void prepareGUI2() {
		// initialize frame object
		mainFrame = new JFrame("User-Tickets");

		// create jmenu bar
		JMenuBar bar = new JMenuBar();
		bar.add(mnuFile); // add main menu items in order, to JMenuBar
		bar.add(mnuTickets);
		// add menu bar components to frame
		mainFrame.setJMenuBar(bar);

		mainFrame.addWindowListener(new WindowAdapter() {
			// define a window close operation
			public void windowClosing(WindowEvent wE) {
				System.exit(0);
			}
		});
		// set frame options
		mainFrame.setSize(400, 400);
		mainFrame.getContentPane().setBackground(Color.LIGHT_GRAY);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		// implement actions for sub menu items
		if (e.getSource() == mnuItemExit) {
			System.exit(0);
		} else if (e.getSource() == mnuItemOpenTicket) {

			try {

				// get ticket information
				//String ticketName = JOptionPane.showInputDialog(null, "Enter your name");
				//ImageIcon icon = new ImageIcon(ticketsGUI.class.getResource("/happy.jpg"));
				String ticketDesc = JOptionPane.showInputDialog(null, "Enter a ticket description");
				
				// insert ticket information to database

				Statement statement = Dao.getConnection().createStatement();
 
				if ((ticketDesc != null) && (ticketDesc.length() > 0))
				{
					int result = statement
							.executeUpdate("Insert into jjw1_tickets(ticket_issuer, ticket_description) values(" + " '"
									+ role + "','" + ticketDesc + "')", Statement.RETURN_GENERATED_KEYS);
					
					// retrieve ticket id number newly auto generated upon record insertion
					ResultSet resultSet = null;
					resultSet = statement.getGeneratedKeys();
					int id = 0;
					if (resultSet.next()) {
						id = resultSet.getInt(1); // retrieve first field in table
					}
					// display results if successful or not to console / dialog box
					if (result != 0) {
						System.out.println("Ticket ID : " + id + " created successfully!!!");
						ImageIcon icon2 = new ImageIcon(ticketsGUI.class.getResource("/wow.jpg"));
						JOptionPane.showMessageDialog(null, "Ticket id: " + id + " created", null , JOptionPane.INFORMATION_MESSAGE, icon2);
					} else {

						System.out.println("Ticket cannot be created!!!");
					}
				}
				else {
					ImageIcon icon3 = new ImageIcon(ticketsGUI.class.getResource("/question.jpg"));
					JOptionPane.showMessageDialog(null, "Action has been cancelled!", null, JOptionPane.INFORMATION_MESSAGE, icon3);
				}

			} catch (SQLException ex) {
 				ex.printStackTrace();
			}
		} else if (e.getSource() == mnuItemViewTicket) {

			// retrieve ticket information for viewing in JTable

			try {

				Statement statement = Dao.getConnection().createStatement();

				ResultSet results = statement.executeQuery("SELECT * FROM jjw1_tickets");

				// Use JTable built in functionality to build a table model and
				// display the table model off your result set!!!
				JTable jt = new JTable(ticketsJTable.buildTableModel(results));

				jt.setBounds(30, 40, 200, 300);
				sp = new JScrollPane(jt);
				mainFrame.add(sp);
				mainFrame.setVisible(true); // refreshes or repaints frame on
											// screen
				statement.close();  // close connections!!!

			} catch (SQLException e1) {
					e1.printStackTrace();
			}
		}   /*
			 * continue implementing any other desired sub menu items (like for
			 * update and delete sub menus for example) with similar syntax &
			 * logic as shown above*
			 */
		/*else if (e.getSource() == mnuItemUpdate) {

			try {

				// get ticket information
				Statement statement = Dao.getConnection().createStatement();
				
				String updateTicketid = JOptionPane.showInputDialog(null, "Enter the ticket number you want to update");
				if ((updateTicketid != null) && (updateTicketid.length() > 0))
				{
					
					String sql = "Select ticket_id from tickets.jjw1_tickets" ;
					 ResultSet result = statement.executeQuery(sql);
					 
					 //System.out.println(result);
					//int result = statement
							//.executeUpdate("select ticket_id from tickets.jjw1_tickets where ticket_id = "+ updateTicketid);
					
					 
					 
					 while(result.next()){
				         //Retrieve by column name
				         String rs  = result.getString("ticket_id");
				         
					
					
					//int updateid = Integer.parseInt(updateTicketid);
					if(rs!= null)
					{
						JOptionPane.showMessageDialog(null, "Record exists!");
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Record not exists!");
					}
				}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Action has been cancelled!");
				}

			} catch (SQLException ex) {
 				ex.printStackTrace();
			}
			
			
		}*/
		
		else if (e.getSource() == mnuItemUpdate) {

			try {

				// get ticket information
				//ImageIcon icon = new ImageIcon(ticketsGUI.class.getResource("/happy.jpg"));
				String updateTicketid = JOptionPane.showInputDialog(null, "Enter the ticket number you want to update");

				// insert ticket information to database

				Statement statement = Dao.getConnection().createStatement();
				
				
				String UpdateDes = JOptionPane.showInputDialog(null, "Enter new ticket description");
				
				if ((UpdateDes != null) && (UpdateDes.length() > 0))
				{
 
					int result = statement
							.executeUpdate("Update jjw1_tickets SET ticket_description ='" + 
					UpdateDes + "'WHERE ticket_id = " + updateTicketid);
	
					// display results if successful or not to console / dialog box
					if (result != 0) {
						System.out.println("Ticket ID : " + updateTicketid + " has successfully been updated!");
						ImageIcon icon2 = new ImageIcon(ticketsGUI.class.getResource("/wow.jpg"));
						JOptionPane.showMessageDialog(null, "Ticket id: " + updateTicketid + " updated",null, JOptionPane.INFORMATION_MESSAGE, icon2);
					} else {
						System.out.println("Ticket cannot be updated!!!");
					}
				}
				else {
					ImageIcon icon3 = new ImageIcon(ticketsGUI.class.getResource("/glasses.jpg"));
					JOptionPane.showMessageDialog(null, "Action has been cancelled!",null, JOptionPane.INFORMATION_MESSAGE, icon3);
				}

			} catch (SQLException ex) {
 				ex.printStackTrace();
			}
			
			
		}  
		
		else if (e.getSource() == mnuItemDelete) {

			try {

				// get ticket information
				//ImageIcon icon = new ImageIcon(ticketsGUI.class.getResource("/happy.jpg"));
				String deleteTicketid = JOptionPane.showInputDialog(null, "Enter the ticket number you want to delete");

				// insert ticket information to database

				Statement statement = Dao.getConnection().createStatement();
				//ImageIcon icon2 = new ImageIcon(ticketsGUI.class.getResource("/question.jpg"));
				int warning =  JOptionPane.showConfirmDialog(null,"Are you sure you want to delete ticket number "+ deleteTicketid +"?", "Warning", JOptionPane.YES_NO_OPTION);
				//google why yes no can't have icon

				// display results if successful or not to console / dialog box
				if(warning==JOptionPane.YES_OPTION)
				{
					statement.executeUpdate("DELETE FROM clope_tckts WHERE ticket_id =" + deleteTicketid);
					ImageIcon icon3 = new ImageIcon(ticketsGUI.class.getResource("/happy.jpg"));
					JOptionPane.showMessageDialog(null, "Ticket id: " + deleteTicketid + "deleted",null, JOptionPane.INFORMATION_MESSAGE, icon3);
				}
				else if(warning==JOptionPane.NO_OPTION)
				{
					System.out.println("Ticket was not deleted");
				}
				// retrieve ticket id number newly auto generated upon record
				// insertion

			} catch (SQLException ex) {
 				ex.printStackTrace();
			}
			
			
		}  
		
	}
}
