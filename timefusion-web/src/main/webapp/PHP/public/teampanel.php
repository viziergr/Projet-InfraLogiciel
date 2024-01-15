<?php

require '../src/bootstrap.php';
require '../src/Calendar/Events.php';
require '../src/Team/Teams.php';

session_start();

$mysqli = connectDB();
$teams = new TimeFusion\Team\Teams($mysqli);

// Supposons que vous ayez un mécanisme d'authentification qui donne l'ID de l'utilisateur connecté
$userId = $_SESSION['compte']; // Assurez-vous de récupérer l'ID de l'utilisateur correctement

$userTeams = $teams->getUserTeams($userId);

require '../views/header.php';
?>

<?php 
// Utilisez $userTeams pour afficher les équipes
foreach ($userTeams as $team) {
    // Vous pouvez personnaliser le code d'affichage selon vos besoins
    echo '<h1>Equipe: <small>' . $team->getName() .'</small></h1>';
    $members = $team->getMembers();
    foreach ($members as $member) {
        echo $member['role'] . ': ' . $member['user']->getFullName() . '<br>';
    }
}
require '../views/footer.php';
?>
