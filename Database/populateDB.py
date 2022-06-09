import mysql.connector
import csv
import random
import time
from itertools import permutations 

class Player:
    def __init__(self,id):
        self.id = id

        self.three_points_in = self.calc_shot_type_in_count(0.6)
        self.two_points_in = self.calc_shot_type_in_count(0.9)
        self.freethrows_in = self.calc_shot_type_in_count(0.8)

        self.three_points_out = self.calc_shot_type_out_count(self.three_points_in+2)
        self.two_points_out = self.calc_shot_type_out_count(self.two_points_in+1)
        self.freethrows_out = self.calc_shot_type_out_count(self.freethrows_in)

        self.off_rebounds = self.calc_off_rebounds()
        self.def_rebounds = self.calc_def_rebounds()

        self.assists = self.calc_assists()
        self.blocks = self.calc_blocks()
        self.steals = self.calc_steals()
        self.turnovers = self.calc_turnovers()
        self.fouls = self.calc_fouls()

    def calc_shot_type_in_count(self, in_likelihood):
        return int((random.random()+in_likelihood)*random.randint(random.randint(0, 1),int(random.randint(3, 4)*in_likelihood)))
        # return random.randint(0, int(0.5+(upper_bound/(2*points*(random.random()+0.8)))))

    def calc_shot_type_out_count(self, out_likelihood):
        return random.randint(0, out_likelihood)

    def calc_off_rebounds(self):
        return random.randint(0, 3)

    def calc_def_rebounds(self):
      return random.randint(0, 6) + random.randint(0, int(random.random()*random.randint(0, 1)))*random.randint(0, 4)

    def calc_assists(self):
        return int(random.randint(0, 4)*random.random()+random.randint(0, 2))

    def calc_blocks(self):
        return int(4*random.random()*random.random())

    def calc_steals(self):
        return int(2*random.random())

    def calc_turnovers(self):
        return int(random.random()+random.randint(0, 2))

    def calc_fouls(self):
        return random.randint(0, 4)+int(random.random())*random.randint(0, 1)

#Adds all products in products.csv to the products table (ignoring duplicates)
def add_cities(conn, cities_file_path):
    db_cursor = conn.cursor()
    #open file and start reading
    with open(cities_file_path, mode='r', encoding="utf8") as cities_file:
        cities_csv = csv.reader(cities_file)
        for city in cities_csv:
            db_cursor.execute("INSERT INTO city (name_gr, name_en) VALUES (%s, %s)", (city[0],city[1],))
        #save changes
        conn.commit()

def add_teams(conn, teams_file_path):
    db_cursor = conn.cursor()
    #open file and start reading
    with open(teams_file_path, mode='r', encoding="utf8") as teams_file:
        teams_csv = csv.reader(teams_file)
        loops=0
        for team in teams_csv:
            loops+=1
            db_cursor.execute("SELECT id FROM city WHERE name_gr=%s", (team[2], ))
            fk_city_ids = db_cursor.fetchone()
            photo_path = "/resources/images/teams/" + str(loops).zfill(4) + ".jpg"
            #Order: 
            db_cursor.execute("INSERT INTO team (city_id, name_gr, name_en, short_name_en, short_name_gr,logo_path) VALUES (%s, %s, %s, %s, %s, %s)", (fk_city_ids[0], team[0], team[1], team[4], team[5],photo_path,))
        #save changes
        conn.commit()

def add_positions(conn, positions_file_path):
    db_cursor = conn.cursor()
    #open file and start reading
    with open(positions_file_path, mode='r', encoding="utf8") as positions_file:
        positions_csv = csv.reader(positions_file)
        for position in positions_csv:
            db_cursor.execute("INSERT IGNORE INTO player_position (code, name) VALUES (%s, %s)", (position[1],position[0],))
        #save changes
        conn.commit()

def add_players(conn, players_file_path):
    db_cursor = conn.cursor()
    #open file and start reading
    with open(players_file_path, mode='r', encoding="utf8") as players_file:
        players_csv = csv.reader(players_file)
        loops=0
        for player in players_csv:
            loops+=1
            db_cursor.execute("SELECT id FROM team WHERE name_gr=%s", (player[6], ))
            fk_team_ids = db_cursor.fetchone()
            photo_path = "/resources/images/players/" + str(loops).zfill(4) + ".jpg"
            #Order: 
            db_cursor.execute("INSERT INTO player (surname_gr, name_gr, surname_en, name_en, player_position_code, team_id, img_path) VALUES (%s, %s, %s, %s, %s, %s, %s)", (player[0], player[1], player[2], player[3], player[5], fk_team_ids[0], photo_path,))
        #save changes
        conn.commit()

def create_championship(conn):
    db_cursor = conn.cursor()
    db_cursor.execute("INSERT INTO championship (name) VALUES (%s)", ("ESAKE",))
    conn.commit()

def create_user(conn):
    db_cursor = conn.cursor()
    db_cursor.execute("INSERT INTO user (username,password) VALUES (%s,%s)", ("admin","superpassword",))
    conn.commit()

def get_teams(conn):
    db_cursor = conn.cursor()
    db_cursor.execute("SELECT id FROM team")
    team_ids = db_cursor.fetchall()
    teams = []
    for uid in team_ids:
        teams.append(uid[0])

    return teams

def get_first_half_fixture(all_matches,first_half_length, matches_per_day):
    original_matchups = all_matches.copy()
    temp_fixtures = []

    for i in range(first_half_length):
        start_time = time.time()
        fixture_finalized = False

        while not fixture_finalized:
            temp_matches = all_matches.copy()
            fixture = []
            for j in range (matches_per_day):
                
                match = random.choice(temp_matches)
                remove_match(match, fixture, temp_matches)

                if len(fixture)==matches_per_day:
                    all_matches = register_matches(all_matches,fixture)
                    temp_fixtures.append(fixture)
                    fixture_finalized = True
                    break

                elif len(temp_matches)==0 and len(fixture)<matches_per_day:
                    break

                if (time.time()-start_time > 1):
                    temp_fixtures = []
                    print("CAUTION: Timeout")
                    return temp_fixtures

    return temp_fixtures

def register_matches(all_matches, fixture):
    for match in fixture:
        all_matches.remove(match)
        second_half_equiv_match = (match[1],match[0])
        all_matches.remove(second_half_equiv_match)
    return all_matches

def remove_match(match, fixture, temp_matches):
    fixture.append(match)
    remove_chosen_team_matches_from_list(temp_matches,match[0],match[1])

def remove_chosen_team_matches_from_list(temp_matches,home_team,away_team):
    temp = temp_matches.copy()
    for match in temp:
        if ((home_team in match) or (away_team in match)):
            temp_matches.remove(match)

def insert_matchups(conn,teams):
    db_cursor = conn.cursor()
    matchups = permutations(teams, 2)
    all_matches = list(matchups)
    fixtures = []

    days = 2*(len(teams)-1)

    while(len(fixtures) < int(days/2)):
        fixtures = get_first_half_fixture(all_matches, int(days/2), int(len(teams)/2))

    for i,j in zip(range(int(days/2),days),range(int(days/2))):
        fixtures.append([])
        for match in fixtures[j]:
            fixtures[i].append((match[1],match[0]))

    # #get championship id outside loop
    db_cursor.execute("SELECT id FROM championship WHERE id=%s", (1, ))
    fk_championship_id = (db_cursor.fetchone())[0]
	    
    for i in range (0,len(fixtures)):
        print(i)
        db_cursor.execute("INSERT INTO round (championship_id) VALUES (%s)", (fk_championship_id,))
        conn.commit()
        db_cursor.execute("SELECT id FROM round WHERE id=%s", (i+1, ))
        round_id = (db_cursor.fetchone())[0]
        j=0
        status = 2
        if i<7:
            status = 0

        for match in fixtures[i]:
            j+=1
            db_cursor.execute("INSERT INTO game (championship_id, round_id, id, home_team_id, away_team_id, game_status) VALUES (%s, %s, %s, %s, %s, %s)", (fk_championship_id, round_id, j, match[0], match[1], status,))
                #save changes
    conn.commit()

def assign_stats_to_players(conn, round_id, game_id, home_team_id, away_team_id):
    home_score = add_player_stats_per_team(conn, home_team_id, round_id, game_id)
    calculate_quarter_score(conn, 1, round_id, game_id, home_team_id, home_score)
    away_score = add_player_stats_per_team(conn, away_team_id, round_id, game_id)
    calculate_quarter_score(conn, 1, round_id, game_id, away_team_id, away_score)

def add_player_stats_per_team(conn, team_id, round_id, game_id):
    db_cursor = conn.cursor()

    db_cursor.execute("SELECT id FROM player WHERE team_id=%s", (team_id,))
    team_players = db_cursor.fetchall()

    score = 0
    for p in team_players:
        player = Player(p[0])
        score+=player.freethrows_in+2*player.two_points_in+3*player.three_points_in

        stmt = ("INSERT IGNORE INTO player_stats (championship_id, round_id, game_id, team_id, player_id, freethrows_in, freethrows_out, \
            two_points_in, two_points_out, three_points_in, three_points_out, offensive_rebounds, defensive_rebounds, \
            assists, blocks, steals, turnovers, fouls) VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)"
            )
        player_tuple = (1, round_id, game_id, team_id, player.id, player.freethrows_in+0, player.freethrows_out+0,
            player.two_points_in, player.two_points_out, player.three_points_in, player.three_points_out, player.off_rebounds, player.def_rebounds,
            player.assists, player.blocks, player.steals, player.turnovers, player.fouls,)
        db_cursor.execute(stmt, player_tuple)

        conn.commit()

    return score

def calculate_quarter_score(conn, championship_id, round_id, game_id, team_id, total_score):
    db_cursor = conn.cursor()
    score_copy = total_score
    
    for i in range(1,5):
        avg_quarter_score = int(score_copy/(5-i))
        quarter_score = random.randint(avg_quarter_score-3,avg_quarter_score+5) if i<4 else score_copy
        score_copy -= quarter_score
        db_cursor.execute("INSERT INTO team_score_per_quarter (championship_id, round_id, game_id, quarter, team_id, quarter_score) VALUES (%s, %s, %s, %s, %s, %s)", (championship_id, round_id, game_id, i, team_id, quarter_score))

    conn.commit()

def assign_round_7_game_3_stats_to_players(conn, round_id, game_id, home_team_id, away_team_id):
    home_score = add_round_7_game_3_player_stats_per_team(conn, home_team_id, round_id, game_id)
    calculate_round_7_quarter_score(conn, 1, round_id, game_id, home_team_id, home_score)
    away_score = add_round_7_game_3_player_stats_per_team(conn, away_team_id, round_id, game_id)
    calculate_round_7_quarter_score(conn, 1, round_id, game_id, away_team_id, away_score)

def add_round_7_game_3_player_stats_per_team(conn, team_id, round_id, game_id):
    db_cursor = conn.cursor()

    db_cursor.execute("SELECT id FROM player WHERE team_id=%s", (team_id,))
    team_players = db_cursor.fetchall()

    team_score = 0 
    for p in team_players:
        player = Player(p[0])
        player_points_scored_so_far = player.freethrows_in+2*player.two_points_in+3*player.three_points_in
        avg_score = int(player_points_scored_so_far/2)
        
        q1_points = random.randint(avg_score,player_points_scored_so_far)
        q1_points_copy = q1_points
        q1_three_points_in = random.randint(0, int(q1_points_copy/3)) if q1_points_copy>=3 else 0
        q1_points_copy -= q1_three_points_in
        q1_two_points_in = random.randint(0, int(q1_points_copy/2)) if q1_points_copy>=2 else 0
        q1_points_copy -= q1_two_points_in
        q1_freethrows_in = q1_points_copy
       
        q2_points = player_points_scored_so_far-q1_points
        q2_points_copy = q2_points
        q2_three_points_in = random.randint(0, int(q2_points_copy/3)) if q2_points_copy>=3 else 0
        q2_points_copy -= q2_three_points_in
        q2_two_points_in = random.randint(0, int(q2_points_copy/2)) if q2_points_copy>=2 else 0
        q2_points_copy -= q2_two_points_in
        q2_freethrows_in = q2_points_copy

        team_score+=(q1_points+q2_points)
        q1_three_points_out = random.randint(0,player.three_points_out)
        q2_three_points_out = player.three_points_out - q1_three_points_out
        q1_two_points_out = random.randint(0,player.two_points_out)
        q2_two_points_out = player.two_points_out - q1_two_points_out
        q1_freethrows_out = random.randint(0,player.freethrows_out)
        q2_freethrows_out = player.freethrows_out - q1_freethrows_out

        stmt = ("INSERT IGNORE INTO ongoing_game_player_stats (championship_id, round_id, game_id, quarter, team_id, player_id, freethrows_in, freethrows_out, \
            two_points_in, two_points_out, three_points_in, three_points_out, offensive_rebounds, defensive_rebounds, \
            assists, blocks, steals, turnovers, fouls) VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)"
            )
        player_tuple = (1, round_id, game_id, 1, team_id, player.id, q1_freethrows_in+0, q1_freethrows_out+0,
            q1_two_points_in, q1_two_points_out, q1_three_points_in, q1_three_points_out, 
            random.randint(0,int(player.off_rebounds/2)+1), random.randint(0,int(player.def_rebounds/2)+1),
            random.randint(0,int(player.assists/2)+1), random.randint(0,int(player.blocks/2)+1), 
            random.randint(0,int(player.steals/2)+1), random.randint(0,int(player.turnovers/2)+1), 
            random.randint(0,int(player.fouls/2)+1))
        db_cursor.execute(stmt, player_tuple)

        player_tuple = (1, round_id, game_id, 1, team_id, player.id, q2_freethrows_in+0, q2_freethrows_out+0,
            q2_two_points_in, q2_two_points_out, q2_three_points_in, q2_three_points_out, 
            random.randint(0,int(player.off_rebounds/2)+1), random.randint(0,int(player.def_rebounds/2)+1),
            random.randint(0,int(player.assists/2)+1), random.randint(0,int(player.blocks/2)+1), 
            random.randint(0,int(player.steals/2)+1), random.randint(0,int(player.turnovers/2)+1), 
            random.randint(0,int(player.fouls/2)+1))
        db_cursor.execute(stmt, player_tuple)

        conn.commit()

    return team_score

def calculate_round_7_quarter_score(conn, championship_id, round_id, game_id, team_id, total_score):
    db_cursor = conn.cursor()
    score_copy = total_score
    
    for i in range(1,3):
        avg_quarter_score = int(score_copy/(5-i))
        quarter_score = random.randint(avg_quarter_score-3,avg_quarter_score+5) if i<4 else score_copy
        score_copy -= quarter_score
        db_cursor.execute("INSERT INTO team_score_per_quarter (championship_id, round_id, game_id, quarter, team_id, quarter_score) VALUES (%s, %s, %s, %s, %s, %s)", (championship_id, round_id, game_id, i, team_id, quarter_score))

    conn.commit()

#--------MAIN----------
#Connect to db
""" Connect to MySQL database """
conn = None
conn = mysql.connector.connect(host='localhost',
                               database='basketball_db',
                               user='root',
                               password='')
if conn.is_connected():
    print('Connected to MySQL database')
else:
    sys.exit('Cannot connect to MySQL database')
       
random.seed()

#Read exported files and add new entries to database
add_cities(conn, 'cities.csv') 
add_teams(conn, 'teams.csv')
add_positions(conn, 'positions.csv')
add_players(conn, 'players.csv')

create_championship(conn)
create_user(conn)
teams = get_teams(conn)
insert_matchups(conn,teams)

db_cursor = conn.cursor()
db_cursor.execute("SELECT round_id, id, home_team_id, away_team_id FROM game WHERE round_id<7 ORDER BY round_id,id")
games = db_cursor.fetchall()
for game in games:
    assign_stats_to_players(conn,game[0],game[1],game[2],game[3])

db_cursor.execute("SELECT round_id, id, home_team_id, away_team_id FROM game WHERE round_id=7 ORDER BY round_id,id")
round_7_games = db_cursor.fetchall()
for game in round_7_games:
    if game[1]<3:
        assign_stats_to_players(conn,game[0],game[1],game[2],game[3])
    elif game[1]==3:
        assign_round_7_game_3_stats_to_players(conn,game[0],game[1],game[2],game[3])
    

conn.close()
print("Connection to MySQL database closed")