package net.thxlotl.cavernous.datagen.custom;

public enum EightDirection {
    NORTH,
    NORTHEAST,
    EAST,
    SOUTHEAST,
    SOUTH,
    SOUTHWEST,
    WEST,
    NORTHWEST;

    // store in an array so we can rotate easily
    private static final EightDirection[] VALUES = values();

}
