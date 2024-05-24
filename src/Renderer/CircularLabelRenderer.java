package Renderer;

import javax.swing.*;
import javax.swing.plaf.basic.BasicLabelUI;
import java.awt.*;
import java.awt.geom.Ellipse2D;

import Assets.ConstantesColor;

public class CircularLabelRenderer extends BasicLabelUI {

    @Override
    public void paint(Graphics g, JComponent c) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = c.getWidth();
        int height = c.getHeight();
        int diameter = Math.min(width, height);

        g2d.setClip(new Ellipse2D.Double(0, 0, diameter, diameter));

        Point center = new Point(width / 2, height / 2);
        float radius = Math.min(width, height) / 2f;
        float[] dist = {0.0f, 1.0f};
        Color[] colors = {ConstantesColor.blanco, ConstantesColor.fondo};
        RadialGradientPaint gradient = new RadialGradientPaint(center, radius, dist, colors, MultipleGradientPaint.CycleMethod.NO_CYCLE);

        g2d.setPaint(gradient);
        g2d.fill(new Ellipse2D.Double(0, 0, diameter, diameter));

        Icon icon = ((JLabel) c).getIcon();
        if (icon != null) {
            int iconWidth = icon.getIconWidth();
            int iconHeight = icon.getIconHeight();
            int iconX = (diameter - iconWidth) / 2;
            int iconY = (diameter - iconHeight) / 2;
            icon.paintIcon(c, g2d, iconX, iconY);
        }

        String text = ((JLabel) c).getText();
        if (text != null && !text.isEmpty()) {
            g2d.setColor(c.getForeground());
            g2d.setFont(c.getFont());
            FontMetrics metrics = g2d.getFontMetrics();
            int textX = (diameter - metrics.stringWidth(text)) / 2;
            int textY = ((diameter - metrics.getHeight()) / 2) + metrics.getAscent();
            g2d.drawString(text, textX, textY);
        }

        g2d.dispose();
    }

    @Override
    public Dimension getPreferredSize(JComponent c) {
        Dimension size = super.getPreferredSize(c);
        int diameter = Math.max(size.width, size.height);
        return new Dimension(diameter, diameter);
    }
}
