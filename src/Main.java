import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Main {

  public static void main(String[] args) {
    new Main(args);
  }
  
  public Main(String args[]) {

    // main frame settings
    final JFrame frame = new JFrame("MainFrame");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setPreferredSize(new Dimension(800,600));
  
    JPanel panel = new JPanel();
    
    // exit button
    JButton btn1 = new JButton("exit");
    btn1.setToolTipText("exit program");
    btn1.addActionListener(new ActionListener() {
      
      @Override
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
        
      }
    });
    panel.add(btn1);


    // second frame open button
    JButton btn2 = new JButton("open");
    btn2.setToolTipText("open new window");
    btn2.addActionListener( new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        SwingUtilities.invokeLater(() -> {
          showFrameOnOtherScreen(frame);
        });
      }
      
    });
    panel.add(btn2);

    frame.getContentPane().add(panel, BorderLayout.CENTER);

    // show frame
    frame.pack();
    frame.setVisible(true);
  }

  
  private void showFrameOnOtherScreen(JFrame parent) {

    // find a screen different to "parent"
    final GraphicsDevice otherScreen = getOtherScreen(parent);
    
    // create frame with different configuration
    JFrame frameOnOtherScreen = new JFrame(otherScreen.getDefaultConfiguration());
    // basic settings
    frameOnOtherScreen.setTitle("FrameOnOtherScreen");
    frameOnOtherScreen.setPreferredSize(new Dimension(800,600));
    
    JPanel panel = new JPanel();
    
    // add test button
    JButton b1 = new JButton("Test");
    b1.setToolTipText("Test text");
    b1.addActionListener(new ActionListener() {
      
      @Override
      public void actionPerformed(ActionEvent e) {
        
        System.out.println("Test pressed");
        
      }
    });
    panel.add(b1);
    panel.add(new JButton("other"));
    panel.add(new JButton("test"));
    panel.add(new JButton("buttons"));
   
    frameOnOtherScreen.add(panel);
    
    frameOnOtherScreen.pack();
    frameOnOtherScreen.setVisible(true);
  }

 private static GraphicsDevice getOtherScreen(Component component) {
     GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
     if (graphicsEnvironment.getScreenDevices().length == 1) {
         // if there is only one screen, return that one
         return graphicsEnvironment.getScreenDevices()[0];
     }

     GraphicsDevice theWrongOne = component.getGraphicsConfiguration().getDevice();
     for (GraphicsDevice dev : graphicsEnvironment.getScreenDevices()) {
         if (dev != theWrongOne) {
             return dev;
         }
     }

     return null;
 }

}
