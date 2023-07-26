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
        JPanel createListPanel;
        JPanel readListPanel;

    //
    JLabel choosingFolderLabel;
    JPanel choosingFolderPathPanel;
        JButton choosingFolderButton;
        JScrollPane scrollChoosingFolderPathLabel;
            JLabel choosingFolderPathLabel;

    //
    JLabel statusLabel;
    JLabel currectProcessObjectLabel;
    JScrollPane scrollCurrectProcessObjectPath;
            JLabel currectProcessObjectPathLabel;
    
    //
    JButton startButton;

    //
    JLabel chooseFilesToReadLabel;
    JButton chooseFilesToReadButton;
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
        this.setChoosingFolder();
        this.setReadingList();

        this.setVisible(true);
    }

    //CREATE LIST 
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
        this.choosingFolderLabel.setMaximumSize(new Dimension(400, 50));
        
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
        this.statusLabel = new JLabel("Aktualny status: BRAK DZIAŁANIA", JLabel.CENTER);
        this.statusLabel.setForeground(Color.RED);
        this.statusLabel.setVisible(true);
        this.statusLabel.setAlignmentX(CENTER_ALIGNMENT);
        this.changeFontSize(this.statusLabel, 20);
        this.statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.statusLabel.setPreferredSize(new Dimension(400, 50));

        //
        this.currectProcessObjectLabel = new JLabel("Aktualnie przetwarzany plik:", JLabel.CENTER);
        this.currectProcessObjectLabel.setVisible(true);
        this.currectProcessObjectLabel.setAlignmentX(CENTER_ALIGNMENT);
        this.changeFontSize(this.currectProcessObjectLabel, 20);
        this.currectProcessObjectLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.currectProcessObjectLabel.setPreferredSize(new Dimension(400, 50));

        //
        this.currectProcessObjectPathLabel = new JLabel("\s\s\sBRAK");
        this.scrollCurrectProcessObjectPath = new JScrollPane(this.currectProcessObjectPathLabel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.changeFontSize(this.currectProcessObjectPathLabel, 15);
        this.changeFontSize(this.scrollCurrectProcessObjectPath, 15);
        this.currectProcessObjectPathLabel.setVisible(true);
        this.scrollCurrectProcessObjectPath.setMaximumSize(new Dimension(400, 50));

        //
        this.startButton = new JButton("START");
        this.changeFontSize(this.startButton, 15);
        this.startButton.setMaximumSize(new Dimension(400, 50));
        this.startButton.setAlignmentX(CENTER_ALIGNMENT);

        //
        this.createListPanel.add(Box.createVerticalStrut(10));
        this.createListPanel.add(this.choosingFolderLabel);
        this.createListPanel.add(Box.createVerticalStrut(10));
        this.createListPanel.add(this.choosingFolderPathPanel);
            this.choosingFolderPathPanel.add(Box.createHorizontalStrut(10));
            this.choosingFolderPathPanel.add(this.choosingFolderButton);
            this.choosingFolderPathPanel.add(Box.createHorizontalStrut(10));
            this.choosingFolderPathPanel.add(this.scrollChoosingFolderPathLabel);
        this.createListPanel.add(Box.createVerticalStrut(10));
        JSeparator x = new JSeparator();
        x.setMaximumSize(new Dimension(450, 1));
        this.createListPanel.add(x);
        this.createListPanel.add(Box.createVerticalStrut(10));
        this.createListPanel.add(this.statusLabel);
        this.createListPanel.add(Box.createVerticalStrut(10));
        JSeparator y = new JSeparator();
        y.setMaximumSize(new Dimension(450, 1));
        this.createListPanel.add(y);
        this.createListPanel.add(Box.createVerticalStrut(10));
        this.createListPanel.add(this.currectProcessObjectLabel);
        this.createListPanel.add(Box.createVerticalStrut(10));
        this.createListPanel.add(this.scrollCurrectProcessObjectPath);
        this.createListPanel.add(Box.createVerticalStrut(10));
        JSeparator z = new JSeparator();
        z.setMaximumSize(new Dimension(450, 1));
        this.createListPanel.add(z);
        this.createListPanel.add(Box.createVerticalStrut(10));
        this.createListPanel.add(this.startButton);
    }

    public void updateChoosingFolderPathLabel(File newPath)
    {
        if(newPath == null) this.choosingFolderPathLabel.setText("\s\s\sBRAK");
        else this.choosingFolderPathLabel.setText("   " + newPath.getPath() + "  ");
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

    //READ LIST
    public void setReadingList()
    {
        //
        this.chooseFilesToReadLabel = new JLabel("Wybierz pliki do wczytania:", JLabel.CENTER);
        this.chooseFilesToReadLabel.setVisible(true);
        this.chooseFilesToReadLabel.setAlignmentX(CENTER_ALIGNMENT);
        this.changeFontSize(this.chooseFilesToReadLabel, 20);
        this.chooseFilesToReadLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.chooseFilesToReadLabel.setMaximumSize(new Dimension(400, 50));

        //
        this.chooseFilesToReadButton = new JButton("Wybierz pliki");
        this.changeFontSize(this.chooseFilesToReadButton, 15);
        this.chooseFilesToReadButton.setMaximumSize(new Dimension(400, 50));
        this.chooseFilesToReadButton.setAlignmentX(CENTER_ALIGNMENT);

        //
        this.chooseFilesToReadListLabel = new JLabel("Wybrane pliki:", JLabel.CENTER);
        this.chooseFilesToReadListLabel.setVisible(true);
        this.chooseFilesToReadListLabel.setAlignmentX(CENTER_ALIGNMENT);
        this.changeFontSize(this.chooseFilesToReadListLabel, 20);
        this.chooseFilesToReadListLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.chooseFilesToReadListLabel.setMaximumSize(new Dimension(400, 50));

        //
        this.chooseFilesToReadList = new JList<String>(new String[]{"BRAK"});
        this.chooseFilesToReadList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        this.chooseFilesToReadList.setLayoutOrientation(JList.VERTICAL);
        this.scrollChooseFilesToReadList = new JScrollPane(this.chooseFilesToReadList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.scrollChooseFilesToReadList.setMaximumSize(new Dimension(400, 150));

        //
        this.generateButton = new JButton("Generuj liste");
        this.changeFontSize(this.generateButton, 15);
        this.generateButton.setMaximumSize(new Dimension(400, 50));
        this.generateButton.setAlignmentX(CENTER_ALIGNMENT);

        //  
        this.readListPanel.add(Box.createVerticalStrut(10));
        this.readListPanel.add(this.chooseFilesToReadLabel);
        this.readListPanel.add(Box.createVerticalStrut(10));
        this.readListPanel.add(this.chooseFilesToReadButton);
        this.readListPanel.add(Box.createVerticalStrut(10));
        this.readListPanel.add(this.chooseFilesToReadListLabel);
        this.readListPanel.add(Box.createVerticalStrut(10));
        this.readListPanel.add(this.scrollChooseFilesToReadList);
        this.readListPanel.add(Box.createVerticalStrut(10));
        this.readListPanel.add(this.generateButton);
    }
}
