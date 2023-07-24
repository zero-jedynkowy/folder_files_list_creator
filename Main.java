import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;

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

        //VIEW
        this.view = new View();
        this.add(this.view, BorderLayout.CENTER);
        
        this.setVisible(true);
    }
    
    public static void main(String args[])
    {
        new Main();
    }
}