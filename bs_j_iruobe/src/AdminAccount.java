import java.awt.EventQueue;
import javax.swing.table.TableRowSorter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
//import com.toedter.calendar.JDateChooser;
public class AdminAccount extends JFrame {

	private JPanel contentPane;
	private JTable tblBook;
	private DefaultTableModel dtmBook;
	private JTextField audioBarcode;
	private JTextField audioTitle;
	private JTextField audioDate;
	private JTextField audioQuantity;
	private JTextField audioPrice;
	private JComboBox<String> audioGenre;
	private JComboBox audioLanguage;
	private JComboBox audioFormat;
	private ArrayList<Book> newBookList;
	private JTextField audioLength;
	private JTextField pages;
	private String inputtedDate;
	private JComboBox inputType;
	private JComboBox condition;
	private JTextField paperBarcode;
	private JTextField paperTitle;
	private JTextField paperDate;
	private JTextField paperQuantity;
	private JTextField paperPrice;
	private JTextField paperPages;
	private JComboBox paperCondition;
	private JComboBox paperGenre;
	private JComboBox eLanguage;
	private JTextField eBarcode;
	private JTextField eTitle;
	private JTextField eDate;
	private JTextField eQuantity;
	private JTextField ePrice;
	private JTextField ePages;
	private JComboBox eGenre;
	private JComboBox eFormat;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminAccount frame = new AdminAccount();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	/**
	 * Create the frame.
	 */
	public AdminAccount() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		//newBookList = new ArrayList<Book>();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1373, 759);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 10, 1339, 702);
		contentPane.add(tabbedPane);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("View Books", null, panel_1, null);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 100, 1176, 513);
		panel_1.add(scrollPane);
		
		dtmBook = new DefaultTableModel();
		dtmBook.setColumnIdentifiers(new Object[] {"Barcode", "Book Type", "Title", "Language", "Genre", "Release Date", 
				"Quantity in Stock", "Retail Price", "Length", "Format(Paper)", "Pages(Paper)", "Condition", "Pages(Ebook)", "Format(Ebook)"});
		
		tblBook = new JTable(dtmBook);
		scrollPane.setViewportView(tblBook);
		
		JLabel lblNewLabel_11 = new JLabel("Welcome, Admin");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_11.setBounds(471, 41, 167, 38);
		panel_1.add(lblNewLabel_11);
		
		JButton ViewRefresh = new JButton("View/Refresh");
		ViewRefresh.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	dtmBook.setRowCount(0);
		        try {
		        	//
		            BufferedReader reader = new BufferedReader(new FileReader("Stock.txt"));
		            String line;
		            while ((line = reader.readLine()) != null) {
		                //System.out.println("Reading line: " + line);
		                String[] sections = line.split(", ");
		                //dtmBook.addRow(sections);
		                if (sections[1].equals("paperback")) {
		                    try {
		                        Paperback pbk = new Paperback(Integer.parseInt(sections[0]), BookType.valueOf(sections[1]), sections[2], 
		                                BookLanguage.valueOf(sections[3]), sections[4], LocalDate.parse(sections[5], formatter), 
		                                Integer.parseInt(sections[6]), Double.parseDouble(sections[7]), Integer.parseInt(sections[8]), 
		                                Condition.valueOf(sections[9].toUpperCase()));
//		                        System.out.println("Testing: ");
//		                        System.out.println(Integer.parseInt(sections[8]));
//		                        System.out.println(pbk.getPages());
		                        
		                        //WORKS: System.out.println("EIGHT:"+Integer.parseInt(sections[8]));
		                        //System.out.println("NINE:"+Condition.valueOf(sections[9].toUpperCase()));
		                        Object[] row = new Object[] {pbk.getBarcode(), pbk.getBookType(),pbk.getTitle(),pbk.getLanguage(),pbk.getGenre(),
		                                pbk.getReleaseDate(),pbk.getStockQuantity(),pbk.getPrice(), null, null, pbk.getPages(),pbk.getBookCondition(), null, null
		                            };
		                        //System.out.println("LOOK:"+row[8]);
		                        //System.out.println(row[9]);
		                        dtmBook.addRow(row);
		                        //System.out.println("Added paperback to table: " + pbk);
		                    } catch (IllegalArgumentException ex) {
		                        //System.err.println("Error parsing paperback from line: " + line);
		                        ex.printStackTrace();
		                    }
		                } else if (sections[1].equals("audiobook")) {
		                    try {
		                        AudioBook abk = new AudioBook(Integer.parseInt(sections[0]), BookType.valueOf(sections[1]), sections[2], 
		                                BookLanguage.valueOf(sections[3]), sections[4], LocalDate.parse(sections[5], formatter), 
		                                Integer.parseInt(sections[6]), Double.parseDouble(sections[7]), Double.parseDouble(sections[8]), 
		                                AudioFormat.valueOf(sections[9]));
		                        Object[] row = new Object[] {abk.getBarcode(), abk.getBookType(),abk.getTitle(),abk.getLanguage(),abk.getGenre(),
		                                abk.getReleaseDate(),abk.getStockQuantity(),abk.getPrice(),abk.getLength(),abk.getAformat(), null, null, null, null
		                            };
		                        dtmBook.addRow(row);
		                        //System.out.println("Added audiobook to table: " + abk);
		                    } catch (IllegalArgumentException ex) {
		                        //System.err.println("Error parsing audiobook from line: " + line);
		                        ex.printStackTrace();
		                    }
		                } else {
		                    try {
		                        Ebook ebk = new Ebook(Integer.parseInt(sections[0]), BookType.valueOf(sections[1]), sections[2], 
		                                BookLanguage.valueOf(sections[3]), sections[4], LocalDate.parse(sections[5], formatter), 
		                                Integer.parseInt(sections[6]), Double.parseDouble(sections[7]), Integer.parseInt(sections[8]), 
		                                EbookFormat.valueOf(sections[9]));
		                        
		                        Object[] row = new Object[] {ebk.getBarcode(), ebk.getBookType(),ebk.getTitle(),ebk.getLanguage(),ebk.getGenre(),
		                                ebk.getReleaseDate(),ebk.getStockQuantity(),ebk.getPrice(), null, null, null, null, ebk.getEpages(), ebk.getEformat()
		                            };
		      
		                        dtmBook.addRow(row);
		                       // System.out.println("Added ebook to table: " + ebk);
		                    } catch (IllegalArgumentException ex) {
		                        //System.err.println("Error parsing ebook from line: " + line);
		                        ex.printStackTrace();
		                    }
		                }
		            }
		            reader.close();
		        } catch (IOException ex) {
		            System.err.println("Error reading from Stock.txt");
		            ex.printStackTrace();
		        }
		       TableRowSorter<TableModel> sorter = new TableRowSorter<>(tblBook.getModel());
		        tblBook.setRowSorter(sorter);
		        
		        Comparator<Integer> comparator = new Comparator<Integer>() {
		        	public int compare(Integer int_1, Integer int_2) {
		        		return int_1.compareTo(int_2);
		        	}
		        };
		        sorter.setComparator(6, comparator);
		        
		        ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<>();
		        sortKeys.add(new RowSorter.SortKey(6, SortOrder.ASCENDING));
		        sorter.setSortKeys(sortKeys);
		    }
		});


		ViewRefresh.setBounds(1198, 95, 113, 21);
		panel_1.add(ViewRefresh);
		
		
		/*Code for Tab2 Starts Here*/
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Add AudioBook", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Barcode:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(83, 103, 61, 15);
		panel_2.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Title:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(83, 155, 45, 13);
		panel_2.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Language:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(83, 223, 61, 21);
		panel_2.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Genre:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_4.setBounds(83, 305, 45, 13);
		panel_2.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Release Date:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_5.setBounds(83, 367, 89, 15);
		panel_2.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Quantity In Stock:");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_6.setBounds(860, 104, 116, 13);
		panel_2.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Retail Price:");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_7.setBounds(860, 188, 97, 13);
		panel_2.add(lblNewLabel_7);
		
		audioBarcode = new JTextField();
		audioBarcode.setBounds(216, 102, 116, 19);
		panel_2.add(audioBarcode);
		audioBarcode.setColumns(10);
		
		audioTitle = new JTextField();
		audioTitle.setBounds(216, 153, 285, 19);
		panel_2.add(audioTitle);
		audioTitle.setColumns(10);
		
		audioDate = new JTextField();
		audioDate.setBounds(216, 366, 96, 19);
		panel_2.add(audioDate);
		audioDate.setColumns(10);
		
		audioQuantity = new JTextField();
		audioQuantity.setBounds(1056, 102, 50, 19);
		panel_2.add(audioQuantity);
		audioQuantity.setColumns(10);
		
		audioPrice = new JTextField();
		audioPrice.setBounds(1056, 186, 50, 19);
		panel_2.add(audioPrice);
		audioPrice.setColumns(10);
		
		audioLanguage = new JComboBox(BookLanguage.values());
		audioLanguage.setBounds(216, 224, 97, 21);
		panel_2.add(audioLanguage);
	
		String[] genres = new String[] {"Politics", "Business", "Computer Science", "Biography"};
		audioGenre = new JComboBox(genres);
		audioGenre.setBounds(216, 302, 150, 21);
		panel_2.add(audioGenre);
		

		audioLength = new JTextField();
		audioLength.setBounds(1056, 255, 50, 19);
		panel_2.add(audioLength);
		audioLength.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("Listening Length:");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_9.setBounds(860, 257, 115, 13);
		panel_2.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("Format:");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_10.setBounds(860, 339, 115, 13);
		panel_2.add(lblNewLabel_10);
		
		audioFormat = new JComboBox(AudioFormat.values());
		audioFormat.setBounds(1056, 336, 96, 21);
		panel_2.add(audioFormat);
		
		JButton addAudio = new JButton("Add Audiobook");
		addAudio.setFont(new Font("Tahoma", Font.PLAIN, 13));
		addAudio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//						String releaseDate = releaseDate.format(format);
				AudioBook abk = new AudioBook(Integer.parseInt(audioBarcode.getText()), BookType.audiobook, 
						audioTitle.getText(), (BookLanguage)audioLanguage.getSelectedItem(),
						(String)audioGenre.getSelectedItem(), LocalDate.parse(audioDate.getText(), formatter), 
						Integer.parseInt(audioQuantity.getText()), Double.parseDouble(audioPrice.getText()), 
						Double.parseDouble(audioLength.getText()), (AudioFormat)audioFormat.getSelectedItem());
				String filePath = "Stock.txt";
				String objectString = ""+abk.getBarcode() +", "+abk.getBookType()+", "+abk.getTitle()+", "+abk.getLanguage()+", "+abk.getGenre()+", "
						+abk.getReleaseDate().format(format)+", "+abk.getStockQuantity()+", "+abk.getPrice()+", "+abk.getLength()+", "+abk.getAformat()+"";
				try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
		            writer.write(objectString);
		            writer.newLine();
		            System.out.println("Object written to the file successfully.");
		            writer.close();
		        } catch (IOException er) {
		            System.out.println("An error occurred while writing the object to the file: " + er.getMessage());
		        }
			}
		});
		addAudio.setBounds(560, 502, 184, 43);
		panel_2.add(addAudio);
		
		JLabel lblNewLabel = new JLabel("Add Audiobook:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(455, 43, 179, 21);
		panel_2.add(lblNewLabel);
	
// Add Paperback Tab
		JPanel panel = new JPanel();
		tabbedPane.addTab("Add Paperback", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblNewLabel_8 = new JLabel("Add Paperback:");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_8.setBounds(470, 51, 195, 35);
		panel.add(lblNewLabel_8);
		
		JLabel lblNewLabel_12 = new JLabel("Barcode:");
		lblNewLabel_12.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_12.setBounds(126, 141, 84, 13);
		panel.add(lblNewLabel_12);
		
		paperBarcode = new JTextField();
		paperBarcode.setBounds(290, 139, 96, 19);
		panel.add(paperBarcode);
		paperBarcode.setColumns(10);
		
		JLabel lblNewLabel_13 = new JLabel("Title:");
		lblNewLabel_13.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_13.setBounds(126, 208, 76, 26);
		panel.add(lblNewLabel_13);
		
		paperTitle = new JTextField();
		paperTitle.setBounds(290, 213, 168, 19);
		panel.add(paperTitle);
		paperTitle.setColumns(10);
		
		JLabel lblNewLabel_14 = new JLabel("");
		lblNewLabel_14.setBounds(126, 308, 45, 13);
		panel.add(lblNewLabel_14);
		
		JLabel lblNewLabel_15 = new JLabel("Language:");
		lblNewLabel_15.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_15.setBounds(126, 277, 84, 13);
		panel.add(lblNewLabel_15);
		
		JComboBox paperLanguage = new JComboBox(BookLanguage.values());
		paperLanguage.setBounds(290, 273, 96, 21);
		panel.add(paperLanguage);
		
		JLabel lblNewLabel_16 = new JLabel("Genre:");
		lblNewLabel_16.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_16.setBounds(126, 347, 45, 13);
		panel.add(lblNewLabel_16);
		
		JLabel lblNewLabel_17 = new JLabel("Release Date:");
		lblNewLabel_17.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_17.setBounds(126, 404, 84, 13);
		panel.add(lblNewLabel_17);
		
		JLabel lblNewLabel_18 = new JLabel("Quantity in Stock:");
		lblNewLabel_18.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_18.setBounds(677, 141, 109, 13);
		panel.add(lblNewLabel_18);
		
		JLabel lblNewLabel_19 = new JLabel("");
		lblNewLabel_19.setBounds(751, 203, 45, 13);
		panel.add(lblNewLabel_19);
		
		JLabel lblNewLabel_20 = new JLabel("Price:");
		lblNewLabel_20.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_20.setBounds(677, 215, 45, 13);
		panel.add(lblNewLabel_20);
		
		JLabel lblNewLabel_21 = new JLabel("Number of Pages:");
		lblNewLabel_21.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_21.setBounds(677, 265, 141, 13);
		panel.add(lblNewLabel_21);
		
		paperGenre = new JComboBox(genres);
		paperGenre.setBounds(290, 343, 168, 21);
		panel.add(paperGenre);
		
		paperDate = new JTextField();
		paperDate.setBounds(290, 401, 96, 19);
		panel.add(paperDate);
		paperDate.setColumns(10);
		
		paperCondition = new JComboBox(Condition.values());
		paperCondition.setBounds(846, 316, 96, 21);
		panel.add(paperCondition);
		
		paperQuantity = new JTextField();
		paperQuantity.setBounds(846, 139, 96, 19);
		panel.add(paperQuantity);
		paperQuantity.setColumns(10);
		
		paperPrice = new JTextField();
		paperPrice.setBounds(846, 208, 96, 19);
		panel.add(paperPrice);
		paperPrice.setColumns(10);
		
		paperPages = new JTextField();
		paperPages.setBounds(846, 263, 96, 19);
		panel.add(paperPages);
		paperPages.setColumns(10);
		
		JLabel lblNewLabel_32 = new JLabel("Condition:");
		lblNewLabel_32.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_32.setBounds(677, 319, 65, 13);
		panel.add(lblNewLabel_32);
		
		JButton AddPaperback = new JButton("Add Paperback");
		AddPaperback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	//				String releaseDate = releaseDate.format(format);
			Paperback pbk = new Paperback(Integer.parseInt(paperBarcode.getText()), BookType.paperback, 
					paperTitle.getText(), (BookLanguage)paperLanguage.getSelectedItem(),
					(String)paperGenre.getSelectedItem(), LocalDate.parse(audioDate.getText(), formatter), 
					Integer.parseInt(paperQuantity.getText()), Double.parseDouble(paperPrice.getText()), 
					Integer.parseInt(paperPages.getText()), (Condition)paperCondition.getSelectedItem());
			String filePath = "Stock.txt";
			String objectString = ""+pbk.getBarcode() +", "+pbk.getBookType()+", "+pbk.getTitle()+", "+pbk.getLanguage()+", "+pbk.getGenre()+", "
					+pbk.getReleaseDate().format(format)+", "+pbk.getStockQuantity()+", "+pbk.getPrice()+", "+pbk.getPages()+", "+pbk.getBookCondition()+"";
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
	            writer.write(objectString);
	            writer.newLine();
	            System.out.println("Object written to the file successfully.");
	            writer.close();
	        } catch (IOException er) {
	            System.out.println("An error occurred while writing the object to the file: " + er.getMessage());
	        }
			}
		});
		AddPaperback.setFont(new Font("Tahoma", Font.PLAIN, 13));
		AddPaperback.setBounds(470, 500, 148, 41);
		panel.add(AddPaperback);
		
// Add Ebook tab
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Add Ebook", null, panel_3, null);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_22 = new JLabel("Add Ebook:");
		lblNewLabel_22.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_22.setBounds(498, 45, 134, 49);
		panel_3.add(lblNewLabel_22);
		
		JLabel lblNewLabel_23 = new JLabel("Barcode:");
		lblNewLabel_23.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_23.setBounds(44, 166, 65, 13);
		panel_3.add(lblNewLabel_23);
		
		JLabel lblNewLabel_24 = new JLabel("Title:");
		lblNewLabel_24.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_24.setBounds(44, 234, 45, 13);
		panel_3.add(lblNewLabel_24);
		
		JLabel lblNewLabel_25 = new JLabel("Language:");
		lblNewLabel_25.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_25.setBounds(44, 319, 65, 13);
		panel_3.add(lblNewLabel_25);
		
		JLabel lblNewLabel_26 = new JLabel("Genre:");
		lblNewLabel_26.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_26.setBounds(44, 400, 45, 13);
		panel_3.add(lblNewLabel_26);
		
		JLabel lblNewLabel_27 = new JLabel("Release Date:");
		lblNewLabel_27.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_27.setBounds(44, 475, 96, 13);
		panel_3.add(lblNewLabel_27);
		
		JLabel lblNewLabel_28 = new JLabel("Quantity in Stock:");
		lblNewLabel_28.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_28.setBounds(638, 166, 120, 13);
		panel_3.add(lblNewLabel_28);
		
		JLabel lblNewLabel_29 = new JLabel("Price:");
		lblNewLabel_29.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_29.setBounds(638, 235, 45, 13);
		panel_3.add(lblNewLabel_29);
		
		JLabel lblNewLabel_30 = new JLabel("Number of Pages:");
		lblNewLabel_30.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_30.setBounds(638, 319, 120, 13);
		panel_3.add(lblNewLabel_30);
		
		JLabel lblNewLabel_31 = new JLabel("Format:");
		lblNewLabel_31.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_31.setBounds(638, 400, 65, 13);
		panel_3.add(lblNewLabel_31);
		
		eBarcode = new JTextField();
		eBarcode.setBounds(150, 163, 96, 19);
		panel_3.add(eBarcode);
		eBarcode.setColumns(10);
		
		eTitle = new JTextField();
		eTitle.setBounds(150, 231, 201, 19);
		panel_3.add(eTitle);
		eTitle.setColumns(10);
		
		eDate = new JTextField();
		eDate.setBounds(150, 472, 96, 19);
		panel_3.add(eDate);
		eDate.setColumns(10);
		
		eLanguage = new JComboBox(BookLanguage.values());
		eLanguage.setBounds(150, 315, 96, 21);
		panel_3.add(eLanguage);
		
		eGenre = new JComboBox(genres);
		eGenre.setBounds(150, 396, 134, 21);
		panel_3.add(eGenre);
		
		eFormat = new JComboBox(EbookFormat.values());
		eFormat.setBounds(814, 392, 92, 21);
		panel_3.add(eFormat);
		
		eQuantity = new JTextField();
		eQuantity.setBounds(810, 164, 96, 19);
		panel_3.add(eQuantity);
		eQuantity.setColumns(10);
		
		ePrice = new JTextField();
		ePrice.setBounds(810, 232, 96, 19);
		panel_3.add(ePrice);
		ePrice.setColumns(10);
		
		ePages = new JTextField();
		ePages.setBounds(810, 317, 96, 19);
		panel_3.add(ePages);
		ePages.setColumns(10);
		
		JButton AddEbook = new JButton("Add Ebook");
		AddEbook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			//				String releaseDate = releaseDate.format(format);
					Ebook ebk = new Ebook(Integer.parseInt(eBarcode.getText()), BookType.ebook, 
							eTitle.getText(), (BookLanguage)eLanguage.getSelectedItem(),
							(String)eGenre.getSelectedItem(), LocalDate.parse(eDate.getText(), formatter), 
							Integer.parseInt(eQuantity.getText()), Double.parseDouble(ePrice.getText()), 
							Integer.parseInt(ePages.getText()), (EbookFormat)eFormat.getSelectedItem());
					String filePath = "Stock.txt";
					String objectString = ""+ebk.getBarcode() +", "+ebk.getBookType()+", "+ebk.getTitle()+", "+ebk.getLanguage()+", "+ebk.getGenre()+", "
							+ebk.getReleaseDate().format(format)+", "+ebk.getStockQuantity()+", "+ebk.getPrice()+", "+ebk.getEpages()+", "+ebk.getEformat()+"";
					try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
			            writer.write(objectString);
			            writer.newLine();
			            System.out.println("Object written to the file successfully.");
			            writer.close();
			        } catch (IOException er) {
			            System.out.println("An error occurred while writing the object to the file: " + er.getMessage());
			        }
			}
		});
		AddEbook.setFont(new Font("Tahoma", Font.PLAIN, 13));
		AddEbook.setBounds(487, 541, 152, 49);
		panel_3.add(AddEbook);
		
	}
}
