import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

public class Module2 extends DefaultPanelModeView
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


    LinkedList<File> addedFiles;

    public Module2(Main mainWindow) {
        super(mainWindow);
        this.addedFiles = new LinkedList<File>();
    }

    @Override
    public void setView() 
    {
        this.label_loadFilesTitle = new JLabel("Wybierz pliki do wyświetlenia:", JLabel.CENTER);
        this.label_loadFilesTitle.setVisible(true);
        this.label_loadFilesTitle.setAlignmentX(CENTER_ALIGNMENT);
        DefaultPanelModeView.changeFontSize(this.label_loadFilesTitle, 20);
        this.label_loadFilesTitle.setHorizontalAlignment(SwingConstants.CENTER);
        this.label_loadFilesTitle.setMaximumSize(new Dimension(400, 50));

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
                DefaultPanelModeView.changeFontSize(this.button_addFile, 15);
                this.button_addFile.setMaximumSize(new Dimension(190, 50));
                this.button_addFile.setAlignmentX(CENTER_ALIGNMENT);
                this.button_removeFiles = new JButton("Usuń pliki");
                DefaultPanelModeView.changeFontSize(this.button_removeFiles, 15);
                this.button_removeFiles.setMaximumSize(new Dimension(190, 50));
                this.button_removeFiles.setAlignmentX(CENTER_ALIGNMENT);
                this.button_addFolder = new JButton("Dodaj folder");
                DefaultPanelModeView.changeFontSize(this.button_addFolder, 15);
                this.button_addFolder.setMaximumSize(new Dimension(190, 50));
                this.button_addFolder.setAlignmentX(CENTER_ALIGNMENT);
                this.button_reset = new JButton("Reset");
                DefaultPanelModeView.changeFontSize(this.button_reset, 15);
                this.button_reset.setMaximumSize(new Dimension(190, 50));
                this.button_reset.setAlignmentX(CENTER_ALIGNMENT);
                
        this.button_generateList = new JButton("Generuj listę");
        DefaultPanelModeView.changeFontSize(this.button_generateList, 15);
        this.button_generateList.setMaximumSize(new Dimension(400, 50));
        this.button_generateList.setAlignmentX(CENTER_ALIGNMENT);
    }

    @Override
    public void addElements() 
    {
        this.add(Box.createVerticalStrut(10));
        this.add(this.label_loadFilesTitle);
        this.add(Box.createVerticalStrut(10));
        this.add(this.panel_mainCenterPanel);
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
        this.add(Box.createVerticalStrut(10));
        this.add(this.button_generateList);
    }

    @Override
    public void setActions() 
    {
        this.button_addFile.addActionListener(e -> this.addFile());
        this.button_removeFiles.addActionListener(e -> this.removeFiles());
        this.button_addFolder.addActionListener(e -> this.addFolder());
        this.button_reset.addActionListener(e -> this.reset());
    }
    
    @SuppressWarnings("unchecked")
    public void addFile()
    {
        JFileChooser x = new JFileChooser("Wybierz pliki");
        x.setMultiSelectionEnabled(true);
        x.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int flag = x.showOpenDialog(this);

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


        // DefaultListModel newModel = new DefaultListModel();
        
        // if(flag == JFileChooser.APPROVE_OPTION)
        // {
        //     this.addedFiles = x.getSelectedFiles();
        //     for(int i=0; i<this.addedFiles.length; i++) newModel.addElement(this.addedFiles[i].getPath());
        // }
        // else
        // {
        //     this.addedFiles = null;
        //     newModel.addElement("BRAK");
        // }
        // this.list_choosenFilesToReadList.setModel(newModel);
    }

    public void addFolder()
    {
        JFileChooser x = new JFileChooser("Wybierz foldery");
        x.setMultiSelectionEnabled(true);
        x.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int flag = x.showOpenDialog(this);

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
        DefaultListModel newModel = new DefaultListModel();
        this.list_choosenFilesToReadList.setModel(newModel);
    }
}
