import java.awt.Component;
import java.util.HashMap;

import javax.print.DocFlavor.STRING;

interface BasicSetters
{
    default void init()
    {
        this.setView();
        this.addElements();
        this.setActions();
    }
    
    void setView();
    void addElements();
    void setActions();
}

public abstract class BasicElement<T extends Component> implements BasicSetters, LanguageInterface
{
    protected Language language;
    protected Settings settings;
    protected T component;
    protected HashMap<String, Component> listOfElements;

    public BasicElement(String id, T comp)
    {
        this.component = comp;
        this.listOfElements = new HashMap<>();
        this.language = new Language(id, this);
    }

    public static void changeFontSize(Component comp, int newSize)
    {
        comp.setFont(comp.getFont().deriveFont((float) newSize));
    }

    public String getID()
    {
        return this.language.getID();
    }

    public <K extends Component> K createElement(String id, K comp)
    {
        this.listOfElements.put(id, comp);
        return comp;
    }
}