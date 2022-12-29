package classes;

public class Coup {
    private   int eval;
    private  int colonne;

    public Coup(int eval, int colonne) {
        this.eval = eval;
        this.colonne = colonne;
    }

    public int getEval() {
        return eval;
    }

    public int getColonne() {
        return colonne;
    }

    @Override
    public String toString() {
        return "v = "+ eval +" c=" + colonne;
    }
}

