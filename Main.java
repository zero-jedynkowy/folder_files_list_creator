import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.json.*;

public class Main extends JFrame
{
    final static int WIDTH = 500;
    final static int HEIGHT = 500;

    JSONObject settings;
    JSONObject languageContent;
    
    JPanel mainView;
        JTabbedPane tabbedModules;
            AbstractModule module1;
            AbstractModule module2;
            AbstractModule module3;
            AbstractModule module4;
    
    public Main()
    {
        super();
        this.setTitle("FFLC - Folder Files List Creator");
        this.setLayout(new BorderLayout());
        this.setSize(WIDTH, HEIGHT);
        this.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            this.loadSettings();
            this.loadLanguage();
            this.setLook();
            this.setView();
            this.setLanguage();
        this.setVisible(true);
    }
    
    void loadSettings()
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
        this.settings = new JSONObject(data);
    }

    void loadLanguage()
    {
        String fileName = "";
        switch(this.settings.getInt("language"))
        {
            case 0:
                fileName = "english.json";
                break;
            case 1:
                fileName = "polski.json";
                break;
        }
        String data = "";
        try 
        {
            File myObj = new File(fileName);
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
        this.languageContent = new JSONObject(data);
    }

    public boolean setLook()
    {
        
        try 
        {
            if(this.settings.getInt("style") == 1)
            {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            }
            else
            {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            }
            return true;
        } 
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) 
        {
            JOptionPane.showMessageDialog(this, "There is no possibility to set this look!", "Error!", JOptionPane.ERROR_MESSAGE);
        }
        SwingUtilities.updateComponentTreeUI(this);
        return false;
    }

    void setView()
    {
        this.mainView = new JPanel();
        this.mainView.setLayout(new BorderLayout());
        this.tabbedModules = new JTabbedPane();
        this.setTabbedModules();
        this.mainView.add(this.tabbedModules, BorderLayout.CENTER);
        AbstractModule.changeFontSize(this.tabbedModules, 15);
        this.tabbedModules.setVisible(true);
        this.mainView.setVisible(true);
        this.add(this.mainView, BorderLayout.CENTER);
    }

    void setLanguage()
    {
        this.module1.setLanguage(languageContent.getJSONObject("CreateListModule"));
        this.tabbedModules.setTitleAt(0, languageContent.getJSONObject("CreateListModule").getJSONArray("titleModule").getString(0));
    
        // this.module2.setLanguage(languageContent.getJSONObject("CreateListModule"));
        // this.tabbedModules.setTitleAt(0, languageContent.getJSONObject("CreateListModule").getJSONArray("titleModule").getString(0));
    

        // this.module2.setLanguage(languageContent.getJSONObject("CreateListModule"));
        // this.tabbedModules.setTitleAt(1, languageContent.getJSONObject("CreateListModule").getJSONArray("titleModule").getString(0));
    


        this.module4.setLanguage(languageContent.getJSONObject("Settings"));
        this.tabbedModules.setTitleAt(3, languageContent.getJSONObject("Settings").getJSONArray("titleModule").getString(0));
    
    }

    void setTabbedModules()
    {
        this.module1 = new CreateListModule(this);
        this.module1.init();
        this.tabbedModules.addTab("Tworzenie listy", this.module1);

        this.module2 = new ShowListModule(this);
        this.module2.init();
        this.tabbedModules.addTab("Lista", this.module2);

        this.module3 = new AboutProgramModule(this);
        this.module3.init();
        this.tabbedModules.addTab("O programie", this.module3);

        this.module4 = new Settings(this);
        this.module4.init();
        this.tabbedModules.addTab("Ustawienia", this.module4);
    }


    public static void main(String args[])
    {
        new Main();
    }
}