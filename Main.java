import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.LinkedList;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileSystemView;

public class Main extends JFrame
{
    View view;

    final static int WIDTH = 500;
    final static int HEIGHT = 500;

    File choosenFolder;
    File choosenFiles[];

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

        try 
        {
            UIManager.setLookAndFeel(
            UIManager.getSystemLookAndFeelClassName());
        } 
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) 
        {}

        //
        this.view = new View();
        this.add(this.view, BorderLayout.CENTER);
        
        
        this.view.createListPanel.choosingFolderButton.addActionListener(e -> this.chooseFolder());
        this.view.createListPanel.startButton.addActionListener(e -> {
            if(this.choosenFolder != null) 
            {
                this.view.createListPanel.statusLabel.setText("Aktualny status: DZIAŁANIE");
                this.view.createListPanel.statusLabel.setForeground(Color.BLUE);
                this.view.createListPanel.statusLabel.paintImmediately(this.view.createListPanel.statusLabel.getVisibleRect());
                this.createFolderList();
                this.view.createListPanel.statusLabel.setText("Aktualny status: BRAK DZIAŁANIA");
                this.view.createListPanel.statusLabel.setForeground(Color.RED);
                this.view.createListPanel.statusLabel.paintImmediately(this.view.createListPanel.statusLabel.getVisibleRect());
                this.view.createListPanel.currectProcessObjectPathLabel.setText("\s\s\sBRAK");
                this.view.createListPanel.currectProcessObjectPathLabel.paintImmediately(this.view.createListPanel.currectProcessObjectPathLabel.getVisibleRect());
            }            
            else
            {
                JOptionPane.showMessageDialog(this,"Nie wybrano folderu do zrobienia listy!","Błąd!", JOptionPane.ERROR_MESSAGE);
            }
        });

        //this.view.chooseFilesToReadButton.addActionListener(e -> this.chooseFiles());
        //this.view.resetFilesToReadButton.addActionListener(e -> this.resetReadingList());
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
        while(true)
        {
            int flag = x.showOpenDialog(this);
            if(flag == JFileChooser.APPROVE_OPTION)
            {
                this.choosenFolder = x.getSelectedFile();
                if(this.choosenFolder.listFiles() == null) JOptionPane.showMessageDialog(this,"Nie można odczytać zawartości wybranego folderu!","Błąd!", JOptionPane.ERROR_MESSAGE);
                else break;
            }
            else
            {
                this.choosenFolder = null;
                break;
            }
        }
        this.view.createListPanel.updateChoosingFolderPathLabel(this.choosenFolder);
    }

    //////////////////////////////

    // @SuppressWarnings("unchecked")
    // public void resetReadingList()
    // {
    //     DefaultListModel<String> newModel = new DefaultListModel();
    //     this.choosenFiles = null;
    //     newModel.addElement("BRAK");
    //     this.view.chooseFilesToReadList.setModel(newModel);
    // }

    

    public void createFolderList()
    {
        LinkedList<String> mainList = new LinkedList<>();
        LinkedList<LinkedList<File>> twoDimensionList = new LinkedList<>();
        
        //mainList.add(this.choosenFolder.getName());
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
                    mainList.add(newTabByLevel(twoDimensionList.size() - 1) + twoDimensionList.getLast().getFirst().getName());
                    this.view.createListPanel.currectProcessObjectPathLabel.setText("\s\s\s" + twoDimensionList.getLast().getFirst().getName());
                    this.view.createListPanel.currectProcessObjectPathLabel.paintImmediately(this.view.createListPanel.currectProcessObjectPathLabel.getVisibleRect());
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

        String tempName = FileSystemView.getFileSystemView().getSystemDisplayName(this.choosenFolder);
        
        for(String z : new String[]{"<", ">", ":", "\"", "*", "?", "|", "\\", "/"})
        {
            tempName = tempName.replace(z, "");
        }
        JFileChooser x = new JFileChooser();
        x.setDialogTitle("Zapisz plik");
        
        while(true)
        {
            x.setSelectedFile(new File(tempName + ".txt"));
            int flag = x.showSaveDialog(this);
            if(flag == JFileChooser.APPROVE_OPTION)
            {
                File y = x.getSelectedFile();
                if(!y.getName().endsWith(".txt")) y = new File(x.getSelectedFile().getPath() + ".txt");
                try 
                {
                    if(y.getName().equals(".txt")) throw new Exception();
                    Files.copy(temp, y.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    break;
                }
                catch (Exception e) 
                {
                    JOptionPane.showMessageDialog(this,"Nieprawidłowa nazwa pliku!","Błąd!", JOptionPane.ERROR_MESSAGE);
                }
            }
            else break;
        }
    }

    public String newTabByLevel(int level)
    {
        String x = "";
        for(int i=0; i<level; i++) x += "\t";
        return x;
    }

    // @SuppressWarnings("unchecked")
    // public void chooseFiles()
    // {
    //     JFileChooser x = new JFileChooser("Wybierz pliki");
    //     x.setMultiSelectionEnabled(true);
    //     x.setFileSelectionMode(JFileChooser.FILES_ONLY);
    //     int flag = x.showOpenDialog(this);

    //     DefaultListModel newModel = new DefaultListModel();
        
    //     if(flag == JFileChooser.APPROVE_OPTION)
    //     {
    //         this.choosenFiles = x.getSelectedFiles();
    //         for(int i=0; i<this.choosenFiles.length; i++) newModel.addElement(this.choosenFiles[i].getPath());
    //     }
    //     else
    //     {
    //         this.choosenFiles = null;
    //         newModel.addElement("BRAK");
    //     }
    //     this.view.chooseFilesToReadList.setModel(newModel);
    // }
}