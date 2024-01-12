<?php

namespace TimeFusion\Calendar;

class EventValidator {

    public function validate(array $data) {
        $this->validate('name','minlength',3);
    }

}

?>