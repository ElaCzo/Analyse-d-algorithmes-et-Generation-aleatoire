package TernaryTree;

public class Arbre{
    public static int numero = 0;
    public int id;
    public int valeur;
    public char cle;
    public Arbre[] fils;

    public Arbre(Integer valeur, char cle, Arbre[] fils){
        this.id = numero;
        numero++;
        this.cle = cle;
        this.valeur = valeur;
        this.fils = fils;

    }

    public static Arbre generArbre(int valeur, char cle, Arbre[] fils) {
        return new Arbre(valeur, cle, fils);
    }

    public static Arbre generFeuille() {
        return new Arbre(null, (char) 0, new Arbre[3]);
    }

    public static Arbre construction(String mot){
        if(mot == ""){
            return generFeuille();
        } else {
            Arbre[] tmp = new Arbre[3];
            if(mot.length() == 1){
                tmp[0] = generFeuille();
                tmp[1] = generFeuille();
                tmp[2] = generFeuille();
                return generArbre(0, mot.charAt(0), tmp);
            } else {
                tmp[0] = generFeuille();
                tmp[1] = construction(mot.substring(1));
                tmp[2] = generFeuille();
                return generArbre(-1, mot.charAt(0), tmp);
            }
        }
    }

    public static Arbre insert(Arbre tree, String mot) {
        if ( mot == "" ) {
            return tree;
        }
        if (tree.cle == (char)0 ){
            return construction(mot);
        }
        Arbre[] tmp = new Arbre[3];
        if( tree.cle > mot.charAt(0)){
            tmp[0] = insert(tree.fils[0], mot);
            tmp[1] = tree.fils[1];
            tmp[2] = tree.fils[2];
            return generArbre(tree.valeur, tree.cle, tmp);
        } else if (tree.cle == mot.charAt(0)){
            int valeur = tree.valeur;
            if (mot.length() == 1 ){
                valeur = 0;
            }
            tmp[0] = tree.fils[0];
            tmp[1] = insert(tree.fils[1], mot.substring(1));
            tmp[2] = tree.fils[2];
            return generArbre(valeur, tree.cle, tmp);
        } else {
            tmp[0] = tree.fils[0];
            tmp[1] = tree.fils[1];
            tmp[2] = insert(tree.fils[2], mot);
            return generArbre(tree.valeur, tree.cle, tmp);
        }
    }

    public static Arbre fusion(Arbre A, Arbre B){
        if (A.cle == (char)0 ){
            return B;
        }
        if (B.cle == (char)0 ){
            return A;
        }

        Arbre[] tmp = new Arbre[3];
        if ( A.cle < B.cle ){
            tmp[0] = A.fils[0];
            tmp[1] = A.fils[1];
            tmp[2] = fusion(A.fils[2], B);
            return generArbre(A.valeur, A.cle, tmp);
        }

        if ( A.cle > B.cle ){
            tmp[0] = fusion(A.fils[0], B);
            tmp[1] = A.fils[1];
            tmp[2] = A.fils[2];
            return generArbre(A.valeur, A.cle, tmp);
        }
        int valeur; 
        if(A.valeur != -1){
            valeur = A.valeur;
        } else {
            valeur = B.valeur;
        }
        tmp[0] = fusion(A.fils[0], B.fils[0]);
        tmp[1] = fusion(A.fils[1], B.fils[1]);
        tmp[2] = fusion(A.fils[2], B.fils[2]);
        return generArbre(valeur, A.cle, tmp);
    }
    
}