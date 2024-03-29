<?php

namespace TimeFusion\Team;

class Team
{
    private $members = [];
    private $name;

    private $color;
    
    private $id;

    public function __construct(string $name = null, array $members = [], string $color = "#000352")
    {
        $this->name = $name;
        $this->members = $members;
        $this->color = $color;
    }

    public static function createFromDbResult(array $result): Team
    {
        $team = new self($result['team_name'], $result['team_members']);
        $team->setId($result['id']);

        return $team;
    }

    // Ajouter un membre à l'équipe avec un rôle spécifique
    public function addMember($user, $role)
    {
        $this->members[] = ['user' => $user, 'role' => $role];
    }

    // Obtenir la liste des membres de l'équipe avec leurs rôles
    public function getMembers()
    {
        return $this->members;
    }

    public function getId()
    {
        return $this->id;
    }

    public function getColor(){
        return $this->color;
    }

    public function getName()
    {
        return $this->name;
    }

    public function setId($id)
    {
        $this->id = $id;
    }

    public function setName(string $name){
        $this->name = $name;
    }

    public function setColor(string $color){
        $this->color = $color;
    }

    public function setMembers($members)
    {
        $this->members = $members;
    }

}
