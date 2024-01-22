<?php

namespace TimeFusion\Calendar;

require __DIR__ . '\..\App\Validator.php';
use TimeFusion\App\Validator;

class EventValidator extends Validator{

    public function validates(array $data) {
        parent::validates($data);
        $this->validate('name','minLength', 3);
        $this->validate('date','date');
        $this->validate('start','beforeTime', 'end');
        $this->validate('','publicOprivate');
        return $this->errors;
    }

}

?>