<?php
require __DIR__ .'\..\..\views\header.php';
?>

<body>

    <div class="bandeV">
        <div class="dropdown">
            <img id="trbar" src="/pictures/lat.png" alt="Mon panneau déroulant" />
            <div class="dropdown-content">
                <p>Team</p>
                <p>Profil</p>
                <p>Calendrier</p>
            </div>
        </div>
        <h1>TimeFusion</h1>
        <img id ="logo" src="C:\Users\gross\Documents\Eseo\E4e\S7\LD\projetld\Projet-InfraLogiciel\timefusion-web\src\main\webapp\pictures\Logo.png" alt="Logo Time Fusion">
    </div>

    <div class="information">
        <h1>Profil</h1>
        
        <p id="username">Nom d'utilisateur: "affciher en php le nom de l'utilisateur"</p>
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

        <p id="password">Mot de passe: "affciher en php le mdp de l'utilisateur"</p>
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

    </div>


</body>
</html>
