import java.awt.Component;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.json.JSONObject;

public abstract class AbstractModule extends JPanel
{
    Main mainWindow;
    HashMap<String, Component> listOfElements;
    JSONObject languageContent;

    public AbstractModule(Main mainWindow)
    {
        super(false);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.listOfElements = new HashMap<>();
        this.mainWindow = mainWindow;
    }

    public void init()
    {
        this.setView();
        this.addElements();
        this.setActions();
    }

    public abstract void setView();

    public abstract void addElements();

    public abstract void setActions();

    public void setLanguage(JSONObject languageContent)
    {
        Component temp;
        String content;
        this.languageContent = languageContent;
        
        for(Map.Entry<String, Component> c : this.listOfElements.entrySet())
        {
            
            temp = this.listOfElements.get(c.getKey());
            content = languageContent.getJSONArray(c.getKey()).getString(0);
            System.out.println(content);
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

    public static void changeFontSize(Component comp, int newSize)
    {
        comp.setFont(comp.getFont().deriveFont((float) newSize));
    }

    public static String newTabCharacterByLevel(int level)
    {
        String x = "";
        for(int i=0; i<level; i++) x += "\t";
        return x;
    }

    String getLanguageRecord(String key, int id)
    {
        return this.languageContent.getJSONArray(key).getString(id);
    }
}