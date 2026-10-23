package fr.univartois.butinfo.fractalens.image;

import java.awt.*;
import java.util.List;

public class PaletteCollections {

    private PaletteCollections() {
    }

    static java.util.List<Color> cosmicPalette = List.of(
            new Color(4, 4, 20),      // 0: Vide abyssal (presque noir)
            new Color(20, 10, 50),    // 1: Violet très sombre
            new Color(55, 15, 90),    // 2: Prune profond
            new Color(100, 20, 110),  // 3: Magenta foncé
            new Color(160, 30, 110),  // 4: Rose framboise
            new Color(220, 50, 90),   // 5: Rose vif
            new Color(250, 100, 60),  // 6: Orange corail
            new Color(255, 170, 40),  // 7: Jaune doré
            new Color(255, 230, 100), // 8: Jaune clair
            new Color(255, 255, 220), // 9: Blanc éclatant (cœur lumineux)
            new Color(180, 240, 255), // 10: Cyan très clair
            new Color(100, 200, 255), // 11: Bleu ciel neon
            new Color(40, 140, 220),  // 12: Bleu azur
            new Color(15, 80, 170),   // 13: Bleu roi
            new Color(10, 40, 100),   // 14: Bleu sombre profond
            new Color(6, 20, 55)      // 15: Transition douce vers le noir
    );

    // 25 couleurs : 250 % 25 == 0, donc l'intérieur de la fractale (max itérations) reste noir
    static java.util.List<Color> auroraPalette = List.of(
            new Color(0, 0, 0),       // 0: Noir (intérieur de la fractale)
            new Color(3, 10, 25),     // 1: Nuit polaire
            new Color(5, 20, 45),     // 2: Bleu nuit
            new Color(6, 35, 65),     // 3: Bleu nuit glacé
            new Color(8, 55, 85),     // 4: Bleu pétrole
            new Color(10, 80, 105),   // 5: Bleu canard
            new Color(12, 110, 120),  // 6: Sarcelle profond
            new Color(18, 140, 128),  // 7: Sarcelle
            new Color(35, 170, 135),  // 8: Vert émeraude
            new Color(70, 200, 145),  // 9: Vert aurore
            new Color(120, 225, 160), // 10: Vert menthe
            new Color(180, 245, 190), // 11: Halo vert pâle
            new Color(230, 255, 235), // 12: Blanc glacé (pic de lumière)
            new Color(210, 230, 255), // 13: Bleu très clair
            new Color(175, 190, 250), // 14: Lavande
            new Color(150, 150, 240), // 15: Pervenche
            new Color(135, 115, 225), // 16: Violet clair
            new Color(125, 85, 205),  // 17: Violet aurore
            new Color(115, 60, 180),  // 18: Améthyste
            new Color(100, 40, 150),  // 19: Violet
            new Color(80, 28, 120),   // 20: Violet profond
            new Color(60, 20, 95),    // 21: Prune
            new Color(40, 14, 70),    // 22: Indigo sombre
            new Color(22, 10, 48),    // 23: Indigo nuit
            new Color(10, 6, 30)      // 24: Retour vers la nuit
    );
}
