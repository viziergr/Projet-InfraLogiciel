<?php

require_once("../bootstrap.php");

if (isset($_POST['ajout_equipe_submit']) && $_POST['ajout_equipe_submit'] == 2) {
    //Gestion de l'inscription
    $mysqli = connectDB();

    if (
        isset($_POST['nomequipe']) && isset($_POST['email']) && isset($_POST['color'])
    ) {

        $nom_equipe = $_POST['nomequipe'];
        $membre_equipe = $_POST['email'];
        $couleur_equipe = $_POST['color'];

        $nom_equipe_escaped = $mysqli->real_escape_string(trim($nom_equipe));
        $membre_equipe_escaped = $mysqli->real_escape_string(trim($membre_equipe));
        $color_escaped = $mysqli->real_escape_string(trim($couleur_equipe));

        // Vérification de l'existence de l'email dans la base de données
        $check_nom_equipe_query = "SELECT * FROM Equipe WHERE nomequipe = '$nom_equipe_escaped'";
        $nom_equipe_result = $mysqli->query($check_nom_equipe_query);

        if ($nom_equipe_result->num_rows > 0) {

            echo "Ce nom d'équipe est déjà associé à un compte existant.";
        }else{

            $id_defined = false;
            while (!$id_defined){
                try {
                    $sql = "INSERT INTO Equipe (nomequipe, email, color) VALUES ('$nom_equipe_escaped', '$membre_equipe_escaped', '$color_escaped')";
                    $mysqli->query($sql);
                    $id_defined = true;
                    echo "Equipe ajoutée";
                } catch (mysqli_sql_exception $e) {
                    echo "". $e->getMessage() ."";
                }
            }
        }
    } else {
        echo "Tous les champs du formulaire doivent être remplis.";

        //Affichage de l'état de chaque condition
        echo "isset(nom_equipe): " . isset($_POST['nom_equipe']) . "<br>";
?>


