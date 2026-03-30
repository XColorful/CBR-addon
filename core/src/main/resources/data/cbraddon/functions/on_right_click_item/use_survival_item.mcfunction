# 检查物品标签
# Check item tag
execute unless data entity @s SelectedItem.tag.survivalItem run return 0

# --------Start--------

# 传送至生存模式大厅
# Teleport to survival mode lobby
execute if data entity @s SelectedItem{id:"minecraft:compass"} run cbr utility tosurvivallobby
execute if data entity @s SelectedItem{id:"minecraft:compass"} run function cbraddon:on_right_click_item/use_item_function_success_effect
execute if data entity @s SelectedItem{id:"minecraft:compass"} run return -1

# --------return--------

# 正常执行
# Command.SINGLE_SUCCESS
return 1