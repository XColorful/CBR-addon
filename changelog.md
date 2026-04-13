### 0.5.x

#### 0.5.4
> Add 26.1.2neoforge
- Auto give wooden sword (for activating CBR addon datapack) when first login (In 1.21.10+ single player is second login)
- Add built-in void dimension (battleroyale:world) for game world
- Give login item to respawned player
- Give survival lobby item
- Restrict lobby protection message to the triggering player
- Add CBR 0.5.4 event tag

#### 0.5.3
> Add 26.1neoforge
Right-click wooden sword in creative mode to enable all datapack functions

Add option scoreboard (cbraddon):
- Enable/disable server, game, effect option separately

Add extended feature:
- lobby protection
- Join team by block
- Give player command book as login item
- Teleport non-game player spectator to lobby after game

Add command book:
- Auto give team join command book when teleport to lobby
- Right-click wooden sword in creative mode to get game command book

Item function:
- Right-click wooden sword to get player command book
- Recovery compass: lobby teleport
- Compass: survival lobby
- Spyglass: spectate game

Other:
- Restrict auto-spectate to lobby area only
- Add BlockEvent, PlayerInteractEvent, TriggerEvent tag
- Update logo with inverted crafting table

Event-driven architecture; dependency inversion.

#### 0.5.2

Built-in Custom BattleRoyale datapack:
- Add vanilla sounds to game events
- Auto spectate game when logged in
- Disable standing game player lobby teleport

### 0.4.x

#### 0.4.4

- Add cbra:0.4.4 protocol to entity loot zone