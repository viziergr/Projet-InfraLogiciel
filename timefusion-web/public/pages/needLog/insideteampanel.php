<?php
include __DIR__ .'/../../scripts/bootstrap.php';
include __DIR__ .'/../../scripts/Team/Teams.php';  

sess_exists();

include __DIR__ .'/../../includes/header.php';

$mysqli = connectDB();
$members = new TimeFusion\Team\Teams($mysqli);

// Supposons que vous ayez un mécanisme d'authentification qui donne l'ID de l'utilisateur connecté
$userId = $_SESSION['compte']; // Assurez-vous de récupérer l'ID de l'utilisateur correctement

if(isset($_POST['team_id'])) {
    $team_id = $_POST['team_id'];
}else{
    header('Location: teampanel.php');
}

$members = $members->getMembersByTeamId($team_id);
?>

<div class="request-container">
    <?php foreach ($members as $member): ?>
        <h3>'Nom' 'Prenom': 'Role' de l'équipe</h3>
        
        <!-- Ajout des boutons Accepter et Refuser -->
        <form action='' method="post">
            <input type="hidden" name="request_id" value="<?= $member?>">
            <button type="submit" name="accept_request">Promouvoir</button>
            <button type="submit" name="reject_request">Relèguer</button>
        </form>
    <?php endforeach; ?>
</div>

</body>
</html>

<?php
include __DIR__ .'/../../includes/footer.php';
?>
