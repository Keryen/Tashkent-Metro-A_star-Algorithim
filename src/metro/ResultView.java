package metro;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ResultView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JFrame parent;
	private String msg;
	private double tiempoTotal;
	private double distanciaTotal;
	
	public ResultView(JFrame interfazGrafica, String msg, double tiempoTotal, double distanciaTotal) {
		this.msg = msg;
		this.tiempoTotal = tiempoTotal;
		this.distanciaTotal = distanciaTotal;
		this.parent = interfazGrafica;
		setTitle("Ruta hallada");
		setSize(500, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBackground(Color.WHITE);
		setForeground(Color.WHITE);
		
		createPanels();
	}

	private void createPanels() {
		JPanel panel  = new JPanel (new GridLayout(1,2));
		
		JPanel textPanel = new JPanel ();
		
		JTextArea area = new JTextArea("El camino a seguir es: " + '\n' + msg);
		area.setEditable(false);
		
        textPanel.setPreferredSize(new Dimension(500, 250));
        
		textPanel.add(area);
		panel.add(textPanel);
		
		JLabel tiempoLabel = new JLabel("El tiempo total para el recorrido es: "+'\n');
		JLabel distLabel = new JLabel("La distancia total a recorrer es de: "+'\n');
		
		JPanel resPanel = new JPanel (new FlowLayout());
		JLabel tiempo = new JLabel();
		tiempo.setText(conversionHoras(tiempoTotal));
		
		resPanel.add(tiempoLabel);
		resPanel.add(tiempo);
		
		JLabel distancia = new JLabel();
		DecimalFormat df = new DecimalFormat("#0.00");
		distancia.setText(df.format(conversionDistancia(distanciaTotal)) + " kilometros");
		resPanel.add(distLabel);
		resPanel.add(distancia);
		
		panel.add(resPanel);
		
		
		JButton close = new JButton("Close");
		close.addActionListener(event -> {
			parent.setVisible(true);
			this.setVisible(false);
		});
		close.setBounds(202, 200, 75, 25);
		getContentPane().add(close);
		add(panel);
	}
	
	private String conversionHoras(double num) {
		int horas;
		int minutos;
		horas = (int) num/60;
		minutos = (int) num - horas * 60;
		return horas + " horas " + minutos + " minutos";
	}
	
	private double conversionDistancia(double num) {
		return  num ;
	}
}
