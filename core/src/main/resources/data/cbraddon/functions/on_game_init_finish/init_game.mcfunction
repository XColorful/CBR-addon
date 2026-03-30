# --------Start--------

function cbraddon:on_game_init_finish/init_game_effect

# 给游戏准备阶段物品
# Give game init phase item
execute as @gameplayers run function cbraddon:server/give_game_init_item

# --------return--------

# 正常执行
# Command.SINGLE_SUCCESS
return 1