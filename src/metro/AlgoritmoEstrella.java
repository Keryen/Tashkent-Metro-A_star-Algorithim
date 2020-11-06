package metro;

import java.util.HashMap;
import java.util.LinkedList;

public class AlgoritmoEstrella {

	// Constantes
	private static final double VELOCIDAD_MEDIA_TREN = 46;				// 46 km/hora
	private static final double VELOCIDAD_MEDIA_HUMANO = 6;				// 6 km/hora
	private static final double TIEMPO_ESCALERAS = 0.05;				// 3 minutos 
	private static final double TIEMPO_ESPERA_DEL_TREN = 0.1166;		// 7 minutos
	private static final double TIEMPO_PARADA_POR_ESTACION = 0.0083;	// 30 segundos

	// Variables globales
	private HashMap <String,Estacion> posiblesCaminos = new HashMap<>();
	private HashMap <String, Estacion> estacionesVisitadas = new HashMap<>();
	private LinkedList<Estacion> mejorCamino;
	private double tiempoTotal;
	private double distG = 0;

	public AlgoritmoEstrella (String eIni, String eFin) {
		if(!eIni.equals(eFin))
			tiempoTotal = TIEMPO_ESCALERAS + TIEMPO_ESPERA_DEL_TREN;    // entrar a la boca de metro + esperar al tren
		
		mejorCamino = calcularCamino(TashkentMetro.estaciones.get(eIni), TashkentMetro.estaciones.get(eFin));
	}

	private LinkedList<Estacion> calcularCamino(Estacion eIni, Estacion eFin) {  

		Estacion sigEst, eAct = eIni;

		while(!eAct.getNombre().equals(eFin.getNombre())) {	// Mientras la estacion actual no sea la final

			visitarEstacion(eAct);
			System.out.println("Estacion Actual :"  + eAct.getNombre());

			for(String nombreSigEst: eAct.getConectadas()) {

				if(estacionesVisitadas.containsKey(nombreSigEst))	// Si la estacion esta visitada se salta
					continue;

				sigEst = TashkentMetro.estaciones.get(nombreSigEst);
				System.out.println("Posible camino :"  + sigEst.getNombre());
				sigEst.setPadre(eAct);
				sigEst.setG(distG + getTiempoEstaciones(eAct, sigEst, eFin));
				sigEst.setH(getTiempoEstaciones(sigEst, eFin, eFin));

				anadirCamino(sigEst);
			}

			eAct = getEstacionMenorCoste();
			System.out.println("Estacion Siguiente :"  + eAct.getNombre()+ "\n");
			distG = eAct.getG();
		}

		visitarEstacion(eAct);
		tiempoTotal += eAct.getG();

		return getMejorCamino(eIni, eAct);
	}

	private double getDistEstaciones(Estacion eAct, Estacion eSig) {
		double distX = eAct.getX()- eSig.getX();
		double distY = eAct.getY()- eSig.getY();
		return Math.sqrt(Math.pow(distX, 2) + Math.pow(distY, 2)) / 1000;
	
	}

	private double getTiempoEstaciones(Estacion eAct, Estacion eSig, Estacion eFin) {
		double dist = getDistEstaciones(eAct, eSig);
		double tiempo=0;
		String [] con = eAct.getConectadas();
		if((con.length == 3 || eAct.getNombre().equals("Ming O'riq")) && con[1].equals(eSig.getNombre())) {
			if(!eSig.getNombre().equals(eFin.getNombre()))
				tiempo = TIEMPO_ESCALERAS + TIEMPO_ESPERA_DEL_TREN;		// entrar a la boca de metro + esperar al tren
			tiempo += TIEMPO_ESCALERAS + dist/VELOCIDAD_MEDIA_HUMANO;	// salir de la boca de metro + ir hasta el tranbordo 
		} else
			tiempo = TIEMPO_PARADA_POR_ESTACION +  dist/VELOCIDAD_MEDIA_TREN;	// espera del tren en la estacion + tiempo en ir hacia la siguiente estacion
		return tiempo;
	}

	private void visitarEstacion (Estacion est) {
		if(estacionesVisitadas.containsKey(est.getNombre())) {
			System.out.println("No se puede visitar una estacion ya visitada " + est.getNombre());
			return;
		}

		estacionesVisitadas.put(est.getNombre(), est);
		posiblesCaminos.remove(est.getNombre());
	}

	private void anadirCamino (Estacion est) {
		if(estacionesVisitadas.containsKey(est.getNombre())) {
			return;
		}

		for(String nEst: posiblesCaminos.keySet()) {
			if(nEst.equals(est.getNombre()) && est.getG()<posiblesCaminos.get(nEst).getG())
				posiblesCaminos.get(nEst).setG(est.getG());
		}
		
		posiblesCaminos.putIfAbsent(est.getNombre(), est);
	}

	private Estacion getEstacionMenorCoste () {
		Estacion eMenorCoste = null;
		for(String nEst: posiblesCaminos.keySet()) {
			Estacion est = posiblesCaminos.get(nEst);
			if(eMenorCoste == null)
				eMenorCoste = est;
			if(eMenorCoste.getF()>est.getF())
				eMenorCoste = est;		
		}
		return eMenorCoste;
	}

	private LinkedList<Estacion> getMejorCamino (Estacion eIni, Estacion eFin) {

		LinkedList <Estacion> auxCamino = new LinkedList<>();
		Estacion eAct = eFin;
		auxCamino.add(eAct);

		while(!eAct.getNombre().equals(eIni.getNombre())) {
			eAct = eAct.getPadre();
			auxCamino.addFirst(eAct);
		}

		return auxCamino;
	}

	public double getTiempoTotal () {
		return tiempoTotal * 60;
	}

	public double getDistanciatotal () {

		double dist = 0.0;
		for(int i = 0; i < mejorCamino.size() - 1; i++){
			dist += getDistEstaciones(mejorCamino.get(i), mejorCamino.get(i+1));
		}
		return dist;
	}

	public LinkedList<Estacion> getMejorCamino () {
		return mejorCamino;
	}
}
