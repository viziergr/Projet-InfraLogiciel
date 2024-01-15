<?php 

require '../scripts/bootstrap.php';
require '../scripts/Calendar/Month.php';
require '../scripts/Calendar/Events.php';

$mysqli = connectDB();
$events = new TimeFusion\Calendar\Events($mysqli);
$month = new TimeFusion\Calendar\Month($_GET['month'] ?? null, $_GET['year'] ?? null);
$start = $month->getFirstDay();
if ($start->format('N') !== '1') {
    $start->modify('last monday');
}
$end = (clone $start)->modify('+'. (4 * 7 -1).' days');
$events = $events->getEventsBetweenByDay($start,$end);
require '../includes/header.php';
?>

<div class="calendar">
    <div class='d-flex flex-row align-items-center justify-content-between mx-sm-3'>
        <h1><?= $month->__toString(); ?></h1>
        <div>
            <a href="Calendrier.php?month=<?= $month->previousMonth()->month; ?>&year=<?= $month->previousMonth()->year; ?>" class="btn btn-primary">&lt;</a>
            <a href="Calendrier.php?month=<?= $month->nextMonth()->month; ?>&year=<?= $month->nextMonth()->year; ?>" class="btn btn-primary">&gt;</a>
        </div>
    </div>

    <table class="calendar__table calendar__table--<?= $month->getWeeks(); ?>weeks">
        <?php for ($i = 0; $i < $month->getWeeks(); $i++): ?>
        <tr>
            <?php 
                foreach ($month->days as $k => $day): 
                $date = (clone $start)->modify("+".($k + $i*7). "days");
                $eventsForDay = $events[$date->format('Y-m-d')] ?? [];    
            ?>
            <td class="<?= $month->withinMonth($date) ? '' : 'calendar__othermonth'; ?>" >
                <?php if ($i === 0): ?>
                    <div class='calendar__weekday'><?= $day; ?></div>
                <?php endif; ?>
                <div class='calendar__day'><?= $date->format('d'); ?></div>
                <?php foreach ($eventsForDay as $event): ?>
                <div class="calendar__event">
                    <?= (new \DateTime($event['start_time']))->format('H:i') ?> - <a href="eventpanel.php?id=<?= $event['id']; ?>"> <?= h($event['title']); ?></a>
                </div>
                <?php endforeach; ?>
            </td>
            <?php endforeach; ?>
        </tr>
        <?php endfor; ?>
    </table>
    <a href="add.php" class="calendar__button">+</a>
</div>



<?php 
require '../includes/footer.php';
?>