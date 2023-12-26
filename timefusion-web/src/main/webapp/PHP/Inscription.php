<?php include 'gestion_inscription.php'; ?>

<!DOCTYPE html>
<html lang="en">
<html>
<head>
    <title>Inscription</title>
    <link rel="stylesheet" href="..\CSS\code.css">
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
    <img id="img2" src="..\pictures\Logo.png" alt="Logo Time Fusion">
    <div class="home">
        <a href="index.html">
            <img id="home" src="..\pictures\home.png" alt="Accueil">
        </a>
    </div>
    <form>
        <div>
            <input type="text" id="prenom" name="prenom" placeholder="Prénom" required><br><br>
        </div>
        <div>
            <input type="text" id="nom" name="nom" placeholder="Nom" required><br><br>
        </div>
        <div>
            <input type="email" id="email2" name="email" placeholder="E-mail" required><br><br>
        </div>
        <div>
            <input type="date" id="date" name="date" required><br><br>
        </div>
        <div>
            <input type="text" id="username" name="username" placeholder="Nom d'utilisateur" required><br><br>
        </div>
        <div>
            <input type="password" id="password2" name="password" placeholder="Mot de passe" required><br><br>
        </div>
        <div>
            <input type="password" id="confirmPassword" name="confirmPassword" placeholder="Confirmer le mot de passe" required><br><br>
        </div>
        <div>
            <input type="submit" id="boutonBleu2" value="Inscription">
        </div>
    </form>
    <p id="dc">Déjà un compte ?</p>
    <a href="Connexion.html">
        <button id="btnRed2" type="button">Connectez vous</button>
    </a>
</body>
</html>