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

public class CreateListModeView extends DefaultPanelModeView
{
    JLabel choosingFolderLabel;
    JPanel choosingFolderPathPanel;
    JButton choosingFolderButton;
    JScrollPane scrollChoosingFolderPathLabel;
    JLabel choosingFolderPathLabel;

    JLabel statusLabel;
    JLabel currectProcessObjectLabel;
    JScrollPane scrollCurrectProcessObjectPath;
    JLabel currectProcessObjectPathLabel;
            
           
    JButton startButton;

    @Override
    public void setView()
    {
        //
        this.choosingFolderLabel = new JLabel("Wybierz folder do zrobienia listy:", JLabel.CENTER);
        this.choosingFolderLabel.setVisible(true);
        this.choosingFolderLabel.setAlignmentX(CENTER_ALIGNMENT);
        DefaultPanelModeView.changeFontSize(this.choosingFolderLabel, 20);
        this.choosingFolderLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.choosingFolderLabel.setMaximumSize(new Dimension(400, 50));
        
        //
        this.choosingFolderPathPanel = new JPanel();
        this.choosingFolderPathPanel.setLayout(new BoxLayout(this.choosingFolderPathPanel, BoxLayout.X_AXIS));
        this.choosingFolderPathPanel.setVisible(true);
        this.choosingFolderPathPanel.setMaximumSize(new Dimension(400, 50));

        //
        this.choosingFolderButton = new JButton("Wybierz folder");
        DefaultPanelModeView.changeFontSize(this.choosingFolderButton, 15);
        this.choosingFolderButton.setMaximumSize(new Dimension(100, 50));

        //
        this.choosingFolderPathLabel = new JLabel("   BRAK");
        this.scrollChoosingFolderPathLabel = new JScrollPane(this.choosingFolderPathLabel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        DefaultPanelModeView.changeFontSize(this.choosingFolderPathLabel, 15);
        DefaultPanelModeView.changeFontSize(this.scrollChoosingFolderPathLabel, 15);
        this.choosingFolderPathLabel.setVisible(true);
        
        //
        this.statusLabel = new JLabel("Aktualny status: BRAK DZIAŁANIA", JLabel.CENTER);
        this.statusLabel.setForeground(Color.RED);
        this.statusLabel.setVisible(true);
        this.statusLabel.setAlignmentX(CENTER_ALIGNMENT);
        DefaultPanelModeView.changeFontSize(this.statusLabel, 20);
        this.statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.statusLabel.setPreferredSize(new Dimension(400, 50));

        //
        this.currectProcessObjectLabel = new JLabel("Aktualnie przetwarzany plik:", JLabel.CENTER);
        this.currectProcessObjectLabel.setVisible(true);
        this.currectProcessObjectLabel.setAlignmentX(CENTER_ALIGNMENT);
        DefaultPanelModeView.changeFontSize(this.currectProcessObjectLabel, 20);
        this.currectProcessObjectLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.currectProcessObjectLabel.setPreferredSize(new Dimension(400, 50));

        //
        this.currectProcessObjectPathLabel = new JLabel("\s\s\sBRAK");
        this.scrollCurrectProcessObjectPath = new JScrollPane(this.currectProcessObjectPathLabel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        DefaultPanelModeView.changeFontSize(this.currectProcessObjectPathLabel, 15);
        DefaultPanelModeView.changeFontSize(this.scrollCurrectProcessObjectPath, 15);
        this.currectProcessObjectPathLabel.setVisible(true);
        this.scrollCurrectProcessObjectPath.setMaximumSize(new Dimension(400, 50));

        //
        this.startButton = new JButton("START");
        DefaultPanelModeView.changeFontSize(this.startButton, 15);
        this.startButton.setMaximumSize(new Dimension(400, 50));
        this.startButton.setAlignmentX(CENTER_ALIGNMENT);

        //
        
    }

    @Override
    public void addElements() 
    {
        this.add(Box.createVerticalStrut(10));
        this.add(this.choosingFolderLabel);
        this.add(Box.createVerticalStrut(10));
        this.add(this.choosingFolderPathPanel);
        this.choosingFolderPathPanel.add(Box.createHorizontalStrut(10));
        this.choosingFolderPathPanel.add(this.choosingFolderButton);
        this.choosingFolderPathPanel.add(Box.createHorizontalStrut(10));
        this.choosingFolderPathPanel.add(this.scrollChoosingFolderPathLabel);
        this.add(Box.createVerticalStrut(10));
        JSeparator x = new JSeparator();
        x.setMaximumSize(new Dimension(450, 1));
        this.add(x);
        this.add(Box.createVerticalStrut(10));
        this.add(this.statusLabel);
        this.add(Box.createVerticalStrut(10));
        JSeparator y = new JSeparator();
        y.setMaximumSize(new Dimension(450, 1));
        this.add(y);
        this.add(Box.createVerticalStrut(10));
        this.add(this.currectProcessObjectLabel);
        this.add(Box.createVerticalStrut(10));
        this.add(this.scrollCurrectProcessObjectPath);
        this.add(Box.createVerticalStrut(10));
        JSeparator z = new JSeparator();
        z.setMaximumSize(new Dimension(450, 1));
        this.add(z);
        this.add(Box.createVerticalStrut(10));
        this.add(this.startButton);
    }

    public void updateChoosingFolderPathLabel(File newPath)
    {
        if(newPath == null) this.choosingFolderPathLabel.setText("\s\s\sBRAK");
        else this.choosingFolderPathLabel.setText("   " + newPath.getPath() + "  ");
    }
}