<?php

require __DIR__ . '\..\..\src\Team\Teams.php';
require __DIR__ . '\..\..\src\bootstrap.php';

sess_exists();

$mysqli = connectDB();
$teams = new TimeFusion\Team\Teams($mysqli);

// Supposons que vous ayez un mécanisme d'authentification qui donne l'ID de l'utilisateur connecté
$userId = $_SESSION['compte']; // Assurez-vous de récupérer l'ID de l'utilisateur correctement

$userTeams = $teams->getUserTeams($userId);

require __DIR__ . '\..\..\views\header.php';
?>

<div class="grid-container">
    <?php 
    // Utilisez $userTeams pour afficher les équipes
    foreach ($userTeams as $team) {
    ?>
        <div class="team-container" style="background-color: <?php echo $team->getColor(); ?>;">
            <div class="team-header">
                <h1>Equipe: <small><?php echo $team->getName(); ?></small></h1>
            </div>
            <h3>Membres:</h3>
            <?php
            $members = $team->getMembers();
            foreach ($members as $member) {
                echo $member['role'] . ': ' . $member['user']->getFullName() . '<br>';
            }
            ?>
            
            <!-- Bouton "Ajouter un membre" qui redirige vers networkpanel.php avec l'ID de la team -->
            <div class="team-actions">
                <form action="networkpanel.php" method="get">
                    <input type="hidden" name="team_id" value="<?php echo $team->getId(); ?>">
                    <button type="submit">Ajouter un membre</button>
                </form>

                <!-- Bouton "Afficher l'agenda commun" (ajoutez ici le lien vers la page d'agenda commun) -->
                <form action="Calendrier.php" method="post">
                    <input type="hidden" name="team_id" value="<?php echo $team->getId(); ?>">
                    <button type="submit">Afficher l'agenda commun</button>
                </form>
            </div>
        </div>
    <?php
    }
    ?>
</div>
<a href="/PHP/public/needLog/addTeam.php" class="calendar__button">+</a>

<?php
require __DIR__ . '\..\..\views\footer.php';
?>