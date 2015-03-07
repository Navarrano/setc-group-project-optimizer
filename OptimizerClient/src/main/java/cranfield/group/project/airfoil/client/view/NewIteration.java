package cranfield.group.project.airfoil.client.view;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import cranfield.group.project.airfoil.api.model.IterationValuesSet;
import cranfield.group.project.airfoil.client.MarsClient;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

/**
 *
 * @author Kat
 */
public class NewIteration extends JPanel implements ActionListener {

    protected ImageIcon icon = new ImageIcon("img/logo.png");
    protected JLabel logo = new JLabel(icon);
    protected JPanel panelComponent = new JPanel();
    protected JPanel panelInput = new JPanel();
    protected JPanel panelPicture = new JPanel();
    protected JPanel panelInitVar = new JPanel();
    protected JPanel panelButton = new JPanel();
    protected JPanel panelComboBox = new JPanel();

    protected GraphPanel panelGraph = new GraphPanel();
    protected JComboBox comboDragCoeff = new JComboBox();
    protected String[] labelsInput = {"Aeroplane mass: ", "Minimal drag coefficient: ", "Maximum lift coeficient: ",
        "Air speed: ", "Minimal air speed: "};
    protected SpinnerModel spinnerModelMass;
    protected SpinnerModel spinnerModelLift;
    protected SpinnerModel spinnerModelSpeed;
    protected SpinnerModel spinnerModelMinSpeed;
    protected SpinnerModel spinnerModelSpan;
    protected SpinnerModel spinnerModelChord;
    protected SpinnerModel spinnerModelEdge;
    protected SpinnerModel spinnerModelIterNumber;
    protected String[] labelsInitVar = {"Span: ", "Chord: ", "Leading edge: "};
    protected JLabel picture;

    protected MarsClient client;

    public NewIteration(MarsClient client) {
        super();
        this.client = client;
        spinnerModelMass = new SpinnerNumberModel(3523, //initial value
                0, //min
                5000, //max
                200);//step

        JSpinner spinnerMass = addLabeledSpinner(panelInput, labelsInput[0], spinnerModelMass);

        spinnerModelLift = new SpinnerNumberModel(1.78, //initial value
                0, //min
                2, //max
                0.01);//step
        JSpinner spinnerLift = addLabeledSpinner(panelInput, labelsInput[2], spinnerModelLift);

        spinnerModelSpeed = new SpinnerNumberModel(120.11, //initial value
                0, //min
                600, //max
                50);//step
        JSpinner spinnerSpeed = addLabeledSpinner(panelInput, labelsInput[3], spinnerModelSpeed);

        spinnerModelMinSpeed = new SpinnerNumberModel(46.18, //initial value
                0, //min
                600, //max
                50);//step
        JSpinner spinnerMinSpeed = addLabeledSpinner(panelInput, labelsInput[4], spinnerModelMinSpeed);

        JLabel labelComboBox = new JLabel(labelsInput[1]);
        comboDragCoeff.setRenderer(new render());
        comboDragCoeff.addItem(new String[]{"0.021", "F-4 Phantom II"});
        comboDragCoeff.addItem(new String[]{"0.022", "Learjet 24"});
        comboDragCoeff.addItem(new String[]{"0.024", "Boeing 787"});
        comboDragCoeff.addItem(new String[]{"0.0265", "Airbus A380"});
        comboDragCoeff.addItem(new String[]{"0.027", "Cessna 172-182"});
        comboDragCoeff.addItem(new String[]{"0.027", "Cessna 310"});
        comboDragCoeff.addItem(new String[]{"0.031", "Boeing 747"});
        comboDragCoeff.addItem(new String[]{"0.044", "F-4 Phantom II "});
        comboDragCoeff.addItem(new String[]{"0.048", "F-104 Starfighter"});
        comboDragCoeff.addActionListener(this);

        picture = new JLabel();
        picture.setFont(picture.getFont().deriveFont(Font.ITALIC));
        picture.setHorizontalAlignment(JLabel.CENTER);
        updateComboLabel();
        picture.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        picture.setPreferredSize(new Dimension(300, 100));
         // picture.setSize((panelPicture.getWidth()/2), panelPicture.getHeight());

        // panelComboBox.setLayout(new BoxLayout(panelComboBox, BoxLayout.Y_AXIS));
        panelComboBox.add(labelComboBox);
        panelComboBox.add(comboDragCoeff);

        spinnerModelSpan = new SpinnerNumberModel(20, //initial value
                0, //min
                160, //max
                10);//step
        JSpinner spinnerSpan = addLabeledSpinner(panelInitVar, labelsInitVar[0], spinnerModelSpan);
        spinnerModelChord = new SpinnerNumberModel(20, //initial value
                0, //min
                35, //max
                1);//step
        JSpinner spinnerChord = addLabeledSpinner(panelInitVar, labelsInitVar[1], spinnerModelChord);
        spinnerModelEdge = new SpinnerNumberModel(0, //initial value
                0, //min
                360, //max
                1);//step
        JSpinner spinnerEdge = addLabeledSpinner(panelInitVar, labelsInitVar[2], spinnerModelEdge);
        spinnerModelIterNumber = new SpinnerNumberModel(200, //initial value
                0, //min
                500, //max
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
        panelPicture.setLayout(new BoxLayout(panelPicture, BoxLayout.X_AXIS));
        panelPicture.add(picture);
        panelPicture.add(panelInput);

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
        panelGraph = new GraphPanel();
        panelGraph.setLayout(new BoxLayout(panelGraph, BoxLayout.X_AXIS));
        panelGraph.setPreferredSize(new Dimension(200, 200));

        panelComponent.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.VERTICAL;
        c.gridx = 0;
        c.gridy = 0;
        panelComponent.add(logo, c);
        c.fill = GridBagConstraints.VERTICAL;
        c.gridx = 0;
        c.gridy = 1;
        panelComponent.add(panelPicture, c);
        c.fill = GridBagConstraints.VERTICAL;
        c.gridx = 0;
        c.gridy = 2;
        panelComponent.add(panelInitVar, c);
        c.fill = GridBagConstraints.VERTICAL;
        c.gridx = 0;
        c.gridy = 3;
        panelComponent.add(panelButton, c);
        c.fill = GridBagConstraints.VERTICAL;
        c.gridx = 0;
        c.gridy = 2;
        c.anchor = GridBagConstraints.PAGE_END;
        panelComponent.add(panelGraph, c);
    }

    protected static JSpinner addLabeledSpinner(Container c, String label, SpinnerModel model) {
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
    
    public void actionPerformed(ActionEvent e) {
     JComboBox cb = (JComboBox) e.getSource();
     String planeName = (((String[]) comboDragCoeff.getSelectedItem())[1]).toString();
     updateComboLabel();
     }
    
    protected void updateComboLabel() {

     String planeName = (((String[]) comboDragCoeff.getSelectedItem())[1]).toString();
     ImageIcon icon = new ImageIcon("img/" + planeName + ".png");
     picture.setIcon(icon);
     picture.setToolTipText("A photo of a " + planeName.toLowerCase());
     if (icon != null) {
     picture.setText(null);
     } else {
     picture.setText("Image not found");
     }
     }

    class GetValueListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            Hashtable<String, Double> inputs = new Hashtable<String, Double>();
            String a = (((String[]) comboDragCoeff.getSelectedItem())[0]).toString();
            Double minDragCoeff = Double.parseDouble(a);

            inputs.put(labelsInput[0], Double.parseDouble(spinnerModelMass.getValue().toString()));
            inputs.put(labelsInput[2], Double.parseDouble(spinnerModelLift.getValue().toString()));
            inputs.put(labelsInput[3], Double.parseDouble(spinnerModelSpeed.getValue().toString()));
            inputs.put(labelsInput[4], Double.parseDouble(spinnerModelMinSpeed.getValue().toString()));
            inputs.put(labelsInput[1], minDragCoeff);
            inputs.put(labelsInitVar[0], Double.parseDouble(spinnerModelSpan.getValue().toString()));
            inputs.put(labelsInitVar[1], Double.parseDouble(spinnerModelChord.getValue().toString()));
            inputs.put(labelsInitVar[2], Double.parseDouble(spinnerModelEdge.getValue().toString()));
            inputs.put("Iteration Number", Double.parseDouble(spinnerModelIterNumber.getValue().toString()));

            client.sendOptimizationInputs(inputs);
            Vector<IterationValuesSet> optimizationResults = client.receiveOptimizationOutputs();
            panelGraph.displayOptimizationRatio(optimizationResults);
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
