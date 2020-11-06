package metro;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.LinkedList;
import java.net.URL;

public class InterfazGrafica extends JFrame {

	private static final long serialVersionUID = 1L;

	private static final Color BGCOLOR = Color.WHITE;

    private JFrame InterfazGrafica;

    private LinkedList<String> listaParadas;

    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            try {
                InterfazGrafica window = new InterfazGrafica();
                window.InterfazGrafica.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public InterfazGrafica() {
    	listaParadas = (LinkedList<String>) new TashkentMetro().getEstaciones();	
        initialize();
    }

    private void initialize() {
        InterfazGrafica = new JFrame();
        InterfazGrafica.getContentPane().setFont(new Font("Times New Roman", Font.BOLD, 15));
        InterfazGrafica.getContentPane().setBackground(BGCOLOR);
        InterfazGrafica.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		InterfazGrafica.setFont(new Font("Times New Roman", Font.BOLD, 15));
        InterfazGrafica.setTitle("Metro Tashkent");
        InterfazGrafica.setBounds(300, 25, 650, 650);
        InterfazGrafica.getContentPane().setLayout(null);

        JPanel panelOrigen = new JPanel();
        panelOrigen.setBackground(BGCOLOR);
        panelOrigen.setBounds(0, 530, 217, 76); //Bordes
        InterfazGrafica.getContentPane().add(panelOrigen);
        panelOrigen.setLayout(null);

        final JComboBox<Object> comboBoxOrigen = new JComboBox<Object>(listaParadas.toArray());
        comboBoxOrigen.setEditable(true);
        comboBoxOrigen.setBackground(Color.WHITE);
        comboBoxOrigen.setForeground(Color.BLACK);
        comboBoxOrigen.setFont(new Font("Times New Roman", Font.BOLD, 12));
        comboBoxOrigen.setBounds(25,37, 182, 28);
        panelOrigen.add(comboBoxOrigen);

        JLabel labelOrigen = new JLabel("Parada inicio:");
        labelOrigen.setFont(new Font("Times New Roman", Font.BOLD, 15));
        labelOrigen.setForeground(Color.BLACK);
        labelOrigen.setBounds(25, 11, 104, 15);
        panelOrigen.add(labelOrigen);

        JPanel panelDestino = new JPanel();
        panelDestino.setBackground(BGCOLOR);
        panelDestino.setForeground(Color.BLACK);
        panelDestino.setLayout(null);
        panelDestino.setBounds(230,530, 217, 76);
        InterfazGrafica.getContentPane().add(panelDestino);

        final JComboBox<Object> comboBoxDestino = new JComboBox<Object>(listaParadas.toArray());
        comboBoxDestino.setForeground(Color.BLACK);
        comboBoxDestino.setFont(new Font("Times New Roman", Font.BOLD, 12));
        comboBoxDestino.setEditable(true);
        comboBoxDestino.setBackground(Color.WHITE);
        comboBoxDestino.setBounds(25, 37, 182, 28);
        panelDestino.add(comboBoxDestino);

        JLabel labelDestino = new JLabel("Parada destino:");
        labelDestino.setFont(new Font("Times New Roman", Font.BOLD, 14));
        labelDestino.setBounds(25, 11, 95, 19);
        panelDestino.add(labelDestino);

        JButton buttonHallarRuta = new JButton("Hallar ruta");
        buttonHallarRuta.setForeground(Color.BLACK);
        buttonHallarRuta.setFont(new Font("Times New Roman", Font.BOLD, 12));
        buttonHallarRuta.setBackground(Color.decode("#00ffff"));
        buttonHallarRuta.setBounds(500, 570, 117, 25);
        buttonHallarRuta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	actionBotton(comboBoxOrigen,comboBoxDestino);
            	setVisible(false);
            }
        });
        
        InterfazGrafica.getContentPane().add(buttonHallarRuta);

        URL url = InterfazGrafica.class.getResource("/resources/Tashkent_metro_map.png");
        ImageIcon imageIcon = new ImageIcon(url);

        //ImageIcon imageIcon = new ImageIcon("./resources/Tashkent_metro_map.png"); 
        JLabel imageLabel = new JLabel(); 
        imageLabel.setIcon(imageIcon); 
        imageLabel.setBounds(0, 0, 730, 546);
        InterfazGrafica.getContentPane().add(imageLabel);
        
    }
    
    private void actionBotton(JComboBox<Object> comboBoxOrigen, JComboBox<Object> comboBoxDestino) {
      String estacionInicial =comboBoxOrigen.getSelectedItem().toString();
      String estacionFinal= comboBoxDestino.getSelectedItem().toString();
  	
      AlgoritmoEstrella aEstrella = new AlgoritmoEstrella(estacionInicial, estacionFinal);
      LinkedList<Estacion> estacionesOptimas = aEstrella.getMejorCamino();
      if (estacionesOptimas == null){
          return;
      }
      double tiempoTotal = aEstrella.getTiempoTotal();
      double distanciaTotal = aEstrella.getDistanciatotal();

      String msg = "";
      for (Estacion estacion : estacionesOptimas) {
          if (estacion != null) {
              msg += estacion.getNombre() + "\n";
          }
      }
      resultado(msg, tiempoTotal,distanciaTotal);
    }
    
    private void resultado(String msg, double tiempoTotal, double distanciaTotal) {
    	 ResultView res = new ResultView(this,msg,tiempoTotal,distanciaTotal);
         res.setVisible(true);
    }
}
