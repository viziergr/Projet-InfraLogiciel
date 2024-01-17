<?php

require_once("../bootstrap.php");

if (isset($_POST['ajout_equipe_submit']) && $_POST['ajout_equipe_submit'] == 2) {
    //Gestion de l'inscription
    $mysqli = connectDB();

    if (
        isset($_POST['nom_equipe']) && isset($_POST['id_equipe']) && isset($_POST['id_projet']) &&
        isset($_POST['id_user'])
    ) {

        $nom_equipe = $_POST['nom_equipe'];
        $id_equipe = $_POST['id_equipe'];
        $id_projet = $_POST['id_projet'];
        $id_user = $_POST['id_user'];

        $nom_equipe_escaped = $mysqli->real_escape_string(trim($nom_equipe));
        $id_equipe_escaped = $mysqli->real_escape_string(trim($id_equipe));
        $id_projet_escaped = $mysqli->real_escape_string(trim($id_projet));
        $id_user_escaped = $mysqli->real_escape_string(trim($id_user));

        // Vérification de l'existence de l'email dans la base de données
        $check_id_equipe_query = "SELECT * FROM Equipe WHERE id_equipe = '$id_equipe_escaped'";
        $id_equipe_result = $mysqli->query($check_id_equipe_query);

        if ($id_equipe_result->num_rows > 0) {
            // L'email existe déjà dans la base de données
            echo "Cet id d'équipe est déjà associé à un compte existant.";
        }else{
            // Requête SQL pour insérer l'étudiant dans la table
            $id_defined = false;
            while (!$id_defined){
                try {
                    $sql = "INSERT INTO Equipe (nom_equipe, id_equipe, id_projet, id_user) VALUES ('$nom_equipe_escaped', '$id_equipe_escaped', '$id_projet_escaped', '$id_user_escaped')";
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