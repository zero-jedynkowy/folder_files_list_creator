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
import java.awt.Component;
import java.util.Map;
import org.json.JSONObject;

public class CreateListModule extends BasicModule
{
    JLabel label_choosingFolder;
    JPanel panel_choosingFolderPath;
        JButton button_choosingFolder;
        JScrollPane scrollPane_choosingFolderPathScroll;
            JLabel label_choosingFolderPath;

    JLabel label_status;
    JLabel label_currectProcessedObject;
        JScrollPane  scrollPane_currectProcessObjectPathScroll;
            JLabel label_currentProcessObjectPathLabel;
        
    JLabel label_filesCounterTitle;
    JLabel label_filesCounter;

    JButton button_start;

    File choosenFolder;
    File choosenFiles[];

    long filesCounter;

    boolean startStopButtonFlag = false;
    Thread myThread;
    
    public CreateListModule(String id) 
    {
        super(id);
    }

    @Override
    public void setView() 
    {
        //
        // this.label_choosingFolder = new JLabel("Wybierz folder do zrobienia listy:", JLabel.CENTER);
        this.label_choosingFolder = this.createElement("label_choosingFolder", new JLabel());
        this.label_choosingFolder.setVisible(true);
        this.label_choosingFolder.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        BasicElement.changeFontSize(this.label_choosingFolder, 20);
        this.label_choosingFolder.setHorizontalAlignment(SwingConstants.CENTER);
        this.label_choosingFolder.setMaximumSize(new Dimension(400, 50));
        
        //
        this.panel_choosingFolderPath = new JPanel();
        this.panel_choosingFolderPath.setLayout(new BoxLayout(this.panel_choosingFolderPath, BoxLayout.X_AXIS));
        this.panel_choosingFolderPath.setVisible(true);
        this.panel_choosingFolderPath.setMaximumSize(new Dimension(400, 50));

        //
        this.button_choosingFolder = this.createElement("button_choosingFolder", new JButton());
        BasicElement.changeFontSize(this.button_choosingFolder, 15);
        this.button_choosingFolder.setMaximumSize(new Dimension(100, 50));


        //
        this.label_choosingFolderPath = this.createElement("label_choosingFolderPath", new JLabel());
        this.scrollPane_choosingFolderPathScroll = new JScrollPane(this.label_choosingFolderPath, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        BasicElement.changeFontSize(this.label_choosingFolderPath, 15);
        BasicElement.changeFontSize(this.scrollPane_choosingFolderPathScroll, 15);
        this.label_choosingFolderPath.setVisible(true);

        
        //
        this.label_status = this.createElement("label_status", new JLabel());
        this.label_status.setForeground(Color.RED);
        this.label_status.setVisible(true);
        this.label_status.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        BasicElement.changeFontSize(this.label_status, 20);
        this.label_status.setHorizontalAlignment(SwingConstants.CENTER);
        this.label_status.setPreferredSize(new Dimension(400, 50));

        //
        this.label_currectProcessedObject = this.createElement("label_currectProcessedObject", new JLabel("", JLabel.CENTER));
        this.label_currectProcessedObject.setHorizontalAlignment(JLabel.CENTER);
        this.label_currectProcessedObject.setVisible(true);
        this.label_currectProcessedObject.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        BasicElement.changeFontSize(this.label_currectProcessedObject, 20);
        this.label_currectProcessedObject.setHorizontalAlignment(SwingConstants.CENTER);
        this.label_currectProcessedObject.setPreferredSize(new Dimension(400, 50));

        //
        this.label_currentProcessObjectPathLabel = this.createElement("label_currentProcessObjectPathLabel", new JLabel());
        this.scrollPane_currectProcessObjectPathScroll = new JScrollPane(this.label_currentProcessObjectPathLabel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        BasicElement.changeFontSize(this.label_currentProcessObjectPathLabel, 15);
        BasicElement.changeFontSize(this.scrollPane_currectProcessObjectPathScroll, 15);
        this.label_currentProcessObjectPathLabel.setVisible(true);
        this.scrollPane_currectProcessObjectPathScroll.setMaximumSize(new Dimension(400, 50));


        //
        this.label_filesCounterTitle = this.createElement("label_filesCounterTitle", new JLabel("", JLabel.CENTER));
        this.label_filesCounterTitle.setVisible(true);
        this.label_filesCounterTitle.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        BasicElement.changeFontSize(this.label_filesCounterTitle, 20);
        this.label_filesCounterTitle.setHorizontalAlignment(SwingConstants.CENTER);
        this.label_filesCounterTitle.setPreferredSize(new Dimension(400, 50));

        //
        this.label_filesCounter = new JLabel("0", JLabel.CENTER);
        this.label_filesCounter.setForeground(Color.BLUE);
        this.label_filesCounter.setVisible(true);
        this.label_filesCounter.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        BasicElement.changeFontSize(this.label_filesCounter, 20);
        this.label_filesCounter.setHorizontalAlignment(SwingConstants.CENTER);
        this.label_filesCounter.setPreferredSize(new Dimension(400, 50));

        //
        this.button_start = this.createElement("button_start", new JButton(""));
        BasicElement.changeFontSize(this.button_start, 15);
        this.button_start.setMaximumSize(new Dimension(400, 50));
        this.button_start.setAlignmentX(JPanel.CENTER_ALIGNMENT);
    }

    @Override
    public void addElements() 
    {
        this.component.add(Box.createVerticalStrut(10));
        this.component.add(this.label_choosingFolder);
        this.component.add(Box.createVerticalStrut(10));
        this.component.add(this.panel_choosingFolderPath);
            this.panel_choosingFolderPath.add(Box.createHorizontalStrut(10));
            this.panel_choosingFolderPath.add(this.button_choosingFolder);
            this.panel_choosingFolderPath.add(Box.createHorizontalStrut(10));
            this.panel_choosingFolderPath.add(this.scrollPane_choosingFolderPathScroll);
        this.component.add(Box.createVerticalStrut(10));
        JSeparator x = new JSeparator();
        x.setMaximumSize(new Dimension(450, 1));
        this.component.add(x);
        this.component.add(Box.createVerticalStrut(10));
        this.component.add(this.label_status);
        this.component.add(Box.createVerticalStrut(10));
        JSeparator y = new JSeparator();
        y.setMaximumSize(new Dimension(450, 1));
        this.component.add(y);
        this.component.add(Box.createVerticalStrut(10));
        this.component.add(this.label_currectProcessedObject);
        this.component.add(Box.createVerticalStrut(10));
        this.component.add(this.scrollPane_currectProcessObjectPathScroll);
        this.component.add(Box.createVerticalStrut(10));
        JSeparator z = new JSeparator();
        z.setMaximumSize(new Dimension(450, 1));
        this.component.add(z);
        this.component.add(Box.createVerticalStrut(10));
        this.component.add(this.label_filesCounterTitle);
        this.component.add(Box.createVerticalStrut(10));
        this.component.add(this.label_filesCounter);
        this.component.add(Box.createVerticalStrut(10));
        this.component.add(this.button_start);
    }

    @Override
    public void setActions() 
    {
        this.button_choosingFolder.addActionListener(e -> this.chooseFolder());
        this.button_start.addActionListener(e -> this.startMakingList());
    }

    @Override
    public void setLanguage() 
    {
        Component temp;
        String content;
        JSONObject languageContent = Language.getGlobalContent(this.getID());
        
        for(Map.Entry<String, Component> c : this.listOfElements.entrySet())
        {
            
            temp = this.listOfElements.get(c.getKey());
            content = languageContent.getJSONArray(c.getKey()).getString(0);
            if(temp instanceof JLabel)
            {
                ((JLabel)temp).setText(content);
            }
            else if(temp instanceof JButton)
            {
                ((JButton)temp).setText(content);
            }
        }
    }

    public void updateChoosedFolderPathLabel(File newPath)
    {
        if(newPath == null) this.label_choosingFolderPath.setText(this.language.getLanguageRecord("label_choosingFolderPath", 0));
        else this.label_choosingFolderPath.setText("   " + newPath.getPath() + "  ");
    }

    public void chooseFolder()
    {
        if(!this.startStopButtonFlag)
        {
            JFileChooser x = new JFileChooser(this.language.getLanguageRecord("fileChooser_title", 0));
            x.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            while(true)
            {
                int flag = x.showOpenDialog(this.component);
                if(flag == JFileChooser.APPROVE_OPTION)
                {
                    this.choosenFolder = x.getSelectedFile();
                    if(this.choosenFolder.listFiles() == null) JOptionPane.showMessageDialog(this.component, this.language.getLanguageRecord("error", 0),this.language.getLanguageRecord("not_read_folder", 0), JOptionPane.ERROR_MESSAGE);
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
    }

    public void startMakingList()
    {
        this.startStopButtonFlag = !this.startStopButtonFlag;
        if(this.choosenFolder != null && this.startStopButtonFlag) 
        {
            this.startStopButtonFlag = true;
            this.button_start.setText("STOP");
            this.label_status.setText(this.language.getLanguageRecord("label_status", 1));
            this.label_status.setForeground(Color.BLUE);
            this.label_status.paintImmediately(this.label_status.getVisibleRect());
            this.myThread = new Thread(this::createList);
            this.myThread.start();
            this.label_currentProcessObjectPathLabel.setText(this.language.getLanguageRecord("label_currentProcessObjectPathLabel", 0));
            this.label_currentProcessObjectPathLabel.paintImmediately(this.label_currentProcessObjectPathLabel.getVisibleRect());
        }        
        else if(this.choosenFolder == null)
        {
            JOptionPane.showMessageDialog(this.component, this.language.getLanguageRecord("not_choose_folder", 0),this.language.getLanguageRecord("error", 0), JOptionPane.ERROR_MESSAGE);
            this.startStopButtonFlag = false;
        }
    }

    public void createList()
    {
        LinkedList<String> mainList = new LinkedList<>();
        LinkedList<LinkedList<File>> twoDimensionList = new LinkedList<>();
        this.filesCounter = 0;
        
        //mainList.add(this.choosenFolder.getName());
        twoDimensionList.add(new LinkedList<File>(Arrays.asList(this.choosenFolder.listFiles())));

        while(true && this.startStopButtonFlag)
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
                    mainList.add(CreateListModule.newTabCharacterByLevel(twoDimensionList.size() - 1) + twoDimensionList.getLast().getFirst().getName());
                    this.filesCounter++;
                    this.label_currentProcessObjectPathLabel.setText("\s\s\s" + twoDimensionList.getLast().getFirst().getName());
                    this.label_currentProcessObjectPathLabel.paintImmediately(this.label_currentProcessObjectPathLabel.getVisibleRect());
                    this.label_filesCounter.setText(Long.toString(this.filesCounter));
                    this.label_filesCounter.paintImmediately(this.label_filesCounter.getVisibleRect());
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
        this.label_status.setText(this.language.getLanguageRecord("label_status", 0));
        this.label_status.setForeground(Color.RED);
        this.label_status.paintImmediately(this.label_status.getVisibleRect());
        Path temp = null;
        try 
        {
            temp = Files.createTempFile("temp", ".txt");
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
        x.setDialogTitle(this.language.getLanguageRecord("save_file", 0));
        
        while(true)
        {
            x.setSelectedFile(new File(tempName + ".txt"));
            int flag = x.showSaveDialog(this.component);
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
                    JOptionPane.showMessageDialog(this.component,this.language.getLanguageRecord("wrong_file_name", 0),this.language.getLanguageRecord("error", 0), JOptionPane.ERROR_MESSAGE);
                }
            }
            else break;
        }
        this.button_start.setText(this.language.getLanguageRecord("button_start", 0));
        this.startStopButtonFlag = false;
        this.choosenFolder = null;
        this.label_currentProcessObjectPathLabel.setText(this.language.getLanguageRecord("label_currentProcessObjectPathLabel", 0));
        this.label_choosingFolderPath.setText(this.language.getLanguageRecord("label_choosingFolderPath", 0));
        this.filesCounter = 0;
        this.label_filesCounter.setText(Integer.toString(0));
    }

    public static String newTabCharacterByLevel(int level)
    {
        String x = "";
        for(int i=0; i<level; i++) x += "\t";
        return x;
    }
}
