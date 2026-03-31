[English](#English)

# 物品功能

## 木剑
> _/give @s minecraft:wooden_sword_

- 获取[玩家指令](https://github.com/XColorful/CBR-addon/wiki/Command-book#玩家指令)书

创造模式：
- 获取[大逃杀指令书](https://github.com/XColorful/CBR-addon/wiki/Command-book#大逃杀指令书)、[追溯指针](#追溯指针)、[指南针](#指南针)

### 加载时功能

在 _minecraft:load_ 触发：
- 当未[禁用加载时功能](https://github.com/XColorful/CBR-addon/wiki/Option-scoreboard#禁用加载时功能)时，为 _cbraddon:load/get_item_once_ [注册模组事件](https://github.com/XColorful/BattleRoyale/wiki/Function-API-command#注册模组事件)监听`RIGHT_CLICK_ITEM_EVENT`
- 当创造模式右键`木剑`后：
	- 启用所有[实体选择器类型](https://github.com/XColorful/BattleRoyale/wiki/Temp-data-command#启用实体选择器类型)
	- 切换[函数配置](https://github.com/XColorful/BattleRoyale/wiki/Function-config#单个配置) _example_all_event_tags.json_，如无则[生成函数配置](https://github.com/XColorful/BattleRoyale/wiki/Example-command#函数配置)、[重载函数配置](https://github.com/XColorful/BattleRoyale/wiki/Reload-command#函数配置)、[切换函数配置](https://github.com/XColorful/BattleRoyale/wiki/Config-command#服务端配置) _example_all_event_tags.json_
	- 为 _cbraddon:load/get_item_once_ [取消注册模组事件](https://github.com/XColorful/BattleRoyale/wiki/Function-API-command#取消注册模组事件)监听
	- [禁用加载时功能](https://github.com/XColorful/CBR-addon/wiki/Option-scoreboard#禁用加载时功能)
	- 执行 _/reload_

## 游戏物品

### 追溯指针
> _/function cbraddon:item/game_compass_tolobby_

执行[/cbr game toLobby](https://github.com/XColorful/BattleRoyale/wiki/Game-command#传送至大厅)

### 望远镜
> _/function cbraddon:item/game_spyglass_spectate_

执行[/cbr game spectate](https://github.com/XColorful/BattleRoyale/wiki/Game-command#切换旁观模式)

## 生存物品

### 指南针
> _/function cbraddon:item/survival_compass_tolobby_

执行[/cbr utility tosurvivallobby](https://github.com/XColorful/BattleRoyale/wiki/Utility-command#传送至生存模式大厅)

# English

## Wooden sword
> _/give @s minecraft:wooden_sword_

- Obtain the [Player command](https://github.com/XColorful/CBR-addon/wiki/Command-book#Player-command) book:

Creative mode:
- Obtain [BattleRoyale command book](https://github.com/XColorful/CBR-addon/wiki/Command-book#BattleRoyale-command-book), [Recovery compass](#Recovery-compass), [Compass](#Compass)

### Load function

Triggered at _minecraft:load_:
- When [Disable load function](https://github.com/XColorful/CBR-addon/wiki/Option-scoreboard#Disable-load-function), [Register mod event](https://github.com/XColorful/BattleRoyale/wiki/Function-API-command#Register-mod-event) _cbraddon:load/get_item_once_ to listen for `RIGHT_CLICK_ITEM_EVENT`.
- After right-click a `wooden sword` in creative mode：
	- Enable all [entity selector type](https://github.com/XColorful/BattleRoyale/wiki/Temp-data-command#Enable-entity-selector-type).
	- Switch [Function config](https://github.com/XColorful/BattleRoyale/wiki/Function-config#Single-function-config) to _example_all_event_tags.json_; if it does not exist, [Generate function config](https://github.com/XColorful/BattleRoyale/wiki/Example-command#Function-config), [reload function config](https://github.com/XColorful/BattleRoyale/wiki/Reload-command#Function-config), and [switch function config](https://github.com/XColorful/BattleRoyale/wiki/Config-command#Server-config) to _example_all_event_tags.json_.
	- [Unregister mod event](https://github.com/XColorful/BattleRoyale/wiki/Function-API-command#Unregister-mod-event) for _cbraddon:load/get_item_once_.
	- [Disable load function](https://github.com/XColorful/CBR-addon/wiki/Option-scoreboard#Disable-load-function).
	- Execute _/reload_.

## Game item

### Recovery compass
> _/function cbraddon:item/game_compass_tolobby_

Execute [/cbr game toLobby](https://github.com/XColorful/BattleRoyale/wiki/Game-command#Teleport-to-lobby)

### Spyglass
> _/function cbraddon:item/game_spyglass_spectate_

Execute [/cbr game spectate](https://github.com/XColorful/BattleRoyale/wiki/Game-command#Switch-spectator-mode)

## Survival item

### Compass
> _/function cbraddon:item/survival_compass_tolobby_

Execute [/cbr utility tosurvivallobby](https://github.com/XColorful/BattleRoyale/wiki/Utility-command#Teleport-to-survival-mode-lobby)