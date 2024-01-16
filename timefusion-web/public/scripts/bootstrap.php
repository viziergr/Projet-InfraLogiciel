<?php
error_reporting(E_ALL);
ini_set('display_errors', 'On');
ini_set('display_startup_errors', 'On');

function e404(){
    require '../pages/404.php';
    exit();
}

function connectDB() {

    $infoBdd = [
        'server' => '192.168.56.81',
        'login' => 'root',
        'password' => '',
        'db_name' => 'TimeFusion',
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

function dd(...$vars){
    foreach ($vars as $var) {
        echo '<pre>';
        print_r($var);
        echo '</pre>';
    }
}

function h(string $value): string {
    if($value === null) return 'null';
    return htmlentities($value);
}

function render(string $view, $parameters = []) {
    extract($parameters);
    include "../includes/{$view}.php";
}

?>