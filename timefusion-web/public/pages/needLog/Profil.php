<?php 
include __DIR__ . '/../../scripts/gestion_inscription.php'; 
include __DIR__ .'/../../scripts/Team/Users.php';

sess_exists();

$users = new TimeFusion\Team\Users(connectDB());
$user = $users->getUserById($_SESSION['compte']);
?>

<!DOCTYPE html>
<html>
<head>
    <title>Profil</title>
    <link rel="stylesheet" href="../../../CSS/code.css">
    
</head>
<body>

    <div class="bandeV">
        <div class="dropdown">
            <img id="trbar" src="../../../pictures/trBarre.png" alt="redirection"/>
            <div class="dropdown-content">
                <p>Team</p>
                <p>Profil</p>
                <p>Calendrier</p>
            </div>
        </div>
        <h1>TimeFusion</h1>
        <a href="/PHP/public/index.html">
            <img id ="logo" src="../../../pictures/Logo.png">
        </a>
    </div>

    <div class="profil">
        <h1>Profil</h1>
    </div>
    
    <div class="information">


        <div class="traitV"></div>

        <?php        

        echo '<p id="fname"> Prénom : ' . $user->getFirstName() . '</p>';

        echo '<p id="lname"> Nom : ' . $user->getLastName() . '</p>';

        echo '<p id="email"> Email : ' . $user->getEmail() . '</p>';

        echo '<p id="year"> Date d\'anniversaire : ' . (new \DateTime($user->getYear()))->format('d/m/Y') . '</p>';

        ?>

    </div>

</body>
</html>
