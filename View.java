import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

public class View extends JPanel
{

    JTabbedPane tabbedModes;
    JPanel createListPanel;
    JPanel readListPanel;

    //CREATE LIST PANEL'S ELEMENTS
    JLabel chooseFolderLabel;
    JButton chooseFolderButton;
    JLabel chooseFolderPathLabel;

    public View()
    {
        super();
        this.setLayout(new BorderLayout());
        
        //SET ELEMENTS' APPEARANCE
        this.setTabbedModes();
        this.setCreateListModePanel();

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

    public void setCreateListModePanel()
    {
        this.chooseFolderLabel = new JLabel("Wybierz folder do zrobienia listy:", JLabel.CENTER);
        this.chooseFolderLabel.setVisible(true);
        this.chooseFolderLabel.setAlignmentX(CENTER_ALIGNMENT);
        this.changeFontSize(this.chooseFolderLabel, 20);
        this.chooseFolderLabel.setHorizontalAlignment(SwingConstants.CENTER);



        this.createListPanel.add(Box.createVerticalStrut(10));
        this.createListPanel.add(this.chooseFolderLabel);
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
