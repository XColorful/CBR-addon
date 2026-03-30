execute unless score joinTeamByBlock cbraddon matches 1.. run return 0
# --------Option--------

# --------Start--------

# 检查是否在游戏大厅内
# Check IGameLobbyManager::isInLobbyRange
execute store result storage battleroyale:temp isInLobbyRange byte 1 run battleroyale api gameLobbyManager isInLobbyRange ~ ~ ~
execute if data storage battleroyale:temp {isInLobbyRange: 0b} run return 0

# 清理临时数据
# Clear temp data
data remove storage battleroyale:temp isInLobbyRange

execute if score forceJoinTeam cbraddon matches 1.. store result storage battleroyale:temp returnValue int 1 run function cbraddon:on_right_click_block/join_team_by_wool_force
execute if data storage battleroyale:temp {returnValue: -1} run return -1
execute unless score forceJoinTeam cbraddon matches 1.. store result storage battleroyale:temp returnValue int 1 run function cbraddon:on_right_click_block/join_team_by_wool
execute if data storage battleroyale:temp {returnValue: -1} run return -1

# --------return--------

# 清理临时数据
# Clear temp data
data remove storage battleroyale:temp returnValue

return 0