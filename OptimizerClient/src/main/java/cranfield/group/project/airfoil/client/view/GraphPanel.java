package cranfield.group.project.airfoil.client.view;

import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import cranfield.group.project.airfoil.api.model.IterationValuesSet;

public class GraphPanel extends JPanel
{
   public GraphPanel()
   {
      super();
      reset();
   }
   
   public void reset(){
	   removeAll();
	   JFreeChart ratioGraph = ChartFactory.createXYLineChart(
    		  "", 
    		  "Iterations", 
    		  "Lift/Drag", 
    		  null, 
    		  PlotOrientation.VERTICAL,
    		  false, 
    		  false, 
    		  false);

      add(new ChartPanel(ratioGraph));	
	  // Update the display to make the new graph appear
	  revalidate();
   }
   
   public void displayOptimizationRatio(Vector<IterationValuesSet> optimizationResults){
	   removeAll();
	   	   
	   JFreeChart ratioGraph = ChartFactory.createXYLineChart(
	    		  "", 
	    		  "Iterations", 
	    		  "Lift/Drag", 
	    		  (XYDataset) createDataset(optimizationResults), 
	    		  PlotOrientation.VERTICAL,
	    		  false, 
	    		  false, 
	    		  false);

	   add(new ChartPanel(ratioGraph));
	   // Update the display to make the new graph appear
	   revalidate();
   }

   private XYDataset createDataset(Vector<IterationValuesSet> optimizationResults) {
	    XYSeriesCollection dataset = new XYSeriesCollection();
	    XYSeries ratios = new XYSeries("Lift / Drag");
	 
	    for(int i=0; i<optimizationResults.size(); i++){
			   ratios.add(i+1, optimizationResults.get(i).getRatio());
	    }
	   
	    dataset.addSeries(ratios);
	    return dataset;
	}
   
   /*public static void main( String[ ] args ) 
   {
      GraphPanel chart = new GraphPanel();
      JFrame window = new JFrame();
      window.setContentPane(chart);
      window.setVisible( true );
   }*/
}