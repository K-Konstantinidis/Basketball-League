import mysql.connector
import random
from itertools import permutations 

class Player:
    def __init__(self,id,seed):
        self.id = id

        self.three_points_in = self.calc_shot_type_in_count(3, 3)
        self.two_points_in = self.calc_shot_type_in_count(4, 2)
        self.freethrows_in = self.calc_shot_type_in_count(2, 1)

        self.three_points_out = self.calc_shot_type_out_count(self.three_points_in+4)
        self.two_points_out = self.calc_shot_type_out_count(self.two_points_in+3)
        self.freethrows_out = self.calc_shot_type_out_count(self.freethrows_in+3)

        self.off_rebounds = self.calc_off_rebounds(seed)
        self.def_rebounds = self.calc_def_rebounds(seed)

        self.assists = self.calc_assists()
        self.blocks = self.calc_blocks()
        self.steals = self.calc_steals()
        self.turnovers = self.calc_turnovers()
        self.fouls = self.calc_fouls()

    def calc_shot_type_in_count(self, upper_bound, points):
        return random.randint(0, int(0.8+(upper_bound/(2*points*(random.random()+0.1)))))

    def calc_shot_type_out_count(self, out_likelihood):
        return random.randint(0, out_likelihood)

    def calc_off_rebounds(self,seed):
        total_approx = int(seed / (random.random()+2))
        return int((random.randint(0,total_approx)/(random.randint(3,4))))

    def calc_def_rebounds(self,seed):
        total_approx = int(seed / (random.random()+2))
        return int((random.randint(0,total_approx)/(random.randint(2,3)*(random.random()+0.3))))

    def calc_assists(self):
        return int(random.randint(0, 4)*random.random()+random.randint(0, 2))

    def calc_blocks(self):
        return int(6*random.random()*random.random())

    def calc_steals(self):
        return int(2.5*random.random())

    def calc_turnovers(self):
        return int(random.random()+random.randint(0, 3))

    def calc_fouls(self):
        return int((random.random()+0.7)*random.randint(0,6))

def assign_stats_to_players(conn, round_id, game_id, home_team_id, away_team_id):
    add_player_stats_per_team(conn, home_team_id, round_id, game_id)
    add_player_stats_per_team(conn, away_team_id, round_id, game_id)

def add_player_stats_per_team(conn, team_id, round_id, game_id):
    db_cursor = conn.cursor()

    db_cursor.execute("SELECT id FROM player WHERE team_id=%s", (team_id,))
    team_players = db_cursor.fetchall()

    for p in team_players:
        player = Player(p[0],random.randint(50, 100))
        stmt = ("INSERT INTO player_stats (championship_id, round_id, game_id, team_id, player_id, freethrows_in, freethrows_out, \
            two_points_in, two_points_out, three_points_in, three_points_out, offensive_rebounds, defensive_rebounds, \
            assists, blocks, steals, turnovers, fouls) VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)"
            )
        player_tuple = (1, round_id, game_id, team_id, player.id, player.freethrows_in+0, player.freethrows_out+0,
            player.two_points_in, player.two_points_out, player.three_points_in, player.three_points_out, player.off_rebounds, player.def_rebounds,
            player.assists, player.blocks, player.steals, player.turnovers, player.fouls,)
        db_cursor.execute(stmt, player_tuple)
        conn.commit()

        


#--------MAIN----------
#Connect to db
""" Connect to MySQL database """
conn = None
conn = mysql.connector.connect(host='localhost',
                               database='esake_management_app',
                               user='root',
                               password='')
if conn.is_connected():
    print('Connected to MySQL database')
else:
    sys.exit('Cannot connect to MySQL database')

random.seed()
db_cursor = conn.cursor()
db_cursor.execute("SELECT round_id, game_id, home_team_id, away_team_id FROM game WHERE round_id<3 ORDER BY round_id,game_id")
games = db_cursor.fetchall()
for game in games:
    assign_stats_to_players(conn,game[0],game[1],game[2],game[3])

#teams = create_fixtures(conn)
#insert_matchups(conn,teams)

conn.close()
print("Connection to MySQL database closed")

