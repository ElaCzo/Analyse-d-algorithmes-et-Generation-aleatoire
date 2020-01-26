package main.java.TernaryTree;

import java.util.Random;

public class ShakesTree {

    private static String REPOSITORY = "/";
    public static void main(String[] args) {
        String titreLivre = args[0];
        int nb = Integer.parseInt(args[1]);

        String oeuvreShakee = ""; // ici loader dans une String une oeuvre du bon vieux Shakespear

        oeuvreShakee = oeuvreShakee.replace(",", "");
        oeuvreShakee = oeuvreShakee.replace(".", "");
        oeuvreShakee = oeuvreShakee.replace("?", "");
        oeuvreShakee = oeuvreShakee.replace("!", "");
        // l'oeuvre est netoyer partiellement de la ponctuation
        
        String[] splittedOeuvre = oeuvreShakee.split(" ");
        int range = splittedOeuvre.length;

        String[] baseMots = new String[nb];

        for( int i = 0; i < nb; i++){
            Random rand = new Random();
            int indice = rand.nextInt(range);
            while(splittedOeuvre[indice] == null){
                indice = rand.nextInt(range);
            }

            baseMots[i] = splittedOeuvre[indice];
            splittedOeuvre[indice] = null;
        }

        Arbre arbreClassique = new Arbre();
        for( int i = 0 ; i < nb ; i++){
            Arbre.insert(arbreClassique, baseMots[i]);
        }

        ArbreTernaire arbreCustom = new ArbreTernaire();
        for( int i = 0; i< nb; i++ ){
            arbreCustom.addMot(baseMots[i], arbreCustom);
        }

    }

    public static boolean isFusionBugged(){
        String mot1 = "anticonstitutionnellement";
        String mot2 = "antipasti";
        Arbre normal = new Arbre();
        Arbre.insert(normal, mot1);
        Arbre.insert(normal, mot2);

        Arbre fusionne1 = new Arbre();
        Arbre.insert(fusionne1, mot1);
        Arbre fusionne2 = new Arbre();
        Arbre.insert(fusionne2, mot2);

        Arbre fusionne = Arbre.fusion(fusionne1, fusionne2);
        
        // check si les arbres sont pareils

        return false;
    }

}