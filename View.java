import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class View extends JPanel
{
    JButton selectFolderButton;

    JLabel selectFolderPathTitleLabel;
    JScrollPane scrollSelectFolderPath;
    JLabel selectFolderPathLabel;

    public View()
    {
        super();
        this.setBounds(0, 0, 500, 500);
        this.setLayout(null);
        
        //SET VIEW METHODS
        this.setSelectButton();
        this.setScrollSelectFolderPath();

        this.setVisible(true);
    }


    //SET VIEW METHODS
    public void setSelectButton()
    {
        this.selectFolderButton = new JButton("Wybierz folder");
        this.selectFolderButton.setLocation(15, 15);
        this.selectFolderButton.setSize(this.selectFolderButton.getText().length()*9, 50);
        this.add(this.selectFolderButton);
    }

    public void setScrollSelectFolderPath()
    {
        this.selectFolderPathLabel = new JLabel("   BRAK");
        this.scrollSelectFolderPath = new JScrollPane(this.selectFolderPathLabel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.selectFolderPathLabel.setBounds(25, 0, this.scrollSelectFolderPath.getWidth() + 200, this.scrollSelectFolderPath.getHeight());
        this.scrollSelectFolderPath.setBounds(this.selectFolderButton.getWidth() + this.selectFolderButton.getX() + 15, 30, this.getWidth() - this.selectFolderButton.getWidth() - this.selectFolderButton.getX() - 45, 50);
        this.selectFolderPathLabel.setVisible(true);
        this.add(this.scrollSelectFolderPath);
        this.selectFolderPathTitleLabel = new JLabel("Ścieżka wybranego folderu:");
        this.add(this.selectFolderPathTitleLabel);
        this.selectFolderPathTitleLabel.setBounds(this.scrollSelectFolderPath.getX(), this.scrollSelectFolderPath.getY() - 35, 200, 50);
    }
}
