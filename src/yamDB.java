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
	//private ButtonGroup menuButtonGroup;
	private JRadioButton mySearchButton;
	private JRadioButton myRouleteButton;
	private JRadioButton myPredictorButton;
	private JTextField myJtxtFieldName;
	private JTextField myJtxtFieldActor;
	private JTextField myJtxtFieldYearFrom;
	private JTextField myJtxtFieldYearTo;
	private JComboBox<String> myRatingsfrom;
	private JComboBox<String> myRatingsto;
	private JComboBox<String> myGenre;
	private JButton myButtonSearch;
	private DefaultListModel<String> myModel;

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
		
		ButtonGroup menuButtonGroup = new ButtonGroup();
		
		mySearchButton = new JRadioButton(" SEARCH");
		mySearchButton.setSelected(true);
		myRouleteButton = new JRadioButton(" PLAY ROULETTE");
		myPredictorButton = new JRadioButton(" USE PREDICTOR");
		innerPan1.add(radiolb);
		
		menuButtonGroup.add(mySearchButton);
		menuButtonGroup.add(myRouleteButton);
		menuButtonGroup.add(myPredictorButton);
		
		innerPan1.add(mySearchButton);
		innerPan1.add(myRouleteButton);
		innerPan1.add(myPredictorButton);
		
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
		myJtxtFieldName = new JTextField();
		innerPan.add(lb1);
		innerPan.add(myJtxtFieldName);

		//year from
		JPanel innerPan2 = new JPanel();
		innerPan2.setLayout(new BoxLayout(innerPan2, BoxLayout.X_AXIS));		
		JLabel yearfromlb = new JLabel("Year From  ");
		myJtxtFieldYearFrom = new JTextField();
		innerPan2.add(yearfromlb);
		innerPan2.add(myJtxtFieldYearFrom);

		//year to
		JLabel yeartolb = new JLabel("  To  ");
		myJtxtFieldYearTo = new JTextField();
		innerPan2.add(yeartolb);
		innerPan2.add(myJtxtFieldYearTo);

		//actor
		JPanel innerPan3 = new JPanel();
		innerPan3.setLayout(new BoxLayout(innerPan3, BoxLayout.X_AXIS));	
		JLabel actorlb = new JLabel("Actor's Name  ");
		myJtxtFieldActor = new JTextField();
		innerPan3.add(actorlb);
		innerPan3.add(myJtxtFieldActor);
		myJtxtFieldActor.setEnabled(false);

		//rating From
		JPanel innerPan4 = new JPanel();
		innerPan4.setLayout(new BoxLayout(innerPan4, BoxLayout.X_AXIS));	
		JLabel ratingsfromlb = new JLabel("Rating From  ");
		myRatingsfrom = new JComboBox<String>();
		ArrayList<Integer> ratingsfromList = new ArrayList<Integer>();
		myRatingsfrom.addItem(" ");
		for(int i = 0; i < 11; i++){
			ratingsfromList.add(i);
			myRatingsfrom.addItem(i + "");
		}
		
		innerPan4.add(ratingsfromlb);
		innerPan4.add(myRatingsfrom);

		//rating To
		JLabel ratingstolb = new JLabel("  To  ");
		myRatingsto = new JComboBox<String>();
		ArrayList<Integer> ratingstoList = new ArrayList<Integer>();
		myRatingsto.addItem(" ");
		for(int i = 1; i < 11; i++){
			ratingstoList.add(i);
			myRatingsto.addItem(i + "");
		}
		innerPan4.add(ratingstolb);
		innerPan4.add(myRatingsto);

		// Genre
		JPanel innerPan5 = new JPanel();
		innerPan5.setLayout(new BoxLayout(innerPan5, BoxLayout.X_AXIS));	
		JLabel genrelb = new JLabel("Genre  ");
		myGenre = new JComboBox<String>();
		String[] genres = new String[] {"Select Genre", "Short", "Drama", "Comedy", "Documentary", "Adult", "Action", "Thriller",
				"Romance", "Animation", "Family", "Horror", "Music", "Crime", "Adventure", "Fantasy", "Sci-Fi",
				"Mystery", "Biography", "History", "Sport", "Musical", "War", "Western", "Reality-TV", "News",
				"Talk-Show", "Game-Show", "Film-Noir", "Lifestyle" , "Experimental", "Erotica", "Commercial"};
		myGenre = new JComboBox<>(genres);
		innerPan5.add(genrelb);
		innerPan5.add(myGenre);

		//search button
		JPanel innerPan6 = new JPanel();
		innerPan6.setLayout(new BorderLayout());
		myButtonSearch = new JButton("Search");
		ActionHandler handler = new ActionHandler();

		//listeners
		myButtonSearch.addActionListener(handler);
		myJtxtFieldName.addActionListener(handler);
		myJtxtFieldYearFrom.addActionListener(handler);
		innerPan6.add(myButtonSearch,BorderLayout.CENTER);
		
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
		
		myModel = new DefaultListModel<>();
		JList<String> list = new JList<String>(myModel);
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
			myModel.removeAllElements();
			try {
				//1.Get connection to database
				//Connection myCon = DriverManager.getConnection("jdbc:mysql://LocalHost/world", "root", "password"); // replace world with the name of your database, put your password in ""
				Connection myCon = DriverManager.getConnection("jdbc:mysql://yamdb.ddns.net/yamdb?useSSL=false", "devs", "P@ssw0rd"); // replace world with the name of your database, put your password in ""
				//2. Create a statement
				Statement myStmt = myCon.createStatement();
				
				//3 Execute SQl query
				String queryName = myJtxtFieldName.getText();
				String queryStartYear = myJtxtFieldYearFrom.getText();
				String queryEndYear = myJtxtFieldYearTo.getText();
				String queryStartRank = (String) myRatingsfrom.getSelectedItem();
				String queryEndRank = (String) myRatingsto.getSelectedItem();
				String queryGenre = (String) myGenre.getSelectedItem();
				String completedQuery = "";
				if(mySearchButton.isSelected()) {
					completedQuery = createSearchQuery(queryName, queryStartYear, queryEndYear, queryStartRank.trim(), queryEndRank.trim(), queryGenre);
				}
				else if (myRouleteButton.isSelected()) {
					completedQuery = createRouletteQuery();
				}
				else if (myPredictorButton.isSelected()) {
					completedQuery = createPredictorQuery();
				}


				/*String query = "select * from test WHERE name = ?";
     			PreparedStatement pst = (PreparedStatement) myCon.prepareStatement(query);
     			pst.setString(1, "%" + searchText + "%");
     			ResultSet rs = pst.executeQuery();*/
				String outputString = "";
				ResultSet rs = null;
				System.out.println("Your SQL Query: " + completedQuery);
				rs = myStmt.executeQuery(completedQuery);
				
				//4 Process the result set

				int n = 1;
				while(rs.next()) {
					
					outputString = n + "";
					
					String resultName = rs.getString("Title");
					String resultYear = rs.getString("Year").substring(0, 4);
					String resultRating = rs.getString("Rank");
					String resultVotes = rs.getString("Votes");
					//ONCE GENRE IS WORKING FOR SEARCH
					//String genre = rs.getString("Genres");
					
					if(mySearchButton.isSelected()) {
						outputString += ". Movie Title: " + resultName + "    Release Date: " + resultYear + "    Rating: " + resultRating + "    Votes: " + resultVotes;
					}
					else if (myRouleteButton.isSelected()) {
						//This Sprint (Sprint 3)
						//outputString +=
						// TODO Auto-generated method stub
					}
					else if (myPredictorButton.isSelected()) {
						//LAST SPRINT (SPRINT 4)
						//outputString +=
						// TODO Auto-generated method stub
					}
					
					n++;
					myModel.addElement(outputString);

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

		//SELECT * FROM Ratings WHERE Year >= queryStartYear AND Year <= queryEndYear AND Rank >= queryStartRank AND Rank <= queryEndRank
		
		//Generates a SQL search query (goal to find movies that match the stuff they put in) and returns that string
		private String createSearchQuery(String name, String startYear, String endYear, String startRank, String endRank, String genre) {
			String searchStatement = "SELECT Ratings.Title, Ratings.Year, Ratings.Rank, Ratings.Votes FROM Ratings";
			String searchSpecifics = "";
			//also search date if we were sent one
			String and = " AND";
			
			if(name.length() > 0) {
				searchSpecifics += and + " Ratings.Title = '" + name + "'";
			}
			if(startYear.length() == 4) {
				searchSpecifics += and + " Ratings.Year >= " + startYear;
			}
			if(endYear.length() == 4) {
				searchSpecifics += and + " Ratings.Year <= " + endYear;
			}
			if(startRank.length() > 0) {
				searchSpecifics += and + " Ratings.Rank >= " + startRank;
			}
			if(endRank.length() > 0) {
				searchSpecifics += and + " Ratings.Rank <= " + endRank;
			}
			if(genre != null && !genre.equals("Select Genre")) {
				//I think this next line is nearly working, but it freezes... I don't know why... may just be too big? -David
				//searchStatement = "SELECT Ratings.Title, Ratings.Year, Ratings.Rank, Ratings.Votes, Genres.Genres FROM Ratings INNER JOIN Genres";
				//This Sprint (Sprint 3)
				// TODO method stub
				System.out.println("Genres isn't workign here yet, but you chose: " + genre);
			}

			//SELECT Ratings.Title, Ratings.Rank, Ratings.Votes, Ratings.Year, Genres.Genres FROM
			//Ratings INNER JOIN Genres Where Ratings.Year >= 1992 AND Ratings.Year <= 1995 AND
			//Ratings.Rank >= 8 AND Ratings.Rank <= 10
			if(searchSpecifics.length() > 0) {
				searchStatement += " WHERE" + searchSpecifics.substring(and.length(), searchSpecifics.length()) + ";";
			}
			
			return searchStatement;
		}

		//Generates a SQL Roulette query (goal being to give a specific movie recommendation) and returns that string
		private String createRouletteQuery() {
			//This Sprint (Sprint 3)
			// TODO Auto-generated method stub
			return null;
		}

		//Generates a SQL Predictor query (goal being to have the user select an Actor OR a Production Company then also add
		// year range they are interested in, to see
		private String createPredictorQuery() {
			//LAST SPRINT (SPRINT 4)
			// TODO Auto-generated method stub
			return null;
		}
	}
}
