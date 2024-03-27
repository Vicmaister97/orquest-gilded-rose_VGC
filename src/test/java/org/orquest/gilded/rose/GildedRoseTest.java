package org.orquest.gilded.rose;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the {@link org.orquest.gilded.rose.GildedRose} class.
 * @author Vicotr Garcia Carrera
 * @since 27 march 2024
 */
public class GildedRoseTest {

    private Item[] items;
    private GildedRose gildedRose;

    /**
     * Sets up the items for each test to make them consistent.
     */
    @BeforeEach
    public void setUp () {
        items = new Item[] {
                new Item("+5 Dexterity Vest", 10, 20), //
                new Item("Aged Brie", 2, 0), //
                new Item("Elixir of the Mongoose", 0, 8), //
                new Item("Sulfuras, Hand of Ragnaros", 0, 80), //
                new Item("Backstage passes to a TAFKAL80ETC concert", 11, 20),
                new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
                new Item("Backstage passes to a TAFKAL80ETC concert", 6, 20),
                // this conjured item does not work properly yet
                new Item("Conjured Mana Cake", 3, 6)
        };
        gildedRose = new GildedRose(items);
    }


    /**
     * Tests the updateQuality method for a normal item
     */
    @Test
    public void testUpdateQuality_NormalItem() {

        // Given
        int indexItem = 0;
        Item normalItem = gildedRose.items[indexItem];

        // When
        gildedRose.updateQuality();

        // Then
        assertEquals("+5 Dexterity Vest", normalItem.name);
        assertEquals(9, normalItem.sellIn);
        assertEquals(19, normalItem.quality);

        // When
        gildedRose.updateQuality();

        // Then
        assertEquals("+5 Dexterity Vest", normalItem.name);
        assertEquals(8, normalItem.sellIn);
        assertEquals(18, normalItem.quality);
    }

    /**
     * Tests the updateQuality method for a normal item out of date.
     */
    @Test
    public void testUpdateQuality_NormalItemOutOfDate() {

        // Given
        int indexItem = 2;
        Item normalItem = gildedRose.items[indexItem];

        // When
        gildedRose.updateQuality();

        // Then
        assertEquals("Elixir of the Mongoose", normalItem.name);
        assertEquals(-1, normalItem.sellIn);
        assertEquals(6, normalItem.quality);

        // When
        gildedRose.updateQuality();

        // Then
        assertEquals("Elixir of the Mongoose", normalItem.name);
        assertEquals(-2, normalItem.sellIn);
        assertEquals(4, normalItem.quality);
    }

    /**
     * Tests the updateQuality method for an Aged Bried item
     */
    @Test
    public void testUpdateQuality_AgedBrie() {

        // Given
        int agedBrieIndex = 1;
        Item agedBrieItem = gildedRose.items[agedBrieIndex];

        // When
        gildedRose.updateQuality();

        // Then
        assertEquals("Aged Brie", agedBrieItem.name);
        assertEquals(1, agedBrieItem.sellIn);
        assertEquals(1, agedBrieItem.quality);

        // When
        gildedRose.updateQuality();

        // Then
        assertEquals("Aged Brie", agedBrieItem.name);
        assertEquals(0, agedBrieItem.sellIn);
        assertEquals(2, agedBrieItem.quality);

        // When
        gildedRose.updateQuality();

        // Then
        assertEquals("Aged Brie", agedBrieItem.name);
        assertEquals(-1, agedBrieItem.sellIn);
        assertEquals(4, agedBrieItem.quality);
    }

    /**
     * Tests the updateQuality method for a Legendary item
     */
    @Test
    public void testUpdateQuality_LegendaryItem() {

        // Given
        int legendaryItemIndex = 3;
        Item legendaryItemItem = gildedRose.items[legendaryItemIndex];

        // When
        gildedRose.updateQuality();

        // Then
        assertEquals("Sulfuras, Hand of Ragnaros", legendaryItemItem.name);
        assertEquals(0, legendaryItemItem.sellIn);
        assertEquals(80, legendaryItemItem.quality);

        // When
        gildedRose.updateQuality();

        // Then
        assertEquals("Sulfuras, Hand of Ragnaros", legendaryItemItem.name);
        assertEquals(0, legendaryItemItem.sellIn);
        assertEquals(80, legendaryItemItem.quality);
    }

    /**
     * Tests the updateQuality method for a backstage ticket in the condition of less than 10 days.
     */
    @Test
    public void testUpdateQuality_BackstageItemLessThan10Days() {

        // Given
        int backstageItemIndex = 4;
        Item backstageItem = gildedRose.items[backstageItemIndex];

        // When
        gildedRose.updateQuality();

        // Then
        assertEquals("Backstage passes to a TAFKAL80ETC concert", backstageItem.name);
        assertEquals(10, backstageItem.sellIn);
        assertEquals(21, backstageItem.quality);

        // When
        gildedRose.updateQuality();

        // Then
        assertEquals("Backstage passes to a TAFKAL80ETC concert", backstageItem.name);
        assertEquals(9, backstageItem.sellIn);
        assertEquals(23, backstageItem.quality);
    }

    /**
     * Tests the updateQuality method for a backstage ticket in the condition of less than 5 days.
     */
    @Test
    public void testUpdateQuality_BackstageItemLessThan5Days() {

        // Given
        int backstageItemIndex = 5;
        Item backstageItem = gildedRose.items[backstageItemIndex];

        // When
        gildedRose.updateQuality();

        // Then
        assertEquals("Backstage passes to a TAFKAL80ETC concert", backstageItem.name);
        assertEquals(9, backstageItem.sellIn);
        assertEquals(50, backstageItem.quality);

        // When
        gildedRose.updateQuality();

        // Then
        assertEquals("Backstage passes to a TAFKAL80ETC concert", backstageItem.name);
        assertEquals(8, backstageItem.sellIn);
        assertEquals(50, backstageItem.quality);
    }

    /**
     * Tests the updateQuality method for a backstage ticket in the condition of being out of date.
     */
    @Test
    public void testUpdateQuality_BackstageItemOutOfDate() {

        // Given
        int backstageItemIndex = 6;
        Item backstageItem = gildedRose.items[backstageItemIndex];

        // When
        gildedRose.updateQuality();

        // Then
        assertEquals("Backstage passes to a TAFKAL80ETC concert", backstageItem.name);
        assertEquals(5, backstageItem.sellIn);
        assertEquals(22, backstageItem.quality);

        // When
        gildedRose.updateQuality();

        // Then
        assertEquals("Backstage passes to a TAFKAL80ETC concert", backstageItem.name);
        assertEquals(4, backstageItem.sellIn);
        assertEquals(25, backstageItem.quality);

        // When
        gildedRose.updateQuality();
        gildedRose.updateQuality();
        gildedRose.updateQuality();
        gildedRose.updateQuality();
        gildedRose.updateQuality();

        // Then
        assertEquals("Backstage passes to a TAFKAL80ETC concert", backstageItem.name);
        assertEquals(-1, backstageItem.sellIn);
        assertEquals(0, backstageItem.quality);
    }

}
