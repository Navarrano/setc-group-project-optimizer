package cranfield.group.project.airfoil.client.view;

import java.awt.*;
import java.awt.geom.*;
import java.util.Vector;

import javax.swing.*;

import cranfield.group.project.airfoil.api.model.IterationValuesSet;


public class DrawGraph extends JPanel{
    
    int[] data = {                                  // graph points
        21, 14, 18, 03, 86, 88, 74, 87, 54, 77,
        61, 55, 48, 60, 49, 36, 38, 27, 20, 18
    };
    Vector<Double> ratioValues;
    GeneralPath ratioCurve;
    
    final int PAD = 20;
    
    public DrawGraph(){
    	ratioValues = new Vector<Double>();
    	ratioCurve = new GeneralPath();
    	/*
    	ratioValues.add(21.4);
    	ratioValues.add(18.5);
    	ratioValues.add(27.9);
    	*/
    }
    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        
        int w = getWidth();
        int h = getHeight();
        
        // Draw ordinate.
        g2.draw(new Line2D.Double(PAD, PAD, PAD, h-PAD));
        // Draw abcissa.
        g2.draw(new Line2D.Double(PAD, h-PAD, w-PAD, h-PAD));
        
        double xInc = (double)(w - 2*PAD)/(ratioValues.size()-1);
        double scale = (double)(h - 2*PAD)/getMax();
        // Mark data points.
        g2.setPaint(Color.red);
        for(int i = 0; i < ratioValues.size(); i++) {
            double x = PAD + i*xInc;
            double y = h - PAD - scale*ratioValues.get(i);
            if(i==0)
            	ratioCurve.moveTo(x, y);
            else {
            	ratioCurve.lineTo(x, y);
            }
            //g2.fill(new Ellipse2D.Double(x-2, y-2, 4, 4));
        }
        g2.draw(ratioCurve);
    }
    private double getMax() {
        Double max = -Double.MAX_VALUE;
        for(int i = 0; i < ratioValues.size(); i++) {
            if(ratioValues.get(i) > max)
                max = ratioValues.get(i);
        }
        return max;
    }
    
    public void displayOptimizationRatio(Vector<IterationValuesSet> optimizationResults){
    	ratioValues.removeAllElements();
    	ratioCurve.reset();
    	for(int i=0; i<optimizationResults.size(); i++){
    		ratioValues.add(optimizationResults.get(i).getRatio());
    	}
    	repaint();
    }
    
    public static void main(String[] args) {
    	JFrame frame = new JFrame();
		DrawGraph graph = new DrawGraph();
    	frame.add(graph);
    	frame.setSize(400, 400);
		frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
    
}
