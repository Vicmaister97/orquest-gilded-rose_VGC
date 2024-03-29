package org.orquest.gilded.rose;

import java.util.EnumSet;

/**
 * Enumeration used to simplify updateQuality logic for each particular
 * type of item in {@link org.orquest.gilded.rose.GildedRose} class.
 *
 * @author Victor Garcia Carrera
 * @since 27 march 2024
 */
public enum ItemType {
    AGED_BRIE("Aged Brie"),
    LEGENDARY("Legendary"),
    BACKSTAGE("Backstage"),
    CONJURED("Conjured"),
    DEFAULT("Normal");

    private final String type;

    ItemType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    /**
     * Method for getting the corresponding ItemType of an Item
     *  with the name of the Item.
     *
     * @param itemName is the name of the Item which we want to obtain it's ItemType
     * @return ItemType value for the corresponding itemName
     */
    public ItemType findByItemName (String itemName) {
        return EnumSet.allOf(ItemType.class)
                .stream()
                .filter(itemType -> formatString(itemName).contains(formatString(itemType.getType())))
                .findFirst()
                .orElse(DEFAULT);
    }

    /**
     * We compare only characters from a-z and A-Z (normal alphabet) and
     *  case-insensitive to provide flexibility to the naming of the items.
     *
     * @param original String to be formatted to properly obtain the ItemType of an Item
     * @return String with only alphabet characters in lowercase
     */
    private String formatString (String original) {
        return original.replaceAll("[^a-zA-Z]", "").toLowerCase();
    }

}