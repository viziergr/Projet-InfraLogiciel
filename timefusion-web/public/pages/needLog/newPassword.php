<?php 
include __DIR__ . '/../../scripts\bootstrap.php';
include __DIR__ . '/../../includes/header.php'; 
?>

<DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Réinitialiser le mot de passe</title>
    <link rel="stylesheet" href="../../../CSS/code.css">
</head>
<body>
    <div class="modifPassword">
        <?php
        if ($_SERVER["REQUEST_METHOD"] == "POST") {
            $newPassword = $_POST["new_password"];
            $confirmPassword = $_POST["conf_password"];

            if ($newPassword === $confirmPassword) {
                // Hasher le nouveau mot de passe
                // $hashedPassword = password_hash($newPassword, PASSWORD_DEFAULT);

                connectDB();

                $id = $_SESSION["user_id"]; // Remplacez par l'identifiant de l'utilisateur connecté
                $sql = "UPDATE user SET password = '$newPassword' WHERE id = $id";

                if ($mysqli->query($sql) === TRUE) {
                    echo "Mot de passe mis à jour avec succès.";
                } else {
                    echo "Erreur lors de la mise à jour du mot de passe : " . $mysqli->error;
                }

                $conn->close();
            } else {
                echo "Les mots de passe ne correspondent pas.";
            }
        }
        ?>
        <form method="POST" action="">
            <div>
                <input type="password" id="new_password" name="new_password" placeholder="Nouveau mot de passe" required><br><br>
            </div>
            <div>    
                <input type="password" id="conf_password" name="conf_password" placeholder="Confirmer mot de passe" required>
            </div>
            <div>
                <button type="submit">Réinitialiser le mot de passe</button>
            </div>
        </form>
    </div>

<?php
include __DIR__ . '/../../includes/header.php'; 
?>
