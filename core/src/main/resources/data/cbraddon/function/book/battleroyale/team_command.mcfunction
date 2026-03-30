# 先清除已有的书
# Clear duplicate book
clear @s written_book[custom_data={title:"CBR Team command"}]

# --------Start--------

# <Page 1>
# /cbr team join
# /cbr team join <teamId>
# /cbr team request
# /cbr team accept request

# <Page 2>
# /cbr team decline request
# /cbr team invite
# /cbr team accept invite
# /cbr team decline invite

# <Page 3>
# /cbr team kick
# /cbr team leave
# /cbr team id
# /cbr team add

# <Page 4>
# /cbr team build
# ---- Github Wiki ----
# https://github.com/XColorful/BattleRoyale/wiki/Team-command

give @s written_book[custom_data={title:"CBR Team command"},custom_name={"translate":"battleroyale.command.cbr_team","italic":false,"color":"light_purple"},written_book_content={title:"CBR Team command",author:"CBR addon",pages:[["",{"text":"<","color":"light_purple"},{"translate":"battleroyale.command.cbr_team","color":"light_purple"},{"text":">","color":"light_purple"},{"text":"\n"},{"translate":"battleroyale.command.cbr_team_join","italic":false},{"text":"\n"},{"text":"/cbr team join","italic":true,"color":"gray","click_event":{"action":"run_command","command":"/cbr team join"}},{"text":"\n"},{"translate":"battleroyale.command.cbr_team_join_teamid","italic":false},{"text":"\n"},{"text":"/cbr team join <teamId>","italic":true,"color":"gray"},{"text":"\n"},{"translate":"battleroyale.command.cbr_team_request","italic":false},{"text":"\n"},{"text":"/cbr team request","italic":true,"color":"gray"},{"text":"\n"},{"translate":"battleroyale.command.cbr_team_accept_request","italic":false},{"text":"\n"},{"text":"/cbr team accept request","italic":true,"color":"gray"}],["",{"text":"<","color":"light_purple"},{"translate":"battleroyale.command.cbr_team","color":"light_purple"},{"text":">","color":"light_purple"},{"text":"\n"},{"translate":"battleroyale.command.cbr_team_decline_request","italic":false},{"text":"\n"},{"text":"/cbr team decline request","italic":true,"color":"gray"},{"text":"\n"},{"translate":"battleroyale.command.cbr_team_invite","italic":false},{"text":"\n"},{"text":"/cbr team invite","italic":true,"color":"gray"},{"text":"\n"},{"translate":"battleroyale.command.cbr_team_accept_invite","italic":false},{"text":"\n"},{"text":"/cbr team accept invite","italic":true,"color":"gray"},{"text":"\n"},{"translate":"battleroyale.command.cbr_team_decline_invite","italic":false},{"text":"\n"},{"text":"/cbr team decline invite","italic":true,"color":"gray"}],["",{"text":"<","color":"light_purple"},{"translate":"battleroyale.command.cbr_team","color":"light_purple"},{"text":">","color":"light_purple"},{"text":"\n"},{"translate":"battleroyale.command.cbr_team_kick","italic":false},{"text":"\n"},{"text":"/cbr team kick","italic":true,"color":"gray"},{"text":"\n"},{"translate":"battleroyale.command.cbr_team_leave","italic":false},{"text":"\n"},{"text":"/cbr team leave","italic":true,"color":"gray","click_event":{"action":"run_command","command":"/cbr team leave"}},{"text":"\n"},{"translate":"battleroyale.command.cbr_team_id","italic":false},{"text":"\n"},{"text":"/cbr team id","italic":true,"color":"gray","click_event":{"action":"run_command","command":"/cbr team id"}},{"text":"\n"},{"translate":"battleroyale.command.cbr_team_add","color":"aqua"},{"text":"\n"},{"text":"/cbr team add","italic":true,"color":"gray"}],["",{"text":"<","color":"light_purple"},{"translate":"battleroyale.command.cbr_team","color":"light_purple"},{"text":">","color":"light_purple"},{"text":"\n"},{"translate":"battleroyale.command.cbr_team_build","color":"aqua"},{"text":"\n"},{"text":"/cbr team build","italic":true,"color":"gray"},{"text":"\n"},{"text":"---- "},{"text":"Github Wiki","color":"blue","underlined":true,"click_event":{"action":"open_url","url":"https://github.com/XColorful/BattleRoyale/wiki/Team-command"}},{"text":" ----"}]]}]

function cbraddon:sounds/book_sound

# --------return--------

# 正常执行
# Command.SINGLE_SUCCESS
return 1