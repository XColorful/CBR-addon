# 检查当前是否在游戏中
# Check IGameManager::isInGame
execute store result storage battleroyale:temp isInGame byte 1 run battleroyale api gameManager isInGame
execute if data storage battleroyale:temp {isInGame: 0b} run return 0

# 检查是否为未被淘汰的游戏玩家
# Check ITeamManager::hasStandingGamePlayer
execute store result storage battleroyale:temp isStanding byte 1 run battleroyale api teamManager hasStandingGamePlayer @s
execute if data storage battleroyale:temp {isStanding: 1b} run return 0

# --------Start--------

# 观战游戏
# IGameProcessManager::spectateGame
execute store result storage battleroyale:temp spectateGame byte 1 run battleroyale api gameProcessManager spectateGame @s
execute if data storage battleroyale:temp {spectateGame: 0b} run return 0

# 发送传送回大厅的消息
# Send lobby teleport message
battleroyale api gameLobbyManager sendLobbyTeleportMessage @s false

# --------return--------

# 清理临时数据
# Clear temp data
data remove storage battleroyale:temp isInGame
data remove storage battleroyale:temp isStanding
data remove storage battleroyale:temp spectateGame

# 正常执行
# Comman.SINGLE_SUCCESS
return 1