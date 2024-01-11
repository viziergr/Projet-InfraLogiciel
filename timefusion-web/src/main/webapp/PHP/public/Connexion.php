<?php include '../src/Log/gestion_connexion.php'; ?>

<!DOCTYPE html>
<html lang="en">
<html>
<head>
    <title>Formulaire de connexion</title>
    <link rel="stylesheet" href="..\..\CSS\code.css">
    <meta charset="utf-8">
</head>
<body>
    <div class="bande">
        <a href="..\..\HTML\index.html">
            <img id="home" src="..\..\pictures\home.png" alt="Accueil">
        </a>
    </div>

    <?php if (empty($_SESSION['compte'])): ?>
        <div class="cnx">
            <img src="..\..\pictures\Logo.png" alt="Logo Time Fusion">
            <h1>Bienvenue</h1>
            <form method="post">
                <div>          
                    <input type="email" id="email" name="mail" placeholder="E-mail" required>
                </div>
                <div>
                    <input type="password" id="password" name="password" placeholder="Mot de passe" required>
                </div>
                <div>
                    <button type="submit" id="boutonConnexion" value="1" name="connexion_submit">Connexion</button>
                </div>
            </form>
        </div>
       
        <div class="redirectionInscription">
            <p>Pas de compte ?</p>
            <a href="..\..\PHP\Inscription.php">
                <button type="button">Inscrivez vous</button>
            </a>
        </div>
    <?php endif; ?>
</body>

</html>
