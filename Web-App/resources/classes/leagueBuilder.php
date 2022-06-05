<?php

/**
 * Class which handles the creation of a league's day matches.
 */
class LeagueBuilder
{
	// Teams in championship as an array. Teams are saved as integers (Unique incrementing ID for DB-related purposes)
	private $participating_teams;
	private $num_teams;			// Number of participating teams
	private $matches_per_day;	// Number of matches per day/round
	private $days;				// Number of days/rounds

	/* 
	 * 3-D array containing all days and their matches
	 * (i-index: Ascending number of day/round
	 * j-index: Ascending number of match in day/round
	 * k-index: Team participating in match)
	 */
	private $fixtures;
			
	private $matchups; //Array of all possible matches (aka a permutation table)


	/**
	 * Class constructor.
	 * 
	 * @param teams The participating teams in an array.
	 */
	function __construct($teams) {
		$this->participating_teams = $teams;
		$this->num_teams = count($this->participating_teams);
		$this->matches_per_day = $this->num_teams / 2;
		$this->days = 2 * ($this->num_teams - 1);
		$this->fixtures = array();
		$this->matchups = array();
	}

	/**
	 * Generates the matches for the league.
	 */
	public function generateDays() {
		// Creates all possible matches.
		// Follows the [n!/(n-k)!] formula, where n is the number of teams, and k is the number of competing teams in a matchup.
		// For k=2, it creates all possible matches for a 1v1 tournament of n participants.
		$this->permutationsPer2();

		// Second half of the championship is conducted with the same matchups as the first half, with inverted home/away statuses
		// While condition checks whether the fixtures array has been filled with all the necessary matches to conduct the first half,
		//  so that the second half of the championship can be created without errors
		while(count($this->fixtures) < ($this->days / 2)) {
			$this->matchups = $this->getFirstHalfFixtures($this->days / 2, $this->matchups);
		}
		
		// At this point, the first half of the championship's fixtures has been successfully created
		// Therefore, the second half's matches will be, as mentioned above, inverted home/away statuses of the first half's matches
		// E.g. Match (1 VS 3) will be repeated in the second half as (3 VS 1)
		$this->getSecondHalfFixtures($this->days / 2);

		//print_r($this->fixtures);
	}

	/**
	 * Creates the 2-permutations of the "participating_teams" array,
	 * AKA: Creates all the possible matches.
	 */
	private function permutationsPer2() {
		// Creates all matches with the i-th team as the home team
		for($i = 0; $i < $this->num_teams; ++$i) {
			$home_team = $this->participating_teams[$i];
			$tmp_array = array(); // Array that temporarily holds the finalized match: [home_team, away_team]

			// Adds the away team in the i-th team's matches (aka in $tmp_array)
			for($j = 0; $j < $this->num_teams; ++$j) {
				$away_team = $this->participating_teams[$j];
				
				// Prevent the team from playing against itself
				if($home_team === $away_team) {
					continue;
				}
				else {
					$tmp_array[] = array($home_team, $away_team);
				}
			}

			// Adds the match in the permutation array
			$this->matchups[] = $tmp_array;
		}
	}

	/**
	 * Creates the first half of the championship.
	 * First, matches are chosen from the 2-permutation pool (aka all possible matchups)
	 * If those matches are feasible, they are added to the fixtures table, and removed from the possible matchups table
	 * This continues until the first half of the championship is created WITHOUT conflicting matches
	 * 
	 * @param first_half_len The length of the first half
	 * @param matchups_cpy The matchup array
	 */
	private function getFirstHalfFixtures($first_half_len, $matchups_cpy) {
		/* Copy of the complete permutation table in case fixtures cannot be created
			This situation can occur if the nested while(true) statement (function explained below)
			runs for longer than 1 second (whereas a successful creation of all fixtures takes less than half a sec)
			In this case, the permutation table becomes empty, and thus has to be replaced
			to create all fixtures all over again until a feasible result is reached */
		$original_matchups = $matchups_cpy;
		
		// Iterator for each day/round (e.g. day/round 3 of the championship)
		for($i = 0; $i < $first_half_len; ++$i) {
			$start_time = time(); // Timeout flag in case there are fixtures that cannot be created, and thus reset the $i loop

			/*
			 *  Loop to choose all matches to be played in fixture $i
			 *  Loop stops when: 
			 * 		Case 1: Fixture has been successfully created
			 * 		Case 2: Permutation table is empty AND there are fixtures that cannot be finalized because of that
			 */
			while(true) {
				/*
				 * Internal copy of permutation table for modification
				 * If the next loop "hangs", it means that the i-th fixture cannot be created,
				 * Therefore, $all_matches_cpy holds all permutations EXCLUDING the ones that 
				 *  have been used already in finalized fixtures.
				 * In turn, that means that the "pool" size is reduced with every successful fixture creation,
				 *  which can lead to case 2.
				 */
				$all_matches_cpy = $matchups_cpy;
				
				// Array to hold all matches in a single day
				$fixture = array();

				// Iterator to choose a single match in the fixture
				for($j = 0; $j < $this->matches_per_day; ++$j) {
					// Next 4 lines choose a random match from the permutation pool
					$random_home_team_index = array_rand($all_matches_cpy);
					$random_home_team_matches = $all_matches_cpy[ $random_home_team_index ];

					$random_match_index = array_rand($random_home_team_matches);
					$random_match = $random_home_team_matches[ $random_match_index ];
					
					// Random match is inserted temporarily
					$fixture[] = $random_match;

					// Returns updated permutation pool, having removed the chosen match (and its inverted counterpart)
					$all_matches_cpy = $this->removeChosenTeamMatchesFromArray($all_matches_cpy, $random_match[0], $random_match[1]);
					
					// Case 1: Fixture has been filled with the right amount of matches that have to be played in each day
					// The created fixture is added to the fixtures table
					// A new iteration of $i has to take place, aka a new day/round has to be created, hence break 2;
					if(count($fixture) == $this->matches_per_day) {
						// Removes all matches in the fixture and their inverse counterparts 
						//  from the actual permutation table, so that they be not be chosen again.
						$matchups_cpy=$this->registerMatches($matchups_cpy,$fixture);
						$this->fixtures[] = $fixture; // Adds the finalized fixture to the fixtures array

						break 2;
					}
					// Case 2: Fixture is incomplete but permutation table is empty, thus the execution hangs
					// While-loop is executed once again to create the i-th fixture from scratch
					elseif (count($all_matches_cpy) == 0 && count($fixture) < $this->matches_per_day) {
						break;
					}
					
					// Timeout check
					// If the execution hangs, this clause allows the program
					//  to start all over again to mitigate infinite loading times.
					// Probability of occurrence is low, but it is almost guaranteed to find a solution
					//  within a given amount of time (usually a few secs).
					// The fixtures array containing all (up to the point of timeout) finalized fixtures is essentially emptied, 
					//  and the original permutation table is returned so that the function can be called once again.
					if((time() - $start_time) > 1) {
						$this->fixtures = array();
						return $original_matchups;
					}
				}
			}
		}
		
		// If the program reaches this line, all needed fixtures have been successfully created
		// Thus the if condition from where this function is called will return TRUE in this case.
		return $matchups_cpy;
	}

	/**
	 * Removes all matches that contain either team_1 or team_2 from the copy of the INTERNAL copy of the permutation table
	 * !!!IMPORTANT: This function only affects the examined round's matchups, which are reset after the fixture is finalized,
	 * and NOT the overall entries in the permutation table (registerMatches achieves the latter)
	 * 
	 * @param all_matches_cpy Array to search the teams
	 * @param team_1 The first team (the database ID as int)
	 * @param team_2 The second team (the database ID as int)
	 */
	private function removeChosenTeamMatchesFromArray($all_matches_cpy, $team_1, $team_2) {
		/*
		 * The permutation table is organized as a 2-D array
		 * The i-th index is an array containing all matches where a certain team is the home team, 
		 * e.g. for team 3 being the home team, thus containing (3,1), (3,2), etc
		 * The j-th index is the opponent/away team, e.g. 1 and 2 respectively in the example above
		 * After removal, the array is normalized with built-in PHP functions (explained in detail below)
		 * $remaining_team_count counts how many rows the array has after normalization
		 */
		$remaining_team_count = count($all_matches_cpy);

		for ($i = 0; $i < $remaining_team_count; ++$i) {
			// Likewise, $remaining_matches_for_team_i_count counts how many matches containing team i as the home team exist
			$remaining_matches_for_team_i_count = count($all_matches_cpy[$i]);

			for ($j = 0; $j < $remaining_matches_for_team_i_count; ++$j) {
				$match = $all_matches_cpy[$i][$j];

				// Condition checks whether team 1 or team 2 are contained in the chosen match
				if(is_int(array_search($team_1, $match)) || is_int(array_search($team_2, $match))) {
					// unset() removes the element from the array, without reindexing the array
					// This does not disturb either loop's behavior, as indexes for non-removed matches are not affected
					// E.g. for team 3 as one of the teams passed as a parameter, all matches that contain it are removed.
					// If team 2 is the home team, the array containing the matches is as follows:
					// [0]->(2,1), [1]->(2,3), [2]->(2,4), etc
					// unset() removes (2,3) from the array, leaving the indexes intact
					// [0]->(2,1), [2]->(2,4), etc
					// For (2,3) to be chosen as a match, $j is equal to 1, which will increment to 2 right after exiting the if-clause
					// Therefore, the execution is not disturbed by the removal of the match
					unset($all_matches_cpy[$i][$j]);
				}
			}

			// Once all matches where team 1 or team 2 are present are removed, the i-th row's elements are reindexed for later iterations
			// The built-in array_values() function essentially achieves the desired reindexing
			// See: https://www.php.net/manual/en/function.array-values.php
			// E.g. //[0]->(2,1), [2]->(2,4) becomes [0]->(2,1), [1]->(2,4), as expected
			$all_matches_cpy[$i] = array_values($all_matches_cpy[$i]); 
		} 

		/*
		 * Likewise, once the outer loop stops running, all empty rows are removed, and the array is reindexed
		 * E.g. for team 3 as one of the teams passed as a parameter, before any checks, the array is as follows:
		 * [0]->[Matches where Home team==1, >0 elements], [1]->[Matches where Home team==2, >0 elements]
		 * [2]->[Matches where Home team==3, EMPTY**], [3]->[Matches where Home team==4, >0 elements]
		 * 		
		 * 		(**SIDE NOTE: In reality, row [2] contains an empty array, thus not making it truly empty.
		 * 		To mitigate that, the built-in array_filter() function without a callback parameter removes all empty entries from an array.
		 * 		See: https://www.php.net/manual/en/function.array-filter.php
		 * 			Parameters section: No callback passed
		 * 			Examples section: 	Example #2 without callback
		 * 		END OF SIDE NOTE)
		 * 
		 * array_values() once again reindexes the array, which becomes as follows:
		 * [0]->[Matches where Home team==1, >0 elements], [1]->[Matches where Home team==2, >0 elements], [2]->[Matches where Home team==4, >0 elements]
		 * The renewed internal copy of the permutation table is returned after that.
		 */
		$all_matches_cpy = array_values( array_filter($all_matches_cpy) );

		return $all_matches_cpy; 
	}

	/**
	 * Removes all matches contained in the fixture parameter passed, as well as their inverted counterparts 
	 * from the actual the permutation table semi-permanently (semi- in case the execution hangs and has to restart from scratch)
	 * 
	 * @param all_matchups The matchups array from which we want the match to be removed
	 * @param fixture The match to be removed
	 */
	private function registerMatches($all_matchups, $fixture) {
		//Counts the number of matches contained in the fixture, and then used in the iteration
		$fixture_count = count($fixture);

		for ($fixture_num = 0; $fixture_num < $fixture_count; ++$fixture_num) {
			//Same as removeChosenTeamMatchesFromArray
			$remaining_team_count = count($all_matchups);

			for ($i = 0; $i < $remaining_team_count ; ++$i) {
				$remaining_matches_for_team_i_count = count($all_matchups[$i]);

				for ($j = 0; $j < $remaining_matches_for_team_i_count ; ++$j) {
					$match = $all_matchups[$i][$j];

					// Checks whether the chosen match contains both teams (either in the same order as in the fixture or inverted)
					if( 
						(($fixture[$fixture_num][0] === $match[0]) && ($fixture[$fixture_num][1] === $match[1])) 
						|| (($fixture[$fixture_num][0] === $match[1]) && ($fixture[$fixture_num][1] === $match[0]))
					)
					{
						// Same as removeChosenTeamMatchesFromArray
						unset($all_matchups[$i][$j]);
					}
				}
				// Same as removeChosenTeamMatchesFromArray
				$all_matchups[$i] = array_values($all_matchups[$i]);
			}
		}
		// The renewed version of the permutation table is returned, having removed all matches in the fixture and their counterparts
		$all_matchups = array_values( array_filter($all_matchups) );

		return $all_matchups; 
	}

	/**
	 * Generates the counterparts of the first half's matches, which become the matches for the second half
	 * 
	 * @param second_half_len The length of the second half.
	 */
	private function getSecondHalfFixtures($second_half_len) {
		/*
		 * Outer loop iterates from the last index of $this->fixtures + 1, creating the new round,
		 * and runs until all counterparts have been created
		 * The matchups for this new round are the same as their counterparts, only with the home/away statuses inverted
		 */
		for ($i = $second_half_len; $i < $this->days; ++$i) {
			/*
			 * The numerical difference between corresponding rounds in the first and seconds halves is ($this->days / 2),
			 * aka the parameter passed as $second_half_len.
			 * Since the counterpart contains the same matches as the original matchup, the row containing the original fixture is copied.
			 * The "exchange" occurs inside the inner loop.
			 */
			$this->fixtures[$i] = $this->fixtures[$i - $second_half_len];

			for ($j = 0; $j < count($this->fixtures[$i]); ++$j) {
				$this->fixtures[$i][$j][0] = $this->fixtures[$i-$second_half_len][$j][1];
				$this->fixtures[$i][$j][1] = $this->fixtures[$i-$second_half_len][$j][0];
			}
		}
	}

	/**
	 * Returns the fixtures which "generateDays" has generated.
	 * 
	 * @return fixtures The generated fixtures.
	 */
	public function getFixtures() {
		return $this->fixtures;
	}
}

?>