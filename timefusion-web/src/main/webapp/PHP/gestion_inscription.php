<?php

require_once("connexion_bdd.php");

if (isset($_POST['connexion_submit']) && $_POST['connexion_submit'] == 2) {
    //Gestion de l'inscription
    $mysqli = connectDB();

    if (
        isset($_POST['fname']) && isset($_POST['lname']) && isset($_POST['year']) &&
        isset($_POST['mail']) && isset($_POST['pwd']) && isset($_POST['cpwd'])
    ) {

        $nom = $_POST['fname'];
        $prenom = $_POST['lname'];
        $annee = $_POST['year'];
        $email = $_POST['mail'];
        $pwd = $_POST['pwd'];
        $cpwd = $_POST['cpwd'];

        $mail_escaped = $mysqli->real_escape_string(trim($email));
        $password_escaped = $mysqli->real_escape_string(trim($pwd));

        if ($pwd == $cpwd) {
            // Requête SQL pour insérer l'étudiant dans la table
            $date = date('Y-m-d');
            $photo = strtolower($prenom) . ".jpg";

            switch ($annee) {
                case "1":
                    $description = "Étudiant en E1";
                    break;
                case "2":
                    $description = "Étudiant en E2";
                    break;
                case "3":
                    $description = "Étudiant en E3e";
                    break;
                case "4":
                    $description = "Étudiant en E4e";
                    break;
                case "5":
                    $description = "Étudiant en E5e";
                    break;
                default:
                    $description = "";
                    break;
            }


            $sql = "INSERT INTO Etudiant (nom, prenom, email, motDePasse, anneeScolaire, dateIns, dateModif, description,photo) VALUES ('$nom', '$prenom', '$email', '$pwd', '$annee','$date','$date','$description','$photo')";

            if ($mysqli->query($sql)) {
                // L'étudiant a été inscrit avec succès
                //echo "L'étudiant a été inscrit avec succès.";
            } else {
                //echo "Une erreur s'est produite lors de l'inscription de l'étudiant : " . $mysqli->error;
            }
        } else {
            //echo "Le mot de passe n'est pas le même.";
        }
    } else {
        //echo "Tous les champs du formulaire doivent être remplis.";

        // Affichage de l'état de chaque condition
        //echo "isset(fname): " . isset($_POST['fname']) . "<br>";
        //echo "isset(lname): " . isset($_POST['lname']) . "<br>";
        //echo "isset(year): " . isset($_POST['year']) . "<br>";
        //echo "isset(mail): " . isset($_POST['mail']) . "<br>";
        //echo "isset(pwd): " . isset($_POST['pwd']) . "<br>";
        //echo "isset(cpwd): " . isset($_POST['cpwd']) . "<br>";
    }

    $mysqli->close();
}

?>