import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import org.json.JSONObject;

public abstract class AbstractModule extends JPanel
{
    Main mainWindow;

    public AbstractModule(Main mainWindow)
    {
        super(false);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
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

    public abstract void setLanguage(JSONObject languageContent);

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
}