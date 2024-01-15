<?php include '../src/Log/gestion_inscription.php'; ?>

<!DOCTYPE html>
<html lang="en">
<html>
<head>
    <title>Inscription</title>
    <link rel="stylesheet" href="..\..\CSS\code.css">
    <meta charset="utf-8">
    <style>
        input[type="text"], input[type="password"], input[type="email"], input[type="date"] {
            background-color: transparent;
            border: none;
            outline: none;
        }
    </style>
</head>
<body>
    <div class="bande">
        <a href="index.html">
            <img src="..\..\pictures\home.png" alt="Accueil">
        </a>
    </div>

    <div class="inscription">
        <img src="..\..\pictures\Logo.png" alt="Logo Time Fusion">
        <h1 id="bvn2">Bienvenue</h1>
        
        <form method="post">
            <div>
                <input type="text" id="prenom" name="fname" placeholder="Prénom" required><br><br>
            </div>
            <div>
                <input type="text" id="nom" name="lname" placeholder="Nom" required><br><br>
            </div>
            <div>
                <input type="email" id="email" name="mail" placeholder="E-mail" required><br><br>
            </div>
            <div>
                <input type="date" id="date" name="year" required><br><br>
            </div>
            <div>
                <input type="text" id="username" name="username" placeholder="Nom d'utilisateur" required><br><br>
            </div>
            <div>
                <input type="password" id="password" name="pwd" placeholder="Mot de passe" required><br><br>
            </div>
            <div>
                <input type="password" id="password" name="cpwd" placeholder="Confirmer le mot de passe" required><br><br>
            </div>
            <div>
                <button type="submit" id="boutonInscription" value="2" name="inscription_submit">Inscription</button>
            </div>
        </form>
    </div>
    <div class = "redirectionConnexion">
        <p>Déjà un compte ?</p>
        <a href="Connexion.php">
            <button type="button">Connectez vous</button>
        </a>
    </div>
</body>
</html>
