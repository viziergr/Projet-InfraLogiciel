<?php
include __DIR__ .'/../../scripts/bootstrap.php';
include __DIR__ .'/../../scripts/Team/Teams.php';
include __DIR__ .'/../../scripts/Team/Role.php';
// Inclusion double User

sess_exists();

include __DIR__ .'/../../includes/header.php';

$mysqli = connectDB();
$teams = new TimeFusion\Team\Teams($mysqli);

// Supposons que vous ayez un mécanisme d'authentification qui donne l'ID de l'utilisateur connecté
$userId = $_SESSION['compte']; // Assurez-vous de récupérer l'ID de l'utilisateur correctement

if(isset($_POST['team_id'])) {
    $team_id = $_POST['team_id'];
}else{
    header('Location: teampanel.php');
}

$team = $teams->getTeamObjectFromDb($team_id);
$members = $team->getMembers();
?>


<?php foreach ($members as $member):
    $memberId = $member->getId();
    $memberRole = $teams->getRoleById($member->getId(),$team_id);
    $memberName = $member->getFullName();

    if($userId != $memberId):
        $roleUser = $teams->getRoleById($userId,$team_id)->getRoleByName();
        $roleMember = $memberRole->getRoleByName();
        dd($roleMember, $roleUser)?>
        <div class="request-container">
            <h3><?= $memberName?> : <?= $memberRole?> de l'équipe</h3>
            <!-- Ajout des boutons Accepter et Refuser -->
            <?php if($teams->getRoleById($userId,$team_id)->getRoleByName() > $teams->getRoleById($memberId,$team_id)->getRoleByName()): ?>
                <form action='' method="post">
                    <input type="hidden" name="request_id" value="<?= $member?>">
                    <button type="submit" name="accept_request">Promouvoir</button>
                    <button type="submit" name="reject_request">Relèguer</button>
                </form>
            <?php endif; ?>
        </div>

    <?php else: ?>
        <div class="request-container">
            <h3><?= $memberName?> : <?= $memberRole?> de l'équipe</h3>
            
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
