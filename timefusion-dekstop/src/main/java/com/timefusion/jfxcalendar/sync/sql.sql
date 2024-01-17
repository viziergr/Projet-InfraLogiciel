--List<Event>

SELECT *
    FROM event
    WHERE id IN (SELECT event_id FROM event_participant WHERE participant_id = 1);


--
SELECT
    participant_id
FROM
    event_participant
WHERE
    event_id IN(
    SELECT
        event_id
    FROM
        event_participant
    WHERE
        participant_id = 1
) and participant_id != 1;


-- List<User>
SELECT
    *
FROM
    User
WHERE
    id IN(
    SELECT
        participant_id
    FROM
        event_participant
    WHERE
        event_id = 1 AND participant_id != 1
);

-- List<User>

SELECT
    *
FROM
    User
WHERE
    id IN(
    SELECT
        creator_id
    FROM event
WHERE
    id = 1
);


