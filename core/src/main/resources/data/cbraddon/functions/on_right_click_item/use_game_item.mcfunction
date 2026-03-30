# 检查物品标签
# Check item tag
execute unless data entity @s SelectedItem.tag.gameItem run return 0

# --------Start--------

# 传送至大厅
# Teleport to lobby
execute if data entity @s SelectedItem{id:"minecraft:compass"} run cbr game toLobby
execute if data entity @s SelectedItem{id:"minecraft:compass"} run function cbraddon:on_right_click_item/use_item_function_success_effect
execute if data entity @s SelectedItem{id:"minecraft:compass"} run return -1

# --------return--------

# 正常执行
# Command.SINGLE_SUCCESS
return 1