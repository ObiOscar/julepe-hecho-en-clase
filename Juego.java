import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase Juego que simula el juego del Julepe.
 * 
 * @author Miguel Bayon
 */
public class Juego
{
    private Jugador[] jugadores;
    private Mazo mazo;
    private Palo paloQuePinta;
    private static final int NUMERO_DE_RONDAS = 5;


    /**
     * Constructor de la clase Juego
     *
     * @param numeroJugadores El número de jugadores que van a jugar
     * @param nombreJugadorHumano El nombre del jugador humano
     */
    public Juego(int numeroJugadores, String nombreJugadorHumano)
    {
        mazo = new Mazo();
        jugadores = new Jugador[numeroJugadores];

        ArrayList<String> posiblesNombres = new ArrayList<String>();
        posiblesNombres.add("Pepe");
        posiblesNombres.add("Maria");
        posiblesNombres.add("Juan");
        posiblesNombres.add("Luis");
        posiblesNombres.add("Marcos");
        posiblesNombres.add("Omar"); 
        posiblesNombres.add("Carlos");
        posiblesNombres.add("Azahara");  

        Jugador jugadorHumano = new Jugador(nombreJugadorHumano);
        jugadores[0] = jugadorHumano;
        System.out.println("Bienvenido a esta partida de julepe, " + nombreJugadorHumano);

        Random aleatorio = new Random();
        for (int i = 1; i < numeroJugadores; i++) {
            int posicionNombreElegido = aleatorio.nextInt(posiblesNombres.size());
            String nombreAleatorioElegido = posiblesNombres.get(posicionNombreElegido);
            posiblesNombres.remove(posicionNombreElegido);

            Jugador jugador = new Jugador(nombreAleatorioElegido);
            jugadores[i] = jugador;

        }

        System.out.println("Tus rivales son: ");
        for (int i = 1; i < jugadores.length; i++) {
            System.out.println(jugadores[i].getNombre());
        }
        System.out.println();
        
        jugar();
    }
    
    
    /**
     * Método que reparte 5 cartas a cada uno de los jugadores presentes en
     * la partida y elige un palo para que pinte.
     *
     * @return El palo que pinta tras repartir
     */
    private Palo repartir() 
    {
        mazo.barajar();

        Carta nuevaCarta = null;
        for (int cartaARepartir = 0; cartaARepartir < 5; cartaARepartir++) {            
            for (int jugadorActual = 0; jugadorActual < jugadores.length; jugadorActual++) {
                nuevaCarta = mazo.sacarCarta();
                jugadores[jugadorActual].recibirCarta(nuevaCarta);
            }
        }

        paloQuePinta = nuevaCarta.getPalo();


        return paloQuePinta;           
    }
   


    /**
     * Devuelve la posición del jugador cuyo nombre se especifica como
     * parámetro.
     *
     * @param nombre El nombre del jugador a buscar
     * @return La posición del jugador buscado o -1 en caso de no hallarlo.
     */
    private int encontrarPosicionJugadorPorNombre(String nombre)
    {
        int devuelve = -1;
        int contador = -1;          //Me la quiero cargar pero voy mal de tiempo...de momento funciona
         for(Jugador jugador : jugadores){
            contador++;
            if(jugador.getNombre().equals(nombre)){
                devuelve = contador;
            }
        }
        return devuelve;
    }
    
        
    /**
     * Desarrolla una partida de julepe teniendo en cuenta que el mazo y los
     * jugadores ya han sido creados. 
     * 
     * La partida se desarrolla conforme a las normas del julepe con la
     * excepción de que es el usuario humano quien lanza cada vez la primera
     * carta, independientemente de qué usuario haya ganado la baza anterior y,
     * además, los jugadores no humanos no siguen ningún criterio a la hora
     * de lanzar sus cartas, haciéndolo de manera aleatoria.
     * 
     * En concreto, el método de se encarga de:
     * 1. Repartir las cartas a los jugadores.
     * 2. Solicitar por teclado la carta que quiere lanzar el jugador humano.
     * 3. Lanzar una carta por cada jugador no humano.
     * 4. Darle la baza al jugador que la ha ganado.
     * 5. Informar de qué jugador ha ganado la baza.
     * 6. Repetir el proceso desde el punto 2 hasta que los jugadores hayan
     *    tirado todas sus cartas.
     * 7. Informar de cuántas bazas ha ganado el jugador humano.
     * 8. Indicar si el jugador humano "es julepe" (ha ganado menos de dos
     *    bazas) o "no es julepe".
     *
     */
    public void jugar()
    {
        Scanner cartaLanzar = new Scanner (System.in);
        repartir();                                                                              //1. Repartir las cartas a los jugadores.
        int contarCartas = 0;
        while(contarCartas < NUMERO_DE_RONDAS){                                                   // 6. Repetir el proceso desde el punto 2 hasta que los jugadores hayan tirado todas sus cartas.
            Baza cartasEnLaBaza = new Baza(jugadores.length,paloQuePinta);                       //Creo un objeto baza para el punto 3
            boolean haLanzadoCarta = false;
            int  contadorJugadores = 0;                                                                            
            while(contadorJugadores < (jugadores.length) ){  
                System.out.println("\nlas cartas de "+jugadores[contadorJugadores].getNombre()+ " son");        
                jugadores[contadorJugadores].verCartasJugador();
                contadorJugadores++;
            }
            
            while(!haLanzadoCarta){                                                              //2. Solicitar por teclado la carta que quiere lanzar el jugador humano.
                //jugadores[0].verCartasJugador();
                System.out.println(jugadores[0].getNombre()+ " �Que carta de su mazo quiere tirar?");
                System.out.println("");
                String nombreCarta = cartaLanzar.nextLine();
                System.out.println("");
                System.out.println("Las cartas jugadas son: ");
                Carta tieneCarta = jugadores[0].tirarCarta(nombreCarta);                         //Ser� la carta que tiene para tirar
                if( tieneCarta!= null){
                    cartasEnLaBaza.addCarta(tieneCarta,jugadores[0].getNombre());               //A�ado la carta que lanza a la baza
                    haLanzadoCarta = true;
                }
                else{
                    System.out.println("Lo sentiemos "+ jugadores[0].getNombre()+ " esa carta no la tiene en su mano");
                    System.out.println("");
                }
            }
        
            int contador = 1;
            while(contador < (jugadores.length) ){                                              //3. Lanzar una carta por cada jugador no humano.
                Carta cartaTiradaPorBot = jugadores[contador].tirarCartaInteligentemente(cartasEnLaBaza.getPaloPrimeraCartaDeLaBaza(),cartasEnLaBaza.cartaQueVaGanandoLaBaza(),paloQuePinta);
                cartasEnLaBaza.addCarta(cartaTiradaPorBot,jugadores[contador].getNombre());    //A�ado la carta que lanza a la baza
                contador++;
            }
        
        
            int posicionJugadorGanaBaza = encontrarPosicionJugadorPorNombre(cartasEnLaBaza.nombreJugadorQueVaGanandoLaBaza());
            jugadores[posicionJugadorGanaBaza].addBaza(cartasEnLaBaza);                         // 4. Darle la baza al jugador que la ha ganado.
            System.out.println("");
            System.out.println("Est� baza la ha ganado: "+ cartasEnLaBaza.nombreJugadorQueVaGanandoLaBaza());   // 5. Informar de qué jugador ha ganado la baza.
            System.out.println("");
            contarCartas++;                                                                    
        }
        
        System.out.println(""); 
        System.out.println("El jugador humano "+jugadores[0].getNombre()+ " ha ganado " + jugadores[0].getNumeroBazasGanadas());    //7. Informar de cuántas bazas ha ganado el jugador humano.
        System.out.println("");      
                
        if (jugadores[0].getNumeroBazasGanadas() < 2){                                          //8. Indicar si el jugador humano "es julepe" (ha ganado menos de dos bazas) o "no es julepe".
              System.out.println("Lo sentimos "+jugadores[0].getNombre()+ " eres julepe ");  
        }
        else{
              System.out.println("GENIAL "+jugadores[0].getNombre()+ " NO eres julepe ");  
        }
    }   
}













