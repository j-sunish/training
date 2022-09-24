package com.live.demo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class LeagueTable {

	private final List<Match> matches;

	public LeagueTable(final List<Match> matches) {
		this.matches = matches;
	}

	/**
	 * Get the list of table entries using the list of matches.
	 * @return List of LeagueTableEnry
	 */
	public List<LeagueTableEntry> getTableEntries() {
		Map<String, Integer> teamIndex = createTeamIndex();
		List<LeagueTableEntry> leagues = populateScoreTable(teamIndex);
		return leagues;
	}

	/**
	 * Create a team index with key as team name and value as an index starting from 0
	 * @return Map of teamIndea
	 */
	private Map<String, Integer> createTeamIndex() {
		Map<String, Integer> map = new HashMap<>();
		int index = 0;
		for(Match match : matches) {
			index = map.putIfAbsent(match.getHomeTeam(), index) == null ? ++index : index;
			index = map.putIfAbsent(match.getAwayTeam(), index) == null ? ++index : index;
		}
		return map;
	}

	/**
	 * Sort the table based on condition given 
	 * Sort by total points (descending)
	 * Then by goal difference (descending) 
	 * Then by goals scored (descending) 
	 * Then by team name (in alphabetical order)
	 * @param leagues List of LeagueTableEntry
	 * @return sorted List
	 */
	public List<LeagueTableEntry> sort(List<LeagueTableEntry> leagues) {
		Comparator<LeagueTableEntry> comparator = Comparator
				.comparing(LeagueTableEntry::getPoints, Comparator.reverseOrder())
				.thenComparing(LeagueTableEntry::getGoalDifference, Comparator.reverseOrder())
				.thenComparing(LeagueTableEntry::getGoalsFor, Comparator.reverseOrder())
				.thenComparing(LeagueTableEntry::getTeamName);
		return leagues.stream().sorted(comparator).collect(Collectors.toList());
	}

	/**
	 * Populate a two dimension array for the LeagueTableEntry creation using the table index created above.
	 * @param teamIndex 
	 * @return list of table with LeagueTableEntry
	 */
	public List<LeagueTableEntry> populateScoreTable(final Map<String, Integer> teamIndex) {
		//index 0 played, 1 won, 2 drawn, 3 lost, 4 goalsFor, 5 goalsAganist, 6 goalDifference, 7 points
		int[][] tab = new int[teamIndex.size()][8];
		for (Match match : matches) {
			int homeIndex = teamIndex.get(match.getHomeTeam());
			int awayIndex = teamIndex.get(match.getAwayTeam());
			tab[homeIndex][0]++;
			tab[awayIndex][0]++;
			if (match.getHomeScore() == match.getAwayScore()) {
				tab[homeIndex][2]++;
				tab[awayIndex][2]++;
				tab[homeIndex][7] += 1;
				tab[awayIndex][7] += 1;
			} else if (match.getHomeScore() > match.getAwayScore()) {
				tab[homeIndex][1]++;
				tab[awayIndex][3]++;
				tab[homeIndex][7] += 3;
			} else {
				tab[homeIndex][3]++;
				tab[awayIndex][1]++;
				tab[awayIndex][7] += 3;
			}
			tab[homeIndex][4] += match.getHomeScore();
			tab[homeIndex][5] += match.getAwayScore();

			tab[awayIndex][4] += match.getAwayScore();
			tab[awayIndex][5] += match.getHomeScore();
		}
		for (int i = 0; i < tab.length; i++) {
			tab[i][6] = Math.abs(tab[i][4] - tab[i][5]);
		}
		List<LeagueTableEntry> leagues = new ArrayList<>();
		for (Map.Entry<String, Integer> entry : teamIndex.entrySet()) {
			Integer val = entry.getValue();
			LeagueTableEntry leagueTabEntry = new LeagueTableEntry(entry.getKey(), tab[val][0], tab[val][01],
					tab[val][2], tab[val][3], tab[val][4], tab[val][5], tab[val][6], tab[val][7]);
			leagues.add(leagueTabEntry);
		}
		return sort(leagues);
	}
}
