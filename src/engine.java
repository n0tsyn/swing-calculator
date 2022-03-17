import javax.swing.*;

import java.awt.event.*;
import java.awt.Font;
import java.awt.Color;

public class engine
{
  class frame
  {
    private JFrame f_;

    private JLabel initlabel(String name, int[] dmns)
    {
      JLabel label = new JLabel(name, SwingConstants.RIGHT);
      label.setFont(new Font(this.label_font, Font.PLAIN, this.label_size));
      label.setBounds(dmns[0], dmns[1], dmns[2], dmns[3]);
      label.setForeground(Color.white);

      return (label);
    }
    
    private JButton initbutton(String name, int[] dmns, int style, JLabel frame, compute comp)
    {
      JButton button = new JButton(name);  
      button.setFont(new Font(this.button_font, Font.PLAIN, this.button_size));
      button.setBounds(dmns[0], dmns[1], dmns[2], dmns[3]);
      button.setFocusPainted(false);

      switch (style)
      {
          case 1: 
            button.setBackground(Color.white);
            break;
          
          case 2: 
            button.setBackground(Color.orange);
            break;
          
          case 3: 
            button.setBackground(Color.darkGray);    
            button.setForeground(Color.white);
            break;
      }

      button.addActionListener(new ActionListener()
      {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
          comp.addc(name);
          updateframe(frame, comp);
        }
      });

      return (button);
    }

    private void updateframe(JLabel frame, compute comp)
    {
      if ((comp.gettotal() % 1 == 0)) frame.setText(String.valueOf((int)comp.gettotal()));
      else frame.setText(String.valueOf(comp.gettotal()));
    }
    
    frame(String t)
    {
      f_ = new JFrame(t);
      f_.setLayout(null); 

      switch(t)
      {
        case "calculator":
          compute comp = new compute();
          String[] labels = 
          {
            "AC#1", "!#1", "^#1", "/#2", 
            "7#3", "8#3", "9#3", "*#2",
            "4#3", "5#3", "6#3", "-#2",
            "1#3", "2#3", "3#3", "+#2",
            "0#3", " ", ".#3", "=#2"
          };
          
          f_.setSize(300, 430);
          f_.getContentPane().setBackground(Color.black);

          int[] l_dmns = {0, 0, (f_.getWidth() - 30), 100};
          JLabel frame = this.initlabel("null", l_dmns);
          f_.add(frame);
          
          int gx = 0, gy = -1;
          for (int i = 0; i < labels.length; i++)
          {
            if (gx % 4 == 0) gx = 0; 
            if (gx == 0) gy += 1;

            String istr = "";
            int isty = 1;

            try
            {
              istr = labels[i].substring(0, labels[i].indexOf('#'));
              isty = Integer.parseInt(labels[i].substring((labels[i].indexOf('#') + 1)));
            } catch(Exception e) {}
            
            try
            {
              if (labels[i + 1].equals(" ")) 
              {
                int[] b_dmns = {(gx * 75), (100 + (gy * 60)), 150, 60};
                f_.add(this.initbutton(istr, b_dmns, isty, frame, comp)); 
                gx += 2; 
                i += 1; 
                continue;
              }  
            } catch(Exception e) {}

            int[] b_dmns = {(gx * 75), (100 + (gy * 60)), 75, 60};
            f_.add(this.initbutton(istr, b_dmns, isty, frame, comp)); 
            gx += 1;
          }
                 
          f_.setVisible(true);
          updateframe(frame, comp);
          break;
      }
    } 
    
    JFrame getframe()
    {
      return (f_);
    }

    String label_font = "Open Sans", button_font = "Open Sans";
    int label_size = 32, button_size = 18;
  }
    
  class compute
  {
    private double total;
    private String hashstring;

    compute() 
    {
      this.total = 0;
    }

    private void calculate()
    {
    }
    
    void addc(String n)
    {
    }

    double gettotal()
    {
      return (this.total);
    }
  }
  
  class input implements KeyListener
  {
    @Override
    public void keyPressed(KeyEvent e) 
    {
    }

    @Override
    public void keyReleased(KeyEvent e) 
    {
    }

    @Override
    public void keyTyped(KeyEvent e) 
    {
    }
  }
}