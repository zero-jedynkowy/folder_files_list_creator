import java.awt.Dimension;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Settings extends AbstractModule
{
    JLabel label_settingsHeader;
    JPanel panel_choosingLanguage;
        JLabel label_choosingLanguage;
        JComboBox comboBox_choosingLanguage;
    JPanel panel_choosingTheme;
        JLabel label_choosingTheme;
        JComboBox comboBox_choosingTheme;
    JButton button_setSettings;

    public Settings(Main mainWindow) 
    {
        super(mainWindow);
    }

    @Override
    public void setView() 
    {
        //
        this.label_settingsHeader = new JLabel("Ustawienia", JLabel.CENTER);
        this.label_settingsHeader.setVisible(true);
        this.label_settingsHeader.setAlignmentX(CENTER_ALIGNMENT);
        AbstractModule.changeFontSize(this.label_settingsHeader, 20);
        this.label_settingsHeader.setHorizontalAlignment(SwingConstants.CENTER);
        this.label_settingsHeader.setMaximumSize(new Dimension(400, 50));
        this.listOfElements.put("label_settingsHeader", this.label_settingsHeader);

        //
        this.panel_choosingLanguage = new JPanel();
        this.panel_choosingLanguage.setLayout(new BoxLayout(this.panel_choosingLanguage, BoxLayout.X_AXIS));
        this.panel_choosingLanguage.setVisible(true);
        this.panel_choosingLanguage.setMaximumSize(new Dimension(400, 50));

        //
        this.label_choosingLanguage = new JLabel("Language/Język:", JLabel.CENTER);
        this.label_choosingLanguage.setVisible(true);
        this.label_choosingLanguage.setAlignmentX(LEFT_ALIGNMENT);
        AbstractModule.changeFontSize(this.label_choosingLanguage, 20);
        this.label_choosingLanguage.setHorizontalAlignment(SwingConstants.LEFT);
        this.label_choosingLanguage.setMaximumSize(new Dimension(400, 50));

        //
        String[] langauges = {"English", "Polski"};
        this.comboBox_choosingLanguage = new JComboBox<String>(langauges);
        this.comboBox_choosingLanguage.setSelectedItem(0);
        this.comboBox_choosingLanguage.setMaximumSize(new Dimension(500, 50));
        this.comboBox_choosingLanguage.setMinimumSize(new Dimension(500, 50));

        //
        this.panel_choosingTheme = new JPanel();
        this.panel_choosingTheme.setLayout(new BoxLayout(this.panel_choosingTheme, BoxLayout.X_AXIS));
        this.panel_choosingTheme.setVisible(true);
        this.panel_choosingTheme.setMaximumSize(new Dimension(400, 50));

        //
        this.label_choosingTheme = new JLabel("Style/Styl:", JLabel.CENTER);
        this.label_choosingTheme.setVisible(true);
        this.label_choosingTheme.setAlignmentX(LEFT_ALIGNMENT);
        AbstractModule.changeFontSize(this.label_choosingTheme, 20);
        this.label_choosingTheme.setHorizontalAlignment(SwingConstants.LEFT);
        this.label_choosingTheme.setMaximumSize(new Dimension(400, 50));

        //
        String[] styles = {"Normal", "System"};
        this.comboBox_choosingTheme = new JComboBox<String>(styles);
        this.comboBox_choosingTheme.setSelectedItem(0);
        this.comboBox_choosingTheme.setMaximumSize(new Dimension(500, 50));
        this.comboBox_choosingTheme.setMinimumSize(new Dimension(500, 50));

        //
        this.button_setSettings = new JButton("Set/Ustaw");
        AbstractModule.changeFontSize(this.button_setSettings, 15);
        this.button_setSettings.setMaximumSize(new Dimension(400, 50));
        this.button_setSettings.setAlignmentX(CENTER_ALIGNMENT);

        //
        this.getAndSetSettings();
    }

    @Override
    public void addElements() 
    {
        this.add(Box.createVerticalStrut(10));
        this.add(this.label_settingsHeader);
        this.add(Box.createVerticalStrut(10));
        this.add(this.panel_choosingLanguage);
            this.panel_choosingLanguage.add(this.label_choosingLanguage);
            this.panel_choosingLanguage.add(Box.createHorizontalStrut(100));
            this.panel_choosingLanguage.add(this.comboBox_choosingLanguage);
        this.add(Box.createVerticalStrut(10));
        this.add(this.panel_choosingTheme);
            this.panel_choosingTheme.add(this.label_choosingTheme);
            this.panel_choosingTheme.add(Box.createHorizontalStrut(100));
            this.panel_choosingTheme.add(this.comboBox_choosingTheme);
        this.add(Box.createVerticalStrut(50));
        this.add(this.button_setSettings);
    }

    @Override
    public void setActions() 
    {
        this.button_setSettings.addActionListener(e -> this.setAndCheckNewSettings());
    }

    public void getAndSetSettings()
    {
        this.comboBox_choosingLanguage.setSelectedIndex(this.mainWindow.settings.getInt("language"));
        this.comboBox_choosingTheme.setSelectedIndex(this.mainWindow.settings.getInt("style"));
    }

    public void setAndCheckNewSettings()
    {
        int language = this.comboBox_choosingLanguage.getSelectedIndex();
        int style = this.comboBox_choosingTheme.getSelectedIndex();
        boolean myFlag = (language != this.mainWindow.settings.getInt("language")) || (style != this.mainWindow.settings.getInt("style"));
        boolean close = false;

        if(language != this.mainWindow.settings.getInt("language"))
        {
            this.mainWindow.settings.put("language", language);
            this.mainWindow.loadLanguage();
            this.mainWindow.setLanguage();
        }
        
        if(style != this.mainWindow.settings.getInt("style"))
        {
            this.mainWindow.settings.put("style", style);
            JOptionPane.showMessageDialog(this, this.getLanguageRecord("setNewThemeMessage", 0), this.getLanguageRecord("setNewThemeMessage", 1), JOptionPane.ERROR_MESSAGE);
            close = true;
            if(!this.mainWindow.setLook())
            {
                this.mainWindow.settings.put("style", 0);
            }
        }

        if(myFlag)
        {
            try (FileWriter file = new FileWriter("settings.json")) 
            {
                file.write(this.mainWindow.settings.toString());
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }
        
    
        
        if(close) System.exit(0);
    }
}
