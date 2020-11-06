package metro;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class TashkentMetro {

	protected static final HashMap<String, Estacion> estaciones = new HashMap<>();

	public TashkentMetro(){

		String [] nombresLineaAzul = {"Beruniy", "Tinchlik", "Chorsu", "G'afur G'ulom", "Alisher Navoiy", "O'zbekiston", "Kosmonavtlar", "Oybek", "Toshkent", "Mashinasozlar", "Chkalov"};

		double [] xAzul = {517279.75, 518335.41, 519736.21, 520712.45, 521263.33, 521235.58, 522090.06, 522848.99, 523987.46, 525483.98, 526908.82};
		double [] yAzul = {4577181.59, 4575635.85, 4574899.20, 4575143.86, 4574304.83, 4573374.66, 4572668.41, 4572029.37, 4571297.66, 4572045.85, 4571469.61};

		System.out.println("*Linea Azul:");
		for (int i = 0; i<nombresLineaAzul.length; i++) {
			String nEst = nombresLineaAzul[i];
			Estacion nueva;
			if (i == 0) {
				String [] contiguas = {nombresLineaAzul[i+1]};
				nueva = new Estacion (nEst, xAzul[i], yAzul[i], contiguas);
				System.out.println("Estacion: " + nEst + ", Coordenada x: " + xAzul[i] + ", Coordenada y: " + yAzul[i] + ", Contiguas: " + nombresLineaAzul[i+1]);
			}
			else if(i == nombresLineaAzul.length-1) {
				String [] contiguas = {nombresLineaAzul[i-1]};
				nueva = new Estacion (nEst, xAzul[i], yAzul[i], contiguas);
				System.out.println("Estacion: " + nEst + ", Coordenada x: " + xAzul[i] + ", Coordenada y: " + yAzul[i] + ", Contiguas: " + nombresLineaAzul[i-1]);
			}
			else if(nEst.equals("Alisher Navoiy")) {
				String [] contiguas = {nombresLineaAzul[i-1], "Paxtakor", nombresLineaAzul[i+1]};
				nueva = new Estacion (nEst, xAzul[i], yAzul[i], contiguas);
				System.out.println("Estacion: " + nEst + ", Coordenada x: " + xAzul[i] + ", Coordenada y: " + yAzul[i] + ", Contiguas: " + nombresLineaAzul[i-1] + ", Paxtakor, " + nombresLineaAzul[i+1]);
			} 
			else if(nEst.equals("Oybek")) {
				String [] contiguas = {nombresLineaAzul[i-1], "Ming O'riq", nombresLineaAzul[i+1]};
				nueva = new Estacion (nEst, xAzul[i], yAzul[i], contiguas);
				System.out.println("Estacion: " + nEst + ", Coordenada x: " + xAzul[i] + ", Coordenada y: " + yAzul[i] + ", Contiguas: " + nombresLineaAzul[i-1] + ", Ming O'riq, " + nombresLineaAzul[i+1]);
			} 
			else {
				String [] contiguas = {nombresLineaAzul[i-1], nombresLineaAzul[i+1]};
				nueva = new Estacion (nEst, xAzul[i], yAzul[i], contiguas);
				System.out.println("Estacion: " + nEst + ", Coordenada x: " + xAzul[i] + ", Coordenada y: " + yAzul[i] + ", Contiguas: " + nombresLineaAzul[i-1] + ", " + nombresLineaAzul[i+1]);
			}
			estaciones.put(nEst, nueva);
		}

		String [] nombresLineaRoja = {"Sobir Rahimov", "Chilonzor", "Mirzo Ulug'bek", "Hamza", "Yoshlik", "Xalqlar Do'stligi", 
				"Paxtakor", "Mustaqilik Maydoni", "Amir Temur Hiyoboni", "Hamid Olimjon", "Pushkin", "Buyuk Ipak Yo'li"};

		double [] xRoja = {516412.33, 517023.49, 517723.35, 518693.61, 519748.66, 520253.04, 521231.02, 522593.61, 523798.94, 524680.32, 526130.05, 527410.62};
		double [] yRoja = {4567346.13, 4569387.53, 4570181.19, 4571117.34, 4572527.47, 4573359.69, 4574039.11, 4573817.57, 4573572.14, 4574024.76, 4574566.55, 4575015.62};

		System.out.println("\n*Linea Roja:");
		for (int i = 0; i<nombresLineaRoja.length; i++) {
			String nEst = nombresLineaRoja[i];
			Estacion nueva;
			if (i == 0) {
				String [] contiguas = {nombresLineaRoja[i+1]};
				nueva = new Estacion (nEst, xRoja[i], yRoja[i], contiguas);
				System.out.println("Estacion: " + nEst + ", Coordenada x: " + xRoja[i] + ", Coordenada y: " + yRoja[i] + ", Contiguas: " + nombresLineaRoja[i+1]);
			}
			else if(i == nombresLineaRoja.length-1) {
				String [] contiguas = {nombresLineaRoja[i-1]};
				nueva = new Estacion (nEst, xRoja[i], yRoja[i], contiguas);
				System.out.println("Estacion: " + nEst + ", Coordenada x: " + xRoja[i] + ", Coordenada y: " + yRoja[i] + ", Contiguas: " + nombresLineaRoja[i-1]);
			}
			else if(nEst.equals("Paxtakor")) {
				String [] contiguas = {nombresLineaRoja[i-1], "Alisher Navoiy", nombresLineaRoja[i+1]};
				nueva = new Estacion (nEst, xRoja[i], yRoja[i], contiguas);
				System.out.println("Estacion: " + nEst + ", Coordenada x: " + xRoja[i] + ", Coordenada y: " + yRoja[i] + ", Contiguas: " + nombresLineaRoja[i-1] + ", Alisher Navoiy, " + nombresLineaRoja[i+1]);
			}
			else if(nEst.equals("Amir Temur Hiyoboni")) {
				String [] contiguas = {nombresLineaRoja[i-1], "Yunus Rajabiy", nombresLineaRoja[i+1]};
				nueva = new Estacion (nEst, xRoja[i], yRoja[i], contiguas);
				System.out.println("Estacion: " + nEst + ", Coordenada x: " + xRoja[i] + ", Coordenada y: " + yRoja[i] + ", Contiguas: " + nombresLineaRoja[i-1] + ", Yunus Rajabiy, " + nombresLineaRoja[i+1]);
			}
			else {
				String [] contiguas = {nombresLineaRoja[i-1], nombresLineaRoja[i+1]};
				nueva = new Estacion (nEst, xRoja[i], yRoja[i], contiguas);
				System.out.println("Estacion: " + nEst + ", Coordenada x: " + xRoja[i] + ", Coordenada y: " + yRoja[i] + ", Contiguas: " + nombresLineaRoja[i-1] + ", " + nombresLineaRoja[i+1]);
			}
			estaciones.put(nEst, nueva);
		}

		String [] nombresLineaVerde = {"Habib Abdullayev", "Bodomzor", "Minor", "Abdulla Qodiri", "Yunus Rajabiy", "Ming O'riq"};

		double [] xVerde = {524081.09, 523825.02, 523724.18, 523604.61, 523612.97, 523024.80};
		double [] yVerde = {4577969.94, 4576281.41, 4575142.13, 4574234.80, 4573464.38, 4572129.50};

		System.out.println("\n*Linea Verde:");
		for (int i = 0; i<nombresLineaVerde.length; i++) {
			String nEst = nombresLineaVerde[i];
			Estacion nueva;
			if (i == 0) {
				String [] contiguas = {nombresLineaVerde[i+1]};
				nueva = new Estacion (nEst, xVerde[i], yVerde[i], contiguas);
				System.out.println("Estacion: " + nEst + ", Coordenada x: " + xVerde[i] + ", Coordenada y: " + yVerde[i] + ", Contiguas: " + nombresLineaVerde[i+1]);
			}
			else if(nEst.equals("Yunus Rajabiy")) {
				String [] contiguas = {nombresLineaVerde[i-1], "Amir Temur Hiyoboni", nombresLineaVerde[i+1]};
				nueva = new Estacion (nEst, xVerde[i], yVerde[i], contiguas);
				System.out.println("Estacion: " + nEst + ", Coordenada x: " + xVerde[i] + ", Coordenada y: " + yVerde[i] + ", Contiguas: " + nombresLineaVerde[i-1] + ", Amir Temur Hiyoboni, " + nombresLineaVerde[i+1]);
			}
			else if(nEst.equals("Ming O'riq")) {
				String [] contiguas = {nombresLineaVerde[i-1], "Oybek"};
				nueva = new Estacion (nEst, xVerde[i], yVerde[i], contiguas);
				System.out.println("Estacion: " + nEst + ", Coordenada x: " + xVerde[i] + ", Coordenada y: " + yVerde[i] + ", Contiguas: " + nombresLineaVerde[i-1] + ", Oybek");
			} else {
				String [] contiguas = {nombresLineaVerde[i-1], nombresLineaVerde[i+1]};
				nueva = new Estacion (nEst, xVerde[i], yVerde[i], contiguas);
				System.out.println("Estacion: " + nEst + ", Coordenada x: " + xVerde[i] + ", Coordenada y: " + yVerde[i] + ", Contiguas: " + nombresLineaVerde[i-1] + ", " + nombresLineaVerde[i+1]);
			}
			estaciones.put(nEst, nueva);
		}
		System.out.println();
	}

	public List<String> getEstaciones(){
		LinkedList<String> copia = new LinkedList<>();
		Iterator<String> it = estaciones.keySet().iterator();
		while(it.hasNext()) {
			copia.add(it.next());
		}
		Collections.sort(copia);
		return copia;
	}
}
