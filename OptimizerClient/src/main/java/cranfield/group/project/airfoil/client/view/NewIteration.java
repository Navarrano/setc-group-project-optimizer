package cranfield.group.project.airfoil.client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Hashtable;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Kat
 */
public class NewIteration extends JPanel {

    ImageIcon icon = new ImageIcon("src/img/logo.png");
    JLabel logo = new JLabel(icon);
    JPanel panelInput = new JPanel();
    JPanel panelInitVar = new JPanel();
    JPanel panelButton = new JPanel();
    JPanel panelComboBox = new JPanel();
    JComboBox comboDragCoeff = new JComboBox();
    String[] labelsInput = {"Aeroplane mass: ", "Minimal drag coefficient: ", "Maximum drag coeficient: ",
        "Air speed: ", "Minimal air speed: "};
    SpinnerModel spinnerModelMass;
    SpinnerModel spinnerModelLift;
    SpinnerModel spinnerModelSpeed;
    SpinnerModel spinnerModelMinSpeed;
    SpinnerModel spinnerModelSpan;
    SpinnerModel spinnerModelChord;
    SpinnerModel spinnerModelEdge;
    SpinnerModel spinnerModelIterNumber;
    String[] labelsInitVar = {"Span: ", "Chord: ", "Leading edge: ",};

    NewIteration() {

        spinnerModelMass = new SpinnerNumberModel(500, //initial value
                0, //min
                5000, //max
                200);//step
        JSpinner spinnerMass = addLabeledSpinner(panelInput, labelsInput[0], spinnerModelMass);

        spinnerModelLift = new SpinnerNumberModel(500, //initial value
                0, //min
                5000, //max
                200);//step
        JSpinner spinnerLift = addLabeledSpinner(panelInput, labelsInput[2], spinnerModelLift);

        spinnerModelSpeed = new SpinnerNumberModel(500, //initial value
                0, //min
                5000, //max
                200);//step
        JSpinner spinnerSpeed = addLabeledSpinner(panelInput, labelsInput[3], spinnerModelSpeed);

        spinnerModelMinSpeed = new SpinnerNumberModel(500, //initial value
                0, //min
                5000, //max
                200);//step
        JSpinner spinnerMinSpeed = addLabeledSpinner(panelInput, labelsInput[4], spinnerModelMinSpeed);

        JLabel labelComboBox = new JLabel(labelsInput[1]);
        comboDragCoeff.setRenderer(new render());
        comboDragCoeff.addItem(new String[]{"0.021", "F-4 Phantom II"});
        comboDragCoeff.addItem(new String[]{"0.022", "Learjet 24"});
        comboDragCoeff.addItem(new String[]{"0.024", "Boeing 787"});
        comboDragCoeff.addItem(new String[]{"0.0265", "Airbus A380"});
        comboDragCoeff.addItem(new String[]{"0.027", "Cessna 172/182"});
        comboDragCoeff.addItem(new String[]{"0.027", "Cessna 310"});
        comboDragCoeff.addItem(new String[]{"0.031", "Boeing 747"});
        comboDragCoeff.addItem(new String[]{"0.044", "F-4 Phantom II "});
        comboDragCoeff.addItem(new String[]{"0.048", "F-104 Starfighter"});

        panelComboBox.setLayout(new BoxLayout(panelComboBox, BoxLayout.Y_AXIS));
        panelComboBox.add(labelComboBox);
        panelComboBox.add(comboDragCoeff);

       
        spinnerModelSpan = new SpinnerNumberModel(500, //initial value
                0, //min
                5000, //max
                200);//step
        JSpinner spinnerSpan = addLabeledSpinner(panelInitVar, labelsInitVar[0], spinnerModelSpan);
        spinnerModelChord = new SpinnerNumberModel(500, //initial value
                0, //min
                5000, //max
                200);//step
        JSpinner spinnerChord = addLabeledSpinner(panelInitVar, labelsInitVar[1], spinnerModelChord);
        spinnerModelEdge = new SpinnerNumberModel(500, //initial value
                0, //min
                5000, //max
                200);//step
        JSpinner spinnerEdge = addLabeledSpinner(panelInitVar, labelsInitVar[2], spinnerModelEdge);
        spinnerModelIterNumber = new SpinnerNumberModel(500, //initial value
                0, //min
                5000, //max
                1);//step
        JSpinner spinnerIterNumber = addLabeledSpinner(panelInitVar, "Iteration Number", spinnerModelIterNumber);

        JButton startButton = new JButton("start");
        JButton stopButton = new JButton("stop");
        JButton resumeButton = new JButton("resume");

        panelInput.add(panelComboBox);
        Border loweredbevel;
        loweredbevel = BorderFactory.createLoweredBevelBorder();
        panelInput.setBorder(loweredbevel);
        TitledBorder titleInput;
        titleInput = BorderFactory.createTitledBorder(
                loweredbevel, "Input parameters");
        titleInput.setTitlePosition(TitledBorder.CENTER);
        panelInput.setBorder(titleInput);
        panelInput.setLayout(new BoxLayout(panelInput, BoxLayout.Y_AXIS));

        Border loweredbevelInitvar;
        loweredbevelInitvar = BorderFactory.createLoweredBevelBorder();
        panelInitVar.setBorder(loweredbevel);
        TitledBorder titleInitVar;
        titleInitVar = BorderFactory.createTitledBorder(
                loweredbevelInitvar, "Initial variables: ");
        titleInitVar.setTitlePosition(TitledBorder.CENTER);
        panelInitVar.setBorder(titleInitVar);
        panelInitVar.setLayout(new BoxLayout(panelInitVar, BoxLayout.Y_AXIS));

        panelButton.add(startButton);
        panelButton.add(Box.createRigidArea(new Dimension(10, 10)));
        panelButton.add(stopButton);
        panelButton.add(Box.createRigidArea(new Dimension(10, 10)));
        panelButton.add(resumeButton);
        Border loweredbevelButton;
        loweredbevelButton = BorderFactory.createLoweredBevelBorder();
        panelButton.setBorder(loweredbevel);
        TitledBorder titleButton;
        titleButton = BorderFactory.createTitledBorder(
                loweredbevelButton, "Button area");
        titleButton.setTitlePosition(TitledBorder.CENTER);
        panelButton.setBorder(titleButton);
        panelButton.setLayout(new BoxLayout(panelButton, BoxLayout.Y_AXIS));

        startButton.addActionListener(new GetValueListener());

        add(logo);
        add(panelInput);
        add(panelInitVar);
        add(panelButton);
    }

    static protected JSpinner addLabeledSpinner(Container c, String label, SpinnerModel model) {
        JLabel l = new JLabel(label);
        c.add(l);

        JSpinner spinner = new JSpinner(model);
        int w = 10;
        int h = 20;
        Dimension d = new Dimension(w, h);
        spinner.setPreferredSize(d);
        spinner.setMinimumSize(d);
        l.setLabelFor(spinner);
        c.add(spinner);

        return spinner;
    }

    class GetValueListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            Hashtable<String, Double> inputs = new Hashtable<String, Double>();
            String a = (((String[])comboDragCoeff.getSelectedItem())[0]).toString();
            Double minDragCoeff = Double.parseDouble(a);
           
            inputs.put(labelsInput[0], Double.parseDouble(spinnerModelMass.getValue().toString()));
            inputs.put(labelsInput[2], Double.parseDouble(spinnerModelLift.getValue().toString()));
            inputs.put(labelsInput[3], Double.parseDouble(spinnerModelSpeed.getValue().toString()));
            inputs.put(labelsInput[4], Double.parseDouble(spinnerModelMinSpeed.getValue().toString()));
            inputs.put(labelsInput[1],  minDragCoeff);
            inputs.put(labelsInitVar[0], Double.parseDouble(spinnerModelLift.getValue().toString()));
            inputs.put(labelsInitVar[1], Double.parseDouble(spinnerModelLift.getValue().toString()));
            inputs.put(labelsInitVar[2], Double.parseDouble( spinnerModelLift.getValue().toString()));
            inputs.put("Iteration Number", Double.parseDouble(spinnerModelIterNumber.getValue().toString()));

            System.out.println(inputs);
        }
    }

}

class render extends JPanel implements ListCellRenderer {

    private JLabel label1 = new JLabel();
    private JLabel label2 = new JLabel();
    private JLabel label3 = new JLabel();
    private JLabel label4 = new JLabel();
    private JLabel label5 = new JLabel();
    private JLabel label6 = new JLabel();
    private JLabel label7 = new JLabel();
    private JLabel label8 = new JLabel();
    private JLabel label9 = new JLabel();
    private JLabel label10 = new JLabel();

    public render() {
        setLayout(new GridLayout(1, 10));
        add(label1);
        add(label2);
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        String[] values = (String[]) value;
        label1.setText(values[0]);
        label2.setText(values[1]);

        return this;
    }

}
