# 先清除已有的物品
# Clear duplicate item
clear @s minecraft:spyglass[custom_data={itemFunction:1b,gameItem:1b}]

# --------Start--------

# [观战]
# [Spectate]
give @s minecraft:spyglass[custom_data={itemFunction:1b,gameItem:1b},custom_name={"translate":"battleroyale.message.spectate","italic":false}]

# --------return--------

# 正常执行
# Command.SINGLE_SUCCESS
return 1