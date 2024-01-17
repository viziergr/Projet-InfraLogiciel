<?php session_start();?> 
<!DOCTYPE html>
<html>
<head>
    <title>Profil</title>
    <link rel="stylesheet" href="..\..\CSS\code.css">
</head>
<body>

    <div class="bandeV">
        <div class="dropdown">
            <img id="trbar" src="..\..\pictures\trBarre.png" alt="redirection"/>
            <div class="dropdown-content">
                <p>Team</p>
                <p>Profil</p>
                <p>Calendrier</p>
            </div>
        </div>
        <h1>TimeFusion</h1>
        <a href="index.html">
            <img id ="logo" src="..\..\pictures\Logo.png">
        </a>
    </div>

    <div class="information">
        <h1>Profil</h1>
        
        <?php
        $userName = $_SESSION['compte']; // récupération du nom d'utilisateur de l'utilisateur connecté
        echo '<p> Nom d\'utilisateur : ' . $userName . '</p>'; // affichage du nom d'utilisateur
        ?>
        
        <!-- Partie pour modification de nom d'utilisateur (extension à voir plus tard)
        <div class="dropdownModif">
            <img src="C:\Users\gross\Documents\Eseo\E4e\S7\LD\projetld\Projet-InfraLogiciel\timefusion-web\src\main\webapp\pictures\text-edit-tool-icon-with-pencil-free-vector-removebg-preview.png" alt="Modifier">
            <div class="dropdownUser-content">
                <form>
                    <label for="new-password">Nouveau mot de passe:</label>
                    <input type="password" id="new-password" name="new-password"><br><br>

                    <label for="confirm-password">Confirmer le mot de passe:</label>
                    <input type="password" id="confirm-password" name="confirm-password"><br><br>
                </form>
            </div>
        </div> 
        -->

        <p id="password">Mot de passe: "afficher en php le mdp de l'utilisateur"</p>
        
        <!-- Partie pour modification de mot de passe (extension à voir plus tard)
        <div class="dropdownModif">
            <img src="C:\Users\gross\Documents\Eseo\E4e\S7\LD\projetld\Projet-InfraLogiciel\timefusion-web\src\main\webapp\pictures\text-edit-tool-icon-with-pencil-free-vector-removebg-preview.png" alt="Modifier">
            <div class="dropdownMDP-content">
                <form>
                    <label for="new-password">Nouveau mot de passe:</label>
                    <input type="password" id="new-password" name="new-password">

                    <label for="confirm-password">Confirmer le mot de passe:</label>
                    <input type="password" id="confirm-password" name="confirm-password">
                </form>
            </div>
        </div>
        -->

    </div>

</body>
</html>
