import fileinput
# Foosball Dynasty
###
# WINNER: Offense and defense player swap places. Every win constitutes a new
# dynasty

# LOSER: Offense player becomes Defense player of the same team.
# The Defense player of the team that lost the point goes to the back of the line
# and waits for their next chance to play.

# LINE: The person at the front of the line becomes the new Offense player of the team that lost the point.

numOfPlayers = raw_input()
namesOfPlayers = raw_input()
dynasties = list(raw_input())

line = [name for name in namesOfPlayers.split(' ')]
# print line[1] + " " + line[3]
# print "INPUT:"
# print line
# print dynasties
# print "OUTPUT:"

# Teams : [Offense, Defense]
teams = {"W" : [line[0], line[2]], "B" : [line[1], line[3]]}
del line[:4]

# length, team, player1, player2, original ordering
currentStreak = [-1, "", "", "", ""]
longestStreak = 0
longestWinners = list()

def updateStreak(win):
    # Fix the current streak and update the longest winners & streak
    global currentStreak, longestWinners, longestStreak, teams
    if (currentStreak[1] == win): # Another win
        currentStreak[0] += 1
        if (currentStreak[0] > longestStreak and not currentStreak[4] in longestWinners):
            longestWinners.append(currentStreak[4])
    else: # Team lost and streak is over
        if (currentStreak[0] >= longestStreak): # They either set the record, or are equal with it
            if (currentStreak[0] > longestStreak): # New record
                longestStreak = currentStreak[0]
                del longestWinners[:] # Wipe the longest winners list
            longestWinners.append(currentStreak[4]) # Append the original ordering of the winning players

        # Now that we've fixed up our losing team, we need to fix the new currentStreak to be the winners
        currentStreak = [0, win, teams[win][0], teams[win][1], teams[win][0] + " " + teams[win][1]]

def updateTeams(win):
    global line, teams
    loser = "B" if win == "W" else "W"
    tmp = teams[win]
    teams[win] = [ tmp[1], tmp[0] ] # Here, we are just swapping the two players positions.
    tmp = teams[loser][1] # Grab the losing defender, to be added to the back (beginning) of the line
    teams[loser] = [ line.pop(0), teams[loser][0] ] # We kick the defense player from the losing team and add
    # next player in line to the offense position to the losing team.
    line.append(tmp)

for win in dynasties:
    updateStreak(win)
    updateTeams(win)

for winner in longestWinners:
    print winner
