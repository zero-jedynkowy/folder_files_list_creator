import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

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
    //
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
        
        //
        this.setTabbedModes();
        this.setChoosingFolder();

        this.setVisible(true);
    }

    public void setTabbedModes()
    {
        //
        this.tabbedModes = new JTabbedPane();
        this.createListPanel = this.createNewPanel("Tworzenie listy");
        this.readListPanel = this.createNewPanel("Odczyt listy");
        
        //
        this.tabbedModes.addTab("Tworzenie listy", this.createListPanel);
        this.tabbedModes.addTab("Odczyt listy", this.readListPanel);
        this.add(this.tabbedModes, BorderLayout.CENTER);
        
        //
        this.changeFontSize(this.tabbedModes, 15);
        this.tabbedModes.setVisible(true);
    }

    public void setChoosingFolder()
    {
        //
        this.choosingFolderLabel = new JLabel("Wybierz folder do zrobienia listy:", JLabel.CENTER);
        this.choosingFolderLabel.setVisible(true);
        this.choosingFolderLabel.setAlignmentX(CENTER_ALIGNMENT);
        this.changeFontSize(this.choosingFolderLabel, 20);
        this.choosingFolderLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        //
        this.choosingFolderPathPanel = new JPanel();
        this.choosingFolderPathPanel.setLayout(new BoxLayout(this.choosingFolderPathPanel, BoxLayout.X_AXIS));
        this.choosingFolderPathPanel.setVisible(true);
        this.choosingFolderPathPanel.setMaximumSize(new Dimension(400, 50));

        //
        this.choosingFolderButton = new JButton("Wybierz folder");
        this.changeFontSize(this.choosingFolderButton, 15);
        this.choosingFolderButton.setMaximumSize(new Dimension(100, 50));

        //
        this.choosingFolderPathLabel = new JLabel("   BRAK");
        this.scrollChoosingFolderPathLabel = new JScrollPane(this.choosingFolderPathLabel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.changeFontSize(this.choosingFolderPathLabel, 15);
        this.changeFontSize(this.scrollChoosingFolderPathLabel, 15);
        this.choosingFolderPathLabel.setVisible(true);
        

        //
        this.createListPanel.add(Box.createVerticalStrut(10));
        this.createListPanel.add(this.choosingFolderLabel);
        this.createListPanel.add(Box.createVerticalStrut(10));
        this.createListPanel.add(this.choosingFolderPathPanel);
            this.choosingFolderPathPanel.add(Box.createHorizontalStrut(10));
            this.choosingFolderPathPanel.add(this.choosingFolderButton);
            this.choosingFolderPathPanel.add(Box.createHorizontalStrut(10));
            this.choosingFolderPathPanel.add(this.scrollChoosingFolderPathLabel);
    }


    //
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
