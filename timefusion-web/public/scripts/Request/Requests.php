<?php

namespace TimeFusion\Request;

require __DIR__ . '/Request.php';

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
    try {
        // Utilisation d'une requête préparée pour éviter les injections SQL
        $stmt = $this->mysqli->prepare("SELECT requests.*, name
                                        FROM requests
                                        JOIN team ON requests.team_id = team.id
                                        WHERE requests.user_id = ? AND requests.status = 'en_attente'");

        if (!$stmt) {
            throw new \Exception("Erreur lors de la préparation de la requête : " . $this->mysqli->error);
        }

        // Liaison des paramètres et exécution de la requête
        $stmt->bind_param("i", $userId);
        $stmt->execute();

        // Récupération du résultat
        $result = $stmt->get_result();

        if (!$result) {
            throw new \Exception("Erreur lors de l'exécution de la requête : " . $this->mysqli->error);
        }

        $requests = [];

        while ($row = $result->fetch_assoc()) {
            $request = new Request(
                $row['id'],
                $row['team_id'],
                $row['name'],
                $row['user_id'],
                $row['status']
                // Ajoutez d'autres propriétés au besoin
            );

            // Ajouter la demande à la liste des demandes
            $requests[] = $request;
        }

        return $requests;
    } catch (\Exception $e) {
        // Gérer l'erreur, par exemple, en journalisant l'erreur ou en renvoyant une valeur par défaut
        // Dans cet exemple, on renvoie une liste vide en cas d'erreur
        error_log("Erreur dans getUserRequests : " . $e->getMessage());
        return [];
    }
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
        $sql = "SELECT COUNT(*) FROM team_membership WHERE team_id = ? AND user_id = ?";
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
            $sql = "INSERT INTO team_membership (team_id, user_id) VALUES (?, ?)";
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
        try {
            // Vérifier si la user_id existe dans la table user
            $userExists = $this->checkUserExists($guestId);
    
            if (!$userExists) {
                throw new \Exception("L'utilisateur avec l'ID $guestId n'existe pas.");
            }
    
            // Vérifier si la team_id existe dans la table teams
            $teamExists = $this->checkTeamExists($teamId);
    
            if (!$teamExists) {
                throw new \Exception("L'équipe avec l'ID $teamId n'existe pas.");
            }
    
            // Commencer une transaction
            $this->mysqli->begin_transaction();
    
            // Insérer une nouvelle demande d'invitation dans la base de données
            $sql = "INSERT INTO requests (user_id, team_id, status) VALUES (?, ?, 'en_attente')";
            $stmt = $this->mysqli->prepare($sql);
    
            if (!$stmt) {
                throw new \Exception("Erreur lors de la préparation de la requête d'insertion de demande d'invitation.");
            }
            $stmt->bind_param("ii", $guestId, $teamId);
            $stmt->execute();
            $stmt->close();
    
            // Valider la transaction
            $this->mysqli->commit();
            header('Location: /pages/needLog/networkpanel.php?demande_envoyee=1&team_id=' . $teamId . '&param=' . urlencode($teamId. $guestId));
            
        } catch (\Exception $e) {
            // En cas d'erreur, annuler la transaction et rediriger avec un message d'erreur
            $this->mysqli->rollback();
            header('Location: /pages/needLog/networkpanel.php?demande_envoyee=2&team_id=' . $teamId . '&error=' . urlencode($e->getMessage()));
            exit();
        }
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
        $sql = "SELECT COUNT(*) FROM team WHERE id = ?";
        $stmt = $this->mysqli->prepare($sql);
        $stmt->bind_param("i", $teamId);
        $stmt->execute();
        $stmt->bind_result($count);
        $stmt->fetch();
        $stmt->close();
    
        return $count > 0;
    }
    



}
