<?php
require '../src/bootstrap.php';
require '../views/header.php';

if($_SERVER['REQUEST_METHOD'] === 'POST') {
    $errors = [];
    $validator = new TimeFusion\Calendar\EventValidator();
    $errors = $validator->validate($_POST);
    if(empty($errors)){

    }
}
?>

<div class="container">
    <h1>Ajouter un évènement</h1>
    <form action="" method="POST">
        <div class="row">
            <div class="col-sm-6">
                <div class="form-group">
                    <label for="name">Nom de l'évènement</label>
                    <input type="text" required name="name" id="name" class="form-control" value="Demo">
                    <?php if($errors['name']): ?>
                        <p class="help-block"><?= $errors['name']; ?></p>
                    <?php endif; ?>
                </div>
            </div>
            <div class="col-sm-6">
                <div class="form-group">
                    <label for="date">Date</label>
                    <input type="date" required name="date" id="date" class="form-control" value="2024-01-01">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-6">
                <div class="form-group">
                    <label for="start">Début</label>
                    <input type="time" required name="start" id="start" class="form-control" placeholder="HH:MM" value="11:00">
                </div>
            </div>
            <div class="col-sm-6">
                <div class="form-group">
                    <label for="end">Fin</label>
                    <input type="time" required name="end" id="end" class="form-control" placeholder="HH:MM" value="12:00">
                </div>
            </div>
        </div>
        <div class="form-group">
            <label for="description">Description</label>
            <textarea name="description" id="description" class="form-control"></textarea>
        </div>
        <div class="form-group">
            <button class="btn btn-primary">Ajouter l'évènement</button>
        </div>
    </form>
</div>


<?php
require '../views/footer.php';
?>