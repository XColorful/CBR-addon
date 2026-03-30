# 检查是否不为未被淘汰的游戏玩家
# Check if not ITeamManager::hasStandingGamePlayer
execute store result storage battleroyale:temp isStanding byte 1 run battleroyale api teamManager hasStandingGamePlayer @s

# 检查当前是否在游戏中
# Check IGameManager::isInGame
execute store result storage battleroyale:temp isInGame byte 1 run battleroyale api gameManager isInGame

# --------Start--------

# 玩家指令
# Player command
function cbraddon:book/battleroyale/player_command

# (指令书) 加入指定队伍
# (Command book) Join a specified team
execute if data storage battleroyale:temp {isStanding: 0b} run function cbraddon:book/battleroyale/team_join_command

# 传送至大厅
# Teleport to lobby
function cbraddon:item/game_compass_tolobby

# 传送至生存模式大厅
# Teleport to survival mode lobby
function cbraddon:item/survival_compass_tolobby

# [观战]
# Spectate
execute if data storage battleroyale:temp {isInGame: 1b} run function cbraddon:item/game_spyglass_spectate

# --------return--------

# 清理临时数据
# Clear temp data
data remove storage battleroyale:temp isStanding
data remove storage battleroyale:temp isInGame

# 正常执行
# Command.SINGLE_SUCCESS
return 1