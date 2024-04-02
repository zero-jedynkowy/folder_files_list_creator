import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.tree.DefaultMutableTreeNode;

import org.json.JSONObject;

public class EditShowModule extends BasicModule implements Runnable
{
     JLabel label_loadFilesTitle;
    
    JPanel panel_mainCenterPanel;
        JPanel panel_mainLeftCenterPanel;
            JScrollPane scroll_choosenFilesToReadList;
                JList<String> list_choosenFilesToReadList;
        JPanel panel_mainRightCenterPanel;
            JButton button_addFile;
            JButton button_removeFiles;
            JButton button_addFolder;
            JButton button_reset;
    JButton button_generateList;
    public boolean flag = true;

    EditShowFrame frame_newWindow;
    

    LinkedList<File> addedFiles;
    JTree originalTree;
    DefaultMutableTreeNode originalNode;

    long  processingFilesCount;
    long processingLinesCount;
    ProgressCreatingTree processStatsDialog;

    public EditShowModule(String id) 
    {
        super(id);
        this.addedFiles = new LinkedList<File>();
    }

    @Override
    public void setView() 
    {
        this.label_loadFilesTitle = new JLabel("Wybierz pliki do wyświetlenia:", JLabel.CENTER);
        this.label_loadFilesTitle.setVisible(true);
        this.label_loadFilesTitle.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        BasicElement.changeFontSize(this.label_loadFilesTitle, 20);
        this.label_loadFilesTitle.setHorizontalAlignment(SwingConstants.CENTER);
        this.label_loadFilesTitle.setMaximumSize(new Dimension(400, 50));
        this.listOfElements.put("label_loadFilesTitle", this.label_loadFilesTitle);

        this.panel_mainCenterPanel = new JPanel();
        this.panel_mainCenterPanel.setLayout(new BoxLayout(this.panel_mainCenterPanel, BoxLayout.X_AXIS));
        this.panel_mainCenterPanel.setMaximumSize(new Dimension(400, 200));
            this.panel_mainLeftCenterPanel = new JPanel();
            this.panel_mainLeftCenterPanel.setLayout(new BorderLayout());
            this.panel_mainLeftCenterPanel.setMaximumSize(new Dimension(300, 200));
                this.list_choosenFilesToReadList = new JList<String>();
                this.list_choosenFilesToReadList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
                this.list_choosenFilesToReadList.setLayoutOrientation(JList.VERTICAL);
                this.scroll_choosenFilesToReadList = new JScrollPane(this.list_choosenFilesToReadList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                this.scroll_choosenFilesToReadList.setMaximumSize(new Dimension(300, 150));
            this.panel_mainRightCenterPanel = new JPanel();
            this.panel_mainRightCenterPanel.setLayout(new BoxLayout(this.panel_mainRightCenterPanel, BoxLayout.Y_AXIS));
            this.panel_mainRightCenterPanel.setMaximumSize(new Dimension(190, 200));
                this.button_addFile = new JButton("Dodaj plik");
                BasicElement.changeFontSize(this.button_addFile, 15);
                this.button_addFile.setMaximumSize(new Dimension(190, 50));
                this.button_addFile.setAlignmentX(JPanel.CENTER_ALIGNMENT);
                this.listOfElements.put("button_addFile", this.button_addFile);
                this.button_removeFiles = new JButton("Usuń pliki");
                BasicElement.changeFontSize(this.button_removeFiles, 15);
                this.button_removeFiles.setMaximumSize(new Dimension(190, 50));
                this.button_removeFiles.setAlignmentX(JPanel.CENTER_ALIGNMENT);
                this.listOfElements.put("button_removeFiles", this.button_removeFiles);
                this.button_addFolder = new JButton("Dodaj folder");
                BasicElement.changeFontSize(this.button_addFolder, 15);
                this.button_addFolder.setMaximumSize(new Dimension(190, 50));
                this.button_addFolder.setAlignmentX(JPanel.CENTER_ALIGNMENT);
                this.listOfElements.put("button_addFolder", this.button_addFolder);
                this.button_reset = new JButton("Reset");
                BasicElement.changeFontSize(this.button_reset, 15);
                this.button_reset.setMaximumSize(new Dimension(190, 50));
                this.button_reset.setAlignmentX(JPanel.CENTER_ALIGNMENT);
                this.listOfElements.put("button_reset", this.button_reset);

                
        this.button_generateList = new JButton("Generuj listę");
        BasicElement.changeFontSize(this.button_generateList, 15);
        this.button_generateList.setMaximumSize(new Dimension(400, 50));
        this.button_generateList.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        this.listOfElements.put("button_generateList", this.button_generateList);


        this.processStatsDialog = new ProgressCreatingTree("ProgressCreatingTree", Main.mainWindow, this);


        this.frame_newWindow = new EditShowFrame("EditShowFrame");

    }

    @Override
    public void addElements() 
    {
        this.component.add(Box.createVerticalStrut(10));
        this.component.add(this.label_loadFilesTitle);
        this.component.add(Box.createVerticalStrut(10));
        this.component.add(this.panel_mainCenterPanel);
            this.panel_mainCenterPanel.add(this.panel_mainLeftCenterPanel);
                this.panel_mainLeftCenterPanel.add(this.scroll_choosenFilesToReadList);
            this.panel_mainCenterPanel.add(Box.createHorizontalStrut(20));
            this.panel_mainCenterPanel.add(this.panel_mainRightCenterPanel);
                this.panel_mainRightCenterPanel.add(this.button_addFile);
                this.panel_mainRightCenterPanel.add(Box.createVerticalStrut(5));
                this.panel_mainRightCenterPanel.add(this.button_removeFiles);
                this.panel_mainRightCenterPanel.add(Box.createVerticalStrut(5));
                this.panel_mainRightCenterPanel.add(this.button_addFolder);
                this.panel_mainRightCenterPanel.add(Box.createVerticalStrut(5));
                this.panel_mainRightCenterPanel.add(this.button_reset);
        this.component.add(Box.createVerticalStrut(10));
        this.component.add(this.button_generateList);
    }

    @Override
    public void setActions() 
    {
        this.button_addFile.addActionListener(e -> this.addFile());
        this.button_removeFiles.addActionListener(e -> this.removeFiles());
        this.button_addFolder.addActionListener(e -> this.addFolder());
        this.button_reset.addActionListener(e -> this.reset());
        this.button_generateList.addActionListener(e -> this.generate());
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

    @SuppressWarnings("unchecked")
    public void addFile()
    {
        JFileChooser x = new JFileChooser(this.language.getLanguageRecord("fileChooser_title", 0));
        x.setMultiSelectionEnabled(true);
        x.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int flag = x.showOpenDialog(this.component);

        if(flag == JFileChooser.APPROVE_OPTION)
        {
            File tempList[] = x.getSelectedFiles();
            for(File y : tempList)
            {
                if(y.getPath().endsWith(".txt") && !this.addedFiles.contains(y)) this.addedFiles.add(y);
            }
            Iterator<File> z = this.addedFiles.iterator(); 
            DefaultListModel newModel = new DefaultListModel();
            while(z.hasNext())
            {
                newModel.addElement(z.next().getPath());
            }
            this.list_choosenFilesToReadList.setModel(newModel);
        }
    }

    public void addFolder()
    {
        JFileChooser x = new JFileChooser(this.language.getLanguageRecord("folderChooser_title", 0));
        x.setMultiSelectionEnabled(true);
        x.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int flag = x.showOpenDialog(this.component);

        if(flag == JFileChooser.APPROVE_OPTION)
        {
            File tempList[] = x.getSelectedFiles();
            for(File y : tempList)
            {
                if(y.isDirectory())
                {
                    for(File z : y.listFiles())
                    {
                        if(!this.addedFiles.contains(z) && !z.isDirectory() && z.getPath().endsWith(".txt")) this.addedFiles.add(z);
                    }
                }
            }
            Iterator<File> z = this.addedFiles.iterator(); 
            DefaultListModel newModel = new DefaultListModel();
            while(z.hasNext())
            {
                newModel.addElement(z.next().getPath());
            }
            this.list_choosenFilesToReadList.setModel(newModel);
        }

    }

    public void removeFiles()
    {
        for(String x : this.list_choosenFilesToReadList.getSelectedValuesList())
        {
            for(File y : this.addedFiles)
            {
                if(y.getPath().equals(x)) this.addedFiles.remove(y);
            }
        }
        Iterator<File> z = this.addedFiles.iterator(); 
        DefaultListModel newModel = new DefaultListModel();
        while(z.hasNext())
        {
            newModel.addElement(z.next().getPath());
        }
        this.list_choosenFilesToReadList.setModel(newModel);
    }

    public void reset()
    {
        this.addedFiles.removeAll(addedFiles);
        DefaultListModel newModel = new DefaultListModel();
        this.list_choosenFilesToReadList.setModel(newModel);
    }

    public void generate()
    {
        if(this.addedFiles.size() == 0 || this.addedFiles == null) 
        {
            JOptionPane.showMessageDialog(this.component,this.language.getLanguageRecord("not_choose_any_file", 0),this.language.getLanguageRecord("error", 0), JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            this.processingFilesCount = 0;
            this.processingLinesCount = 0;
            this.flag = true;
            // this.processStatsDialog = new ProgressCreatingTree(this.mainWindow, "Tworzenie listy", true);
            this.originalNode = new DefaultMutableTreeNode();
            this.processStatsDialog.setCounts(0, 0);
            Thread makeTree = new Thread(this);
            makeTree.start();
            this.processStatsDialog.component.setVisible(true);
            
            this.originalTree = new JTree(this.originalNode);
            
            BasicElement.changeFontSize(this.originalTree, 20);
            this.frame_newWindow.show(true, this.originalTree, this.originalNode);
            Main.mainWindow.setEnabled(false);
        }
    }

    public void run()
    {   
        this.processStatsDialog.setMaxCounts(this.addedFiles.size(), 100);
        for(File x : this.addedFiles)
        {
            this.originalNode.add(this.createList(x));
            this.processingFilesCount++;
            this.processStatsDialog.setCounts(this.processingFilesCount, -1);
        }
        this.processStatsDialog.component.dispose();
    }
    
    public DefaultMutableTreeNode createList(File file)
    {
        LinkedList<Integer> countTabs = new LinkedList<>();
        DefaultMutableTreeNode mainTree = new DefaultMutableTreeNode(file.getName().replace(".txt", ""));
        int counter = 1;
        try 
        {
            LinkedList<String> lines = new LinkedList<>();
            
            for(String x : Files.readAllLines(file.toPath(), StandardCharsets.UTF_8))
            {
                lines.add(x);
            }
            this.processStatsDialog.setMaxCounts(-1, 2*lines.size());
            this.processingLinesCount = 0;
            this.processStatsDialog.setCounts(-1, 0);
            for(int i=0; i<lines.size(); i++)
            {
                if(!this.flag)
                {
                    return mainTree;
                }
                counter = 1;
                while(true)
                {
                    if(lines.get(i).startsWith(CreateListModule.newTabCharacterByLevel(counter)) == true) counter += 1;
                    else if(counter == 1)
                    {
                        countTabs.add(0);
                        break;
                    }
                    else
                    {
                        countTabs.add(counter - 1);
                        lines.set(i, lines.get(i).replace(CreateListModule.newTabCharacterByLevel(counter), ""));
                        break;
                    }   
                }
                this.processingLinesCount++;
                this.processStatsDialog.setCounts(-1, this.processingLinesCount);
                this.processStatsDialog.progressBar_progressLines.paintImmediately(this.processStatsDialog.progressBar_progressLines.getVisibleRect());
            }
            LinkedList<DefaultMutableTreeNode> loweringList = new LinkedList<>();
            int level = 0;
            loweringList.add(mainTree);
            DefaultMutableTreeNode lastTreeNode = mainTree;
            for(int i=0; i<countTabs.size(); i++)
            {
                if(!this.flag)
                {
                    return mainTree;
                }
                if(countTabs.get(i) == level)
                {
                    DefaultMutableTreeNode newTree = new DefaultMutableTreeNode(lines.get(i));
                    loweringList.getLast().add(newTree);
                    lastTreeNode = newTree;
                }
                else if(countTabs.get(i) > level)
                {
                    loweringList.add(lastTreeNode);
                    lastTreeNode = new DefaultMutableTreeNode(lines.get(i));
                    loweringList.getLast().add(lastTreeNode);
                    level++;
                }
                else
                {
                    for(int x=0; x<level-countTabs.get(i); x++) loweringList.removeLast();
                    level = countTabs.get(i);
                    lastTreeNode = new DefaultMutableTreeNode(lines.get(i));
                    loweringList.getLast().add(lastTreeNode);
                }
                this.processingLinesCount++;
                this.processStatsDialog.setCounts(-1, this.processingLinesCount);
                this.processStatsDialog.progressBar_progressLines.paintImmediately(this.processStatsDialog.progressBar_progressLines.getVisibleRect());
            }
            
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        return mainTree;
    }
}
