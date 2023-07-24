import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

public class View extends JPanel
{
    //MAIN PANELS OF THE PROGRAMME
    JTabbedPane tabbedModes;
        JPanel createListPanel;
        JPanel readListPanel;

    //
    JLabel choosingFolderLabel;
    JPanel choosingFolderPathPanel;
        JButton choosingFolderButton;
        JScrollPane scrollChoosingFolderPathLabel;
            JLabel choosingFolderPathLabel;

    
    public View()
    {
        super();
        this.setLayout(new BorderLayout());
        
        //SET ELEMENTS' APPEARANCE
        this.setTabbedModes();
        this.setChoosingFolder();

        this.setVisible(true);
    }

    public void setTabbedModes()
    {
        //SET VARIABLES
        this.tabbedModes = new JTabbedPane();
        this.createListPanel = this.createNewPanel("Tworzenie listy");
        this.readListPanel = this.createNewPanel("Odczyt listy");
        
        //ADD MODES' PANELS
        this.tabbedModes.addTab("Tworzenie listy", this.createListPanel);
        this.tabbedModes.addTab("Odczyt listy", this.readListPanel);
        this.add(this.tabbedModes, BorderLayout.CENTER);
        
        //FINAL INTRUCTIONS
        this.changeFontSize(this.tabbedModes, 15);
        this.tabbedModes.setVisible(true);
    }

    public void setChoosingFolder()
    {
        //TITLE OF THE CHOOSING FOLDER TO CREATE THE LIST
        this.choosingFolderLabel = new JLabel("Wybierz folder do zrobienia listy:", JLabel.CENTER);
        this.choosingFolderLabel.setVisible(true);
        this.choosingFolderLabel.setAlignmentX(CENTER_ALIGNMENT);
        this.changeFontSize(this.choosingFolderLabel, 20);
        this.choosingFolderLabel.setHorizontalAlignment(SwingConstants.CENTER);

        //

        //ADD ALL ELEMENTS
        this.createListPanel.add(Box.createVerticalStrut(10));
        this.createListPanel.add(this.choosingFolderLabel);
    }


    //OTHER METHODS
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
