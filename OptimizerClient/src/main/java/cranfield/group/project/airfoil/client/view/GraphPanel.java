package cranfield.group.project.airfoil.client.view;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import cranfield.group.project.airfoil.api.model.ResultsDTO;

public class GraphPanel extends JPanel implements Observer {
	protected XYSeriesCollection graphData;

	public GraphPanel() {
		super();
		graphData = new XYSeriesCollection(new XYSeries("Lift / Drag"));
		reset();
	}

	public void reset() {
		graphData = new XYSeriesCollection(new XYSeries("Lift / Drag"));
		removeAll();
		JFreeChart ratioGraph = ChartFactory.createXYLineChart("",
				"Iterations", "Lift/Drag", graphData, PlotOrientation.VERTICAL,
				false, true, false);

		add(new ChartPanel(ratioGraph));
		// Update the display to make the new graph appear
		revalidate();
	}

	public void displayOptimizationRatio(List<ResultsDTO> workflowResults) {
		removeAll();

		JFreeChart ratioGraph = ChartFactory.createXYLineChart("",
				"Iterations", "Lift/Drag",
				(XYDataset) createDataset(workflowResults),
				PlotOrientation.VERTICAL, false, true, false);

		add(new ChartPanel(ratioGraph));
		// Update the display to make the new graph appear
		revalidate();
	}

	private void updateGraph() {
		removeAll();

		JFreeChart ratioGraph = ChartFactory.createXYLineChart("",
				"Iterations", "Lift/Drag", (XYDataset) graphData,
				PlotOrientation.VERTICAL, false, true, false);

		add(new ChartPanel(ratioGraph));
		// Update the display to make the new graph appear
		revalidate();
	}

	public void addValueToDataset(ResultsDTO result) {
		graphData.getSeries(0).add(result.getIteration(), result.getRatio());
	}

	private XYDataset createDataset(List<ResultsDTO> workflowResults) {
		graphData.getSeries(0).clear();
		for (int i = 0; i < workflowResults.size(); i++) {
			graphData.getSeries(0)
					.add(i + 1, workflowResults.get(i).getRatio());
		}
		return graphData;
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg.getClass() == ResultsDTO.class) {
			ResultsDTO resultToBeAdded = (ResultsDTO) arg;
			addValueToDataset(resultToBeAdded);
			updateGraph();
		}
	}

	public static void main(String[] args) {
		GraphPanel graph = new GraphPanel();
		JFrame window = new JFrame();
		window.add(graph);
		window.setVisible(true);
		window.setSize(800, 800);
		window.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		graph.reset();
		for (int i = 0; i < 10; i++) {
			double ratio = 5.7 + (double) i;
			ResultsDTO results = new ResultsDTO(i + 1, ratio);

			graph.addValueToDataset(results);
			// graph.updateGraph();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
