package com.kata;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public record PokerResult(Set<PokerHand> playerListWins) {
    public PokerResult {
        if (playerListWins.isEmpty()) {
            throw new java.lang.IllegalArgumentException(
                    String.format("PokerResult need 1 poker hands to display the result"));
        }
    }

    public String report() {
        Stream<String> playersWinsStream = playerListWins.stream().map(PokerHand::player).map(Player::name);
        if(playerListWins.size() == 1) {
            return playersWinsStream.findAny().get() + " wins";
        }
        return "Egality between : " + playersWinsStream.sorted().collect(Collectors.joining(" and "));
    }

    public String reportDetail() {
        return report() + detail(playerListWins.stream().findAny().get());
    }

    private String detail(PokerHand pokerHand) {
        Combination bestCombinaison = pokerHand.bestCombinaison;
        Optional<Comparable> lessCombinationCardValues = bestCombinaison.lessCombinationCardValues();
        String over = lessCombinationCardValues.isPresent() ? " over " + lessCombinationCardValues.get().toString() : "";
        return " - with " + bestCombinaison.value().label + ": "
                + bestCombinaison.cardValueComparator().toString()
                + over;
    }
}
