public class StationVelos {

    int capacite;
    int velosDisponibles;

    StationVelos(int capacite, int velosDisponibles) {
        this.capacite = capacite;
        this.velosDisponibles = velosDisponibles;
    }

    void louer() {
        if (velosDisponibles == 0) {
            throw new IllegalArgumentException("Aucun vélo disponible");
        }
        velosDisponibles = velosDisponibles - 1;
    }

    void rendre() {
        if (velosDisponibles == capacite) {
            throw new IllegalArgumentException("La station est pleine");
        }
        velosDisponibles = velosDisponibles + 1;
    }

    double prix(int minutes, boolean membre) {
        if (minutes <= 0) {
            throw new IllegalArgumentException("La durée doit être positive");
        } else if (membre && minutes <= 45) {
            return 0;
        } else if (membre) {
            return (minutes - 45) * 0.15;
        } else {
            return 1.25 + minutes * 0.15;
        }
    }

    boolean estVide() {
        return velosDisponibles == 0;
    }
}
