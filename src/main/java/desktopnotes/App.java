package desktopnotes;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.*;
import java.awt.BorderLayout;
//import java.awt.FlowLayout;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.IsoFields;
import java.time.format.DateTimeFormatter;

import com.formdev.flatlaf.*;

public class App {
    public static void main( String[] args ) {

      try {
              // Set cross-platform Java L&F (also called "Metal")
//          UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
//          UIManager.setLookAndFeel(new FlatLightLaf());
//          UIManager.setLookAndFeel(new FlatIntelliJLaf());
            UIManager.setLookAndFeel(new FlatDarculaLaf());
      }
      catch (Exception e) {
         System.err.println( "Failed to initialize LaF" );
      }



        JFrame frame = new JFrame( "Hello!" );
//        JLabel label = new JLabel("Hello, Java!", JLabel.CENTER );


        ZonedDateTime now = ZonedDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
        ZonedDateTime cn = ZonedDateTime.ofInstant(Instant.now(), ZoneId.of("Asia/Shanghai"));

        //Create formatter
        DateTimeFormatter timeFormated = DateTimeFormatter.ofPattern("HH:mm:ss");
        //Get formatted String
        String localTimeStringLocal = timeFormated.format(now);
        String localTimeStringAsia = timeFormated.format(cn);

        //Greeting
        DateTimeFormatter timeFormatedGreeting = DateTimeFormatter.ofPattern("H");
        String localTimeStringGreeting = timeFormatedGreeting.format(now);

        //Label
        JLabel label = new JLabel();

        //String Array
        String sarray[] = new String[10];
        sarray[0]="Hi";
        sarray[1]="";
        sarray[2]="Date: "+now.toLocalDate();
        sarray[3]="Mexico Time: "+localTimeStringLocal;
        sarray[4]="Shanghai Time: "+localTimeStringAsia;
        sarray[5]="Week: "+now.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
        sarray[6]="";
        sarray[7]="User: ";
        sarray[8]="PC: ";
        sarray[9]="Mobile: ";

        String valueLabel = "";
        for (int j=0 ; j < sarray.length; j++){
          valueLabel += "<html>"+sarray[j]+"<br/>";
        }
        label.setText(valueLabel+"</html");

        frame.add(label,BorderLayout.NORTH);

//        frame.setLayout(new FlowLayout());
//        ImageIcon img = new ImageIcon(getClass().getResource("ico.png")); //Error

        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("ico.png"));
        frame.setIconImage(img.getImage());

        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.setSize( 250, 200 );
        frame.setLocationRelativeTo(null);
        frame.setVisible( true );
    }
}
