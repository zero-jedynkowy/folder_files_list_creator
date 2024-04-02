import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;

import org.json.*;

public class Main extends JFrame
{
    final static int WIDTH = 550;
    final static int HEIGHT = 550;
    public static Main mainWindow;
    Tabs tabs;

    public Main()
    {
        super();
        Main.mainWindow = this;
        this.setTitle("FFLC - Folder Files List Creator");
        this.setLayout(new BorderLayout());
        this.setSize(WIDTH, HEIGHT);
        this.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            this.tabs = new Tabs("Tabs");
            this.tabs.init();
            this.add(this.tabs.component);
            Settings.loadSettings();
            Language.loadLanguage((int)Settings.getSetting("SettingsModule", "language"));
            Language.setLanguage();
        this.revalidate();
        this.setVisible(true);
    }
    
    public static void main(String args[])
    {
        new Main();
    }
}