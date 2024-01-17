<?php

namespace TimeFusion\Calendar;

require __DIR__ . '\..\App\Validator.php';
use TimeFusion\App\Validator;

class EventValidator extends Validator{

    public function validate(array $data) {
        $this->validate('name','minlength',3);
    }

}

?>