import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Tests de la station de vélos")
class StationVelosTest {

    StationVelos station;

    @BeforeEach
    void preparer() {
        station = new StationVelos(10, 6);
    }

    @ParameterizedTest
    @DisplayName("Le prix dépend de la durée et du statut de membre")
    @CsvSource({
        "30, true, 0.0",
        "45, true, 0.0",
        "46, true, 0.15",
        "60, true, 2.25",
        "1, false, 1.40",
        "10, false, 2.75"
        })
        void calculDuPrix(int minutes, boolean membre, double prixAttendu) {
            assertEquals(prixAttendu, station.prix(minutes, membre), 0.01);
        }

    @Test
    @DisplayName("Louer un vélo laisse 5 vélos disponibles")
    void louerUnVeloRetireUnVeloDisponible() {
        station.louer();
        assertEquals(5, station.velosDisponibles);
    }

    @Test
    @DisplayName("La station de départ (6 vélos) n'est pas vide")
    void stationDeDepartNEstPasVide() {
        assertFalse(station.estVide());
    }

    @Test
    @DisplayName("Rendre un vélo donne 7 vélos disponibles")
    void rendreUnVeloAjouteUnVeloDisponible() {
        station.rendre();
        assertEquals(7, station.velosDisponibles);
    }

    @Test
    @DisplayName("Louer le dernier vélo rend la station vide")
    void louerLeDernierVeloRendLaStationVide() {
        StationVelos petiteStation = new StationVelos(10, 1);
        petiteStation.louer();
        assertTrue(petiteStation.estVide());
    }

    @Test
    @DisplayName("Rendre un vélo dans une station de 9 vélos la remplit")
    void rendreUnVeloDansStationPresquePleineLaRemplit() {
        StationVelos stationPresquePleine = new StationVelos(10, 9);
        stationPresquePleine.rendre();
        assertEquals(10, stationPresquePleine.velosDisponibles);
    }

    @Test
        @DisplayName("Louer dans une station vide est refusé")
        void louerDansStationVideEstRefuse() {
            StationVelos stationVide = new StationVelos(10, 0);

            IllegalArgumentException erreur = assertThrows(IllegalArgumentException.class, () -> {
                stationVide.louer();
            });

            assertEquals("Aucun vélo disponible", erreur.getMessage());
        }

        @Test
        @DisplayName("Rendre dans une station pleine est refusé")
        void rendreDansStationPleineEstRefuse() {
            StationVelos stationPleine = new StationVelos(10, 10);

            IllegalArgumentException erreur = assertThrows(IllegalArgumentException.class, () -> {
                stationPleine.rendre();
            });

            assertEquals("La station est pleine", erreur.getMessage());
        }

        @ParameterizedTest
        @DisplayName("Une durée nulle ou négative est refusée")
        @ValueSource(ints = {0, -1, -30})
        void dureeInvalideEstRefusee(int minutes) {
            IllegalArgumentException erreur = assertThrows(IllegalArgumentException.class, () -> {
                station.prix(minutes, true);
            });

            assertEquals("La durée doit être positive", erreur.getMessage());
        }

        @Test
        @Disabled("Règle à venir : 60 minutes gratuites pour les membres (pas encore programmée)")
        @DisplayName("Nouvelle règle : un membre ne paie rien jusqu'à 60 minutes")
        void prixNouvelleRegleMembre() {
            assertEquals(0.0, station.prix(60, true), 0.01);

        }
}
