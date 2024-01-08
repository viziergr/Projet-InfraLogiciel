<?php
error_reporting(E_ALL);
ini_set('display_errors', 'On');
ini_set('display_startup_errors', 'On');

function connectDB() {

    $infoBdd = [
        'server' => 'localhost',
        'login' => 'pwnd',
        'password' => 'network',
        'db_name' => 'projet',
    ];

    $mysqli = new mysqli(
        $infoBdd['server'], $infoBdd['login'],
        $infoBdd['password'], $infoBdd['db_name']
    );
    
    // Vérifier la connexion à la base de données
    if ($mysqli->connect_errno) {
        exit('Problème de connexion à la BDD');
    }

    return $mysqli; // Retourne l'objet MySQLi pour être utilisé dans d'autres parties du code
}
?>
