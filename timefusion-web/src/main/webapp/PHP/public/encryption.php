<?php

$workload = 12;
$password = 'paul2003';
$hash = password_hash($password, PASSWORD_BCRYPT, ['cost' => $workload]);

echo $hash;

?>
