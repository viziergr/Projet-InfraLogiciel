<?php 

include __DIR__ .'/../../scripts/bootstrap.php';
include __DIR__ .'/../../scripts/Team/Teams.php';  

include __DIR__ .'/../../includes/header.php';

sess_exists();

$data = [];
$errors = [];;

$mysqli = connectDB();
if($_SERVER['REQUEST_METHOD'] === 'POST') {
    dd($_POST); 
    $data = $_POST;
    $userId = $_SESSION['compte'];
    if(isset($_POST['name']) && isset($_POST['color']) && !empty($_POST['name']) && estCouleurValide($_POST['color'])){
        $teams = new TimeFusion\Team\Teams($mysqli);
        $team = $teams->hydrate(new TimeFusion\Team\Team(), $data);
        if($team==null) {
            $errors = ['teamExists'];
        }else{
            $teams->create($team, $userId);
            // header('Location: Calendrier.php?success=3');
            exit();
        }
    }elseif (isset($_POST['name']) && isset($_POST['color']) && (empty($_POST['name']) || !estCouleurValide($_POST['color']))){
        $errors = ['nameOrColor'];
    }
}
?>

<?php if($errors): ?>
    <?php if($errors == ['teamExists']): ?>
        <div class="alert alert-danger">
            Ce nom d'équipe est déjà pris
        </div> 
    <?php else: ?>
        <div class="alert alert-danger">
            Merci de corriger vos erreurs
        </div>
    <?php endif; ?>
<?php endif; ?>

<div class="container mt-5">
    <h2 class="mb-4">Créer une nouvelle équipe</h2>
    <form action="addTeam.php" method="POST">
        <div class="row">
            <div class="col-sm-6">
                <div class="form-group">
                    <label for="name">Nom de l'équipe</label>
                    <input type="text" required name="name" id="name" class="form-control" value="<?= isset($data['name']) ? $data['name'] : '' ?>">
                    <?php if(isset($errors['name'])): ?>
                        <small class="text-danger"><?= $errors['name']; ?></small>
                    <?php endif; ?>
                </div>
            </div>
            <div class="col-sm-6">
                <div class="form-group">
                    <label for="color">Couleur</label>
                    <input type="color" required name="color" id="color" class="form-control" value="<?= isset($data['color']) ? $data['color'] : '' ?>">
                    <?php if(isset($errors['color'])): ?>
                        <small class="text-danger"><?= $errors['color']; ?></small>
                    <?php endif; ?>
                </div>
            </div>
        </div>
        <div class="form-group text-center">
            <button class="btn btn-primary btn-block mt-3">Créer l'équipe</button>
        </div>
    </form>
</div>

<?php include __DIR__ .'/../../includes/footer.php'; ?>
