<?php include '../src/Log/gestion_inscription.php'; ?>

<?php require __DIR__ . '\..\bootstrap.php'; 
sess_exists();
?>

<?php session_start();?> 
<!DOCTYPE html>
<html>
<head>
    <title>Profil</title>
    <link rel="stylesheet" href="C:\xampp\htdocs\code.css">
    <style>
        .bandeV {
            position: fixed;
            top: 0;
            left: 0;
            width: 20%;
            height: 100%;
            background-color: #000352;
        }

        .bandeV #trbar{
            width: 15%;
            padding-top: 0%;
            padding-bottom: 0;
            padding-left: 0%;
            margin: 1%;
        }

        .bandeV h1{
            color: #FFF;
            position: absolute;
            bottom: 0;
            font-weight: 400;
            left: 0;
            width:100%;
            text-align: center;
            font-size: 1.75em;
        }

        .bandeV #logo{
            width: 30%;
            padding-top: 0%;
            padding-bottom: 0;
            padding-left: 0%;
            margin: 1%;
            margin-left: 35%;
            position: absolute;
            bottom: 0;
            margin-bottom: 20%;
        }

        .dropdown {
            top:0;
            margin : 1%;
        }

        .dropdown-content {
            display: none;
            position: absolute;
            background-color: #5458b8;
            min-width: 90%;
            min-height: auto;
            box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
            padding: 1%;
            z-index: 1;
        }

        .dropdown:hover .dropdown-content {
            display: block;
        }

        .profil{
            width: 30%;
            height: 100%;
            background-color: #ffffff;
            position: absolute;
            top: 0;
            left: 20%;
        }
        .information{
            width: 50%;
            height: 100%;
            background-color: #ffffff;
            position: absolute;
            top: 0;
            left: 50%;
        }

        .profil h1{
            color : #000000;
            padding-top : 30%;
            padding-bottom : 0;
            font-family: League Gothic;
            font-weight: 400;
            font-size: 4em;
            font-style: normal;
            text-transform: uppercase;
            letter-spacing: -0.1em;
            text-align: center;
            margin-bottom: 0;
            margin-top: 0;
            line-height: normal;
        }


        .information #fname, #lname, #email, #year{
            background-color : #000352;
            font-size: 1em;
            font-family:Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;
            text-transform: uppercase;
            padding: 1%;
            font-weight: 700;
            margin-left: 13%;
            margin-right : 13%;
            margin-top: 10%;
            color : #FFF;
            padding : 1%;
            border-radius : 0%;
            border : 1px #000352;            
        }

        .information #fname{
            margin-top: 30%;
        }

        .modif p{
            font-size: 1em;
            font-family:Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;
            text-transform: uppercase;
            padding: 1%;
            font-weight: 700;
            margin-left: 13%;
            margin-right : 13%;
            margin-top: 10%;
            color : ##000352;
            padding : 1%;
            border-radius : 10%;
            border : 1px #000352;
            text-align: center;}


        .traitV{
            border-left: 1px solid black;
            height: 80%;
            position: absolute;
            left: 0%;
            margin-left: 0%;
            margin-top: 10%;
            top: 0;}
    </style>
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

    <div class="profil">
        <h1>Profil</h1>
    </div>
    
    <div class="information">
        <div class="traitV"></div>

        <?php

        $prenom =  $_SESSION['compte'];
        echo '<p id="fname"> Prénom : ' . $prenom . '</p>';

        $nom = $_SESSION['compte]'];
        echo '<p id="lname"> Nom : ' . $nom . '</p>';

        $email = $_SESSION['compte'];
        echo '<p id="email"> Email : ' . $email . '</p>';

        $annee = $_SESSION['compte'];
        echo '<p id="year"> Année : ' . $annee . '</p>';

        ?>
        
        <div class="modif">
            <a href="..\..\newPassword.php">
                <p> Modifier votre profil </p>
            </a>
        </div>
    </div>

</body>
</html>
