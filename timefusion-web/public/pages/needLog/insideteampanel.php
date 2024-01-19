<?php
include __DIR__ .'/../../scripts/bootstrap.php';
include __DIR__ .'/../../scripts/Team/Teams.php';
include __DIR__ .'/../../scripts/Team/Users.php';  
// Inclusion double User

sess_exists();

include __DIR__ .'/../../includes/header.php';

$mysqli = connectDB();
$members = new TimeFusion\Team\Teams($mysqli);
$users = new TimeFusion\Team\Users($mysqli);

// Supposons que vous ayez un mécanisme d'authentification qui donne l'ID de l'utilisateur connecté
$userId = $_SESSION['compte']; // Assurez-vous de récupérer l'ID de l'utilisateur correctement

if(isset($_POST['team_id'])) {
    $team_id = $_POST['team_id'];
}else{
    header('Location: teampanel.php');
}

$members = $members->getMembersByTeamId($team_id);
?>


<?php foreach ($members as $member): 
    if($userId != $member):?>
        <div class="request-container">
            <h3><?= $users->getUserById($member)->getFullName()?> : <?= '$teams->getRoleById($member)'?> de l'équipe</h3>
            
            <!-- Ajout des boutons Accepter et Refuser -->
            <form action='' method="post">
                <input type="hidden" name="request_id" value="<?= $member?>">
                <button type="submit" name="accept_request">Promouvoir</button>
                <button type="submit" name="reject_request">Relèguer</button>
            </form>
        </div>
    <?php else: ?>
        <div class="request-container">
            <h3><?= $users->getUserById($member)->getFullName()?> : <?= '$teams->getRoleById($member)'?> de l'équipe</h3>
            
            <!-- Ajout des boutons Accepter et Refuser -->
            <form action='' method="post">
                <input type="hidden" name="request_id" value="<?= $member?>">
                <button type="submit" name="quit_team">Quitter l'équipe</button>
            </form>
        </div>
    <?php endif; ?>
<?php endforeach; ?>

</body>
</html>

<?php
include __DIR__ .'/../../includes/footer.php';
?>
