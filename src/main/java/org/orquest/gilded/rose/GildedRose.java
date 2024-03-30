package org.orquest.gilded.rose;

/**
 * GildedRose class in charge of managing the inventory system of GildedRose Inn.
 *
 * @author Victor Garcia Carrera
 * @since 28 march 2024
 */

class GildedRose {

    private Item[] items;

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
            ItemType itemType = ItemType.findByItemName(item.name);
            if (!itemType.equals(ItemType.AGED_BRIE) && !itemType.equals(ItemType.BACKSTAGE)) {
                if (item.quality > 0) {
                    if (!itemType.equals(ItemType.SULFURAS)) {
                        if (itemType.equals(ItemType.CONJURED)) {
                            item.quality = item.quality - 2;
                        } else {
                            item.quality = item.quality - 1;
                        }
                    }
                }
            } else {
                if (item.quality < 50) {
                    item.quality = item.quality + 1;

                    if (itemType.equals(ItemType.BACKSTAGE)) {
                        if (item.sellIn < 11) {
                            if (item.quality < 50) {
                                item.quality = item.quality + 1;
                            }
                        }

                        if (item.sellIn < 6) {
                            if (item.quality < 50) {
                                item.quality = item.quality + 1;
                            }
                        }
                    }
                }
            }

            if (!itemType.equals(ItemType.SULFURAS)) {
                item.sellIn = item.sellIn - 1;
            }

            if (item.sellIn < 0) {
                if (!itemType.equals(ItemType.AGED_BRIE)) {
                    if (!itemType.equals(ItemType.BACKSTAGE)) {
                        if (item.quality > 0) {
                            if (!itemType.equals(ItemType.SULFURAS)) {
                                if (itemType.equals(ItemType.CONJURED)) {
                                    item.quality = item.quality - 2;
                                } else {
                                    item.quality = item.quality - 1;
                                }
                            }
                        }
                    } else {
                        item.quality = 0;
                    }
                } else {
                    if (item.quality < 50) {
                        item.quality = item.quality + 1;
                    }
                }
            }
        }
    }
}
