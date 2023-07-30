import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public abstract class DefaultPanelModeView extends JPanel
{
    public DefaultPanelModeView()
    {
        super(false);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public abstract void setView();
    public abstract void addElements();

    public static void changeFontSize(Component comp, int newSize)
    {
        comp.setFont(comp.getFont().deriveFont((float) newSize));
    }
}