[English](#English)

# 扩展机制

## 服务器

### 大厅保护

在游戏大厅范围内，拦截非创造模式玩家：
- `BLOCK_BREAK_EVENT`：破坏方块
- `BLOCK_TOOL_MODIFICATION_EVENT`：用物品修改方块
- `ENTITY_PLACE_BLOCK_EVENT`：放置方块
- `FARMLAND_TRAMPLE_EVENT`：踩踏耕地

## 游戏

### 自动观战

在`PLAYER_LOGGED_IN_EVENT`触发：
- 检查当前是否在游戏中
- 检查是否为未被淘汰的游戏玩家
- 检查是否在游戏大厅内
- 观战游戏
- 发送传送回大厅的消息

### 游戏中拒绝传送回大厅

在`GAME_LOBBY_TELEPORT_EVENT`触发：
- 检查当前是否在游戏中
- 检查是否不为未被淘汰的游戏玩家
- 提示当前在游戏中
- 取消事件

## 效果

### 玩家效果

为游戏玩家添加村民音效：
- `GAME_PLAYER_DAMAGE_FINISH_EVENT`：游戏玩家受伤事件
- `GAME_PLAYER_DOWN_FINISH_EVENT`：游戏玩家倒地事件
- `GAME_PLAYER_REVIVE_FINISH_EVENT`：游戏玩家复活事件
- `GAME_PLAYER_DEATH_FINISH_EVENT`：游戏玩家死亡事件

在`GAME_PLAYER_DEATH_FINISH_EVENT`添加雷声音效

# English

## Server

### Lobby protection

Within the game lobby range, intercept players not in creative mode:
- `BLOCK_BREAK_EVENT`
- `BLOCK_TOOL_MODIFICATION_EVENT`
- `ENTITY_PLACE_BLOCK_EVENT`
- `FARMLAND_TRAMPLE_EVENT`

## Game

### Auto spectate

Triggered at `PLAYER_LOGGED_IN_EVENT`:
- Check IGameManager::isInGame
- Check ITeamManager::hasStandingGamePlayer
- Check IGameLobbyManager::isInLobbyRange
- IGameProcessManager::spectateGame
- Send lobby teleport message

### Reject lobby teleport in game

Triggered at `GAME_LOBBY_TELEPORT_EVENT`:
- Check IGameManager::isInGame
- Check if not ITeamManager::hasStandingGamePlayer
- Game is currently in progress, unable to perform this action
- Cancel the event

## Effect

### Player effects

Add Villager sound effects for game players:
- `GAME_PLAYER_DAMAGE_FINISH_EVENT`
- `GAME_PLAYER_DOWN_FINISH_EVENT`
- `GAME_PLAYER_REVIVE_FINISH_EVENT`
- `GAME_PLAYER_DEATH_FINISH_EVENT`

Add thunder sound effects at `GAME_PLAYER_DEATH_FINISH_EVENT`