<?php
require __DIR__ . '\..\bootstrap.php';
require __DIR__ . '\..\Request\Requests.php';

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    // Vérifier si le formulaire "Accepter" a été soumis
    if (isset($_POST['accept_request'])) {
        traiterAcceptation();
    }
    // Vérifier si le formulaire "Refuser" a été soumis
    elseif (isset($_POST['reject_request'])) {
        traiterRefus();
    }
    elseif (isset($_POST['add_team'])) {
        demanderARejoindre();
    }
}

function traiterAcceptation() {
    // Récupérer l'ID de la demande à partir du formulaire
    $requestId = $_POST['request_id'];

    // Mettre à jour le statut de la demande dans la base de données
    // (Assumez que vous avez une méthode dans votre classe Requests pour effectuer cela)
    $mysqli = connectDB();
    $requests = new TimeFusion\Request\Requests($mysqli);
    $requests->accepterDemande($requestId);

    // Rediriger ou afficher un message de succès, etc.
    header('Location: /PHP/public/needLog/requestpanel.php');
    exit();
}

function traiterRefus() {
    // Récupérer l'ID de la demande à partir du formulaire
    $requestId = $_POST['request_id'];

    // Mettre à jour le statut de la demande dans la base de données
    // (Assumez que vous avez une méthode dans votre classe Requests pour effectuer cela)
    $mysqli = connectDB();
    $requests = new TimeFusion\Request\Requests($mysqli);
    $requests->refuserDemande($requestId);

    // Rediriger ou afficher un message de succès, etc.
    header('Location: /PHP/public/needLog/requestpanel.php');
    exit();
}

function demanderARejoindre() {
    // Récupérer l'ID de l'utilisateur invité à partir du formulaire
    $guestId = $_POST['user_id'];
    $teamId = $_POST['team_id'];

    // Mettre à jour le statut de la demande dans la base de données
    $mysqli = connectDB();
    $requests = new TimeFusion\Request\Requests($mysqli);

    // Vérifiez si l'utilisateur n'a pas déjà une invitation en attente de cette équipe
    $existingRequests = $requests->getUserRequests($guestId);
    foreach ($existingRequests as $existingRequest) {
        if ($existingRequest->getTeamId() == $teamId && $existingRequest->getUserId() == $guestId && $existingRequest->getStatus() == 'en_attente') {
            // L'utilisateur a déjà une invitation en attente pour rejoindre cette équipe
            // Vous pouvez rediriger ou afficher un message d'erreur ici
            header('Location: /PHP/public/needLog/networkpanel.php?demande_envoyee=2&team_id=' . $teamId . '');
            exit();
        }
    }

    // Envoi de l'invitation
    $requests->envoyerInvitation($guestId, $teamId);

    // Rediriger ou afficher un message de succès, etc.
    header('Location: /PHP/public/needLog/networkpanel.php?demande_envoyee=1&team_id=' . $teamId . '');
    exit();

}



?>
