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
     * MÃ©todo que tira una carta aleatoria 
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
     * MÃ©todo que tira una carta "inteligentemente"
     */
    public Carta tirarCartaInteligentemente1(Palo paloPrimeraCartaDeLaBaza, 
                                            Carta cartaQueVaGanando,
                                            Palo paloQuePinta)
    {
        return tirarCartaAleatoria();        
    }
    
    
    /**Se pide que, trabajando sobre el código terminado de la activida 0892,
     * modifiques el cuerpo del método tirarCartaInteligentemente (solo el cuerpo) para que los jugadores no humanos tiren la carta 
     * inteligentemente y no aleatoriamente. (no tiene que ser muy inteligente, con que cumplan las normas es suficiente, no hace falta controlar tirar la carta mas conveniente en cada situación)
     */

     public Carta tirarCartaInteligentemente(Palo paloPrimeraCartaDeLaBaza, Carta cartaQueVaGanando,Palo paloQuePinta)
    {
       Carta cartaTirada = null;                                                                                   //Inicializo el objeto carta a null
       boolean elJugadorHaTiradoUnaCarta = false;                                                                  //Usaré este booleano como indicador de si el jugador ha lanzado ya una carta
       int contador = 0;
       
       
        if(numeroCartasEnLaMano >= 0){                                                                            //Me aseguro que tienen cartas en la mano
            for(Carta cartaMirar : cartasQueTieneEnLaMano){
                if (cartasQueTieneEnLaMano[contador] != null && !elJugadorHaTiradoUnaCarta) {                     //Si la carta que estamos mirando es distinto de nulo y el jugador no ha tirado carta    
                     //Compruebo si tiene cartas del mismo palo de la carta de la baza y si la gana
                    if(paloPrimeraCartaDeLaBaza == cartaMirar.getPalo() && cartaMirar.ganaA(cartaQueVaGanando,paloPrimeraCartaDeLaBaza)){                                        
                            cartaTirada = cartaMirar;                                                              //Piso el valor null de mi objeto cartaTirada guardando el valor de la cartaMirar
                            cartasQueTieneEnLaMano[contador] = null;                                               //Pongo a null la carta ya válida
                            numeroCartasEnLaMano--;                                                                //Elimino una carta del entero que guardaba las cartas que tiene en la mano
                            elJugadorHaTiradoUnaCarta = true;                                                      //Poniendo este valor a true evitor mirar estos dos if
                    }
                }
                contador++;
            }    
            contador = 0;                                                                                          //Inicializo a 0 mi variable contador para volver a recorrer el mazo
            
            //Si no hemos conseguido una carta del mismo palo del paloPrimeraCartaDeLaBaza que gane pues busco una carta que no gane, o que sea del paloQuePinta
            if(cartaTirada == null){                                                                               
                for(Carta cartaMirar : cartasQueTieneEnLaMano){                                                      
                    if (cartasQueTieneEnLaMano[contador] != null && !elJugadorHaTiradoUnaCarta) {                  //Si la carta que estamos mirando es distinto de nulo y el jugador no ha tirado carta    
                        //Si el paloPrimeraCartaDeLaBaza == al de mi carta,(esto ya lo habia comprobado en el for de más arriba) y no puedo ganara a la cartaQueVaGanando pues tengo que asistir al palo si o si.
                        if(paloPrimeraCartaDeLaBaza == cartaMirar.getPalo() && cartaTirada == null && !elJugadorHaTiradoUnaCarta){                                      
                            cartaTirada = cartaMirar;                                                              
                            cartasQueTieneEnLaMano[contador] = null;                                               
                            numeroCartasEnLaMano--;                                                               
                            elJugadorHaTiradoUnaCarta = true;                                                      
                         } 
                        //Sino tengo cartas del paloPrimeraCartaDeLaBaza tengo que lanzar una carta que sea del paloQuePinta
                         else{                                                                                                                                    
                              if(!elJugadorHaTiradoUnaCarta && cartaMirar.getPalo() == paloQuePinta){              //Compruebo que no haya tirado ninguna carta y que el palo a mirar es = al palo que pinta              
                                  cartaTirada = cartaMirar;     
                                  cartasQueTieneEnLaMano[contador] = null;
                                  numeroCartasEnLaMano--;
                                  elJugadorHaTiradoUnaCarta = true;;    
                              }
                        }           
                    }
                    contador++;
                }
             }
             
            //Si no hemos conseguido una carta del mismo palo del paloPrimeraCartaDeLaBaza y tampoco del paloQuePinta pues que lance una aleatorio
            if(cartaTirada == null){                                                
                contador = 0;
                for(Carta cartaMirar : cartasQueTieneEnLaMano){
                    if (cartasQueTieneEnLaMano[contador] != null && !elJugadorHaTiradoUnaCarta) {                  //Si la carta que estamos mirando es distinto de nulo y el jugador no ha tirado carta    
                        //Si el paloPrimeraCartaDeLaBaza es distinto al de mi carta, y tambien distinto del paloQuePinta
                        if(paloPrimeraCartaDeLaBaza != cartaMirar.getPalo() && cartaMirar.getPalo() != paloQuePinta){  
                             Random aleatorio = new Random();                                                      //Creo un objeto Random llamado aleatorio
                             while (!elJugadorHaTiradoUnaCarta) {                                                  //Un bucle que no para hasta que el jugador lance una carta
                                 int posicionAleatoria = aleatorio.nextInt(5);                                     //Obtengo un numero aleatorio predefinido
                                 if (cartasQueTieneEnLaMano[posicionAleatoria] != null) {                          //No entra si la carta de posicion aleatoria es null
                                     cartaTirada = cartasQueTieneEnLaMano[posicionAleatoria];  
                                     cartasQueTieneEnLaMano[posicionAleatoria] = null;
                                     numeroCartasEnLaMano--;
                                     elJugadorHaTiradoUnaCarta = true;
                                    }
                             }
                        }
                    }
                    contador++;
                } 
            }
        }

       System.out.println(nombre + " ha tirado " + cartaTirada);                                                        
       return cartaTirada;     
    }
   
    
    /**
     public Carta tirarCartaInteligentemente(Palo paloPrimeraCartaDeLaBaza, Carta cartaQueVaGanando,Palo paloQuePinta)
    {
              Carta cartaTirada = null;                                                                                   //Inicializo el objeto carta a null
       boolean elJugadorHaTiradoUnaCarta = false;                                                                  //Usaré este booleano como indicador de si el jugador ha lanzado ya una carta
       int contador = 0;
       
       
        if(numeroCartasEnLaMano >= 0){                                                                            //Me aseguro que tienen cartas en la mano
            for(Carta cartaMirar : cartasQueTieneEnLaMano){
                if (cartasQueTieneEnLaMano[contador] != null && !elJugadorHaTiradoUnaCarta) {                     //Si la carta que estamos mirando es distinto de nulo y el jugador no ha tirado carta    
                    if(paloPrimeraCartaDeLaBaza == cartaMirar.getPalo()){                                         //Compruebo si tiene cartas del mismo palo de la carta de la baza y si la gana
                        if(cartaMirar.ganaA(cartaQueVaGanando,paloPrimeraCartaDeLaBaza)){      
                            cartaTirada = cartaMirar;                                                              //Puedo llegar a tirar esta carta
                            cartasQueTieneEnLaMano[contador] = null;                                    //La pongo a null
                            numeroCartasEnLaMano--;                                                        //elimino una carta del entero que guardaba las cartas que tiene en la mano
                            elJugadorHaTiradoUnaCarta = true;              
                        }
                    }
                }
                contador++;
            }    
            contador = 0;                                                                                              //Inicializo a 0 mi variable contador para volver a recorrer el mazo
            if(cartaTirada == null){                                                                              //Si ya hemos conseguido una carta del mismo palo de la primera carta de la baza, pues no hace todo lo siguiente
                for(Carta cartaMirar : cartasQueTieneEnLaMano){                                                      //Creo un for each que recorra sus cartas.
                    if (cartasQueTieneEnLaMano[contador] != null && !elJugadorHaTiradoUnaCarta) {                //Si la carta que estamos mirando es distinto de nulo y el jugador no ha tirado carta
                        if(paloPrimeraCartaDeLaBaza == cartaMirar.getPalo()){                              //Si el palo de la primera carta de la baza == al de mi carta
                                if(cartaTirada == null && !elJugadorHaTiradoUnaCarta){                                                         //Si no puedo ganar a la carta que va ganando tengo que asistir  al palo de la baza
                                    cartaTirada = cartaMirar;     
                                    cartasQueTieneEnLaMano[contador] = null;
                                    numeroCartasEnLaMano--;
                                    elJugadorHaTiradoUnaCarta = true;
                                 }
                                /* else{                                                                           //Si no puedo ganar a la carta que va ganando tengo que asistir  al palo de la baza
                                     cartaTirada = cartaMirar;     
                                     cartasQueTieneEnLaMano[cartaActual] = null;
                                     numeroCartasEnLaMano--;
                                     elJugadorHaTiradoUnaCarta = true;
                                 }*
                         } 
                         else{                                                                               //Si el palo de la primera carta de la baza != al de mi carta                                                         
                                if(!elJugadorHaTiradoUnaCarta && cartaMirar.getPalo() == paloQuePinta){                                        //El palo de la carta que estoy mirando = al palo que pinta (no el de la baza)
                                    cartaTirada = cartaMirar;     
                                    cartasQueTieneEnLaMano[contador] = null;
                                    numeroCartasEnLaMano--;
                                    elJugadorHaTiradoUnaCarta = true;;    
                                }
                        }           
                    }
                    contador++;
                }
             }
             
            if(cartaTirada == null){                                                    //Aun no tengo carta que lanzar
                contador = 0;
                for(Carta cartaMirar : cartasQueTieneEnLaMano){
                    if (cartasQueTieneEnLaMano[contador] != null && !elJugadorHaTiradoUnaCarta) {                //Si la carta que estamos mirando es distinto de nulo y el jugador no ha tirado carta    
                        if(paloPrimeraCartaDeLaBaza != cartaMirar.getPalo() && cartaMirar.getPalo() != paloQuePinta && cartaTirada == null){  
                             Random aleatorio = new Random();
                             while (!elJugadorHaTiradoUnaCarta) {
                                 int posicionAleatoria = aleatorio.nextInt(5);
                                 if (cartasQueTieneEnLaMano[posicionAleatoria] != null) {
                                     cartaTirada = cartasQueTieneEnLaMano[posicionAleatoria];  
                                     cartasQueTieneEnLaMano[posicionAleatoria] = null;
                                     numeroCartasEnLaMano--;
                                     elJugadorHaTiradoUnaCarta = true;
                                    }
                             }
                        }
                    }
                    contador++;
                } 
            }
        }

       System.out.println(nombre + " ha tirado " + cartaTirada);
       return cartaTirada;     
    }
     */
    
    
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




























