import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * @version 1.0, 17 October 2022
 * @author https://github.com/tnckdev
 */

public class Parkingmachine extends JFrame {
  // Anfang Attribute
  private State s0 = new State("s0", 0.00, 0, 0);
  private State s50 = new State("s50", 0.50, 0, 25);
  private State s100 = new State("s100", 1.00, 0, 50);
  private State s150 = new State("s150", 1.50, 0, 75);
  private State s200 = new State("s200", 2.00, 0, 100);
  private State s250 = new State("s250", 2.50, 0.5, 100);
  private State s300 = new State("s300", 3.00, 1.0, 100);
  private State s350 = new State("s350", 3.50, 1.5, 100);
  private State currentState = null;
  
  private JButton b50 = new JButton();
  private JButton b100 = new JButton();
  private JButton b200 = new JButton();
  private JButton bOpen = new JButton();
  private JButton bCancel = new JButton();
  private JLabel lBalance = new JLabel();
  private JLabel lExchange = new JLabel();
  private JProgressBar pbProgress = new JProgressBar();
  private JTextArea taInformation = new JTextArea("");
    private JScrollPane taInformationScrollPane = new JScrollPane(taInformation);
  // Ende Attribute
  
  public Parkingmachine() { 
    // Frame-Initialisierung
    super();
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 385; 
    int frameHeight = 300;
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setTitle("Parkingmachine | github/tnckdev");
    setResizable(false);
    Container cp = getContentPane();
    cp.setLayout(null);
    // Anfang Komponenten
    
    b50.setBounds(10, 10, 75, 25);
    b50.setText("0,50 Euro");
    b50.setMargin(new Insets(2, 2, 2, 2));
    b50.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        b50_ActionPerformed(evt);
      }
    });
    cp.add(b50);
    b100.setBounds(100, 10, 75, 25);
    b100.setText("1,00 Euro");
    b100.setMargin(new Insets(2, 2, 2, 2));
    b100.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        b100_ActionPerformed(evt);
      }
    });
    cp.add(b100);
    b200.setBounds(190, 10, 75, 25);
    b200.setText("2,00 Euro");
    b200.setMargin(new Insets(2, 2, 2, 2));
    b200.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        b200_ActionPerformed(evt);
      }
    });
    cp.add(b200);
    lBalance.setBounds(10, 70, 165, 20);
    lBalance.setText("Current balance: 0,00 Euro");
    cp.add(lBalance);
    lExchange.setBounds(10, 90, 165, 20);
    lExchange.setText("Current exchange: 0,00 Euro");
    cp.add(lExchange);
    bOpen.setBounds(280, 10, 75, 25);
    bOpen.setText("Open");
    bOpen.setMargin(new Insets(2, 2, 2, 2));
    bOpen.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bOpen_ActionPerformed(evt);
      }
    });
    cp.add(bOpen);
    
    SetState(s0);
    pbProgress.setBounds(10, 40, 255, 20);
    pbProgress.setValue(0);
    pbProgress.setBackground(Color.RED);
    pbProgress.setForeground(Color.GREEN);
    pbProgress.setString("n,nn / N,NN Euro");
    pbProgress.setStringPainted(true);
    cp.add(pbProgress);
    bCancel.setBounds(280, 40, 75, 25);
    bCancel.setText("Cancel");
    bCancel.setMargin(new Insets(2, 2, 2, 2));
    bCancel.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bCancel_ActionPerformed(evt);
      }
    });
    cp.add(bCancel);
    taInformationScrollPane.setBounds(10, 125, 350, 125);
    taInformation.setText("");
    taInformation.setEditable(false);
    taInformation.setWrapStyleWord(false);
    taInformation.setLineWrap(true);
    taInformationScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    cp.add(taInformationScrollPane);
    // Ende Komponenten
    
    SetState(s0);
    
    setVisible(true);
  } // end of public Parkingmachine
  
  // Anfang Methoden
  
  public static void main(String[] args) {
    new Parkingmachine();
  } // end of main
                                
  private void SetState(State s){
    currentState = s;
    if (currentState == s0) {
      taInformation.setText("INFORMATION:\n     Insert 2,00 Euro to open the barrier!");
    } // end of if
    lBalance.setText(String.format("Current balance: %.2f Euro", currentState.balance));
    lExchange.setText(String.format("Current exchange: %.2f Euro", currentState.exchange));
    pbProgress.setValue(currentState.completion);
    pbProgress.setString(String.format("%.2f / 2,00 Euro", currentState.balance));
  }
  
  public void b50_ActionPerformed(ActionEvent evt) {
    switch (currentState.name) {
      case "s0": 
        SetState(s50);
        break;
      case "s50": 
        SetState(s100);
        break;
      case "s100": 
        SetState(s150);
        break;
      case "s150": 
        SetState(s200);
        break;
      default: 
        taInformation.append("\nDENIED:\n     There is already enough money in the parking machine!\n     Press 'Open' to open the barrier!");       
    }        
  } // end of b50_ActionPerformed

  public void b100_ActionPerformed(ActionEvent evt) {
    switch (currentState.name) {
      case "s0": 
        SetState(s100);
        break;
      case "s50": 
        SetState(s150);
        break;
      case "s100": 
        SetState(s200);
        break;
      case "s150": 
        SetState(s250);
        break;
      default: 
        taInformation.append("\nDENIED:\n     There is already enough money in the parking machine!\n     Press 'Open' to open the barrier!");       
    }
  } // end of b100_ActionPerformed

  public void b200_ActionPerformed(ActionEvent evt) {
    switch (currentState.name) {
      case "s0": 
        SetState(s200);
        break;
      case "s50": 
        SetState(s250);
        break;
      case "s100": 
        SetState(s300);
        break;
      case "s150": 
        SetState(s350);
        break;
      default: 
        taInformation.append("\nDENIED:\n     There is already enough money in the parking machine!\n     Press 'Open' to open the barrier!");       
    } 
  } // end of b200_ActionPerformed

  public void bOpen_ActionPerformed(ActionEvent evt) {
    if (currentState == s200 || currentState == s250 || currentState == s300 || currentState == s350) {
      SetState(s0);
    } // end of if
    
  } // end of bOpen_ActionPerformed

  public void bCancel_ActionPerformed(ActionEvent evt) {
    SetState(s0);         
  } // end of bCancel_ActionPerformed

  // Ende Methoden
} // end of class Parkingmachine
