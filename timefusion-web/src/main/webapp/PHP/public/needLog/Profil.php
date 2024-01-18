<?php include '../src/Log/gestion_inscription.php'; ?>

<?php require __DIR__ . '\..\bootstrap.php'; 
sess_exists();
?>

<!DOCTYPE html>
<html>
<head>
    <title>Profil</title>
    <link rel="stylesheet" href="..\..\CSS\code.css">
</head>
<body>

    <div class="bandeV">
        <div class="dropdown">
            <img id="trbar" src="..\..\pictures\trBarre.png" alt="redirection"/>
            <div class="dropdown-content">
                <p>Team</p>
                <p>Profil</p>
                <p>Calendrier</p>
            </div>
        </div>
        <h1>TimeFusion</h1>
        <a href="index.html">
            <img id ="logo" src="..\..\pictures\Logo.png">
        </a>
    </div>

    <div class="profil">
        <h1>Profil</h1>
    </div>
    
    <div class="information">
        <div class="traitV"></div>

        <?php

        $prenom =  $_SESSION['compte'];
        echo '<p id="fname"> Prénom : ' . $prenom . '</p>';

        $nom = $_SESSION['compte]'];
        echo '<p id="lname"> Nom : ' . $nom . '</p>';

        $email = $_SESSION['compte'];
        echo '<p id="email"> Email : ' . $email . '</p>';

        $annee = $_SESSION['compte'];
        echo '<p id="year"> Année : ' . $annee . '</p>';

        ?>

        <div class="modif">
            <a href="..\..\newPassword.php">
                <p> Modifier votre profil </p>
            </a>
        </div>
    </div>

</body>
</html>
