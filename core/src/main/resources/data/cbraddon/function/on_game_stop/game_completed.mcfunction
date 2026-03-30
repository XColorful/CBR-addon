# --------Start--------

execute as @standinggameplayers.player at @s run function cbraddon:on_game_stop/notify_standing_game_stop
execute as @eliminatedgameplayers.player at @s run function cbraddon:on_game_stop/notify_eliminated_game_stop

# --------return--------

# 正常执行
# Command.SINGLE_SUCCESS
return 1