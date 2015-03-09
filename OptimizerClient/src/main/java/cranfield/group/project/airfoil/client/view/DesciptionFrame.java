package cranfield.group.project.airfoil.client.view;

import java.awt.GridLayout;
import javax.swing.*;

public class DesciptionFrame extends JPanel {

    protected ImageIcon icon = new ImageIcon("img/logo.png");
    protected JLabel logo = new JLabel(icon);
    public static final String description = "<html><p style=\"text-align: center;\">MARS application</p>\n"
            + "\n"
            + "<hr />\n"
            + "<p>Multi-Platform application</p>\n"
            + "\n"
            + "<p>Version 1.0</p>\n"
            + "\n"
            + "<p>Software engineering:&nbsp; KACPRZAK Emilia, RYBAK Katarzyna, LE BRIS Aurore, KOSECKI Jan, SANANIKONE Maud</p>\n"
            + "\n"
            + "<p>MARS (meaning Monitoring Application and Recovery System) is a multi-platform application allowing the user to find the best value of the parameters for designing airfoil of airplane wings.</p>\n"
            + "\n"
            + "<p>The user should input:</p>\n"
            + "\n"
            + "<ol>\n"
            + "	<li>Span (m)</li>\n"
            + "	<li>Chord (m)</li>\n"
            + "	<li>Leading edge (degree)</li>\n"
            + "	<li>Aeroplane mass ( kg )</li>\n"
            + "	<li>Minimal Drag Coefficient ( depending on the type of the airplane )</li>\n"
            + "	<li>Max lift coefficient ( depending on the type of the airplane)</li>\n"
            + "	<li>Air speed ( m/s )</li>\n"
            + "	<li>Minimal air speed ( m/s ) - minimal air speed that allows given plane to fly</li>\n"
            + "</ol>\n"
            + "\n"
            + "<p>There MARS should display:</p>\n"
            + "\n"
            + "<ol>\n"
            + "	<li>The ratio in a graph while the system computes it</li>\n"
            + "	<li>The final best ratio</li>\n"
            + "</ol>\n"
            + "\n"
            + "<p>&nbsp;</p>\n"
            + "\n"
            + "<p>&nbsp;</p>\n"
            + "\n"
            + "<p>MARS contains 3 different modules:</p>\n"
            + "\n"
            + "<ol>\n"
            + "	<li>The interface v1.0 (Java)</li>\n"
            + "	<li>The server v1.0 (Java)</li>\n"
            + "	<li>The database v1.0 (Google Cloud SQL)</li>\n"
            + "</ol>\n"
            + "\n"
            + "<p>&nbsp;</p>\n"
            + "\n"
            + "<p>MARS should provide a recovery system to the user in order to start again the computation from the last point in case of failure.</p></html>";
    protected JLabel labeDescription = new JLabel(description);
    protected JPanel panelComponent = new JPanel();

    DesciptionFrame() {
        panelComponent.setLayout(new BoxLayout(panelComponent, BoxLayout.Y_AXIS));
        panelComponent.add(labeDescription);
        panelComponent.add(logo);
    }

}