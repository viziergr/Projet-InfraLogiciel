<?php

enum Roles: int {
    case Leader = 1;
    case CoLeader = 2;
    case Elder = 3;
    case Member = 4;

    public function getName(): string {
        switch ($this) {
            case Roles::Leader:
                return 'Leader';
            case Roles::CoLeader:
                return 'Co-Leader';
            case Roles::Elder:
                return 'Elder';
            case Roles::Member:
                return 'Member';
            default:
                return 'Unknown';
        }
    }
}

function getRoleByName(string $roleName): ?Roles {
    $roleName = ucfirst($roleName);
    $roleConstant = 'Roles::' . $roleName;

    if (defined($roleConstant)) {
        return constant($roleConstant);
    } else {
        return null;
    }
}
