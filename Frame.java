import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
/** 
 * @author Horatiu Lazu */

public class Frame implements ActionListener{
  JFrame a;
  JPanel panel;
  int [] [] truthTableVal;
  JButton [] [] truthTable;
  
  JComponent panel1;
  JComponent panel2;
  JComponent panel3;
  JComponent panel4;
  
  public static void main (String [] args){
    new Frame();
  }
  
  protected JComponent makeTextPanel(String text) {
    JPanel panel = new JPanel(false);
    JLabel filler = new JLabel(text);
    filler.setHorizontalAlignment(JLabel.CENTER);
    panel.setLayout(new GridLayout(1, 1));
    panel.add(filler);
    return panel;
  }
  
  protected static ImageIcon createImageIcon(String path) {
    java.net.URL imgURL = Frame.class.getResource(path);
    if (imgURL != null) {
      return new ImageIcon(imgURL);
    } else {
      System.err.println("Couldn't find file: " + path);
      return null;
    }
  }
  
  
  public void addKarnaughMaps(){
    /* Add Karnaugh map for 2 variables. */
    JScrollPane scroll;
    DefaultTableModel km = new DefaultTableModel();
    JTable karnaugh;
    km.setDataVector(new Object[][]{{"", "", "0", "1"}, {"B", "0", "0", "0"},{"B", "1", "0", "0"}},new Object[]{"", "", "A", "A"});
    
    karnaugh = new JTable(km);
    karnaugh.getTableHeader().setFont(new Font("SansSerif", Font.PLAIN, 40));
    karnaugh.setFont(new Font("Serif", Font.PLAIN, 35));
    //karnaugh.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    //karnaugh.getColumn("A").setCellRenderer(new ButtonRenderer());
    //karnaugh.getColumn("A").setCellEditor(new ButtonEditor(new JCheckBox()));
    
    
    scroll = new JScrollPane(karnaugh);
    //panel1.add(truthTableText[2]);//same one? nope
    //truthTableText[2].setBounds(47, 8, 100, 30); //35 prev
    
    karnaugh.setPreferredScrollableViewportSize(new Dimension(10,200)); //table.getPreferredSize()
    
    for(int i = 0; i < 4; i++)
      karnaugh.getColumnModel().getColumn(i).setPreferredWidth(50);//so buttons will fit and not be shown butto..
    scroll.setBounds(275 , 36, 300, 400); //set bounds...
    panel1.add(scroll);
    
    /* End of Karnaugh Map code */
  }
  
  /** This method adds truth tables to the panels. */
  public void addTruthTables(){
    
    /* Adding two variable truth-table */
    panel1.setLayout(null);
    
    JLabel truthTableText [] = new JLabel [3];
    for(int i = 0; i < 3; i++)
      truthTableText[i] = new JLabel("Truth Table");
    
    DefaultTableModel dm = new DefaultTableModel();
    dm.setDataVector(generateTruthTable(2), new Object[]{"A", "B", "F (AB)"});
    
    JTable table = new JTable(dm);
    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    table.getColumn("F (AB)").setCellRenderer(new ButtonRenderer());
    table.getColumn("F (AB)").setCellEditor(new ButtonEditor(new JCheckBox()));
    
    panel1.add(truthTableText[0]);
    truthTableText[0].setBounds(40, 8, 100, 30);
    
    JScrollPane scroll = new JScrollPane(table);
    scroll.setBounds(25, 36, 120, 90);
    
    table.setPreferredScrollableViewportSize(table.getPreferredSize());//thanks mKorbel +1 http://stackoverflow.com/questions/10551995/how-to-set-jscrollpane-layout-to-be-the-same-as-jtable
    
    for(int i = 0; i < 2; i++) 
      table.getColumnModel().getColumn(i).setPreferredWidth(20);
    panel1.add(scroll);
    
    /* Adding 3 variable truth-table*/
    panel2.setLayout(null);
    dm = new DefaultTableModel();
    dm.setDataVector(generateTruthTable(3), new Object[]{"A", "B", "C", "F (ABC)"});
    
    table = new JTable(dm);
    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    table.getColumn("F (ABC)").setCellRenderer(new ButtonRenderer());
    table.getColumn("F (ABC)").setCellEditor(new ButtonEditor(new JCheckBox()));
    
    scroll = new JScrollPane(table);
    panel2.add(truthTableText[1]);//same one? nope
    truthTableText[1].setBounds(42, 8, 100, 30); //35 prev
    
    table.setPreferredScrollableViewportSize(new Dimension(10,200)); //table.getPreferredSize()
    
    for(int i = 0; i < 3; i++)
      table.getColumnModel().getColumn(i).setPreferredWidth(10);//so buttons will fit and not be shown butto..
    scroll.setBounds(25 , 36, 125, 150); //set bounds...
    
    panel2.add(scroll);
    
    
    /* Adding 4 variable truth-table*/
    panel3.setLayout(null);
    
    dm = new DefaultTableModel();
    dm.setDataVector(generateTruthTable(4), new Object[]{"A", "B", "C", "D", "F (ABCD)"});
    
    table = new JTable(dm);
    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    table.getColumn("F (ABCD)").setCellRenderer(new ButtonRenderer());
    table.getColumn("F (ABCD)").setCellEditor(new ButtonEditor(new JCheckBox()));
    
    scroll = new JScrollPane(table);
    panel3.add(truthTableText[2]);
    truthTableText[2].setBounds(47, 8, 100, 30); //35 prev
    
    table.setPreferredScrollableViewportSize(new Dimension(10,200)); //table.getPreferredSize()
    
    for(int i = 0; i < 4; i++)
      table.getColumnModel().getColumn(i).setPreferredWidth(10);//so buttons will fit and not be shown butto..
    scroll.setBounds(25 , 36, 140, 280); //set bounds...
    panel3.add(scroll);
  }
  
  /* Credits to Java Doc for code in using TabbedPanes */
  public void addTabs(){
    JTabbedPane tabbedPane = new JTabbedPane();
    ImageIcon icon = createImageIcon("images/middle.gif");
    
    panel1 = makeTextPanel("Panel #1");
    tabbedPane.addTab("2 Variable", icon, panel1,
                      "2 Variable Boolean Expression Simplification");
    tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
    
    panel2 = makeTextPanel("Panel #2");
    tabbedPane.addTab("3 Variable", icon, panel2,
                      "3 Variable Boolean Expression Simplification");
    tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
    
    panel3 = makeTextPanel("Panel #3");
    tabbedPane.addTab("4 Variable", icon, panel3,
                      "4 Variable Boolean Expression Simplification");
    tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
    
    panel4 = makeTextPanel("Panel #4 (has a preferred size of 410 x 50).");
    panel4.setPreferredSize(new Dimension(1000, 500));
    
    tabbedPane.addTab("Virtual Grid", icon, panel4,"Variety of functions");
    tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);
    panel.add(tabbedPane);
    
  }
  
  
  
  /* Generate truth table using bitmasking */
  private Object [] [] generateTruthTable(int variables){
    Object [] [] returnArray = new Object[(int)(Math.pow(2, variables))][variables +1];
    int num = (int)(Math.pow(2, variables));
    for(int i = 0; i < num; i++){
      int tempNum = i;
      for(int x = variables-1; x >= 0; x--){
        returnArray[i][x] = (tempNum >> (variables-1-x) & 1);
        System.out.println((tempNum >> (variables-1-x) & 1));
      }
      returnArray[i][variables] = "0";
    }
    return returnArray;
  }
  
  
  public void actionPerformed(ActionEvent ae){
    
  }
  
  
  
  public Frame(){
    a = new JFrame("Karnaugh Map Simplification - Horatiu Lazu");
    panel = new JPanel();
    
    JMenuBar bar = new JMenuBar();
    
    JMenu file = new JMenu("File");
    JMenu help = new JMenu("Help");
    
    JMenuItem quit = new JMenuItem("Quit");
    file.add(quit);
    quit.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        System.exit(0);
      }
    });
    
    JMenuItem about = new JMenuItem("About");
    about.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        new About();
      }
    });
    help.add(about);
    
    bar.add(file);
    bar.add(help);
    panel.add(bar);
    addTabs();
    addTruthTables();
    addKarnaughMaps();
    a.add(panel);
    a.setJMenuBar(bar);
    
    
    a.setVisible(true);
    a.setSize(1024, 600);
    
    //new About(); //test
  }
  
  
}