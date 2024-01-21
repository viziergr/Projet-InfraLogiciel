<?php

namespace TimeFusion\Team;

class Roles {
    const Leader = 1;
    const CoLeader = 2;
    const Elder = 3;
    const Member = 4;

    public static function getName($role) {
        switch ($role) {
            case self::Leader:
                return 'Leader';
            case self::CoLeader:
                return 'Co-Leader';
            case self::Elder:
                return 'Elder';
            case self::Member:
                return 'Member';
            default:
                return 'Unknown';
        }
    }
}

function getRoleByName($roleName) {
    $roleName = ucfirst($roleName);
    $roleConstant = 'Roles::' . $roleName;

    if (defined($roleConstant)) {
        return constant($roleConstant);
    } else {
        return null;
    }
}
