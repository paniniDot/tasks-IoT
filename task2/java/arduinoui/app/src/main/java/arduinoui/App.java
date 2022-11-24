/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package arduinoui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import com.fazecast.jSerialComm.SerialPort;

public class App {
    public static void main(String[] args) {
    	JFrame win = new JFrame();
    	win.setTitle("Arduino UI");
    	win.setSize(600, 400);
    	win.setLayout(new BorderLayout());
    	win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	JComboBox<String> portList = new JComboBox<>();
    	JButton connectBtn = new JButton("Connect");
    	JPanel topPanel = new JPanel();
    	topPanel.add(portList);
    	topPanel.add(connectBtn);
    	win.add(topPanel, BorderLayout.NORTH); 
    	
    	Arrays.stream(SerialPort.getCommPorts())
		.map(SerialPort::getSystemPortName)
		.forEach(name -> portList.addItem(name));
    	
    	XYSeries series = new XYSeries("Water Level Readings");
    	XYDataset dataset = new XYSeriesCollection(series);
    	JFreeChart chart = ChartFactory.createXYLineChart("Water Level Readings", "Time (Seconds)",  "Water Level (cm)", dataset, PlotOrientation.VERTICAL, true, false, false);
    	
    	connectBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
    		
    	});
    	
    	win.add(new ChartPanel(chart), BorderLayout.CENTER);
    	
    	win.setVisible(true);
	}
}
