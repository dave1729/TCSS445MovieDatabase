import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.DefaultListModel;
import javax.swing.border.EmptyBorder;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class yamDB {

	private JFrame frame;
	private ButtonGroup menuButtonGroup;
	private JRadioButton searchButton;
	private JRadioButton rouleteButton;
	private JRadioButton predictorButton;
	private JTextField jtxtFieldName;
	private JTextField jtxtFieldActor;
	private JTextField jtxtFieldYearFrom;
	private JTextField jtxtFieldYearTo;
	private JButton btnSearch;
	private DefaultListModel<String> model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Splash mySplash = new Splash(2000);
		mySplash.showSplash();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					yamDB window = new yamDB();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public yamDB() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Yam DB");
		frame.setBounds(100, 100, 900, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.setLayout(boxlayout);
		panel.setBorder(new EmptyBorder(new Insets(50, 80, 50, 80))); 

		// Icon
		JLabel lbIcon = new JLabel();
		ImageIcon icon = createImageIcon("logo.png","");
		lbIcon.setIcon(icon);

		//radio buttons
		JPanel innerPan1 = new JPanel();
		innerPan1.setLayout(new BoxLayout(innerPan1, BoxLayout.X_AXIS));
		JLabel radiolb = new JLabel("I WANT TO  ");
		
		menuButtonGroup = new ButtonGroup();
		
		searchButton = new JRadioButton(" SEARCH");
		searchButton.setSelected(true);
		rouleteButton = new JRadioButton(" PLAY ROULETTE");
		predictorButton = new JRadioButton(" USE PREDICTOR");
		innerPan1.add(radiolb);
		
		menuButtonGroup.add(searchButton);
		menuButtonGroup.add(rouleteButton);
		menuButtonGroup.add(predictorButton);
		
		innerPan1.add(searchButton);
		innerPan1.add(rouleteButton);
		innerPan1.add(predictorButton);
		
		/*searchButton.addActionListener(this);
	    rouleteButton.addActionListener(this);
	    predictorButton.addActionListener(this);
		public void actionPerformed(ActionEvent e) {
		    ;
		}*/
		
		
		//search by movie title
		JPanel innerPan = new JPanel();
		innerPan.setLayout(new BoxLayout(innerPan, BoxLayout.X_AXIS));
		JLabel lb1 = new JLabel("Movie Title  ");
		jtxtFieldName = new JTextField();
		innerPan.add(lb1);
		innerPan.add(jtxtFieldName);

		//year from
		JPanel innerPan2 = new JPanel();
		innerPan2.setLayout(new BoxLayout(innerPan2, BoxLayout.X_AXIS));		
		JLabel yearfromlb = new JLabel("Year From  ");
		jtxtFieldYearFrom = new JTextField();
		innerPan2.add(yearfromlb);
		innerPan2.add(jtxtFieldYearFrom);

		//year to
		JLabel yeartolb = new JLabel("  To  ");
		jtxtFieldYearTo = new JTextField();
		innerPan2.add(yeartolb);
		innerPan2.add(jtxtFieldYearTo);

		//actor
		JPanel innerPan3 = new JPanel();
		innerPan3.setLayout(new BoxLayout(innerPan3, BoxLayout.X_AXIS));	
		JLabel actorlb = new JLabel("Actor's Name  ");
		jtxtFieldActor = new JTextField();
		innerPan3.add(actorlb);
		innerPan3.add(jtxtFieldActor);

		//rating From
		JPanel innerPan4 = new JPanel();
		innerPan4.setLayout(new BoxLayout(innerPan4, BoxLayout.X_AXIS));	
		JLabel ratingsfromlb = new JLabel("Rating From  ");
		JComboBox ratingsfrom = new JComboBox();
		ArrayList<Integer> ratingsfromList = new ArrayList();
		ratingsfrom.addItem(" ");
		for(int i = 1; i < 11; i++){
			ratingsfromList.add(i);
			ratingsfrom.addItem(i);
		}
		innerPan4.add(ratingsfromlb);
		innerPan4.add(ratingsfrom);

		//rating To
		JLabel ratingstolb = new JLabel("  To  ");
		JComboBox ratingsto = new JComboBox();
		ArrayList<Integer> ratingstoList = new ArrayList();
		ratingsto.addItem(" ");
		for(int i = 1; i < 11; i++){
			ratingstoList.add(i);
			ratingsto.addItem(i);
		}
		innerPan4.add(ratingstolb);
		innerPan4.add(ratingsto);

		// Genre
		JPanel innerPan5 = new JPanel();
		innerPan5.setLayout(new BoxLayout(innerPan5, BoxLayout.X_AXIS));	
		JLabel genrelb = new JLabel("Genre  ");
		JComboBox genre = new JComboBox();
		String[] genres = new String[] {"Short", "Drama", "Comedy", "Documentary", "Adult", "Action", "Thriller",
				"Romance", "Animation", "Family", "Horror", "Music", "Crime", "Adventure", "Fantasy", "Sci-Fi",
				"Mystery", "Biography", "History", "Sport", "Musical", "War", "Western", "Reality-TV", "News",
				"Talk-Show", "Game-Show", "Film-Noir", "Lifestyle" , "Experimental", "Erotica", "Commercial"};
		JComboBox<String> genresList = new JComboBox<>(genres);
		innerPan5.add(genrelb);
		innerPan5.add(genresList);

		//search button
		JPanel innerPan6 = new JPanel();
		innerPan6.setLayout(new BorderLayout());
		btnSearch = new JButton("Search");
		ActionHandler handler = new ActionHandler();

		//listeners
		btnSearch.addActionListener(handler);
		jtxtFieldName.addActionListener(handler);
		jtxtFieldYearFrom.addActionListener(handler);
		innerPan6.add(btnSearch,BorderLayout.CENTER);
		
		//Table
		/*JTable movieTalbe = new JTable();
		DefaultTableModel dtm = new DefaultTableModel();
		String columNames[] = {"Title", "Year","Rating"};
		dtm.setColumnCount(3);
		dtm.setColumnIdentifiers(columNames);
		movieTalbe.setModel(dtm);
		movieTalbe.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		dtm.addRow(new Object[] {"The Secret Life of Pets","2016","7.5"});
		dtm.addRow(new Object[] {"The BFG","2016","8.5"});
		dtm.addRow(new Object[] {"Ghostbusters","2016","5.5"});
		dtm.addRow(new Object[] {"Central Intelligence","2016","8.0"});*/
		
		model = new DefaultListModel<>();
		JList list = new JList(model);
		panel.add(lbIcon);
		panel.add(Box.createVerticalStrut(30));
		panel.add(innerPan1);
		panel.add(Box.createVerticalStrut(30));
		panel.add(innerPan);
		panel.add(Box.createVerticalStrut(15));
		panel.add(innerPan3);
		panel.add(Box.createVerticalStrut(15));
		panel.add(innerPan2);
		panel.add(Box.createVerticalStrut(15));
		panel.add(innerPan4);
		panel.add(Box.createVerticalStrut(15));
		panel.add(innerPan5);
		panel.add(Box.createVerticalStrut(15));
		panel.add(innerPan6);
		panel.add(Box.createVerticalStrut(15));
		panel.add(new JScrollPane(list));
		//panel.add(new JScrollPane(movieTalbe));
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}

	private ImageIcon createImageIcon(String path, String description) {
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL, description);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}
	
	public class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			model.removeAllElements();
			try {
				//1.Get connection to database
				//Connection myCon = DriverManager.getConnection("jdbc:mysql://LocalHost/world", "root", "password"); // replace world with the name of your database, put your password in ""
				Connection myCon = DriverManager.getConnection("jdbc:mysql://yamdb.ddns.net/yamdb?useSSL=false", "devs", "P@ssw0rd"); // replace world with the name of your database, put your password in ""
				//2. Create a statement
				Statement myStmt = myCon.createStatement();
				
				//3 Execute SQl query
				String name = jtxtFieldName.getText();
				String date = jtxtFieldYearFrom.getText();
				String rank = null;
				String votes = null;
				String startYear = jtxtFieldYearFrom.getText();
				String endYear = jtxtFieldYearTo.getText();
				
				String query = "";
				if(searchButton.isSelected()) {
					query = createSearchQuery(name, date, rank, votes);
				}
				else if (rouleteButton.isSelected()) {
					query = createRouletteQuery();
				}
				else if (predictorButton.isSelected()) {
					query = createPredictorQuery();
				}


				/*String query = "select * from test WHERE name = ?";
     			PreparedStatement pst = (PreparedStatement) myCon.prepareStatement(query);
     			pst.setString(1, "%" + searchText + "%");
     			ResultSet rs = pst.executeQuery();*/
				String outputString = "";
				ResultSet rs = null;
				
				rs = myStmt.executeQuery(query);
				
				//4 Process the result set
				String previousOutput = "";
				int n = 1;
				while(rs.next()) {
					name = rs.getString("Title");
					System.out.println(name);
					date = rs.getString("Year").substring(0, 4);
					rank = rs.getString("Rank");
					votes = rs.getString("Votes");
					
					outputString = n + ". Movie Title: " + name + "    Release Date: " + date + "    Rating: " + rank + "    Votes: " + votes;
					n++;
					
					if(!outputString.equals(previousOutput)) {
						model.addElement(outputString);
						previousOutput = outputString;
					}
				}
				/*if (searchText.equals(query)) {
     	            System.out.println("Searching names.."  + query);
     	            }*/
			} 
			catch (SQLException g) {
				System.err.println("Yo' Shit Didn't Run Main! You need to find this code and fix it up real good!!!");
				g.printStackTrace();
			}
		}

		//Generates a SQL search query (goal to find movies that match the stuff they put in) and returns that string
		private String createSearchQuery(String name, String date, String rank, String votes) {
			String searchStatement = "select * from Ratings";
			//also search date if we were sent one
			if(date.length() == 4 && (name.length() > 0 && name != null)) {
				searchStatement += " WHERE Title = '" + name + "' AND Year = '" + date + "'";
			}
			else if(date.length() == 4) {
				searchStatement += " WHERE Year = '" + date + "'";
			}
			else if(name.length() > 0 && name != null) {
				searchStatement += " WHERE Title = '" + name + "'";
			}
			return searchStatement;
		}

		//Generates a SQL Roulette query (goal being to give a specific movie recommendation) and returns that string
		private String createRouletteQuery() {
			// TODO Auto-generated method stub
			return null;
		}

		//Generates a SQL Predictor query (goal being to have the user select an Actor OR a Production Company then also add
		// year range they are interested in, to see
		private String createPredictorQuery() {
			// TODO Auto-generated method stub
			return null;
		}
	}
}
