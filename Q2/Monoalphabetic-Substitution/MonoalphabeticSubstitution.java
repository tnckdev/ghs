import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author https://github.com/tnckdev 
 */

public class MonoalphabeticSubstitution extends JFrame {
  // Anfang Attribute
  private JLabel lInput = new JLabel();
  private JLabel lOutput = new JLabel();
  private JLabel lSubstitutionAlphabet = new JLabel();
  private JLabel lType = new JLabel();
  private JTextField tfSubstitution = new JTextField();
  private JTextArea taOutput = new JTextArea("");
    private JScrollPane taOutputScrollPane = new JScrollPane(taOutput);
  private JTextArea taInput = new JTextArea("");
    private JScrollPane taInputScrollPane = new JScrollPane(taInput);
  private JComboBox<String> cbType = new JComboBox<String>();
    private DefaultComboBoxModel<String> cbTypeModel = new DefaultComboBoxModel<String>();
  private JButton bRun = new JButton();
  
  private HashMap<Character, Character> encodingAlphabet = new HashMap<Character, Character>();
  private HashMap<Character, Character> decodingAlphabet = new HashMap<Character, Character>();
  // Ende Attribute
  
  public MonoalphabeticSubstitution() { 
    // Frame-Initialisierung
    super();
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 395; 
    int frameHeight = 310;
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setTitle("MonoalphabeticSubstitution");
    setResizable(false);
    Container cp = getContentPane();
    cp.setLayout(null);
    // Anfang Komponenten
    
    tfSubstitution.setBounds(220, 30, 150, 20);
    cp.add(tfSubstitution);
    taOutputScrollPane.setBounds(10, 160, 200, 100);
    cp.add(taOutputScrollPane);
    taInputScrollPane.setBounds(10, 30, 200, 100);
    cp.add(taInputScrollPane);
    lInput.setBounds(10, 10, 110, 20);
    lInput.setText("Input:");
    cp.add(lInput);
    lOutput.setBounds(10, 140, 110, 20);
    lOutput.setText("Output:");
    cp.add(lOutput);
    lSubstitutionAlphabet.setBounds(220, 10, 127, 20);
    lSubstitutionAlphabet.setText("Substitution alphabet:");
    cp.add(lSubstitutionAlphabet);
    lType.setBounds(220, 60, 110, 20);
    lType.setText("Type:");
    cp.add(lType);
    cbType.setModel(cbTypeModel);
    cbType.setBounds(220, 80, 150, 20);
    cbTypeModel.addElement("Encode");
    cbTypeModel.addElement("Decode");
    cp.add(cbType);
    bRun.setBounds(295, 105, 75, 25);
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
  } // end of public MonoalphabeticSubstitution
  
  // Anfang Methoden
  
  public static void main(String[] args) {
    new MonoalphabeticSubstitution();
  } // end of main
  
  public void bRun_ActionPerformed(ActionEvent evt) {
    int type = cbType.getSelectedIndex();
    if (createAlphabets(tfSubstitution.getText())) {
      if (type == 0) {
        taOutput.setText(encodeString(taInput.getText().toUpperCase()));
      } else if (type == 1){
        taOutput.setText(decodeString(taInput.getText()));
      } // end of if-else
    } // end of if
    else {
      System.out.println("The substitution alphabet has either duplicates or the wrong length!");
    } // end of if-else  
  } // end of bRun_ActionPerformed
  
  private String encodeString(String input){
    String s = "";
    for (char c: input.toCharArray()) {
      s += encodingAlphabet.getOrDefault(c, ' ');
    } // end of for
    return s;
  }
  
  private String decodeString(String input){
    String s = "";
    for (char c: input.toCharArray()) {
      s += decodingAlphabet.getOrDefault(c, ' ');
    } // end of for
    return s;
  }
  
  private boolean createAlphabets(String substitution){
    HashSet<Character> temp = new HashSet<>();
    for (char c: substitution.toCharArray()) temp.add(c);
    if (temp.size() == 26 && temp.size() == substitution.length()) {
      String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
      encodingAlphabet.clear();
      decodingAlphabet.clear();
      for (int i = 0; i < 26; i++) {
        encodingAlphabet.put(alphabet.charAt(i), substitution.charAt(i));
        decodingAlphabet.put(substitution.charAt(i), alphabet.charAt(i));
      }
      return true;
    }
    return false;
  }

  // Ende Methoden
} // end of class MonoalphabeticSubstitution
