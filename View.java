import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class View extends JPanel
{
    JTabbedPane tabbedModules;
    
    Module1 module1;
    // Module2 module2;
    // Module3 module3;

    public View(int language)
    {
        super();
        this.setLayout(new BorderLayout());
        this.tabbedModules = new JTabbedPane();
        this.setTabbedModules();
        this.add(this.tabbedModules, BorderLayout.CENTER);
        DefaultPanelModeView.changeFontSize(this.tabbedModules, 15);
        this.tabbedModules.setVisible(true);
        this.setVisible(true);
    }

    public void setTabbedModules()
    {
        //MODULE 1
        this.module1 = new Module1((Main)this.getParent());
        this.module1.init();
        this.tabbedModules.addTab("Tworzenie listy", this.module1);

        // //MODULE 2
        // this.module2 = new Module2((Main)this.getParent());
        // this.module2.init();
        // this.tabbedModules.addTab("Wyświetlenie list", this.module2);

        // //MODULE 3
        // this.module3 = new Module3((Main)this.getParent());
        // this.module3.init();
        // this.tabbedModules.addTab("Ustawienia", this.module3);
    }
}
