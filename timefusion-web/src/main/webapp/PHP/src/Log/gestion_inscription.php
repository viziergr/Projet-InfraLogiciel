<?php

require __DIR__ . '\..\bootstrap.php';

if (isset($_POST['inscription_submit']) && $_POST['inscription_submit'] == 2) {
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

        // Vérification de l'existence de l'email dans la base de données
        $check_email_query = "SELECT * FROM User WHERE email = '$mail_escaped'";
        $email_result = $mysqli->query($check_email_query);

        if ($email_result->num_rows > 0) {
            // L'email existe déjà dans la base de données
            echo "Cet e-mail est déjà associé à un compte existant.";
        }else{
            if ($pwd == $cpwd) {
                // Requête SQL pour insérer l'étudiant dans la table
                $id_defined = false;
                while (!$id_defined){
                    try {
                        $id = rand();
                        $sql = "INSERT INTO User (id, first_name, last_name, email, username password, year) VALUES ('$id','$nom', '$prenom', '$email', '$pwd', '$annee')";
                        $mysqli->query($sql);
                        $id_defined = true;
                        echo "Inscrit";
                    } catch (mysqli_sql_exception $e) {
                        echo "". $e->getMessage() ."";
                    }
                }

            } else {
                echo "Le mot de passe n'est pas le même.";
            }
        }
    } else {
        echo "Tous les champs du formulaire doivent être remplis.";

        //Affichage de l'état de chaque condition
        echo "isset(fname): " . isset($_POST['fname']) . "<br>";
        echo "isset(lname): " . isset($_POST['lname']) . "<br>";
        echo "isset(year): " . isset($_POST['year']) . "<br>";
        echo "isset(mail): " . isset($_POST['mail']) . "<br>";
        echo "isset(pwd): " . isset($_POST['pwd']) . "<br>";
        echo "isset(cpwd): " . isset($_POST['cpwd']) . "<br>";
    }

    $mysqli->close();
}

?>