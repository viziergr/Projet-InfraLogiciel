<?php

namespace TimeFusion\Team;

require 'Team.php';
require 'User.php';

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
            $this->mysqli->query("INSERT INTO teams () VALUES ()"); // Ajoutez votre requête d'insertion pour les équipes
            $teamId = $this->mysqli->insert_id;

            $stmt = $this->mysqli->prepare("INSERT INTO team_members (team_id, user_id, role) VALUES (?, ?, ?)");

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
        $result = $this->mysqli->query("SELECT teams.*, team_members.user_id
                                        FROM teams
                                        JOIN team_members ON teams.id = team_members.team_id
                                        WHERE team_members.user_id = '$userId'");
    
        $teams = [];
    
        while ($row = $result->fetch_assoc()) {
            $team = new Team($row['team_name']);
            $team->setId($row['id']); // Assurez-vous d'avoir une méthode setId dans votre classe Team
            // Ajoutez d'autres propriétés au besoin
    
            // Récupérer les membres de l'équipe avec leurs informations complètes depuis la table User
            $membersResult = $this->mysqli->query("SELECT u.id as user_id, u.first_name, u.last_name, u.email, u.password, u.year, tm.role
                                                   FROM team_members tm
                                                   JOIN User u ON tm.user_id = u.id
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
    


}
