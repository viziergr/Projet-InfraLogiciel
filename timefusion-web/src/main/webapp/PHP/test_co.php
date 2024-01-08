<?php 

require_once('connexion_bdd.php');

try{
    connectDB();
    echo 'Well done';
}catch(Exception $e){
    echo('Fail');
}

?>