# 检查当前是否在游戏中
# Check IGameManager::isInGame
execute store result storage battleroyale:temp isInGame byte 1 run battleroyale api gameManager isInGame
execute if data storage battleroyale:temp {isInGame: 0b} run return 0

# 检查是否不为未被淘汰的游戏玩家
# Check if not ITeamManager::hasStandingGamePlayer
execute store result storage battleroyale:temp isStanding byte 1 run battleroyale api teamManager hasStandingGamePlayer @s
execute if data storage battleroyale:temp {isStanding: 0b} run return 0

# --------Start--------

# 提示当前在游戏中
# Game is currently in progress, unable to perform this action
tellraw @s [{"text":""}, {"translate":"battleroyale.message.game_in_progress", "color":"red"}, {"text":", "}, {"text": "Click to leave team", "bold":true, "italic":true, "underlined":true, "hoverEvent": {"action":"show_text", "contents":[{"text": "/battleroyale team leave"}]}, "clickEvent": {"action":"run_command", "value": "/battleroyale team leave"}}]

execute at @s run function cbraddon:sounds/deny_sound

# --------return--------

# 清理临时数据
# Clear temp data
data remove storage battleroyale:temp isInGame
data remove storage battleroyale:temp isStanding

# 取消事件
# Cancel the event
return -1