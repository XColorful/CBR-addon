execute unless score lobbyProtect cbraddon matches 1.. run return 0
# --------Option--------

# --------Start--------

# 检查是否在游戏大厅内
# Check IGameLobbyManager::isInLobbyRange
execute store result storage battleroyale:temp isInLobbyRange byte 1 run battleroyale api gameLobbyManager isInLobbyRange ~ ~ ~
execute if data storage battleroyale:temp {isInLobbyRange: 0b} run return 0

# 检查是否为创造模式
# Check creative mode
execute if entity @s[gamemode=creative] run return 0

# --------return--------

# 清理临时数据
# Clear temp data
data remove storage battleroyale:temp isInLobbyRange

# 取消事件
# Cancel the event
return -1