# 先清除已有的书
# Clear duplicate book
clear @s written_book[custom_data={title:"CBR Team Join command"}]

# --------Start--------

# <Page 1>
# /cbr team join 1
# /cbr team join 2
# /cbr team join 3
# /cbr team join 4

# <Page 2>
# /cbr team join 5
# /cbr team join 6
# /cbr team join 7
# /cbr team join 8

# <Page 3>
# /cbr team join 9
# /cbr team join 10
# /cbr team join 11
# /cbr team join 12

# <Page 4>
# /cbr team join 13
# /cbr team join 14
# /cbr team join 15
# /cbr team join 16

give @s written_book[custom_data={title:"CBR Team Join command"},custom_name={"translate":"battleroyale.command.cbr_team_join_teamid","italic":false,"color":"light_purple"},written_book_content={title:"CBR Team Join command",author:"CBR addon",pages:[["",{"text":"<","color":"light_purple"},{"translate":"battleroyale.command.cbr_team_join_teamid","color":"light_purple"},{"text":">","color":"light_purple"},{"text":"\n"},{"translate":"battleroyale.message.team","with":["1"],"color":"dark_red"},{"text":"\n"},{"text":"/cbr team join 1","italic":true,"color":"gray","click_event":{"action":"run_command","command":"/cbr team join 1"}},{"text":"\n"},{"translate":"battleroyale.message.team","with":["2"],"color":"dark_blue"},{"text":"\n"},{"text":"/cbr team join 2","italic":true,"color":"gray","click_event":{"action":"run_command","command":"/cbr team join 2"}},{"text":"\n"},{"translate":"battleroyale.message.team","with":["3"],"color":"gold"},{"text":"\n"},{"text":"/cbr team join 3","italic":true,"color":"gray","click_event":{"action":"run_command","command":"/cbr team join 3"}},{"text":"\n"},{"translate":"battleroyale.message.team","with":["4"],"color":"dark_purple"},{"text":"\n"},{"text":"/cbr team join 4","italic":true,"color":"gray","click_event":{"action":"run_command","command":"/cbr team join 4"}}],["",{"text":"<","color":"light_purple"},{"translate":"battleroyale.command.cbr_team_join_teamid","color":"light_purple"},{"text":">","color":"light_purple"},{"text":"\n"},{"translate":"battleroyale.message.team","with":["5"],"color":"dark_green"},{"text":"\n"},{"text":"/cbr team join 5","italic":true,"color":"gray","click_event":{"action":"run_command","command":"/cbr team join 5"}},{"text":"\n"},{"translate":"battleroyale.message.team","with":["6"],"color":"dark_aqua"},{"text":"\n"},{"text":"/cbr team join 6","italic":true,"color":"gray","click_event":{"action":"run_command","command":"/cbr team join 6"}},{"text":"\n"},{"translate":"battleroyale.message.team","with":["7"],"color":"red"},{"text":"\n"},{"text":"/cbr team join 7","italic":true,"color":"gray","click_event":{"action":"run_command","command":"/cbr team join 7"}},{"text":"\n"},{"translate":"battleroyale.message.team","with":["8"],"color":"blue"},{"text":"\n"},{"text":"/cbr team join 8","italic":true,"color":"gray","click_event":{"action":"run_command","command":"/cbr team join 8"}}],["",{"text":"<","color":"light_purple"},{"translate":"battleroyale.command.cbr_team_join_teamid","color":"light_purple"},{"text":">","color":"light_purple"},{"text":"\n"},{"translate":"battleroyale.message.team","with":["9"],"color":"yellow"},{"text":"\n"},{"text":"/cbr team join 9","italic":true,"color":"gray","click_event":{"action":"run_command","command":"/cbr team join 9"}},{"text":"\n"},{"translate":"battleroyale.message.team","with":["10"],"color":"light_purple"},{"text":"\n"},{"text":"/cbr team join 10","italic":true,"color":"gray","click_event":{"action":"run_command","command":"/cbr team join 10"}},{"text":"\n"},{"translate":"battleroyale.message.team","with":["11"],"color":"green"},{"text":"\n"},{"text":"/cbr team join 11","italic":true,"color":"gray","click_event":{"action":"run_command","command":"/cbr team join 11"}},{"text":"\n"},{"translate":"battleroyale.message.team","with":["12"],"color":"aqua"},{"text":"\n"},{"text":"/cbr team join 12","italic":true,"color":"gray","click_event":{"action":"run_command","command":"/cbr team join 12"}}],["",{"text":"<","color":"light_purple"},{"translate":"battleroyale.command.cbr_team_join_teamid","color":"light_purple"},{"text":">","color":"light_purple"},{"text":"\n"},{"translate":"battleroyale.message.team","with":["13"],"color":"black"},{"text":"\n"},{"text":"/cbr team join 13","italic":true,"color":"gray","click_event":{"action":"run_command","command":"/cbr team join 13"}},{"text":"\n"},{"translate":"battleroyale.message.team","with":["14"],"color":"gray"},{"text":"\n"},{"text":"/cbr team join 14","italic":true,"color":"gray","click_event":{"action":"run_command","command":"/cbr team join 14"}},{"text":"\n"},{"translate":"battleroyale.message.team","with":["15"],"color":"dark_gray"},{"text":"\n"},{"text":"/cbr team join 15","italic":true,"color":"gray","click_event":{"action":"run_command","command":"/cbr team join 15"}},{"text":"\n"},{"translate":"battleroyale.message.team","with":["16"],"color":"white"},{"text":"\n"},{"text":"/cbr team join 16","italic":true,"color":"gray","click_event":{"action":"run_command","command":"/cbr team join 16"}}]]}]

function cbraddon:sounds/book_sound

# --------return--------

# 正常执行
# Command.SINGLE_SUCCESS
return 1