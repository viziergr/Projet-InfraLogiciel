<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../CSS/calendar.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>

<nav class="navbar navbar-dark bg-primary mb-3">
    <a href="/index.php" class="navbar-brand">Mon calendrier</a>
</nav>

<?php 
require './src/Date/Month.php';
$month = new TimeFusion\Date\Month($_GET['month'] ?? null, $_GET['year'] ?? null);
$start = $month->getFirstDay();
if ($start->format('N') !== '1') {
    $start->modify('last monday');
}
?>

<div class='d-flex flex-row align-items-center justify-content-between mx-sm-3'>
    <h1><?= $month->__toString(); ?></h1>
    <div>
        <a href="/PHP/Calendrier.php?month=<?= $month->previousMonth()->month; ?>&year=<?= $month->previousMonth()->year; ?>" class="btn btn-primary">&lt;</a>
        <a href="/PHP/Calendrier.php?month=<?= $month->nextMonth()->month; ?>&year=<?= $month->nextMonth()->year; ?>" class="btn btn-primary">&gt;</a>
    </div>
</div>

<table class="calendar__table calendar__table--<?= $month->getWeeks(); ?>weeks">
    <?php for ($i = 0; $i < $month->getWeeks(); $i++): ?>
    <tr>
        <?php 
            foreach ($month->days as $k => $day): 
            $date = (clone $start)->modify("+".($k + $i*7). "days")
        ?>
        <td class="<?= $month->withinMonth($date) ? '' : 'calendar__othermonth'; ?>" >
            <?php if ($i === 0): ?>
                <div class='calendar__weekday'><?= $day; ?></div>
            <?php endif; ?>
            <div class='calendar__day'><?= $date->format('d'); ?></div>
        </td>
        <?php endforeach; ?>
    </tr>
    <?php endfor; ?>
</table>

</body>
</html>