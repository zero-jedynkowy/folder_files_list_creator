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

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileSystemView;

public class Module1 extends DefaultPanelModeView
{
    public Module1(Main mainWindow) 
    {
        super(mainWindow);
    }

    JLabel label_choosingFolder;
    JPanel panel_choosingFolderPath;
        JButton button_choosingFolder;
        JScrollPane scrollPane_choosingFolderPathScroll;
            JLabel label_choosingFolderPath;

    JLabel label_status;
    JLabel label_currectProcessedObject;
        JScrollPane  scrollPane_currectProcessObjectPathScroll;
            JLabel currectProcessObjectPathLabel;
            
           
    JButton button_start;

    File choosenFolder;
    File choosenFiles[];

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
        this.button_start = new JButton("START");
        DefaultPanelModeView.changeFontSize(this.button_start, 15);
        this.button_start.setMaximumSize(new Dimension(400, 50));
        this.button_start.setAlignmentX(CENTER_ALIGNMENT);
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
        this.add(this.button_start);
    }

    @Override
    public void setActions()
    {
        this.button_choosingFolder.addActionListener(e -> this.chooseFolder());
        this.button_start.addActionListener(e -> this.startMakingList());
    }

    public void updateChoosedFolderPathLabel(File newPath)
    {
        if(newPath == null) this.label_choosingFolderPath.setText("\s\s\sBRAK");
        else this.label_choosingFolderPath.setText("   " + newPath.getPath() + "  ");
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
        this.updateChoosedFolderPathLabel(this.choosenFolder);
    }

    public void startMakingList()
    {
        System.out.println("ln");
        if(this.choosenFolder != null) 
        {
            
            this.label_status.setText("Aktualny status: DZIAŁANIE");
            this.label_status.setForeground(Color.BLUE);
            this.label_status.paintImmediately(this.label_status.getVisibleRect());
            this.createList();
            this.label_status.setText("Aktualny status: BRAK DZIAŁANIA");
            this.label_status.setForeground(Color.RED);
            this.label_status.paintImmediately(this.label_status.getVisibleRect());
            this.currectProcessObjectPathLabel.setText("\s\s\sBRAK");
            this.currectProcessObjectPathLabel.paintImmediately(this.currectProcessObjectPathLabel.getVisibleRect());
        }            
        else
        {
            JOptionPane.showMessageDialog(this,"Nie wybrano folderu do zrobienia listy!","Błąd!", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void createList()
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
                    mainList.add(Main.newTabCharacterByLevel(twoDimensionList.size() - 1) + twoDimensionList.getLast().getFirst().getName());
                    this.currectProcessObjectPathLabel.setText("\s\s\s" + twoDimensionList.getLast().getFirst().getName());
                    this.currectProcessObjectPathLabel.paintImmediately(this.currectProcessObjectPathLabel.getVisibleRect());
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
}