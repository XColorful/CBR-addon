# 检查物品标签
# Check item tag
execute unless data entity @s SelectedItem.components."minecraft:custom_data".survivalItem run return 0

# --------Start--------

# 传送至生存模式大厅
# Teleport to survival mode lobby
execute if items entity @s weapon.mainhand minecraft:compass run cbr utility tosurvivallobby
execute if items entity @s weapon.mainhand minecraft:compass run function cbraddon:on_right_click_item/use_item_function_success_effect
execute if items entity @s weapon.mainhand minecraft:compass run return -1

# --------return--------

# 正常执行
# Command.SINGLE_SUCCESS
return 1