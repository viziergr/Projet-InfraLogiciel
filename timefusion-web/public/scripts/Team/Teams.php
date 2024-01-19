<?php

namespace TimeFusion\Team;

include __DIR__ . '/Team.php';
include __DIR__ . '/User.php';

class Teams
{
    private $mysqli;

    public function __construct(\mysqli $mysqli) {
        $this->mysqli = $mysqli;
    }

    // Sauvegarder l'équipe dans la base de données avec MySQLi
    public function saveToDatabase($members)
    {
        try {
            $this->mysqli->query("INSERT INTO team () VALUES ()"); // Ajoutez votre requête d'insertion pour les équipes
            $teamId = $this->mysqli->insert_id;

            $stmt = $this->mysqli->prepare("INSERT INTO team_membership (team_id, user_id, role) VALUES (?, ?, ?)");

            foreach ($members as $member) {
                $userId = $member['user']->getId();
                $role = $member['role'];

                $stmt->bind_param("iss", $teamId, $userId, $role);
                $stmt->execute();
            }

        } catch (\Exception $e) {
            throw $e;
        } finally {
            $stmt->close();
        }
    }

    // Récupérer les équipes d'un utilisateur
    // Récupérer les équipes d'un utilisateur avec leurs membres
    public function getUserTeams($userId)
    {
        $result = $this->mysqli->query("SELECT team.*, team_membership.user_id
                                FROM team
                                JOIN team_membership ON team.id = team_membership.team_id
                                WHERE team_membership.user_id = '$userId'");

        if (!$result) {
            die('Erreur SQL : ' . $this->mysqli->error);
        }

        $teams = [];
    
        while ($row = $result->fetch_assoc()) {
            $team = new Team($row['name']);
            $team->setColor($row['Color']);
            $team->setId($row['id']); // Assurez-vous d'avoir une méthode setId dans votre classe Team
            // Ajoutez d'autres propriétés au besoin
    
            // Récupérer les membres de l'équipe avec leurs informations complètes depuis la table User
            $membersResult = $this->mysqli->query("SELECT u.id as user_id, u.first_name, u.last_name, u.email, u.password, u.year, tm.role
                                                   FROM team_membership tm
                                                   JOIN user u ON tm.user_id = u.id
                                                   WHERE tm.team_id = '{$row['id']}'");
    
            $members = [];
            while ($memberRow = $membersResult->fetch_assoc()) {
                // Créer un objet User avec les informations complètes
                $user = new User($memberRow['user_id'], $memberRow['first_name'], $memberRow['last_name'], $memberRow['email'], $memberRow['password'], $memberRow['year']);
                $members[] = ['user' => $user, 'role' => $memberRow['role']];
            }
    
            // Ajouter les membres à l'équipe
            $team->setMembers($members);
    
            // Ajouter l'équipe à la liste des équipes
            $teams[] = $team;
        }
    
        return $teams;
    }
    
    // Fonction pour obtenir une couleur pastel aléatoire
    public function getRandomPastelColor() {
        static $pastelColors = null;
    
        if ($pastelColors === null) {
            // Liste prédéfinie de couleurs pastel
            $pastelColors = array(
                '#77dd77', '#99ddff', '#ffcccb', '#ffddca', '#c0c0c0', '#ffb6c1', '#d2b48c',
                '#b19cd9', '#ffdb58', '#b0e0e6', '#a0d6b4', '#f0e68c', '#c2b280', '#dda0dd'
            );
    
            // Mélanger la liste des couleurs
            shuffle($pastelColors);
        }
    
        // Retourner et retirer la première couleur de la liste
        return array_shift($pastelColors);
    }

    public function getMembersByTeamId($team_id)
    {
        $result = $this->mysqli->query("SELECT user_id FROM team_membership WHERE team_id = $team_id");
        if ($result === false) {
            throw new \Exception("Erreur lors de l'exécution de la requête : " . $this->mysqli->error);
        }
        $userIds = array(); // Initialise un tableau vide pour stocker les user_ids
        // Boucle sur les résultats pour extraire les user_ids
        while ($row = $result->fetch_assoc()) {
            $userIds[] = $row['user_id'];
        }
        // Vérifie s'il n'y a aucun membre trouvé
        if (empty($userIds)) {
            throw new \Exception("Aucun membre n'a été trouvé");
        }
        return $userIds;
    }

    public function hydrate(Team $team, array $data){
        $name = $data['name'];
        $color = $data['color'];
    
        // Vérification de l'existence d'une équipe du même nom
        if ($this->teamNameExists($name)) {
            return null; // Équipe du même nom déjà existante
        }
    
        // Hydratation de l'équipe
        $team->setName($name);
        $team->setColor($color);
    
        return $team;
    }
    
    // Méthode pour vérifier l'existence d'une équipe du même nom
    private function teamNameExists($name) {
        $sql = "SELECT COUNT(*) as count FROM team WHERE team_name = '$name'";
        
        // Exécutez la requête SQL
        $result = $this->mysqli->query($sql);
    
        if ($result) {
            // Récupérez la première ligne du résultat
            $row = $result->fetch_assoc();
            // Retournez vrai si le nombre est supérieur à 0
            return $row['count'] > 0;
        }
        // Retournez faux en cas d'erreur d'exécution de la requête
        return false;
    }
    
    

    public function create(Team $team, $userId) {
        if($userId == null) {
            return "Grosse erreur";
        }
        $sql = "INSERT INTO team (team_name, color) VALUES (?, ?)";
        $stmt = $this->mysqli->prepare($sql);
        if (!$stmt) {return false;}
        
        // Extraire les valeurs des méthodes de l'objet Event
        $name = $team->getName();
        $color = $team->getColor();
        // Bind parameters avec des variables
        $stmt->bind_param("ss", $name, $color);
        // Exécuter la requête
        $result = $stmt->execute();
        // Fermer le statement
        $stmt->close();

        $sql = "SELECT id FROM team WHERE team_name = '$name' LIMIT 1";
        // Exécution de la requête
        $result = $this->mysqli->query($sql);
        // Vérification des erreurs
        if (!$result) {
            die("Erreur dans la requête : " . $this->mysqli->error);
        }
        // Récupération du résultat
        $row = $result->fetch_assoc();
        
        $sql = "INSERT INTO team_membership (team_id, user_id, role) VALUES (?, ?, ?)";
        $stmt = $this->mysqli->prepare($sql);
    
        if (!$stmt) {
            // Gérer l'erreur, par exemple, retourner false ou lever une exception
            return false;
        }
    
        // Extraire les valeurs des méthodes de l'objet Event
        $team_id = $row['id'];
        $role = 'Leader';
    
        // Bind parameters avec des variables
        $stmt->bind_param("sss", $team_id, $userId, $role);
    
        // Exécuter la requête
        $result = $stmt->execute();
    
        // Fermer le statement
        $stmt->close();
    }

}
