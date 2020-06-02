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


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import com.google.gson.Gson;


public class App {
    public static void main( String[] args ) {

      BufferedReader reader = null;
      reader = new BufferedReader(new InputStreamReader(App.class.getClassLoader().getResourceAsStream("user.json")));

/*
      try {
//        reader = new BufferedReader(new FileReader("user3.json"));
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
      }
*/

      Gson gson = new Gson();
      User user = gson.fromJson(reader, User.class);

      String id = user.getId();
      String pc = user.getPc();
      String mobile = user.getMobile();


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
        sarray[7]="ID: "+id;
        sarray[8]="PC: "+pc;
        sarray[9]="Mobile: "+mobile;

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
        frame.setSize( 500, 400 );
        frame.setLocationRelativeTo(null);
        frame.setVisible( true );
    }
}