execute unless score autoSpectate cbraddon matches 1.. run return 0
# --------Option--------

# --------Start--------

# 游戏玩家有游戏进程管理器传送/消息
# IGameProcessManager::teleportAfterGame

# 将旁观的非游戏玩家传送回大厅
# Teleport non gameplayers in spectator mode to lobby
execute as @nongameplayers.player if entity @s[gamemode=spectator] run cbr game toLobby

# --------return--------

# 正常执行
# Command.SINGLE_SUCCESS
return 1