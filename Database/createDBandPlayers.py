import mysql.connector
import csv
import mysql.connector
import csv
import random
from itertools import permutations 

#Adds all products in products.csv to the products table (ignoring duplicates)
def add_cities(conn, cities_file_path):
    db_cursor = conn.cursor()
    #open file and start reading
    with open(cities_file_path, mode='r') as cities_file:
        cities_csv = csv.reader(cities_file)
        for city in cities_csv:
            db_cursor.execute("INSERT INTO city (name_gr, name_en) VALUES (%s, %s)", (city[0],city[1],))
        #save changes
        conn.commit()

def add_teams(conn, teams_file_path):
    db_cursor = conn.cursor()
    #open file and start reading
    with open(teams_file_path, mode='r') as teams_file:
        teams_csv = csv.reader(teams_file)
        for team in teams_csv:
            db_cursor.execute("SELECT id FROM city WHERE name_gr=%s", (team[2], ))
            fk_city_ids = db_cursor.fetchone()
            print(fk_city_ids[0])
            #Order: 
            db_cursor.execute("INSERT INTO team (city_id, name_gr, name_en, short_name_en, short_name_gr) VALUES (%s, %s, %s, %s, %s)", (fk_city_ids[0], team[0], team[1], team[4], team[5],))
        #save changes
        conn.commit()

def add_positions(conn, positions_file_path):
    db_cursor = conn.cursor()
    #open file and start reading
    with open(positions_file_path, mode='r') as positions_file:
        positions_csv = csv.reader(positions_file)
        for position in positions_csv:
            db_cursor.execute("INSERT IGNORE INTO player_position (position_code, position_name) VALUES (%s, %s)", (position[1],position[0],))
        #save changes
        conn.commit()

def add_players(conn, players_file_path):
    db_cursor = conn.cursor()
    #open file and start reading
    with open(players_file_path, mode='r') as players_file:
        players_csv = csv.reader(players_file)
        loops=0
        for player in players_csv:
            loops+=1
            db_cursor.execute("SELECT id FROM team WHERE name_gr=%s", (player[6], ))
            fk_team_ids = db_cursor.fetchone()
            print(fk_team_ids[0])
            photo_path = "......" + str(loops)
            #Order: 
            db_cursor.execute("INSERT INTO player (surname_gr, name_gr, surname_en, name_en, player_position_code, team_id, img_path) VALUES (%s, %s, %s, %s, %s, %s, %s)", (player[0], player[1], player[2], player[3], player[5], fk_team_ids[0], photo_path,))
        #save changes
        conn.commit()

def create_fixtures(conn):
    db_cursor = conn.cursor()
    db_cursor.execute("SELECT id FROM team")
    team_ids = db_cursor.fetchall()
    teams = []
    for uid in team_ids:
        teams.append(uid[0])

    return teams

def new_fixture(all_matches,length):

    while True:
        temp_matches = all_matches.copy()
        fixture = []
        for j in range (int(length/2)):
            
            # while True:
            #team_already_has_match=False
            match = random.choice(temp_matches)
            remove_match(match, fixture, temp_matches)

            if len(fixture)==int(length/2):
                all_matches = register_matches(all_matches,fixture)
                return fixture
            elif len(temp_matches)==0 and len(fixture)<int(length/2):
                break

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

    for i in range(int(days/2)):
        fixtures.append([])
        fixtures[i]=new_fixture(all_matches,len(teams))

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
        for match in fixtures[i]:
            j+=1
            db_cursor.execute("INSERT INTO game (championship_id, round_id, game_id, home_team_id, away_team_id) VALUES (%s, %s, %s, %s, %s)", (fk_championship_id, round_id, j, match[0], match[1],))
                #save changes
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
       
#Read exported files and add new entries to database
add_cities(conn, 'cities.csv') 
add_teams(conn, 'teams.csv')
add_positions(conn, 'positions.csv')
add_players(conn, 'players.csv')

teams = create_fixtures(conn)
insert_matchups(conn,teams)

conn.close()
print("Connection to MySQL database closed")

