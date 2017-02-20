import java.util.Random;
import java.util.ArrayList;
/**
 * Write a description of class Jugador here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Jugador
{
    private String nombre;
    private Carta[] cartasQueTieneEnLaMano;
    private int numeroCartasEnLaMano;
    private ArrayList<Baza> bazasGanadas;
    

    /**
     * Constructor for objects of class Jugador
     */
    public Jugador(String nombreJugador)
    {
        nombre = nombreJugador;
        cartasQueTieneEnLaMano = new Carta[5];
        numeroCartasEnLaMano = 0;   
        bazasGanadas = new ArrayList<Baza>();
    }

 
    /**
     * Metodo que hace que el jugador reciba una carta
     */
    public void recibirCarta(Carta cartaRecibida)
    {
        if (numeroCartasEnLaMano < 5) {
            cartasQueTieneEnLaMano[numeroCartasEnLaMano] = cartaRecibida;
            numeroCartasEnLaMano++;
        }
    }
    
    /**
     * Metodo que muestra las cartas del jugador por pantalla
     */
    public void verCartasJugador()
    {
        for (Carta cartaActual : cartasQueTieneEnLaMano) {
            if (cartaActual != null) {
                System.out.println(cartaActual);
            }
        }
    }

    
    /**
     * Metodo que devuelve el nombre del jugador
     */
    public String getNombre()
    {
        return nombre;
    }
    
    /**
     * Metodo que devuelve la carta especificada como parametro si
     * el jugador dispone de ella y simula que se lanza a la mesa
     */    
    public Carta tirarCarta(String nombreCarta)
    {
        Carta cartaTirada = null;
        
        if (numeroCartasEnLaMano > 0) {
            
            int cartaActual = 0;
            boolean buscando = true;
            while (cartaActual < cartasQueTieneEnLaMano.length && buscando) {
                if (cartasQueTieneEnLaMano[cartaActual] != null) {                                 
                    if (nombreCarta.equals(cartasQueTieneEnLaMano[cartaActual].toString())) {
                        buscando = false;
                        cartaTirada = cartasQueTieneEnLaMano[cartaActual];
                        cartasQueTieneEnLaMano[cartaActual] = null;
                        numeroCartasEnLaMano--;
                        System.out.println(nombre + " ha tirado " + cartaTirada);
                    }
                }
                cartaActual++;
            }
            
            
            
        }
                
        return cartaTirada;
    }
    
    
    
    /**
     * Método que tira una carta aleatoria 
     */
    public Carta tirarCartaAleatoria() 
    {
        Carta cartaTirada = null;
        
        
        if (numeroCartasEnLaMano > 0) {
            Random aleatorio = new Random();
            
            boolean elJugadorHaTiradoUnaCarta = false;
            while (elJugadorHaTiradoUnaCarta == false) {
                int posicionAleatoria = aleatorio.nextInt(5);
                if (cartasQueTieneEnLaMano[posicionAleatoria] != null) {
                    cartaTirada = cartasQueTieneEnLaMano[posicionAleatoria];
                    cartasQueTieneEnLaMano[posicionAleatoria] = null;
                    numeroCartasEnLaMano--;
                    System.out.println(nombre + " ha tirado " + cartaTirada);
                    elJugadorHaTiradoUnaCarta = true;
                }
            }
        }
        
        return cartaTirada;
    }
    
    
    /**
     * Método que tira una carta "inteligentemente"
     */
    public Carta tirarCartaInteligentemente(Palo paloPrimeraCartaDeLaBaza, 
                                            Carta cartaQueVaGanando,
                                            Palo paloQuePinta)
    {
        return tirarCartaAleatoria();        
    }
    
    
    /**este es mio
     */
     
     
   /*  public Carta tirarCartaInteligentemente1(int paloPrimeraCartaDeLaBaza, Carta cartaQueVaGanando,int paloQuePinta)
    {
        Carta cartaTirada = null;
        boolean elJugadorHaTiradoUnaCarta = false;
       int cartaActual = 0;
        
        if(numeroCartasEnLaMano > 0){
            for(Carta cartaMirar : cartasQueTieneEnLaMano){
                 if (cartasQueTieneEnLaMano[cartaActual] != null && !elJugadorHaTiradoUnaCarta) {  
                           //Si el palo de la primera carta de la baza == al de mi carta
                              if(paloPrimeraCartaDeLaBaza == cartaMirar.getPalo()){          
                                  //Si la carta a mirar gana a la carta que va ganando devuelve true            
                                  if(cartaMirar.ganaA(cartaQueVaGanando,paloPrimeraCartaDeLaBaza)){         
                                      cartaTirada = cartaMirar;     
                                      cartasQueTieneEnLaMano[cartaActual] = null;
                                      numeroCartasEnLaMano--;
                                      elJugadorHaTiradoUnaCarta = true;
                                 }
                                 //Si no puedo ganar a la carta que va ganando tengo que asistir  al palo de la baza
                                 else{
                                     cartaTirada = cartaMirar;     
                                     cartasQueTieneEnLaMano[cartaActual] = null;
                                     numeroCartasEnLaMano--;
                                     elJugadorHaTiradoUnaCarta = true;
                                 }
                              } 

                              //Si el palo de la primera carta de la baza != al de mi carta
                              else{            
                                  //El palo de la carta = al palo que pinta                                                         
                                if(cartaMirar.getPalo() == paloQuePinta ){       
                                    cartaTirada = cartaMirar;     
                                    cartasQueTieneEnLaMano[cartaActual] = null;
                                    numeroCartasEnLaMano--;
                                    elJugadorHaTiradoUnaCarta = true;;    
                                }                    
                           }
                 }
                 cartaActual++;
            } 
       }
            System.out.println(nombre + " ha tirado " + cartaTirada);
            return cartaTirada;    
    }*/
   
    
    
    /**
     * Metodo que hace que jugador recoja una baza ganada
     */
    public void addBaza(Baza bazaGanada)
    {
        bazasGanadas.add(bazaGanada);
    }
    
    
    /**
     * Metodo que devuelve el numero de bazas ganadas por el jugador hasta
     * el momento
     */
    public int getNumeroBazasGanadas() 
    {
        return bazasGanadas.size();
    }
}




























