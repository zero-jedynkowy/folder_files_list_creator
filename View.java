import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class View extends JPanel
{
    //
    JTabbedPane tabbedModes;
        Module1 createListPanel;

    public View()
    {
        super();
        this.setLayout(new BorderLayout());
        
        //
        this.setTabbedModes();
        this.createListPanel.setView();
        this.createListPanel.addElements();
        //this.setReadingList();

        this.setVisible(true);
    }

    //CREATE LIST 
    public void setTabbedModes()
    {
        //
        this.tabbedModes = new JTabbedPane();
        this.createListPanel = new Module1();
        
        //
        this.tabbedModes.addTab("Tworzenie listy", this.createListPanel);
        this.add(this.tabbedModes, BorderLayout.CENTER);
        
        //
        DefaultPanelModeView.changeFontSize(this.tabbedModes, 15);
        this.tabbedModes.setVisible(true);
    }
}
