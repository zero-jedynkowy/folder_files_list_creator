import java.awt.BorderLayout;
import java.util.LinkedList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import org.json.JSONObject;

public class Tabs extends BasicElement<JPanel>
{
    private JTabbedPane tabbedModules;
    private LinkedList<BasicElement<JPanel>> list;

    public Tabs(String id)
    {
        super(id, new JPanel());
        this.component.setLayout(new BorderLayout());
        this.tabbedModules = new JTabbedPane();
        this.list = new LinkedList<>();
    }

    @Override
    public void setView() 
    {
        this.list.add(new CreateListModule("CreateListModule"));
        this.list.add(new EditShowModule("EditShowModule"));
        this.list.add(new SettingsModule("SettingsModule"));
        this.list.add(new AboutModule("AboutModule"));
    }

    @Override
    public void addElements()
    {
        for(int i=0; i<this.list.size(); i++)
        {
            this.tabbedModules.addTab("Tworzenie listy", this.list.get(i).component);
        }
        this.tabbedModules.setVisible(true);
        this.component.add(this.tabbedModules);
        this.component.setVisible(true);
    }

    @Override
    public void setActions()
    {}

    @Override
    public void setLanguage() 
    {
        JSONObject key = Language.getGlobalContent("Tabs");
        for(int i=0; i<list.size(); i++)
        {
            this.tabbedModules.setTitleAt(i, key.getString(this.list.get(i).getID()));
        }
    }  
}
