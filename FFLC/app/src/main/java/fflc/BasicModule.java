package fflc;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public abstract class BasicModule extends BasicElement<JPanel>
{
    public BasicModule(String id) 
    {
        super(id, new JPanel());
        this.component.setLayout(new BoxLayout(this.component, BoxLayout.Y_AXIS));
        this.init();
    }
}
