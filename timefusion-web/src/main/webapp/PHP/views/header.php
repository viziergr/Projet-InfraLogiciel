<?php require __DIR__ . '\..\src\Log\gestion_deconnexion.php'; ?>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href= "../../../CSS/calendar.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>

<nav class="navbar navbar-dark custom-navbar mb-3" style="color: #000352;">
    <a href="/PHP/public/index.html" class="header_title">TimeFusion</a>

    <?php
    // Check if the user is logged in and display the logout button accordingly
    if (isset($_SESSION['compte'])) {
        echo '<a href="?logout=1"><img src="/pictures/se-deconnecter.png" class="logout_logo" alt="Deconnexion"></a>';
    }
    ?>
    
    <div class="dropdown">
        <img src="/pictures/lat.png" alt="Mon panneau déroulant" onclick="toggleDropdown()">
        <div class="dropdown-content">
            <a href="/PHP/public/needLog/Calendrier.php">Mon calendrier</a><br>
            <a href="/PHP/public/needLog/teampanel.php">Mes équipes</a><br>
            <a href="/PHP/public/needLog/requestpanel.php">Mes notifications</a><br>
            <a href="/PHP/public/needLog/Profil.php">Mon profil</a>
        </div>
    </div>
</nav>

<script>
    function toggleDropdown() {
        var dropdownContent = document.querySelector('.dropdown-content');
        dropdownContent.style.display = (dropdownContent.style.display === 'block') ? 'none' : 'block';
    }
</script>
