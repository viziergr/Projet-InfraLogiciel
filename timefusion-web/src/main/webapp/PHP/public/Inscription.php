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
    <h1 id="bvn2">Bienvenue</h1>
    <img id="img2" src="..\..\pictures\Logo.png" alt="Logo Time Fusion">
    <div class="home">
        <a href="index.html">
            <img id="home" src="..\..\pictures\home.png" alt="Accueil">
        </a>
    </div>
    <form method="post">
        <div>
            <input type="text" id="prenom" name="fname" placeholder="Prénom" required><br><br>
        </div>
        <div>
            <input type="text" id="nom" name="lname" placeholder="Nom" required><br><br>
        </div>
        <div>
            <input type="email" id="email2" name="mail" placeholder="E-mail" required><br><br>
        </div>
        <div>
            <input type="date" id="date" name="year" required><br><br>
        </div>
        <div>
            <input type="password" id="password2" name="pwd" placeholder="Mot de passe" required><br><br>
        </div>
        <div>
            <input type="password" id="confirmPassword" name="cpwd" placeholder="Confirmer le mot de passe" required><br><br>
        </div>
        <div>
            <button type="submit" id="boutonBleu2" value="2" name="inscription_submit">Inscription</button>
        </div>
    </form>
    <p id="dc">Déjà un compte ?</p>
    <a href="Connexion.php">
        <button id="btnRed2" type="button">Connectez vous</button>
    </a>
</body>
</html>
