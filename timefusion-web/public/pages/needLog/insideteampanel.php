<?php
include __DIR__ .'/../../scripts/bootstrap.php';
include __DIR__ .'/../../scripts/Team/Teams.php';

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
dd($members);
?>


<?php foreach ($members as $member):
    $memberId = $member->getId();
    $memberRole = $teams->getRoleById($member->getId(),$team_id);
    $memberName = $member->getFullName();

    if($userId != $memberId):
        dd('1');
        $userRole = $teams->getRoleById($userId,$team_id);
        $permissions = false;
        dd($userRole,$memberRole);
        switch($userRole){
            case 'Leader':
                switch($memberRole){
                    case 'Co-Leader':
                        $permissions = true;
                        break;
                    case 'Elder':
                        $permissions = true;
                        break;
                    case 'Member':
                        $permissions = true;
                        break;
                }
                break;
            case 'Co-Leader':
                switch($memberRole){
                    case 'Elder':
                        $permissions = true;
                        break;
                    case 'Member':
                        $permissions = true;
                        break;
                }
                break;
            case 'Elder':
                switch($memberRole){
                    case 'Member':
                        $permissions = true;
                        break;
                }
                break;
        }?>
        <?php if($permissions): ?>
            <div class="request-container">
                <h3><?= $memberName?> : <?= $memberRole?> de l'équipe</h3>
                <!-- Ajout des boutons Accepter et Refuser -->
                <form action='' method="post">
                    <input type="hidden" name="request_id" value="<?= $member?>">
                    <button type="submit" name="accept_request">Promouvoir</button>
                    <button type="submit" name="reject_request">Relèguer</button>
                </form>
            </div>
        <?php else: ?>
            <div class="request-container">
                <h3><?= $memberName?> : <?= $memberRole?> de l'équipe</h3>
            </div>
        <?php endif; ?>

    <?php else: 
        dd('2');?>
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
