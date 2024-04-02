2.0.0
=====
- Refactored GildedRose.java setting items as private property.
- Added and improved JavaDoc documentation.
- Removed build and test execution files from repository as
  they are automatically generated when executing
  gradle wrapper.
- Created ItemType enumeration to simplify updateQuality logic
  for each particular type of item.
- Use ItemType findByItemName method to improve checks of each
  item special conditions in updateQuality method.
- Improved updateQuality of each item (with it's special conditions)
  with separated methods for updateQuality (due to SRP principle).
- Improved GildedRoseTest with correct behavior of backstage items.
- Created ItemTypeException for managing errors with ItemType


1.0.0
=====
- Added unitary TESTING before refactor/changes to
  ensure we keep the original intended behavior.
- Added new requested requirement related to
  Conjured items with a simple solution.