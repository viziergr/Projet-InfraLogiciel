<?php

dd('ok');

if($_SERVER['REQUEST_METHOD'] === 'POST'&& isset($_POST['promote']) && isset($_POST['member_id']) && isset($_POST['team_id'])) {
    $member_id = $_POST['member_id'];
    $teams->promote($member_id,$team_id);
    header('Location: ../pages/insideteampanel.php?');
    exit();
}
if($_SERVER['REQUEST_METHOD'] === 'POST'&& isset($_POST['relegate']) && isset($_POST['member_id']) && isset($_POST['team_id'])) {
    $member_id = $_POST['member_id'];
    $teams->relegate($member_id,$team_id);
    header('Location: ../pages/insideteampanel.php?team_id='.$team_id);
    exit();
}

?>