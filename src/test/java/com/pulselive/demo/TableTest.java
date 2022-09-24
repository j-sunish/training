package com.pulselive.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TableTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void oneMatchTwoTeamDraw() {
		Match matchA = new Match("Team A", "Team B", 0, 0);
		LeagueTable table = new LeagueTable(List.of(matchA));
		List<LeagueTableEntry> lentryList = table.getTableEntries();
		assertEquals(lentryList.size(), 2);

		assertEquals(lentryList.get(0).getPlayed(), 1);
		assertEquals(lentryList.get(1).getPlayed(), 1);

		assertEquals(lentryList.get(0).getWon(), 0);
		assertEquals(lentryList.get(1).getWon(), 0);

		assertEquals(lentryList.get(0).getLost(), 0);
		assertEquals(lentryList.get(1).getLost(), 0);

		assertEquals(lentryList.get(0).getGoalsFor(), 0);
		assertEquals(lentryList.get(1).getGoalsFor(), 0);

		assertEquals(lentryList.get(0).getGoalsAgainst(), 0);
		assertEquals(lentryList.get(1).getGoalsAgainst(), 0);

		assertEquals(lentryList.get(0).getGoalDifference(), 0);
		assertEquals(lentryList.get(1).getGoalDifference(), 0);

		assertEquals(lentryList.get(0).getPoints(), 1);
		assertEquals(lentryList.get(1).getPoints(), 1);

		assertEquals(lentryList.get(0).getDrawn(), 1);
		assertEquals(lentryList.get(1).getDrawn(), 1);

		assertEquals(lentryList.get(0).getTeamName(), "Team A");
		assertEquals(lentryList.get(1).getTeamName(), "Team B");
	}

	@Test
	public void testPremierLeague() {

		Match ManUtd_Arsenal = new Match("Man Utd", "Arsenal", 3, 1);
		Match Brighton_Leicester = new Match("Brighton", "Leiester", 5, 2);
		Match AstonVilla_ManCity = new Match("Aston Villa", "Man City", 1, 1);
		Match Brentford_Leed = new Match("Brentford", "Leeds", 5, 2);

		LeagueTable table = new LeagueTable(
				List.of(ManUtd_Arsenal, Brighton_Leicester, AstonVilla_ManCity, Brentford_Leed));

		List<LeagueTableEntry> lentryList = table.getTableEntries();

		assertEquals(lentryList.size(), 8);
		assertEquals(lentryList.get(0).getPlayed(), 1);

		assertEquals(lentryList.get(0).getTeamName(), "Brentford");
		assertEquals(lentryList.get(1).getTeamName(), "Brighton");
		
		assertEquals(lentryList.get(6).getTeamName(), "Leiester");
		assertEquals(lentryList.get(7).getTeamName(), "Arsenal");
	}
	
	@Test
	public void testPremierLeague2() {

		Match ManUtd_Arsenal = new Match("Man Utd", "Arsenal", 3, 1);
		Match Brighton_Leicester = new Match("Brighton", "Leiester", 5, 2);
		
		
		Match AstonVilla_ManCity = new Match("Aston Villa", "Man City", 1, 1);
		Match Brentford_Leed = new Match("Brentford", "Leeds", 5, 2);
		
		Match ManUtd_ManCity = new Match("Man Utd", "Man City", 2, 1);
		Match AstonVilla_Arsenal = new Match("Aston Villa", "Arsenal", 1, 1);

		Match Brighton_Leeds = new Match("Brighton", "Leeds", 4, 1);
		Match Brentford_Leiester = new Match("Brentford", "Leiester", 2, 2);
		
		LeagueTable table = new LeagueTable(
				List.of(ManUtd_Arsenal, Brighton_Leicester, AstonVilla_ManCity, Brentford_Leed, ManUtd_ManCity, AstonVilla_Arsenal, Brighton_Leeds, Brentford_Leiester));

		List<LeagueTableEntry> lentryList = table.getTableEntries();

		assertEquals(lentryList.size(), 8);
		assertEquals(lentryList.get(0).getPlayed(), 2);

		assertEquals(lentryList.get(0).getTeamName(), "Brighton");
	}

	@Test
	public void testSort() {

		LeagueTableEntry lt1 = new LeagueTableEntry("Man Utd", 1, 1, 0, 0, 3, 1, 2, 3);
		LeagueTableEntry lt7 = new LeagueTableEntry("Brentford", 1, 1, 0, 0, 5, 2, 3, 3);
		LeagueTableEntry lt3 = new LeagueTableEntry("Brighton", 1, 1, 0, 0, 5, 2, 3, 3);
		LeagueTableEntry lt5 = new LeagueTableEntry("Aston Villa", 1, 0, 1, 0, 1, 1, 0, 1);
		LeagueTableEntry lt6 = new LeagueTableEntry("Man City", 1, 0, 1, 0, 1, 1, 0, 1);
		
		LeagueTableEntry lt2 = new LeagueTableEntry("Arsenal", 1, 0, 0, 1, 1, 3, 2, 0);
		LeagueTableEntry lt4 = new LeagueTableEntry("Leiester", 1, 0, 0, 1, 2, 5, 3, 0);
		LeagueTableEntry lt8 = new LeagueTableEntry("Leeds", 1, 0, 0, 1, 2, 5, 3, 0);

		List<LeagueTableEntry> list = List.of(lt1, lt2, lt3, lt4, lt5, lt6, lt7, lt8);
		LeagueTable table = new LeagueTable(null);
		list = table.sort(list);
		
		assertNotNull(list);

	}

}
