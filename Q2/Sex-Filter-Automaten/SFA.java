import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * @author https://github.com/tnckdev 
 */

public class SFA extends JFrame {
  // Anfang Attribute
  private State s0 = new State("s0", false);
  private State s1 = new State("s1", false);
  private State s2 = new State("s2", false);
  private State s3 = new State("s3", true);
  private State currentState;
  
  private JLabel lOutput = new JLabel();
  private JTextField tfInput = new JTextField();
  private JButton bRun = new JButton();
  // Ende Attribute
  
  public SFA() { 
    // Frame-Initialisierung
    super();
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 300;
    int frameHeight = 300;
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setTitle("SFA");
    setResizable(false);
    Container cp = getContentPane();
    cp.setLayout(null);
    // Anfang Komponenten
    
    lOutput.setBounds(10, 35, 150, 20);
    lOutput.setText("Result pending...");
    cp.add(lOutput);
    tfInput.setBounds(10, 10, 150, 20);
    cp.add(tfInput);
    bRun.setBounds(170, 10, 75, 25);
    bRun.setText("Run");
    bRun.setMargin(new Insets(2, 2, 2, 2));
    bRun.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bRun_ActionPerformed(evt);
      }
    });
    cp.add(bRun);
    // Ende Komponenten
    
    setVisible(true);
    
    currentState = s0;
  } // end of public SFA
  
  // Anfang Methoden
  
  public static void main(String[] args) {
    new SFA();
  } // end of main
  
  private void checkWord(String word){
    for (char c: word.toLowerCase().toCharArray())
    {
      changeState(c);
      if (currentState.exit) {
        lOutput.setText("SEARCH IS NOT SAFE!");
        return;
      } // end of if
    }
    lOutput.setText("SEARCH IS SAFE!");
    currentState = s0;
  }
  
  private void changeState(char input){
    if (input == 's') {
      currentState = s1;
      return;
    } // end of if
    if (input == 'e' && currentState.name == "s1") {
      currentState = s2;
      return;
    } // end of if
    if (input == 'x' && currentState.name == "s2") {
      currentState = s3;
      return;
    } // end of if
    currentState = s0;
  }
  
  public void bRun_ActionPerformed(ActionEvent evt) {
    checkWord(tfInput.getText());         
  } // end of bRun_ActionPerformed

  // Ende Methoden
} // end of class SFA
