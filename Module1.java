import java.awt.Color;
import java.awt.Dimension;
import java.io.File;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class Module1 extends DefaultPanelModeView
{
    JLabel label_choosingFolder;
    JPanel panel_choosingFolderPath;
        JButton button_choosingFolder;
        JScrollPane scrollPane_choosingFolderPathScroll;
            JLabel label_choosingFolderPath;

    JLabel label_status;
    JLabel label_currectProcessedObject;
        JScrollPane  scrollPane_currectProcessObjectPathScroll;
            JLabel currectProcessObjectPathLabel;
            
           
    JButton startButton;

    @Override
    public void setView()
    {
        //
        this.label_choosingFolder = new JLabel("Wybierz folder do zrobienia listy:", JLabel.CENTER);
        this.label_choosingFolder.setVisible(true);
        this.label_choosingFolder.setAlignmentX(CENTER_ALIGNMENT);
        DefaultPanelModeView.changeFontSize(this.label_choosingFolder, 20);
        this.label_choosingFolder.setHorizontalAlignment(SwingConstants.CENTER);
        this.label_choosingFolder.setMaximumSize(new Dimension(400, 50));
        
        //
        this.panel_choosingFolderPath = new JPanel();
        this.panel_choosingFolderPath.setLayout(new BoxLayout(this.panel_choosingFolderPath, BoxLayout.X_AXIS));
        this.panel_choosingFolderPath.setVisible(true);
        this.panel_choosingFolderPath.setMaximumSize(new Dimension(400, 50));

        //
        this.button_choosingFolder = new JButton("Wybierz folder");
        DefaultPanelModeView.changeFontSize(this.button_choosingFolder, 15);
        this.button_choosingFolder.setMaximumSize(new Dimension(100, 50));

        //
        this.label_choosingFolderPath = new JLabel("   BRAK");
        this.scrollPane_choosingFolderPathScroll = new JScrollPane(this.label_choosingFolderPath, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        DefaultPanelModeView.changeFontSize(this.label_choosingFolderPath, 15);
        DefaultPanelModeView.changeFontSize(this.scrollPane_choosingFolderPathScroll, 15);
        this.label_choosingFolderPath.setVisible(true);
        
        //
        this.label_status = new JLabel("Aktualny status: BRAK DZIAŁANIA", JLabel.CENTER);
        this.label_status.setForeground(Color.RED);
        this.label_status.setVisible(true);
        this.label_status.setAlignmentX(CENTER_ALIGNMENT);
        DefaultPanelModeView.changeFontSize(this.label_status, 20);
        this.label_status.setHorizontalAlignment(SwingConstants.CENTER);
        this.label_status.setPreferredSize(new Dimension(400, 50));

        //
        this.label_currectProcessedObject = new JLabel("Aktualnie przetwarzany plik:", JLabel.CENTER);
        this.label_currectProcessedObject.setVisible(true);
        this.label_currectProcessedObject.setAlignmentX(CENTER_ALIGNMENT);
        DefaultPanelModeView.changeFontSize(this.label_currectProcessedObject, 20);
        this.label_currectProcessedObject.setHorizontalAlignment(SwingConstants.CENTER);
        this.label_currectProcessedObject.setPreferredSize(new Dimension(400, 50));

        //
        this.currectProcessObjectPathLabel = new JLabel("\s\s\sBRAK");
        this.scrollPane_currectProcessObjectPathScroll = new JScrollPane(this.currectProcessObjectPathLabel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        DefaultPanelModeView.changeFontSize(this.currectProcessObjectPathLabel, 15);
        DefaultPanelModeView.changeFontSize(this.scrollPane_currectProcessObjectPathScroll, 15);
        this.currectProcessObjectPathLabel.setVisible(true);
        this.scrollPane_currectProcessObjectPathScroll.setMaximumSize(new Dimension(400, 50));

        //
        this.startButton = new JButton("START");
        DefaultPanelModeView.changeFontSize(this.startButton, 15);
        this.startButton.setMaximumSize(new Dimension(400, 50));
        this.startButton.setAlignmentX(CENTER_ALIGNMENT);
    }

    @Override
    public void addElements() 
    {
        this.add(Box.createVerticalStrut(10));
        this.add(this.label_choosingFolder);
        this.add(Box.createVerticalStrut(10));
        this.add(this.panel_choosingFolderPath);
            this.panel_choosingFolderPath.add(Box.createHorizontalStrut(10));
            this.panel_choosingFolderPath.add(this.button_choosingFolder);
            this.panel_choosingFolderPath.add(Box.createHorizontalStrut(10));
            this.panel_choosingFolderPath.add(this.scrollPane_choosingFolderPathScroll);
        this.add(Box.createVerticalStrut(10));
        JSeparator x = new JSeparator();
        x.setMaximumSize(new Dimension(450, 1));
        this.add(x);
        this.add(Box.createVerticalStrut(10));
        this.add(this.label_status);
        this.add(Box.createVerticalStrut(10));
        JSeparator y = new JSeparator();
        y.setMaximumSize(new Dimension(450, 1));
        this.add(y);
        this.add(Box.createVerticalStrut(10));
        this.add(this.label_currectProcessedObject);
        this.add(Box.createVerticalStrut(10));
        this.add(this.scrollPane_currectProcessObjectPathScroll);
        this.add(Box.createVerticalStrut(10));
        JSeparator z = new JSeparator();
        z.setMaximumSize(new Dimension(450, 1));
        this.add(z);
        this.add(Box.createVerticalStrut(10));
        this.add(this.startButton);
    }

    public void updateChoosingFolderPathLabel(File newPath)
    {
        if(newPath == null) this.label_choosingFolderPath.setText("\s\s\sBRAK");
        else this.label_choosingFolderPath.setText("   " + newPath.getPath() + "  ");
    }
}