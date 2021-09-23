package game.enums;

/**
 * Use this enum class to give `buff` or `debuff`.
 * It is also useful to give a `state` to abilities or actions that can be attached-detached.
 */
public enum Status {
    HOSTILE_TO_ENEMY, // use this capability to be hostile towards something (e.g., to be attacked by enemy)
    HOSTILE_TO_PLAYER,
    IS_PLAYER,
    IS_YHORM,
    IS_VALLEY,
    ABLE_TO_BUY,
    RAGE_MODE,
    DISARMED,
    STUNNED,
    IMMUNE_TO_BURN,
}
