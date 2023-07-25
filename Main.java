import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.List;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileSystemView;
import javax.swing.text.html.HTMLDocument.Iterator;

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
        this.view.startButton.addActionListener(e -> this.createFolderList());
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
        // Path temp = null;
        // try 
        // {
        //     temp = Files.createTempFile("hello", ".txt");
        //     System.out.println(temp);
        // } catch (IOException e) {}
        // try (FileWriter fileWriter = new FileWriter(temp.toFile())) 
        // {
        //     PrintWriter printWriter = new PrintWriter(fileWriter);
        //     
            
        // } 
        // catch (IOException e) 
        // {
        //     e.printStackTrace();
        // }
        // System.out.println(temp);
    
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
                    //System.out.println(twoDimensionList.size());
                    if(twoDimensionList.getLast().size() == 0)
                    {
                        twoDimensionList.removeLast();
                        break;
                    }
                    
                    System.out.println(newTabByLevel(twoDimensionList.size()) + twoDimensionList.getLast().getFirst().getName());
                    
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
                    
                }
                
            }
            if(twoDimensionList.size() == 0) break;
        }
    }

    public String newTabByLevel(int level)
    {
        String x = "";
        for(int i=0; i<level; i++) x += "\t";
        return x;
    }
}