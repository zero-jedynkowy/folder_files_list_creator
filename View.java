import java.awt.Color;
import java.io.File;
import java.nio.file.Path;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class View extends JPanel
{
    JButton selectFolderButton;

    JLabel selectFolderPathTitleLabel;
    JLabel selectFolderPathUndertitleLabel;
    JScrollPane scrollSelectFolderPath;
    JLabel selectFolderPathLabel;

    public View()
    {
        super();
        this.setBounds(0, 0, 500, 500);
        this.setLayout(null);
        
        //SET VIEW METHODS
        this.setSelectFolderTitle();
        this.setSelectButton();
        this.setScrollSelectFolderPath();

        this.setVisible(true);
    }


    //SET VIEW METHODS
    public void setSelectFolderTitle()
    {
        this.selectFolderPathTitleLabel = new JLabel("Wybierz folder do zrobienia listy:");
        this.add(this.selectFolderPathTitleLabel);
        this.selectFolderPathTitleLabel.setBounds(15, 15, 200, 15);
        this.selectFolderPathTitleLabel.setVisible(true);
    }

    public void setSelectButton()
    {
        this.selectFolderButton = new JButton("Wybierz folder");
        this.selectFolderButton.setLocation(15, 50);
        this.selectFolderButton.setSize(this.selectFolderButton.getText().length()*9, 50);
        this.add(this.selectFolderButton);
    }

    public void setScrollSelectFolderPath()
    {
        this.selectFolderPathUndertitleLabel = new JLabel("Ścieżka wybranego folderu:");
        this.add(this.selectFolderPathUndertitleLabel);
        this.selectFolderPathUndertitleLabel.setBounds(this.selectFolderButton.getWidth() + this.selectFolderButton.getX() + 15, this.selectFolderButton.getY(), 200, 15);
        
        this.selectFolderPathLabel = new JLabel("   BRAK");
        this.scrollSelectFolderPath = new JScrollPane(this.selectFolderPathLabel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.scrollSelectFolderPath.setBounds(this.selectFolderPathUndertitleLabel.getX(), this.selectFolderPathUndertitleLabel.getHeight() + this.selectFolderPathUndertitleLabel.getY() + 5, 50, 30);
        this.scrollSelectFolderPath.setSize(this.getWidth() - this.scrollSelectFolderPath.getX() - 25, 40);
        this.selectFolderPathLabel.setBounds(0, 0, this.scrollSelectFolderPath.getWidth() + 200, this.scrollSelectFolderPath.getHeight());
        this.selectFolderPathLabel.setVisible(true);
        this.add(this.scrollSelectFolderPath);
    }

    public void updateSelectFolderPath(File newPath)
    {
        if(newPath == null) this.selectFolderPathLabel.setText("    BRAK");
        else this.selectFolderPathLabel.setText("   " + newPath.getPath() + "  ");
    }
}
