# 检查物品标签
# Check item tag
execute unless data entity @s SelectedItem.components."minecraft:custom_data".gameItem run return 0

# --------Start--------

# 传送至大厅
# Teleport to lobby
execute if items entity @s weapon.mainhand minecraft:recovery_compass run cbr game toLobby
execute if items entity @s weapon.mainhand minecraft:recovery_compass run function cbraddon:on_right_click_item/use_item_function_success_effect
execute if items entity @s weapon.mainhand minecraft:recovery_compass run return -1

# [观战]
# [Spectate]
execute if items entity @s weapon.mainhand minecraft:spyglass run cbr game spectate
execute if items entity @s weapon.mainhand minecraft:spyglass run function cbraddon:on_right_click_item/use_item_function_success_effect
execute if items entity @s weapon.mainhand minecraft:spyglass run return -1

# --------return--------

# 正常执行
# Command.SINGLE_SUCCESS
return 1