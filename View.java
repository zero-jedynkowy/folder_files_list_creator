import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class View extends JPanel
{

    JTabbedPane tabbedModes;
    JPanel createListPanel;
    JPanel readListPanel;

    public View()
    {
        super();
        this.setLayout(new BorderLayout());
        
        //SET ELEMENTS' APPEARANCE
        this.setTabbedModes();

        this.setVisible(true);
    }

    public void setTabbedModes()
    {
        this.tabbedModes = new JTabbedPane();
        this.createListPanel = this.createNewPanel("Tworzenie listy");
        this.readListPanel = this.createNewPanel("Odczyt listy");
        this.tabbedModes.addTab("Tworzenie listy", this.createListPanel);
        this.tabbedModes.addTab("Odczyt listy", this.readListPanel);
        this.add(this.tabbedModes, BorderLayout.CENTER);
        this.changeFontSize(this.tabbedModes, 15);
        this.tabbedModes.setVisible(true);
    }

    public JPanel createNewPanel(String text)
    {
        JPanel panel = new JPanel(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        return panel;
    }

    public void changeFontSize(Component comp, int newSize)
    {
        comp.setFont(comp.getFont().deriveFont((float) newSize));
    }
}
