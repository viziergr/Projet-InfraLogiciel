<?php
echo __DIR__;
if (isset($_GET['logout']) && $_GET['logout'] == 1) {
    unset($_SESSION['compte']);
    header("Location: ../index.html");
}

?>