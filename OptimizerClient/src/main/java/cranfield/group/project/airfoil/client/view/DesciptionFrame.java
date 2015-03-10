package cranfield.group.project.airfoil.client.view;

import javax.swing.*;
import javax.swing.border.Border;

public class DesciptionFrame extends JPanel {

    protected ImageIcon icon = new ImageIcon("img/logo.png");
    protected JLabel logo = new JLabel(icon);
     public static final String description = "<html><head>\n"
            + "	<title></title>\n"
            + "</head>\n"
            + "<body>\n"
            + "<p style=\"text-align: center;\"><span style=\"font-family:verdana,geneva,sans-serif;\"><span style=\"font-size: 26px;\">MARS application</span></span></p>\n"
            + "\n"
            + "<p><span style=\"font-family:verdana,geneva,sans-serif;\">Multi-Platform application</span></p>\n"
            + "\n"
            + "<hr />\n"
            + "<p><span style=\"font-family:verdana,geneva,sans-serif;\">Version 1.0</span></p>\n"
            + "\n"
            + "<p><span style=\"font-family:verdana,geneva,sans-serif;\">Software engineering:&nbsp; </span></p>\n"
            + "\n"
            + "<ol>\n"
            + "	<li><span style=\"font-family:verdana,geneva,sans-serif;\">KACPRZAK Emilia, </span></li>\n"
            + "	<li><span style=\"font-family:verdana,geneva,sans-serif;\">RYBAK Katarzyna, </span></li>\n"
            + "	<li><span style=\"font-family:verdana,geneva,sans-serif;\">LE BRIS Aurore, </span></li>\n"
            + "	<li><span style=\"font-family:verdana,geneva,sans-serif;\">KOSECKI Jan, </span></li>\n"
            + "	<li><span style=\"font-family:verdana,geneva,sans-serif;\">SANANIKONE Maud</span></li>\n"
            + "</ol>\n"
            + "\n"
            + "<p><span style=\"font-family:verdana,geneva,sans-serif;\">MARS (meaning Monitoring Application and Recovery System) is a multi-platform application allowing the user to find the best value of the parameters for designing airfoil of airplane wings.</span></p>\n"
            + "\n"
            + "<p><span style=\"font-family:verdana,geneva,sans-serif;\">The user should input:</span></p>\n"
            + "\n"
            + "<ol>\n"
            + "	<li><span style=\"font-family:verdana,geneva,sans-serif;\">Span (m)</span></li>\n"
            + "	<li><span style=\"font-family:verdana,geneva,sans-serif;\">Chord (m)</span></li>\n"
            + "	<li><span style=\"font-family:verdana,geneva,sans-serif;\">Leading edge (degree)</span></li>\n"
            + "	<li><span style=\"font-family:verdana,geneva,sans-serif;\">Aeroplane mass ( kg )</span></li>\n"
            + "	<li><span style=\"font-family:verdana,geneva,sans-serif;\">Minimal Drag Coefficient ( depending on the type of the airplane )</span></li>\n"
            + "	<li><span style=\"font-family:verdana,geneva,sans-serif;\">Max lift coefficient ( depending on the type of the airplane)</span></li>\n"
            + "	<li><span style=\"font-family:verdana,geneva,sans-serif;\">Air speed ( m/s )</span></li>\n"
            + "	<li><span style=\"font-family:verdana,geneva,sans-serif;\">Minimal air speed ( m/s ) - minimal air speed that allows given plane to fly</span></li>\n"
            + "	<li><span style=\"font-family:verdana,geneva,sans-serif;\">Iteration number</span></li>\n"
            + "</ol>\n"
            + "\n"
            + "<p><span style=\"font-family:verdana,geneva,sans-serif;\">There MARS should display:</span></p>\n"
            + "\n"
            + "<ol>\n"
            + "	<li><span style=\"font-family:verdana,geneva,sans-serif;\">The ratio in a graph while the system computes it</span></li>\n"
            + "</ol>\n"
            + "\n"
            + "<p><span style=\"font-family:verdana,geneva,sans-serif;\">MARS contains 3 different modules:</span></p>\n"
            + "\n"
            + "<ol>\n"
            + "	<li><span style=\"font-family:verdana,geneva,sans-serif;\">The interface v1.0 (Java)</span></li>\n"
            + "	<li><span style=\"font-family:verdana,geneva,sans-serif;\">The server v1.0 (Java)</span></li>\n"
            + "	<li><span style=\"font-family:verdana,geneva,sans-serif;\">The database v1.0 (Google Cloud SQL)</span></li>\n"
            + "</ol>\n"
            + "\n"
            + "<p>&nbsp;</p>\n"
            + "\n"
            + "<p><span style=\"font-family:verdana,geneva,sans-serif;\">MARS should provide a recovery system to the user in order to start again the computation from the last point in case of failure.</span></p>\n"
            + "</body></html>";
    protected JLabel labeDescription = new JLabel(description);
    protected JPanel panelComponent = new JPanel();

    DesciptionFrame() {
        panelComponent.setLayout(new BoxLayout(panelComponent, BoxLayout.Y_AXIS));
        Border padding = BorderFactory.createEmptyBorder(10, 200, 10, 200);

        panelComponent.setBorder(padding);
        labeDescription.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        panelComponent.add(labeDescription);
        panelComponent.add(logo);
    }

}