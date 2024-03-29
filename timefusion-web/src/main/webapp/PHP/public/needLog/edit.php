<?php

require __DIR__ . '\..\..\src\bootstrap.php';
require __DIR__ . '\..\..\src\Calendar\Events.php';
require __DIR__ . '\..\..\src\Calendar\EventValidator.php';

sess_exists();

$mysqli = connectDB();
$events = new TimeFusion\Calendar\Events($mysqli);
$errors = [];

if(!isset($_GET['id'])){
    e404();
}
try{
    $event = $events->find($_GET['id']);
} catch(\Exception $e){
    e404();
}

$data = [
    'name' => $event->getTitle(),
    'date' => $event->getStartTime()->format('Y-m-d'),
    'start' => $event->getStartTime()->format('H:i'),
    'end' => $event->getEndTime()->format('H:i'),
    'description' => $event->getDescription()
];

if($_SERVER['REQUEST_METHOD'] === 'POST') {
    $data = $_POST;
    $validator = new TimeFusion\Calendar\EventValidator();
    $errors = $validator->validates($data);
    if(empty($errors)) {
        $events->hydrate($event,$data);
        $events->update($event);
        header('Location: /PHP/public/Calendrier.php?success=2');
        exit();
    }
}

require '../../views/header.php';
?>

<div class="container">
    <h1>Editer l'évènement: <small><?= h($event->getTitle()); ?></small></h1>
    <form action="" method="POST">
        <?php render('Calendar\form', ['data' => $data, 'errors' => $errors]); ?>
    </form>
</div>

<?php 
require __DIR__ . '\..\..\views\footer.php';
?>