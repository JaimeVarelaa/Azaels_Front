package Renderer;

import javax.swing.plaf.basic.BasicPanelUI;
import java.awt.*;
import javax.swing.JComponent;

public class LoginRenderer extends BasicPanelUI {

    @Override
    public void paint(Graphics g, JComponent c) {
        super.paint(g, c);
        Graphics2D g2d = (Graphics2D) g.create();

        int width = c.getWidth();
        int height = c.getHeight();
        
        GradientPaint gradient = new GradientPaint(0, 0, new Color(247, 192, 192), 0, height, new Color(255, 128, 128));
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, width, height);

        g2d.dispose();
        c.repaint();
    }
}
