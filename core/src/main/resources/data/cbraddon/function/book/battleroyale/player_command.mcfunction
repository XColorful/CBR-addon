# 先清除已有的书
# Clear duplicate book
clear @s written_book[custom_data={title:"CBR Player command"}]

# --------Start--------

# <Page 1>
# /cbr game toLobby
# /cbr team join
# /cbr game spectate
# /cbr team leave
# /cbr utility tosurvivallobby

# ---- Github Wiki ----
# https://github.com/XColorful/BattleRoyale/wiki

give @s written_book[custom_data={title:"CBR Player command"},custom_name={"translate":"itemGroup.tab.battleroyale","italic":false,"color":"light_purple"},written_book_content={title:"CBR Player command",author:"CBR addon",pages:[["",{"text":"<","color":"light_purple"},{"translate":"itemGroup.tab.battleroyale","color":"light_purple"},{"text":">","color":"light_purple"},{"text":"\n"},{"translate":"battleroyale.command.cbr_game_tolobby","italic":false},{"text":"\n"},{"text":"/cbr game toLobby","italic":true,"color":"gray","click_event":{"action":"run_command","command":"/cbr game toLobby"}},{"text":"\n"},{"translate":"battleroyale.command.cbr_team_join","italic":false},{"text":"\n"},{"text":"/cbr team join","italic":true,"color":"gray","click_event":{"action":"run_command","command":"/cbr team join"}},{"text":"\n"},{"translate":"battleroyale.command.cbr_game_spectate","italic":false},{"text":"\n"},{"text":"/cbr game spectate","italic":true,"color":"gray","click_event":{"action":"run_command","command":"/cbr game spectate"}},{"text":"\n"},{"translate":"battleroyale.command.cbr_team_leave","italic":false},{"text":"\n"},{"text":"/cbr team leave","italic":true,"color":"gray","click_event":{"action":"run_command","command":"/cbr team leave"}},{"text":"\n"},{"translate":"battleroyale.command.cbr_utility_tosurvivallobby","italic":false},{"text":"\n"},{"text":"/cbr utility tosurvivallobby","italic":true,"color":"gray","click_event":{"action":"run_command","command":"/cbr utility tosurvivallobby"}},{"text":"\n"},{"text":"---- "},{"text":"Github Wiki","color":"blue","underlined":true,"click_event":{"action":"open_url","url":"https://github.com/XColorful/BattleRoyale/wiki"}},{"text":" ----"}]]}]

function cbraddon:sounds/book_sound

# --------return--------

# 正常执行
# Command.SINGLE_SUCCESS
return 1