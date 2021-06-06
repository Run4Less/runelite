package net.runelite.client.plugins.bonnerrunnerai.commands;


import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class Commands {

    @Inject
    ActiveHostCommand activeHostCommand;

    @Inject
    HostCommand hostCommand;

    @Inject
    RunnerDiscordCommand runnerDiscordCommand;

    @Inject
    UnHostCommand unHostCommand;

    @Inject
    AdminHostCommand adminHostCommand;

    @Inject
    AdminUnHostCommand adminUnHostCommand;

    @Inject
    PriceCommand priceCommand;

    @Inject
    AfkPriceCommand afkPriceCommand;

    @Inject
    TickPriceCommand tickPriceCommand;

    @Inject
    ExplainBoneRunningCommand explainBoneRunningCommand;

    @Inject
    AfkExplainCommand afkExplainCommand;

    @Inject
    TickExplainCommand tickExplainCommand;

    @Inject
    DiscordInfoCommand discordInfoCommand;

    @Inject
    InsuranceCommand insuranceCommand;

    @Inject
    ExplainInsuranceCommand explainInsuranceCommand;

    @Inject
    HostStatsCommand hostStatsCommand;

    @Inject
    QuestionsCommand questionsCommand;

    @Inject
    KickCommand kickCommand;

    public List<Command> getList() {
        ArrayList<Command> commands = new ArrayList<>();
        commands.add(activeHostCommand);
        commands.add(hostCommand);
        commands.add(unHostCommand);
        commands.add(runnerDiscordCommand);
        commands.add(adminHostCommand);
        commands.add(adminUnHostCommand);
        commands.add(priceCommand);
        commands.add(afkPriceCommand);
        commands.add(tickPriceCommand);
        commands.add(explainBoneRunningCommand);
        commands.add(afkExplainCommand);
        commands.add(tickExplainCommand);
        commands.add(discordInfoCommand);
        commands.add(insuranceCommand);
        commands.add(explainInsuranceCommand);
        commands.add(hostStatsCommand);
        commands.add(questionsCommand);
        commands.add(kickCommand);
        return commands;
    }
}
