import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileSystemView;

public class Main extends JFrame
{
    View view;

    final static int WIDTH = 500;
    final static int HEIGHT = 500;

    File choosenFolder;

    public Main()
    {
        super();
        this.setTitle("Lista plików");
        this.setLayout(new BorderLayout());
        this.setSize(WIDTH, HEIGHT);
        this.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        //
        this.view = new View();
        this.add(this.view, BorderLayout.CENTER);
        

        //
        this.view.choosingFolderButton.addActionListener(e -> this.chooseFolder());

        this.setVisible(true);
    }
    
    public static void main(String args[])
    {
        new Main();
    }

    public void chooseFolder()
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
        this.view.updateChoosingFolderPathLabel(this.choosenFolder);
    }
}