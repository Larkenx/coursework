def LCSBackTrack(s1, s2):
    lower = [[0 for y in range(len(s2) + 1)] for x in range((len(s1) + 1))]
    middle = [[0 for y in range(len(s2) + 1)] for x in range((len(s1) + 1))]
    upper = [[0 for y in range(len(s2) + 1)] for x in range((len(s1) + 1))]

    backTrack = [[0 for y in range(len(s2) + 1)] for x in range((len(s1) + 1))]
