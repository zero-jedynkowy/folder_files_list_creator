import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;


public class ProgressCreatingTree extends BasicElement<JDialog> implements WindowListener
{
    JPanel panel_content;
        JLabel label_firstTitle;
        JProgressBar progressBar_progressFiles;
        JLabel label_secondTitle;
        JProgressBar progressBar_progressLines;
        BasicElement<JPanel> parentModule;

    public ProgressCreatingTree(String id, Frame owner, BasicElement parent) 
    {
        super(id, new JDialog(owner, "", true));
        this.parentModule = parent;
        this.component.setMinimumSize(new Dimension(400, 200));
        this.component.setLocationRelativeTo(null);
        this.component.setResizable(false);
        this.init();
    }

    
    @Override
    public void setView() 
    {
        this.panel_content = new JPanel();
        this.panel_content.setMinimumSize(new Dimension(400, 200));
        this.panel_content.setLayout(new BoxLayout(this.panel_content, BoxLayout.Y_AXIS));

        this.label_firstTitle = this.createElement("label_firstTitle", new JLabel("Przetwarzanie plików:", JLabel.CENTER));
        this.label_firstTitle.setVisible(true);
        this.label_firstTitle.setAlignmentX(JDialog.CENTER_ALIGNMENT);
        BasicElement.changeFontSize(this.label_firstTitle, 15);
        this.label_firstTitle.setHorizontalAlignment(SwingConstants.CENTER);
        this.label_firstTitle.setMaximumSize(new Dimension(400, 50));
        
        this.progressBar_progressFiles = new JProgressBar(0, 100);
        this.progressBar_progressFiles.setMaximumSize(new Dimension(300, 50));
        this.progressBar_progressFiles.setStringPainted(true);    
        this.progressBar_progressFiles.setValue(0);


        this.label_secondTitle = this.createElement("label_secondTitle", new JLabel("Przetwarzanie linijek obecnego pliku:", JLabel.CENTER));
        this.label_secondTitle.setVisible(true);
        this.label_secondTitle.setAlignmentX(JDialog.CENTER_ALIGNMENT);
        BasicElement.changeFontSize(this.label_secondTitle, 15);
        this.label_secondTitle.setHorizontalAlignment(SwingConstants.CENTER);
        this.label_secondTitle.setMaximumSize(new Dimension(400, 50));

        this.progressBar_progressLines = new JProgressBar(0, 200);
        this.progressBar_progressLines.setMaximumSize(new Dimension(300, 50));
        this.progressBar_progressLines.setStringPainted(true);    
        this.progressBar_progressLines.setValue(0);
    }

    @Override
    public void addElements() 
    {
        this.panel_content.add(Box.createVerticalStrut(10));
        this.panel_content.add(this.label_firstTitle);
        this.panel_content.add(Box.createVerticalStrut(10));
        this.panel_content.add(this.progressBar_progressFiles);
        this.panel_content.add(Box.createVerticalStrut(10));
        this.panel_content.add(this.label_secondTitle);
        this.panel_content.add(Box.createVerticalStrut(10));
        this.panel_content.add(this.progressBar_progressLines);
        this.panel_content.add(Box.createVerticalStrut(20));

        this.component.add(this.panel_content);
    }

    @Override
    public void setActions() 
    {
        this.component.addWindowListener(this);
    }

    @Override
    public void setLanguage() 
    {
        this.label_firstTitle.setText(this.language.getLanguageRecord("label_firstTitle", 0));
        this.label_secondTitle.setText(this.language.getLanguageRecord("label_secondTitle", 0));
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


    @Override
    public void windowActivated(WindowEvent arg0)
    {}


    @Override
    public void windowClosed(WindowEvent arg0) 
    {
        if((JDialog)arg0.getSource() == this.component)
        {
            ((EditShowModule)this.parentModule).flag = false;
        }
    }


    @Override
    public void windowClosing(WindowEvent arg0) 
    {}


    @Override
    public void windowDeactivated(WindowEvent arg0) 
    {}


    @Override
    public void windowDeiconified(WindowEvent arg0) 
    {}


    @Override
    public void windowIconified(WindowEvent arg0) 
    {}


    @Override
    public void windowOpened(WindowEvent arg0) 
    {}
}
