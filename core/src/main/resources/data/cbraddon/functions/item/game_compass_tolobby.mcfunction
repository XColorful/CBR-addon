# 先清除已有的物品
# Clear duplicate item
clear @s minecraft:compass{itemFunction:1b, gameItem:1b}

# --------Start--------

# 传送至大厅
# Teleport to lobby
give @s minecraft:compass{itemFunction:1b, gameItem:1b, display:{Name:'{"translate":"battleroyale.command.cbr_game_tolobby"}'}}

# --------return--------

# 正常执行
# Command.SINGLE_SUCCESS
return 1