package cranfield.group.project.airfoil.client.view;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.ListCellRenderer;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import cranfield.group.project.airfoil.api.model.OperationType;
import cranfield.group.project.airfoil.api.model.ResultsDTO;
import cranfield.group.project.airfoil.api.model.WorkflowDTO;
import cranfield.group.project.airfoil.client.MarsClient;
import cranfield.group.project.airfoil.client.model.ServerOfflineException;

/**
 *
 * @author Kat
 */
public class NewIteration extends JPanel implements ActionListener {

	private final Container parent;
	protected JButton createButton;
	protected JButton iterateButton;
	protected JButton startButton;
	protected JPanel panelComponent = new JPanel();
	protected JPanel panelInput = new JPanel();
	protected JPanel panelPicture = new JPanel();
	protected JPanel panelInitVar = new JPanel();
	protected JPanel panelButton = new JPanel();
	protected JPanel panelComboBox = new JPanel();
	protected JPanel panelList = new JPanel();

	protected GraphPanel panelGraph = new GraphPanel();
	protected JComboBox comboDragCoeff = new JComboBox();
	protected String[] labelsInput = { "Aeroplane mass: ",
			"Minimal drag coefficient: ", "Maximum lift coeficient: ",
			"Air speed: ", "Minimal air speed: " };
	protected SpinnerModel spinnerModelMass;
	protected SpinnerModel spinnerModelLift;
	protected SpinnerModel spinnerModelSpeed;
	protected SpinnerModel spinnerModelMinSpeed;
	protected SpinnerModel spinnerModelSpan;
	protected SpinnerModel spinnerModelChord;
	protected SpinnerModel spinnerModelEdge;
	protected SpinnerModel spinnerModelIterNumber;
	protected JSpinner spinnerIterNumber;
	protected String[] labelsInitVar = { "Span: ", "Chord: " };
	protected JLabel picture;
	protected int counter = 0;
	protected int index = 0;
	protected JList optimizationsList;
	protected DefaultListModel<WorkflowDTO> optimizationsListModel;
	protected String workflowName;
	protected WorkflowDTO currentWorkflow;
	protected ListSelectionListener listSelectionListener;

	protected MarsClient client;

	public NewIteration(Container parent, MarsClient client) {
		super();
		this.parent = parent;
		this.client = client;
		spinnerModelMass = new SpinnerNumberModel(3523, // initial value
				0, // min
				5000, // max
				200);// step

		JSpinner spinnerMass = addLabeledSpinner(panelInput, labelsInput[0],
				spinnerModelMass);

		spinnerModelLift = new SpinnerNumberModel(1.78, // initial value
				0, // min
				2, // max
				0.01);// step
		JSpinner spinnerLift = addLabeledSpinner(panelInput, labelsInput[2],
				spinnerModelLift);

		spinnerModelSpeed = new SpinnerNumberModel(120.11, // initial value
				0, // min
				600, // max
				50);// step
		JSpinner spinnerSpeed = addLabeledSpinner(panelInput, labelsInput[3],
				spinnerModelSpeed);

		spinnerModelMinSpeed = new SpinnerNumberModel(46.18, // initial value
				0, // min
				600, // max
				50);// step
		JSpinner spinnerMinSpeed = addLabeledSpinner(panelInput,
				labelsInput[4], spinnerModelMinSpeed);

		JLabel labelComboBox = new JLabel(labelsInput[1]);
		comboDragCoeff.setRenderer(new render());
		comboDragCoeff.addItem(new String[] { "0.021", "F-4 Phantom II" });
		comboDragCoeff.addItem(new String[] { "0.022", "Learjet 24" });
		comboDragCoeff.addItem(new String[] { "0.024", "Boeing 787" });
		comboDragCoeff.addItem(new String[] { "0.0265", "Airbus A380" });
		comboDragCoeff.addItem(new String[] { "0.027", "Cessna 172-182" });
		comboDragCoeff.addItem(new String[] { "0.028", "Cessna 310" });
		comboDragCoeff.addItem(new String[] { "0.031", "Boeing 747" });
		comboDragCoeff.addItem(new String[] { "0.048", "F-104 Starfighter" });
		comboDragCoeff.addActionListener(this);

		picture = new JLabel();
		picture.setFont(picture.getFont().deriveFont(Font.ITALIC));
		picture.setHorizontalAlignment(JLabel.CENTER);
		picture.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
		panelComboBox.setLayout(new BoxLayout(panelComboBox, BoxLayout.Y_AXIS));
		panelComboBox.add(labelComboBox);
		panelComboBox.add(comboDragCoeff);

		panelInitVar.add(Box.createRigidArea(new Dimension(10, 10)));
		spinnerModelSpan = new SpinnerNumberModel(20, // initial value
				0, // min
				160, // max
				10);// step
		JSpinner spinnerSpan = addLabeledSpinner(panelInitVar,
				labelsInitVar[0], spinnerModelSpan);
		panelInitVar.add(Box.createRigidArea(new Dimension(10, 20)));
		spinnerModelChord = new SpinnerNumberModel(20, // initial value
				0, // min
				35, // max
				1);// step
		JSpinner spinnerChord = addLabeledSpinner(panelInitVar,
				labelsInitVar[1], spinnerModelChord);
		panelInitVar.add(Box.createRigidArea(new Dimension(10, 20)));
		spinnerModelIterNumber = new SpinnerNumberModel(200, // initial value
				0, // min
				500, // max
				1);// step
		spinnerIterNumber = addLabeledSpinner(panelInitVar, "Iteration Number",
				spinnerModelIterNumber);
		panelInitVar.add(Box.createRigidArea(new Dimension(10, 10)));
		startButton = new JButton("start optimization");
		iterateButton = new JButton("iterate");
		createButton = new JButton("create new optimization");

		optimizationsListModel = new DefaultListModel();
		initWorkflows();
		updateComboLabel();
		JScrollPane paneList = new JScrollPane(optimizationsList);

		panelInput.add(panelComboBox);
		Border loweredbevel;
		loweredbevel = BorderFactory.createLoweredBevelBorder();
		TitledBorder titleInput;
		titleInput = BorderFactory.createTitledBorder(loweredbevel,
				"Input parameters");
		titleInput.setTitlePosition(TitledBorder.CENTER);
		panelInput.setLayout(new BoxLayout(panelInput, BoxLayout.Y_AXIS));
		panelPicture.setLayout(new BoxLayout(panelPicture, BoxLayout.X_AXIS));
		panelPicture.add(picture);
		panelPicture.add(panelInput);
		panelPicture.setBorder(loweredbevel);
		panelPicture.setBorder(titleInput);

		Border loweredbevelInitvar;
		loweredbevelInitvar = BorderFactory.createLoweredBevelBorder();
		panelInitVar.setBorder(loweredbevel);
		TitledBorder titleInitVar;
		titleInitVar = BorderFactory.createTitledBorder(loweredbevelInitvar,
				"Initial variables: ");
		titleInitVar.setTitlePosition(TitledBorder.CENTER);
		panelInitVar.setBorder(titleInitVar);
		panelInitVar.setLayout(new BoxLayout(panelInitVar, BoxLayout.Y_AXIS));

		panelButton.setLayout(new BoxLayout(panelButton, BoxLayout.X_AXIS));
		panelButton.setBorder(new EmptyBorder(50, 50, 50, 50));
		panelButton.add(startButton);
		panelButton.add(Box.createRigidArea(new Dimension(10, 10)));
		panelButton.add(iterateButton);
		panelButton.add(Box.createRigidArea(new Dimension(10, 10)));
		panelButton.add(createButton);
		Border loweredbevelButton;
		loweredbevelButton = BorderFactory.createLoweredBevelBorder();
		panelButton.setBorder(loweredbevel);
		TitledBorder titleButton;
		titleButton = BorderFactory.createTitledBorder(loweredbevelButton,
				"Buttons");
		titleButton.setTitlePosition(TitledBorder.CENTER);
		panelButton.setBorder(titleButton);

		Border loweredbevelList;
		loweredbevelList = BorderFactory.createLoweredBevelBorder();
		panelList.setLayout(new BoxLayout(panelList, BoxLayout.X_AXIS));
		panelList.add(paneList);
		panelList.setBorder(loweredbevelList);
		panelList.setPreferredSize(new Dimension(210, 100));
		TitledBorder titleList;
		titleList = BorderFactory.createTitledBorder(loweredbevelList,
				"Last values");
		titleList.setTitlePosition(TitledBorder.CENTER);
		panelList.setBorder(titleList);

		panelGraph.setLayout(new BoxLayout(panelGraph, BoxLayout.X_AXIS));
		panelGraph.setPreferredSize(new Dimension(100, 400));

		JPanel panelBoxLayout1 = new JPanel();
		panelBoxLayout1.setLayout(new BoxLayout(panelBoxLayout1,
				BoxLayout.LINE_AXIS));
		panelBoxLayout1.add(panelPicture);
		panelBoxLayout1.add(panelInitVar);
		JPanel panelBoxLayout2 = new JPanel();
		panelBoxLayout2.setLayout(new BoxLayout(panelBoxLayout2,
				BoxLayout.PAGE_AXIS));
		panelBoxLayout2.add(panelGraph);
		panelBoxLayout2.add(panelButton);
		JPanel panelBoxLayout3 = new JPanel();
		panelBoxLayout3.setLayout(new BoxLayout(panelBoxLayout3,
				BoxLayout.PAGE_AXIS));
		panelBoxLayout3.add(panelBoxLayout1);
		panelBoxLayout3.add(panelBoxLayout2);
		JPanel panelBoxLayout4 = new JPanel();
		panelBoxLayout4.setLayout(new BoxLayout(panelBoxLayout4,
				BoxLayout.LINE_AXIS));
		panelBoxLayout4.add(panelList);
		panelBoxLayout4.add(panelBoxLayout3);
		panelComponent.add(panelBoxLayout4);

		startButton.addActionListener(new GetValueListener());
		createButton.addActionListener(new CreateNewOptimListener());
		iterateButton.addActionListener(new AddNewIterListener());
		createButton.setEnabled(false);
		iterateButton.setEnabled(false);
		listSelectionListener = new SharedListSelectionHandler();
		optimizationsList.addListSelectionListener(listSelectionListener);

		client.addObserver(panelGraph);
	}

	protected static JSpinner addLabeledSpinner(Container c, String label,
			SpinnerModel model) {
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

	public void enableComponents(Container container, boolean enable) {
		Component[] components = container.getComponents();
		for (Component component : components) {
			if (component != spinnerIterNumber) {
				component.setEnabled(enable);
				if (component instanceof Container) {
					enableComponents((Container) component, enable);
				}
			}

		}
	}

	public void setInputValues(WorkflowDTO workflowData) {
		spinnerModelMass.setValue(workflowData.getAeroplaneMass());
		spinnerModelLift.setValue(workflowData.getMaxLiftCoef());
		spinnerModelSpeed.setValue(workflowData.getAirSpeed());
		spinnerModelMinSpeed.setValue(workflowData.getMinAirSpeed());
		spinnerModelSpan.setValue(workflowData.getSpan());
		spinnerModelChord.setValue(workflowData.getChord());
		spinnerModelIterNumber.setValue(workflowData.getNbIterations());
		spinnerIterNumber.setValue(workflowData.getNbIterations());

		double a = workflowData.getMinDragCoef();

		for (int i = 0; i < comboDragCoeff.getItemCount(); i++) {
			if (Double.toString(a).equalsIgnoreCase(
					((String[]) comboDragCoeff.getItemAt(i))[0])) {
				comboDragCoeff.setSelectedIndex(i);
				break;
			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		JComboBox cb = (JComboBox) e.getSource();
		updateComboLabel();
	}

	protected void updateComboLabel() {

		String planeName = (((String[]) comboDragCoeff.getSelectedItem())[1]);
		ImageIcon icon = null;
		try {
			Image img = ImageIO.read(new File("img/" + planeName + ".png"));
			Image resizedImage = img.getScaledInstance(250, 170,
					Image.SCALE_SMOOTH);
			icon = new ImageIcon(resizedImage);
		} catch (IOException ex) {
		}

		index = comboDragCoeff.getSelectedIndex();

		picture.setIcon(icon);
		picture.setToolTipText("A photo of a " + planeName.toLowerCase());
		if (icon != null) {
			picture.setText(null);
		} else {
			picture.setText("Image not found");
		}
	}

	protected void initWorkflows() {
		ObjectInputStream in;
		try {
			in = new ObjectInputStream(client.getClientSocket()
					.getInputStream());
			List<WorkflowDTO> workflows = (List<WorkflowDTO>) in.readObject();

			for (WorkflowDTO w : workflows) {
				optimizationsListModel.addElement(w);
			}

			optimizationsList = new JList<WorkflowDTO>(optimizationsListModel);

		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	class GetValueListener implements ActionListener {

		public void actionPerformed(ActionEvent event) {
			Hashtable<String, Double> inputs = new Hashtable<String, Double>();
			String a = (((String[]) comboDragCoeff.getSelectedItem())[0]);
			Double minDragCoeff = Double.parseDouble(a);

			inputs.put(labelsInput[0],
					Double.parseDouble(spinnerModelMass.getValue().toString()));
			inputs.put(labelsInput[2],
					Double.parseDouble(spinnerModelLift.getValue().toString()));
			inputs.put(labelsInput[3],
					Double.parseDouble(spinnerModelSpeed.getValue().toString()));
			inputs.put(labelsInput[4], Double.parseDouble(spinnerModelMinSpeed
					.getValue().toString()));
			inputs.put(labelsInput[1], minDragCoeff);
			inputs.put(labelsInitVar[0],
					Double.parseDouble(spinnerModelSpan.getValue().toString()));
			inputs.put(labelsInitVar[1],
					Double.parseDouble(spinnerModelChord.getValue().toString()));
			inputs.put("Iteration Number", Double
					.parseDouble(spinnerModelIterNumber.getValue().toString()));

			workflowName = JOptionPane.showInputDialog(null,
					"Please enter the name of the workflow");
			if (workflowName == null || workflowName.trim().isEmpty()) {
				String currentTimestamp = new SimpleDateFormat(
						"dd-MM-yyyy hh:mm:ss").format(new Date());
				workflowName = "Optimization " + currentTimestamp;
			}

			try {
				client.sendOptimizationInputs(workflowName, inputs);
			} catch (ServerOfflineException e) {
				((MainFrame) parent).closeAfterDisconnection();
				return;
			}

			enableComponents(panelInitVar, false);
			enableComponents(panelInput, false);
			startButton.setEnabled(false);

			counter++;

			currentWorkflow = null;
			try {
				ObjectInputStream in = new ObjectInputStream(client
						.getClientSocket().getInputStream());
				currentWorkflow = (WorkflowDTO) in.readObject();
				currentWorkflow.setResults(new LinkedList<ResultsDTO>());
				optimizationsListModel.addElement(currentWorkflow);
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}

			new Thread(new Runnable() {

				@Override
				public void run() {
					boolean cont = true;
					panelGraph.reset();
					while (cont) {
						ResultsDTO results = client.receiveResult();
						if (results == null || results.getId() == -1) {
							cont = false;
						} else if (results.getId() == -2) {
							cont = false;
							JOptionPane
									.showMessageDialog(
											parent,
											"Calculations has been stopped. NaN value reached",
											"NaN", JOptionPane.ERROR_MESSAGE);
						} else {
							currentWorkflow.getResults().add(results);
							panelGraph.addValueToDataset(results);
						}
					}
					createButton.setEnabled(true);
					iterateButton.setEnabled(true);
				}
			}).start();

			optimizationsList
					.removeListSelectionListener(listSelectionListener);
			optimizationsList
					.setSelectedIndex(optimizationsListModel.size() - 1);
			optimizationsList.addListSelectionListener(listSelectionListener);
		}
	}

	class CreateNewOptimListener implements ActionListener {

		public void actionPerformed(ActionEvent event) {
			panelGraph.reset();
			enableComponents(panelInitVar, true);
			enableComponents(panelInput, true);
			enableComponents(panelButton, true);
			createButton.setEnabled(false);
			iterateButton.setEnabled(false);
		}
	}

	class AddNewIterListener implements ActionListener {

		public void actionPerformed(ActionEvent event) {
			createButton.setEnabled(false);
			iterateButton.setEnabled(false);
			try {
				client.getClientSocket().send(
						OperationType.ITERATE,
						currentWorkflow.getId(),
						Integer.parseInt(spinnerModelIterNumber.getValue()
								.toString()));
			} catch (ServerOfflineException e) {
				((MainFrame) parent).closeAfterDisconnection();
				return;
			}

			new Thread(new Runnable() {

				@Override
				public void run() {
					boolean cont = true;
					while (cont) {
						ResultsDTO results = client.receiveResult();
						if (results == null || results.getId() == -1) {
							cont = false;
						} else {
							panelGraph.addValueToDataset(results);
						}
					}
					createButton.setEnabled(true);
					iterateButton.setEnabled(true);
				}
			}).start();
		}
	}

	class SharedListSelectionHandler implements ListSelectionListener {

		public void valueChanged(ListSelectionEvent e) {
			createButton.setEnabled(true);
			iterateButton.setEnabled(true);
			startButton.setEnabled(false);

			int selectedOptimization = optimizationsList.getSelectedIndex();
			currentWorkflow = optimizationsListModel.get(selectedOptimization);

			System.out.println("Event for indexes "
					+ optimizationsList.getSelectedIndex());
			WorkflowDTO dto = null;
			try {
				dto = client.receiveWorkflow(currentWorkflow.getId());
			} catch (ServerOfflineException ex) {
				((MainFrame) parent).closeAfterDisconnection();
			}
			if (dto != null) {
				enableComponents(panelInitVar, false);
				enableComponents(panelInput, false);
				startButton.setEnabled(false);
				setInputValues(dto);
				panelGraph.displayOptimizationRatio(dto.getResults());
			}
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
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		String[] values = (String[]) value;
		label1.setText(values[0]);
		label2.setText(values[1]);

		return this;
	}

}
