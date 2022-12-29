package classes;
import java.util.*;
import java.lang.*;
public class TicTac {
	    private  int[][] matriceJeu;
	    private final  int WIDTH = 3;
	    private final  int HEIGHT = 3;

	    public TicTac() {
	        this.matriceJeu = new int[WIDTH][HEIGHT];
	        for (int i = 0; i < HEIGHT; i++) {
	            for (int j = 0; j < WIDTH; j++) {
	                matriceJeu[i][j]=0;
	            }
	        }
	    }

	    public int[][] getMatriceJeu() {
	        return matriceJeu;
	    }

	    public boolean jouer(boolean typeJoueur,int colonne,int[][] matrice){
	        int joueurValeur = typeJoueur?1:2;
	        int l;
	        if(colonne<0 || colonne>= WIDTH){
	            return false;
	        }

	        for ( l = HEIGHT-1; l >= 0; l--) {
	            if(matrice[l][colonne]==0){
	                break;
	            }
	        }
	        //System.out.println("le l="+l);
	        if(l>=0){
	            matrice[l][colonne]=joueurValeur;
	            return true;
	        }
	        return  false;
	    }

	    public boolean jouer(boolean typeJoueur,int colonne){
	       return jouer(typeJoueur,colonne,matriceJeu);
	    }



	    public boolean estFinJeu(boolean typeJoueur,int[][] matrice){
	        Noeud n = new Noeud(matrice,typeJoueur);
	        return  n.estFinJeu();
	    }
	    public boolean finJeu(){
	        return estFinJeu(true,matriceJeu) || estFinJeu(false,matriceJeu)  ;
	    }
	    public void affichage () {
            System.out.print("["+"\n");
   		for(int i=0;i<this.HEIGHT;i++) {
   			for(int j=0;j<this.WIDTH;j++) {
   					System.out.print(this.matriceJeu[i][j]+"\t");
   					
   			}
   			System.out.print("\n");
   		}
   		System.out.print("]"+"\n");
   	}
	    public void copieMatrice(int[][] mSource,int[][] mDest){
	        for (int i = 0; i < HEIGHT; i++) {
	            for (int j = 0; j < WIDTH; j++) {
	                mDest[i][j] = mSource[i][j];
	            }
	        }
	    }

	    public Coup alpha_beta(Noeud n,int alpha,int beta,int profondeur){
	        /*Random r = new Random();
	        int j = r.nextInt(WIDTH);
	        return  new Coup(0,j);*/

	       // System.out.println(toString(n.getMatrice()));


	        if(profondeur==0 || estFinJeu(! n.isMax(),n.getMatrice())){
	            n.evaluer();
	            return  new Coup(n.getH(),-1);
	        }
	        else if (n.isMax()==true) {
	            int bestC=0;
	            for (int c = 0; c < WIDTH; c++) {

	                int[][] cp= new int[5][5];
	                copieMatrice(n.getMatrice(),cp);


	                if(jouer(true,c,cp)){

	                    Noeud successeur = new Noeud(cp,false);
	                    Coup coup = alpha_beta(successeur,alpha,beta,profondeur-1);




	                    if(coup.getEval() > alpha){
	                        alpha=coup.getEval();
	                        bestC=c;
	                    }
	                    if(alpha> beta){
	                       // System.out.println("test 2 :" +(alpha> beta));
	                        return  new Coup(alpha,bestC);
	                    }
	                }


	            }
	            //System.out.println(bestC+"  ");

	            return  new Coup(alpha,bestC);
	        }
	        else{
	           int  bestC=0;
	            for (int c = 0; c < WIDTH; c++) {
	                int[][] cp= new int[5][5];
	                copieMatrice(n.getMatrice(),cp);
	                if(jouer(false,c,cp)){
	                    Noeud successeur = new Noeud(cp,true);
	                    Coup coup = alpha_beta(successeur,alpha,beta,profondeur-1);
	                    if(coup.getEval() < beta){
	                        beta=coup.getEval();
	                        bestC=c;
	                    }
	                    if(beta < alpha){
	                        return  new Coup(beta,bestC);
	                    }
	                }
	            }

	            return  new Coup(beta,bestC);
	        }
	    }
}
