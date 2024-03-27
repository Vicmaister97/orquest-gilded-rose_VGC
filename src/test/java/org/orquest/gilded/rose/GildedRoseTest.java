package org.orquest.gilded.rose;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.RegisterExtension;

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


    @Test
    public void testUpdateQuality_NormalItem() {
        // Given
        int normalItemIndex = 0;
        Item normalItem = items[normalItemIndex];

        // When
        gildedRose.updateQuality();

        // Then
        assertEquals("+5 Dexterity Vest", gildedRose.items[normalItemIndex].name);
        assertEquals(9, gildedRose.items[normalItemIndex].sellIn);
        assertEquals(19, gildedRose.items[normalItemIndex].quality);

        // When
        gildedRose.updateQuality();

        // Then
        assertEquals("+5 Dexterity Vest", gildedRose.items[normalItemIndex].name);
        assertEquals(8, gildedRose.items[normalItemIndex].sellIn);
        assertEquals(18, gildedRose.items[normalItemIndex].quality);
    }

    /**
     * Tests the updateQuality method for a normal item out of date.
     */
    @Test
    public void testUpdateQuality_NormalItemOutOfDate() {
        // Given
        int normalItemIndex = 2;
        Item normalItem = items[normalItemIndex];

        // When
        gildedRose.updateQuality();

        // Then
        assertEquals("Elixir of the Mongoose", gildedRose.items[normalItemIndex].name);
        assertEquals(-1, gildedRose.items[normalItemIndex].sellIn);
        assertEquals(6, gildedRose.items[normalItemIndex].quality);

        // When
        gildedRose.updateQuality();

        // Then
        assertEquals("Elixir of the Mongoose", gildedRose.items[normalItemIndex].name);
        assertEquals(-2, gildedRose.items[normalItemIndex].sellIn);
        assertEquals(4, gildedRose.items[normalItemIndex].quality);
    }

    @Test
    public void testUpdateQuality_AgedBrie() {
        // Given
        int agedBrieIndex = 1;
        Item normalItem = items[agedBrieIndex];

        // When
        gildedRose.updateQuality();

        // Then
        assertEquals("Aged Brie", gildedRose.items[agedBrieIndex].name);
        assertEquals(1, gildedRose.items[agedBrieIndex].sellIn);
        assertEquals(1, gildedRose.items[agedBrieIndex].quality);

        // When
        gildedRose.updateQuality();

        // Then
        assertEquals("Aged Brie", gildedRose.items[agedBrieIndex].name);
        assertEquals(0, gildedRose.items[agedBrieIndex].sellIn);
        assertEquals(2, gildedRose.items[agedBrieIndex].quality);

        // When
        gildedRose.updateQuality();

        // Then
        assertEquals("Aged Brie", gildedRose.items[agedBrieIndex].name);
        assertEquals(-1, gildedRose.items[agedBrieIndex].sellIn);
        assertEquals(4, gildedRose.items[agedBrieIndex].quality);
    }

    @Test
    public void testUpdateQuality_LegendaryItem() {
        // Given
        int legendaryItemIndex = 3;
        Item normalItem = items[legendaryItemIndex];

        // When
        gildedRose.updateQuality();

        // Then
        assertEquals("Sulfuras, Hand of Ragnaros", gildedRose.items[legendaryItemIndex].name);
        assertEquals(0, gildedRose.items[legendaryItemIndex].sellIn);
        assertEquals(80, gildedRose.items[legendaryItemIndex].quality);

        // When
        gildedRose.updateQuality();

        // Then
        assertEquals("Sulfuras, Hand of Ragnaros", gildedRose.items[legendaryItemIndex].name);
        assertEquals(0, gildedRose.items[legendaryItemIndex].sellIn);
        assertEquals(80, gildedRose.items[legendaryItemIndex].quality);
    }

    /**
     * Tests the updateQuality method for a backstage ticket for the condition of less than 10 days.
     */
    @Test
    public void testUpdateQuality_BackstageItemLessThan10Days() {
        // Given
        int backstageItemIndex = 4;
        Item normalItem = items[backstageItemIndex];

        // When
        gildedRose.updateQuality();

        // Then
        assertEquals("Backstage passes to a TAFKAL80ETC concert", gildedRose.items[backstageItemIndex].name);
        assertEquals(10, gildedRose.items[backstageItemIndex].sellIn);
        assertEquals(21, gildedRose.items[backstageItemIndex].quality);

        // When
        gildedRose.updateQuality();

        // Then
        assertEquals("Backstage passes to a TAFKAL80ETC concert", gildedRose.items[backstageItemIndex].name);
        assertEquals(9, gildedRose.items[backstageItemIndex].sellIn);
        assertEquals(23, gildedRose.items[backstageItemIndex].quality);
    }

    /**
     * Tests the updateQuality method for a backstage ticket for the condition of less than 5 days.
     */
    @Test
    public void testUpdateQuality_BackstageItemLessThan5Days() {
        // Given
        int backstageItemIndex = 5;
        Item normalItem = items[backstageItemIndex];

        // When
        gildedRose.updateQuality();

        // Then
        assertEquals("Backstage passes to a TAFKAL80ETC concert", gildedRose.items[backstageItemIndex].name);
        assertEquals(9, gildedRose.items[backstageItemIndex].sellIn);
        assertEquals(50, gildedRose.items[backstageItemIndex].quality);

        // When
        gildedRose.updateQuality();

        // Then
        assertEquals("Backstage passes to a TAFKAL80ETC concert", gildedRose.items[backstageItemIndex].name);
        assertEquals(8, gildedRose.items[backstageItemIndex].sellIn);
        assertEquals(50, gildedRose.items[backstageItemIndex].quality);
    }

    /**
     * Tests the updateQuality method for a backstage ticket for the condition of being out of date.
     */
    @Test
    public void testUpdateQuality_BackstageItemOutOfDate() {
        // Given
        int backstageItemIndex = 6;
        Item normalItem = items[backstageItemIndex];

        // When
        gildedRose.updateQuality();

        // Then
        assertEquals("Backstage passes to a TAFKAL80ETC concert", gildedRose.items[backstageItemIndex].name);
        assertEquals(5, gildedRose.items[backstageItemIndex].sellIn);
        assertEquals(22, gildedRose.items[backstageItemIndex].quality);

        // When
        gildedRose.updateQuality();

        // Then
        assertEquals("Backstage passes to a TAFKAL80ETC concert", gildedRose.items[backstageItemIndex].name);
        assertEquals(4, gildedRose.items[backstageItemIndex].sellIn);
        assertEquals(25, gildedRose.items[backstageItemIndex].quality);

        // When
        gildedRose.updateQuality();
        gildedRose.updateQuality();
        gildedRose.updateQuality();
        gildedRose.updateQuality();
        gildedRose.updateQuality();

        // Then
        assertEquals("Backstage passes to a TAFKAL80ETC concert", gildedRose.items[backstageItemIndex].name);
        assertEquals(-1, gildedRose.items[backstageItemIndex].sellIn);
        assertEquals(0, gildedRose.items[backstageItemIndex].quality);
    }

}
