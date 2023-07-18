import javax.swing.JButton;
import javax.swing.JPanel;

public class View extends JPanel
{
    JButton selectButton;

    public View()
    {
        super();
        this.setBounds(0, 0, 500, 500);
        this.setLayout(null);
        
        //SET VIEW METHODS
        this.setSelectButton();

        this.setVisible(true);
    }


    //SET VIEW METHODS
    public void setSelectButton()
    {
        this.selectButton = new JButton("Wybierz folder");
        this.selectButton.setLocation(15, 15);
        this.selectButton.setSize(this.selectButton.getText().length()*9, 50);
        this.add(this.selectButton);
    }
}
