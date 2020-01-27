package main.java.TernaryTree;

public class ArbreTernaire {
    private char racine;
    private ArbreTernaire[] children;

    public ArbreTernaire(){
        this.racine = (char) 0 ;
        this.children = new ArbreTernaire[3];
    }

    public ArbreTernaire(char racine, ArbreTernaire[] children){
        this.racine = racine ;
        this.children = children;
    }

    public void addMot(String mot, ArbreTernaire tree){
        if(mot.length() == 0){ // erreur
            System.out.println("Empty word");
            return;
        }

        boolean isLast = (mot.length() == 1);

        char current = mot.charAt(0); // update de la racine pour correspondre au premier caractere du mot en argument

        if( this.racine != (char) 0 ){ // on ajoute un mot a une arbre non vide
            if ( (int) current < (int) this.racine ){
                if ( this.children[0] == null ) {
                    this.children[0] = new ArbreTernaire();
                }
                addMot(mot, this.children[0]);
            } else if ( (int) current == (int) this.racine ) { // Meme caractere a la racine et premiere lettre du mot
                if ( isLast ){ // derniere lettre du mot -> fini
                    System.out.println("The tree is build");
                    return;
                }
                if ( this.children[1] == null ){
                    this.children[1] = new ArbreTernaire();
                }
                addMot(mot.substring(1), this.children[1]); //ajout de la suite du mot
            } else {
                if ( this.children[2] == null ) {
                    this.children[2] = new ArbreTernaire();
                }
                addMot(mot, this.children[2]);
            }
        } else { // ajout dans un arbre vide
            if ( isLast ){
                System.out.println("The tree is build");
                return;
            }
            this.racine = current;
            this.children[1] = new ArbreTernaire();
            addMot(mot.substring(1), this.children[1]);
        }
        return;
    }

    public ArbreTernaire fusionne(ArbreTernaire tree){
        if (this.racine == (char)0 ){
            return tree;
        }
        if (tree.racine == (char)0 ){
            return this;
        }

        ArbreTernaire[] tmp = new ArbreTernaire[3];
        if ( this.racine < tree.racine ){
            tmp[0] = this.children[0];
            tmp[1] = this.children[1];
            tmp[2] = this.children[2].fusionne(tree);
            return new ArbreTernaire(this.racine, tmp);
        }

        if ( this.racine > tree.racine ){
            tmp[0] = this.children[0].fusionne(tree);
            tmp[1] = this.children[1];
            tmp[2] = this.children[2].fusionne(tree);
            return new ArbreTernaire(this.racine, tmp);
        }

        tmp[0] = this.children[0].fusionne(tree.children[0]);
        tmp[1] = this.children[1].fusionne(tree.children[1]);
        tmp[2] = this.children[2].fusionne(tree.children[2]);
        return new ArbreTernaire(this.racine, tmp);
    }
}
