import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.RowFilter;

public class CustomerAccount extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel dtmBasket;
	private JTable tblBook;
	private DefaultTableModel dtmBook;
	private JTable tblBasket;
	private Book[] selectedRowData;
	private ArrayList<Object[]> basketList;
	private JTextField sBarcode;
	private JTextField sLength;
	


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerAccount frame = new CustomerAccount();
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
	public CustomerAccount() {
		basketList = new ArrayList<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
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
		scrollPane.setBounds(10, 145, 1163, 520);
		panel_1.add(scrollPane);
		
		dtmBook = new DefaultTableModel();
		dtmBook.setColumnIdentifiers(new Object[] {"Barcode", "Book Type", "Title", "Language", "Genre", "Release Date", 
				"Quantity in Stock", "Retail Price", "Length", "Format(Paper)", "Pages(Paper)", "Condition", "Pages(Ebook)", "Format(Ebook)"});
		
		tblBook = new JTable(dtmBook);
		scrollPane.setViewportView(tblBook);
		
		JLabel lblNewLabel = new JLabel("Welcome, Customer");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(387, 35, 179, 30);
		panel_1.add(lblNewLabel);
		
		JButton ViewRefresh2 = new JButton("View/Refresh");
		ViewRefresh2.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	dtmBook.setRowCount(0);
		        try {
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
		        
		        Comparator<Double> comparator = new Comparator<Double>() {
		        	public int compare(Double price_1, Double price_2) {
		        		return price_1.compareTo(price_2);
		        	}
		        };
		        sorter.setComparator(7, comparator);
		        
		        ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<>();
		        sortKeys.add(new RowSorter.SortKey(7, SortOrder.ASCENDING));
		        sorter.setSortKeys(sortKeys);
		    }
		});


		ViewRefresh2.setBounds(1183, 143, 127, 21);
		panel_1.add(ViewRefresh2);
		
		JButton searchBarcodeBtn = new JButton("Search Barcode");
		searchBarcodeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TableRowSorter<TableModel> sorter = new TableRowSorter<>(tblBook.getModel());
				tblBook.setRowSorter(sorter);
				
				String givenBarcode = sBarcode.getText();
				if (givenBarcode.trim().length() == 0) {
					sorter.setRowFilter(null);
				} else {
					sorter.setRowFilter(RowFilter.regexFilter("^" + givenBarcode + "$", 0));
				}
			}
		});
		searchBarcodeBtn.setBounds(10, 105, 168, 21);
		panel_1.add(searchBarcodeBtn);
		
		sBarcode = new JTextField();
		sBarcode.setBounds(253, 106, 279, 19);
		panel_1.add(sBarcode);
		sBarcode.setColumns(10);
		
		JButton searchLengthBtn = new JButton("Search Length");
		searchLengthBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TableRowSorter<TableModel> sorter = new TableRowSorter<>(tblBook.getModel());
				tblBook.setRowSorter(sorter);
				
				String givenLength = sLength.getText();
				if (givenLength.trim().length() == 0) {
					sorter.setRowFilter(null);
				} else {
					try {
						double minLength = Double.parseDouble(givenLength);
						sorter.setRowFilter (new RowFilter<TableModel, Integer>() {
							public boolean include(Entry<? extends TableModel, ? extends Integer> entry) {
								Object lengthValue = entry.getValue(8);
								if(lengthValue == null) {
									return false; //If the length of the column is null (i.e it's not an audiobook) filter it out
								}
								double length = ((Number) entry.getValue(8)).doubleValue();
								return length > minLength;
							}
						});
					} catch (NumberFormatException nfe) {
						sorter.setRowFilter(null);
					}
				}
			}
		});
		searchLengthBtn.setBounds(665, 105, 150, 21);
		panel_1.add(searchLengthBtn);
		
		sLength = new JTextField();
		sLength.setText("");
		sLength.setBounds(853, 106, 309, 19);
		panel_1.add(sLength);
		sLength.setColumns(10);
		
		JButton AddToBasket = new JButton("Add To Basket ");
		AddToBasket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = tblBook.getSelectedRow();
				if (selectedRow != -1) {
					selectedRow = tblBook.convertRowIndexToModel(selectedRow);
					Object[] rowdata = new Object[dtmBook.getColumnCount()];
					for (int i=0; i < rowdata.length; i++) {
						rowdata[i] = dtmBook.getValueAt(selectedRow, i);
					}
					dtmBasket.addRow(rowdata);
				}
				
				
			}
		});
		AddToBasket.setBounds(1183, 208, 127, 21);
		panel_1.add(AddToBasket);

//TAB 2: 
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Shopping Basket", null, panel_2, null);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(33, 71, 1132, 580);
		panel_2.add(scrollPane_1);
		
		dtmBasket = new DefaultTableModel();
		dtmBasket.setColumnIdentifiers(new Object[] {"Barcode", "Book Type", "Title", "Language", "Genre", "Release Date", 
				"Quantity in Stock", "Retail Price", "Length", "Format(Paper)", "Pages(Paper)", "Condition", "Pages(Ebook)", "Format(Ebook)"});
		
		tblBasket = new JTable(dtmBasket);
		scrollPane_1.setViewportView(tblBasket);
		
		JButton refreshBasket = new JButton("Refresh");
		refreshBasket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Object[] rowData : basketList) {
					dtmBasket.addRow(rowData);
				}
			}
		});
		refreshBasket.setBounds(1195, 82, 105, 21);
		panel_2.add(refreshBasket);
		
		JButton clearBtn = new JButton("Cancel Basket ");
		clearBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dtmBasket.setRowCount(0);
			}
		});
		clearBtn.setBounds(1195, 135, 105, 21);
		panel_2.add(clearBtn);
		
		JButton payBtn = new JButton("Pay");
		payBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		payBtn.setBounds(1195, 191, 105, 21);
		panel_2.add(payBtn);
		
		
	
	}
}