package cranfield.group.project.airfoil.client.view;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import javax.swing.*;

import cranfield.group.project.airfoil.api.model.IterationValuesSet;

/**
 *
 * @author Kat
 */
public class ShowLogs extends JPanel implements Observer{
    
    protected ImageIcon icon = new ImageIcon("src/img/logo.png");
    protected JLabel logo = new JLabel(icon);
    protected JTextArea textAreaLogs;
    protected JLabel labelLogs = new JLabel("Logs: ");
    protected JPanel panelComponent = new JPanel();

    protected static final String DATE_FORMAT_NOW = "dd/MM/yyyy HH:mm:ss";

    ShowLogs() {

       textAreaLogs = new JTextArea(12, 40);
        textAreaLogs.setEditable(false);
        JScrollPane scrollPaneLogs = new JScrollPane(textAreaLogs);
        scrollPaneLogs.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPaneLogs.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panelComponent.setLayout(new BoxLayout(panelComponent, BoxLayout.Y_AXIS));
        panelComponent.add(labelLogs);
        panelComponent.add(scrollPaneLogs);
    }

	@Override
	public void update(Observable o, Object arg) {
    	Calendar calendar = Calendar.getInstance();

		if(arg.getClass() == String.class){
			SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_NOW);
			String currentTimestamp = dateFormat.format(calendar.getTime());
			textAreaLogs.append(currentTimestamp + ": "+(String) arg+"\n");
			textAreaLogs.setCaretPosition(textAreaLogs.getDocument().getLength());
		}
		
		else if(arg.getClass() == Hashtable.class){
			Hashtable<String, Double> receivedData = (Hashtable<String, Double>) arg;
			textAreaLogs.append(receivedData.toString()+"\n");
			textAreaLogs.setCaretPosition(textAreaLogs.getDocument().getLength());		
		}
		
		else if(arg.getClass() == Vector.class){
			DecimalFormat formatter = new DecimalFormat("###,###.######");

			Vector<IterationValuesSet> resultValues = (Vector<IterationValuesSet>) arg;
			
			for(IterationValuesSet result : resultValues){
				String resultString = "Iteration "+result.getIteration()+
						" => Drag: "+ formatter.format(result.getDragForce())+
						" Lift: "+formatter.format(result.getLiftForce())+"\n";
				textAreaLogs.append(resultString);
			}
			textAreaLogs.setCaretPosition(textAreaLogs.getDocument().getLength());			
		}
	}
}