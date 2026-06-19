package xiao.cbraddon.command.sub;

import com.google.gson.JsonObject;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.world.BossEvent;
import org.jetbrains.annotations.NotNull;
import xiao.battleroyale.data.io.TempDataManager;
import xiao.cbraddon.api.data.CbraTempDataTag;
import xiao.cbraddon.config.common.game.zone.ProgressBarManager;
import xiao.cbraddon.config.common.game.zone.ProgressBarProtocol;

import java.util.Arrays;

import static xiao.cbraddon.command.CommandArg.*;

public class ProgressBarCommand {

    public static LiteralArgumentBuilder<CommandSourceStack> get() {
        return Commands.literal(PROGRESS_BAR)
                .executes(ProgressBarCommand::getProgressBarProtocol)
                .then(Commands.literal(ENABLE)
                        .executes(ProgressBarCommand::enableProgressBarProtocol)
                )
                .then(Commands.literal(DISABLE)
                        .executes(ProgressBarCommand::disenableProgressBarProtocol)
                )
                .then(Commands.literal(ZONE_ID_REGEX)
                        .then(Commands.argument(CbraTempDataTag.ZONE_ID_REGEX, StringArgumentType.string())
                                .executes(ProgressBarCommand::setZoneId_regex)
                        )
                )
                .then(Commands.literal(MOVE_DELAY_COLOR)
                        .then(Commands.argument(PROGRESS_BAR_COLOR, StringArgumentType.string())
                                .suggests(PROGRESS_BAR_COLOR_SUGGESTS)
                                .executes(ProgressBarCommand::setMoveDelay_color)
                        )
                )
                .then(Commands.literal(MOVE_DELAY_OVERLAY)
                        .then(Commands.argument(PROGRESS_BAR_OVERLAY, StringArgumentType.string())
                                .suggests(PROGRESS_BAR_OVERLAY_SUGGESTS)
                                .executes(ProgressBarCommand::setMoveDelay_overlay)
                        )
                )
                .then(Commands.literal(MOVE_TIME_COLOR)
                        .then(Commands.argument(PROGRESS_BAR_COLOR, StringArgumentType.string())
                                .suggests(PROGRESS_BAR_COLOR_SUGGESTS)
                                .executes(ProgressBarCommand::setMoveTime_color)
                        )
                )
                .then(Commands.literal(MOVE_TIME_OVERLAY)
                        .then(Commands.argument(PROGRESS_BAR_OVERLAY, StringArgumentType.string())
                                .suggests(PROGRESS_BAR_OVERLAY_SUGGESTS)
                                .executes(ProgressBarCommand::setMoveTime_overlay)
                        )
                );
    }

    public static final SuggestionProvider<CommandSourceStack> PROGRESS_BAR_COLOR_SUGGESTS = (context, builder) ->
            SharedSuggestionProvider.suggest(
                    Arrays.stream(BossEvent.BossBarColor.values())
                            .map(BossEvent.BossBarColor::getName)
                    , builder);

    public static final SuggestionProvider<CommandSourceStack> PROGRESS_BAR_OVERLAY_SUGGESTS = (context, builder) ->
            SharedSuggestionProvider.suggest(
                    Arrays.stream(BossEvent.BossBarOverlay.values())
                            .map(BossEvent.BossBarOverlay::getName)
                    , builder);

    public static int getProgressBarProtocol(CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = context.getSource();
        @NotNull ProgressBarProtocol protocol = ProgressBarManager.get().getProgressBarProtocol();
        if (protocol != null) {
            source.sendSuccess(() -> Component.literal("ProgressBarProtocol:")
                            .append(String.format("\n%s:%s", CbraTempDataTag.ZONE_ID_REGEX, protocol.zoneId_regex.pattern()))
                            .append(String.format("\n%s:%s", CbraTempDataTag.MOVE_DELAY_COLOR, protocol.moveDelay_color.getName()))
                            .append(String.format("\n%s:%s", CbraTempDataTag.MOVE_DELAY_OVERLAY, protocol.moveDelay_overlay.getName()))
                            .append(String.format("\n%s:%s", CbraTempDataTag.MOVE_TIME_COLOR, protocol.moveTime_color.getName()))
                            .append(String.format("\n%s:%s", CbraTempDataTag.MOVE_TIME_OVERLAY, protocol.moveTime_overlay.getName())),
                    false);
            return Command.SINGLE_SUCCESS;
        } else {
            source.sendFailure(Component.literal(""));
            return 0;
        }
    }
    public static int enableProgressBarProtocol(CommandContext<CommandSourceStack> context) {
        TempDataManager tempDataManager = TempDataManager.get();
        tempDataManager.writeBool(CbraTempDataTag.CBR_ADDON, CbraTempDataTag.ENABLE_PROGRESS_BAR, true);
        tempDataManager.saveTempData();

        ProgressBarManager.get().enable();

        context.getSource().sendSuccess(() -> Component.literal("CbrAddon: ProgressBar enabled"), false);
        return Command.SINGLE_SUCCESS;
    }
    public static int disenableProgressBarProtocol(CommandContext<CommandSourceStack> context) {
        TempDataManager tempDataManager = TempDataManager.get();
        tempDataManager.writeBool(CbraTempDataTag.CBR_ADDON, CbraTempDataTag.ENABLE_PROGRESS_BAR, false);
        tempDataManager.saveTempData();

        ProgressBarManager.get().disable();

        context.getSource().sendSuccess(() -> Component.literal("CbrAddon: ProgressBar disabled"), false);
        return Command.SINGLE_SUCCESS;
    }

    public static int setZoneId_regex(CommandContext<CommandSourceStack> context) {
        TempDataManager tempDataManager = TempDataManager.get();
        JsonObject jsonTag = tempDataManager.getJsonObject(CbraTempDataTag.CBR_ADDON, CbraTempDataTag.PROGRESS_BAR_PROTOCOL);
        if (jsonTag == null) jsonTag = new JsonObject();

        String regex = StringArgumentType.getString(context, CbraTempDataTag.ZONE_ID_REGEX);
        jsonTag.addProperty(CbraTempDataTag.ZONE_ID_REGEX, regex);

        tempDataManager.writeJsonObject(CbraTempDataTag.CBR_ADDON, CbraTempDataTag.PROGRESS_BAR_PROTOCOL, jsonTag);
        tempDataManager.saveTempData();
        context.getSource().sendSuccess(() -> Component.literal(String.format("CbrAddon: Set %s to %s", CbraTempDataTag.ZONE_ID_REGEX, regex)), false);
        return Command.SINGLE_SUCCESS;
    }
    public static int setMoveDelay_color(CommandContext<CommandSourceStack> context) {
        TempDataManager tempDataManager = TempDataManager.get();
        JsonObject jsonTag = tempDataManager.getJsonObject(CbraTempDataTag.CBR_ADDON, CbraTempDataTag.PROGRESS_BAR_PROTOCOL);
        if (jsonTag == null) jsonTag = new JsonObject();

        String color = StringArgumentType.getString(context, PROGRESS_BAR_COLOR);
        jsonTag.addProperty(CbraTempDataTag.MOVE_DELAY_COLOR, color);

        tempDataManager.writeJsonObject(CbraTempDataTag.CBR_ADDON, CbraTempDataTag.PROGRESS_BAR_PROTOCOL, jsonTag);
        tempDataManager.saveTempData();
        context.getSource().sendSuccess(() -> Component.literal(String.format("CbrAddon: Set %s to %s", CbraTempDataTag.MOVE_DELAY_COLOR, color)), false);
        return Command.SINGLE_SUCCESS;
    }
    public static int setMoveDelay_overlay(CommandContext<CommandSourceStack> context) {
        TempDataManager tempDataManager = TempDataManager.get();
        JsonObject jsonTag = tempDataManager.getJsonObject(CbraTempDataTag.CBR_ADDON, CbraTempDataTag.PROGRESS_BAR_PROTOCOL);
        if (jsonTag == null) jsonTag = new JsonObject();

        String overlay = StringArgumentType.getString(context, PROGRESS_BAR_OVERLAY);
        jsonTag.addProperty(CbraTempDataTag.MOVE_DELAY_OVERLAY, overlay);

        tempDataManager.writeJsonObject(CbraTempDataTag.CBR_ADDON, CbraTempDataTag.PROGRESS_BAR_PROTOCOL, jsonTag);
        tempDataManager.saveTempData();
        context.getSource().sendSuccess(() -> Component.literal(String.format("CbrAddon: Set %s to %s", CbraTempDataTag.MOVE_DELAY_OVERLAY, overlay)), false);
        return Command.SINGLE_SUCCESS;
    }
    public static int setMoveTime_color(CommandContext<CommandSourceStack> context) {
        TempDataManager tempDataManager = TempDataManager.get();
        JsonObject jsonTag = tempDataManager.getJsonObject(CbraTempDataTag.CBR_ADDON, CbraTempDataTag.PROGRESS_BAR_PROTOCOL);
        if (jsonTag == null) jsonTag = new JsonObject();

        String color = StringArgumentType.getString(context, PROGRESS_BAR_COLOR);
        jsonTag.addProperty(CbraTempDataTag.MOVE_TIME_COLOR, color);

        tempDataManager.writeJsonObject(CbraTempDataTag.CBR_ADDON, CbraTempDataTag.PROGRESS_BAR_PROTOCOL, jsonTag);
        tempDataManager.saveTempData();
        context.getSource().sendSuccess(() -> Component.literal(String.format("CbrAddon: Set %s to %s", CbraTempDataTag.MOVE_TIME_COLOR, color)), false);
        return Command.SINGLE_SUCCESS;
    }
    public static int setMoveTime_overlay(CommandContext<CommandSourceStack> context) {
        TempDataManager tempDataManager = TempDataManager.get();
        JsonObject jsonTag = tempDataManager.getJsonObject(CbraTempDataTag.CBR_ADDON, CbraTempDataTag.PROGRESS_BAR_PROTOCOL);
        if (jsonTag == null) jsonTag = new JsonObject();

        String overlay = StringArgumentType.getString(context, PROGRESS_BAR_OVERLAY);
        jsonTag.addProperty(CbraTempDataTag.MOVE_TIME_OVERLAY, overlay);

        tempDataManager.writeJsonObject(CbraTempDataTag.CBR_ADDON, CbraTempDataTag.PROGRESS_BAR_PROTOCOL, jsonTag);
        tempDataManager.saveTempData();
        context.getSource().sendSuccess(() -> Component.literal(String.format("CbrAddon: Set %s to %s", CbraTempDataTag.MOVE_TIME_OVERLAY, overlay)), false);
        return Command.SINGLE_SUCCESS;
    }
}
