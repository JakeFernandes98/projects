import java.awt.event.*;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.*;

public class View{
  JLabel screen;

  public void start(){
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JPanel panel = new JPanel();
    panel.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    frame.add(panel);
    screen = new JLabel();
    frame.setSize(320,550);
    panel.setSize(320,550);
    screen.setSize(300,20);
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridwidth = 5;
    c.gridx = 0;
    c.gridy = 0;
    panel.add(screen, c);
    c.gridwidth = 1;

    Action NUMlist2 = new AbstractAction(){
      public void actionPerformed(ActionEvent e){
        screen.setText(screen.getText()+e.getActionCommand());
      }};

    JButton clear = new JButton("CLR");
    clear.setName("CLR");
    clear.addActionListener(new clearing());
    c.gridy=4;
    panel.add(clear,c);

    JButton del = new JButton("DEL");
    del.setName("DEL");
    del.addActionListener(new clearing());
    c.gridy=5;
    panel.add(del,c);

    JButton lbr = new JButton("(");
    lbr.setName("(");
    lbr.addActionListener(new NUMlist());
    c.gridx = 1;
    panel.add(lbr,c);

    JButton rbr = new JButton(")");
    rbr.setName(")");
    rbr.addActionListener(new NUMlist());
    c.gridx = 2;
    panel.add(rbr,c);

    c.gridy = 1;
    for(int i=1;i<10;i++){
      JButton btn = new JButton(Integer.toString(i));
      btn.setName(Integer.toString(i));
      btn.addActionListener(new NUMlist());
      if(i>3) c.gridy = 2;
      if(i>6) c.gridy = 3;
      c.gridx = (i-1)%3;
      panel.add(btn, c);

      InputMap inputMap = panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
      inputMap.put(KeyStroke.getKeyStroke(Integer.toString(i)), Integer.toString(i));
      panel.getActionMap().put(Integer.toString(i), NUMlist2);
    }
    JButton zero = new JButton("0");
    zero.setName("0");
    zero.addActionListener(new NUMlist());
    c.gridx=1;
    c.gridy=4;
    panel.add(zero, c);

    JButton mult = new JButton("*");
    mult.setName("*");
    mult.addActionListener(new NUMlist());
    c.gridx=3;
    panel.add(mult,c);

    JButton equals = new JButton("=");
    equals.addActionListener(new equality());
    c.gridx=2;
    panel.add(equals, c);

    JButton plus = new JButton("+");
    plus.setName("+");
    plus.addActionListener(new NUMlist());
    c.gridx=3;
    c.gridy=1;
    panel.add(plus,c);

    JButton min = new JButton("-");
    min.setName("-");
    min.addActionListener(new NUMlist());
    c.gridy=2;
    panel.add(min,c);

    JButton divi = new JButton("/");
    divi.setName("/");
    divi.addActionListener(new NUMlist());
    c.gridy=3;
    panel.add(divi,c);

    InputMap inputMap = panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
    inputMap.put(KeyStroke.getKeyStroke("0"), "0");
    panel.getActionMap().put("0", NUMlist2);

    frame.setVisible(true);

  }
  public class NUMlist implements ActionListener{
    public void actionPerformed(ActionEvent event){
      JButton orgbutton = (JButton) event.getSource();
      String name = orgbutton.getName();
      screen.setText(screen.getText()+orgbutton.getName());
    }
  }
  public class clearing implements ActionListener{
    public void actionPerformed(ActionEvent event){
      JButton orgbutton = (JButton) event.getSource();
      String name = orgbutton.getName();
      if(name.equals("CLR")) screen.setText("");
      else{
        String text = screen.getText();
        if(text.length()>0) screen.setText(text.substring(0,text.length()-1));
      }
    }
  }
  public class equality implements ActionListener{
    public void actionPerformed(ActionEvent event){
      Model mod = new Model();
      String output;
      try{
        int evaluation = mod.start(screen.getText());
        output = Integer.toString(evaluation);
      }catch(Error e){
        System.out.println(e);
        output = "ERR";
      }catch(Exception e){
        System.out.println(e);
        output = "ERR";
      }
      screen.setText(output);
    }
  }
}
