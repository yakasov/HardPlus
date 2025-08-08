# Hard Plus

*Overwrites Hard difficulty!*

Intended for use with [StrongholdEssences](https://github.com/yakasov/StrongholdEssences) (which forces exploration to structures before entering The End)

## General

Regional Difficulty (and therefore Local Difficulty) scaling has been increased:
- The moon phase is always counted as being a full moon, and consequently always adds the maximum difficulty modifier
- The 'chunk inhabited' difficulty factor increases 6x faster
- The 'time played' difficulty factor is doubled
- The starting difficulty for the calculation has been increased by 1.33x

These result in Regional Difficulty topping out at about 9 rather than 6.75.

## Mobs

General
- Mobs now have a follow range of 24 blocks (from 16)
- Mobs are willing to take more fall damage to follow a target (roughly 1.25x)
- Max group spawn size of mobs has been increased to 6 (from 4)
- The cap on spawns per mob per chunk has been increased to 6 (from 4)
- The chance for mobs to wear armour has been increased to 95% ^ Piece Number (from 90% ^ Piece Number)
- The chance for mobs to wear higher grade armour has been doubled
- The chance for mobs to have enchanted armour has been increased by 1.5x

Bogged
- Can now rarely spawn outside of swamps
- Regular attack interval decreased to 1.5s (from 1.75s)
- Hard attack interval decreased to 1s (from 1.25s)

Cave Spiders
- Instead of inflicting Poison I for 15 seconds, they now inflict Poison II for 10 seconds

Dragon
- Fireballs have an increased radius

Drowned
- Likely spawn chance increased to 1/10 (from 1/15)
- Unlikely spawn chance increased to 1/20 (from 1/40)

Endermen
- Ender Pearl drop chance increased to 66% (from 50%)

Ghasts
- Two Ghasts can spawn per chunk (from one)

Husks
- Can now rarely spawn outside of deserts
- No longer require access to the sky to spawn

Illusioners
- Can always throw Blindness potions

Phantoms
- Group size increased to 2 - 6 (from 1 - 4)
- Max size increased by 2.5x

Piglins
- Ender Pearls are half as likely to be gained from trading, and will have half the quantity (1 - 2, previously 2 - 4)

Slimes
- Small Slimes deal 0.5 damage

Spiders
- Chance to have status effects increased to 25% x CRD (from 10% x CRD)
- Spiders can spawn with Resistance, Fire Resistance, Infested, Oozing or Weaving

Strays
- Can now rarely spawn outside of ice biomes
- No longer require access to the sky to spawn
- Instead of inflicting Slowness for 30 seconds, they now inflict Slowness II for 20 seconds

Witches
- Have a 15% chance to use a stronger potion (excl. Poison and Harming)

Zombies
- Chance to spawn with equipment increased to 15% (from 5%)
- Can always pick up loot
- Will always set their target on fire if they are also on fire
- Always gain a random follow distance bonus, and this bonus is increased by 25%
- Chance for a zombie to be classified as a 'leader' (can summon reinforcements and gains extra health) has been doubled

## Items

- Diamond Tool enchantability increased to 13 (from 10)
- Diamond Armour enchantability increased to 13 (from 10)

## Player

Damage taken is equal to 2x Normal difficulty (or 1.33x Hard difficulty)

Shields are disabled for 0.5s after blocking *any* attack (they are still disabled for longer if they would be normally)

On death, dropped experience is equal to half your experience (capped at 1000XP, ~level 26)

The discount provided to players from villagers has been capped to roughly one and a half cures

## Structures

5/10 End Crystals are now guarded in The End (from 2/10)

The chance for a village to generate as abandoned is roughly 33% (from 2%)

The End Portal will never spawn with any eyes in, requiring all 12 to be obtained

## World

Fire has an increased chance to spread (roughly 1.25x Hard difficulty).