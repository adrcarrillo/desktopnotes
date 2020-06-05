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
import java.io.FileReader;
import java.io.InputStreamReader;
import com.google.gson.Gson;

import java.io.InputStream;

public class App {
    public static void main( String[] args ) {

      //Gson
      BufferedReader reader = null;

      try {
// It works on the folder
//      reader = new BufferedReader(new FileReader("user.json"));

// It works with Gradle as resources!
//      reader = new BufferedReader(new InputStreamReader(App.class.getClassLoader().getResourceAsStream("user.json")));

// It works with Gradle as resources!
//        InputStream inputStream = App.class.getClassLoader().getResourceAsStream("user.json");
//        reader = new BufferedReader(new InputStreamReader(inputStream));

       reader = new BufferedReader(new FileReader("C://Users/Public/user.json"));

//      reader = new BufferedReader(new FileReader("user.json"));

      }
      catch (Exception e) {
        e.printStackTrace();
        return;
      }
      finally {
        // Cleanup here is always executed
      }

      Gson gson = new Gson();
      User user = gson.fromJson(reader, User.class);
      String id = user.getId();
      String pc = user.getPc();
      String mobile = user.getMobile();

      //Look and Feel
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



        JFrame frame = new JFrame( "Desktop Notes" );
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

        int localhour = Integer.parseInt(localTimeStringGreeting);

        //Test
//        localhour = 6;

        //Label
        JLabel label = new JLabel();

        //String Array
        String sarray[] = new String[10];
        sarray[0]="Hi!, "+getGreeting(localhour); //With method to get Greeting
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

        frame.add(label,BorderLayout.CENTER);

//        frame.setLayout(new FlowLayout());
//        ImageIcon img = new ImageIcon(getClass().getResource("ico.png")); //Error

        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("ico.png"));
        frame.setIconImage(img.getImage());

        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.setSize( 300, 200 );
        frame.setLocationRelativeTo(null);
        frame.setVisible( true );
    }

    public static String getGreeting(int x) {
      String greeting = new String();
        if (6<=x&&x<=11) {
          greeting = "\nGood morning!\n";
        } else if (12<=x&&x<=18) {
          greeting = "\nGood afternoon!\n";
        } else if (19<=x&&x<=22) {
          greeting = "\nGood night!\n";
        } else {
            greeting = "\nGo to sleep!\n";
        }
      return greeting;
    }
}
