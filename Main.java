import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.json.*;

public class Main extends JFrame
{
    final static int WIDTH = 500;
    final static int HEIGHT = 500;
    Map<String, Integer> settings;
    View view;
    
    public Main()
    {
        super();
        this.readSettings();
        this.setTitle("File Lister");
        this.setLayout(new BorderLayout());
        this.setSize(WIDTH, HEIGHT);
        this.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSystemLook(this.settings.get("style"));
        this.view = new View(this.settings.get("language"));
        this.add(this.view, BorderLayout.CENTER);
        this.setVisible(true);
    }
    

    public void setSystemLook(int mode)
    {
        if(mode == 1)
        {
            try 
            {
                UIManager.setLookAndFeel(
                UIManager.getSystemLookAndFeelClassName());
            } 
            catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) 
            {
                JOptionPane.showMessageDialog(this,"Nie można załadować motywu systemu","Błąd!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void readSettings()
    {
        String data = "";
        try 
        {
            File myObj = new File("settings.json");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
            data += myReader.nextLine();
        }
        myReader.close();
        } 
        catch (FileNotFoundException e) 
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        this.settings = new HashMap<String, Integer>();
        this.settings.put("style", 0);
        this.settings.put("language", 0);
        JSONObject temp = new JSONObject(data);
        this.settings.put("style", temp.getInt("style"));
        this.settings.put("language", temp.getInt("language"));
    }

    public static void main(String args[])
    {
        new Main();
    }
}