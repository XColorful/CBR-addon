# 判断是否是正常结算
# Check IGameManager::hasWinner
execute store result storage battleroyale:temp hasWinner byte 1 run battleroyale api gameManager hasWinner

# --------Start--------

execute if data storage battleroyale:temp {hasWinner: 0b} run function cbraddon:on_game_stop/game_interrupted
execute if data storage battleroyale:temp {hasWinner: 1b} run function cbraddon:on_game_stop/game_completed

# 将观战玩家传送回大厅
# Teleport spectator to lobby
function cbraddon:on_game_stop/teleport_spectator_to_lobby

# --------return--------

# 清理临时数据
# Clear temp data
data remove storage battleroyale:temp hasWinner

# 正常执行
# Command.SINGLE_SUCCESS
return 1