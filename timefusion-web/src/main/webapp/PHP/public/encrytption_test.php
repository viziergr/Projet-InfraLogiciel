<?php
$password = "password";

// Générer un hash Bcrypt avec le préfixe $2a$
$hashedPassword = crypt($password, '$2a$12$' . bin2hex(random_bytes(22)));

// Afficher le hash généré
echo "Mot de passe original : " . $password . "\n";
echo "Hash Bcrypt : " . $hashedPassword . "\n";

// Vérifier le mot de passe
$enteredPassword = "password";
$hashedPasswordFromJava = "$2a$12$9cnM6FmT3QIN2NWe4nFDVe7t5t29oVGnFo.5QDOq85.VhuUB0aRC.";

//echo "Hash Java Bcrypt: " . $hashedPasswordFromJava . "\n";

if (password_verify($enteredPassword, $hashedPassword)) {
    echo "Le mot de passe est correct.\n";
} else {
    echo "Le mot de passe est incorrect.\n";
}
?>

