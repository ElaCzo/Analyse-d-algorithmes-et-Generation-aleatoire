package main.java.TernaryTree;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class ShakesTree {

    private static String REPOSITORY = "/";
    public static void main(String[] args) {
        String titreLivre = "Shakespeare/Shakespeare/1henryiv.txt";
        int nb = 30;

        String oeuvreShakee = "";

        BufferedReader lecteurAvecBuffer = null;
        String ligne = "";
        try
        {
            lecteurAvecBuffer = new BufferedReader(new FileReader(titreLivre));
        }
        catch(FileNotFoundException exc)
        {
            System.out.println("Erreur d'ouverture");
        }
        try {
            while ((ligne = lecteurAvecBuffer.readLine()) != null)
                oeuvreShakee+=ligne+" ";

        }catch (Exception e){
            System.out.println("Erreur lecture");
        }

        oeuvreShakee = oeuvreShakee.replace(",", "");
        oeuvreShakee = oeuvreShakee.replace(".", "");
        oeuvreShakee = oeuvreShakee.replace("?", "");
        oeuvreShakee = oeuvreShakee.replace("!", "");
        // l'oeuvre est nettoyée partiellement de la ponctuation

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

        Arbre fusionnes1 = new Arbre();
        Arbre fusionnes2 = new Arbre();
        Arbre fusionnes;
        Arbre inseres = new Arbre();
        for(int i=0; i<baseMots.length; i++) {
            if (i<baseMots.length/2)
                fusionnes1 = Arbre.fusion(fusionnes1, Arbre.insert(new Arbre(), baseMots[i]));
            else
                fusionnes2 = Arbre.fusion(fusionnes2, Arbre.insert(new Arbre(), baseMots[i]));

            inseres = Arbre.insert(inseres, baseMots[i]);
        }

        fusionnes = Arbre.fusion(fusionnes1, fusionnes2);

        System.out.println(inseres);
        System.out.println(fusionnes);
        System.out.println("Est-ce que la fusion fonctionne : "+areTreesEqual(fusionnes, inseres));

    }

    public static boolean isFusionBugged(String s1, String s2){
        String mot1 = s1;
        String mot2 = s2;
        Arbre normal = new Arbre();
        normal=Arbre.insert(normal, mot1);
        normal=Arbre.insert(normal, mot2);

        Arbre fusionne1 = new Arbre();
        fusionne1=Arbre.insert(fusionne1, mot1);
        Arbre fusionne2 = new Arbre();
        fusionne2=Arbre.insert(fusionne2, mot2);


        System.out.println(normal);
        Arbre fusionne = Arbre.fusion(fusionne1, fusionne2);

        System.out.println(fusionne);
        return normal.toString().equals(fusionne.toString());
    }

    public static boolean areTreesEqual(Arbre a1, Arbre a2){
        return a1.toString().equals(a2.toString());
    }

}