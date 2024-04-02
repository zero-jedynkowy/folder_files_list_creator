import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import org.json.JSONObject;
import java.io.InputStream;

public class Language
{
    private static HashMap<String, BasicElement> components = new HashMap<>();
    private static JSONObject globalContent;
    private String id;

    public Language(String id, BasicElement comp)
    {
        this.id = id;
        Language.components.put(id, comp);
    }

    public static void setGlobalContent(JSONObject obj)
    {
        Language.globalContent = obj;
    }

    static JSONObject getGlobalContent(String key)
    {
        return Language.globalContent.getJSONObject(key);
    }

    public String getID()
    {
        return this.id;
    }

    static void loadLanguage(int language)
    {
        String fileName = "";
        switch(language)
        {
            case 0:
                fileName = "sources/english.json";
                break;
            case 1:
                fileName = "sources/polski.json";
                break;
        }
        String data = "";
        try 
        {
            InputStream myObj = Settings.class.getClassLoader().getResourceAsStream(fileName);
            
            Scanner myReader = new Scanner(myObj);
            
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
        Language.setGlobalContent(new JSONObject(data));
    }

    public static void setLanguage()
    {
        BasicElement temp;
        
        for(Map.Entry<String, BasicElement> c : Language.components.entrySet())
        {
            
            temp = Language.components.get(c.getKey());
            temp.setLanguage();
        }
    }

    public String getLanguageRecord(String key, int index)
    {
        JSONObject temp = Language.globalContent.getJSONObject(id);
        return temp.getJSONArray(key).getString(index);
    }
}

interface LanguageInterface
{
    void setLanguage();
}