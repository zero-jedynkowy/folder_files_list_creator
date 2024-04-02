import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.*;
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
        Path tempDir = Paths.get(System.getProperty("java.io.tmpdir"), "settings_fflc.json");
        String data = "";
        InputStream inputStream = null;
        Scanner myReader = null;
        if(!Files.exists(tempDir))
        {
            inputStream = Settings.class.getClassLoader().getResourceAsStream("sources/settings_fflc.json");
            myReader = new Scanner(inputStream);
            while (myReader.hasNextLine()) 
            {
                data += myReader.nextLine();
            }
            myReader.close();
            try 
            {
                FileWriter file = new FileWriter(tempDir.toString());
                file.write(data);
                file.close();
            } 
            catch (Exception e) 
            {
                System.out.println("An error occurred.");
            }
            System.err.println(tempDir.toString());
        }
        
        data = "";
        try 
        {
            inputStream = new FileInputStream(tempDir.toString());
            // File myObj = new File("app/sources/settings.json");
            myReader = new Scanner(inputStream);
            while (myReader.hasNextLine()) {
            data += myReader.nextLine();}
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
        System.out.println(Settings.allSettings.toString());
        Path tempDir = Paths.get(System.getProperty("java.io.tmpdir"), "settings_fflc.json");
        try (FileWriter file = new FileWriter(tempDir.toString())) 
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
