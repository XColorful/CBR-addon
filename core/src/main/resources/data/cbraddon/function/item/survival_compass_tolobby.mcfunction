# 先清除已有的物品
# Clear duplicate item
clear @s minecraft:compass[custom_data={itemFunction:1b,survivalItem:1b}]

# --------Start--------

# 传送至生存模式大厅
# Teleport to survival mode lobby
give @s minecraft:compass[custom_data={itemFunction:1b,survivalItem:1b},custom_name='{"translate":"battleroyale.command.cbr_utility_tosurvivallobby","italic":false}']

# --------return--------

# 正常执行
# Command.SINGLE_SUCCESS
return 1