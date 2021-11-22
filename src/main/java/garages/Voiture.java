package garages;

import java.io.PrintStream;
import java.util.*;

public class Voiture {

    private final String immatriculation;
    private final List<Stationnement> myStationnements = new LinkedList<>();

    public Voiture(String i) {
        if (null == i) {
            throw new IllegalArgumentException("Une voiture doit avoir une immatriculation");
        }

        immatriculation = i;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    /**
     * Fait rentrer la voiture au garage Précondition : la voiture ne doit pas
     * être déjà dans un garage
     *
     * @param g le garage où la voiture va stationner
     * @throws java.lang.Exception Si déjà dans un garage
     */
    public void entreAuGarage(Garage g) throws Exception {
        // Et si la voiture est déjà dans un garage ?
        Stationnement s = new Stationnement(this, g);
        //this = voiture 
        if (this.estDansUnGarage()) {
            throw new IllegalArgumentException("La voiture ne doit pas être déjà dans un garage");
        }
        myStationnements.add(s);
    }

    /**
     * Fait sortir la voiture du garage Précondition : la voiture doit être dans
     * un garage
     *
     * @throws java.lang.Exception si la voiture n'est pas dans un garage
     */
    public void sortDuGarage() throws Exception {
        int taille = myStationnements.size();
        if (this.estDansUnGarage() == false) {
            throw new IllegalArgumentException("La voiture doit être dans un garage");

        } else {
            Stationnement s = myStationnements.get(taille - 1);
            s.terminer();
        }

        // TODO: Implémenter cette méthode
        // Trouver le dernier stationnement de la voiture
        // Terminer ce stationnement
    }

    /**
     * @return l'ensemble des garages visités par cette voiture
     */
    public Set<Garage> garagesVisites() {
        Set<Garage> visite = new HashSet<Garage>();
        for (Stationnement s : myStationnements) {
            visite.add(s.getGarage());

        }
        return visite;
    }

    /**
     * @return vrai si la voiture est dans un garage, faux sinon
     */
    public boolean estDansUnGarage() {
        if (myStationnements.size() == 0) {
            return false;
        } else {

            int taille = myStationnements.size();

            Stationnement s = myStationnements.get(taille - 1);

            return (s.estEnCours());

            // Vrai si le dernier stationnement est en cours
        }
    }

    /**
     * Pour chaque garage visité, imprime le nom de ce garage suivi de la liste
     * des dates d'entrée / sortie dans ce garage
     * <br>Exemple :
     * <pre>
     * Garage Castres:
     *		Stationnement{ entree=28/01/2019, sortie=28/01/2019 }
     *		Stationnement{ entree=28/01/2019, en cours }
     *  Garage Albi:
     *		Stationnement{ entree=28/01/2019, sortie=28/01/2019 }
     * </pre>
     *
     * @param out l'endroit où imprimer (ex: System.out)
     */
    public void imprimeStationnements(PrintStream out) {
        String nom = "";
        for (Garage g : this.garagesVisites()) {
            nom = nom + g + "\n";
            for (Stationnement s : myStationnements) {

                if (s.getGarage() == g) {

                    nom = nom + s + "\n";
                }
            }
        }
        out.println(nom);

    }

}
