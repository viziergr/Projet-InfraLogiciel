<?php
require __DIR__ . '\..\..\src\bootstrap.php';
require __DIR__ . '\..\..\views\header.php';
require __DIR__ . '\..\..\src\Calendar\EventValidator.php';
require __DIR__ . '\..\..\src\Calendar\Event.php';
require __DIR__ . '\..\..\src\Calendar\Events.php';

sess_exists();

$data = [];
$errors = [];

$mysqli = connectDB();
if($_SERVER['REQUEST_METHOD'] === 'POST') {
    $data = $_POST;
    $userId = $_SESSION['compte'];
    $validator = new TimeFusion\Calendar\EventValidator();
    $errors = $validator->validates($_POST);
    if(empty($errors)) {
        $events = new TimeFusion\Calendar\Events($mysqli);
        $event = $events->hydrate(new TimeFusion\Calendar\Event(),$data, $userId);
        $events->create($event);
        header('Location: /PHP/public/needLog/Calendrier.php?success=1');
        exit();
    }
}
?>

<?php if(!empty($errors)): ?>
    <div class="alert alert-danger">
        Merci de corriger vos erreurs
    </div> 
<?php endif; ?>


<div class="container">
    <h1>Ajouter un évènement</h1>
    <form action="" method="POST">
        <?php render('Calendar\form', ['data' => $data, 'errors' => $errors]); ?>
    </form>
</div>


<?php
require __DIR__ . '\..\..\views\footer.php';
?>