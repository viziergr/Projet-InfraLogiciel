<?php include 'gestion_connexion.php'; ?>

<!DOCTYPE html>
<html lang="en">
<html>
<head>
    <title>Formulaire de connexion</title>
    <link rel="stylesheet" href="..\CSS\code.css">
    <meta charset="utf-8">
</head>
<body class="cnx">
    <div class="home">
        <a href="..\HTML\index.html">
            <img id="home" src="..\pictures\home.png" alt="Accueil">
        </a>
    </div>
    <?php if (empty($_SESSION['compte'])): ?>
        <h1 id="bvn1">Bienvenue</h1>
        <img id="img2" src="..\pictures\Logo.png" alt="Logo Time Fusion">
        <form method="post">
            <div>          
                <input type="email" id="email1" name="email" placeholder="E-mail" required>
            </div>
            <div>
                <input type="password" id="password1" name="password" placeholder="Mot de passe" required>
            </div>
            <div>
                <input type="submit" id="boutonBleu1" value="Connexion">
            </div>
        </form>
        <p id="pdc">Pas de compte ?</p>
        <a href="..\PHP\Inscription.php">
            <button id="btnRed1" type="button">Inscrivez vous</button>
        </a>
    <?php else: ?>
        <div class="text-center mt-5">
            <?php echo "Bonjour " . $prenom . " " . $nom . ", vous êtes désormais connecté." ?>
            <br>
            <a href="http://192.168.56.80/pwnd?logout=1" class="btn btn-secondary mt-3">Se déconnecter</a>
        </div>
    <?php endif; ?>
</body>

</html>
