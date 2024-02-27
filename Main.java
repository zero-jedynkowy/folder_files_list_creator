import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main extends JFrame
{
    View view;
    
    final static int WIDTH = 500;
    final static int HEIGHT = 500;

    public Main()
    {
        super();
        
        this.setTitle("Lista plików");
        this.setLayout(new BorderLayout());
        this.setSize(WIDTH, HEIGHT);
        this.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setSystemLook();
        this.view = new View();
        this.add(this.view, BorderLayout.CENTER);
        
        

        this.setVisible(true);
    }
    
    public static void main(String args[])
    {
        new Main();
    }

    public static String newTabCharacterByLevel(int level)
    {
        String x = "";
        for(int i=0; i<level; i++) x += "\t";
        return x;
    }

    public void setSystemLook()
    {
        try 
        {
            UIManager.setLookAndFeel(
            UIManager.getSystemLookAndFeelClassName());
        } 
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) 
        {
            JOptionPane.showMessageDialog(this,"Nie można załadować motywu systemu","Błąd!", JOptionPane.ERROR_MESSAGE);
        }
    }
}