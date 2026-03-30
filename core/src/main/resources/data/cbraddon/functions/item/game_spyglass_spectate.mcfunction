# 先清除已有的物品
# Clear duplicate item
clear @s minecraft:spyglass{itemFunction:1b, gameItem:1b}

# --------Start--------

# [观战]
# [Spectate]
give @s minecraft:spyglass{itemFunction:1b, gameItem:1b, display:{Name:'{"translate":"battleroyale.message.spectate"}'}}

# --------return--------

# 正常执行
# Command.SINGLE_SUCCESS
return 1