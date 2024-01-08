<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Agenda de la semaine</title>
    <style>
        /* Styles CSS */
        body {
            font-family: Arial, sans-serif;
            padding: 20px;
        }

        .agenda {
            display: grid;
            grid-template-columns: 100px repeat(7, 1fr);
            grid-gap: 1px;
        }

        .heure {
            border: 1px solid #ccc;
            background-color: #f2f2f2;
            text-align: center;
            padding: 10px;
        }

        .case {
            /* Ajoutez les styles pour améliorer l'apparence de la case */
            border: 1px solid #ccc;
            min-height: 50px;
            position: relative;
            display: flex;
            align-items: center;
            justify-content: center;
            cursor: pointer;
        }

        .evenement {
            /* Styles pour l'affichage de l'événement dans la case */
            background-color: #ffcc00; /* Couleur de fond attrayante */
            color: #000; /* Couleur du texte */
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* Ombre légère */
            padding: 5px;
            text-align: left;
            width: 80%;
        }
    </style>
</head>
<body>

<h2>Agenda de la semaine</h2>

<div class="agenda">
    <div class="heure"></div>
    <div class="jour">Lundi</div>
    <div class="jour">Mardi</div>
    <div class="jour">Mercredi</div>
    <div class="jour">Jeudi</div>
    <div class="jour">Vendredi</div>
    <div class="jour">Samedi</div>
    <div class="jour">Dimanche</div>

    <!-- Boucle pour les heures de travail -->
    <?php
    // Définir les heures de début et de fin de l'agenda (modifiable selon vos besoins)
    $heures_debut = 8; // Heure de début
    $heures_fin = 18; // Heure de fin

    for ($heure = $heures_debut; $heure <= $heures_fin; $heure++) {
        echo "<div class='heure'>$heure:00</div>";

        // Ajouter les cellules vides pour chaque jour de la semaine
        for ($jour = 1; $jour <= 7; $jour++) {
            echo "<div class='case' data-jour='$jour' data-heure='$heure'></div>";
        }
    }
    ?>
</div>

<script>
    // Fonction pour générer une couleur aléatoire au format RGB
    function couleurAleatoire() {
        const r = Math.floor(Math.random() * 256);
        const g = Math.floor(Math.random() * 256);
        const b = Math.floor(Math.random() * 256);
        return `rgb(${r},${g},${b})`;
    }

    // Récupère toutes les cases de l'agenda
    const cases = document.querySelectorAll('.case');

    // Ajoute un événement de clic à chaque case
    cases.forEach(caseElement => {
        caseElement.addEventListener('click', () => {
            const jour = caseElement.getAttribute('data-jour');
            const heure = caseElement.getAttribute('data-heure');

            // Crée une boîte de dialogue pour saisir les informations de l'événement
            const createur = prompt("Nom du créateur de l'événement :");
            const date = prompt('Date de l\'événement (ex: 25 décembre 2023) :');
            const description = prompt('Description de l\'événement :');

            // Génère une couleur aléatoire
            const couleur = couleurAleatoire();

            // Affiche l'événement dans la case avec une couleur aléatoire
            if (createur && date && description) {
                const evenementHTML = `
                    <div class="evenement" style="background-color: ${couleur};">
                        <strong>Créateur :</strong> ${createur}<br>
                        <strong>Date :</strong> ${date}<br>
                        <strong>Description :</strong> ${description}
                    </div>
                `;
                caseElement.innerHTML = evenementHTML;
            }
        });
    });
</script>
</body>
</html>
