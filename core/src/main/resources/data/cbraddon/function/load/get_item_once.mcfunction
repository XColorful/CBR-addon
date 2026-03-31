# 检查是否手持木剑
# Check wooden sword
execute unless items entity @s weapon.mainhand minecraft:wooden_sword run return 0

# 检查是否为创造模式
# Check if player is in creative mode
execute if entity @s[gamemode=!creative] run return 0

# --------Start--------

function cbraddon:on_right_click_item/get_item

# 启用实体选择器
# Enable entity selector
battleroyale temp entitySelector true
battleroyale temp entitySelector selector.gameplayers true
battleroyale temp entitySelector selector.nongameplayers.player true
battleroyale temp entitySelector selector.gameplayers.player true
battleroyale temp entitySelector selector.gameplayers.bot true
battleroyale temp entitySelector selector.gameplayers.downed true
battleroyale temp entitySelector selector.standinggameplayers true
battleroyale temp entitySelector selector.nonstandinggameplayers.player true
battleroyale temp entitySelector selector.standinggameplayers.player true
battleroyale temp entitySelector selector.standinggameplayers.bot true
battleroyale temp entitySelector selector.eliminatedgameplayers true
battleroyale temp entitySelector selector.eliminatedgameplayers.player true
battleroyale temp entitySelector selector.eliminatedgameplayers.bot true
tellraw @s {"text": "Enabled all entity selectors"}

# 尝试切换函数配置example_all_event_tags.json
# Attept to switch function config to example_all_event_tags.json
execute store result storage battleroyale:temp switchConfig byte 1 run battleroyale config server function switch example_all_event_tags
execute if data storage battleroyale:temp {switchConfig: 0b} run tellraw @s {"text": "Failed to switch to ./config/battleroyale/server/function/example_all_event_tags.json"}
execute if data storage battleroyale:temp {switchConfig: 0b} run function cbraddon:load/switch_example_config

# 取消自身监听
# Unregister this
battleroyale api functionManager unregisterEvent cbraddon:load/get_item_once false eventType RIGHT_CLICK_ITEM_EVENT

# 禁用加载时功能
# Disable load.mcfunction
scoreboard players set disableLoad cbraddon 1

# 重载以重新加载包含自定义选择器的函数
# Reload to load functions containing custom selectors
tellraw @s {"text": "Start to /reload"}
reload
tellraw @s {"text": "Reloaded"}

# --------return--------

# 清理临时数据
# Clear temp data
data remove storage battleroyale:temp switchConfig

# 正常执行
# Command.SINGLE_SUCCESS
return 1