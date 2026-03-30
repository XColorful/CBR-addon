# 检测是否为羊毛
# Check wool
execute unless block ~ ~ ~ #minecraft:wool run return 0

# --------Start--------

# Team 1
# minecraft:red_wool
execute if block ~ ~ ~ minecraft:red_wool run battleroyale api teamManager forceJoinTeam @s 1
execute if block ~ ~ ~ minecraft:red_wool run return -1

# Team 2
# minecraft:blue_wool
execute if block ~ ~ ~ minecraft:blue_wool run battleroyale api teamManager forceJoinTeam @s 2
execute if block ~ ~ ~ minecraft:blue_wool run return -1

# Team 3
# minecraft:brown_wool
execute if block ~ ~ ~ minecraft:brown_wool run battleroyale api teamManager forceJoinTeam @s 3
execute if block ~ ~ ~ minecraft:brown_wool run return -1

# Team 4
# minecraft:purple_wool
execute if block ~ ~ ~ minecraft:purple_wool run battleroyale api teamManager forceJoinTeam @s 4
execute if block ~ ~ ~ minecraft:purple_wool run return -1

# Team 5
# minecraft:green_wool
execute if block ~ ~ ~ minecraft:green_wool run battleroyale api teamManager forceJoinTeam @s 5
execute if block ~ ~ ~ minecraft:green_wool run return -1

# Team 6
# minecraft:cyan_wool
execute if block ~ ~ ~ minecraft:cyan_wool run battleroyale api teamManager forceJoinTeam @s 6
execute if block ~ ~ ~ minecraft:cyan_wool run return -1

# Team 7
# minecraft:orange_wool
execute if block ~ ~ ~ minecraft:orange_wool run battleroyale api teamManager forceJoinTeam @s 7
execute if block ~ ~ ~ minecraft:orange_wool run return -1

# Team 8
# minecraft:pink_wool
execute if block ~ ~ ~ minecraft:pink_wool run battleroyale api teamManager forceJoinTeam @s 8
execute if block ~ ~ ~ minecraft:pink_wool run return -1

# Team 9
# minecraft:yellow_wool
execute if block ~ ~ ~ minecraft:yellow_wool run battleroyale api teamManager forceJoinTeam @s 9
execute if block ~ ~ ~ minecraft:yellow_wool run return -1

# Team 10
# minecraft:magenta_wool
execute if block ~ ~ ~ minecraft:magenta_wool run battleroyale api teamManager forceJoinTeam @s 10
execute if block ~ ~ ~ minecraft:magenta_wool run return -1

# Team 11
# minecraft:lime_wool
execute if block ~ ~ ~ minecraft:lime_wool run battleroyale api teamManager forceJoinTeam @s 11
execute if block ~ ~ ~ minecraft:lime_wool run return -1

# Team 12
# minecraft:light_blue_wool
execute if block ~ ~ ~ minecraft:light_blue_wool run battleroyale api teamManager forceJoinTeam @s 12
execute if block ~ ~ ~ minecraft:light_blue_wool run return -1

# Team 13
# minecraft:black_wool
execute if block ~ ~ ~ minecraft:black_wool run battleroyale api teamManager forceJoinTeam @s 13
execute if block ~ ~ ~ minecraft:black_wool run return -1

# Team 14
# minecraft:light_gray_wool
execute if block ~ ~ ~ minecraft:light_gray_wool run battleroyale api teamManager forceJoinTeam @s 14
execute if block ~ ~ ~ minecraft:light_gray_wool run return -1

# Team 15
# minecraft:gray_wool
execute if block ~ ~ ~ minecraft:gray_wool run battleroyale api teamManager forceJoinTeam @s 15
execute if block ~ ~ ~ minecraft:gray_wool run return -1

# Team 16
# minecraft:white_wool
execute if block ~ ~ ~ minecraft:white_wool run battleroyale api teamManager forceJoinTeam @s 16
execute if block ~ ~ ~ minecraft:white_wool run return -1

# --------return--------

return 0