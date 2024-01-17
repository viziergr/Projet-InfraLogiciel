<?php
require __DIR__ . '\..\..\views\header.php';
require __DIR__ . '\..\..\src\Request\Requests.php';
require __DIR__ . '\..\..\src\bootstrap.php';
sess_exists();

$mysqli = connectDB();
$requests = new TimeFusion\Request\Requests($mysqli);

// Supposons que vous ayez un mécanisme d'authentification qui donne l'ID de l'utilisateur connecté
$userId = $_SESSION['compte']; // Assurez-vous de récupérer l'ID de l'utilisateur correctement

$userRequests = $requests->getUserRequests($userId);
?>

<div class="request-container">
    <?php foreach ($userRequests as $request): ?>
        <h3>L'équipe: <?= $request->getTeamName() ?> vous envoie une invitation à les rejoindre</h3>
        
        <!-- Ajout des boutons Accepter et Refuser -->
        <form action="../../src/Log/gestion_notifications.php" method="post">
            <input type="hidden" name="request_id" value="<?= $request->getId()?>">
            <button type="submit" name="accept_request">Accepter</button>
            <button type="submit" name="reject_request">Refuser</button>
        </form>
    <?php endforeach; ?>
</div>

</body>
</html>

<?php
require __DIR__ . '\..\..\views\footer.php';
?>
