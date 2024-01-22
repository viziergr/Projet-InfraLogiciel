<?php
include __DIR__ .'/../../scripts/bootstrap.php';
include __DIR__ .'/../../scripts/Team/Users.php';
include __DIR__ .'/../../scripts/Team/Teams.php';

sess_exists();

include __DIR__ .'/../../includes/header.php';

$mysqli = connectDB();
$teams = new TimeFusion\Team\Teams($mysqli);
$users = new TimeFusion\Team\Users($mysqli);

// Supposons que vous ayez un mécanisme d'authentification qui donne l'ID de l'utilisateur connecté
$userId = $_SESSION['compte']; // Assurez-vous de récupérer l'ID de l'utilisateur correctement

if(isset($_GET['team_id'])) {
    $team_id = $_GET['team_id'];
}else{
    header('Location: teampanel.php');
}
$user = $users->getUserById($userId);
$team = $teams->getTeamObjectFromDb($team_id);
$members = $team->getMembers();
?>

<div class="request-container">
    <h3><?= $user->getFullName() ?> : <?= $teams->getRoleById($userId,$team_id)?> de l'équipe</h3>
    
    <!-- Ajout des boutons Accepter et Refuser -->
    <form action='' method="post">
        <input type="hidden" name="request_id" value="<?= $member?>">
        <button type="submit" name="quit_team">Quitter l'équipe</button>
    </form>
</div>

<?php foreach ($members as $member):
    $memberId = $member->getId();
    $memberName = $member->getFullName();
    $memberRole = $teams->getRoleById($memberId,$team_id);
    $userRole = $teams->getRoleById($userId,$team_id);

    if($userId != $memberId):
        $permissions = $teams->hasRights($userRole,$memberRole);
        if($permissions): ?>
            <div class="request-container">
                <h3><?= $memberName?> : <?= $memberRole?> de l'équipe</h3>
                <!-- Ajout des boutons Accepter et Refuser -->
                <form action='../../scripts/gestion_permission.php' method="post">
                    <input type="hidden" name="member_id" value="<?=$memberId?>">
                    <input type="hidden" name="team_id" value="<?=$team_id?>">
                    <button type="submit" name="promote">Promouvoir</button>
                    <button type="submit" name="relegate">Reléguer</button>
                </form>
            </div>
        <?php else: ?>
            <div class="request-container">
                <h3><?= $memberName?> : <?= $memberRole?> de l'équipe</h3>
            </div>
        <?php endif; ?>
    <?php endif; ?>
<?php endforeach; ?>

</body>
</html>

<?php
include __DIR__ .'/../../includes/footer.php';
?>