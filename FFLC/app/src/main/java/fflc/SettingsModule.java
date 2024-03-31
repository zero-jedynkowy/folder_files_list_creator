package fflc;

import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.json.JSONObject;

public class SettingsModule extends BasicModule
{
    JLabel label_settingsHeader;
    JPanel panel_choosingLanguage;
        JLabel label_choosingLanguage;
        JComboBox comboBox_choosingLanguage;
    JButton button_setSettings;

    public SettingsModule(String id) 
    {
        super(id);
    }

    @Override
    public void setView() 
    {
         //
        this.label_settingsHeader = new JLabel("Ustawienia", JLabel.CENTER);
        this.label_settingsHeader.setVisible(true);
        this.label_settingsHeader.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        BasicElement.changeFontSize(this.label_settingsHeader, 20);
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
        this.label_choosingLanguage.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        BasicElement.changeFontSize(this.label_choosingLanguage, 20);
        this.label_choosingLanguage.setHorizontalAlignment(SwingConstants.LEFT);
        this.label_choosingLanguage.setMaximumSize(new Dimension(400, 50));

        //
        String[] langauges = {"English", "Polski"};
        this.comboBox_choosingLanguage = new JComboBox<String>(langauges);
        this.comboBox_choosingLanguage.setSelectedItem(0);
        this.comboBox_choosingLanguage.setMaximumSize(new Dimension(500, 50));
        this.comboBox_choosingLanguage.setMinimumSize(new Dimension(500, 50));
    
        this.button_setSettings = new JButton("Set/Ustaw");
        BasicElement.changeFontSize(this.button_setSettings, 15);
        this.button_setSettings.setMaximumSize(new Dimension(400, 50));
        this.button_setSettings.setAlignmentX(JButton.CENTER_ALIGNMENT);

    }

    @Override
    public void addElements() 
    {
        this.component.add(Box.createVerticalStrut(10));
        this.component.add(this.label_settingsHeader);
        this.component.add(Box.createVerticalStrut(10));
        this.component.add(this.panel_choosingLanguage);
            this.panel_choosingLanguage.add(this.label_choosingLanguage);
            this.panel_choosingLanguage.add(Box.createHorizontalStrut(100));
            this.panel_choosingLanguage.add(this.comboBox_choosingLanguage);
            this.component.add(Box.createVerticalStrut(10));
        this.component.add(Box.createVerticalStrut(50));
        this.component.add(this.button_setSettings);
    }

    @Override
    public void setActions() 
    {
        this.button_setSettings.addActionListener(e -> this.setAndCheckNewSettings());
    }

    @Override
    public void setLanguage() 
    {
        this.label_settingsHeader.setText(this.language.getLanguageRecord("label_settingsHeader", 0));
    }

    public void setAndCheckNewSettings()
    {
        boolean first = (int)this.settings.getSetting("global", "language") == this.comboBox_choosingLanguage.getSelectedIndex();
        if(!first)
        {
            Settings.setSetting("global", "language", this.comboBox_choosingLanguage.getSelectedIndex());
            Language.loadLanguage((int)Settings.getSetting("global", "language"));
            Language.setLanguage();
        }
        Settings.saveSettings();
    }
}
