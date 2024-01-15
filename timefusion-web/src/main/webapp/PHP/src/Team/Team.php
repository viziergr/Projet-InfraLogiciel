<?php

namespace TimeFusion\Team;

class Team
{
    private $members = [];
    private $name;

    private $id;

    public function __construct($name)
    {
        $this->name = $name;
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

    public function getName()
    {
        return $this->name;
    }

    public function setId($id)
    {
        $this->id = $id;
    }

    public function setMembers($members)
    {
        $this->members = $members;
    }

}
