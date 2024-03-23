import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

public class EditShowFrame extends JFrame implements ComponentListener
{
    private Main mainWindow;
    JInternalFrame a;
    JInternalFrame b;
    
    public EditShowFrame(Main mainWindow)
    {
        super();
        this.mainWindow = mainWindow;
        this.setResizable(true);
        this.setMinimumSize(new Dimension(750, 500));
        this.setSize(new Dimension(Main.WIDTH, Main.HEIGHT));
        this.setResizable(true);
        JPanel x = new JPanel();
        this.add(x);
        x.setLayout(new BoxLayout(x, BoxLayout.X_AXIS));
        a = new JInternalFrame();
        a.setMaximumSize(new Dimension(Main.WIDTH/2, Main.HEIGHT));
        x.add(a);
        a.setVisible(true);
        a.setTitle("Drzewo/lista plików i katalogów");
        b = new JInternalFrame();
        b.setMaximumSize(new Dimension(Main.WIDTH/2, Main.HEIGHT));
        x.add(b);
        b.setVisible(true);
        b.setTitle("Menu");
        this.addComponentListener(this);
    }

    @Override
    public void setVisible(boolean b) 
    {
        this.mainWindow.setEnabled(false);
        super.setVisible(b);
    }

    protected void processWindowEvent(WindowEvent e) 
    {
        super.processWindowEvent(e);
        if(e.getID() == WindowEvent.WINDOW_CLOSING) 
        {
            this.mainWindow.setEnabled(true);
        }
    }

    @Override
    public void componentHidden(ComponentEvent arg0) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'componentHidden'");
    }

    @Override
    public void componentMoved(ComponentEvent arg0) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'componentMoved'");
    }

    @Override
    public void componentResized(ComponentEvent arg0) {
        b.setMaximumSize(new Dimension((int)(this.getWidth()*(0.25)), this.getHeight()));
        a.setMaximumSize(new Dimension((int)(this.getWidth()*(0.75)), this.getHeight()));
        // a.paintImmediately(a.getVisibleRect());
        // b.paintImmediately(b.getVisibleRect());
    }

    @Override
    public void componentShown(ComponentEvent arg0) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'componentShown'");
    }
}

class ProgressCreatingTree extends JDialog
{
    JPanel panel_content;
        JLabel label_firstTitle;
        JProgressBar progressBar_progressFiles;
        JLabel label_secondTitle;
        JProgressBar progressBar_progressLines;
        AbstractModule parenModule;
    
    public ProgressCreatingTree(Frame owner, String title, boolean modal, AbstractModule parentModule)
    {
        super(owner, title, modal);
        this.parenModule = parentModule;
        this.setMinimumSize(new Dimension(400, 200));
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        this.panel_content = new JPanel();
        this.panel_content.setMinimumSize(new Dimension(400, 200));
        this.panel_content.setLayout(new BoxLayout(this.panel_content, BoxLayout.Y_AXIS));

        this.label_firstTitle = new JLabel("Przetwarzanie plików:", JLabel.CENTER);
        this.label_firstTitle.setVisible(true);
        this.label_firstTitle.setAlignmentX(CENTER_ALIGNMENT);
        AbstractModule.changeFontSize(this.label_firstTitle, 15);
        this.label_firstTitle.setHorizontalAlignment(SwingConstants.CENTER);
        this.label_firstTitle.setMaximumSize(new Dimension(400, 50));
        
        this.progressBar_progressFiles = new JProgressBar(0, 100);
        this.progressBar_progressFiles.setMaximumSize(new Dimension(300, 50));
        this.progressBar_progressFiles.setStringPainted(true);    
        this.progressBar_progressFiles.setValue(0);


        this.label_secondTitle = new JLabel("Przetwarzanie linijek obecnego pliku:", JLabel.CENTER);
        this.label_secondTitle.setVisible(true);
        this.label_secondTitle.setAlignmentX(CENTER_ALIGNMENT);
        AbstractModule.changeFontSize(this.label_secondTitle, 15);
        this.label_secondTitle.setHorizontalAlignment(SwingConstants.CENTER);
        this.label_secondTitle.setMaximumSize(new Dimension(400, 50));

        this.progressBar_progressLines = new JProgressBar(0, 200);
        this.progressBar_progressLines.setMaximumSize(new Dimension(300, 50));
        this.progressBar_progressLines.setStringPainted(true);    
        this.progressBar_progressLines.setValue(0);

        this.panel_content.add(Box.createVerticalStrut(10));
        this.panel_content.add(this.label_firstTitle);
        this.panel_content.add(Box.createVerticalStrut(10));
        this.panel_content.add(this.progressBar_progressFiles);
        this.panel_content.add(Box.createVerticalStrut(10));
        this.panel_content.add(this.label_secondTitle);
        this.panel_content.add(Box.createVerticalStrut(10));
        this.panel_content.add(this.progressBar_progressLines);
        this.panel_content.add(Box.createVerticalStrut(20));

        this.add(this.panel_content);
    }

    public void setMaxCounts(long maxFiles, long maxLines)
    {
        if(maxFiles != -1) this.progressBar_progressFiles.setMaximum((int)maxFiles);
        if(maxLines != -1) this.progressBar_progressLines.setMaximum((int)maxLines);
    }

    public void setCounts(long countFiles, long countLines)
    {
        if(countFiles != -1) this.progressBar_progressFiles.setValue((int)countFiles);
        if(countLines != -1) this.progressBar_progressLines.setValue((int)countLines);
    }

    protected void processWindowEvent(WindowEvent e) 
    {
        super.processWindowEvent(e);
        if(e.getID() == WindowEvent.WINDOW_CLOSING) 
        {
            ((EditShowListModule)this.parenModule).flag = false;
        }
    }

    @Override
    public void setVisible(boolean b) 
    {
        this.setMaxCounts(0, 0);
        this.setCounts(0, 0);
        super.setVisible(b);
    }
}