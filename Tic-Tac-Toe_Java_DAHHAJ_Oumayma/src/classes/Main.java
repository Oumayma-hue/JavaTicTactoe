package classes;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
	            //effectuer un simple teste sur l'exemple de l'énoncé
				int m[][]= {{0,1,0},{2,1,0},{1,0,0}};
				//creation d'un noeud
				Noeud racine=new Noeud(m,true);
				//teste des methodes
				System.out.println(racine.troisPionsAlignesLigne(true));
				System.out.println(racine.troisPionsAlignesColonne(false));
				System.out.println(racine.troisPionsPossiblesColonne(true));
				System.out.println(racine.troisPionsPossiblesLigne(false));
				System.out.println();
				//l’evaluation de la situation du jeu dans l'exemple
			    racine.evaluer();
			    //teste de l'algorithme alpha et beta 
		       int matrice [][] ={{1,1,1},{2,0,2},{2,2,2}};
	          Noeud nd = new Noeud(matrice,true);
	          System.out.println(nd.nAlignes(2,2,true,true));
	          System.out.println(nd.troisPionsAlignesColonne(true));
	          System.out.println(nd.evaluatePossibleAlignesNpions(2,false,true));
	          System.out.println(nd.estFinJeu());

	       
	         System.out.println(Integer.MIN_VALUE );
	        System.out.println(Integer.MAX_VALUE);
             //creation d'un objet de tic tac
	        TicTac p = new TicTac();
	        //test des autres fonctionalités
	        Coup coup = p.alpha_beta(nd,-10000,10000,2);
	        System.out.println("le coup est\t"+coup);

	       
	        int[][] cp= new int[3][3];
	        Scanner sc = new Scanner(System.in);
	        Noeud n =  new Noeud(p.getMatriceJeu(),true);
	        int player = 1;
	        racine.affichage();
	       
	       
	       p.copieMatrice(p.getMatriceJeu(),cp);
	       while (!p.finJeu()) {
	            //clearConsole();
	            //le temps de jouer avec L'IA
	            if(player==1){
	                p.copieMatrice(p.getMatriceJeu(),cp);
	                Coup c = p.alpha_beta(new Noeud(cp,true),Integer.MIN_VALUE ,Integer.MAX_VALUE,3);
	                System.out.println(c.getEval()+", c="+c.getColonne());
	                p.jouer(true,c.getColonne());
	                p.affichage();
	                player =2;
	                if(n.gagne(true)){
	                    System.out.println("IA a gagné");
	                }
	            }
	            else{
	                System.out.print("Votre coup : ");
	                int j = sc.nextInt();
	                p.jouer(false, j);
	                p.affichage();;
	                player=1;
	                if(n.gagne(false)){
	                    System.out.println("Vous avez gagné...");
	                }
	            }
	        }
			
			
			
			
	}}



