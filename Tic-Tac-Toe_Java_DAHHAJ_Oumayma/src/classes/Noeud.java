package classes;

import java.util.Arrays;
//dans cette exercice je vais consédirée que 1 est le O et 2 est le X
public class Noeud {
    private  int[][] matrice;
    private  boolean max;
    private  int h;

    private  final int  width = 3;
    private  final int  height = 3;


    public Noeud(int[][] matrice, boolean max) {
        this.matrice = matrice;
        this.max = max;
        this.h=0;
    }

    public int[][] getMatrice() {
        return matrice;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public boolean isMax() {
        return max;
    }

    //cete fonction d'afficher la matrice 
 
    public void affichage () {
             System.out.print("["+"\n");
    		for(int i=0;i<this.height;i++) {
    			for(int j=0;j<this.width;j++) {
    					System.out.print("\t"+this.matrice[i][j]);
    					
    			}
    			System.out.print("\n");
    		}
    		System.out.print("]"+"\n");
    	}
    

    //cette fonction permet de Vérifier si n pions sont alignés en ligne ou en colonne (selon le cas ) pour le joueur typeJoueur
    //le n est soit 1 soit 2 soit 3
    
    public boolean nAlignes(int index,int n,boolean typeJoueur,boolean ligne) {
        int joueurValeur = typeJoueur?1:2;
       boolean algn=false;

        if(ligne) {
            for (int i = 0; i < width-n+1; i++) {
                algn=true;
                for (int j = i; j < i+n ; j++) {
                    if(matrice[index][j]!=joueurValeur){
                        algn=false;
                    }
                }
                if(algn){
                    return  true;
                }
            }
        }
        else {
            for (int i = 0; i < height-n+1; i++) {
                algn=true;
                for (int j = i; j < i+n ; j++) {
                    if(matrice[j][index]!=joueurValeur){
                        algn=false;
                    }
                }
                if(algn){
                    return  true;
                }
            }

        }

        return  false;
}

  //vérifier si il ya des pions qui sont alignés en meme diagonale
    public int troisPionsAlignesDiagonale(Boolean typeJoueur) {
		String diag="";
		int eval =0;
        for(int i=0;i<matrice.length;i++) {
			 for(int j=0;j<matrice[i].length;j++) {
				 if(i==j) {
					 diag+=this.matrice[i][j];
				 }
				 }
			 if(typeJoueur==true) {
					 if(diag.contains("111")) 
						 {
						 eval=30;
						 }
					  }
			 if(typeJoueur==false) {
					 if(diag.contains("222")) {
						 eval=10000;
					 };
					  }
				
        }
        for(int i=matrice.length-1;i<=0;i++) {
			 for(int j=matrice[i].length-1;j<=0;j++) {
				 if(i==j) {
					 diag+=this.matrice[i][j];
				 }
				 }
			 if(typeJoueur==true) {
					 if(diag.contains("111")) 
						 {
						 eval=30;
						 }
					  }
			 if(typeJoueur==false) {
					 if(diag.contains("222")) {
						 eval=10000;
					 };
					  }
				
       }
        return eval;
		}
        

    //Evaluer la possibilité d'aligner n pions
    //n pions =1 ou 2 ou 3

    public int evaluatePossibleAlignesNpions(int n,boolean typeJoueur,boolean ligne){
        int joueurValeur = typeJoueur?1:2,value=0;
        boolean algn=false;

        if(ligne) {
            for (int k = n-1; k >0 ; k--) {
                for (int l = 0; l < height; l++) { // en utilisant le hight on va  parcourourir les lignes

                    for (int i = 0; i < width-k+1; i++) {
                        algn=true;
                        for (int j = i; j < i+k ; j++) {
                            if(matrice[l][j]!=joueurValeur){
                                algn=false;
                            }
                        }
                        if(algn){
                            if(i-1>=0 && matrice[l][i-1]==0){
                                value = value+ (10*(k+1));
                            }
                            if(i+k< width && matrice[l][i+k]==0){
                                value = value+ (10*(k+1));
                            }
                        }
                    }

                }

            }

        }
        else {
            for (int k = n-1; k >0 ; k--) {
                for (int c = 0; c < width; c++) { // dans ce cas et avec le width on va parcourir les colonnes

                    for (int i = 0; i < width-k+1; i++) {
                        algn=true;
                        for (int j = i; j < i+k ; j++) {
                            if(matrice[j][c]!=joueurValeur){
                                algn=false;
                            }
                        }
                        if(algn){
                            if(i-1>=0 && matrice[i-1][c]==0){
                                value = value +  (100*k*(k+1)) ;
                            }
                            if(i+k< height && matrice[i+k][c]==0){
                                value = value+ (100*k*(k+1));
                            }
                        }
                    }

                }

            }

        }

        return value;
    }
//vérifier si il ya trois pions alignés sur la meme ligne selon le type de joueur
    public  int troisPionsAlignesLigne(boolean typeJoueur) {{
    	        for (int i = 0; i <height; i++) {
    	            if(nAlignes(i,3,typeJoueur,false)){
    	                if(typeJoueur==true) {
    	                	return 30;
    	                }
    	                else {
                    		return -30;
                    	}
    	            }
    	        }
    	        return  0;
    	    }
    }
    ////vérifier si il ya trois pions alignés sur la meme colonne selon le type de joueur
    public  int troisPionsAlignesColonne(boolean typeJoueur) {
    	for (int i = 0; i <width; i++) {
            if(nAlignes(i,3,typeJoueur,false)){
                if(typeJoueur==true) {
                	return 30;
                }
                else {
            		return -30;
            	}
            }
        }
        return  0;
    }
    //vérifier la possibilité d'aligner 3 pions pour le joueurtypeJoueur 

    public int troisPionsPossiblesLigne(Boolean typeJoueur) {
    	String str="";
    	int eval=0;
		String[] tab1 = new String[]{"110","101","011","10","01"};
		String[]tab2=new String[]{"220","202","022","20","02"};
		
		for(int i=0;i<matrice.length;i++) {
			 str="";
			for(int j=0;j<matrice[i].length;j++) {
				str+=this.matrice[i][j];}
				if(typeJoueur==true) {
					for(int k=0;k<tab1.length;k++) {
						if(k<3)
						{
							if(str.contains(tab1[k])) 
								//score 9 va etre affecté si c'est le cas de maximisation
								//c'est le cas de 2 pions et une position vide.
								eval+=9;
	   					  }
						else {
							if(str.contains(tab1[k])) 
						  //score 9 va etre affecté si 
						  //c'est le cas de maximisation 1 pion et deux positions vides.
								eval+=6;
						}}}
					 
			      if(typeJoueur==false) {
				    for(int k=0;k<tab2.length;k++) {
				if(k<3)
				{
					if(str.contains(tab2[k])) 
						eval-=9;
					  }
				else {
					if(str.contains(tab2[k])) 
						eval-=6;}
				}
				    }
			      }
		return eval;
    }
    public int troisPionsPossiblesDiagonale(Boolean typeJoueur) {
    	String[] tab1 = new String[]{"110","101","011","10","01"};
		String[]tab2=new String[]{"220","202","022","20","02"};
    	String str="";
		int eval =0;
        for(int i=0;i<matrice.length;i++) {
			 for(int j=0;j<matrice[i].length;j++) {
				 if(i==j) {
					 str+=this.matrice[i][j];
				 }
			 }
        }
        if(typeJoueur==true) {
			for(int k=0;k<tab1.length;k++) {
				if(k<3)
				{
					if(str.contains(tab1[k])) 
						eval+=9;
					  }
				else {
					if(str.contains(tab1[k])) 
						eval+=6;
				}}}
			 
	      if(typeJoueur==false) {
		    for(int k=0;k<tab2.length;k++) {
		if(k<3)
		{
			if(str.contains(tab2[k])) 
				eval-=9;
			  }
		else {
			if(str.contains(tab2[k])) 
				eval-=6;}
		}
		    }
	  
        return eval;
		}
    	
    public int troisPionsPossiblesColonne(Boolean typeJoueur) {
    	String str="";
    	int eval=0;
		String[] tab1 = new String[]{"110","101","011","10","01"};
		String[]tab2=new String[]{"220","202","022","20","02"};
		
		for(int i=0;i<matrice.length;i++) {
			 str="";
			for(int j=0;j<matrice[i].length;j++) {
				str+=this.matrice[j][i];}
				if(typeJoueur==true) {
					for(int k=0;k<tab1.length;k++) {
						if(k<3)
						{
							if(str.contains(tab1[k])) 
								eval+=9;
	   					  }
						else {
							if(str.contains(tab1[k])) 
								eval+=6;
						}}}
					 
			      if(typeJoueur==false) {
				    for(int k=0;k<tab2.length;k++) {
				if(k<3)
				{
					if(str.contains(tab2[k])) 
						eval-=9;
					  }
				else {
					if(str.contains(tab2[k])) 
						eval-=6;}
				}
				    }
			      }
		
		return eval;
    }
    public void evaluer(){
        h=troisPionsAlignesLigne(false)
                + troisPionsAlignesLigne(true)
                + troisPionsAlignesColonne(false)
                +troisPionsAlignesColonne(true)
                +troisPionsPossiblesLigne(false)
                +troisPionsPossiblesLigne(true)
                +troisPionsPossiblesColonne(false)
                + troisPionsPossiblesColonne(true)
                +troisPionsAlignesDiagonale(false)
                +troisPionsAlignesDiagonale(true);
        System.out.println("Votre evaluation est \t"+h);
    }

    public boolean estFinJeu(){
    	
    		 if(troisPionsAlignesLigne(max)==30 || troisPionsAlignesColonne(max)==30||troisPionsAlignesLigne(max)==-30|| troisPionsAlignesColonne(max)==-30){
    	            return true;
    	        } 
        boolean fin=true;

        for (int j = 0; j < width; j++) {
            if(matrice[0][j]==0)
                fin=false;
        }

        return  fin;
    }
    

    public  boolean gagne(boolean typeJoueur){
    	if(max==true) {
    		if(troisPionsAlignesLigne(typeJoueur)==30 || troisPionsAlignesColonne(typeJoueur)==-30){
                return true ;
            }
   	}
   	else if(max==true) {
   		if(troisPionsAlignesLigne(typeJoueur)==30 || troisPionsAlignesColonne(typeJoueur)==-30){
            return true ;
        }
	}
    	
        
        return false;
    }
	
}

