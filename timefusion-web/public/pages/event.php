<?php

require '../src/bootstrap.php';
require '../src/Calendar/Events.php';

$mysqli = connectDB();
$events = new TimeFusion\Calendar\Events($mysqli);

if(!isset($_GET['id'])){
    e404();
}
try{
    $event = $events->find($_GET['id']);
} catch(\Exception $e){
    e404();
}

require '../views/header.php';
?>

<h1>Evènement: <?= h($event['title']); ?></h1>

<ul>
    <li>Date: <?= (new \DateTime($event['start_time']))->format('d/m/Y'); ?></li>
    <li>Heure de début: <?= (new \DateTime($event['start_time']))->format('H:i'); ?></li>
    <li>Heure de fin: <?= (new \DateTime($event['end_time']))->format('H:i'); ?></li>
    <li>
        Description:<br>
        <?=h($event['description']); ?>
    </li>
</ul>



<?php 
require '../views/footer.php';
?>