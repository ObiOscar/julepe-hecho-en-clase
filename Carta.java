
/**
 * Write a description of class Carta here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Carta
{
    // Valor de la carta; de 1 a 7 y de 10 a 12
    private int valor;
    //Palo Clase Enum
    private Palo palo;

    /**
     * Constructor for objects of class Carta
     */
    public Carta(int valor, Palo paloEnum)
    {
        this.valor = valor;
        palo = paloEnum;
    }

    public int getValor()
    {
        return valor;
    }

    public Palo getPalo()
    {
        return palo;
    }

    public String toString()
    {
        String textoADevolver = "";

        String textoValor = Integer.toString(valor);

        switch (valor) {
            case 1:
            textoValor = "as";
            break;
            case 10:
            textoValor = "sota";
            break;
            case 11:
            textoValor = "caballo";
            break;
            case 12:
            textoValor = "rey";
            break;
        }
        
       

        textoADevolver = textoValor + " de " + palo.toString().toLowerCase();//Convertir a minusculas el Enum Palo

        return textoADevolver;
    }
  
    public boolean ganaA(Carta cartaACompararPreviamenteTirada, Palo paloQuePinta)
    {
        boolean gana = false;
    
        if (palo == cartaACompararPreviamenteTirada.getPalo()) {
            // En caso de que tengan el mismo palo...
            if (getPosicionEscalaTute() > cartaACompararPreviamenteTirada.getPosicionEscalaTute()) {
                gana = true;
            }
        }
        else {
            // En caso de que tengan distinto palo...
            if (palo == paloQuePinta) {
                gana = true;
            }
        } 
    
        return gana;
    }
  
  
    public int getPosicionEscalaTute() 
    {
      int posicion = valor;
    
      if (valor == 3) {
          posicion = 13;
        }
        else if (valor == 1) {
            posicion = 14;
        }

        return posicion;    
    } 
  
  
  
  
  
}



















