import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class Main extends JFrame
{
    View view;
    File choosenFolder;

    public Main()
    {
        super();
        this.setTitle("Lista plików");
        this.setBounds(0, 0, 500, 500);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.choosenFolder = null;

        //VIEW
        this.view = new View();
        this.add(this.view);

        //LISTENERS
        this.view.selectFolderButton.addActionListener(e -> this.selectDisk());

        this.setVisible(true);
    }
    
    public static void main(String args[])
    {
        new Main();
    }

    //OTHER METHODS
    public void selectDisk()
    {
        JFileChooser x = new JFileChooser("Wybierz folder");
        x.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int flag = x.showOpenDialog(this);
        if(flag == JFileChooser.APPROVE_OPTION)
        {
            this.choosenFolder = x.getSelectedFile();
        }
        else
        {
            this.choosenFolder = null;
        }
    }
}