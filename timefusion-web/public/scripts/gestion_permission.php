<?php

if($_SERVER['REQUEST_METHOD'] === 'POST' && isset($_POST['promote']) && isset($_POST['member_id']) && isset($_POST['team_id'])) {
    $teams->promote($_POST['member_id'],$_POST['team_id']);    
}

if($_SERVER['REQUEST_METHOD'] === 'POST' && isset($_POST['relegate']) && isset($_POST['member_id']) && isset($_POST['team_id'])) {
    $teams->relegate($_POST['member_id'],$_POST['team_id']);    
}
?>