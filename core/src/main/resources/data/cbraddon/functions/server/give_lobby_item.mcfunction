# --------Start--------

# 检查是否不为未被淘汰的游戏玩家
# Check if not ITeamManager::hasStandingGamePlayer
execute store result storage battleroyale:temp isStanding byte 1 run battleroyale api teamManager hasStandingGamePlayer @s

# (指令书) 加入指定队伍
# (Command book) Join a specified team
execute if data storage battleroyale:temp {isStanding: 0b} run function cbraddon:book/battleroyale/team_join_command

# 传送至大厅
# Teleport to lobby
function cbraddon:item/game_compass_tolobby

# 传送至生存模式大厅
# Teleport to survival mode lobby
function cbraddon:item/survival_compass_tolobby

# --------return--------

# 清理临时数据
# Clear temp data
data remove storage battleroyale:temp isStanding

# 正常执行
# Command.SINGLE_SUCCESS
return 1