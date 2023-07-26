import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.LinkedList;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
        this.view.startButton.addActionListener(e -> {
            if(this.choosenFolder != null) 
            {
                this.view.statusLabel.setText("Aktualny status: DZIAŁANIE");
                this.view.statusLabel.paintImmediately(this.view.statusLabel.getVisibleRect());
                this.createFolderList();
                this.view.statusLabel.setText("Aktualny status: BRAK DZIAŁANIA");
                this.view.statusLabel.paintImmediately(this.view.statusLabel.getVisibleRect());
                this.view.currectProcessObjectPathLabel.setText("\s\s\sBRAK");
                this.view.currectProcessObjectPathLabel.paintImmediately(this.view.currectProcessObjectPathLabel.getVisibleRect());
            }            
            else
            {
                JOptionPane.showMessageDialog(this,"Nie wybrano folderu do zrobienia listy!","Błąd!", JOptionPane.ERROR_MESSAGE);
            }
        });
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

    public void createFolderList()
    {
        LinkedList<String> mainList = new LinkedList<>();
        LinkedList<LinkedList<File>> twoDimensionList = new LinkedList<>();
        
        mainList.add(this.choosenFolder.getName());
        twoDimensionList.add(new LinkedList<File>(Arrays.asList(this.choosenFolder.listFiles())));

        while(true)
        {
            while(true)
            {
                try
                {
                    if(twoDimensionList.getLast().size() == 0)
                    {
                        twoDimensionList.removeLast();
                        break;
                    }
                    mainList.add(newTabByLevel(twoDimensionList.size()) + twoDimensionList.getLast().getFirst().getName());
                    this.view.currectProcessObjectPathLabel.setText("\s\s\s" + twoDimensionList.getLast().getFirst().getName());
                    this.view.currectProcessObjectPathLabel.paintImmediately(this.view.currectProcessObjectPathLabel.getVisibleRect());
                    if(twoDimensionList.getLast().getFirst().isDirectory())
                    {
                        twoDimensionList.add(new LinkedList<File>(Arrays.asList(twoDimensionList.getLast().getFirst().listFiles())));
                        twoDimensionList.get(twoDimensionList.size() - 2).removeFirst();
                    }
                    else
                    {
                        twoDimensionList.getLast().removeFirst();
                    }
                }
                catch(Exception e)
                {
                   //IF THE PROGRAMME CAN'T READ THE LIST OF THE FOLDER (PROBABLY DOESN'T HAVE ACCES TO IT) IT IS SKIPPED; ONLY THE FOLDER'S NAME IS SAVED
                    twoDimensionList.getLast().removeFirst();
                }
                
            }
            if(twoDimensionList.size() == 0) break;
        }

        Path temp = null;
        try 
        {
            temp = Files.createTempFile("temp", ".txt");
            System.out.println(temp);
        } 
        catch (IOException e) {}
        try (FileWriter fileWriter = new FileWriter(temp.toFile())) 
        {
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for(String x : mainList)
            {
                printWriter.print(x + "\n");
            }
        } 
        catch (IOException e) 
        {}

        JFileChooser x = new JFileChooser();
        x.setDialogTitle("Zapisz plik");
        x.setSelectedFile(new File(FileSystemView.getFileSystemView().getSystemDisplayName(this.choosenFolder) + ".txt"));
        int flag = x.showSaveDialog(this);
        if(flag == JFileChooser.APPROVE_OPTION)
        {
            File y = x.getSelectedFile();
            System.out.print(y.getPath());
            try 
            {
                Files.copy(temp, y.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }
    }

    public String newTabByLevel(int level)
    {
        String x = "";
        for(int i=0; i<level; i++) x += "\t";
        return x;
    }
}