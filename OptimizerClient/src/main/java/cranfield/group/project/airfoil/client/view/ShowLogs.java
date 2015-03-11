package cranfield.group.project.airfoil.client.view;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import cranfield.group.project.airfoil.api.model.ResultsDTO;
import cranfield.group.project.airfoil.api.model.WorkflowDTO;

/**
 *
 * @author Kat
 */
public class ShowLogs extends JPanel implements Observer {

    protected JTextArea textAreaLogs;
    protected JLabel labelLogs = new JLabel("<html>\n"
            + "<head>\n"
            + "	<title></title>\n"
            + "</head>\n"
            + "<body>\n"
            + "<p><span style=\"font-family:verdana,geneva,sans-serif;\"><span style=\"font-size: 20px;\">Logs</span></span></p>\n"
            + "</body>\n"
            + "</html>");
    protected JPanel panelComponent = new JPanel();

    protected static final String DATE_FORMAT_NOW = "dd/MM/yyyy HH:mm:ss";

    ShowLogs() {

        textAreaLogs = new JTextArea(12, 40);
        textAreaLogs.setEditable(false);
        JScrollPane scrollPaneLogs = new JScrollPane(textAreaLogs);
        scrollPaneLogs.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPaneLogs.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panelComponent.setLayout(new BoxLayout(panelComponent, BoxLayout.Y_AXIS));
        Border padding = BorderFactory.createEmptyBorder(10, 200, 10, 200);
        labelLogs.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        panelComponent.setBorder(padding);
        panelComponent.add(labelLogs);
        panelComponent.add(scrollPaneLogs);
    }

    @Override
    public void update(Observable o, Object arg) {
        Calendar calendar = Calendar.getInstance();

        if (arg.getClass() == String.class) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_NOW);
            String currentTimestamp = dateFormat.format(calendar.getTime());
            textAreaLogs.append(currentTimestamp + ": " + (String) arg + "\n");
            textAreaLogs.setCaretPosition(textAreaLogs.getDocument()
                    .getLength());
        } else if (arg.getClass() == Hashtable.class) {
            Hashtable<String, Double> receivedData = (Hashtable<String, Double>) arg;
            textAreaLogs.append(receivedData.toString() + "\n");
            textAreaLogs.setCaretPosition(textAreaLogs.getDocument()
                    .getLength());
			// } else if (arg.getClass() == Vector.class) {
			// DecimalFormat formatter = new DecimalFormat("###,###.######");
			//
			// Vector<IterationValuesSet> resultValues =
			// (Vector<IterationValuesSet>) arg;
			//
			// for (IterationValuesSet result : resultValues) {
			// String resultString = "Iteration " + result.getIteration()
			// + " => Drag: "
			// + formatter.format(result.getDragForce()) + " Lift: "
			// + formatter.format(result.getLiftForce()) + "\n";
			// textAreaLogs.append(resultString);
			// }
			// textAreaLogs.setCaretPosition(textAreaLogs.getDocument()
			// .getLength());
		} else if (arg.getClass() == WorkflowDTO.class) {
			DecimalFormat formatter = new DecimalFormat("###,###.######");

			// Vector<IterationValuesSet> resultValues =
			// (Vector<IterationValuesSet>) arg;
			WorkflowDTO dto = (WorkflowDTO) arg;
			List<ResultsDTO> results = dto.getResults();

			for (ResultsDTO result : results) {
				String resultString = "Iteration " + result.getIteration()
						+ " => Drag: "
						+ formatter.format(result.getDragForce()) + " Lift: "
						+ formatter.format(result.getLiftForce()) + "\n";
				textAreaLogs.append(resultString);
			}
			textAreaLogs.setCaretPosition(textAreaLogs.getDocument()
					.getLength());
        }
    }
}
