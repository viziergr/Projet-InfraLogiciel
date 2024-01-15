<?php
require '../scripts/bootstrap.php';
require '../includes/header.php';
require '../scripts/Calendar/EventValidator.php';
require '../scripts/Calendar/Event.php';
require '../scripts/Calendar/Events.php';

$data = [];
$errors = [];

$mysqli = connectDB();
if($_SERVER['REQUEST_METHOD'] === 'POST') {
    $data = $_POST;
    $validator = new TimeFusion\Calendar\EventValidator();
    $errors = $validator->validates($_POST);
    if(empty($errors)) {
        $events = new TimeFusion\Calendar\Events($mysqli);
        $event = $events->hydrate(new TimeFusion\Calendar\Event(),$data);
        $events->create($event);
        header('Location: Calendrier.php?success=1');
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
        <?php render('Calendar/form', ['data' => $data, 'errors' => $errors]); ?>
    </form>
</div>


<?php
require '../includes/footer.php';
?>