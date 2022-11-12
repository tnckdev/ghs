import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 11.11.2022
 * @author 
 */

public class Wordfilter extends JFrame {
  // Anfang Attribute
  // Ende Attribute
  
  public Wordfilter() { 
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
    setTitle("Wordfilter");
    setResizable(false);
    Container cp = getContentPane();
    cp.setLayout(null);
    // Anfang Komponenten
    
    // Ende Komponenten
    
    System.out.println(checkString("syxsesseyse", "sex"));
    
    setVisible(true);
  } // end of public Wordfilter
  
  // Anfang Methoden
  
  public static void main(String[] args) {
    new Wordfilter();
  } // end of main
  
  private boolean checkString(String input, String word){
    int n = word.length();
    int i = 0;
    for (char c: input.toLowerCase().toCharArray()) {
      if (c == word.toLowerCase().charAt(i)) {
        i += 1;
        if (i == n) return true; 
      } // end of if
      else {
        i = 0;
      } // end of if-else
    } // end of for
    return false;
  }
  
  // Ende Methoden
} // end of class Wordfilter
