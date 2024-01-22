<?php

if(isset($_POST['quit_team'])) {
    $teams->quitTeam($userId,$team_id);
    header('Location: teampanel.php');
    exit();
}
if(isset($_POST['promote'])) {
    $member_id = $_POST['member_id'];
    $teams->promote($member_id,$team_id);
    header('Location: insideteampanel.php?');
    exit();
}
if(isset($_POST['relegate'])) {
    $member_id = $_POST['member_id'];
    $teams->relegate($member_id,$team_id);
    header('Location: insideteampanel.php?team_id='.$team_id);
    exit();
}

?>