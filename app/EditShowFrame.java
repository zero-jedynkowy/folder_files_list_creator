import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;

public class EditShowFrame extends BasicElement<JFrame> implements ComponentListener, WindowListener, ChangeListener
{
    private JPanel mainPanel;
    private JInternalFrame frame_leftFrame;
        public JScrollPane scrollPane_treeParent;
        JTree tree;
        DefaultMutableTreeNode node;
    private JInternalFrame frame_rightFrame;
        private JPanel panel_rightFrameContent;
            private JLabel label_scaleTitle;
            private JSlider slider_scaleSlider;
            private JSeparator separator_firstSeparator;
            private JTextField textField_searchBar;
            private JButton button_findButton;
            private JSeparator separator_secondSeparator;
            private JButton button_exportData;
 
    

    public EditShowFrame(String id) 
    {
        super(id, new JFrame());
        this.component.setResizable(true);
        this.component.setLocationRelativeTo(Main.mainWindow);
        this.component.setMinimumSize(new Dimension(750, 500));
        this.component.setSize(new Dimension(Main.WIDTH, Main.HEIGHT));
        this.init();
    }

    @Override
    public void setView() 
    {
        this.component.setLayout(new BorderLayout());
        
        this.frame_leftFrame = this.createElement("frame_leftFrame", new JInternalFrame());
        this.frame_leftFrame.setMaximumSize(new Dimension(Main.WIDTH/2, Main.HEIGHT));
        this.frame_leftFrame.setVisible(true);

            this.scrollPane_treeParent = new JScrollPane();
            this.scrollPane_treeParent.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        this.frame_rightFrame = this.createElement("frame_rightFrame", new JInternalFrame());
        this.frame_rightFrame.setMaximumSize(new Dimension(Main.WIDTH/2, Main.HEIGHT));
        this.frame_rightFrame.setVisible(true);

            this.panel_rightFrameContent = new JPanel();
            this.panel_rightFrameContent.setLayout(new BoxLayout(this.panel_rightFrameContent, BoxLayout.Y_AXIS));
            this.panel_rightFrameContent.setVisible(true);

            this.label_scaleTitle = this.createElement("label_scaleTitle", new JLabel("Scale: 1.0x", JLabel.CENTER));
            this.label_scaleTitle.setVisible(true);
            this.label_scaleTitle.setAlignmentX(JPanel.CENTER_ALIGNMENT);
            BasicElement.changeFontSize(this.label_scaleTitle, 25);
            this.label_scaleTitle.setHorizontalAlignment(SwingConstants.CENTER);
            this.label_scaleTitle.setMaximumSize(new Dimension(200, 50));

            this.slider_scaleSlider = new JSlider();
            this.slider_scaleSlider.setMinimum(1);
            this.slider_scaleSlider.setMaximum(150);
            this.slider_scaleSlider.setValue(75);
            this.slider_scaleSlider.setMajorTickSpacing(100);
            this.slider_scaleSlider.setMinorTickSpacing(10);
            this.slider_scaleSlider.setMaximumSize(new Dimension(200, 50));

            this.separator_firstSeparator = new JSeparator();
            this.separator_firstSeparator.setMaximumSize(new Dimension(200, 10));

            this.textField_searchBar = new JTextField();
            BasicElement.changeFontSize(this.textField_searchBar, 20);
            this.textField_searchBar.setMaximumSize(new Dimension(200, 50));
            this.textField_searchBar.setBorder(BorderFactory.createCompoundBorder(this.textField_searchBar.getBorder(), BorderFactory.createEmptyBorder(10, 10, 10, 10)));

            this.button_findButton = new JButton("Szukaj");
            this.button_findButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
            BasicElement.changeFontSize(this.button_findButton, 20);
            this.button_findButton.setMaximumSize(new Dimension(200, 50));

            this.separator_secondSeparator = new JSeparator();
            this.separator_secondSeparator.setMaximumSize(new Dimension(200, 10));

            this.button_exportData = new JButton("Eksport danych");
            this.button_exportData.setAlignmentX(JButton.CENTER_ALIGNMENT);
            BasicElement.changeFontSize(this.button_exportData, 15);
            this.button_exportData.setMaximumSize(new Dimension(200, 50));
    }

    @Override
    public void addElements() 
    {
        this.component.add(this.frame_leftFrame, BorderLayout.WEST);
        this.component.add(this.frame_rightFrame, BorderLayout.CENTER);
        this.frame_leftFrame.setPreferredSize(new Dimension((int)(0.7*this.component.getWidth()), this.frame_leftFrame.getHeight()));

            this.frame_leftFrame.add(this.scrollPane_treeParent);

        this.frame_rightFrame.setPreferredSize(new Dimension((int)(0.3*this.component.getWidth()), this.frame_rightFrame.getHeight()));
        
            this.frame_rightFrame.add(this.panel_rightFrameContent);
                this.panel_rightFrameContent.add(Box.createVerticalStrut(5));
                this.panel_rightFrameContent.add(this.label_scaleTitle);
                this.panel_rightFrameContent.add(this.slider_scaleSlider);
                this.panel_rightFrameContent.add(this.separator_firstSeparator);
                this.panel_rightFrameContent.add(Box.createVerticalStrut(5));
                this.panel_rightFrameContent.add(this.textField_searchBar);
                this.panel_rightFrameContent.add(Box.createVerticalStrut(5));
                this.panel_rightFrameContent.add(this.button_findButton);
                this.panel_rightFrameContent.add(Box.createVerticalStrut(10));
                this.panel_rightFrameContent.add(this.separator_secondSeparator);
                this.panel_rightFrameContent.add(Box.createVerticalStrut(5));
                this.panel_rightFrameContent.add(this.button_exportData);
    }

    void show(boolean flag, JTree tree, DefaultMutableTreeNode node)
    {
        if(flag)
        {
            this.tree = tree;
            this.node = node;
            this.scrollPane_treeParent.setBackground(null);
            this.tree.setBackground(null);
            this.tree.setCellRenderer(this.createRenderer(25));
            this.slider_scaleSlider.setValue(25);
            
            this.scrollPane_treeParent.setViewportView(this.tree);
            

            this.component.setVisible(flag);
        }
        else
        {
            this.component.setVisible(false);
        }
    }

    @Override
    public void setActions() 
    {
        this.component.addWindowListener(this);
        this.component.addComponentListener(this);
        this.slider_scaleSlider.addChangeListener(this);
        this.button_findButton.addActionListener(e -> {
            DefaultTreeModel model = (DefaultTreeModel)this.tree.getModel();
            DefaultMutableTreeNode root = (DefaultMutableTreeNode)model.getRoot();
            
            // model.reload(root);
            
            
            System.out.println();
        });
    }

    @Override
    public void setLanguage() 
    {
        this.frame_leftFrame.setTitle(this.language.getLanguageRecord("frame_leftFrame", 0));
        this.frame_rightFrame.setTitle(this.language.getLanguageRecord("frame_rightFrame", 0));
    }

    @Override
    public void componentHidden(ComponentEvent arg0) 
    {
          
    }

    @Override
    public void componentMoved(ComponentEvent arg0) 
    {
     
    }

    @Override
    public void componentResized(ComponentEvent arg0) 
    {
        // this.frame_leftFrame.setPreferredSize(new Dimension((int)(0.75*this.component.getWidth()), this.frame_leftFrame.getHeight()));
        // this.frame_rightFrame.setPreferredSize(new Dimension((int)(0.25*this.component.getWidth()), this.frame_rightFrame.getHeight()));
        this.frame_leftFrame.setPreferredSize(new Dimension(this.component.getWidth() - 250, this.frame_leftFrame.getHeight()));
        this.frame_rightFrame.setPreferredSize(new Dimension(250, this.frame_rightFrame.getHeight()));
        this.component.revalidate();
        System.out.println(this.frame_rightFrame.getWidth());
    }

    @Override
    public void componentShown(ComponentEvent arg0) 
    {
       
    }

    @Override
    public void windowActivated(WindowEvent arg0) {
        // TODO Auto-generated method stub
        
        // throw new UnsupportedOperationException("Unimplemented method 'windowActivated'");
    }

    @Override
    public void windowClosed(WindowEvent arg0) {
        // TODO Auto-generated method stub
        Main.mainWindow.setEnabled(true);
    }

    @Override
    public void windowClosing(WindowEvent arg0) {
        Main.mainWindow.setEnabled(true);
        // throw new UnsupportedOperationException("Unimplemented method 'windowClosing'");
    }

    @Override
    public void windowDeactivated(WindowEvent arg0) {
        // TODO Auto-generated method stub
        
        
        // throw new UnsupportedOperationException("Unimplemented method 'windowDeactivated'");
    }

    @Override
    public void windowDeiconified(WindowEvent arg0) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'windowDeiconified'");
    }

    @Override
    public void windowIconified(WindowEvent arg0) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'windowIconified'");
    }

    @Override
    public void windowOpened(WindowEvent arg0) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'windowOpened'");
    }
    

    DefaultTreeCellRenderer createRenderer(int scale)
    {
        int iconSize = scale;
        DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer() {
            @Override
            public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
                super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
                setBackground(null);
                setFont(tree.getFont().deriveFont(Font.BOLD, iconSize));
                setBackgroundNonSelectionColor(null); 

                setOpenIcon(scaleIcon(getOpenIcon(), iconSize));
                setClosedIcon(scaleIcon(getClosedIcon(), iconSize));
                setLeafIcon(scaleIcon(getLeafIcon(), iconSize));
                return this;
            }
        };

        
        return renderer;
    }

    private static ImageIcon scaleIcon(Icon icon, int size) {
        if (icon instanceof ImageIcon) {
            Image img = ((ImageIcon) icon).getImage();
            Image scaledImg = img.getScaledInstance(size, size, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImg);
        }
        return null;
    }

    @Override
    public void stateChanged(ChangeEvent arg0) 
    {
        if((JSlider)arg0.getSource() == this.slider_scaleSlider)
        {
            this.tree.setCellRenderer(this.createRenderer(this.slider_scaleSlider.getValue()));
        }
    }
}