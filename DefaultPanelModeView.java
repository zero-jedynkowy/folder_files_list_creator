import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public abstract class DefaultPanelModeView extends JPanel
{
    Main mainWindow;

    public DefaultPanelModeView(Main mainWindow)
    {
        super(false);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.mainWindow = mainWindow;
    }

    public abstract void setView();

    public abstract void addElements();

    public abstract void setActions();

    public void init()
    {
        this.setView();
        this.addElements();
        this.setActions();
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
}