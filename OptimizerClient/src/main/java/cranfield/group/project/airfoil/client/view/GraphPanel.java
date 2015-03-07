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

import cranfield.group.project.airfoil.api.model.IterationValuesSet;

public class GraphPanel extends JPanel
{
   public GraphPanel()
   {
      super();
      // TODO: Change the Chart into a XYLineChart
      JFreeChart lineChart = ChartFactory.createLineChart(
		  "",
         "Iterations","Ratio Lift/Drag",
         null,
         PlotOrientation.VERTICAL,
         true,true,false);
         
      ChartPanel chartPanel = new ChartPanel( lineChart );
      chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
      add(chartPanel);
   }
   
   public void displayOptimizationRatio(Vector<IterationValuesSet> optimizationResults){
	   removeAll();
	   	   
	   JFreeChart lineChart = ChartFactory.createLineChart(
	         "",
	         "Iterations","Ratio Lift/Drag",
	         createDataset(optimizationResults),
	         PlotOrientation.VERTICAL,
	         false,false,false);
	   
	   XYPlot plot = (XYPlot) lineChart.getPlot();
       final NumberAxis xAxis = (NumberAxis) plot.getDomainAxis();
       xAxis.setTickUnit(new NumberTickUnit(20));
		         
	   add(new ChartPanel( lineChart ));
	   // Update the display to make the new graph appear
	   revalidate();
   }

   private DefaultCategoryDataset createDataset(Vector<IterationValuesSet> optimizationResults )
   {
	   String iteration = "0";
	   DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
	   
	   for(int i=0; i<optimizationResults.size(); i++){
		   iteration = Integer.toString(i+1);
		   dataset.addValue( optimizationResults.get(i).getRatio() , "ratio" , iteration );
	   }
	   
	   return dataset;
   }
   public static void main( String[ ] args ) 
   {
      GraphPanel chart = new GraphPanel();
      JFrame window = new JFrame();
      window.setContentPane(chart);
      //chart.pack( );
      //RefineryUtilities.centerFrameOnScreen( chart );
      window.setVisible( true );
   }
}