package org.orquest.gilded.rose;

/**
 * GildedRose class in charge of managing the inventory system of GildedRose Inn.
 *
 * @author Victor Garcia Carrera
 * @since 28 march 2024
 */

class GildedRose {

    private Item[] items;
    public final static int MIN_QUALITY = 0;
    public final static int MAX_QUALITY = 50;
    public final static int SULFURAS_QUALITY = 80;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    /**
     * This method returns the corresponding item from items property
     *  given the corresponding index of that item.
     *
     * @param indexItem the corresponding index of the item in items
     * @return Item that corresponds to indexItem reference
     */
    public Item getItem (int indexItem) {
        return items[indexItem];
    }

    public void updateQuality() {
        for (Item item : items) {
            try {
                ItemType itemType = ItemType.findByItemName(item.name);

                switch (itemType) {
                    case AGED_BRIE:
                        updateQualityAgedBrie(item);
                        break;
                    case SULFURAS:
                        updateQualitySulfuras(item);
                        break;
                    case BACKSTAGE:
                        updateQualityBackstage(item);
                        break;
                    case CONJURED:
                        updateQualityConjured(item);
                        break;
                    case DEFAULT:
                        updateQualityNormal(item);
                }
            } catch (ItemTypeException e) {
                throw new ItemTypeException(String.format("No matching ItemType for " +
                        "the given item name %s. %s", item.name, e.getMessage()));
            }
        }
    }


    private void updateQualityAgedBrie (Item item) {
        item.sellIn -= 1;

        if (outOfDate(item.sellIn)) {
            item.quality += 2;
        } else {
            item.quality += 1;
        }
        checkBasics(item);
    }

    private void updateQualitySulfuras (Item item) throws ItemTypeException {
        if (item.quality != SULFURAS_QUALITY) {
            throw new ItemTypeException(String.format("Sulfuras Legendary item quality " +
                    "should be %d", SULFURAS_QUALITY));
        }
    }

    private void updateQualityBackstage (Item item) {
        item.sellIn -= 1;

        if (outOfDate(item.sellIn)) {
            item.quality = 0;
        } else if (item.sellIn <= 5) {
            item.quality += 3;
        } else if (item.sellIn <= 10) {
            item.quality += 2;
        }
        checkBasics(item);
    }

    private void updateQualityConjured (Item item) {
        item.sellIn -= 1;

        if (outOfDate(item.sellIn)) {
            item.quality -= 4;
        } else {
            item.quality -= 2;
        }
        checkBasics(item);
    }

    private void updateQualityNormal (Item item) {
        item.sellIn -= 1;

        if (outOfDate(item.sellIn)) {
            item.quality -= 2;
        } else {
            item.quality -= 1;
        }
        checkBasics(item);
    }


    private boolean outOfDate (int days) {
        return days < 0;
    }

    private void checkBasics (Item item) {
        if (item.quality < MIN_QUALITY) {
            item.quality = MIN_QUALITY;
        } else if (item.quality > MAX_QUALITY) {
            item.quality = MAX_QUALITY;
        }
    }

}
