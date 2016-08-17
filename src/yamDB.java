import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;


//import yamDB.Title; remove this 

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

public class yamDB {
	
	private static final int MAX_ROULETTE_CHOICE_RANGE = 10000;//(in titles)
	private static final int QUERY_MAX_TIME = 90; //(in seconds)

	private JFrame frame;
	//private ButtonGroup menuButtonGroup;
	private JRadioButton mySearchButton;
	private JRadioButton myRouleteButton;
	private JRadioButton myPredictorButton;
	private JTextField myJtxtFieldNameOrPC;
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
		frame = new JFrame("Yet Another Movie Database");
		
		frame.setBounds(160, 0, 900, 695);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setBackground(Color.white);
	
		//new
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		//BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
		//panel.setLayout(boxlayout);
		//panel.setBorder(new EmptyBorder(new Insets(50, 80, 50, 80))); 

		// Icon
		JLabel lbIcon = new JLabel();
		ImageIcon icon = createImageIcon("logo1.png","");
		lbIcon.setIcon(icon);
		//new
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(10,0,35,0);	
		panel.add(lbIcon, c);
		
		//new
		JPanel infoPan = new JPanel();
		infoPan.setLayout(new BoxLayout(infoPan,BoxLayout.Y_AXIS));
		infoPan.setBackground(Color.white);
		
		//radio buttons
		JPanel innerPan1 = new JPanel();
		innerPan1.setLayout(new BoxLayout(innerPan1, BoxLayout.X_AXIS));
		JLabel radiolb = new JLabel("I WOULD LIKE TO  ");
		
		ButtonGroup menuButtonGroup = new ButtonGroup();
		
		mySearchButton = new JRadioButton(" SEARCH");
		mySearchButton.setSelected(true);
		myRouleteButton = new JRadioButton(" PLAY ROULETTE");
		myPredictorButton = new JRadioButton(" USE PREDICTOR");
		
		mySearchButton.setBackground(Color.white);
		myRouleteButton.setBackground(Color.white);
		myPredictorButton.setBackground(Color.white);
		
		innerPan1.add(radiolb);
		
		menuButtonGroup.add(mySearchButton);
		menuButtonGroup.add(myRouleteButton);
		menuButtonGroup.add(myPredictorButton);
	
		innerPan1.add(mySearchButton);
		innerPan1.add(myRouleteButton);
		innerPan1.add(myPredictorButton);
		innerPan1.setBackground(Color.white);
		/*searchButton.addActionListener(this);
	    rouleteButton.addActionListener(this);
	    predictorButton.addActionListener(this);
		public void actionPerformed(ActionEvent e) {
		    ;
		}*/
		JPanel innerPan0 = new JPanel();
		innerPan0.setBackground(Color.white);
		innerPan0.setLayout(new BoxLayout(innerPan0, BoxLayout.X_AXIS));
		JLabel instructionLabel = new JLabel("Leave boxes blank to not limit search criteria (large searches can take up to 20 sec)");
		innerPan0.add(instructionLabel);
		
		//search by movie title
		JPanel innerPan = new JPanel();
		innerPan.setBackground(Color.white);
		innerPan.setLayout(new BoxLayout(innerPan, BoxLayout.X_AXIS));

		JLabel lb1 = new JLabel("Movie Title  ");
		myJtxtFieldNameOrPC = new JTextField();
		innerPan.add(lb1);
		innerPan.add(myJtxtFieldNameOrPC);

		//year from
		JPanel innerPan2 = new JPanel();
		innerPan2.setBackground(Color.white);
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
		innerPan3.setBackground(Color.white);
		innerPan3.setLayout(new BoxLayout(innerPan3, BoxLayout.X_AXIS));	
		JLabel actorlb = new JLabel("Actor's Name (Last, First)  ");
		myJtxtFieldActor = new JTextField();
		innerPan3.add(actorlb);
		actorlb.setToolTipText("Actor's Name: Last, First");
		innerPan3.add(myJtxtFieldActor);
		myJtxtFieldActor.setEnabled(false);
		
		//rating From
		JPanel innerPan4 = new JPanel();
		innerPan4.setBackground(Color.white);
		innerPan4.setLayout(new BoxLayout(innerPan4, BoxLayout.X_AXIS));	
		JLabel ratingsfromlb = new JLabel("Rating FROM ");
		myRatingsfrom = new JComboBox<String>();
		ArrayList<Integer> ratingsfromList = new ArrayList<Integer>();
		myRatingsfrom.addItem("");
		for(int i = 1; i < 11; i++){
			ratingsfromList.add(i);
			myRatingsfrom.addItem(i + "");
		}
		
		innerPan4.add(ratingsfromlb);
		innerPan4.add(myRatingsfrom);
		myRatingsfrom.setBackground(Color.white);

		//rating To
		JLabel ratingstolb = new JLabel(" TO ");
		myRatingsto = new JComboBox<String>();
		ArrayList<Integer> ratingstoList = new ArrayList<Integer>();
		myRatingsto.addItem("");
		for(int i = 1; i < 11; i++){
			ratingstoList.add(i);
			myRatingsto.addItem(i + "");
		}
		innerPan4.add(ratingstolb);
		innerPan4.add(myRatingsto);
		myRatingsto.setBackground(Color.white);

		// Genre
		JPanel innerPan5 = new JPanel();
		innerPan5.setBackground(Color.white);
		innerPan5.setLayout(new BoxLayout(innerPan5, BoxLayout.X_AXIS));	
		JLabel genrelb = new JLabel("Genre  ");
		myGenre = new JComboBox<String>();
		String[] genres = new String[] {"Select", "Short", "Drama", "Comedy", "Documentary", "Adult", "Action", "Thriller",
				"Romance", "Animation", "Family", "Horror", "Music", "Crime", "Adventure", "Fantasy", "Sci-Fi",
				"Mystery", "Biography", "History", "Sport", "Musical", "War", "Western", "Reality-TV", "News",
				"Talk-Show", "Game-Show", "Film-Noir", "Lifestyle" , "Experimental", "Erotica", "Commercial"};
		myGenre = new JComboBox<>(genres);
		//myGenre.setEnabled(false);//DISABLE GENRE FIELD FOR NOW UNTIL FIXED
		
		innerPan5.add(genrelb);
		innerPan5.add(myGenre);
		myGenre.setBackground(Color.white);
		
		//UIManager.put("JComboBox.Background", new ColorUIResource(Color.GREEN));
		//search button
		JPanel innerPan6 = new JPanel();
		innerPan6.setLayout(new BorderLayout());
		myButtonSearch = new JButton("SEARCH");
		myButtonSearch.setBackground(new Color(251, 215, 100));
		ActionHandler handler = new ActionHandler();
		
		//listeners
		myButtonSearch.addActionListener(handler);
		myJtxtFieldNameOrPC.addActionListener(handler);
		myJtxtFieldYearFrom.addActionListener(handler);
		innerPan6.add(myButtonSearch,BorderLayout.CENTER);
		myJtxtFieldNameOrPC.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent arg0) {
				myJtxtFieldActor.setText("");
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				// do nothing
			}
        });
		
		myJtxtFieldActor.addFocusListener(new FocusListener(){
			
			@Override
			public void focusGained(FocusEvent arg0) {
				myJtxtFieldNameOrPC.setText("");
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				// do nothing
			}
        });
		
		myRouleteButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	myJtxtFieldNameOrPC.setText("");
	        	myJtxtFieldNameOrPC.setEnabled(false);
	        	myJtxtFieldActor.setText("");
	        	myJtxtFieldActor.setEnabled(false);
	        	myGenre.setEnabled(true);
	        	myRatingsfrom.setEnabled(true);
	        	myRatingsto.setEnabled(true);
	        	myButtonSearch.setText("ROULETTE");
				lb1.setText("Production Company ");
	        }
	    });
		
		mySearchButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	myJtxtFieldNameOrPC.setEnabled(true);
	        	myJtxtFieldActor.setText("");
	        	myJtxtFieldActor.setEnabled(false);
	        	myGenre.setEnabled(true);
	        	myRatingsfrom.setEnabled(true);
	        	myRatingsto.setEnabled(true);
	        	myButtonSearch.setText("SEARCH");
				lb1.setText("Movie Title ");
	        }
	    });
		
		myPredictorButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	myJtxtFieldActor.setEnabled(true);
	        	myJtxtFieldNameOrPC.setEnabled(true);
	        	myGenre.setSelectedIndex(0);
	        	myRatingsfrom.setSelectedIndex(0);
	        	myRatingsto.setSelectedIndex(0);
	        	myGenre.setEnabled(false);
	        	myRatingsfrom.setEnabled(false);
	        	myRatingsto.setEnabled(false);
	        	myButtonSearch.setText("SEARCH");
				lb1.setText("Production Company ");
	        }
	    });
		
		//new
		c.fill =GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1; 
		c.gridx = 1;
		c.insets = new Insets(0,0,0,40);	
		
		infoPan.add(innerPan1);
		infoPan.add(Box.createVerticalStrut(10));
		infoPan.add(innerPan0);
		infoPan.add(Box.createVerticalStrut(10));
		infoPan.add(innerPan);
		infoPan.add(Box.createVerticalStrut(20));
		infoPan.add(innerPan2);
		infoPan.add(Box.createVerticalStrut(20));
		infoPan.add(innerPan3);
		infoPan.add(Box.createVerticalStrut(20));
		infoPan.add(innerPan4);
		infoPan.add(Box.createVerticalStrut(20));
		infoPan.add(innerPan5);
		infoPan.add(Box.createVerticalStrut(20));
		infoPan.add(innerPan6);
		
		panel.add(infoPan,c);
		
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
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 300;      //make this component tall
		c.weightx = 0.0;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(0,35,35,35);	
		
		panel.add(new JScrollPane(list),c);

		JMenuBar menuBar = new JMenuBar();

		//Build the first menu.
		final JMenu fileMenu = new JMenu("MENU"); // created file menu object
        fileMenu.setMnemonic(KeyEvent.VK_M);
        menuBar.add(fileMenu);
        menuBar.setBackground(new Color(251, 215, 100));
		JMenuItem menuAbout = new JMenuItem("About");
		menuAbout.setBackground(new Color(251, 215, 100));
		menuAbout.setForeground(Color.black);
		menuAbout.setMnemonic(KeyEvent.VK_A);
		menuAbout.addActionListener(new ActionListener() {
	            public void actionPerformed(final ActionEvent theEvent) {
	                JOptionPane.showMessageDialog(null, "TCSS 445 Database Systems Design / SUMMER 2016\n"
	                                                + "\nDEVELOPED AND DESIGNED BY: \nVladimir Smirnov - developer\nDaniel Bayless - front-end developer\nChris Kubec - developer\nPeter Phe - developer\nDavid Humphreys - product owner / developer\nLola Howell - UX design / front-end developer",
	                                                "About",
	                                                JOptionPane.INFORMATION_MESSAGE);
	            }
	        });
		fileMenu.add(menuAbout); //added item(sub menu) to file menu object
        fileMenu.addSeparator();
		JMenuItem menuHELP = new JMenuItem("Help");
		menuHELP.setBackground(new Color(251, 215, 100));
		menuHELP.setForeground(Color.black);
		menuHELP.setMnemonic(KeyEvent.VK_H);
		menuHELP.addActionListener(new ActionListener() {
	            public void actionPerformed(final ActionEvent theEvent) {
	            	String helpMessage = "Yet Another Movie Database (YAMBD) is yet another movie database! (we know you were looking for another one).\n\n" +
   						 				 "We aim to give users the chance to sort through some of IMDB's databases\n" +
   						 				 "and hopefully discover some information not easily obtained on IMDB's Website.\n\n" +
   						 				 "You will see at the top we have three features currently avaliable: SEARCH, ROULETTE, and PREDICT!\n\n" +
   						 				 "SEARCH - Allows you to create lists of movies based on specific search criteria, ratings, years, genre.\n\n" +
   						 				 "ROULETTE - Gives you the chance to have us select a random movie for you (given some search parameters).\n\n" +
   						 				 "PREDICTOR - This feature gives you the opportunity to see average movie rankings for different actors or\n" +
	            						 "production companies. Discover for yourself what average ratings the actors and companies that you know\n" +
   						 				 "have over their lifetime and recent history.\n";
	                JOptionPane.showMessageDialog(null, helpMessage, "Help",
	                                                JOptionPane.INFORMATION_MESSAGE);
	            }
	        });
		fileMenu.add(menuHELP); //added item(sub menu) to file menu object
		 fileMenu.addSeparator();
        final JMenuItem quitMenu = new JMenuItem("Exit", KeyEvent.VK_X);
        quitMenu.setBackground(new Color(251, 215, 100));
        quitMenu.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                frame.dispose();
            }
        });
        fileMenu.add(quitMenu);
		UIManager UI=new UIManager();
	        UI.put("OptionPane.background", Color.white);
	        UI.put("Panel.background", Color.white);
		frame.setJMenuBar(menuBar);
		
		
//		panel.add(lbIcon);
//		panel.add(Box.createVerticalStrut(30));
//		panel.add(innerPan1);
//		panel.add(Box.createVerticalStrut(30));
//		panel.add(innerPan);
//		panel.add(Box.createVerticalStrut(15));
//		panel.add(innerPan3);
//		panel.add(Box.createVerticalStrut(15));
//		panel.add(innerPan2);
//		panel.add(Box.createVerticalStrut(15));
//		panel.add(innerPan4);
//		panel.add(Box.createVerticalStrut(15));
//		panel.add(innerPan5);
//		panel.add(Box.createVerticalStrut(15));
//		panel.add(innerPan6);
//		panel.add(Box.createVerticalStrut(15));
//		panel.add(new JScrollPane(list));
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
				
				//How long the statement will wait for a return answer from the database.
				myStmt.setQueryTimeout(QUERY_MAX_TIME);
				
				//3 Execute SQl query
				String queryNameOrPC = myJtxtFieldNameOrPC.getText();
				String queryStartYear = myJtxtFieldYearFrom.getText();
				String queryEndYear = myJtxtFieldYearTo.getText();
				String queryStartRank = (String) myRatingsfrom.getSelectedItem();
				String queryEndRank = (String) myRatingsto.getSelectedItem();
				String queryGenre = (String) myGenre.getSelectedItem();
				String queryActor = myJtxtFieldActor.getText();
				
				String queryStatement = "";
				
				ResultSet rs = null;
				
				if(mySearchButton.isSelected()) {
					queryStatement = createSearchQuery(queryNameOrPC, queryStartYear, queryEndYear, queryStartRank.trim(), queryEndRank.trim(), queryGenre);
					
					System.out.println("Your SQL Query: " + queryStatement);
					rs = myStmt.executeQuery(queryStatement);
				}
				else if (myRouleteButton.isSelected()) {
					queryStatement = createRouletteQuery(queryStartYear, queryEndYear, queryStartRank.trim(), queryEndRank.trim(), queryGenre);
					
					System.out.println("Your SQL Query: " + queryStatement);
					rs = myStmt.executeQuery(queryStatement);
				}
				else if (myPredictorButton.isSelected()) {
					
					//Check For Any Views Here, if we have old views, drop them.
					rs = myStmt.executeQuery("SELECT table_name FROM information_schema.tables WHERE table_name = 'MyActor'");
					//Create Drop Statement, if we created a view earlier
					if(rs.next()) {
						System.out.print("Deleting MyActor");
						myStmt.execute("DROP VIEW MyActor;");
					}
					rs = myStmt.executeQuery("SELECT table_name FROM information_schema.tables WHERE table_name = 'MyPC'");
					if (rs.next()) {
						System.out.print("Deleting MyPC");
						myStmt.execute("DROP VIEW MyPC;");
					}
					
					//Create View Statement
					String manipulationStatement = "";
					System.out.println("Actor Name: " + queryActor + ".");
					if(queryActor != null && queryActor.length() > 0) {
						manipulationStatement = "CREATE VIEW MyActor AS SELECT * FROM Actors WHERE Actors.Name LIKE '" + queryActor + "';";
					}
					else {
						manipulationStatement = "CREATE VIEW MyPC AS SELECT * FROM ProductionCompanies WHERE ProductionCompanies.ProductionCompany LIKE '%" + queryNameOrPC + "%';";
					}
					//Create Query Statement
					queryStatement = createPredictorQuery(queryNameOrPC, queryStartYear, queryEndYear, queryActor);
					
					System.out.println("Your SQL Query: " + queryStatement);
					myStmt.execute(manipulationStatement);
					rs = myStmt.executeQuery(queryStatement);
				}
				System.out.println("Query Complete!");
				
				/*
				CREATE VIEW MyActor AS SELECT * FROM Actors WHERE Actors.Name = 'Jones, Alex';
				SELECT MyActor.ActorID, MyActor.Name, Ratings.Title, Ratings.Year, Ratings.Rank, Ratings.Votes FROM MyActor INNER JOIN Ratings ON Ratings.MovieID = MyActor.MovieID ORDER BY MyActor.ActorID ASC, Ratings.Votes DESC;
				DROP VIEW MyActor;

				CREATE VIEW MyPC AS SELECT * FROM ProductionCompanies WHERE ProductionCompanies.ProductionCompany LIKE '%Sony%';
				SELECT MyPC.ProductionCompany, Ratings.Title, Ratings.Year, Ratings.Rank, Ratings.Votes FROM MyPC INNER JOIN Ratings ON Ratings.MovieID = MyPC.MovieID ORDER BY MyPC.ProductionCompany ASC, Ratings.Votes DESC;
				DROP VIEW MyPC;
				*/
				
				//4 GET RESULT SET BACK FROM SQL

				int n = 1;
				ArrayList<Title> outputList = new ArrayList<Title>();
				while(rs.next()) {
					
					Title currentTitle = null;
					
					String genre = null;
					if(queryGenre != null && !queryGenre.equals("Select")) {
						genre = rs.getString("Genre");
					}
					
					if(mySearchButton.isSelected()) {
						currentTitle = new Title(n, rs.getString("Title"), rs.getString("Year").substring(0, 4), rs.getString("Rank"), rs.getString("Votes"), genre);
					}
					else if (myRouleteButton.isSelected()) {
						currentTitle = new Title(n, rs.getString("Title"), rs.getString("Year").substring(0, 4), rs.getString("Rank"), rs.getString("Votes"), genre);
					}
					else if (myPredictorButton.isSelected()) {
						if(queryActor.length() > 0) {
							currentTitle = new Title(n, rs.getString("Title"), rs.getString("Year").substring(0, 4), rs.getString("Rank"), rs.getString("Votes"), rs.getString("ActorID"), rs.getString("Name"));
						}
						else {
							currentTitle = new Title(rs.getString("Title"), rs.getString("Year").substring(0, 4), rs.getString("Rank"), rs.getString("Votes"), rs.getString("ProductionCompany"));
						}
					}
					
					n++;
					outputList.add(currentTitle);

				}
				
				//Create Drop Statement, if we created a view earlier
				if(myPredictorButton.isSelected() && queryActor != null && queryActor.length() > 0) {
					myStmt.execute("DROP VIEW MyActor;");
				}
				else if (myPredictorButton.isSelected()) {
					myStmt.execute("DROP VIEW MyPC;");
				}
				
				//5 DECIDE HOW MUCH OF RESULT SET TO PRINT TO USER (result set called "outputlist" here)
				
				if(outputList.size() > 0) {
					//SEARCH (output all results)
					if (mySearchButton.isSelected()) {
						for(Title eachTitle : outputList)
							myModel.addElement(eachTitle.toString());
					}
					//ROULETTE (output one result)
					else if (myRouleteButton.isSelected()) {
						myModel.addElement("Our reccomendation to you is.... ");
						int searchRange = Math.min(outputList.size(), MAX_ROULETTE_CHOICE_RANGE);
						Random rand = new Random();
						int index = rand.nextInt(searchRange);
						myModel.addElement(outputList.get(index).toString());
					}
					//PREDICT
					else if (myPredictorButton.isSelected()) {
						outputList.add(new Title());

						//IF WE ARE RETURNING ACTOR INFORMATION
						if(queryActor.length() > 0 && outputList.size() > 0) {
							String previousID = "";
							String actorName = "";
							int totalRating = 0;
							int numberTitles = 0;
							
							for(Title eachTitle : outputList)
								//get first Title info
								if(previousID.equals("")) {
									previousID = eachTitle.getID();
									actorName = eachTitle.getActorName();
									totalRating = Integer.parseInt(eachTitle.getRank());
									numberTitles = 1;
									myModel.addElement(eachTitle.toString());
								}
								//If this is the same actor, record this movie, and continue getting total rank (used for average)
								else if(eachTitle.getID().equals(previousID)) {
									totalRating += Integer.parseInt(eachTitle.getRank());
									previousID = eachTitle.getID();
									numberTitles++;
									myModel.addElement(eachTitle.toString());
								}
								//If it's not the first, and the actorID shows a new actor now
								else {
									//Print Previous Actors Totals, then save new actor in next set of totals
									double avg = (1.0 * totalRating) / numberTitles;
									avg = Math.round(avg * 100) / 100.0;
									myModel.addElement("TOTALS FOR ABOVE ACTOR --->    Name: " + actorName + "  ID: " + previousID + "   Average Rating: " + avg + " over " + numberTitles + " titles.");
									//and start next set if this isn't the terminator title (telling us we are done)
									if(!eachTitle.getID().equals("?")) {
										previousID = eachTitle.getID();
										actorName = eachTitle.getActorName();
										totalRating = Integer.parseInt(eachTitle.getRank());
										numberTitles = 1;
										myModel.addElement(eachTitle.toString());
									}
								}
						}
						//IF WE ARE RETURNING PRODUCTION COMPANY INFORMATION
						else if (outputList.size() > 0) {
							String previousPC = "";
							int totalRating = 0;
							int numberTitles = 0;
							
							for(Title eachTitle : outputList)
								//get first Title info
								if(previousPC.equals("")) {
									previousPC = eachTitle.getPC();
									totalRating = Integer.parseInt(eachTitle.getRank());
									numberTitles = 1;
									myModel.addElement(eachTitle.toString());
								}
								//If this is the same actor, record this movie, and continue getting total rank (used for average)
								else if(eachTitle.getPC().equals(previousPC)) {
									totalRating += Integer.parseInt(eachTitle.getRank());
									previousPC = eachTitle.getPC();
									numberTitles++;
									myModel.addElement(eachTitle.toString());
								}
								//If it's not the first, and the actorID shows a new actor now
								else {
									//Print Previous Actors Totals, then save new actor in next set of totals
									double avg = (1.0 * totalRating) / numberTitles;
									avg = Math.round(avg * 100) / 100.0;
									myModel.addElement("TOTALS FOR ABOVE COMPANY --->    Name: " + previousPC + "   Average Rating: " + avg + " over " + numberTitles + " titles.");
									myModel.addElement("\n");
									//and start next set if this isn't the terminator title (telling us we are done)
									if(!eachTitle.getPC().equals("?")) {
										previousPC = eachTitle.getPC();
										totalRating = Integer.parseInt(eachTitle.getRank());
										numberTitles = 1;
										myModel.addElement(eachTitle.toString());
									}
								}
						}
						
					}
				}
				else {
					myModel.addElement("Sorry, we didn't find anything that matched those parameters.");
				}

				/*if (searchText.equals(query)) {
     	            System.out.println("Searching names.."  + query);
     	            }*/
			} 
			catch (SQLException g) {
				System.err.println("Error with relation to Database. Caught in catchblock at end of actionPerformed().");
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
				searchSpecifics += and + " Ratings.Title LIKE '%" + name + "%'";
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
			if(genre != null && !genre.equals("Select")) {
				System.out.println("GENRE: " + genre);
				searchSpecifics += and + " Genres.Genre = '" + genre + "'";
				searchStatement = "SELECT Ratings.Title, Ratings.Year, Ratings.Rank, Ratings.Votes, Genres.Genre FROM Ratings INNER JOIN Genres ON Ratings.MovieID = Genres.MovieID";
			}

			//SELECT Ratings.Title, Ratings.Rank, Ratings.Votes, Ratings.Year, Genres.Genres FROM
			//Ratings INNER JOIN Genres Where Ratings.Year >= 1992 AND Ratings.Year <= 1995 AND
			//Ratings.Rank >= 8 AND Ratings.Rank <= 10

			if(searchSpecifics.length() > 0) {
				//add our WHERE statements, removing the first "and"
				searchStatement += " WHERE" + searchSpecifics.substring(and.length(), searchSpecifics.length()) + ";";
			}
			
			return searchStatement;
		}

		//Generates a SQL Roulette query (goal being to give a specific movie recommendation) and returns that string
		private String createRouletteQuery(String startYear, String endYear, String startRank, String endRank, String genre) {
			String searchStatement = "SELECT Ratings.Title, Ratings.Year, Ratings.Rank, Ratings.Votes FROM Ratings";
			String searchSpecifics = "";
			//also search date if we were sent one
			String and = " AND";
			
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
			if(genre != null && !genre.equals("Select")) {
				searchSpecifics += and + " Genres.Genre = '" + genre + "'";
				searchStatement = "SELECT Ratings.Title, Ratings.Year, Ratings.Rank, Ratings.Votes, Genres.Genre FROM Ratings INNER JOIN Genres ON Ratings.MovieID = Genres.MovieID";
			}

			if(searchSpecifics.length() > 0) {
				searchStatement += " WHERE" + searchSpecifics.substring(and.length(), searchSpecifics.length());
			}
			
			return searchStatement + " ORDER BY Ratings.Votes DESC;";
		}
		
		/*
		CREATE VIEW MyActor AS SELECT * FROM Actors WHERE Actors.Name = 'Jones, Alex';
		SELECT MyActor.ActorID, MyActor.Name, Ratings.Title, Ratings.Year, Ratings.Rank, Ratings.Votes FROM MyActor INNER JOIN Ratings ON Ratings.MovieID = MyActor.MovieID ORDER BY MyActor.ActorID ASC, Ratings.Votes DESC;
		DROP VIEW MyActor;

		CREATE VIEW MyPC AS SELECT * FROM ProductionCompanies WHERE ProductionCompanies.ProductionCompany LIKE '%Sony%';
		SELECT MyPC.ProductionCompany, Ratings.Title, Ratings.Year, Ratings.Rank, Ratings.Votes FROM MyPC INNER JOIN Ratings ON Ratings.MovieID = MyPC.MovieID ORDER BY MyPC.ProductionCompany ASC, Ratings.Votes DESC;
		DROP VIEW MyPC;
		*/
		
		//Generates a SQL Predictor query (goal being to have the user select an Actor OR a Production Company then also add
		// year range they are interested in, to see
		private String createPredictorQuery(String queryNameOrPC, String startYear, String endYear, String queryActor) {
			String searchStatement = "SELECT MyPC.ProductionCompany, Ratings.Title, Ratings.Year, Ratings.Rank, Ratings.Votes FROM "
					+ "MyPC INNER JOIN Ratings ON Ratings.MovieID = MyPC.MovieID";

			if(queryActor != null && queryActor.length() > 0) {
				searchStatement = "SELECT MyActor.ActorID, MyActor.Name, Ratings.Title, Ratings.Year, Ratings.Rank, Ratings.Votes FROM "
						+ "MyActor INNER JOIN Ratings ON Ratings.MovieID = MyActor.MovieID";
			}
			
			String searchSpecifics = "";

			//also search date if we were sent one
			String and = " AND";
			
			if(startYear.length() == 4) {
				searchSpecifics += and + " Ratings.Year >= " + startYear;
			}
			if(endYear.length() == 4) {
				searchSpecifics += and + " Ratings.Year <= " + endYear;
			}

			if(searchSpecifics.length() > 0) {
				searchStatement += " WHERE" + searchSpecifics.substring(and.length(), searchSpecifics.length());
			}
			
			if(queryActor != null && queryActor.length() > 0) {
				searchStatement += " ORDER BY MyActor.ActorID ASC, Ratings.Year ASC;";
			}
			else {
				//searchStatement += " ORDER BY MyPC.ProductionCompany ASC, Ratings.Votes DESC;";
				searchStatement += " ORDER BY MyPC.ProductionCompany ASC;";
			}
			
			return searchStatement;
		}
		
	}
	
	public class Title {
		
		private int n;
		private String title;
		private String year;
		private String rank;
		private String votes;
		private String actorID;
		private String actorName;
		private String genre;
		private String pc;
		
		//Terminator Title Constructor
		public Title() {
			this.n = 0;
			this.title = null;
			this.year = null;
			this.rank = null;
			this.votes = null;
			this.genre = null;
			this.actorID = "?";
			this.actorName = null;
			this.pc = "?";
		}
		
		//Search and Roulette Constructor
		public Title(int n, String title, String year, String rank, String votes, String genre) {
			this.n = n;
			this.title = title;
			this.year = year;
			this.rank = rank;
			this.votes = votes;
			this.genre = genre;
			this.actorID = null;
			this.actorName = null;
			this.pc = null;
		}

		//Predictor (Actor) Constructor
		public Title(int n, String title, String year, String rank, String votes, String actorID, String ActorName) {
			this.n = n;
			this.title = title;
			this.year = year;
			this.rank = rank;
			this.votes = votes;
			this.genre = null;
			this.actorID = actorID;
			this.actorName = ActorName;
			this.pc = null;
		}

		//Predictor (Production Company) Constructor
		public Title(String title, String year, String rank, String votes, String pc) {
			this.n = 0;
			this.title = title;
			this.year = year;
			this.rank = rank;
			this.votes = votes;
			this.genre = null;
			this.actorID = null;
			this.actorName = null;
			this.pc = pc;
		}

		public String getActorName() {
			return this.actorName;
		}

		public String getRank() {
			return this.rank;
		}

		public String getID() {
			return this.actorID;
		}

		public String getPC() {
			return pc;
		}

		public String getVotes() {
			return votes;
		}
		
		@Override
		public String toString() {
			String theString =  "";
			if(actorName != null) {
				theString += "Actor: " + actorName + "    ";
			}
			if(actorID != null) {
				theString += "ID: " + actorID + "    ";
			}
			if(n != 0) {
				theString += "Result#: " + n + "    ";
			}
			
			theString += "Title: " + title + "    Release Date: " + year + "    Rating: " + rank + "    Votes: " + votes;
			
			if(genre != null) {
				theString += "    Genre: " + genre;
			}
			if(actorID != null) {
				theString += "    ID: " + actorID;
			}
			if(actorName != null) {
				theString += "    Actor: " + actorName;
			}
			if(pc != null) {
				theString += "    Production Company: " + pc;
			}
			return theString;
		}

	}
}
