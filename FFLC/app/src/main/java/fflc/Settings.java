package fflc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Scanner;

import org.json.JSONObject;

interface SettingsInterface
{
    void setSettings();
}

public class Settings 
{
    private static JSONObject allSettings;
    private String id;
    private static boolean changeSettingsFlag = false;
    private static HashMap<String, BasicElement> objects = new HashMap<>();

    public Settings(String id, BasicElement comp)
    {
        this.id = id;
        Settings.objects.put(id, comp);
    }

    public static void loadSettings()
    {
        String data = "";
        InputStream inputStream = Settings.class.getClassLoader().getResourceAsStream("sources/settings.json");
        try 
        {
            // File myObj = new File("app/sources/settings.json");
            Scanner myReader = new Scanner(inputStream);
            while (myReader.hasNextLine()) {
            data += myReader.nextLine();
        }
        myReader.close();
        } 
        catch (Exception e) 
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        Settings.allSettings = new JSONObject(data);
    }

    public static void saveSettings()
    {

        try (FileWriter file = new FileWriter("app/sources/settings.json")) 
        {
            file.write(Settings.allSettings.toString());
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        
    }

    public static void setSetting(String firstKey, String secondKey, int value)
    {
        Settings.allSettings.getJSONObject(firstKey).put(secondKey, value);
    }

    public static Object getSetting(String firstKey, String secondKey)
    {
        return Settings.allSettings.getJSONObject(firstKey).getInt(secondKey);
    }
}
