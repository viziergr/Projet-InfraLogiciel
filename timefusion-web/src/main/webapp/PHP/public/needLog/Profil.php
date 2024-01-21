<?php require __DIR__ . '/../../src/Log/gestion_inscription.php'; ?>
<?php require_once __DIR__ . '/../../src/bootstrap.php'; ?>
<!DOCTYPE html>
<html lang=en>
<head>
    <title>Profil</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="/../../CSS/code.css">
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

        $mysqli = connectDB();
        sess_exists();

        $id =  $_SESSION['compte'];

        $sql = "SELECT * FROM user WHERE id = $id";
        $result = mysqli_query($mysqli, $sql);

        if ($result && mysqli_num_rows($result) > 0) {
            $row = mysqli_fetch_assoc($result);
            $prenom = $row['first_name'];
            $nom = $row['last_name'];
            $email = $row['email'];
            $date = $row['year'];

            echo '<p id="fname"> Prénom : ' . $prenom . '</p>';
            echo '<p id="lname"> Nom : ' . $nom . '</p>';
            echo '<p id="email"> Email : ' . $email . '</p>';
            echo '<p id="year"> Date : ' . $date . '</p>';
        }

        $mysqli->close();
        ?>

        <div class="modif">
            <a href="newPassword.php">
                <p> Modifier votre mot de passe </p>
            </a>
        </div>
    </div>

</body>
</html>
