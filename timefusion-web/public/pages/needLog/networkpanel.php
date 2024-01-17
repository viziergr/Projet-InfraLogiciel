<?php

require __DIR__ . '\..\..\views\header.php';
require __DIR__ . '\..\..\src\Team\Users.php';
require __DIR__ . '\..\..\src\bootstrap.php';

sess_exists();
 

$mysqli = connectDB();
$users = new TimeFusion\Team\Users($mysqli);

// Supposons que vous ayez un mécanisme d'authentification qui donne l'ID de l'utilisateur connecté
$userId = $_SESSION['compte']; // Assurez-vous de récupérer l'ID de l'utilisateur correctement

$userList = $users->getEveryOtherUsers($userId);

?>

<?php if (isset($_GET['demande_envoyee']) && $_GET['demande_envoyee']==='1'): ?>
    <div class="container">
        <div class="alert alert-success">
            La demande d'ajout a bien été envoyée.
        </div>
    </div>
<?php elseif (isset($_GET['demande_envoyee']) && $_GET['demande_envoyee']==='2'): ?>
    <div class="container">
        <div class="alert alert-danger">
            La demande d'ajout n'a pas été envoyée. Attendez que l'utilisateur réponde à votre invitation ou rééssayez plus tard. 
        </div>
    </div>
<?php endif; ?>

<div class="grid-container">
    <?php foreach ($userList as $user): ?>
        <div class="user-card">
            <h3><?= $user->getFirstName() . ' ' . $user->getLastName(); ?></h3>
            <p>Email: <?= $user->getEmail(); ?></p>
            <p>Year: <?= $user->getYear(); ?></p>
            
            <!-- Formulaire avec le bouton "Ajouter à une équipe" -->
            <form action="../../src/Log/gestion_notifications.php" method="post">
                <input type="hidden" name="user_id" value="<?= $user->getId(); ?>">
                <?php 
                    if (isset($_GET['team_id'])){
                        $teamId = $_GET['team_id'];
                    }
                ?>
                <input type="hidden" name="team_id" value="<?= $teamId; ?>">
                <button type="submit" name="add_team">Ajouter à une équipe</button>
            </form>
        </div>
    <?php endforeach; ?>
</div>

</body>
</html>

<?php
require __DIR__ . '\..\..\views\footer.php';
?>