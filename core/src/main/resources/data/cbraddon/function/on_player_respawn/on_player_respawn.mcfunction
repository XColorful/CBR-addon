execute unless score loginItem cbraddon matches 1.. run return 0
# --------Option--------

# 检查是否为游戏玩家
# Check ITeamManager::getGamePlayerId
execute store result storage battleroyale:temp isGamePlayer int 1 run battleroyale api teamManager getGamePlayerId @s

# 检查是否不为未被淘汰的游戏玩家
# Check if not ITeamManager::hasStandingGamePlayer
execute store result storage battleroyale:temp isStanding byte 1 run battleroyale api teamManager hasStandingGamePlayer @s

# --------Start--------

function cbraddon:server/give_login_item

# 额外给予游戏玩家大厅物品
# Give lobby item for game player
execute unless data storage battleroyale:temp {isGamePlayer: 0} if data storage battleroyale:temp {isStanding: 0b} run function cbraddon:server/give_lobby_item

# --------return--------

# 清理临时数据
# Clear temp data
data remove storage battleroyale:temp isGamePlayer
data remove storage battleroyale:temp isStanding

# 正常执行
# Command.SINGLE_SUCCESS
return 1