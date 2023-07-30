import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.io.File;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

public class View extends JPanel
{
    //
    JTabbedPane tabbedModes;
        CreateListModeView createListPanel;
        JPanel readListPanel;

    //
    

    //
    

    //
    JLabel chooseFilesToReadLabel;
    JPanel chooseFilesToReadPanel;
        JButton chooseFilesToReadButton;
        JButton resetFilesToReadButton;
    JLabel chooseFilesToReadListLabel;
    JScrollPane scrollChooseFilesToReadList;
        JList<String> chooseFilesToReadList;
    JButton generateButton;
    
    
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
        this.createListPanel = new CreateListModeView();
        //this.readListPanel = this.createNewPanel("Odczyt listy");
        
        //
        this.tabbedModes.addTab("Tworzenie listy", this.createListPanel);
        //this.tabbedModes.addTab("Odczyt listy", this.readListPanel);
        this.add(this.tabbedModes, BorderLayout.CENTER);
        
        //
        DefaultPanelModeView.changeFontSize(this.tabbedModes, 15);
        this.tabbedModes.setVisible(true);
    }

    

    

    public JPanel createNewPanel(String text)
    {
        JPanel panel = new JPanel(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        return panel;
    }

    //READ LIST
    // public void setReadingList()
    // {
    //     //
    //     this.chooseFilesToReadLabel = new JLabel("Wybierz pliki do wczytania:", JLabel.CENTER);
    //     this.chooseFilesToReadLabel.setVisible(true);
    //     this.chooseFilesToReadLabel.setAlignmentX(CENTER_ALIGNMENT);
    //     DefaultPanelModeView.changeFontSize(this.chooseFilesToReadLabel, 20);
    //     this.chooseFilesToReadLabel.setHorizontalAlignment(SwingConstants.CENTER);
    //     this.chooseFilesToReadLabel.setMaximumSize(new Dimension(400, 50));

    //     //
    //     this.chooseFilesToReadPanel = new JPanel();
    //     this.chooseFilesToReadPanel.setLayout(new BoxLayout(this.chooseFilesToReadPanel, BoxLayout.X_AXIS));
    //     this.chooseFilesToReadPanel.setMaximumSize(new Dimension(400, 50));
        
    //     //
    //     this.chooseFilesToReadButton = new JButton("Wybierz pliki");
    //     DefaultPanelModeView.changeFontSize(this.chooseFilesToReadButton, 15);
    //     this.chooseFilesToReadButton.setMaximumSize(new Dimension(200, 50));
    //     this.chooseFilesToReadButton.setAlignmentX(CENTER_ALIGNMENT);
        
    //     //
    //     this.resetFilesToReadButton = new JButton("Reset");
    //     DefaultPanelModeView.changeFontSize(this.resetFilesToReadButton, 15);
    //     this.resetFilesToReadButton.setMaximumSize(new Dimension(200, 50));
    //     this.resetFilesToReadButton.setAlignmentX(CENTER_ALIGNMENT);
        
    //     //
    //     this.chooseFilesToReadListLabel = new JLabel("Wybrane pliki:", JLabel.CENTER);
    //     this.chooseFilesToReadListLabel.setVisible(true);
    //     this.chooseFilesToReadListLabel.setAlignmentX(CENTER_ALIGNMENT);
    //     DefaultPanelModeView.changeFontSize(this.chooseFilesToReadListLabel, 20);
    //     this.chooseFilesToReadListLabel.setHorizontalAlignment(SwingConstants.CENTER);
    //     this.chooseFilesToReadListLabel.setMaximumSize(new Dimension(400, 50));

    //     //
    //     this.chooseFilesToReadList = new JList<String>(new String[]{"BRAK"});
    //     this.chooseFilesToReadList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
    //     this.chooseFilesToReadList.setLayoutOrientation(JList.VERTICAL);
    //     this.scrollChooseFilesToReadList = new JScrollPane(this.chooseFilesToReadList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    //     this.scrollChooseFilesToReadList.setMaximumSize(new Dimension(400, 150));

    //     //
    //     this.generateButton = new JButton("Generuj liste");
    //     DefaultPanelModeView.changeFontSize(this.generateButton, 15);
    //     this.generateButton.setMaximumSize(new Dimension(400, 50));
    //     this.generateButton.setAlignmentX(CENTER_ALIGNMENT);

    //     //  
    //     this.readListPanel.add(Box.createVerticalStrut(10));
    //     this.readListPanel.add(this.chooseFilesToReadLabel);
    //     this.readListPanel.add(Box.createVerticalStrut(10));
    //     this.readListPanel.add(this.chooseFilesToReadPanel);
    //         this.chooseFilesToReadPanel.add(this.chooseFilesToReadButton);
    //         this.chooseFilesToReadPanel.add(Box.createHorizontalStrut(10));
    //         this.chooseFilesToReadPanel.add(this.resetFilesToReadButton);
    //     this.readListPanel.add(Box.createVerticalStrut(10));
    //     this.readListPanel.add(this.chooseFilesToReadListLabel);
    //     this.readListPanel.add(Box.createVerticalStrut(10));
    //     this.readListPanel.add(this.scrollChooseFilesToReadList);
    //     this.readListPanel.add(Box.createVerticalStrut(10));
    //     this.readListPanel.add(this.generateButton);
    // }
}
