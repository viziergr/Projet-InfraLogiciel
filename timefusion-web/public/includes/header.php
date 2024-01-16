<?php include '../scripts/gestion_deconnexion.php'; ?>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../assets/CSS/calendar.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>

<nav class="navbar navbar-dark bg-primary mb-3">
    <a href="/index.html" class="navbar-brand">TimeFusion</a>

    <?php
    // Check if the user is logged in and display the logout button accordingly
    if (isset($_SESSION['compte'])) {
        echo '<a href="?logout=1" class="btn btn-light">Déconnexion</a>';
    }
    ?>
</nav>
