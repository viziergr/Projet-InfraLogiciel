<?php

//Inclure la connexion à la BDD
require_once("connexion_bdd.php");

session_start();

// Si le formulaire est soumis pour connexion
if ($_SERVER['REQUEST_METHOD'] === 'POST' && isset($_POST['connexion_submit']) && $_POST['connexion_submit'] == 1) {
    // Établir la connexion à la base de données
    $mysqli = connectDB();

    // Code de traitement du formulaire de connexion ici
    $mail_escaped = $mysqli->real_escape_string(trim($_POST['mail']));
    $password_escaped = $mysqli->real_escape_string(trim($_POST['password']));
    $sql = "SELECT id
                FROM User
                WHERE mail = '" . $mail_escaped . "'
                AND pwd = '" . $password_escaped . "'";

    $result = $mysqli->query($sql);
    if (!$result) {
        exit($mysqli->error);
    }

    $nb = $result->num_rows;
    if ($nb) {
        //récupération de l’id de l’étudiant
        $row = $result->fetch_assoc();
        $_SESSION['compte'] = $row['id'];

        $sqlEtudiant = "SELECT id, nom, prenom, email, motDePasse, anneeScolaire FROM Etudiant WHERE id = {$row['id']}";
        $resultEtudiant = $mysqli->query($sqlEtudiant);
        $nbEtu = $resultEtudiant->num_rows;
        if ($nbEtu) {
            $rowEtu = $resultEtudiant->fetch_assoc();

            // Affichage des informations de l'étudiant
            if (isset($rowEtu['nom'])) {
                //echo "Nom de l'étudiant : " . $rowEtu['nom'] . "<br>";
                $nom = $rowEtu['nom'];
            }
            if (isset($rowEtu['prenom'])) {
                //echo "Prenom de l'étudiant : " . $rowEtu['prenom'] . "<br>";
                $prenom = $rowEtu['prenom'];
            }
            if (isset($rowEtu['email'])) {
                //echo "Email de l'étudiant : " . $rowEtu['email'] . "<br>";
            }
            if (isset($rowEtu['motDePasse'])) {
                //echo "Mot de passe de l'étudiant : " . $rowEtu['motDePasse'] . "<br>";
            }

            // Récupération de l'année scolaire via la clé étrangère
            if (isset($rowEtu['anneeScolaire'])) {
                $idAnneeScolaire = $rowEtu['anneeScolaire'];
                $sqlAnneeScolaire = "SELECT nom FROM AnneeScolaire WHERE idAnneeScolaire = $idAnneeScolaire";

                //echo "ID de l'année : " . $idAnneeScolaire . "<br>";
                $resultAnneeScolaire = $mysqli->query($sqlAnneeScolaire);
                $nbAnnee = $resultAnneeScolaire->num_rows;
                if ($nbAnnee) {
                    $rowAnneeScolaire = $resultAnneeScolaire->fetch_assoc();

                    if (isset($rowAnneeScolaire['nom'])) {
                        //echo "Année scolaire de l'étudiant : " . $rowAnneeScolaire['nom'] . "<br>";
                    }
                }
            }
        }
    }

    // Fermer la connexion après avoir terminé le traitement
    $mysqli->close();
}

?>