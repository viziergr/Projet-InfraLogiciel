<?php

require_once("bootstrap.php");

if (isset($_POST['inscription_submit']) && $_POST['inscription_submit'] == 2) {
    //Gestion de l'inscription
    $mysqli = connectDB();

    if (
        isset($_POST['fname']) && isset($_POST['lname']) && isset($_POST['year']) &&
        isset($_POST['mail']) && isset($_POST['pwd']) && isset($_POST['cpwd'])
    ) {

        $nom = h($_POST['fname']);
        $prenom = h($_POST['lname']);
        $annee = h($_POST['year']);
        $email = h($_POST['mail']);
        $pwd = h($_POST['pwd']);
        $cpwd = h($_POST['cpwd']);

        $mail_escaped = $mysqli->real_escape_string(trim($email));
        $password_escaped = $mysqli->real_escape_string(trim($pwd));

        // Vérification de l'existence de l'email dans la base de données
        $check_email_query = "SELECT * FROM user WHERE email = '$mail_escaped'";
        $email_result = $mysqli->query($check_email_query);

        if ($email_result->num_rows > 0) {
            // L'email existe déjà dans la base de données
            $error = "Cet e-mail est déjà associé à un compte existant.";
        }else{
            if ($pwd == $cpwd) {
                // Requête SQL pour insérer l'étudiant dans la table
                $id_defined = false;
                while (!$id_defined){
                    try {
                        $id = rand();
                        $sql = "INSERT INTO user (id, first_name, last_name, email, password, year) VALUES ('$id','$nom', '$prenom', '$email', '$pwd', '$annee')";
                        $mysqli->query($sql);
                        $id_defined = true;
                        header("Location: ./needLog/Calendrier.php");
                    } catch (mysqli_sql_exception $e) {
                        echo "". $e->getMessage() ."";
                    }
                }

            } else {
                $error = "Le mot de passe n'est pas le même.";
            }
        }
    } else {
        $error = "Tous les champs du formulaire doivent être remplis.";
    }

    $mysqli->close();
}

?>