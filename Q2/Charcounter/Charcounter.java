import java.awt.*;
import java.awt.event.*;
import java.math.RoundingMode;
import java.security.KeyStore.Entry;
import java.text.DecimalFormat;
import javax.swing.table.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.plaf.basic.BasicTreeUI.TreeModelHandler;

import java.util.stream.IntStream;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeMap;

/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 03.12.2022
 * @author 
 */

public class Charcounter extends JFrame {
  // Anfang Attribute
  private JButton bCount = new JButton();
  private JTextArea taInput = new JTextArea();
    private JScrollPane taInputScrollPane = new JScrollPane(taInput);
  private JLabel lInput = new JLabel();
  private JLabel lResult = new JLabel();
  private JTable tableResult = new JTable(0, 4);
    private DefaultTableModel tableResultModel = (DefaultTableModel) tableResult.getModel();
    private JScrollPane tableResultScrollPane = new JScrollPane(tableResult);
  // Ende Attribute
  
  public Charcounter() { 
    // Frame-Initialisierung
    super();
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 325; 
    int frameHeight = 415;
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setTitle("Charcounter | tnck.dev");
    setResizable(false);
    Container cp = getContentPane();
    cp.setLayout(null);
    // Anfang Komponenten
    
    bCount.setBounds(220, 30, 80, 24);
    bCount.setText("Count");
    bCount.setMargin(new Insets(2, 2, 2, 2));
    bCount.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bCount_ActionPerformed(evt);
      }
    });
    cp.add(bCount);
    taInputScrollPane.setBounds(10, 30, 200, 100);
    cp.add(taInputScrollPane);
    lInput.setBounds(10, 10, 81, 24);
    lInput.setText("Text to count:");
    cp.add(lInput);
    lResult.setBounds(10, 140, 80, 24);
    lResult.setText("Result:");
    cp.add(lResult);
    tableResultScrollPane.setBounds(10, 160, 290, 200);
    tableResult.getColumnModel().getColumn(0).setHeaderValue("Character");
    tableResult.getColumnModel().getColumn(1).setHeaderValue("Amount");
    tableResult.getColumnModel().getColumn(2).setHeaderValue("Rate");
    tableResult.getColumnModel().getColumn(3).setHeaderValue("in %");
    tableResultScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    tableResult.setColumnSelectionAllowed(false);
    tableResult.setRowSelectionAllowed(false);
    cp.add(tableResultScrollPane);
    // Ende Komponenten
    
    setVisible(true);

  } // end of public Charcounter
  
  // Anfang Methoden
  
  public static void main(String[] args) {
    new Charcounter();
  } // end of main


  private void treeMapToTable(TreeMap<Character, Integer> treeMap)
  {
    DefaultTableModel model = (DefaultTableModel) tableResult.getModel();
    model.setRowCount(0);
    int sum = 0;
    for(Integer n: treeMap.values())
      sum += n;
    DecimalFormat df = new DecimalFormat("#.##");
    df.setRoundingMode(RoundingMode.CEILING);
    for(Map.Entry<Character, Integer> entry: treeMap.entrySet())
      model.addRow(new Object[]{entry.getKey(), entry.getValue(), String.format("%d/%d", entry.getValue(), sum), df.format(entry.getValue().doubleValue() / sum * 100)});  
  }

  private TreeMap<Character, Integer> count(String s)
  {
    TreeMap<Character, Integer> counter = new TreeMap<Character, Integer>();
    for(Character c: s.toCharArray()) counter.put(c, counter.getOrDefault(c, 0) + 1);
    return counter;
  }  
  public void bCount_ActionPerformed(ActionEvent evt) {
    treeMapToTable(count(taInput.getText()));
  } // end of bCount_ActionPerformed

  // Ende Methoden
} // end of class Charcounter
