<?php

if (isset($_GET['logout']) && $_GET['logout'] == 1) {
    unset($_SESSION['compte']);
    header("Location: /PHP/public/index.html");
}

?>