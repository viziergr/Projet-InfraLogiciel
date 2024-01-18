<?php

namespace TimeFusion\Request;

require __DIR__ . '\Request.php';

class Requests
{
    private $mysqli;

    public function __construct(\mysqli $mysqli) {
        $this->mysqli = $mysqli;
    }

    // Récupérer les équipes d'un utilisateur
    // Récupérer les équipes d'un utilisateur avec leurs membres
    public function getUserRequests($userId)
    {
        $result = $this->mysqli->query("SELECT requests.*, teams.team_name
                                        FROM requests
                                        JOIN teams ON requests.team_id = teams.id
                                        WHERE requests.user_id = '$userId' AND requests.status = 'en_attente'");
        
        $requests = [];

        while ($row = $result->fetch_assoc()) {
            $request = new Request(
                $row['id'],
                $row['team_id'],
                $row['team_name'],
                $row['user_id'],
                $row['status']
                // Ajoutez d'autres propriétés au besoin
            );

            // Ajouter la demande à la liste des demandes
            $requests[] = $request;
        }

        return $requests;
    }

    public function refuserDemande($requestId) {
        // Mettre à jour le statut de la demande dans la base de données
        // Supposons que vous ayez une colonne 'status' dans votre table 'request'
        $sql = "UPDATE requests SET status = 'refusee' WHERE id = ?";
        $stmt = $this->mysqli->prepare($sql);
        $stmt->bind_param("i", $requestId);
        $stmt->execute();
        $stmt->close();
    }

    public function accepterDemande($requestId) {
        // Récupérer les informations de la demande
        $request = $this->getRequestById($requestId);
        $teamId = $request['team_id'];
        $userId = $request['user_id'];

        // Mettre à jour le statut de la demande dans la base de données
        // Supposons que vous ayez une colonne 'status' dans votre table 'request'
        $sql = "UPDATE requests SET status = 'acceptee' WHERE id = ?";
        $stmt = $this->mysqli->prepare($sql);
        $stmt->bind_param("i", $requestId);
        $stmt->execute();
        $stmt->close();

        // Ajouter le membre à l'équipe
        $this->ajouterMembreEquipe($teamId, $userId);
    }

    // Méthode pour ajouter un membre à l'équipe
    private function ajouterMembreEquipe($teamId, $userId) {
        // Vérifier si le membre n'est pas déjà dans l'équipe
        $sql = "SELECT COUNT(*) FROM team_members WHERE team_id = ? AND user_id = ?";
        $stmt = $this->mysqli->prepare($sql);
        $stmt->bind_param("ii", $teamId, $userId);
        $stmt->execute();
        $stmt->bind_result($count);
        $stmt->fetch();
        $stmt->close();

        // Initialiser $count si aucune ligne n'est trouvée
        $count = isset($count) ? $count : 0;

        // Si le membre n'est pas déjà dans l'équipe, l'ajouter
        if ($count == 0) {
            $sql = "INSERT INTO team_members (team_id, user_id) VALUES (?, ?)";
            $stmt = $this->mysqli->prepare($sql);
            $stmt->bind_param("ii", $teamId, $userId);
            $stmt->execute();
            $stmt->close();
        }
    }

    // Méthode pour récupérer les informations d'une demande par son ID
    private function getRequestById($requestId) {
        $sql = "SELECT team_id, user_id FROM requests WHERE id = ?";
        $stmt = $this->mysqli->prepare($sql);
        $stmt->bind_param("i", $requestId);
        $stmt->execute();
        $result = $stmt->get_result();
        $request = $result->fetch_assoc();
        $stmt->close();
        return $request;
    }

    // Dans la classe Requests

    public function envoyerInvitation($guestId, $teamId) {
        // Vérifier si la user_id existe dans la table user
        $userExists = $this->checkUserExists($guestId);
    
        if (!$userExists) {
            header('Location: /PHP/public/needLog/networkpanel.php?demande_envoyee=2&team_id=' . $teamId . '');
            exit();
        }
    
        // Vérifier si la team_id existe dans la table teams
        $teamExists = $this->checkTeamExists($teamId);
    
        if (!$teamExists) {
            header('Location: /PHP/public/needLog/networkpanel.php?demande_envoyee=2&team_id=' . $teamId . '');
            exit();
        }
    
        // Insérer une nouvelle demande d'invitation dans la base de données
        $sql = "INSERT INTO requests (user_id, team_id, status) VALUES (?, ?, 'en_attente')";
        $stmt = $this->mysqli->prepare($sql);
        $stmt->bind_param("ii", $guestId, $teamId);
        $stmt->execute();
        $stmt->close();
    }
    
    // Méthode pour vérifier si la user_id existe dans la table user
    private function checkUserExists($userId) {
        $sql = "SELECT COUNT(*) FROM user WHERE id = ?";
        $stmt = $this->mysqli->prepare($sql);
        $stmt->bind_param("i", $userId);
        $stmt->execute();
        $stmt->bind_result($count);
        $stmt->fetch();
        $stmt->close();
    
        return $count > 0;
    }    
    
    // Méthode pour vérifier si la team_id existe dans la table teams
    private function checkTeamExists($teamId) {
        $sql = "SELECT COUNT(*) FROM teams WHERE id = ?";
        $stmt = $this->mysqli->prepare($sql);
        $stmt->bind_param("i", $teamId);
        $stmt->execute();
        $stmt->bind_result($count);
        $stmt->fetch();
        $stmt->close();
    
        return $count > 0;
    }
    



}
